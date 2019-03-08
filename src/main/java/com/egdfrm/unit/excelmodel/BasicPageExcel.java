package com.egdfrm.unit.excelmodel;

import java.io.Serializable;

/**
 * 基本信息 excel 模板
 * @author hgb
 * @date 2017-6-7
 */
@SuppressWarnings("serial")
public class BasicPageExcel implements Serializable{
	
	private String barcode_text;//产品条码
    private String wip_entity_name;//工单号
    private String mo_order;//MO单号
    private String plan_line_code;//生产线
    private String segment1;//装配件
    private String description;//描述
    private String prod_tpye;//产品型号
    private String barcode_status;//状态
    private String wip_date;//生产时间
    private String inv_date;//入库时间
    private String inv_quantity;//入库数量
    private String start_quantity;//库存现有量
    private String subinventory_code;//子库
    private String locattion_code;//库存货位
	public String getBarcode_text() {
		return barcode_text;
	}
	public void setBarcode_text(String barcode_text) {
		this.barcode_text = barcode_text;
	}
	public String getWip_entity_name() {
		return wip_entity_name;
	}
	public void setWip_entity_name(String wip_entity_name) {
		this.wip_entity_name = wip_entity_name;
	}
	public String getMo_order() {
		return mo_order;
	}
	public void setMo_order(String mo_order) {
		this.mo_order = mo_order;
	}
	public String getPlan_line_code() {
		return plan_line_code;
	}
	public void setPlan_line_code(String plan_line_code) {
		this.plan_line_code = plan_line_code;
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProd_tpye() {
		return prod_tpye;
	}
	public void setProd_tpye(String prod_tpye) {
		this.prod_tpye = prod_tpye;
	}
	public String getBarcode_status() {
		return barcode_status;
	}
	public void setBarcode_status(String barcode_status) {
		this.barcode_status = barcode_status;
	}
	public String getWip_date() {
		return wip_date;
	}
	public void setWip_date(String wip_date) {
		this.wip_date = wip_date;
	}
	public String getInv_date() {
		return inv_date;
	}
	public void setInv_date(String inv_date) {
		this.inv_date = inv_date;
	}
	public String getInv_quantity() {
		return inv_quantity;
	}
	public void setInv_quantity(String inv_quantity) {
		this.inv_quantity = inv_quantity;
	}
	public String getStart_quantity() {
		return start_quantity;
	}
	public void setStart_quantity(String start_quantity) {
		this.start_quantity = start_quantity;
	}
	public String getSubinventory_code() {
		return subinventory_code;
	}
	public void setSubinventory_code(String subinventory_code) {
		this.subinventory_code = subinventory_code;
	}
	public String getLocattion_code() {
		return locattion_code;
	}
	public void setLocattion_code(String locattion_code) {
		this.locattion_code = locattion_code;
	}
    
    

}
