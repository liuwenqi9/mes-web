package com.egdfrm.unit.service.pda;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.egdfrm.unit.mapper.expand.pda.OnLineRepairMapper;
import com.egdfrm.unit.model.expand.pda.OnLineRepairData;
import com.egdfrm.unit.model.expand.pda.SaleReturn;
import com.egdfrm.unit.model.standard.MesPackingHeaders;
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
import com.egdfrm.unit.mapper.expand.pda.InventoryMapper;
import com.egdfrm.unit.mapper.expand.pda.PDACommonMapper;
import com.egdfrm.unit.mapper.expand.pda.SaleReturnsMapper;
import com.egdfrm.unit.mapper.standard.MesItemLocattionsMapper;
import com.egdfrm.unit.mapper.standard.MesPackingHeadersMapper;
import com.egdfrm.unit.model.expand.pda.BackInStorage;
import com.egdfrm.unit.model.expand.pda.StorageTransfer;
import com.egdfrm.unit.model.standard.MesWipBarcodes;
import com.egdfrm.unit.model.standard.MtlSystemItemsB;

/**
 * @author sjf
 * @date 2016年12月20日 销售退货SERVICE
 */
@Service
public class SaleReturnsService {
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
	 * Field im: 库存dao
	 * </p>
	 */
	@Autowired
	private InventoryMapper im;
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
	 * Field srm: 销售退货dao
	 * </p>
	 */
	@Autowired
	private SaleReturnsMapper srm;

	/**
	 * <p>
	 * Field pdacService: PDA共通Service
	 * </p>
	 */
	@Autowired
	private PDACommonService pdacService;

	@Autowired
	private OnLineRepairMapper om;

