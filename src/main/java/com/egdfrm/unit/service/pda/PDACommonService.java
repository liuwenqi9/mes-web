package com.egdfrm.unit.service.pda;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.extend.common.DbReturnParameter;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.mapper.expand.CommonMapper;
import com.egdfrm.unit.mapper.expand.pda.CompleteMapper;
import com.egdfrm.unit.mapper.expand.pda.PDACommonMapper;
import com.egdfrm.unit.mapper.standard.MesItemLocattionsMapper;
import com.egdfrm.unit.mapper.standard.MesPackingHeadersMapper;
import com.egdfrm.unit.mapper.standard.MesWipBarcodesMapper;
import com.egdfrm.unit.mapper.standard.MtlSystemItemsBMapper;
import com.egdfrm.unit.mapper.standard.MtlTxnRequestHeadersMapper;
import com.egdfrm.unit.model.standard.MesItemLocattions;
import com.egdfrm.unit.model.standard.MesItemLocattionsCriteria;
import com.egdfrm.unit.model.standard.MesPackingHeaders;
import com.egdfrm.unit.model.standard.MesPackingHeadersCriteria;
import com.egdfrm.unit.model.standard.MesWipBarcodes;
import com.egdfrm.unit.model.standard.MesWipBarcodesCriteria;
import com.egdfrm.unit.model.standard.MtlSystemItemsB;
import com.egdfrm.unit.model.standard.MtlSystemItemsBCriteria;
import com.egdfrm.unit.model.standard.MtlTxnRequestHeaders;
import com.egdfrm.unit.model.standard.MtlTxnRequestHeadersCriteria;
/**
 * @author sjf
 * @date 2016年12月15日 pda共通SERVICE
 **/
@Service
public class PDACommonService {
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
	 * Field mwbm: 产品条码表dao
	 * </p>
	 */
	@Autowired
	private MesWipBarcodesMapper mwbm;
	/**
	 * <p>
	 * Field msm: 物料信息dao
	 * </p>
	 */
	@Autowired
	private MtlSystemItemsBMapper msm;

	/**
	 * <p>
	 * Field mrhm: 挑库dao
	 * </p>
	 */
	@Autowired
	private MtlTxnRequestHeadersMapper mrhm;

