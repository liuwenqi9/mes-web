package com.egdfrm.unit.service.afterSaleManagement;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.egdfrm.extend.common.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.core.model.common.Json;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.afterSaleMenagement.AfterSaleBackMapper;
import com.egdfrm.unit.model.AfterSaleManagement.AfterSaleBack;

/**
 *  售后退货
 * @author hgb
 * @date 2017-6-15
 */
@Service
public class AfterSaleBackService {
	
	@Autowired
	private AfterSaleBackMapper afterSaleBackMapper;
	
	
	public Pagination getCustomerInfo(Pagination pagination,String customer){
		Long total = afterSaleBackMapper.getCustomerInfoCount(customer);
        List<Map<String, Object>> rows = afterSaleBackMapper.getCustomerInfoList(pagination, customer);
        pagination.setTotal(total);
        pagination.setRows(rows);
        return pagination;
	}
	/*
	 * 验证是否存在
	 */
	public boolean isValid(String barcodeText){
		List<String> list = afterSaleBackMapper.isValid(barcodeText);  
		if(list!=null && !list.isEmpty()){
			return true;
		} 
		return false;
	}
	/*
	 * 根据条码 获取产品条码，物料编码，产品型号，产品描述，
	 */
	public Map<String, Object> getBarcodeInfoByBarcodeText(String barcodeText){
		return afterSaleBackMapper.getBarcodeInfoByBarcodeText(barcodeText).get(0);
	}
	
	/**
	 * 批量 添加
	 * @param afterSaleBack 
	 * @return   
	 * @author	hgb
	 * @date 2017-6-16
	 */
	public Json saveAfterSaleBarcode(AfterSaleBack afterSaleBack,String uid){
		Json json = new Json();
		json.setSuccess(true);
		json.setMessage("请求成功！");
		String[] BARCODEIDs = afterSaleBack.getBARCODEID().split(",");
		String[] MATERIAL_IDs = afterSaleBack.getMATERIAL_ID().split(",");
		String[] PRODUCT_BARCODEs =afterSaleBack.getPRODUCT_BARCODE().split(",");
		String[] SEGMENT1s  =afterSaleBack.getSEGMENT1().split(",");
		String[] MODELs = afterSaleBack.getMODEL().split(",");
		String[] DESCRIPTIONs = afterSaleBack.getDESCRIPTION().split(",");  
		for (int i = 0; i < BARCODEIDs.length; i++) { 
			AfterSaleBack back = afterSaleBack ;
			back.setBARCODEID(BARCODEIDs[i]);
			back.setMATERIAL_ID(MATERIAL_IDs[i]);
			back.setPRODUCT_BARCODE(PRODUCT_BARCODEs[i]);
			back.setSEGMENT1(SEGMENT1s[i]);
			back.setMODEL(MODELs[i]);
			back.setDESCRIPTION(DESCRIPTIONs[i]);    
			int result = 0 ; 
			result = afterSaleBackMapper.saveMoel(back,uid);     
			if(result<0){ 
				json.setSuccess(false);
				json.setMessage("数据异常！");
				return json;
			}
		}
		return json;
	}
	
}
