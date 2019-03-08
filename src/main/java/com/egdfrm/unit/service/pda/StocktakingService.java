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
import com.egdfrm.core.service.BaseService;
import com.egdfrm.extend.common.DbReturnParameter;
import com.egdfrm.extend.common.JsonObjectConverTools;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.mapper.expand.CommonMapper;
import com.egdfrm.unit.mapper.expand.pda.OnLineRepairMapper;
import com.egdfrm.unit.mapper.expand.pda.PDACommonMapper;
import com.egdfrm.unit.mapper.expand.pda.StocktakingMapper;
import com.egdfrm.unit.mapper.standard.MesItemLocattionsMapper;
import com.egdfrm.unit.mapper.standard.MesPackingHeadersMapper;
import com.egdfrm.unit.model.expand.pda.OnLineRepairData;
import com.egdfrm.unit.model.expand.pda.SecondaryPack;
import com.egdfrm.unit.model.expand.pda.StocktakingCommit;
import com.egdfrm.unit.model.expand.pda.StocktakingCommit.StockTaking;
import com.egdfrm.unit.model.standard.MesPackingHeaders;
import com.egdfrm.unit.model.standard.MesWipBarcodes;

/**
 * @author sjf
 * @date 2016年12月20日 
 * 盘点 SERVICE
 */
