package com.egdfrm.unit.service.pda;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import com.egdfrm.core.mapper.standard.TtApplicationUserMapper;
import com.egdfrm.core.model.standard.TtApplicationUser;
import com.egdfrm.extend.common.DbReturnParameter;
import com.egdfrm.extend.common.JsonObjectConverTools;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.mapper.expand.CommonMapper;
import com.egdfrm.unit.mapper.expand.pda.PDACommonMapper;
import com.egdfrm.unit.mapper.expand.pda.PickingMapper;
import com.egdfrm.unit.mapper.standard.MesItemLocattionsMapper;
import com.egdfrm.unit.mapper.standard.MesPackingHeadersMapper;
import com.egdfrm.unit.mapper.standard.MtlTxnRequestHeadersMapper;
import com.egdfrm.unit.model.expand.pda.SaleOutStorage;
import com.egdfrm.unit.model.standard.MesPackingHeaders;
import com.egdfrm.unit.model.standard.MesWipBarcodes;
import com.egdfrm.unit.model.standard.MtlTxnRequestHeaders;
/**
 * @author sjf
 * @date 2016年12月15日 挑库SERVICE
 **/
@Service
public class PickingService {
	/**
	 * <p>
	 * Field mphm: 包装箱头表dao
	 * </p>
	 */
	@Autowired
	private MesPackingHeadersMapper mphm;
	/**
	 * <p>
	 * Field milm: 货位表dao
	 * </p>
	 */
	@Autowired
	private MesItemLocattionsMapper milm;
	/**
	 * <p>
	 * Field mrhm: 挑库dao
	 * </p>
	 */
	@Autowired
	private MtlTxnRequestHeadersMapper mrhm;

	/**
	 * <p>
	 * Field comm: 共通dao
	 * </p>
	 */
	@Autowired
	private CommonMapper comm;
	/**
	 * <p>
	 * Field pdam: PDA共通dao
	 * </p>
	 */
	@Autowired
	private PDACommonMapper pdam;
	/**
	 * <p>
	 * Field taum: 用户
	 * </p>
	 */
	@Autowired
	private TtApplicationUserMapper taum;
	/**
	 * <p>
	 * Field pdacService: PDA共通Service
	 * </p>
	 */
	@Autowired
	private PDACommonService pdacService;
	/**
	 * <p>
	 * Field pim: 挑库mapper
	 * </p>
	 */
	@Autowired
	private PickingMapper pim;