	/**
	 * @author sjf
	 * @date 2016年12月21日
	 * @param packBarcode
	 * @return 根据包装条码获取包装箱信息
	 */
	public Map<String, Object> getPackingHeaders(String packBarcode,
			String orgId) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		String[] retVal = new String[2];
		MesPackingHeadersCriteria mphc = new MesPackingHeadersCriteria();
		mphc.createCriteria().andBarcodeTextEqualTo(packBarcode);
		List<MesPackingHeaders> mphList = mphm.selectByExample(mphc);
		MesPackingHeaders mph = new MesPackingHeaders();
		if (mphList.isEmpty()) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "包装箱不存在";
			retMap.put(MesConstants.RESULT, MesConstants.ERROR);
			retMap.put(MesConstants.RETVAL, retVal);
			return retMap;
		} else {
			mph = mphList.get(0);
			if (!mph.getOrganizationId().toString().equals(orgId)) {
				retVal[0] = MesConstants.ERROR;
				retVal[1] = "登录组织与条码组织不一致！";
				retMap.put(MesConstants.RESULT, MesConstants.ERROR);
				retMap.put(MesConstants.RETVAL, retVal);
				return retMap;
			}
			retMap.put(MesConstants.RESULT, MesConstants.SUCCESS);
			retMap.put("mph", mph);
			return retMap;
		}
	}

	/**
	 * @author sjf
	 * @date 2016年12月21日
	 * @param wipBarcode
	 * @return 根据产品条码获取产品信息
	 */
	public Map<String, Object> getWipBarcodes(String wipBarcode, String orgId) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		String[] retVal = new String[2];
		MesWipBarcodesCriteria mwbc = new MesWipBarcodesCriteria();
		mwbc.createCriteria().andBarcodeTextEqualTo(wipBarcode);
		List<MesWipBarcodes> mwbList = mwbm.selectByExample(mwbc);
		MesWipBarcodes mwb = new MesWipBarcodes();
		if (mwbList.isEmpty()) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "条码不存在";
			retMap.put(MesConstants.RESULT, MesConstants.ERROR);
			retMap.put(MesConstants.RETVAL, retVal);
			return retMap;
		} else {
			mwb = mwbList.get(0);
			if (!mwb.getOrganizationId().toString().equals(orgId)) {
				retVal[0] = MesConstants.ERROR;
				retVal[1] = "登录组织与条码组织不一致！";
				retMap.put(MesConstants.RESULT, MesConstants.ERROR);
				retMap.put(MesConstants.RETVAL, retVal);
				return retMap;
			}
			retMap.put(MesConstants.RESULT, MesConstants.SUCCESS);
			retMap.put("mwb", mwb);
			return retMap;
		}
	}
	/**
	 * @author sjf
	 * @date 2016年12月21日
	 * @param locations
	 * @return 根据货位号校验货位有效性
	 */
	public Map<String, Object> getMesItemLocattions(String locations,
			String orgId) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		String[] retVal = new String[2];
		MesItemLocattionsCriteria milc = new MesItemLocattionsCriteria();
		milc.createCriteria().andLocattionCodeEqualTo(locations)
				.andOrganizationIdEqualTo(new BigDecimal(orgId));
		List<MesItemLocattions> milcList = milm.selectByExample(milc);
		MesItemLocattions mil = new MesItemLocattions();
		if (milcList.isEmpty()) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "货位不存在";
			retMap.put(MesConstants.RESULT, MesConstants.ERROR);
			retMap.put(MesConstants.RETVAL, retVal);
			return retMap;
		} else {
			mil = milcList.get(0);
		}
		if (mil.getDisableDate() != null
				&& mil.getDisableDate().before(new Date())) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "货位已失效";
			retMap.put(MesConstants.RESULT, MesConstants.ERROR);
			retMap.put(MesConstants.RETVAL, retVal);
			return retMap;
		}

		retMap.put(MesConstants.RESULT, MesConstants.SUCCESS);
		retMap.put("mil", mil);
		return retMap;
	}

	/**
	 * @author sjf
	 * @date 2016年12月22日
	 * @param locations
	 * @param orgId
	 * @return 验证挑库信息
	 */
	public Map<String, Object> getPickingInfo(String pickNumber, String orgId) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		String[] retVal = new String[2];
		/*
		 * 挑库信息
		 */
		MtlTxnRequestHeadersCriteria mrhc = new MtlTxnRequestHeadersCriteria();
		mrhc.createCriteria().andHeaderStatusEqualTo(new BigDecimal(7))
				.andOrganizationIdEqualTo(new BigDecimal(orgId))
				.andRequestNumberEqualTo(pickNumber);
		List<MtlTxnRequestHeaders> mrhList = mrhm.selectByExample(mrhc);
		MtlTxnRequestHeaders mrh = new MtlTxnRequestHeaders();
		if (mrhList.isEmpty()) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "挑库单号不可用";
			retMap.put(MesConstants.RESULT, MesConstants.ERROR);
			retMap.put(MesConstants.RETVAL, retVal);
			return retMap;
		} else {
			mrh = mrhList.get(0);
			if (!mrh.getOrganizationId().toString().equals(orgId)) {
				retVal[0] = MesConstants.ERROR;
				retVal[1] = "登录组织与挑库组织不一致！";
				retMap.put(MesConstants.RESULT, MesConstants.ERROR);
				retMap.put(MesConstants.RETVAL, retVal);
				return retMap;
			}
			retMap.put(MesConstants.RESULT, MesConstants.SUCCESS);
			retMap.put("mrh", mrh);
			return retMap;
		}
	}

	/**
	 * @author sjf
	 * @date 2016年12月26日
	 * @param barCode
	 * @param orgId
	 * @return 获取包装信息/产品信息
	 */
	public Map<String, Object> getPackOrWip(String barCode, String orgId) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		// 判断条码是包装条码还是产品条码 包装条码S/B 产品条码C/H
		//2017.02.22，码规则改变，有以数字开头的产品条码
		if (barCode.charAt(0) == MesConstants.SMALL_PACK
				|| barCode.charAt(0) == MesConstants.BIG_PACK) {
			/*
			 * 获取包装箱信息
			 */
			Map<String, Object> packMap = new HashMap<String, Object>();
			packMap = getPackingHeaders(barCode, orgId);
			String packFlag = (String) packMap.get(MesConstants.RESULT);
			MesPackingHeaders mph = new MesPackingHeaders();
			// 如果出错，则直接返回
			if (MesConstants.ERROR.equals(packFlag)) {
				return packMap;
			} else {
				mph = (MesPackingHeaders) packMap.get("mph");
				retMap.put(MesConstants.RESULT, MesConstants.SUCCESS);
				retMap.put(MesConstants.TYPE, MesConstants.PACK);
				retMap.put(MesConstants.OBJECT, mph);
				return retMap;
			}
		} 
		else if (barCode.charAt(0) == MesConstants.SHIP_PACK) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "无法操作发运包装";
			retMap.put(MesConstants.RESULT, MesConstants.ERROR);
			retMap.put(MesConstants.RETVAL, retVal);
			return retMap;
			//2017.02.22，码规则改变，有以数字开头的产品条码