@Service
public class StocktakingService extends BaseService{
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
	 * Field pdacService: PDA共通Service
	 * </p>
	 */
	@Autowired
	private PDACommonService pdacService;
//	/**
//	 * @author sjf
//	 * @date 2016年12月21日 
//	 * @param input
//	 * @return
//	 * PDA子库转移-扫描
//	 */
//	public String[] inventoryTransferScan(String[] input) {
//		// 返回值
//		String[] retVal = new String[4];
//		// 登录用户
//		// String loginName = input[0];
//		// 组织ID
//		String orgId = input[1];
//		if (StringUtils.isEmpty(orgId)) {
//			retVal[0] = MesConstants.ERROR;
//			retVal[1] = "登录组织为空，请联系管理员";
//			return retVal;
//		}
//		// 原货位
//		String oldLocation = input[2];
//		// 新货位
//		String newLocation = input[3];
//		// 包装条码
//		String packBarcode = input[4];
//		
//		
//		if(oldLocation.equals(newLocation)){
//			retVal[0] = MesConstants.ERROR;
//			retVal[1] = "新旧货位"+newLocation+"一致，请重输";
//			return retVal;
//		}
//		/*
//		 * 获取原货位信息
//		 */
//		Map<String, Object> olocMap=new HashMap<String, Object>();
//		olocMap=pdacService.getMesItemLocattions(oldLocation, orgId);
//		String olocFlag=(String) olocMap.get(MesConstants.RESULT); 
//		//如果出错，则直接返回
//		if(MesConstants.ERROR.equals(olocFlag)){
//			retVal=(String[]) olocMap.get(MesConstants.RETVAL);
//			return retVal;
//		} 
//		/*
//		 * 获取包装箱信息
//		 */
//		Map<String, Object> packMap=new HashMap<String, Object>();
//		packMap=pdacService.getPackingHeaders(packBarcode, orgId);
//		String flag=(String) packMap.get(MesConstants.RESULT);
//		MesPackingHeaders mph = new MesPackingHeaders();
//		//如果出错，则直接返回
//		if(MesConstants.ERROR.equals(flag)){
//			retVal=(String[]) packMap.get(MesConstants.RETVAL);
//			return retVal;
//		}else{
//			mph=(MesPackingHeaders) packMap.get("mph");
//		}
//		/*
//		 * 获取新货位信息
//		 */
//		Map<String, Object> nlocMap=new HashMap<String, Object>();
//		nlocMap=pdacService.getMesItemLocattions(newLocation, orgId);
//		String nlocFlag=(String) nlocMap.get(MesConstants.RESULT); 
//		//如果出错，则直接返回
//		if(MesConstants.ERROR.equals(nlocFlag)){
//			retVal=(String[]) nlocMap.get(MesConstants.RETVAL);
//			return retVal;
//		} 
//		//据包装条码Id获取物料编码和描述
//		StorageTransfer st=im.getItemidByPackBarcodeId(mph.getPackingBarcodeId());
//
//		retVal[0] = MesConstants.SUCCESS;
//		retVal[1] = st.getLotNo();
//		retVal[2] = st.getDesr();
//		return retVal;
//	}
//	/**
//	 * @author sjf
//	 * @date 2016年12月21日 
//	 * @param jsonString
//	 * @return
//	 * PDA子库转移-提交
//	 */
//	public String[] inventoryTransferCommit(String jsonString) {
//		// 返回值
//		String[] retVal = new String[2];
//		if (StringUtils.isEmpty(jsonString)) {
//			retVal[0] = MesConstants.ERROR;
//			retVal[1] = "数据异常";
//			return retVal;
//		}
//		// 接收转换json数据
//		StorageTransfer initSt = JsonObjectConverTools.jsonToObject(jsonString,
//				StorageTransfer.class);
//		// 登录用户
//		String loginName = initSt.getUserid();
//		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
//		if (user == null) {
//			retVal[0] = MesConstants.ERROR;
//			retVal[1] = "登录用户不存在，请联系管理员";
//			return retVal;
//		}
//		// 组织ID
//		String orgId = initSt.getWarehouse();
//		if (StringUtils.isEmpty(orgId)) {
//			retVal[0] = MesConstants.ERROR;
//			retVal[1] = "登录组织不存在，请联系管理员";
//			return retVal;
//		}
//		List<StorageTransfer> sList = initSt.getStorageTransfer();
//		//取得唯一键
//		BigDecimal processId = comm.getSeqByName("mes_transactions_process_s");
//		// 循环信息
//		for (StorageTransfer st : sList) {
//			//旧货位
//			String oldLocation=st.getOldWareLoca();
//			//新货位
//			String newLocation=st.getNewWareLoca();
//			//包装条码
//			String packBarcode=st.getSnNo();
//			
//			if(oldLocation.equals(newLocation)){
//				retVal[0] = MesConstants.ERROR;
//				retVal[1] = "新旧货位"+newLocation+"一致，请重输";
//				return retVal;
//			}
//			/*
//			 * 获取原货位信息
//			 */
//			Map<String, Object> olocMap=new HashMap<String, Object>();
//			olocMap=pdacService.getMesItemLocattions(oldLocation, orgId);
//			String olocFlag=(String) olocMap.get(MesConstants.RESULT); 
//			//如果出错，则直接返回
//			if(MesConstants.ERROR.equals(olocFlag)){
//				retVal=(String[]) olocMap.get(MesConstants.RETVAL);
//				return retVal;
//			} 
//			/*
//			 * 获取包装箱信息
//			 */
//			Map<String, Object> packMap=new HashMap<String, Object>();
//			packMap=pdacService.getPackingHeaders(packBarcode, orgId);
//			String flag=(String) packMap.get(MesConstants.RESULT);
//			MesPackingHeaders mph = new MesPackingHeaders();
//			//如果出错，则直接返回
//			if(MesConstants.ERROR.equals(flag)){
//				retVal=(String[]) packMap.get(MesConstants.RETVAL);
//				return retVal;
//			}else{
//				mph=(MesPackingHeaders) packMap.get("mph");
//			}
//			/*
//			 * 获取新货位信息
//			 */
//			Map<String, Object> nlocMap=new HashMap<String, Object>();
//			nlocMap=pdacService.getMesItemLocattions(newLocation, orgId);
//			String nlocFlag=(String) nlocMap.get(MesConstants.RESULT); 
//			//如果出错，则直接返回
//			if(MesConstants.ERROR.equals(nlocFlag)){
//				retVal=(String[]) nlocMap.get(MesConstants.RETVAL);
//				return retVal;
//			} 
//			/**
//			 * 写入MES临时接口表
//			 */
//			DbReturnParameter dbreturn = new DbReturnParameter();
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("userId", user.getUserId());
//			paramMap.put("processId", processId);
//			paramMap.put("packingBarcodeId", mph.getPackingBarcodeId());
//			paramMap.put("oldLocation", oldLocation);
//			paramMap.put("newLocation", newLocation);
//			paramMap.put(MesConstants.DBRETURN, dbreturn);
//			im.callTranInsertTransactionTemp(paramMap);
//			// 返回错误信息
//			if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
//				retVal[0] = dbreturn.getxStatus();
//				retVal[1] = dbreturn.getxMessage();
//				TransactionAspectSupport.currentTransactionStatus()
//				.setRollbackOnly();
//				return retVal;
//			}
//		}
//		/**
//		 * 写入MES_TRANSACTION
//		 */
//		DbReturnParameter dbreturn = new DbReturnParameter();
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("processId", processId);
//		paramMap.put(MesConstants.TYPE, "TRANSFER");
//		paramMap.put(MesConstants.DBRETURN, dbreturn);
//		pdam.callProcessBarcode(paramMap);
//
//		// 返回错误信息
//		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
//			retVal[0] = dbreturn.getxStatus();
//			retVal[1] = dbreturn.getxMessage();
//			TransactionAspectSupport.currentTransactionStatus()
//			.setRollbackOnly();
//			return retVal;
//		}
//		/**
//		 * 子库存/货位转移
//		 */
//		im.callMesBarcodesTransfer(paramMap);
//
//		// 返回错误信息
//		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
//			retVal[0] = dbreturn.getxStatus();
//			retVal[1] = dbreturn.getxMessage(); 
//			return retVal;
//		}else{
//			retVal[0] = MesConstants.SUCCESS; 
//			return retVal;
//		}
//	}
// 
	/**
	 * @author sjf
	 * @date 2016年12月26日 
	 * @return
	 * PDA库存盘点-初始化获取序列号
	 */
	public String[] stocktakingInitSeq(String[] input) {
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
		 //mes_barcodes_pd_pkg.get_pd_number(p_organization_id);
		String sql="select apps.mes_barcodes_pd_pkg.get_pd_number("+orgId+") from dual";
		String PDnumber=getImqm().queryOneRowOneValue(sql);
		if (!StringUtils.isEmpty(PDnumber)) {
			retVal[0] = MesConstants.SUCCESS;
			retVal[1] = PDnumber;
			return retVal;
		}else {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "没有最新盘点序列号";
			return retVal;
		}
	}
	/**
	 * @author sjf
	 * @date 2016年12月26日 
	 * @return
	 * PDA库存盘点-扫描货位
	 */
	public String[] stocktakingScanLocattion(String[] input) {
		
		// 返回值
		String[] retVal = new String[4];
		// 登录用户
		// String loginName = input[0];
		String orgId = input[1];
		//货位
		String locationCode=input[2];
		if (StringUtils.isEmpty(locationCode)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "货位不能为空";
			return retVal;
		}
		// 组织ID
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织为空，请联系管理员";
			return retVal;
		}
		//盘点号
		String PDnumber=input[3];
		if (StringUtils.isEmpty(PDnumber)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "盘点号不能为空";
			return retVal;
		}
		// 检测货位有效性
		Map<String, Object> locationMap = new HashMap<String, Object>();
		locationMap = pdacService.getMesItemLocattions(locationCode, orgId);
		String flag = (String) locationMap.get(MesConstants.RESULT);
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(flag)) {
			retVal = (String[]) locationMap.get(MesConstants.RETVAL);
			return retVal;
		}
		//检测是否是本次盘点的货位
		String sql="select apps.mes_barcodes_pd_pkg.get_locattion_code("+orgId+",'"+locationCode+"','"+PDnumber+"') from dual";
		String rightLocation=getImqm().queryOneRowOneValue(sql);
		if (rightLocation==null||!rightLocation.equals(locationCode)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "此货位不是本次盘点的货位";
			return retVal;
		}
		// 返回成功
		retVal[0] = MesConstants.SUCCESS;
		return retVal;
	}
	/**
	 * @author sjf
	 * @date 2016年12月26日 
	 * @return
	 * PDA库存盘点-扫描包装/产品条码
	 */
	public String[] stocktakingScanWip(String[] input) {
		// getPackOrWip
		// 返回值
		String[] retVal = new String[6];
		// 登录用户
		// String loginName = input[0];
		String orgId = input[1];
		// 货位
		String locationCode = input[3];
		if (StringUtils.isEmpty(locationCode)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "货位不能为空";
			return retVal;
		}
		// 产品/包装条码
		String code = input[4];
		if (StringUtils.isEmpty(code)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "产品/包装条码不能为空";
			return retVal;
		}
		// 组织ID
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织为空，请联系管理员";
			return retVal;
		}
		// getMesItemLocattions
		
		Map<String, Object> packOrProductMap = new HashMap<String, Object>();
		
		packOrProductMap = pdacService.getPackOrWip(code, orgId);
		
		String flag = (String) packOrProductMap.get(MesConstants.RESULT);
		// 如果出错，则直接返回
		if (MesConstants.ERROR.equals(flag)) {
			retVal = (String[]) packOrProductMap.get(MesConstants.RETVAL);
			return retVal;
		}
		

		// 返回成功
		retVal[0] = MesConstants.SUCCESS;
		//retMap.put(MesConstants.TYPE, MesConstants.WIP);
		;
		BigDecimal barcodeId=null;
		int type = (int)packOrProductMap.get(MesConstants.TYPE) ;
		if(type==MesConstants.PACK){
			MesPackingHeaders pack = (MesPackingHeaders) packOrProductMap.get(MesConstants.OBJECT);
			 barcodeId=pack.getPackingBarcodeId();
		}else if(type==MesConstants.WIP) {
			MesWipBarcodes product  = (MesWipBarcodes) packOrProductMap.get(MesConstants.OBJECT);
			barcodeId=product.getWipBarcodeId();
		};
		//C01
		//B1703003271
		// 包里面的检查包装/产品方法
		// mes_barcodes_pd_pkg.check_barcode(p_packing_barcode_id =>
		// :p_packing_barcode_id,
		// x_return_status => :x_return_status,
		// x_msg_data => :x_msg_data);
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("packingBarcodeId", barcodeId);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		sm.callCheckBarcode(paramMap);

		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage();
			return retVal;
		}

		//此查询一般需要5秒钟
		long pass=System.currentTimeMillis();
		
		List<OnLineRepairData> result = om.getRepairStyle2(barcodeId);//@DATE 2017-08-01
		long now=System.currentTimeMillis();
		long runUseTime=now-pass;
		System.out.println("此代码执行时间==>"+runUseTime);
		if (result.isEmpty()) {
			retVal[0] = MesConstants.ERROR;
			if (type == MesConstants.PACK) {
				retVal[1]="该包装箱为空，请检查";
			} else if (type == MesConstants.WIP) {
				retVal[1]="该产品不存在，请检查";
			}
			return retVal;
		}
		int num=0;
		String strNum;
		String materialCode="";
		//统计数量
		String category="";
		
		
		for (OnLineRepairData data:result) {
			num+=Integer.valueOf(data.getNum());
			materialCode+=data.getLotNo()+"/";
			//找产品类型
			String queryCategorySql="select apps.mes_barcodes_pd_pkg.get_prod_type(ms.INVENTORY_ITEM_ID) from apps.mtl_system_items_b ms where ms.segment1 = '"+data.getLotNo()+"'"+"and ms.organization_id= "+orgId;
			category+=getImqm().queryOneRowOneValue(queryCategorySql)+"/";
		}
		
		strNum=String.valueOf(num);
		//把最后一个斜杆去掉
		materialCode=materialCode.substring(0,materialCode.length()-1);
		category=category.substring(0,category.length()-1);
		retVal[1]=strNum;
		retVal[2]=materialCode;
		retVal[3]=category;
