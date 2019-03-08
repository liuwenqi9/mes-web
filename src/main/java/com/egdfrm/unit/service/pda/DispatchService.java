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
import com.egdfrm.unit.mapper.expand.pda.DispatchMapper;
import com.egdfrm.unit.mapper.expand.pda.PDACommonMapper;
import com.egdfrm.unit.mapper.standard.MesPackingHeadersMapper;
import com.egdfrm.unit.model.expand.pda.ShipConfirm;
import com.egdfrm.unit.model.standard.MesPackingHeaders;
/**
 * @author sjf
 * @date 2016年12月15日 PDA发运确认SERVER
 **/
@Service
public class DispatchService {
	/**
	 * <p>
	 * Field taum: 用户
	 * </p>
	 */
	@Autowired
	private TtApplicationUserMapper taum;
	/**
	 * <p>
	 * Field mphm: 包装箱头表dao
	 * </p>
	 */
	@Autowired
	private MesPackingHeadersMapper mphm;
	/**
	 * <p>
	 * Field comm: 共通dao
	 * </p>
	 */
	@Autowired
	private CommonMapper comm;
	/**
	 * <p>
	 * Field pdac: PDA共通dao
	 * </p>
	 */
	@Autowired
	private PDACommonMapper pdam;
	/**
	 * <p>
	 * Field dm: 发运确认DAO
	 * </p>
	 */
	@Autowired
	private DispatchMapper dm;

	
	/**
	 * <p>
	 * Field pdas: PDA共通service
	 * </p>
	 */
	@Autowired
	private PDACommonService pdas;

	/**
	 * @author sjf
	 * @date 2016年12月23日
	 * @param input
	 * @return PDA发运确认-扫描
	 */
	public String[] dispatchScan(String[] input) {
		// 返回值
		String[] retVal = new String[4];
		// 登录用户
		// String loginName = input[0];
		// 组织ID
		String orgId = input[1];
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织为空，请联系管理员";
			return retVal;
		}
		// 快递单
		// String expressNum = input[2];
		// 包装条码
		String packBarcode = input[3];
		/*
		 * 获取包装箱信息
		 */
		Map<String, Object> packMap = new HashMap<String, Object>();
		packMap = pdas.getPackingHeaders(packBarcode, orgId);
		String packFlag = (String) packMap.get(MesConstants.RESULT);
		MesPackingHeaders mph = new MesPackingHeaders();
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(packFlag)) {
			retVal = (String[]) packMap.get(MesConstants.RETVAL);
			return retVal;
		}
		mph = (MesPackingHeaders) packMap.get("mph");
		BigDecimal num = pdam.getNumByPackBarcodeId(mph.getPackingBarcodeId());
		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = num.toString();
		return retVal;
	}
	/**
	 * @author sjf
	 * @date 2016年12月23日
	 * @param jsonString
	 * @return PDA发运确认-提交
	 */
	public String[] dispatchCommit(String jsonString) { 
		// 返回值
		String[] retVal = new String[2];
		if (StringUtils.isEmpty(jsonString)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "数据异常";
			return retVal;
		}
		// 接收转换json数据
		ShipConfirm initSc = JsonObjectConverTools.jsonToObject(jsonString,
				ShipConfirm.class);
		// 登录用户
		String loginName = initSc.getUserid();
		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
		if (user == null) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录用户不存在，请联系管理员";
			return retVal;
		}
		// 组织ID
		String orgId = initSc.getWarehouse();
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织不存在，请联系管理员";
			return retVal;
		}
		List<ShipConfirm> scList = initSc.getShipConfirm();
		//快递单号
		//2017.02.22,临时决定不记录快递单号了，用别的方式来记录快递单号
//		String expressNumber=scList.get(0).getExpressNo();
//		if (StringUtils.isEmpty(expressNumber)) {
//			retVal[0] = MesConstants.ERROR;
//			retVal[1] = "快递单号不能为空";
//			return retVal;
//		}
		// 取得唯一键
		BigDecimal processId = comm.getSeqByName("mes_transactions_process_s");
		// 循环产品信息
		for (ShipConfirm sc : scList) { 
			/**
			 * 第一个条码 获取包装箱信息
			 */
			Map<String, Object> packMap = new HashMap<String, Object>();
			packMap = pdas
					.getPackingHeaders(sc.getPackNo(), orgId);
			String packFlag = (String) packMap.get(MesConstants.RESULT);
			MesPackingHeaders mph = new MesPackingHeaders();
			// 如果出错，则直接返回
			if (MesConstants.ERROR.equals(packFlag)) {
				retVal = (String[]) packMap.get(MesConstants.RETVAL);
				return retVal;
			}
			mph = (MesPackingHeaders) packMap.get("mph");
			/**
			 * 循环插入
			 */
			DbReturnParameter dbreturn = new DbReturnParameter();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", user.getUserId());
			paramMap.put("processId", processId);
			paramMap.put("barCodeId", mph.getPackingBarcodeId());
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			dm.callInsertTransactionTemp(paramMap);
			
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
		 * 处理mes ，insert tmp用于EBS操作
		 */
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("processId", processId);
		//paramMap.put("expressNumber",null );
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		dm.callProcessMesTransaction(paramMap);

		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage();
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			return retVal;
		}
		/**
		 * ebs
		 */
		dm.callBarcodesAutoShip(paramMap);
		retVal[0] = dbreturn.getxStatus();
		retVal[1] = dbreturn.getxMessage();
		return retVal; 
	}

}
