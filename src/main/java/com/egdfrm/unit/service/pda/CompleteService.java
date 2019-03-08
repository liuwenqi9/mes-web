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
import com.egdfrm.unit.mapper.expand.pda.CompleteMapper;
import com.egdfrm.unit.mapper.expand.pda.PDACommonMapper;
import com.egdfrm.unit.mapper.standard.MesItemLocattionsMapper;
import com.egdfrm.unit.mapper.standard.MesPackingHeadersMapper;
import com.egdfrm.unit.model.expand.pda.WoInStorage;
import com.egdfrm.unit.model.standard.MesPackingHeaders;
/**
 * @author sjf
 * @date 2016年12月15日 工单SERVICE
 **/
@Service
public class CompleteService {
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
	 * Field cm: 工单dao
	 * </p>
	 */
	@Autowired
	private CompleteMapper cm;
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
	 * @date 2016年12月20日
	 * @param packBarcode
	 * @return PDA工单完工入库-获取建议货位
	 */
	public String[] getAdviseLocations(String[] receiveVal) {
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
		String packBarcode = receiveVal[2];
		/*
		 * 获取包装箱信息
		 */ 
		Map<String, Object> packMap=new HashMap<String, Object>();
		packMap=pdacService.getPackingHeaders(packBarcode, orgId);
		String flag=(String) packMap.get(MesConstants.RESULT);
		MesPackingHeaders mph = new MesPackingHeaders();
		//如果出错，则直接返回
		if(MesConstants.ERROR.equals(flag)){
			retVal=(String[]) packMap.get(MesConstants.RETVAL);
			return retVal;
		}else{
			mph=(MesPackingHeaders) packMap.get("mph");
		}
		//获取建议货位
		String location = cm.getAdviseLocations(mph.getPackingBarcodeId());
		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = location==null?"无":location;
		return retVal;
	}
	/**
	 * @author 兰继明
	 * @date 2016年01月15日
	 * @param input
	 * @return 
	 * PDA工单完工入库-较验货位（全部调用pl/sql）
	 */
	public String[] completeCheckLocations(String[] input) {
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
		
		// 货位
		String locations = input[2];

		
		/*
		 * 获取货位信息
		 */
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("orgId", orgId);
		paramMap.put("location",locations );
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		cm.callCheckLocation(paramMap);

		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage();
			
			return retVal;
		}
		
		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = "货位有效";
		
		return retVal;
	}
	/**
	 * @author sjf
	 * @date 2016年12月20日
	 * @param input
	 * @return PDA工单完工入库-扫描货位
	 */
	public String[] completeScanLocations(String[] input) {
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
		// 货位
		String locations = input[3];

		/*
		 * 获取包装箱信息
		 */
		Map<String, Object> packMap=new HashMap<String, Object>();
		packMap=pdacService.getPackingHeaders(packBarcode, orgId);
		String packFlag=(String) packMap.get(MesConstants.RESULT);
		MesPackingHeaders mph = new MesPackingHeaders();
		//如果出错，则直接返回
		if(MesConstants.ERROR.equals(packFlag)){
			retVal=(String[]) packMap.get(MesConstants.RETVAL);
			return retVal;
		}else{
			mph=(MesPackingHeaders) packMap.get("mph");
		}
		/*
		 * 获取货位信息//兰继明修改，独立校验货位信息，先校验货位信息
		 */
//		Map<String, Object> locMap=new HashMap<String, Object>();
//		locMap=pdacService.getMesItemLocattions(locations, orgId);
//		String locFlag=(String) locMap.get(MesConstants.RESULT); 
//		//如果出错，则直接返回
//		if(MesConstants.ERROR.equals(locFlag)){
//			retVal=(String[]) packMap.get(MesConstants.RETVAL);
//			return retVal;
//		} 
		//兰继明2017.03.13更新,校验包装箱是否有对应的入库单，防止没有入库单的包装入库
		//check_pda_pack_barcode
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("packBarcode", packBarcode);
		paramMap.put("locations", locations);

		paramMap.put(MesConstants.DBRETURN, dbreturn);
		cm.callCheckCanInStock(paramMap);

		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
			retVal[0] = dbreturn.getxStatus();
			retVal[1] = dbreturn.getxMessage();
			
			return retVal;
		}
		/**
		 * 获取结果
		 */
		WoInStorage ct = cm.completeScanLocations(mph
				.getPackingBarcodeId());
		retVal[0] = MesConstants.SUCCESS;
		retVal[1] = ct.getWorkNo();
		retVal[2] = ct.getLotNo();
		retVal[3] = ct.getNum();
		return retVal;
	}

	/**
	 * @author sjf
	 * @date 2016年12月20日
	 * @param jsonString
	 * @return PDA工单完工入库-提交
	 */
	public String[] completeCommit(String jsonString) {
		// 返回值
		String[] retVal = new String[2];
		if (StringUtils.isEmpty(jsonString)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "数据异常";
			return retVal;
		}
		// 接收转换json数据
		WoInStorage initWis = JsonObjectConverTools.jsonToObject(jsonString,
				WoInStorage.class);
		// 登录用户
		String loginName = initWis.getUserid();
		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
		if (user == null) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录用户不存在，请联系管理员";
			return retVal;
		}
		// 组织ID
		String orgId = initWis.getWarehouse();
		if (StringUtils.isEmpty(orgId)) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "登录组织不存在，请联系管理员";
			return retVal;
		}
		List<WoInStorage> wList = initWis.getWoInStorage();
		//取得唯一键
		BigDecimal processId = comm.getSeqByName("mes_transactions_process_s");
		// 循环信息
		for (WoInStorage wis : wList) {
			/*
			 * 获取包装箱信息
			 */
			Map<String, Object> packMap=new HashMap<String, Object>();
			packMap=pdacService.getPackingHeaders(wis.getPackNo(), orgId);
			String packFlag=(String) packMap.get(MesConstants.RESULT);
			MesPackingHeaders mph = new MesPackingHeaders();
			//如果出错，则直接返回
			if(MesConstants.ERROR.equals(packFlag)){
				retVal=(String[]) packMap.get(MesConstants.RETVAL);
				return retVal;
			}else{
				mph=(MesPackingHeaders) packMap.get("mph");
			}
			/*
			 * 获取货位信息
			 */
			Map<String, Object> locMap=new HashMap<String, Object>();
			locMap=pdacService.getMesItemLocattions(wis.getWareLoca(), orgId);
			String locFlag=(String) locMap.get(MesConstants.RESULT); 
			//如果出错，则直接返回
			if(MesConstants.ERROR.equals(locFlag)){
				retVal=(String[]) locMap.get(MesConstants.RETVAL);
				return retVal;
			} 
			/**
			 * 数据提交
			 */
			DbReturnParameter dbreturn = new DbReturnParameter();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", user.getUserId());
			paramMap.put("processId", processId);
			paramMap.put("packingBarcodeId", mph.getPackingBarcodeId());
			paramMap.put("locator", wis.getWareLoca());
			paramMap.put(MesConstants.DBRETURN, dbreturn);
			cm.callInvInsertTransactionTemp(paramMap);

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
		 * 更新产品条码
		 */
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("processId", processId);
		paramMap.put(MesConstants.TYPE, "S_INV");
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
		 * 完工入库
		 */
		cm.callWipCompleteTransaction(paramMap);

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
