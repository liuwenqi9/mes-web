package com.egdfrm.unit.service.pda;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.egdfrm.core.mapper.standard.TtApplicationUserMapper;
import com.egdfrm.core.model.standard.TtApplicationUser;
import com.egdfrm.extend.common.DbReturnParameter;
import com.egdfrm.extend.common.JsonObjectConverTools;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.mapper.expand.CommonMapper;
import com.egdfrm.unit.mapper.expand.pda.OnLineRepairMapper;
import com.egdfrm.unit.mapper.expand.pda.PDACommonMapper;
import com.egdfrm.unit.mapper.expand.pda.StocktakingMapper;
import com.egdfrm.unit.mapper.standard.MesItemLocattionsMapper;
import com.egdfrm.unit.mapper.standard.MesPackingHeadersMapper;
import com.egdfrm.unit.model.expand.pda.OnLineRepair;
import com.egdfrm.unit.model.expand.pda.OnLineRepairData;
import com.egdfrm.unit.model.standard.MesPackingHeaders;
import com.egdfrm.unit.model.standard.MesWipBarcodes;
import com.egdfrm.unit.ws.PDAService;

/**
 * @author sjf
 * @date 2016年12月20日  上线返修
 */
@Service
public class OnLineRepairService {
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
	 * Field sm: 盘点dao
	 * </p>
	 */
	@Autowired
	private StocktakingMapper sm;
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
	 * Field om: 上线返修dao
	 * </p>
	 */
	@Autowired
	private OnLineRepairMapper om;

	/**
	 * <p>
	 * Field pdacService: PDA共通Service
	 * </p>
	 */
	@Autowired
	private PDACommonService pdacService;


