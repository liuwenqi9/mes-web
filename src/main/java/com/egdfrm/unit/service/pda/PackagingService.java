package com.egdfrm.unit.service.pda;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.egdfrm.unit.model.expand.pda.BigPackModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import com.egdfrm.core.mapper.standard.TtApplicationUserMapper;
import com.egdfrm.core.model.standard.TtApplicationUser;
import com.egdfrm.extend.common.DbReturnParameter;
import com.egdfrm.extend.common.JsonObjectConverTools;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.common.Utils;
import com.egdfrm.unit.mapper.expand.CommonMapper;
import com.egdfrm.unit.mapper.expand.pda.PDACommonMapper;
import com.egdfrm.unit.mapper.expand.pda.PackagingMapper;
import com.egdfrm.unit.mapper.standard.MesPackingHeadersMapper;
import com.egdfrm.unit.mapper.standard.MesPackingLinesMapper;
import com.egdfrm.unit.mapper.standard.MesWipBarcodesMapper;
import com.egdfrm.unit.model.expand.pda.SecondaryPack;
import com.egdfrm.unit.model.standard.MesPackingHeaders;
import com.egdfrm.unit.model.standard.MesPackingLines;
import com.egdfrm.unit.model.standard.MesPackingLinesCriteria;
import com.egdfrm.unit.model.standard.MesWipBarcodes;
import com.egdfrm.unit.model.standard.MtlSystemItemsB;
/**
 * @author sjf
 * @date 2016年12月15日 二次打包SERVICE
 **/
@Service
public class PackagingService {
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
	 * Field mwbm: 产品条码表dao
	 * </p>
	 */
	@Autowired
	private MesWipBarcodesMapper mwbm;

	/**
	 * <p>
	 * Field pm:二次包装dao
	 * </p>
	 */
	@Autowired
	private PackagingMapper pm;
	/**
	 * <p>
	 * Field comm: 共通dao
	 * </p>
	 */
	@Autowired
	private CommonMapper comm;

	/**
	 * <p>
	 * Field mplm:包装关联表dao
	 * </p>
	 */
	@Autowired
	private MesPackingLinesMapper mplm;
	/**
	 * <p>
	 * Field pdam: PDA共通dao
	 * </p>
	 */
	@Autowired
	private PDACommonMapper pdam;

	/**
	 * <p>
	 * Field pdacService: PDA共通Service
	 * </p>
	 */
	@Autowired
	private PDACommonService pdacService;
	/**
	 * @author sjf
	 * @date 2016年12月15日
	 * @param packBarcode
	 * @return PDA二次包装-扫描包装条码 根据包装条码获取包装箱容量 SP PDA发运包装-扫描包装条码 SH
	 */

	public String[] packagingPackBarcode(String[] receiveVal) {
		String[] retVal = new String[2];
		// 登录用户
		// String loginName = receiveVal[0];
		// 组织ID
		String orgId = receiveVal[1];
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织为空，请联系管理员";
			return retVal;
		}
		// 包装条码
		String packBarcode = receiveVal[2];
		// 类型：二次包装SP ， 发运包装SH
		String type = receiveVal[3];
		// 如果扫描的类型不匹配直接报错
		if (MesConstants.SECOND_PACKING.equals(type)
				&& !(packBarcode.charAt(0) == MesConstants.BIG_PACK || packBarcode
						.charAt(0) == MesConstants.SMALL_PACK)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "请正确扫描二次包装条码";
			return retVal;
		}
		if (MesConstants.SHIP_PACKING.equals(type)
				&& !(packBarcode.charAt(0) == MesConstants.SHIP_PACK)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "请正确扫描发运包装条码";
			return retVal;
		}
		// @hgb @date 2017-07-13 二次包装 验证
		if(MesConstants.SECOND_PACKING.equals(type)){
			DbReturnParameter dbreturn = new DbReturnParameter();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("packBarcode", packBarcode); 
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			pm.callCheckPackForReprod(paramMap);
			if("E".equals(dbreturn.getxStatus())){//如果是上线返修的，
				retVal[0] = dbreturn.getxStatus();
				retVal[1] = dbreturn.getxMessage();
				return retVal;
			}
			
		}
		
