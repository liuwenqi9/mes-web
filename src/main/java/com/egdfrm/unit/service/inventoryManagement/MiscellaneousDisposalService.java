package com.egdfrm.unit.service.inventoryManagement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.egdfrm.core.security.realm.UserAuthenRealm.ShiroUser;
import com.egdfrm.core.service.BaseService;
import com.egdfrm.extend.common.DbReturnParameter;
import com.egdfrm.extend.common.JsonObjectConverTools;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.mapper.expand.inventoryManagement.MiscellaneousDisposalMapper;
import com.egdfrm.unit.mapper.standard.MesTransactionsInterfaceMapper;
import com.egdfrm.unit.model.standard.MesTransactionsInterface;
import com.egdfrm.unit.model.standard.MesWipBarcodes;
import com.egdfrm.unit.model.standard.MtlSystemItemsB;
import com.egdfrm.unit.service.commonSetting.CargospaceService;
import com.egdfrm.unit.service.commonSetting.SublibraryService;
import com.egdfrm.unit.service.pda.PDACommonService;


/**
 * @author sjf
 * @date 2017年1月12日 
 * 杂项事务处理service
 */
@Service
public class MiscellaneousDisposalService extends BaseService {

	@Autowired
	private MiscellaneousDisposalMapper mdm;
	@Autowired
	private PDACommonService pdas;
	@Autowired
	private MesTransactionsInterfaceMapper mesTransactionsInterfaceMapper;
	@Autowired
	private SublibraryService sublibraryService;
	@Autowired
	private CargospaceService cargospaceService;
	/**
	 * @author sjf
	 * @date 2017年1月12日 
	 * @return
     * 获取【事务处理类型】列表
	 */
	public List<Map<String, Object>> getTransactionTypeList() {
		List<Map<String, Object>> a=mdm.getTransactionTypeList();
		return a;
	}

	/**
	 * @author sjf
	 * @date 2017年1月12日 
	 * @param orgId
	 * @return
	 * 搜索【来源】
	 */
	public List<Map<String, Object>> searchSourceByName(ShiroUser su,String sourceName) { 
		String sourceLike = (StringUtils.isEmpty(sourceName)) ? null : "%"
				+ sourceName.trim() + "%";
		return mdm.searchSourceByName(su.getOrgId(),sourceLike);
	}

	/**
	 * @author sjf
	 * @date 2017年1月12日 
	 * @param barCode
	 * @return
     * 根据产品条码获取物料信息
	 */
	public Map<String, Object> searchWipInfoByBarCode(ShiroUser su,String barCode) {
		/**
		 * 验证条码有效性
		 */
		Map<String, Object> retMap = new HashMap<String, Object>();
		
		if(StringUtils.isEmpty(barCode)){
			retMap.put(MesConstants.RESULT, MesConstants.ERROR);
			retMap.put(MesConstants.ERROR_MESSAGE, "输入条码不能为空");
			return retMap;
		}
		if(barCode.charAt(0) != MesConstants.NX_PRODUCT){
			retMap.put(MesConstants.RESULT, MesConstants.ERROR);
			retMap.put(MesConstants.ERROR_MESSAGE, "只能输入内销产品条码");
			return retMap;
		}
		/**
		 * 获取产品信息
		 */
		Map<String, Object> wipMap = new HashMap<String, Object>();
		wipMap=pdas.getWipBarcodes(barCode,su.getOrgId().toString());
		String wipFlg=(String) wipMap.get(MesConstants.RESULT);
		if(MesConstants.ERROR.equals(wipFlg)){
			String[] errVal=(String[]) wipMap.get(MesConstants.RETVAL);
			String errMsg=errVal[1];
			retMap.put(MesConstants.RESULT, MesConstants.ERROR);
			retMap.put(MesConstants.ERROR_MESSAGE, errMsg);
			return retMap;
		}
		/**
		 * 正常获取产品信息后 获取物料信息
		 */
		MesWipBarcodes mwb = (MesWipBarcodes) wipMap.get("mwb");
		Map<String, Object> msibMap = new HashMap<String, Object>();
		msibMap=pdas.getInventoryItemInfo(mwb.getPrimaryItemId(), su.getOrgId());
		String msibFlg=(String) msibMap.get(MesConstants.RESULT);
		if(MesConstants.ERROR.equals(msibFlg)){
			String[] errVal=(String[]) msibMap.get(MesConstants.RETVAL);
			String errMsg=errVal[1];
			retMap.put(MesConstants.RESULT, MesConstants.ERROR);
			retMap.put(MesConstants.ERROR_MESSAGE, errMsg);
			return retMap;
		}
		MtlSystemItemsB msib=(MtlSystemItemsB) msibMap.get("ms");
		/**
		 * 返回
		 */
		retMap.put(MesConstants.RESULT, MesConstants.SUCCESS);
		retMap.put("startQuantity", mwb.getStartQuantity());
		retMap.put("lotNo", msib.getSegment1());
		retMap.put("description", msib.getDescription());
		return retMap;
	}

