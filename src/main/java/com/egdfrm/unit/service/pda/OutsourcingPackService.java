package com.egdfrm.unit.service.pda;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.egdfrm.core.exception.CommonException;
import com.egdfrm.core.mapper.standard.TtApplicationUserMapper;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.model.standard.TtApplicationUser;
import com.egdfrm.extend.common.DbReturnParameter;
import com.egdfrm.extend.common.JsonObjectConverTools;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.controller.productionManagement.BuyFinishedLCLController;
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
import com.egdfrm.unit.model.expand.pda.SecondaryPack;
import com.egdfrm.unit.model.standard.MesPackingHeaders;
import com.egdfrm.unit.model.standard.MesWipBarcodes;
import com.egdfrm.unit.model.standard.MesWipBarcodesCriteria;
import com.egdfrm.unit.model.standard.MtlTxnRequestHeaders;
import com.egdfrm.unit.service.productionManagement.BuyFinishedLCLService;
/**
 * 外购机拼箱SERVICE
 * <br>
 * 因为网页版本的外购机拼箱已经做好。所以调用网页版本的外购机拼箱代码。
 * @author 兰继明
 * @date 2017年03月15日 
 **/
@Service
public class OutsourcingPackService {
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
	@Autowired
	public BuyFinishedLCLService buyFinishedLCLService;

	/**
	 * 网页版本的外购机拼箱控制器
	 * */
	
	public static BuyFinishedLCLController buyFinishedLCLController;
	
	public synchronized BuyFinishedLCLController getBuyFinishedLCLController() {
		if (buyFinishedLCLController==null) {
			buyFinishedLCLController=new BuyFinishedLCLController();
			buyFinishedLCLController.buyFinishedLCLService=this.buyFinishedLCLService;
			buyFinishedLCLController.mphm=this.mphm;
		}
		return buyFinishedLCLController;
		
	}

	/**
	 * //外购机拼箱--检查包装箱条码
	 * @author 兰继明
	 * @date 2017年03月15日
	 * @param input
	 * @return 
	 */
	public String[] outsourcingPackCheckPack(String[] input) {
		String[] returnValue = new String[10];
		String packageBarcode = input[2];
		if (StringUtils.isEmpty(packageBarcode)) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "包装箱号不能为空，请联系管理员";
			return returnValue;
		}
		String jsonResult=getBuyFinishedLCLController().scanPackageBarcode(packageBarcode);
		Json result = JsonObjectConverTools.jsonToObject(jsonResult,Json.class);
		if (!result.isSuccess()) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "包装箱号错误";
			return returnValue;
		}
		returnValue[0] = MesConstants.SUCCESS;
//		returnValue[1] = "包装箱号正确";
		returnValue[1] = result.getMessage();
		return returnValue;
	}
	@Autowired
	private MesWipBarcodesMapper mesWipBarcodesMapper;
	/**
	 * //外购机拼箱--检查产品条码
	 * @author 兰继明
	 * @date 2017年03月15日
	 * @param input
	 * @return 
	 */
	public String[] outsourcingPackCheckProduct(String[] input) {
		String[] returnValue = new String[6];
		String packageBarcode = input[2];
		if (StringUtils.isEmpty(packageBarcode)) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "包装箱号不能为空，请联系管理员";
			return returnValue;
		}
		String productBarcode = input[3];
		if (StringUtils.isEmpty(productBarcode)) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "产品条码号不能为空，请联系管理员";
			return returnValue;
		}
		Map<String,Object> result=new HashMap<String, Object>();
		try {
			result=getBuyFinishedLCLController().scanWipBarcode(packageBarcode, productBarcode);
		} catch (Exception e) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = e.getMessage();
			return returnValue;
		}
		returnValue[0] = MesConstants.SUCCESS;
		//		returnValue[1] = "包装箱号正确";
		returnValue[1] = (String) result.get("PROD_TYPE");
		returnValue[2] = (String) result.get("SEGMENT1");
		returnValue[3] = "1";
		returnValue[4] = (String) result.get("WIP_ENTITY_NAME");
		returnValue[5] = (String) result.get("DESCRIPTION");
		return returnValue;
	}
	/**
	 * //外购机拼箱--提交
	 * @author 兰继明
	 * @date 2017年03月15日
	 * @param input
	 * @return 
	 */
	public String[] comoutsourcingPackCommit(String input) {
		String[] returnValue = new String[6];
		if (StringUtils.isEmpty(input)) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "输入参数不能为空，请联系管理员";
			return returnValue;
		}
		// 接收转换json数据
		SecondaryPack secondaryPack = JsonObjectConverTools.jsonToObject(input,SecondaryPack.class);
		List<SecondaryPack> secondaryPacktList = secondaryPack.getSecondaryPack();
		//传入参数不能为空
		// 登录用户
		String loginName = secondaryPack.getUserid();
		TtApplicationUser user = this.taum.selectByPrimaryKey(loginName);
		if (user == null) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = "登录用户不存在，请联系管理员";
			return returnValue;
		}
		try {
			((BuyFinishedLCLController) getBuyFinishedLCLController()).pda_commit(secondaryPacktList,user);
		} catch (Exception e) {
			returnValue[0] = MesConstants.ERROR;
			returnValue[1] = e.getMessage();
			return returnValue;
		}
		returnValue[0] = MesConstants.SUCCESS;
		returnValue[1] = "拼包成功";
		return returnValue;
	}
}