		/*
		 * 获取包装箱信息
		 */
		Map<String, Object> packMap = new HashMap<String, Object>();
		packMap = pdacService.getPackingHeaders(packBarcode, orgId);
		String flag = (String) packMap.get(MesConstants.RESULT);
		MesPackingHeaders mph = new MesPackingHeaders();
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(flag)) {
			retVal = (String[]) packMap.get(MesConstants.RETVAL);
			return retVal;
		}
		// 返回成功
		retVal[0] = MesConstants.SUCCESS;
		if (MesConstants.SECOND_PACKING.equals(type)) {
			// 二次包装返回数量
			mph = (MesPackingHeaders) packMap.get("mph");
			retVal[1] = pm.getNumByPackBarcodeId(mph.getPackingBarcodeId()
					.toString());
		}
		return retVal;

	}

	/**
	 * @author sjf
	 * @date 2016年12月15日
	 * @param
	 * @return PDA二次包装-扫描产品条码 根据包装条码，产品条码，及是否解除限制进行校验并获取产品编码，产品现有量，产品类型
	 */
	public String[] packagingWIPBarcode(String[] input) {
		// 返回值
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
		// 包装条码
		String packBarcode = input[2];
		// 产品条码
		String wipBarcode = input[3];
		// 是否解除限制Y/N 发运包装SH
		String isFlag = input[4];

		/**
		 * @author @hgb
		 * return 验证产品条码是否是上线返修的
		 *  如果 isFlag=“SH” 是发运包装 页面的 产品条码，wipBarcode 产品条码
		 */
		
		if(!MesConstants.SHIP_PACKING.equals(isFlag)){//属于二次包装的产品扫描
			DbReturnParameter dbreturn = new DbReturnParameter();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("packBarcode", wipBarcode); //产品条码
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			pm.callCheckPackForReprod(paramMap);
			if("E".equals(dbreturn.getxStatus())){//如果是上线返修的，
				retVal[0] = dbreturn.getxStatus();
				retVal[1] = dbreturn.getxMessage();
				return retVal;
			}
		}
		//@hgb 20180117， 改于20180327PM，
		// 二次包装不让包装包包装，只能包装包产品（发运包装无此限制）
		if (wipBarcode.charAt(0) == MesConstants.BIG_PACK&&!MesConstants.SHIP_PACKING.equals(isFlag) ) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "该条码是包装箱条码！";
			return retVal;
		}
		// 如果扫描的类型不匹配直接报错
		if ((MesConstants.YES.equals(isFlag) || MesConstants.NO.equals(isFlag))
				&& !(packBarcode.charAt(0) == MesConstants.BIG_PACK || packBarcode
						.charAt(0) == MesConstants.SMALL_PACK)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "请正确扫描二次包装条码";
			return retVal;
		} else if (MesConstants.SHIP_PACKING.equals(isFlag)
				&& !(packBarcode.charAt(0) == MesConstants.SHIP_PACK)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "请正确扫描发运包装条码";
			return retVal;
		}

		/**
		 * 第一个条码 获取包装箱信息
		 */
		//计时
		long nowTime=System.currentTimeMillis();
		Map<String, Object> packMap = new HashMap<String, Object>();
		packMap = pdacService.getPackingHeaders(packBarcode, orgId);
		String packFlag = (String) packMap.get(MesConstants.RESULT);
		MesPackingHeaders mph = new MesPackingHeaders();
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(packFlag)) {
			retVal = (String[]) packMap.get(MesConstants.RETVAL);
			return retVal;
		} else {
			mph = (MesPackingHeaders) packMap.get("mph");
		}
		//计时
		Utils.printTakeTime(nowTime,"第一个条码 获取包装箱信息");
		/**
		 * 第二个条码 获取包装箱/产品信息
		 */
		// 包装箱/产品ID
		BigDecimal wipBarcodeId = null;
		// 现有量
		BigDecimal onhandQuantity = null;
		// 产品类型 内销/外销/包装箱
		String barCodeType = null;
		//物料号
		String wipId=null;
		Map<String, Object> pwMap = new HashMap<String, Object>();
		/*
		 * 二次包装不校验外销产品，发运包装校验
		 */
		if (MesConstants.YES.equals(isFlag) || MesConstants.NO.equals(isFlag)) {
			/*
			 * 二次包装
			 */
			pwMap=pdacService.getPackOrWipNoCheckWx(wipBarcode,orgId);
		} else if (MesConstants.SHIP_PACKING.equals(isFlag)) {
			/*
			 * 发运包装
			 */
			pwMap=pdacService.getPackOrWip(wipBarcode,orgId);
		}
		String pwFlag = (String) pwMap.get(MesConstants.RESULT);
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(pwFlag)) {
			retVal = (String[]) pwMap.get(MesConstants.RETVAL);
			return retVal;
		}
		//取得是包装还是产品
		int type=  (int) pwMap.get(MesConstants.TYPE);
		if(type==MesConstants.PACK){
			MesPackingHeaders mph2 = (MesPackingHeaders) pwMap.get(MesConstants.OBJECT);
			wipBarcodeId = mph2.getPackingBarcodeId();
			onhandQuantity = pdam.getNumByPackBarcodeId(wipBarcodeId);
			barCodeType = "PACK";

			/*
			 * 获取物料信息
			 */
			Map<String, Object> msMap = new HashMap<String, Object>();
			msMap = pdacService.getInventoryItemInfo(mph2.getInventoryItemId(),
					new BigDecimal(orgId));
			String msFlag = (String) msMap.get(MesConstants.RESULT);
			// 如果出错，则直接返回
			if (MesConstants.ERROR.equals(msFlag)) {
				retVal = (String[]) msMap.get(MesConstants.RETVAL);
				return retVal;
			} 
			//物料号 
			wipId=(String) msMap.get("msLotNos");
		}else if(type==MesConstants.WIP){
			MesWipBarcodes mwb  = (MesWipBarcodes) pwMap.get(MesConstants.OBJECT);
			wipBarcodeId = mwb.getWipBarcodeId();
			onhandQuantity = mwb.getOnhandQuantity();
			barCodeType = mwb.getBarcodeType();

			/*
			 * 获取物料信息
			 */
			Map<String, Object> msMap = new HashMap<String, Object>();
			msMap = pdacService.getInventoryItemInfo(mwb.getPrimaryItemId(),
					new BigDecimal(orgId));
			String msFlag = (String) msMap.get(MesConstants.RESULT);
			// 如果出错，则直接返回
			if (MesConstants.ERROR.equals(msFlag)) {
				retVal = (String[]) msMap.get(MesConstants.RETVAL);
				return retVal;
			} 
			//物料号 
			wipId=(String) msMap.get("msLotNos");
		}
		//计时
		Utils.printTakeTime(nowTime,"第二个条码 获取包装箱/产品信息");
		/**
		 * 进行校验
		 */
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("packingBarcodeId", mph.getPackingBarcodeId());
		paramMap.put("wipBarcodeId", wipBarcodeId);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		if (MesConstants.YES.equals(isFlag) || MesConstants.NO.equals(isFlag)) {
			/*
			 * 二次包装
			 */
			paramMap.put("onhandQuantity", onhandQuantity);
			paramMap.put("isFlag", isFlag);
			pm.callProcessPackCheck(paramMap);
		} else if (MesConstants.SHIP_PACKING.equals(isFlag)) {
			/*
			 * 发运包装
			 */
			pm.callProcessShipCheck(paramMap);
		}
		//计时
		Utils.printTakeTime(nowTime,"进行校验");

		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage();
			return retVal;
		}
		/**
		 * 验证通过，返回产品信息
		 */
		retVal[0] = dbreturn.getxStatus();
		retVal[1] = wipId == null ? "" : wipId;
		// 产品现有量
		retVal[2] = onhandQuantity.toString();

		if (MesConstants.YES.equals(isFlag) || MesConstants.NO.equals(isFlag)) {
			// 二次包装返回产品类型
			retVal[3] = barCodeType;
		}
		//把isFlag回传，以利于客户端判断此次请求是否是取消限制
		retVal[4] = isFlag;
		return retVal;
	}

	/**
	 * @author sjf
	 * @date 2016年12月16日
	 * 
	 *       PDA二次包装-提交
	 * 
	 * @param jsonString
	 * @return
	 */
	@Transactional
	public String[] packagingCommit(String jsonString, String status) {
		// 返回值
		String[] retVal = new String[2];
		if (StringUtils.isEmpty(jsonString) || StringUtils.isEmpty(status)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "数据异常";
			return retVal;
		}
		// 接收转换json数据
		SecondaryPack initSp = JsonObjectConverTools.jsonToObject(jsonString,
				SecondaryPack.class);
		// 登录用户
		String loginName = initSp.getUserid();
		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
		if (user == null) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录用户不存在，请联系管理员";
			return retVal;
		}
		// 组织ID
		String orgId = initSp.getWarehouse();
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织不存在，请联系管理员";
			return retVal;
		}
		List<SecondaryPack> pList = initSp.getSecondaryPack();

		/**
		 * 第一个条码 获取包装箱信息
		 */
		//计时
		long nowTime=System.currentTimeMillis();
		Map<String, Object> packMap = new HashMap<String, Object>();
		packMap = pdacService
				.getPackingHeaders(pList.get(0).getPackNo(), orgId);
		String packFlag = (String) packMap.get(MesConstants.RESULT);
		MesPackingHeaders mph = new MesPackingHeaders();
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(packFlag)) {
			retVal = (String[]) packMap.get(MesConstants.RETVAL);
			return retVal;
		} else {
			mph = (MesPackingHeaders) packMap.get("mph");
		}
		//计时
		Utils.printTakeTime(nowTime,"第一个条码 获取包装箱信息");
		// 循环产品信息
		for (SecondaryPack sp : pList) {
			/**
			 * 第二个条码 获取包装箱/产品信息
			 */
			// 包装箱/产品ID
			BigDecimal wipBarcodeId = null;
			// 现有量
			BigDecimal onhandQuantity = new BigDecimal(sp.getNum());
			
			Map<String, Object> pwMap = new HashMap<String, Object>();
			pwMap=pdacService.getPackOrWipNoCheckWx(sp.getSnNo(),orgId);
			String pwFlag = (String) pwMap.get(MesConstants.RESULT);
			// 如果出错，则直接返回
			if (MesConstants.ERROR.equals(pwFlag)) {
				retVal = (String[]) pwMap.get(MesConstants.RETVAL);
				return retVal;
			}
			//取得是包装还是产品
			int type=  (int) pwMap.get(MesConstants.TYPE);
			if(type==MesConstants.PACK){
				MesPackingHeaders mph2 = (MesPackingHeaders) pwMap.get(MesConstants.OBJECT);
				wipBarcodeId = mph2.getPackingBarcodeId();
//				onhandQuantity = pdam.getNumByPackBarcodeId(wipBarcodeId);
			}else if(type==MesConstants.WIP){
				MesWipBarcodes mwb  = (MesWipBarcodes) pwMap.get(MesConstants.OBJECT);
				wipBarcodeId = mwb.getWipBarcodeId();
//				onhandQuantity = mwb.getOnhandQuantity();
			}
			/**
			 * 包装
			 */
			DbReturnParameter dbreturn = new DbReturnParameter();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", user.getUserId());
			paramMap.put("packingBarcodeId", mph.getPackingBarcodeId());
			paramMap.put("wipBarcodeId", wipBarcodeId);
			paramMap.put("wxQuantity", onhandQuantity);
			paramMap.put(MesConstants.RESULT, status);
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			pm.callProcessPack(paramMap);

			// 返回错误信息
			if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
				retVal[0] = dbreturn.getxStatus();
				retVal[1] = dbreturn.getxMessage();
				TransactionAspectSupport.currentTransactionStatus()
						.setRollbackOnly();
				return retVal;
			}
		}
		//计时
		Utils.printTakeTime(nowTime,"循环  第二个条码 获取包装箱/产品信息");

		/**
		 * 处理mes ，insert tmp用于EBS操作
		 */
		// 取得唯一键
		BigDecimal processId = comm.getSeqByName("mes_transactions_process_s");
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", user.getUserId());
		paramMap.put("processId", processId);
		paramMap.put("barCodeId", mph.getPackingBarcodeId());
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		pm.callPackInsertTransactionTemp(paramMap);

		//计时
		Utils.printTakeTime(nowTime,"处理mes ，insert tmp用于EBS操作");
		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage();
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			return retVal;
		} else {
			/**
			 * ebs
			 */
			dbreturn = new DbReturnParameter();
			paramMap = new HashMap<String, Object>();
			paramMap.put("processId", processId);
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			pm.callWipMoveTransaction(paramMap);

			//计时
			Utils.printTakeTime(nowTime,"ebs");
			
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage();
			return retVal;
		}
	}

	/**
	 * @author sjf
	 * @date 2016年12月23日
	 * @param jsonString
	 * @return PDA发运包装-提交
	 */
	public String[] shipPackagingCommit(String jsonString) {

		// 返回值
		String[] retVal = new String[2];
		if (StringUtils.isEmpty(jsonString)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "数据异常";
			return retVal;
		}
		// 接收转换json数据
		SecondaryPack initSp = JsonObjectConverTools.jsonToObject(jsonString,
				SecondaryPack.class);
		// 登录用户
		String loginName = initSp.getUserid();
		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
		if (user == null) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录用户不存在，请联系管理员";
			return retVal;
		}
		// 组织ID
		String orgId = initSp.getWarehouse();
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织不存在，请联系管理员";
			return retVal;
		}
		List<SecondaryPack> pList = initSp.getSecondaryPack();

		/**
		 * 第一个条码 获取包装箱信息
		 */
		Map<String, Object> packMap = new HashMap<String, Object>();
		packMap = pdacService
				.getPackingHeaders(pList.get(0).getPackNo(), orgId);
		String packFlag = (String) packMap.get(MesConstants.RESULT);
		MesPackingHeaders mph = new MesPackingHeaders();
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(packFlag)) {
			retVal = (String[]) packMap.get(MesConstants.RETVAL);
			return retVal;
		} else {
			mph = (MesPackingHeaders) packMap.get("mph");
		}
		// 循环产品信息
		for (SecondaryPack sp : pList) {
			/**
			 * 第二个条码 获取包装箱/产品信息
			 */
			// 包装箱/产品ID
			BigDecimal wipBarcodeId = null;
			Map<String, Object> pwMap = new HashMap<String, Object>();
			pwMap=pdacService.getPackOrWip(sp.getSnNo(),orgId);
			String pwFlag = (String) pwMap.get(MesConstants.RESULT);
			// 如果出错，则直接返回
			if (MesConstants.ERROR.equals(pwFlag)) {
				retVal = (String[]) pwMap.get(MesConstants.RETVAL);
				return retVal;
			}
			//取得是包装还是产品
			int type=  (int) pwMap.get(MesConstants.TYPE);
			if(type==MesConstants.PACK){
				MesPackingHeaders mph2 = (MesPackingHeaders) pwMap.get(MesConstants.OBJECT);
				wipBarcodeId = mph2.getPackingBarcodeId();
			}else if(type==MesConstants.WIP){
				MesWipBarcodes mwb  = (MesWipBarcodes) pwMap.get(MesConstants.OBJECT);
				wipBarcodeId = mwb.getWipBarcodeId();
			}

			/**
			 * 发运包装
			 */
			DbReturnParameter dbreturn = new DbReturnParameter();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", user.getUserId());
			paramMap.put("packingBarcodeId", mph.getPackingBarcodeId());
			paramMap.put("wipBarcodeId", wipBarcodeId);
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			pm.callProcessShipPack(paramMap);

			// 返回错误信息
			if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
				retVal[0] = dbreturn.getxStatus();
				retVal[1] = dbreturn.getxMessage();
				TransactionAspectSupport.currentTransactionStatus()
						.setRollbackOnly();
				return retVal;
			}
		}

		retVal[0] = MesConstants.SUCCESS;
		return retVal;

	}
	/**
	 * @author sjf
	 * @date 2016年12月19日
	 * @param input
	 * @return
	 * 
	 *         PDA拆包-扫描
	 * 
	 */
	public String[] packagingUnpack(String[] input) {
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
		// 包装条码
		String packBarcode = input[2];
		// 产品条码
		String wipBarcode = input[3];
		/**
		 * 第一个条码
		 * 获取包装箱信息
		 */
		Map<String, Object> packMap = new HashMap<String, Object>();
		packMap = pdacService.getPackingHeaders(packBarcode, orgId);
		String packFlag = (String) packMap.get(MesConstants.RESULT);
		MesPackingHeaders mph = new MesPackingHeaders();
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(packFlag)) {
			retVal = (String[]) packMap.get(MesConstants.RETVAL);
			return retVal;
		} else {
			mph = (MesPackingHeaders) packMap.get("mph");
		}
		/**
		 * 第二个条码
		 * 获取包装箱/产品信息
		 */
		// 物料号
		String lotNo = null;
		// 产品现有量
		String num = null;
		// 产品类型
		String productType = null;
		// 包装箱/产品ID
		BigDecimal wipBarcodeId = null;
		Map<String, Object> pwMap = new HashMap<String, Object>();
		pwMap = pdacService.getPackOrWipNoCheckWx(wipBarcode, orgId);
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
			//包装箱/产品ID
			wipBarcodeId = mph2.getPackingBarcodeId();
			//现有量
			num=pdam.getNumByPackBarcodeId(wipBarcodeId).toString();
			//产品类型
			productType="PACK";
			/*
			 * 获取物料信息 
			 */
			Map<String, Object> msMap = new HashMap<String, Object>();
			msMap = pdacService.getInventoryItemInfo(mph2.getInventoryItemId(),
					new BigDecimal(orgId));
			String msFlag = (String) msMap.get(MesConstants.RESULT);
			// 如果出错，则直接返回
			if (MesConstants.ERROR.equals(msFlag)) {
				retVal = (String[]) msMap.get(MesConstants.RETVAL);
				return retVal;
			} 
			//物料号 
			lotNo=(String) msMap.get("msLotNos");
			
			
		} else if (type == MesConstants.WIP) {
			MesWipBarcodes mwb = (MesWipBarcodes) pwMap
					.get(MesConstants.OBJECT);
			//包装箱/产品ID
			wipBarcodeId = mwb.getWipBarcodeId();
			//现有量
			num=mwb.getOnhandQuantity().toString();
			//产品类型
			productType=mwb.getBarcodeType();
			/*
			 * 获取物料信息
			 */
			Map<String, Object> msMap = new HashMap<String, Object>();
			msMap = pdacService.getInventoryItemInfo(mwb.getPrimaryItemId(),
					new BigDecimal(orgId));
			String msFlag = (String) msMap.get(MesConstants.RESULT);
			MtlSystemItemsB ms = new MtlSystemItemsB();
			// 如果出错，则直接返回
			if (MesConstants.ERROR.equals(msFlag)) {
				retVal = (String[]) msMap.get(MesConstants.RETVAL);
				return retVal;
			} 
			//物料号 
			lotNo=(String) msMap.get("msLotNos");
			
		}
		/**
		 * 进行校验
		 */
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("packingBarcodeId", mph.getPackingBarcodeId());
		paramMap.put("wipBarcodeId", wipBarcodeId);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		pm.callCheckDeleteBarcode(paramMap);

		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage();
			return retVal;
		}
		/**
		 * 验证通过，返回产品信息
		 */
		/*
		 * 获取已包装数量
		 */
		MesPackingLinesCriteria mplc = new MesPackingLinesCriteria();
		mplc.createCriteria()
				.andPackingBarcodeIdEqualTo(mph.getPackingBarcodeId())
				.andLineBarcodeIdEqualTo(wipBarcodeId);
		List<MesPackingLines> mplList = mplm.selectByExample(mplc);
		if (mplList.isEmpty()) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "扫描出错，请正常拆包扫描";
			return retVal;
		}
		retVal[0] = dbreturn.getxStatus();
		// 物料号
		retVal[1] = lotNo;
		// 产品现有量
		retVal[2] = num;
		// 产品类型
		retVal[3] = productType;

		return retVal;

	}

	/**
	 * @author sjf
	 * @date 2016年12月19日
	 * @param jsonString
	 * @return
	 * 
	 *         PDA拆包-提交
	 * 
	 */
	public String[] packagingUnpackCommit(String jsonString) {
		// 返回值
		String[] retVal = new String[2];
		if (StringUtils.isEmpty(jsonString)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "数据异常";
			return retVal;
		}
		// 接收转换json数据
		SecondaryPack initSp = JsonObjectConverTools.jsonToObject(jsonString,
				SecondaryPack.class);
		List<SecondaryPack> pList = initSp.getSecondaryPack();

		// 登录用户
		String loginName = initSp.getUserid();
		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
		if (user == null) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录用户不存在，请联系管理员";
			return retVal;
		}
		// 组织ID
		String orgId = initSp.getWarehouse();
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织不存在，请联系管理员";
			return retVal;
		}
		/**
		 * 第一个条码 
		 * 获取包装箱信息
		 */
		Map<String, Object> packMap = new HashMap<String, Object>();
		packMap = pdacService
				.getPackingHeaders(pList.get(0).getPackNo(), orgId);
		String packFlag = (String) packMap.get(MesConstants.RESULT);
		MesPackingHeaders mph = new MesPackingHeaders();
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(packFlag)) {
			retVal = (String[]) packMap.get(MesConstants.RETVAL);
			return retVal;
		} else {
			mph = (MesPackingHeaders) packMap.get("mph");
		}
		// 循环产品信息
		for (SecondaryPack sp : pList) {
			/**
			 * 第二个条码 
			 * 获取包装箱/产品信息
			 */
			// 包装箱/产品ID
			BigDecimal wipBarcodeId = null;
			Map<String, Object> pwMap = new HashMap<String, Object>();
			pwMap=pdacService.getPackOrWip(sp.getSnNo(),orgId);
			String pwFlag = (String) pwMap.get(MesConstants.RESULT);
			// 如果出错，则直接返回
			if (MesConstants.ERROR.equals(pwFlag)) {
				retVal = (String[]) pwMap.get(MesConstants.RETVAL);
				return retVal;
			}
			//取得是包装还是产品
			int type=  (int) pwMap.get(MesConstants.TYPE);
			if(type==MesConstants.PACK){
				MesPackingHeaders mph2 = (MesPackingHeaders) pwMap.get(MesConstants.OBJECT);
				wipBarcodeId = mph2.getPackingBarcodeId();
			}else if(type==MesConstants.WIP){
				MesWipBarcodes mwb  = (MesWipBarcodes) pwMap.get(MesConstants.OBJECT);
				wipBarcodeId = mwb.getWipBarcodeId();
			}
			/**
			 * PDA拆包-提交
			 */
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("packingBarcodeId", mph.getPackingBarcodeId());
			paramMap.put("wipBarcodeId", wipBarcodeId);
			paramMap.put("wxQuantity", sp.getNum());
			pm.callDeleteBarcode(paramMap);
		}
		retVal[0] = MesConstants.SUCCESS;
		return retVal;

	}

	/**
	 * 验证大大包装箱子是否存在
	 * @param parameter 大大包装箱子
	 * @return
	 */
	public String[] beingBigPack(String parameter){
		String[] retVal = new String[3];
		List<Map<String,Object>> list = pm.getBigPackByBarcode(parameter);
		if(list!=null && !list.isEmpty()){
			Map<String,Object> m = list.get(0);
			if(m!=null){
				retVal[0] = MesConstants.SUCCESS;
				retVal[1] = "请求成功！";
				//已包装个数
				retVal[2] = m.get("PACK_QUANTITY")!=null?m.get("PACK_QUANTITY").toString():"0";
			}else {
				retVal[0] = MesConstants.ERROR;
				retVal[1] = "包装箱不存在！";
				retVal[2]="0";
			}

		}else {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "包装箱不存在！";
			retVal[2]="0";
		}
		return retVal;
	}

	/**
	 *  验证小包装 是否有效
	 * @param parameters 0大包装，1小包装
	 * @return
	 */
	public  String[] beingSmallPack(String[] parameters){
		String [] retVal = new String[7];
		retVal[2] =parameters[0];//大包装
		retVal[3] =parameters[1];//小包装
		try {
			List<Map<String,Object>> list = pm.getSmallPackByBarcode(parameters[1]);
			if(list!=null && !list.isEmpty()){
				Map<String,Object> map = list.get(0);
				//料号segment1
				retVal[4] = map.get("SEGMENT1")!= null ? map.get("SEGMENT1").toString():"";
				//型号prod_type
				retVal[5] = map.get("PROD_TYPE")!= null ? map.get("PROD_TYPE").toString():"";
				//包装个数pack_quantity
				retVal[6] = map.get("PACK_QUANTITY")!= null ? map.get("PACK_QUANTITY").toString():"";
				retVal[0] = MesConstants.SUCCESS;
				retVal[1] = "请求成功！";
			}else {
				retVal[0] = MesConstants.ERROR;
				retVal[1] = "小包装箱不存在！";
			}
		}catch (Exception e){
			e.printStackTrace();
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "exception";
		}
		return  retVal;
	}

	/**
	 *  大大包装 comit
	 * @param input
	 * @return
	 */
	public  String[] bigPackCommit(String input){
		String [] retVal = new String[2];
		if (StringUtils.isEmpty(input)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "数据异常";
			return retVal;
		}
		// 接收转换json数据
		BigPackModel bigPackModel = JsonObjectConverTools.jsonToObject(input,BigPackModel.class);
		// 登录用户
		String loginName = bigPackModel.getUserid();
		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
		if (user == null) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录用户不存在，请联系管理员";
			return retVal;
		}
		// 组织ID
		String orgId = bigPackModel.getWarehouse();
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织不存在，请联系管理员";
			return retVal;
		}
		List<BigPackModel> bigPackModelList = bigPackModel.getBigPackModelList();
		List<Map<String,Object>> bigList = pm.getPackByBarcode(bigPackModelList.get(0).getBigBarcodeText());
		String bigPackId = null;
		String wxQuantity = "";
		//循环包装
		for (BigPackModel big :bigPackModelList){
			List<Map<String,Object>> smallList = pm.getPackByBarcode(big.getSmallPackBarcodeText());
			if(bigList!=null){
				Map<String,Object> m = bigList.get(0);
				if(m != null){//packing_barcode_id
					bigPackId = m.get("PACKING_BARCODE_ID").toString();
					wxQuantity = m.get("QUANTITY")!=null?m.get("QUANTITY").toString():"0";
				}
			}
			String smallPackId = null;
			if(smallList!=null){
				Map<String,Object> m = smallList.get(0);
				if(m != null){//packing_barcode_id
					smallPackId = m.get("PACKING_BARCODE_ID").toString();
				}
			}
			/**
			 * 包装
			 */
			DbReturnParameter dbreturn = new DbReturnParameter();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", user.getUserId());
			paramMap.put("packingBarcodeId", new BigDecimal(bigPackId));
			paramMap.put("wipBarcodeId", new BigDecimal(smallPackId));
			paramMap.put("wxQuantity",new BigDecimal(big.getNum()));
			paramMap.put(MesConstants.RESULT, "Y");//默认可以包不同工单
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			pm.callProcessPack(paramMap);
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
		// 取得唯一键
		BigDecimal processId = comm.getSeqByName("mes_transactions_process_s");
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", user.getUserId());
		paramMap.put("processId", processId);
		paramMap.put("barCodeId",new BigDecimal(bigPackId));
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		pm.callPackInsertTransactionTemp(paramMap);
		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage();
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			return retVal;
		} else {
			/**
			 * ebs
			 */
			dbreturn = new DbReturnParameter();
			paramMap = new HashMap<String, Object>();
			paramMap.put("processId", processId);
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			pm.callWipMoveTransaction(paramMap);
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage();
			return retVal;
		}
		//return  retVal;
	}

}
