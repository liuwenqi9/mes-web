package com.egdfrm.unit.service.afterSaleManagement;

import java.util.List;
import java.util.Map;

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
public class AfterSaleOutService {
	
	@Autowired
	private AfterSaleBackMapper afterSaleBackMapper;
	
	
	/* 
	 * 售后出货 分页数据
	 */
	public Pagination getAfterSaleOutList(Pagination pagination,AfterSaleBack afterSaleBack) {
		if( afterSaleBack.getSTATUS()!=null && !"".equals(afterSaleBack.getSTATUS()) ){
			if("Y".equals(afterSaleBack.getSTATUS()))
				afterSaleBack.setFalg(1);
			else{ 
				afterSaleBack.setFalg(0);
			}
		}
        Long total = afterSaleBackMapper.getAfterSaleOutCount(afterSaleBack);
        List<AfterSaleBack> rows = afterSaleBackMapper.getAfterSaleOutList(pagination,afterSaleBack);
        pagination.setTotal(total);
        pagination.setRows(rows);
        return pagination;
    }
	
	/**
	 *  售后 出货保存
	 * @author	hgb
	 * @date 2017-6-29
	 */
	public Json updateToSave(AfterSaleBack afterSaleBack,String uid){
		Json json = new Json();
		json.setSuccess(true);
		json.setMessage("请求成功！"); 
		String[] id = afterSaleBack.getIDs().split(",");
		for (int i = 0; i < id.length; i++) {
			AfterSaleBack back = afterSaleBack;
			back.setID(Integer.parseInt(id[i]));
			int result = 0 ; 
			result = afterSaleBackMapper.upateToSave(back,uid);     
			if(result<0){
				json.setSuccess(false);
				json.setMessage("数据异常！");
				return json;
			}
		}
		return json;
	}
	/**
	 *  售后 出货确认
	 * @author	hgb
	 * @date 2017-6-29
	 */
	public Json upateToShip(AfterSaleBack afterSaleBack,String uid){
		Json json = new Json();
		json.setSuccess(true);
		json.setMessage("请求成功！"); 
		String[] id = afterSaleBack.getIDs().split(",");
		for (int i = 0; i < id.length; i++) {
			AfterSaleBack back = afterSaleBack;
			back.setID(Integer.parseInt(id[i]));
			int result = 0 ; 
			result = afterSaleBackMapper.upateToShip(back,uid);     
			if(result<0){
				json.setSuccess(false);
				json.setMessage("数据异常！");
				return json;
			}
		}
		return json;
	}
}