	/**
	 *  PDA上线返修-扫描领料单
	 *  @date 2018-07-13
	 * @param input {userNo,wareHouse，MaterialRequisitio}
	 * @return
	 */
	public String[] onLinerepairMaterialRequisitionScan(String[] input){
		String[] retVal = new String[2];
		String userNo = input[0]; //用户名
		String orgId = input[1]; //组织id
		String docNumber = input[2]; //领料单
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织为空，请联系管理员";
			return retVal;
		}
		//materialRequisition 验证
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("docNumber", docNumber);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		om.callPdaCheckDoc(paramMap);
		retVal[0] = dbreturn.getxStatus();
		retVal[1] = dbreturn.getxMessage();
		return  retVal;
	}

	/**
	 *  验证领料单和条码
	 * @param input
	 * @return
	 */
	public String onLinerepairScan(String[] input) {
		OnLineRepairData olrd = new OnLineRepairData();
		String[] retVal = new String[4];
		String loginName = input[0];
		// 领料单
		String docNumber = input[2];
		// 条码
		String barCode = input[3];
		// 组织ID
		String orgId = input[1];
		if (StringUtils.isEmpty(orgId)) {
			olrd.setResultCode(MesConstants.ERROR);
			olrd.setResultMsg("登录组织为空，请联系管理员");
			return JsonObjectConverTools.objectToJson(olrd);
		}
		if (StringUtils.isEmpty(docNumber)) {
			olrd.setResultCode(MesConstants.ERROR);
			olrd.setResultMsg("领料单不能为空！");
			return JsonObjectConverTools.objectToJson(olrd);
		}
		if (StringUtils.isEmpty(barCode)) {
			olrd.setResultCode(MesConstants.ERROR);
			olrd.setResultMsg("条码不能为空！");
			return JsonObjectConverTools.objectToJson(olrd);
		}

		/**
		 * 获取包装箱/产品信息
		 */
		// 包装箱/产品ID
		BigDecimal wipBarcodeId = null;
		Map<String, Object> pwMap = new HashMap<String, Object>();
		pwMap = pdacService.getPackOrWip(barCode, orgId);
		String pwFlag = (String) pwMap.get(MesConstants.RESULT);
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(pwFlag)) {
			retVal = (String[]) pwMap.get(MesConstants.RETVAL);
			olrd.setResultCode("E");
			olrd.setResultMsg(retVal[1]);
			return JsonObjectConverTools.objectToJson(olrd);
		}
		// 取得是包装还是产品
		int type = (int) pwMap.get(MesConstants.TYPE);
		if (type == MesConstants.PACK) {
			MesPackingHeaders mph2 = (MesPackingHeaders) pwMap.get(MesConstants.OBJECT);
			wipBarcodeId = mph2.getPackingBarcodeId();
		} else if (type == MesConstants.WIP) {
			System.out.println("条码类型："+type);
			MesWipBarcodes mwb = (MesWipBarcodes) pwMap.get(MesConstants.OBJECT);
			wipBarcodeId = mwb.getWipBarcodeId();
		}
		/**
		 *  调用call检验 领料单和条码
		 */
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("barCodeId", wipBarcodeId);
		paramMap.put("docNumber", docNumber);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		om.callPdaReprodCheck(paramMap);
		if("E".equals(dbreturn.getxStatus())){
			//验证不通过时
			olrd.setResultCode(dbreturn.getxStatus());
			olrd.setResultMsg(dbreturn.getxMessage());
			return JsonObjectConverTools.objectToJson(olrd);

		}

		/**
		 * 获取相关信息
		 */
		List<OnLineRepairData> result = om.getRepairStyle(wipBarcodeId);
		if (result.isEmpty()) {
			olrd.setResultCode("E");
			if (type == MesConstants.PACK) {
				olrd.setResultMsg("该包装箱为空，请检查");
			} else if (type == MesConstants.WIP) {
				olrd.setResultMsg("该产品不存在，请检查");
			}
			return JsonObjectConverTools.objectToJson(olrd);
		}
		olrd.setResultData(result);
		olrd.setResultCode(MesConstants.SUCCESS);
		return JsonObjectConverTools.objectToJson(olrd);
	}


	/**
	 *  pda 上线返修提交( 包含领料单)
	 * @param jsonString
	 * @return
	 */
	public String[] onLinerepairCommit(String jsonString) {
		// 返回值
		String[] retVal = new String[2];
		if (StringUtils.isEmpty(jsonString)) {
			retVal[0] = "E";
			retVal[1] = "数据异常";
			return retVal;
		}
		// 接收转换json数据
		OnLineRepair initOr = JsonObjectConverTools.jsonToObject(jsonString,
				OnLineRepair.class);
		List<OnLineRepair> oList = initOr.getOnLineRepair();

		// 登录用户
		String loginName = initOr.getUserid();
		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
		if (user == null) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录用户不存在，请联系管理员";
			return retVal;
		}
		// 组织ID
		String orgId = initOr.getWarehouse();
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织不存在，请联系管理员";
			return retVal;
		}
		// 取得唯一键
		BigDecimal processId = comm.getSeqByName("mes_transactions_process_s");
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 循环提交
		for (OnLineRepair olr : oList) {
			/**
			 * 获取包装箱/产品信息
			 */
			// 包装箱/产品ID
			BigDecimal wipBarcodeId = null;
			Map<String, Object> pwMap = new HashMap<String, Object>();
			pwMap = pdacService.getPackOrWip(olr.getSnPackNo(), orgId);
			String pwFlag = (String) pwMap.get(MesConstants.RESULT);
			// 如果出错，则直接返回
			if ("E".equals(pwFlag)) {
				retVal = (String[]) pwMap.get(MesConstants.RETVAL);
				return retVal;
			}
			// 取得是包装还是产品
			int type = (int) pwMap.get(MesConstants.TYPE);
			if (type == MesConstants.WIP) {
				MesWipBarcodes mwb = (MesWipBarcodes) pwMap.get(MesConstants.OBJECT);
				wipBarcodeId = mwb.getWipBarcodeId();
			} else if (MesConstants.PACK == type ) {
				MesPackingHeaders mph2 = (MesPackingHeaders) pwMap.get(MesConstants.OBJECT);
				wipBarcodeId = mph2.getPackingBarcodeId();
			}
			/**
			 * check状态
			 */
			paramMap.put("barcodeId", wipBarcodeId);
			paramMap.put("docNumber", olr.getDocNumber());
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			om.callPdaCheckDoc(paramMap);
			// check不通过则报错
			if ("E".equals(dbreturn.getxStatus())) {
				retVal[0] = dbreturn.getxStatus();
				retVal[1] = dbreturn.getxMessage();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return retVal;
			}
			/*
			 *  插入temp
			 */
			dbreturn = new DbReturnParameter();
			paramMap = new HashMap<String, Object>();
			paramMap.put("userId", user.getUserId());
			paramMap.put("processId", processId);
			paramMap.put("barCodeId", wipBarcodeId);
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			om.callPdaReprodTransactionTemp(paramMap);
			// 返回错误信息
			if (!"S".equals(dbreturn.getxStatus())) {
				System.out.println("processId:"+processId);
				retVal[0] = dbreturn.getxStatus();
				retVal[1] = dbreturn.getxMessage();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return retVal;
			}
		}
		/*
			 *  插入
			 */
		dbreturn = new DbReturnParameter();
		paramMap = new HashMap<String, Object>();
		// paramMap.put("userId", user.getUserId());
		paramMap.put("processId", processId);
		paramMap.put("barCodeId", null);
		paramMap.put("docNumber", oList.get(0).getDocNumber());
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		om.callPdaProcessReprod(paramMap);
		// 返回错误信息
		if (!"S".equals(dbreturn.getxStatus())) {
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return retVal;
		}

		/*
		 * 更新状态
		 *//*
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("processId", processId);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		om.callReprodProcessMes(paramMap);
		// 错误则回滚
		if (MesConstants.ERROR.equals(dbreturn.getxStatus())) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}*/
		// 返回
		retVal[0] = dbreturn.getxStatus();
		retVal[1] = dbreturn.getxMessage();
		return retVal;
	}





	/**
	 * @author sjf
	 * @date 2016年12月26日
	 * @param input
	 * @return PDA上线返修-扫描包装/产品条码
	 */
