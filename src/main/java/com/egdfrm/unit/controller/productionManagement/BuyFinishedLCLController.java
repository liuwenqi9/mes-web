package com.egdfrm.unit.controller.productionManagement;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.egdfrm.core.mapper.standard.TtApplicationUserMapper;
import com.egdfrm.core.model.common.Json;
import com.egdfrm.core.model.standard.TtApplicationUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.core.exception.CommonException;
import com.egdfrm.core.exception.CommonExceptionType;
import com.egdfrm.core.exception.MesThrowException;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm.ShiroUser;
import com.egdfrm.extend.common.JsonObjectConverTools;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.mapper.standard.MesPackingHeadersMapper;
import com.egdfrm.unit.model.expand.pda.BorrowProduct;
import com.egdfrm.unit.model.expand.pda.SecondaryPack;
import com.egdfrm.unit.model.standard.MesPackingHeaders;
import com.egdfrm.unit.model.standard.MesPackingHeadersCriteria;
import com.egdfrm.unit.service.productionManagement.BuyFinishedLCLService;

/**
 * @author sjf
 * @date 2017年2月15日 
 * 采购成品拼箱
 */
@Controller
@RequestMapping("buyFinishedLCLController")
public class BuyFinishedLCLController extends BaseController {

	@Autowired
	public BuyFinishedLCLService buyFinishedLCLService;

	/**
	 * <p>
	 * Field mphm: 包装箱头表dao
	 * </p>
	 */
	@Autowired
	public MesPackingHeadersMapper mphm;
	
	/*
	 * 采购成品拼箱主页
	 */
	@RequestMapping("init")
	public String init(){
		return "unit/productionManagement/buyFinishedLCL";
	}

	/**
	 * 扫描箱号
	 * 查询次包装箱还能装多少
	 * 此方法跟PDA的外购拼箱共用，修改时请注意！
	 * @param packageBarcode 包装箱号
	 * @return
	 */
	@RequestMapping("scanPackageBarcode")
	@ResponseBody
	public String scanPackageBarcode(String packageBarcode){
		Json json = new Json();
		try {
			if(StringUtils.isEmpty(packageBarcode)){
				json.setMessage("包装箱号不能为空");
				return JSON.toJSONString(json);
			}
			String count = buyFinishedLCLService.queryPackageByCount(packageBarcode);
			if(StringUtils.isEmpty(count)){
				json.setMessage("此包装箱不存在");
				return JSON.toJSONString(json);
			}
			json.setSuccess(true);
			json.setMessage(count);
		}catch (Exception e){
			e.printStackTrace();
		}
		return JSON.toJSONString(json);
	}
	/**
	 * 扫描产品条码
	 * 此方法跟PDA的外购拼箱共用，修改时请注意！
	 * @param packageBarcode 箱号
	 * @param wipBarcode 产品条码
	 * @return
	 */
	@RequestMapping("scanWipBarcode")
	@ResponseBody
	public Map<String,Object> scanWipBarcode(String packageBarcode,String wipBarcode){
		//传入参数不能为空
		if(packageBarcode.isEmpty()||wipBarcode.isEmpty()){
			//传入参数不能为空！
			throw new MesThrowException(CommonExceptionType.PARAMETER_NOT_NULL);
		}
		MesPackingHeadersCriteria mphc = new MesPackingHeadersCriteria();
		mphc.createCriteria().andBarcodeTextEqualTo(packageBarcode);
		List<MesPackingHeaders> mphList = mphm.selectByExample(mphc);
		if (mphList.isEmpty()) {
			//包装箱不存在
			throw new MesThrowException(CommonExceptionType.PACKAGE_NOT_EXISTS);
		}
		List<Map<String,Object>> buyFinishedLCLList=buyFinishedLCLService.getWipBarcode(packageBarcode,wipBarcode);
		if(buyFinishedLCLList.isEmpty()){
			//此条码已存在，无法包装
			throw new MesThrowException(CommonExceptionType.BARCODE_EXISTS);
		}
		if (wipBarcode.charAt(0) == MesConstants.SMALL_PACK
				|| wipBarcode.charAt(0) == MesConstants.BIG_PACK||wipBarcode.charAt(0) == MesConstants.SHIP_PACK
				||wipBarcode.charAt(0)==MesConstants.NX_PRODUCT||wipBarcode.charAt(0)==MesConstants.WX_PRODUCT) {
			//此条码命名规则与系统冲突//外购机产品条码命名规则不能与本条码系统有冲突，不能以C,H,P,B,S开头
			throw new CommonException(MesConstants.UNKNOWN,"此条码命名规则与系统冲突！");
			
		}
		Json json = new Json();
		return buyFinishedLCLList.get(0);
	}
	/*
	 * 提交
	 */
	@RequestMapping("commit")
	@ResponseBody
	public void commit(@CurrentLoginInfo ShiroUser su,String packageBarcodeIds,String wipBarcodes){
		//传入参数不能为空
		if(packageBarcodeIds.isEmpty()||wipBarcodes.isEmpty()){
			//传入参数不能为空！
			throw new MesThrowException(CommonExceptionType.PARAMETER_NOT_NULL);
		}
		Map<String, Object> paramMap = new HashMap();
		paramMap.put("userId", su.getUserId());
		paramMap.put("xStatus", "");
		paramMap.put("xMessage", "");
		String[] packageBarcodeIdss = packageBarcodeIds.split(",");
		String[] wipBarcodess = wipBarcodes.split(",");
		for(int i=0;i<packageBarcodeIdss.length;i++){
			String packageBarcodeId = packageBarcodeIdss[i];
			String wipBarcode = wipBarcodess[i]; 
			paramMap.put("packageBarcodeId", packageBarcodeId);
			paramMap.put("wipBarcode", wipBarcode);
			//提交
			buyFinishedLCLService.callCreatePoPack(paramMap);
			String status = String.valueOf(paramMap.get("xStatus"));
			String message = String.valueOf(paramMap.get("xMessage"));
			//报错则返回错误信息
			if ("E".equals(status)) {
				throw new CommonException(MesConstants.UNKNOWN,message);
			}
		}
	}
	/**
	 * <p>
	 * Field taum: 用户
	 * </p>
	 */
	@Autowired
	private TtApplicationUserMapper taum;
	/*
	 * 提交
	 * PDA的外购拼箱专用
	 * 共用二次包装的模型文件
	 */
	public void pda_commit(List<SecondaryPack> secondaryPacktList,TtApplicationUser user){
		
		Map<String, Object> paramMap = new HashMap();
		paramMap.put("userId", user.getUserId());
		paramMap.put("xStatus", "");
		paramMap.put("xMessage", "");
		
		for(SecondaryPack tempSecondaryPack:secondaryPacktList){
			MesPackingHeadersCriteria mphc = new MesPackingHeadersCriteria();
			mphc.createCriteria().andBarcodeTextEqualTo(tempSecondaryPack.getPackNo());
			List<MesPackingHeaders> mphList = mphm.selectByExample(mphc);
			BigDecimal packageBarcodeId = mphList.get(0).getPackingBarcodeId();
			String wipBarcode = tempSecondaryPack.getSnNo(); 
			paramMap.put("packageBarcodeId", packageBarcodeId);
			paramMap.put("wipBarcode", wipBarcode);
			//提交
			buyFinishedLCLService.callCreatePoPack(paramMap);
			String status = String.valueOf(paramMap.get("xStatus"));
			String message = String.valueOf(paramMap.get("xMessage"));
			//报错则返回错误信息
			if ("E".equals(status)) {
				throw new CommonException(MesConstants.UNKNOWN,message);
			}
		}
	}
}