//		}else if (barCode.charAt(0) == MesConstants.NX_PRODUCT
//				|| barCode.charAt(0) == MesConstants.WX_PRODUCT) {
		}else if (true) {
			/*
			 * 获取产品信息
			 */
			Map<String, Object> wipMap = new HashMap<String, Object>();
			wipMap = getWipBarcodes(barCode, orgId);
			String wipFlag = (String) wipMap.get(MesConstants.RESULT);
			MesWipBarcodes mwb = new MesWipBarcodes();
			// 如果出错，则直接返回
			if (MesConstants.ERROR.equals(wipFlag)) {
				return wipMap;
			} else {
				mwb = (MesWipBarcodes) wipMap.get("mwb");
				/*
				 * 外销产品校验
				 */
				DbReturnParameter dbreturn = new DbReturnParameter();
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("barcodeId", mwb.getWipBarcodeId());
				paramMap.put(MesConstants.DBRETURN, dbreturn);
				pdam.callCheckWxBarcode(paramMap);
				// 返回错误信息
				if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
					String[] retVal = new String[2];
					retVal[0] = dbreturn.getxStatus();
					retVal[1] = dbreturn.getxMessage();
					retMap.put(MesConstants.RESULT, MesConstants.ERROR);
					retMap.put(MesConstants.RETVAL, retVal);
					return retMap;
				}
				retMap.put(MesConstants.RESULT, MesConstants.SUCCESS);
				retMap.put(MesConstants.TYPE, MesConstants.WIP);
				retMap.put(MesConstants.OBJECT, mwb);
				return retMap;
			}
		} 
		String[] retVal = new String[2];
		retVal[0] = MesConstants.ERROR;
		retVal[1] = "条码信息异常！";
		retMap.put(MesConstants.RESULT, MesConstants.ERROR);
		retMap.put(MesConstants.RETVAL, retVal);
		return retMap;
	}
	/**
	 * @author sjf
	 * @date 2017年1月10日 
	 * @param barCode
	 * @param orgId
	 * @return
	 *  获取包装信息/产品信息 （不校验外销产品）
	 */
	public Map<String, Object> getPackOrWipNoCheckWx(String barCode, String orgId) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		// 判断条码是包装条码还是产品条码 包装条码S/B 产品条码C/H
		if (barCode.charAt(0) == MesConstants.SMALL_PACK
				|| barCode.charAt(0) == MesConstants.BIG_PACK) {
			/*
			 * 获取包装箱信息
			 */
			Map<String, Object> packMap = new HashMap<String, Object>();
			packMap = getPackingHeaders(barCode, orgId);
			String packFlag = (String) packMap.get(MesConstants.RESULT);
			MesPackingHeaders mph = new MesPackingHeaders();
			// 如果出错，则直接返回
			if (MesConstants.ERROR.equals(packFlag)) {
				return packMap;
			} else {
				mph = (MesPackingHeaders) packMap.get("mph");
				retMap.put(MesConstants.RESULT, MesConstants.SUCCESS);
				retMap.put(MesConstants.TYPE, MesConstants.PACK);
				retMap.put(MesConstants.OBJECT, mph);
				return retMap;
			}
		}else if (barCode.charAt(0) == MesConstants.SHIP_PACK) {
			String[] retVal = new String[2];
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "无法操作发运包装";
			retMap.put(MesConstants.RESULT, MesConstants.ERROR);
			retMap.put(MesConstants.RETVAL, retVal);
			return retMap;
			//2017.02.22，码规则改变，有以数字开头的产品条码
//		}else if (barCode.charAt(0) == MesConstants.NX_PRODUCT
//				|| barCode.charAt(0) == MesConstants.WX_PRODUCT) {
		}else if (true) {
			/*
			 * 获取产品信息
			 */
			Map<String, Object> wipMap = new HashMap<String, Object>();
			wipMap = getWipBarcodes(barCode, orgId);
			String wipFlag = (String) wipMap.get(MesConstants.RESULT);
			MesWipBarcodes mwb = new MesWipBarcodes();
			// 如果出错，则直接返回
			if (MesConstants.ERROR.equals(wipFlag)) {
				return wipMap;
			} else {
				mwb = (MesWipBarcodes) wipMap.get("mwb");
				retMap.put(MesConstants.RESULT, MesConstants.SUCCESS);
				retMap.put(MesConstants.TYPE, MesConstants.WIP);
				retMap.put(MesConstants.OBJECT, mwb);
				return retMap;
			}
		} 
		String[] retVal = new String[2];
		retVal[0] = MesConstants.ERROR;
		retVal[1] = "条码信息异常！";
		retMap.put(MesConstants.RESULT, MesConstants.ERROR);
		retMap.put(MesConstants.RETVAL, retVal);
		return retMap;
	}

	/**
	 * @author sjf
	 * @date 2016年12月27日 
	 * @param inventoryItemId
	 * @param orgId
	 * @return
	 * 根据物料ID获取物料信息
	 */
	public Map<String, Object> getInventoryItemInfo(BigDecimal inventoryItemId, BigDecimal orgId) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		String[] retVal = new String[2];
		MtlSystemItemsBCriteria msibc = new MtlSystemItemsBCriteria();
		msibc.createCriteria().andInventoryItemIdEqualTo(inventoryItemId).andOrganizationIdEqualTo(orgId);
		List<MtlSystemItemsB> msList = msm.selectByExample(msibc);
		MtlSystemItemsB ms = new MtlSystemItemsB();
		if (msList.isEmpty()) {
			retVal[0] = MesConstants.ERROR;
			retVal[1] = "物料不存在";
			retMap.put(MesConstants.RESULT, MesConstants.ERROR);
			retMap.put(MesConstants.RETVAL, retVal);
			return retMap;
		} else {
			ms = msList.get(0);
			retMap.put(MesConstants.RESULT, MesConstants.SUCCESS);
			retMap.put("ms", ms);
			retMap.put("msList", msList);
			//拼接物料号
			StringBuffer LotNos=new StringBuffer();
			for(MtlSystemItemsB msb:msList){
				LotNos.append(",");
				LotNos.append(msb.getSegment1());
			}
			LotNos.deleteCharAt(0);
			//拼接完后的字符串
			retMap.put("msLotNos", LotNos.toString() );
			return retMap;
		}
	}

}