//		retVal[4]=locationCode;
//		retVal[5]=code;
		return retVal;
	}
	
	/**
	 * <p>
	 * Field om: 上线返修dao
	 * </p>
	 */
	@Autowired
	private OnLineRepairMapper om;
	
	/**
	 * @author sjf
	 * @date 2016年12月26日 
	 * @return
	 * PDA库存盘点-提交
	 */
	public String[] stocktakingCommit(String jsonString) {
		// 返回值
		String[] retVal = new String[6];
		// userid，warehouse，stockTaking
		// "checkNo", "wareLoca", "snNo", "num"
		StocktakingCommit stocktakingCommit=JsonObjectConverTools.jsonToObject(jsonString, StocktakingCommit.class);
		if (stocktakingCommit == null) {
			// 组织ID
			// if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "请求参数有误";
			return retVal;
		}
		if (StringUtils.isEmpty(stocktakingCommit.getUserid())) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "用户不能为空";
			return retVal;
		}
		if (StringUtils.isEmpty(stocktakingCommit.getWarehouse())) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "组织不能为空";
			return retVal;
		}
		List<StockTaking> stockTakings= stocktakingCommit.getStockTaking();
		if (stockTakings==null||stockTakings.size()<=0) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "盘点数据不能为空";
			return retVal;
		}