	/**
	 * @author 兰继明
	 * @date 2017年2月12日 
	 * @param su
	 * @param mesTransactionsInterface
	 * @return
     * 杂项事务处理提交1//由于写到MES成功则需要commit，所以对MES和EBS两系统的写入分两个方法写
	 */
	public Map<String, Object> miscellaneousDisposalSave(ShiroUser su,
			String params) {
		Map<String, Object> result= new HashMap<String, Object>();
		List<MesTransactionsInterface> mesTransactionsInterface_s=JsonObjectConverTools.jsonToList(params,MesTransactionsInterface.class);
		//每行数据加序列，组织id，状态，时间，
		//process_id(序列:mes_transactions_process_s)
		String sql1="select mes_transactions_process_s.nextval from dual";
		String processId=getImqm().queryOneRowOneValue(sql1);
		for (MesTransactionsInterface mesTransactionsInterface : mesTransactionsInterface_s) {
			// 先验证表单正确性
			// 开始校验//-帐户别名三步第一步的存储过程已经作了校验，再此不作较验
			// // 验证事务处理类型
			// List<Map<String, Object>>
			// transactionType_results=mdm.checkTransactionType(
			// mesTransactionsInterface.getTransactionType());
			// if
			// (transactionType_results==null||transactionType_results.size()<=0)
			// {
			// result.put("flag", "E");
			// result.put("errorMsg",
			// "事务处理类型"+mesTransactionsInterface.getTransactionType()+"不正确！");
			// return result;
			// }
			// // 验证来源
			// List<Map<String, Object>>
			// sourceCode_results=mdm.checkSourceByNames(su.getOrgId(),
			// mesTransactionsInterface.getSourceCode());
			// if (sourceCode_results==null||sourceCode_results.size()<=0) {
			// result.put("flag", "E");
			// result.put("errorMsg",
			// "来源"+mesTransactionsInterface.getSourceCode()+"不正确！");
			// return result;
			// }
			// // 验证子库
			// List<Map<String, Object>>
			// subinventoryCode_results=sublibraryService.searchSublibrary(
			// mesTransactionsInterface.getSubinventoryCode());
			// if
			// (subinventoryCode_results==null||subinventoryCode_results.size()<=0)
			// {
			// result.put("flag", "E");
			// result.put("errorMsg",
			// "子库"+mesTransactionsInterface.getSubinventoryCode()+"不正确！");
			// return result;
			// }
			// // 验证货位
			// List<Map<String, Object>>
			// locattionCode_results=cargospaceService.searchCargospace(mesTransactionsInterface.getSubinventoryCode(),mesTransactionsInterface.getLocattionCode());
			// if
			// (locattionCode_results==null||locattionCode_results.size()<=0)
			// {
			// result.put("flag", "E");
			// result.put("errorMsg",
			// "货位"+mesTransactionsInterface.getLocattionCode()+"不正确！");
			// return result;
			// }
			// // 验证产品条码
			// String barCode=mesTransactionsInterface.getBarcodeText();
			// if(StringUtils.isEmpty(barCode)){
			// result.put("flag", "E");
			// result.put("errorMsg", "输入条码为空！");
			// return result;
			//
			// }
			// if(barCode.charAt(0) != MesConstants.NX_PRODUCT){
			// result.put("flag", "E");
			// result.put("errorMsg", "条码"+barCode+"不正确，"+"只能输入内销产品条码！");
			// return result;
			// }
			// /**
			// * 获取产品信息
			// */
			// Map<String, Object> wipMap = new HashMap<String, Object>();
			// wipMap=pdas.getWipBarcodes(barCode,su.getOrgId().toString());
			// String wipFlg=(String) wipMap.get(MesConstants.RESULT);
			// if(MesConstants.ERROR.equals(wipFlg)){
			// result.put("flag", "E");
			// result.put("errorMsg", "产品条码不正确！");
			// return result;
			// }
			// //验证通过
			// 每行数据加序列，组织id，状态，时间，
			mesTransactionsInterface.setProcessId(new BigDecimal(processId));
			// 得到transactionId
			String sql2 = "select mes_transactions_s.nextval from dual";
			String transactionId = getImqm().queryOneRowOneValue(sql2);
			mesTransactionsInterface.setTransactionId(new BigDecimal(
					transactionId));
			// 得到组织ID
			mesTransactionsInterface.setOrganizationId(su.getOrgId());
			// 状态
			mesTransactionsInterface.setStatusFlag("R");
			// 时间
			mesTransactionsInterface.setLastUpdateDate(new Date());
			mesTransactionsInterface.setCreationDate(new Date());
			mesTransactionsInterface.setTransactionDate(new Date());
			// 创建人//更新人
			mesTransactionsInterface.setCreatedBy(su.getUserId());
			mesTransactionsInterface.setLastUpdatedBy(su.getUserId());
			// 开始插入数据
			mesTransactionsInterfaceMapper
					.insertSelective(mesTransactionsInterface);
		}

		// <!--帐户别名三步1检查写入事务接口表结果(验证表单)-->
		// 保存后，用这个包检查，如果x_return_status<>'S' 就报出错误，界面保留不动

		DbReturnParameter dbreturn1 = new DbReturnParameter();
		DbReturnParameter dbreturn2 = new DbReturnParameter();
		
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		Map<String, Object> paramMap2 = new HashMap<String, Object>();
		
		paramMap1.put("processId", processId);
		paramMap1.put(MesConstants.DBRETURN, dbreturn1);
		mdm.callCheckTransactionInsert(paramMap1);
		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn1.getxStatus())) {
			result.put("flag", "E");
			result.put("errorMsg", dbreturn1.getxMessage());
			TransactionAspectSupport.currentTransactionStatus()
			.setRollbackOnly();
			return result;
		}
		// <!--帐户别名三步2写入MES-->
		// 如果x_return_status<>'S' 就报出错误，界面保留不动，成功就commit;
		paramMap2.put("processId", processId);
		paramMap2.put(MesConstants.DBRETURN, dbreturn2);
		mdm.callTransactionInsertMES(paramMap2);
		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn2.getxStatus())) {
			result.put("flag", "E");
			result.put("errorMsg", dbreturn2.getxMessage());
			TransactionAspectSupport.currentTransactionStatus()
			.setRollbackOnly();
			return result;
		}
		result.put("flag", "S");
		//成功则写入processId，以作为参数传给第二个杂项事务处理提交方法
		result.put("processId", processId);
		return result;
	}
		
		
		
		/**
		 * @author 兰继明
		 * @date 2017年2月12日 
		 * @return
	     * 杂项事务处理提交2//由于写到MES成功则需要commit，所以对MES和EBS两系统的写入分两个方法写
		 */
		public Map<String, Object> miscellaneousDisposalSave2(String processId) {
		Map<String, Object> result= new HashMap<String, Object>();
		DbReturnParameter dbreturn3 = new DbReturnParameter();
		Map<String, Object> paramMap3 = new HashMap<String, Object>();
		// <!--帐户别名三步3写入EBS-->
		// 写EBS，如果不成功就返回警告EBS未处理成功，请联系IT处理。commmit;
		// TODO 返回错误也提交？
		paramMap3.put("processId", processId);
		paramMap3.put(MesConstants.DBRETURN, dbreturn3);
		mdm.callTransactionInsertEBS(paramMap3);
		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn3.getxStatus())) {
			result.put("flag", "E");
			result.put("errorMsg", dbreturn3.getxMessage());
//			TransactionAspectSupport.currentTransactionStatus()
//			.setRollbackOnly();
			return result;
		}
		result.put("flag", "S");
		return result;
		}
}