	/**
	 * @author sjf
	 * @date 2016年12月22日
	 * @param input
	 * @return PDA挑库-验证挑库单号
	 */
	public String[] pickingCheckNumber(String[] input) {
		String[] retVal = new String[2];
		// 登录用户
		// String loginName = input[0];
		// 组织ID
		String orgId = input[1];
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织为空，请联系管理员";
			return retVal;
		}
		// 挑库单号
		String pickNumber = input[2];
		/*
		 * 获取挑库信息
		 */
		Map<String, Object> pickMap = new HashMap<String, Object>();
		pickMap = pdacService.getPickingInfo(pickNumber, orgId);
		String wipFlag = (String) pickMap.get(MesConstants.RESULT);
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(wipFlag)) {
			retVal = (String[]) pickMap.get(MesConstants.RETVAL);
			return retVal;
		} else {
			retVal[0] = MesConstants.SUCCESS;
			return retVal;
		}
	}

	/**
	 * @author sjf
	 * @date 2016年12月22日
	 * @param input
	 * @return PDA挑库-扫描条码
	 */
	public String[] pickingScanBarcode(String[] input) {
		String[] retVal = new String[5];
		// 登录用户
		// String loginName = input[0];
		// 组织ID
		String orgId = input[1];
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织为空，请联系管理员";
			return retVal;
		}
		// 挑库单号
		String pickNumber = input[2];
		// 条码
		String barCode = input[3];
		/*
		 * 获取挑库信息
		 */
		Map<String, Object> pickMap = new HashMap<String, Object>();
		pickMap = pdacService.getPickingInfo(pickNumber, orgId);
		String pickFlag = (String) pickMap.get(MesConstants.RESULT);
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(pickFlag)) {
			retVal = (String[]) pickMap.get(MesConstants.RETVAL);
			return retVal;
		}
		/*
		 * 获取包装信息/产品信息
		 */
		// 物料ID
		BigDecimal invId = null;
		// 数量
		BigDecimal num = null;
		// 包装箱/产品ID
		BigDecimal wipBarcodeId = null;
		Map<String, Object> pwMap = new HashMap<String, Object>();
		pwMap=pdacService.getPackOrWip(barCode,orgId);
		String pwFlag = (String) pwMap.get(MesConstants.RESULT);
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(pwFlag)) {
			retVal = (String[]) pwMap.get(MesConstants.RETVAL);
			return retVal;
		}
		//取得是包装还是产品
		int type=  (int) pwMap.get(MesConstants.TYPE);
		if(type==MesConstants.PACK){
			MesPackingHeaders mph = (MesPackingHeaders) pwMap.get(MesConstants.OBJECT);
			invId = mph.getInventoryItemId();
			num = pdam.getNumByPackBarcodeId(mph.getPackingBarcodeId());
			wipBarcodeId=mph.getPackingBarcodeId();
		}else if(type==MesConstants.WIP){
			MesWipBarcodes mwb  = (MesWipBarcodes) pwMap.get(MesConstants.OBJECT);
			invId = mwb.getPrimaryItemId();
			num = new BigDecimal(1);
			wipBarcodeId=mwb.getWipBarcodeId();
		}
		// 获取物料信息
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgId", new BigDecimal(orgId));
		paramMap.put("invId", invId);
		SaleOutStorage sos = pim.getNum(paramMap);
		if (sos == null) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "物料信息不存在";
			return retVal;
		}
		/**
		 * check状态
		 */
		DbReturnParameter dbreturn = new DbReturnParameter();
		paramMap = new HashMap<String, Object>();
		paramMap.put("barcodeId", wipBarcodeId);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		pdam.callReprodCheck(paramMap);
		//check不通过则报错
		if(!MesConstants.SUCCESS.equals(dbreturn.getxStatus())){
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage();
			return retVal;
		}
		//检查搬运的货物是否是属于此搬运单
		
		DbReturnParameter dbreturn2 = new DbReturnParameter();
		paramMap = new HashMap<String, Object>();
		paramMap.put("moveOrderId", ((MtlTxnRequestHeaders)pickMap.get("mrh")).getHeaderId());
		paramMap.put("barcodeId", wipBarcodeId);
		paramMap.put(MesConstants.DBRETURN, dbreturn2);
		pim.callCheckIsBarcodeBelongMoveOrder(paramMap);
		//check不通过则报错
		if(!MesConstants.SUCCESS.equals(dbreturn2.getxStatus())){
			retVal[0] = dbreturn2.getxStatus();
			retVal[1] = dbreturn2.getxMessage();
			return retVal;
		}
		// 成功取得
		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = num.toString();
		retVal[2] = sos.getLotNo();
		retVal[3] = sos.getStyle();
		retVal[4] = sos.getDesr();
		return retVal;
	}

	/**
	 * @author sjf
	 * @date 2016年12月22日
	 * @param jsonString
	 * @return PDA挑库-提交
	 */
	public String[] pickingCommit(String jsonString) {
		// 返回值
		String[] retVal = new String[2];
		if (StringUtils.isEmpty(jsonString)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "数据异常";
			return retVal;
		}
		// 接收转换json数据
		SaleOutStorage initSos = JsonObjectConverTools.jsonToObject(jsonString,
				SaleOutStorage.class);
		List<SaleOutStorage> sList = initSos.getSaleOutStorage();

		// 登录用户
		String loginName = initSos.getUserid();
		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
		if (user == null) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录用户不存在，请联系管理员";
			return retVal;
		}
		// 组织ID
		String orgId = initSos.getWarehouse();
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织不存在，请联系管理员";
			return retVal;
		}
		// 取得唯一键
		BigDecimal processId = comm.getSeqByName("mes_transactions_process_s");
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", user.getUserId());
		paramMap.put("processId", processId);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		// 循环挑库信息
		for (SaleOutStorage sos : sList) {
			/*
			 * 获取挑库信息
			 */
			Map<String, Object> pickMap = new HashMap<String, Object>();
			pickMap = pdacService.getPickingInfo(sos.getOutNo(), orgId);
			String pickFlag = (String) pickMap.get(MesConstants.RESULT);
			// 如果出错，则直接返回
			if (MesConstants.ERROR.equals(pickFlag)) {
				retVal = (String[]) pickMap.get(MesConstants.RETVAL);
				return retVal;
			}
			MtlTxnRequestHeaders mrh = (MtlTxnRequestHeaders) pickMap
					.get("mrh");

			/*
			 * 获取包装信息/产品信息
			 */
			// 包装箱/产品ID
			BigDecimal barcodeId = null;
			Map<String, Object> pwMap = new HashMap<String, Object>();
			pwMap=pdacService.getPackOrWip(sos.getSnNo(),orgId);
			String pwFlag = (String) pwMap.get(MesConstants.RESULT);
			// 如果出错，则直接返回
			if (MesConstants.ERROR.equals(pwFlag)) {
				retVal = (String[]) pwMap.get(MesConstants.RETVAL);
				return retVal;
			}
			//取得是包装还是产品
			int type=  (int) pwMap.get(MesConstants.TYPE);
			if(type==MesConstants.PACK){
				MesPackingHeaders mph = (MesPackingHeaders) pwMap.get(MesConstants.OBJECT);
				barcodeId = mph.getPackingBarcodeId();
			}else if(type==MesConstants.WIP){
				MesWipBarcodes mwb  = (MesWipBarcodes) pwMap.get(MesConstants.OBJECT);
				barcodeId = mwb.getWipBarcodeId();
			}
			/**
			 * 循环挑库
			 */
			paramMap.put("moveOrderId", mrh.getHeaderId());
			paramMap.put("barcodeId", barcodeId);
			pim.callTranInsertTransactionTemp(paramMap);
			// 返回错误信息
			if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
				retVal[0] = dbreturn.getxStatus();
				retVal[1] = dbreturn.getxMessage();
				TransactionAspectSupport.currentTransactionStatus()
						.setRollbackOnly();
				return retVal;
			}
		}
		/**
		 * 挑库暂存
		 */
		pim.callProcessMesTransaction(paramMap);
		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage();
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			return retVal;
		}
		/**
		 * 挑库ESB操作
		 */
		pim.callMesBarcodesMoveOrder(paramMap);
		retVal[0] = dbreturn.getxStatus();
		retVal[1] = dbreturn.getxMessage();
		return retVal;
	}

}
