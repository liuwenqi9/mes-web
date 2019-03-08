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
import com.egdfrm.unit.mapper.expand.pda.InventoryMapper;
import com.egdfrm.unit.mapper.expand.pda.PDACommonMapper;
import com.egdfrm.unit.mapper.standard.MesItemLocattionsMapper;
import com.egdfrm.unit.mapper.standard.MesPackingHeadersMapper;
import com.egdfrm.unit.model.expand.pda.SaleOutStorage;
import com.egdfrm.unit.model.expand.pda.StorageTransfer;
import com.egdfrm.unit.model.standard.MesPackingHeaders;
import com.egdfrm.unit.model.standard.MesWipBarcodes;

/**
 * @author sjf
 * @date 2016年12月20日 
 * 库存SERVICE
 */
@Service
public class InventoryService {
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
	 * Field pdacService: PDA共通Service
	 * </p>
	 */
	@Autowired
	private PDACommonService pdacService;
	/**
	 * @author sjf
	 * @date 2016年12月21日 
	 * @param input
	 * @return
	 * PDA子库转移-扫描
	 */
	public String[] inventoryTransferScan(String[] input) {
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
		// 原货位
		String oldLocation = input[2];
		// 新货位
		String newLocation = input[3];
		// 包装条码
		String packBarcode = input[4];
		
		
		if(oldLocation.equals(newLocation)){
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "新旧货位"+newLocation+"一致，请重输";
			return retVal;
		}
		/*
		 * 获取原货位信息
		 */
		Map<String, Object> olocMap=new HashMap<String, Object>();
		olocMap=pdacService.getMesItemLocattions(oldLocation, orgId);
		String olocFlag=(String) olocMap.get(MesConstants.RESULT); 
		//如果出错，则直接返回
		if(MesConstants.ERROR.equals(olocFlag)){
			retVal=(String[]) olocMap.get(MesConstants.RETVAL);
			return retVal;
		} 
		//2017.03.10，兰继明修改
		//子库转移改为可以转产品，因为如果发现某个产品挑错库后要取消挑库，是通过子库转移的方式取消的。
		// 包装箱/产品ID
		BigDecimal wipBarcodeId = null;
		Map<String, Object> pwMap = new HashMap<String, Object>();
		pwMap = pdacService.getPackOrWip(packBarcode, orgId);
		String pwFlag = (String) pwMap.get(MesConstants.RESULT);
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(pwFlag)) {
			retVal = (String[]) pwMap.get(MesConstants.RETVAL);
			return retVal;
		}
		