//		mes_barcodes_pd_pkg.pd_line(
		//p_organization_id,
//                p_pd_number,
//                p_user_id,
//                p_locator,
//                p_packing_barcode_id,
//                x_return_status,
//                x_msg_data);
		for (StockTaking stockTaking : stockTakings) {
			DbReturnParameter dbreturn = new DbReturnParameter();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// 判断条码是包装条码还是产品条码 包装条码S/B 产品条码C/H
			String barCodeText = stockTaking.getSnNo();
			String queryBarcodeIdSql = "";
			if (barCodeText.charAt(0) == MesConstants.SMALL_PACK
					|| barCodeText.charAt(0) == MesConstants.BIG_PACK) {
				queryBarcodeIdSql = "select PACKING_BARCODE_ID from mes_packing_headers where BARCODE_TEXT= '"+ barCodeText+"'";

			} else if (barCodeText.charAt(0) == MesConstants.NX_PRODUCT
					|| barCodeText.charAt(0) == MesConstants.WX_PRODUCT) {
				queryBarcodeIdSql = "select WIP_BARCODE_ID from mes_wip_barcodes where BARCODE_TEXT= '"+ barCodeText+"'";
			} else {
				retVal[0] = MesConstants.ERROR;
				retVal[1] = "产品或者包装条码非法";
			}

			//查询USERID
			//根据用户登录名查用户名
			String realUserId="";
			String queryUserNameSql="select USER_ID from tt_application_user where LOGIN_NAME='"+stocktakingCommit.getUserid()+"'";
			realUserId=getImqm().queryOneRowOneValue(queryUserNameSql);
			String barcodeId = getImqm().queryOneRowOneValue(queryBarcodeIdSql);
			paramMap.put("organizationId", stocktakingCommit.getWarehouse());
			// 不传数量？是
			paramMap.put("pdNumber", stockTaking.getCheckNo());
			paramMap.put("userId", realUserId);
			paramMap.put("location", stockTaking.getWareLoca());
			//要传码ID？是
			paramMap.put("barcodeId", barcodeId);
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			sm.callStocktakingCommit(paramMap);
			// TODO 192.168.1.96服务器获取盘点号返回数据为空？

			// 返回错误信息
			if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
				retVal[0] = dbreturn.getxStatus();
				retVal[1] = dbreturn.getxMessage();
				TransactionAspectSupport.currentTransactionStatus()
						.setRollbackOnly();
				return retVal;
			}
		}

		// 检查是否缺少参数
		// 返回成功
		retVal[0] = MesConstants.SUCCESS;
		return retVal;
	}

}