/*	public String onLinerepairScan_back(String[] input) {
		OnLineRepairData olrd = new OnLineRepairData();
		// 返回值
		String[] retVal = new String[4];
		// 登录用户
		// String loginName = input[0];
		// 组织ID
		String orgId = input[1];
		if (StringUtils.isEmpty(orgId)) {
			olrd.setResultCode(MesConstants.ERROR);
			olrd.setResultMsg("登录组织为空，请联系管理员");
			return JsonObjectConverTools.objectToJson(olrd);
		}
		// 领料单
		String docNumber = input[2];
		// 条码
		String barCode = input[3];
		*//**
		 * 获取包装箱/产品信息
		 *//*
		// 包装箱/产品ID
		BigDecimal wipBarcodeId = null;
		Map<String, Object> pwMap = new HashMap<String, Object>();
		pwMap = pdacService.getPackOrWip(barCode, orgId);
		String pwFlag = (String) pwMap.get(MesConstants.RESULT);
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(pwFlag)) {
			retVal = (String[]) pwMap.get(MesConstants.RETVAL);
			olrd.setResultCode(MesConstants.ERROR);
			olrd.setResultMsg(retVal[1]);
			return JsonObjectConverTools.objectToJson(olrd);
		}
		// 取得是包装还是产品
		int type = (int) pwMap.get(MesConstants.TYPE);
		if (type == MesConstants.PACK) {
			MesPackingHeaders mph2 = (MesPackingHeaders) pwMap
					.get(MesConstants.OBJECT);
			wipBarcodeId = mph2.getPackingBarcodeId();
		} else if (type == MesConstants.WIP) {
			MesWipBarcodes mwb = (MesWipBarcodes) pwMap
					.get(MesConstants.OBJECT);
			wipBarcodeId = mwb.getWipBarcodeId();
		}
		*//**
		 * check状态
		 *//*
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("barcodeId", wipBarcodeId);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		pdam.callReprodCheck(paramMap);
		// check不通过则报错
		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
			olrd.setResultCode(dbreturn.getxStatus());
			olrd.setResultMsg(dbreturn.getxMessage());
			return JsonObjectConverTools.objectToJson(olrd);
		}
		*//**
		 * 获取相关信息
		 *//*
		List<OnLineRepairData> result = om.getRepairStyle(wipBarcodeId);
		if (result.isEmpty()) {
			olrd.setResultCode(MesConstants.ERROR);
			if (type == MesConstants.PACK) {
				olrd.setResultMsg("该包装箱为空，请检查");
			} else if (type == MesConstants.WIP) {
				olrd.setResultMsg("该产品不存在，请检查");
			}
			return JsonObjectConverTools.objectToJson(olrd);
		}
		olrd.setResultData(result);
		olrd.setResultCode(MesConstants.SUCCESS);
		return JsonObjectConverTools.objectToJson(olrd);
	}*/
	/**
	 * @author sjf
	 * @date 2016年12月26日
	 * @return PDA上线返修-提交
	 */
	/*public String[] onLinerepairCommit_back(String jsonString) {
		// 返回值
		String[] retVal = new String[2];
		if (StringUtils.isEmpty(jsonString)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "数据异常";
			return retVal;
		}
		// 接收转换json数据
		OnLineRepair initOr = JsonObjectConverTools.jsonToObject(jsonString,
				OnLineRepair.class);
		List<OnLineRepair> oList = initOr.getOnLineRepair();

		// 登录用户
		String loginName = initOr.getUserid();
		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
		if (user == null) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录用户不存在，请联系管理员";
			return retVal;
		}
		// 组织ID
		String orgId = initOr.getWarehouse();
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织不存在，请联系管理员";
			return retVal;
		}
		// 取得唯一键
		BigDecimal processId = comm.getSeqByName("mes_transactions_process_s");
		// 循环提交
		for (OnLineRepair olr : oList) {
			*//**
			 * 获取包装箱/产品信息
			 *//*
			// 包装箱/产品ID
			BigDecimal wipBarcodeId = null;
			Map<String, Object> pwMap = new HashMap<String, Object>();
			pwMap = pdacService.getPackOrWip(olr.getSnPackNo(), orgId);
			String pwFlag = (String) pwMap.get(MesConstants.RESULT);
			// 如果出错，则直接返回
			if (MesConstants.ERROR.equals(pwFlag)) {
				retVal = (String[]) pwMap.get(MesConstants.RETVAL);
				return retVal;
			}
			// 取得是包装还是产品
			int type = (int) pwMap.get(MesConstants.TYPE);
			if (type == MesConstants.PACK) {
				MesPackingHeaders mph2 = (MesPackingHeaders) pwMap
						.get(MesConstants.OBJECT);
				wipBarcodeId = mph2.getPackingBarcodeId();
			} else if (type == MesConstants.WIP) {
				MesWipBarcodes mwb = (MesWipBarcodes) pwMap
						.get(MesConstants.OBJECT);
				wipBarcodeId = mwb.getWipBarcodeId();
			}
			*//**
			 * check状态
			 *//*
			DbReturnParameter dbreturn = new DbReturnParameter();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("barcodeId", wipBarcodeId);
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			pdam.callReprodCheck(paramMap);
			// check不通过则报错
			if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
				retVal[0] = dbreturn.getxStatus();
				retVal[1] = dbreturn.getxMessage();
				TransactionAspectSupport.currentTransactionStatus()
						.setRollbackOnly();
				return retVal;
			}
			*//*
			 * 插入
			 *//*
			dbreturn = new DbReturnParameter();
			paramMap = new HashMap<String, Object>();
			paramMap.put("userId", user.getUserId());
			paramMap.put("processId", processId);
			paramMap.put("barCodeId", wipBarcodeId);
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			om.callReprodTransactionTemp(paramMap);
			// 返回错误信息
			if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
				retVal[0] = dbreturn.getxStatus();
				retVal[1] = dbreturn.getxMessage();
				TransactionAspectSupport.currentTransactionStatus()
						.setRollbackOnly();
				return retVal;
			}
		}
		*//*
		 * 更新状态
		 *//*
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("processId", processId);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		om.callReprodProcessMes(paramMap);
		// 错误则回滚
		if (MesConstants.ERROR.equals(dbreturn.getxStatus())) {
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
		}
		// 返回
		retVal[0] = dbreturn.getxStatus();
		retVal[1] = dbreturn.getxMessage();
		return retVal;
	}*/
	/**
	 *  验证领料单和条码退回
	 * @param input
	 * @return
	 */
	public String reOnLinerepairScan(String[] input) {
		OnLineRepairData olrd = new OnLineRepairData();
		String[] retVal = new String[4];
		String loginName = input[0];
		// 领料单
		String docNumber = input[2];
		// 条码
		String barCode = input[3];
		// 组织ID
		String orgId = input[1];
		if (StringUtils.isEmpty(orgId)) {
			olrd.setResultCode(MesConstants.ERROR);
			olrd.setResultMsg("登录组织为空，请联系管理员");
			return JsonObjectConverTools.objectToJson(olrd);
		}
		if (StringUtils.isEmpty(docNumber)) {
			olrd.setResultCode(MesConstants.ERROR);
			olrd.setResultMsg("领料单不能为空！");
			return JsonObjectConverTools.objectToJson(olrd);
		}
		if (StringUtils.isEmpty(barCode)) {
			olrd.setResultCode(MesConstants.ERROR);
			olrd.setResultMsg("条码不能为空！");
			return JsonObjectConverTools.objectToJson(olrd);
		}

		/**
		 * 获取包装箱/产品信息
		 */
		// 包装箱/产品ID
		BigDecimal wipBarcodeId = null;
		Map<String, Object> pwMap = new HashMap<String, Object>();
		pwMap = pdacService.getPackOrWip(barCode, orgId);
		String pwFlag = (String) pwMap.get(MesConstants.RESULT);
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(pwFlag)) {
			retVal = (String[]) pwMap.get(MesConstants.RETVAL);
			olrd.setResultCode("E");
			olrd.setResultMsg(retVal[1]);
			return JsonObjectConverTools.objectToJson(olrd);
		}
		// 取得是包装还是产品
		int type = (int) pwMap.get(MesConstants.TYPE);
		if (type == MesConstants.PACK) {
			MesPackingHeaders mph2 = (MesPackingHeaders) pwMap.get(MesConstants.OBJECT);
			wipBarcodeId = mph2.getPackingBarcodeId();
		} else if (type == MesConstants.WIP) {
			System.out.println("条码类型："+type);
			MesWipBarcodes mwb = (MesWipBarcodes) pwMap.get(MesConstants.OBJECT);
			wipBarcodeId = mwb.getWipBarcodeId();
		}
		/**
		 *  调用call检验 领料单和条码
		 */
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("barCodeId", wipBarcodeId);
		paramMap.put("docNumber", docNumber);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		om.reTurnCallPdaReprodCheck(paramMap);
		if("E".equals(dbreturn.getxStatus())){
			//验证不通过时
			olrd.setResultCode(dbreturn.getxStatus());
			olrd.setResultMsg(dbreturn.getxMessage());
			return JsonObjectConverTools.objectToJson(olrd);

		}

		/**
		 * 获取相关信息
		 */
		List<OnLineRepairData> result = om.getRepairStyle(wipBarcodeId);
		if (result.isEmpty()) {
			olrd.setResultCode("E");
			if (type == MesConstants.PACK) {
				olrd.setResultMsg("该包装箱为空，请检查");
			} else if (type == MesConstants.WIP) {
				olrd.setResultMsg("该产品不存在，请检查");
			}
			return JsonObjectConverTools.objectToJson(olrd);
		}
		olrd.setResultData(result);
		olrd.setResultCode(MesConstants.SUCCESS);
		return JsonObjectConverTools.objectToJson(olrd);
	}

	/**
	 *  pda 上线返修提交( 包含领料单)
	 * @param jsonString
	 * @return
	 */
	public String[] reOnLinerepairCommit(String jsonString) {
		// 返回值
		String[] retVal = new String[2];
		if (StringUtils.isEmpty(jsonString)) {
			retVal[0] = "E";
			retVal[1] = "数据异常";
			return retVal;
		}
		// 接收转换json数据
		OnLineRepair initOr = JsonObjectConverTools.jsonToObject(jsonString,
				OnLineRepair.class);
		List<OnLineRepair> oList = initOr.getOnLineRepair();

		// 登录用户
		String loginName = initOr.getUserid();
		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
		if (user == null) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录用户不存在，请联系管理员";
			return retVal;
		}
		// 组织ID
		String orgId = initOr.getWarehouse();
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织不存在，请联系管理员";
			return retVal;
		}
		// 取得唯一键
		BigDecimal processId = comm.getSeqByName("mes_transactions_process_s");
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 循环提交
		for (OnLineRepair olr : oList) {
			/**
			 * 获取包装箱/产品信息
			 */
			// 包装箱/产品ID
			BigDecimal wipBarcodeId = null;
			Map<String, Object> pwMap = new HashMap<String, Object>();
			pwMap = pdacService.getPackOrWip(olr.getSnPackNo(), orgId);
			String pwFlag = (String) pwMap.get(MesConstants.RESULT);
			// 如果出错，则直接返回
			if ("E".equals(pwFlag)) {
				retVal = (String[]) pwMap.get(MesConstants.RETVAL);
				return retVal;
			}
			// 取得是包装还是产品
			int type = (int) pwMap.get(MesConstants.TYPE);
			if (type == MesConstants.WIP) {
				MesWipBarcodes mwb = (MesWipBarcodes) pwMap.get(MesConstants.OBJECT);
				wipBarcodeId = mwb.getWipBarcodeId();
			} else if (MesConstants.PACK == type ) {
				MesPackingHeaders mph2 = (MesPackingHeaders) pwMap.get(MesConstants.OBJECT);
				wipBarcodeId = mph2.getPackingBarcodeId();
			}
			/**
			 * check状态
			 */
			paramMap.put("barcodeId", wipBarcodeId);
			paramMap.put("docNumber", olr.getDocNumber());
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			om.callPdaCheckDoc(paramMap);
			// check不通过则报错
			if ("E".equals(dbreturn.getxStatus())) {
				retVal[0] = dbreturn.getxStatus();
				retVal[1] = dbreturn.getxMessage();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return retVal;
			}
			/*
			 *  插入temp
			 */
			dbreturn = new DbReturnParameter();
			paramMap = new HashMap<String, Object>();
			paramMap.put("userId", user.getUserId());
			paramMap.put("processId", processId);
			paramMap.put("barCodeId", wipBarcodeId);
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			om.reTurnCallPdaReprodTransactionTemp(paramMap);
			// 返回错误信息
			if (!"S".equals(dbreturn.getxStatus())) {
				System.out.println("processId:"+processId);
				retVal[0] = dbreturn.getxStatus();
				retVal[1] = dbreturn.getxMessage();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return retVal;
			}
		}
		/*
		 *  插入
		 */
		dbreturn = new DbReturnParameter();
		paramMap = new HashMap<String, Object>();
		// paramMap.put("userId", user.getUserId());
		paramMap.put("processId", processId);
		paramMap.put("barCodeId", null);
		paramMap.put("docNumber", oList.get(0).getDocNumber());
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		om.reTurnCallPdaProcessReprod(paramMap);
		// 返回错误信息
		if (!"S".equals(dbreturn.getxStatus())) {
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return retVal;
		}

		/*
		 * 更新状态
		 *//*
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("processId", processId);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		om.callReprodProcessMes(paramMap);
		// 错误则回滚
		if (MesConstants.ERROR.equals(dbreturn.getxStatus())) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}*/
		// 返回
		retVal[0] = dbreturn.getxStatus();
		retVal[1] = dbreturn.getxMessage();
		return retVal;
	}


}