		// 物料ID
		BigDecimal invId = null;
		// 取得是包装还是产品
		int type = (int) pwMap.get(MesConstants.TYPE);
		if (type == MesConstants.PACK) {
			MesPackingHeaders mph = (MesPackingHeaders) pwMap
					.get(MesConstants.OBJECT);
			invId = mph.getInventoryItemId();
			wipBarcodeId = mph.getPackingBarcodeId();
		} else if (type == MesConstants.WIP) {
			MesWipBarcodes mwb = (MesWipBarcodes) pwMap
					.get(MesConstants.OBJECT);
			invId = mwb.getPrimaryItemId();
			wipBarcodeId = mwb.getWipBarcodeId();
		}
		// 验证产品是否可以转移货位（放在包装箱内的不行，）
		// mes_barcodes_transfer_pkg.pda_check(p_packing_barcode_id)
		Map<String, Object> paramMap1 = new HashMap<String, Object>();
		DbReturnParameter dbreturn1 = new DbReturnParameter();
		paramMap1.put("packingBarcodeId", wipBarcodeId);
		paramMap1.put(MesConstants.DBRETURN, dbreturn1);
		im.callCheckIsProductLocationCanTransfer(paramMap1);
		// 如果验证不通过则返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn1.getxStatus())) {
			retVal[0] = dbreturn1.getxStatus();
			retVal[1] = dbreturn1.getxMessage();
			return retVal;
		}
		/*
		 * 获取新货位信息
		 */
		Map<String, Object> nlocMap=new HashMap<String, Object>();
		nlocMap=pdacService.getMesItemLocattions(newLocation, orgId);
		String nlocFlag=(String) nlocMap.get(MesConstants.RESULT); 
		//如果出错，则直接返回
		if(MesConstants.ERROR.equals(nlocFlag)){
			retVal=(String[]) nlocMap.get(MesConstants.RETVAL);
			return retVal;
		} 
		if (type == MesConstants.PACK) {
			//据包装条码Id获取物料编码和描述
			//wipBarcodeId
			StorageTransfer st=im.getItemidByPackBarcodeId(wipBarcodeId);
			retVal[0] = MesConstants.SUCCESS;
			retVal[1] = st.getLotNo();
			retVal[2] = st.getDesr();
			return retVal;
			
		} else if (type == MesConstants.WIP) {
			Map<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("orgId", new BigDecimal(orgId));
			paramMap2.put("invId", invId);
			//据产品条码Id获取物料编码和描述
			//wipBarcodeId
			StorageTransfer st=im.getProductItemAndDesc(paramMap2);
			if (st == null) {
				retVal[0] = MesConstants.ERROR;
				retVal[1] = "物料信息不存在";
				return retVal;
			}
			retVal[0] = MesConstants.SUCCESS;
			retVal[1] = st.getLotNo();
			retVal[2] = st.getDesr();
			return retVal;
		}
		retVal[0] = MesConstants.ERROR;
		retVal[2] = "";
		return retVal;
	}
	/**
	 * @author sjf
	 * @date 2016年12月21日 
	 * @param jsonString
	 * @return
	 * PDA子库转移-提交
	 */
	public String[] inventoryTransferCommit(String jsonString) {
		// 返回值
		String[] retVal = new String[2];
		if (StringUtils.isEmpty(jsonString)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "数据异常";
			return retVal;
		}
		// 接收转换json数据
		StorageTransfer initSt = JsonObjectConverTools.jsonToObject(jsonString,
				StorageTransfer.class);
		// 登录用户
		String loginName = initSt.getUserid();
		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
		if (user == null) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录用户不存在，请联系管理员";
			return retVal;
		}
		// 组织ID
		String orgId = initSt.getWarehouse();
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织不存在，请联系管理员";
			return retVal;
		}
		List<StorageTransfer> sList = initSt.getStorageTransfer();
		//取得唯一键
		BigDecimal processId = comm.getSeqByName("mes_transactions_process_s");
		// 循环信息
		for (StorageTransfer st : sList) {
			//旧货位
			String oldLocation=st.getOldWareLoca();
			//新货位
			String newLocation=st.getNewWareLoca();
			//包装条码
			String packBarcode=st.getSnNo();
			
			if(oldLocation.equals(newLocation)){
				retVal[0] = MesConstants.ERROR;
				retVal[1] = "新旧货位"+newLocation+"一致，请重输";
				return retVal;
			}
			/*
			 * 获取原货位信息
			 */
			Map<String, Object> olocMap=new HashMap<String, Object>();
			olocMap=pdacService.getMesItemLocattions(oldLocation, orgId);
			String olocFlag=(String) olocMap.get(MesConstants.RESULT); 
			//如果出错，则直接返回
			if(MesConstants.ERROR.equals(olocFlag)){
				retVal=(String[]) olocMap.get(MesConstants.RETVAL);
				return retVal;
			} 
			/*
			 * 获取包装箱信息
			 */
			Map<String, Object> pwMap = new HashMap<String, Object>();
			pwMap = pdacService.getPackOrWip(packBarcode, orgId);
			String pwFlag = (String) pwMap.get(MesConstants.RESULT);
			// 如果出错，则直接返回
			if (MesConstants.ERROR.equals(pwFlag)) {
				retVal = (String[]) pwMap.get(MesConstants.RETVAL);
				return retVal;
			}
			// 包装箱/产品ID
			BigDecimal wipBarcodeId = null;
			// 取得是包装还是产品
			int type = (int) pwMap.get(MesConstants.TYPE);
			if (type == MesConstants.PACK) {
				MesPackingHeaders mph = (MesPackingHeaders) pwMap
						.get(MesConstants.OBJECT);
				wipBarcodeId = mph.getPackingBarcodeId();
			} else if (type == MesConstants.WIP) {
				MesWipBarcodes mwb = (MesWipBarcodes) pwMap
						.get(MesConstants.OBJECT);
				wipBarcodeId = mwb.getWipBarcodeId();
			}
			/*
			 * 获取新货位信息
			 */
			Map<String, Object> nlocMap=new HashMap<String, Object>();
			nlocMap=pdacService.getMesItemLocattions(newLocation, orgId);
			String nlocFlag=(String) nlocMap.get(MesConstants.RESULT); 
			//如果出错，则直接返回
			if(MesConstants.ERROR.equals(nlocFlag)){
				retVal=(String[]) nlocMap.get(MesConstants.RETVAL);
				return retVal;
			} 
			/**
			 * 写入MES临时接口表
			 */
			DbReturnParameter dbreturn = new DbReturnParameter();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", user.getUserId());
			paramMap.put("processId", processId);
			paramMap.put("packingBarcodeId", wipBarcodeId);
			paramMap.put("oldLocation", oldLocation);
			paramMap.put("newLocation", newLocation);
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			im.callTranInsertTransactionTemp(paramMap);
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
		 * 写入MES_TRANSACTION
		 */
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("processId", processId);
		paramMap.put(MesConstants.TYPE, "TRANSFER");
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		pdam.callProcessBarcode(paramMap);

		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage();
			TransactionAspectSupport.currentTransactionStatus()
			.setRollbackOnly();
			return retVal;
		}
		/**
		 * 子库存/货位转移
		 */
		im.callMesBarcodesTransfer(paramMap);

		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage(); 
			return retVal;
		}else{
			retVal[0] = MesConstants.SUCCESS; 
			return retVal;
		}
	}
 

}
