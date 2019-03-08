package com.egdfrm.unit.excelmodel;

import java.io.Serializable;

/**  
 * @author hgb
 * @date 2017-5-5
 */
@SuppressWarnings("serial")
public class StockInExcel implements Serializable { 
	private String plan_line;
	private String inv_number;
	private String inspect_number;
	private String barcode_text;
	private String wip_entity_name;
	private String mo_order;
	private String segment1;
	private String segment2;
	private String subinventory_code;
	private String locattion_code;
	private Integer pack_quantity;
	private String status_code;
	private String description;
	public String getPlan_line() {
		return plan_line;
	}
	public void setPlan_line(String plan_line) {
		this.plan_line = plan_line;
	}
	public String getInv_number() {
		return inv_number;
	}
	public void setInv_number(String inv_number) {
		this.inv_number = inv_number;
	}
	public String getInspect_number() {
		return inspect_number;
	}
	public void setInspect_number(String inspect_number) {
		this.inspect_number = inspect_number;
	}
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
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public String getSegment2() {
		return segment2;
	}
	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}
	public String getSubinventory_code() {
		return subinventory_code;
	}
	public void setSubinventory_code(String subinventory_code) {
		this.subinventory_code = subinventory_code;
	}
	public Integer getPack_quantity() {
		return pack_quantity;
	}
	public void setPack_quantity(Integer pack_quantity) {
		this.pack_quantity = pack_quantity;
	}
	public String getLocattion_code() {
		return locattion_code;
	}
	public void setLocattion_code(String locattion_code) {
		this.locattion_code = locattion_code;
	}
	public String getStatus_code() {
		return status_code;
	}
	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
