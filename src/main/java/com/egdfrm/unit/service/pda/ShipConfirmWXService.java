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
import com.egdfrm.unit.mapper.expand.pda.BorrowProductMapper;
import com.egdfrm.unit.mapper.expand.pda.PDACommonMapper;
import com.egdfrm.unit.mapper.expand.pda.PickingMapper;
import com.egdfrm.unit.mapper.expand.pda.ShipConfirmWXMapper;
import com.egdfrm.unit.mapper.standard.MesItemLocattionsMapper;
import com.egdfrm.unit.mapper.standard.MesPackingHeadersMapper;
import com.egdfrm.unit.mapper.standard.MesWipBarcodesMapper;
import com.egdfrm.unit.mapper.standard.MtlTxnRequestHeadersMapper;
import com.egdfrm.unit.model.expand.pda.BorrowProduct;
import com.egdfrm.unit.model.expand.pda.SaleOutStorage;
import com.egdfrm.unit.model.expand.pda.ShipConfirmWX;
import com.egdfrm.unit.model.standard.MesPackingHeaders;
import com.egdfrm.unit.model.standard.MesWipBarcodes;
import com.egdfrm.unit.model.standard.MesWipBarcodesCriteria;
import com.egdfrm.unit.model.standard.MtlTxnRequestHeaders;
/**
 * 外销发货SERVICE
 * @author 兰继明
 * @date 2017年04月28日 
 **/
@Service
public class ShipConfirmWXService {
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
	 * <p>
	 * Field  借机mapper
	 * </p>
	 */
	@Autowired
	private ShipConfirmWXMapper shipConfirmWXMapper;

	/**
	 * 外销发货--校验出货通知单
	 * @author 兰继明
	 * @date 2017年03月14日
	 * @param out_sn 出货通知单号
	 * @return 
	 */
	public String[] checkOutSn(String out_sn) {
		String[] returnValue = new String[5];
		// 登录用户
		// String loginName = input[0];
//		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
//		if (user == null) {
//			returnValue[0] = MesConstants.ERROR;
//			returnValue[1] = "登录用户不存在，请联系管理员";
//			return returnValue;
//		}
		
//		// 组织ID
//		String orgId = input[1];
//		if (StringUtils.isEmpty(orgId)) {
//			returnValue[0] = MesConstants.ERROR;
//			returnValue[1] = "登录组织为空，请联系管理员";
//			return returnValue;
//		}
		if (StringUtils.isEmpty(out_sn)) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "出货通知单号为空，请联系管理员";
			return returnValue;
		}
		/**
		 * 检查出货通知单号
		 */
		Map<String, Object> paramMap = new HashMap<String, Object>();
		DbReturnParameter dbreturn = new DbReturnParameter();
		paramMap = new HashMap<String, Object>();
		paramMap.put("out_sn", out_sn);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		shipConfirmWXMapper.callcheckOutSn(paramMap);
		//check不通过则报错
		if(!MesConstants.SUCCESS.equals(dbreturn.getxStatus())){
			returnValue[0] = dbreturn.getxStatus();
			returnValue[1] = dbreturn.getxMessage();
			return returnValue;
		}
		returnValue[0] = MesConstants.SUCCESS;
		returnValue[1] = "出货通知单号正确";
		return returnValue;
	}
	@Autowired
	private MesWipBarcodesMapper mesWipBarcodesMapper;
	/**
	 * 借机--检查产品条码
	 * @author 兰继明
	 * @date 2017年03月14日
	 * @param input
	 * @return 
	 */
	public String[] shipConfirmWXCheckPackNo(String[] input) {
		String[] returnValue = new String[5];
		// 登录用户
		// String loginName = input[0];
//		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
//		if (user == null) {
//			returnValue[0] = MesConstants.ERROR;
//			returnValue[1] = "登录用户不存在，请联系管理员";
//			return returnValue;
//		}
		
		//// 组织ID
//		String orgId = input[1];
//		if (StringUtils.isEmpty(orgId)) {
//			returnValue[0] = MesConstants.ERROR;
//			returnValue[1] = "登录组织为空，请联系管理员";
//			return returnValue;
//		}
		String out_sn=input[2];
		String pack_no=input[3];
		if (StringUtils.isEmpty(out_sn)) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "出货通知单号为空，请联系管理员";
			return returnValue;
		}
		if (StringUtils.isEmpty(pack_no)) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "包装箱号为空，请联系管理员";
			return returnValue;
		}
		
		/**
		 * 检查出货通知单和包装箱，及出货通知单和包装箱的对应关系是否正确
		 */
		Map<String, Object> paramMap = new HashMap<String, Object>();
		DbReturnParameter dbreturn = new DbReturnParameter();
		paramMap = new HashMap<String, Object>();
		paramMap.put("out_sn", out_sn);
		paramMap.put("pack_no", pack_no);
		Map<String, String> packInfo=shipConfirmWXMapper.getPackID(paramMap);
		paramMap.put("pack_id", packInfo.get("PACKING_BARCODE_ID"));
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		shipConfirmWXMapper.callCheckPackNo(paramMap);
		//check不通过则报错
		if(!MesConstants.SUCCESS.equals(dbreturn.getxStatus())){
			returnValue[0] = dbreturn.getxStatus();
			returnValue[1] = dbreturn.getxMessage();
			return returnValue;
		}
		// 获取物料信息
		Map<String, Object> paramMap2 = new HashMap<String, Object>();
		DbReturnParameter dbreturn2 = new DbReturnParameter();
		paramMap2 = new HashMap<String, Object>();
		paramMap2.put("pack_no", pack_no);
		paramMap2.put(MesConstants.DBRETURN, dbreturn2);
		Map<String, Object> result=shipConfirmWXMapper.getPackInfo(paramMap2);
		if (result == null) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "物料信息不存在";
			return returnValue;
		}
		returnValue[0] = MesConstants.SUCCESS;
