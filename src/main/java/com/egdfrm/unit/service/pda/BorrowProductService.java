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
import com.egdfrm.unit.mapper.standard.MesItemLocattionsMapper;
import com.egdfrm.unit.mapper.standard.MesPackingHeadersMapper;
import com.egdfrm.unit.mapper.standard.MesWipBarcodesMapper;
import com.egdfrm.unit.mapper.standard.MtlTxnRequestHeadersMapper;
import com.egdfrm.unit.model.expand.pda.BorrowProduct;
import com.egdfrm.unit.model.expand.pda.SaleOutStorage;
import com.egdfrm.unit.model.standard.MesPackingHeaders;
import com.egdfrm.unit.model.standard.MesWipBarcodes;
import com.egdfrm.unit.model.standard.MesWipBarcodesCriteria;
import com.egdfrm.unit.model.standard.MtlTxnRequestHeaders;
/**
 * 借机SERVICE
 * @author 兰继明
 * @date 2017年03月14日 
 **/
@Service
public class BorrowProductService {
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
	private BorrowProductMapper borrowProductMapper;

	/**
	 * 借机--检查借机单号
	 * @author 兰继明
	 * @date 2017年03月14日
	 * @param input
	 * @return 
	 */
	public String[] CheckBorrowProductBill(String[] input) {
		String[] returnValue = new String[5];
		// 登录用户
		// String loginName = input[0];
//		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
//		if (user == null) {
//			returnValue[0] = MesConstants.ERROR;
//			returnValue[1] = "登录用户不存在，请联系管理员";
//			return returnValue;
//		}
		
		// 组织ID
		String orgId = input[1];
		if (StringUtils.isEmpty(orgId)) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "登录组织为空，请联系管理员";
			return returnValue;
		}
		// 借机单号
		String lendNumber = input[2];
		if (StringUtils.isEmpty(lendNumber)) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "借机单号为空，请联系管理员";
			return returnValue;
		}
		/**
		 * 检查借机单号
		 */
		Map<String, Object> paramMap = new HashMap<String, Object>();
		DbReturnParameter dbreturn = new DbReturnParameter();
		paramMap = new HashMap<String, Object>();
		paramMap.put("organizationId", orgId);
		paramMap.put("lendNumber", lendNumber);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		borrowProductMapper.callCheckBorrowProductBill(paramMap);
		//check不通过则报错
		if(!MesConstants.SUCCESS.equals(dbreturn.getxStatus())){
			returnValue[0] = dbreturn.getxStatus();
			returnValue[1] = dbreturn.getxMessage();
			return returnValue;
		}
		returnValue[0] = MesConstants.SUCCESS;
		returnValue[1] = "借机单号正确";
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
	public String[] CheckProduct(String[] input) {
		String[] returnValue = new String[5];
		// 登录用户
		// String loginName = input[0];
//		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
//		if (user == null) {
//			returnValue[0] = MesConstants.ERROR;
//			returnValue[1] = "登录用户不存在，请联系管理员";
//			return returnValue;
//		}
		
		// 组织ID
		String orgId = input[1];
		if (StringUtils.isEmpty(orgId)) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "登录组织为空，请联系管理员";
			return returnValue;
		}
		// 借机单号
		String lendNumber = input[2];
		if (StringUtils.isEmpty(lendNumber)) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "借机单号为空，请联系管理员";
			return returnValue;
		}
		// 产品条码
		String barcode = input[3];
		if (StringUtils.isEmpty(barcode)) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "产品条码为空，请联系管理员";
			return returnValue;
		}
		/**
		 * 检查借机单号和产品，及检查借机单号和产品的对应关系是否正确
		 */
		Map<String, Object> paramMap = new HashMap<String, Object>();
		DbReturnParameter dbreturn = new DbReturnParameter();
		paramMap = new HashMap<String, Object>();
		paramMap.put("organizationId", orgId);
		paramMap.put("lendNumber", lendNumber);
		paramMap.put("barcode", barcode);
		paramMap.put(MesConstants.DBRETURN, dbreturn);
		borrowProductMapper.callCheckProduct(paramMap);
		//check不通过则报错
		if(!MesConstants.SUCCESS.equals(dbreturn.getxStatus())){
			returnValue[0] = dbreturn.getxStatus();
			returnValue[1] = dbreturn.getxMessage();
			return returnValue;
		}
		// 获取物料信息
		MesWipBarcodesCriteria mesWipBarcodesCriteria = new MesWipBarcodesCriteria();
		mesWipBarcodesCriteria.createCriteria().andBarcodeTextEqualTo(barcode).andOrganizationIdEqualTo(new BigDecimal(Integer.valueOf(orgId)));
		List<MesWipBarcodes> barcodeInfoList = mesWipBarcodesMapper.selectByExample(mesWipBarcodesCriteria);
		BigDecimal invId=barcodeInfoList.get(0).getPrimaryItemId();
		Map<String, Object> paramMap2 = new HashMap<String, Object>();
		paramMap2.put("orgId", new BigDecimal(orgId));
		paramMap2.put("invId", invId);
		SaleOutStorage sos = pim.getNum(paramMap2);
		if (sos == null) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "物料信息不存在";
			return returnValue;
		}
		returnValue[0] = MesConstants.SUCCESS;
//		returnValue[1] = "借机单和产品关系验证通过";
		returnValue[1] = "1";
		returnValue[2] = sos.getLotNo();
		returnValue[3] = sos.getStyle();
		returnValue[4] = sos.getDesr();
		
		return returnValue;
	}
	/**
	 * //借机--提交
	 * @author 兰继明
	 * @date 2017年03月14日
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
		BorrowProduct borrowProduct = JsonObjectConverTools.jsonToObject(input,BorrowProduct.class);
		List<BorrowProduct> borrowProductList = borrowProduct.getBorrowProduct();

		// 登录用户
		String loginName = borrowProduct.getUserid();
		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
		if (user == null) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "登录用户不存在，请联系管理员";
			return returnValue;
		}
		// 组织ID
		String orgId = borrowProduct.getWarehouse();
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
		for (BorrowProduct tempBorrowProduct : borrowProductList) {
			/**
			 * 循环插入临时表
			 */
			paramMap.put("organizationId", orgId);
			paramMap.put("lendNumber", tempBorrowProduct.getOutNo());
			paramMap.put("barcode", tempBorrowProduct.getSnNo());
			paramMap.put("expressNumber", tempBorrowProduct.getExpressNumber());
			borrowProductMapper.callCommitInsertTempTable(paramMap);
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
		borrowProductMapper.callCommitProcesTempTable(paramMap);
		// 返回错误信息
		if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
			returnValue[0] = dbreturn.getxStatus();
			returnValue[1] = dbreturn.getxMessage();
			return returnValue;
		}
		
		returnValue[0] = MesConstants.SUCCESS;
		returnValue[1] = "借机提交成功";
		return returnValue;
	}
}