	/**
	 *  销售退货-扫描RMA订单
	 * @param input
	 * @return
	 */
	public String[] saleReturnsScanRMAOrder(String[] input) {
		// 返回值
		String[] retVal = new String[4];
		// 组织ID
		String orgId = input[1];
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织为空，请联系管理员";
			return retVal;
		}
		String rmaNo = input[2];
		//验证RMA订单
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rmaNo", rmaNo);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		srm.callReturnCheckRma(paramMap);
		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage();
			return retVal;
		}
		retVal[0] = "S";
		return retVal;
	}
	/**
	 * @author sjf
	 * @date 2016年12月27日
	 * @param input
	 * @return PDA销售退货-扫描货位
	 */
	public String[] saleReturnsScanLocations(String[] input) {
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
		// 货位
		String location = input[2];
		/*
		 * 获取原货位信息
		 */
		Map<String, Object> olocMap = new HashMap<String, Object>();
		olocMap = pdacService.getMesItemLocattions(location, orgId);
		String olocFlag = (String) olocMap.get(MesConstants.RESULT);
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(olocFlag)) {
			retVal = (String[]) olocMap.get(MesConstants.RETVAL);
			return retVal;
		}
		retVal[0] = MesConstants.SUCCESS;
		return retVal;
	}
	/**
	 * @author sjf
	 * @date 2016年12月27日
	 * @param input
	 * @return PDA销售退货-扫描条码
	 */
	public String[] saleReturnsScanBarCode(String[] input) {
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
		// 货位
		String rmaNo = input[2];
		// 货位
		String location = input[3];
		// 产品条码
		String barCode = input[4];
		/*
		 * 获取货位信息
		 */
		Map<String, Object> locMap = new HashMap<String, Object>();
		locMap = pdacService.getMesItemLocattions(location, orgId);
		String locFlag = (String) locMap.get(MesConstants.RESULT);
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(locFlag)) {
			retVal = (String[]) locMap.get(MesConstants.RETVAL);
			return retVal;
		}
		/**
		 * 获取包装箱/产品信息
		 */
		// 包装箱/产品ID
		BigDecimal wipBarcodeId  = null;
		BigDecimal primaryItemId = null;
		Map<String, Object> pwMap = new HashMap<String, Object>();
		pwMap = pdacService.getPackOrWip(barCode, orgId);
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
			primaryItemId = mwb.getPrimaryItemId();
		} else if (MesConstants.PACK == type ) {
			MesPackingHeaders mph2 = (MesPackingHeaders) pwMap.get(MesConstants.OBJECT);
			wipBarcodeId = mph2.getPackingBarcodeId();
			primaryItemId = mph2.getInventoryItemId();
		}
		/**
		 *  扫描条码时 验证
		 */
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("location", location);
		paramMap.put("packingBarcodeId", wipBarcodeId);
		paramMap.put("rmaNo", rmaNo);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		srm.callReturnCheck(paramMap);
		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage();
			return retVal;
		}

		/*
		 * 获取物料信息
		 */
		Map<String, Object> msMap = new HashMap<String, Object>();
		msMap = pdacService.getInventoryItemInfo(primaryItemId,new BigDecimal(orgId));
		String msFlag = (String) msMap.get(MesConstants.RESULT);
		MtlSystemItemsB ms = new MtlSystemItemsB();
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(msFlag)) {
			retVal = (String[]) msMap.get(MesConstants.RETVAL);
			return retVal;
		} else {
			ms = (MtlSystemItemsB) msMap.get("ms");
		}
		// 物料号
		String lotNo = ms.getSegment1();
		// 描述
		String desr = ms.getDescription();
		//取箱子数量

		List<SaleReturn> list =  srm.getSaleReturnStyle(wipBarcodeId);
		SaleReturn saleReturn = (list!=null&&!list.isEmpty())?list.get(0):null;

		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = lotNo;//物料编码
		retVal[2] = desr;//物料描述
		retVal[3] = saleReturn!=null?saleReturn.getNum():"1";//数量
		return retVal;
	}
	/**
	 *  提交前 验证， 提交失败，回滚
	 * @return PDA销售退货-提交
	 */
	public String[] saleReturnsCommit(String jsonString) {
		// 返回值
		String[] retVal = new String[2];
		if (StringUtils.isEmpty(jsonString)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "数据异常";
			return retVal;
		}
		// 接收转换json数据
		BackInStorage initBs = JsonObjectConverTools.jsonToObject(jsonString,BackInStorage.class);
		// 登录用户
		String loginName = initBs.getUserid();
		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
		if (user == null) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录用户不存在，请联系管理员";
			return retVal;
		}
		// 组织ID
		String orgId = initBs.getWarehouse();
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织不存在，请联系管理员";
			return retVal;
		}
		List<BackInStorage> bsList = initBs.getBackInStorage();
		// 取得唯一键
		BigDecimal processId = comm.getSeqByName("mes_transactions_process_s");
		// 循环信息
		for (BackInStorage bs : bsList) {
			// 产品条码
			String barCode = bs.getSnNo();
			/**
			 * 获取包装箱/产品信息
			 */
			// 包装箱/产品ID
			BigDecimal wipBarcodeId = null;
			Map<String, Object> pwMap = new HashMap<String, Object>();
			pwMap = pdacService.getPackOrWip(barCode, orgId);
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
			 *  插入前 检验
			 */
			DbReturnParameter dbreturn = new DbReturnParameter();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("location", bs.getWareLoca());
			paramMap.put("packingBarcodeId", wipBarcodeId);
			paramMap.put("rmaNo", bs.getRmaNo());
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			srm.callReturnCheckRma(paramMap);
			// 返回错误信息
			if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
				retVal[0] = dbreturn.getxStatus();
				retVal[1] = dbreturn.getxMessage();
				System.out.println(barCode+"条码验证失败！");
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return retVal;
			}


			/**
			 *  插入前 检验 2
			 * */
			paramMap.put("location", bs.getWareLoca());
			paramMap.put("packingBarcodeId", wipBarcodeId);
			paramMap.put("rmaNo", bs.getRmaNo());
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			srm.callReturnCheck(paramMap);
			// 返回错误信息
			if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
				retVal[0] = dbreturn.getxStatus();
				retVal[1] = dbreturn.getxMessage();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return retVal;
			}
			/**
			 * 循环插入
			 */
			dbreturn = new DbReturnParameter();
			Map<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("userId", user.getUserId());
			paramMap2.put("processId", processId);
			paramMap2.put("location", bs.getWareLoca());
			paramMap2.put("packingBarcodeId",wipBarcodeId);
			paramMap2.put("rmaNo", bs.getRmaNo());
			paramMap2.put(MesConstants.DBRETURN, dbreturn);
			srm.callReturnTransactionTemp(paramMap2);

			// 返回错误信息
			if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
				retVal[0] = dbreturn.getxStatus();
				retVal[1] = dbreturn.getxMessage();
				System.out.println(barCode+"条码插入失败，！");
				TransactionAspectSupport.currentTransactionStatus() .setRollbackOnly();
				return retVal;
			}
		}
		/**
		 * 更新状态
		 */
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("processId", processId); 
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		srm.callReturnProcessMes(paramMap);

		// 错误则回滚
		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
		}
		retVal[0] = dbreturn.getxStatus();
		retVal[1] = dbreturn.getxMessage();
		return retVal;
	}


	/**
	 *  销售确认
	 * @param input
	 * @return
	 */
	public String[] salesReturnConfirm(String[] input ){
		String[] retVal = new String[2];
		String orgId = input[1];
		String rmaNo = input[2];
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织为空，请联系管理员";
			return retVal;
		}
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rmaNo", rmaNo);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		srm.callUpdateOrder(paramMap);
		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage();
			return retVal;
		}

		retVal[0] ="S";
		retVal[1] = "确认成功！";
		return retVal;
	}


}