//		returnValue[1] = "借机单和产品关系验证通过";
		//编码
		returnValue[1] = result.get("SEGMENT1").toString();
		//数量
		returnValue[2] = result.get("PACK_QUANTITY").toString();
		//型号
		returnValue[3] = result.get("PROD_TYPE").toString();
		
		return returnValue;
	}
	/**
	 * //外销发货--提交
	 * @author 兰继明
	 * @date 2017年04月28日
	 * @param input
	 * @return 
	 */
	public String[] commit(String input) {
		// 返回值
		String[] returnValue = new String[2];
		if (StringUtils.isEmpty(input)) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "传入数据异常";
			return returnValue;
		}
		// 接收转换json数据
		ShipConfirmWX shipConfirmWX = JsonObjectConverTools.jsonToObject(input,ShipConfirmWX.class);
		List<ShipConfirmWX> shipConfirmWXs = shipConfirmWX.getShipConfirmWX();

		// 登录用户
		String loginName = shipConfirmWX.getUserid();
		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
		if (user == null) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "登录用户不存在，请联系管理员";
			return returnValue;
		}
		// 组织ID
		String orgId = shipConfirmWX.getWarehouse();
		if (StringUtils.isEmpty(orgId)) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "登录组织不存在，请联系管理员";
			return returnValue;
		}
		// 取得唯一键
		BigDecimal processId = comm.getSeqByName("mes_transactions_s");
		DbReturnParameter dbreturn = new DbReturnParameter();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", user.getUserId());
		paramMap.put("processId", processId);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		// 循环挑库信息
		for (ShipConfirmWX tempShipConfirmWX : shipConfirmWXs) {
			/**
			 * 循环插入临时表
			 */
			//paramMap.put("organizationId", orgId);
			paramMap.put("out_sn", tempShipConfirmWX.getOut_sn());
			paramMap.put("pack_no", tempShipConfirmWX.getPack_no());
			Map<String, String> packInfo=shipConfirmWXMapper.getPackID(paramMap);
			paramMap.put("pack_id", packInfo.get("PACKING_BARCODE_ID"));
			shipConfirmWXMapper.callCommitInsertTempTable(paramMap);
			// 返回错误信息
			if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
				returnValue[0] = dbreturn.getxStatus();
				returnValue[1] = dbreturn.getxMessage();
				TransactionAspectSupport.currentTransactionStatus()
						.setRollbackOnly();
				return returnValue;
			}
		}
		/**
		 * 借机--提交--处理临时表，插入正式表
		 */
		shipConfirmWXMapper.callCommitProcesTempTable(paramMap);
		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
			returnValue[0] = dbreturn.getxStatus();
			returnValue[1] = dbreturn.getxMessage();
			return returnValue;
		}
		
		returnValue[0] = MesConstants.SUCCESS;
		returnValue[1] = "外销发货提交成功";
		return returnValue;
	}
}
