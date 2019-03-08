package com.egdfrm.unit.excelmodel;

import java.io.Serializable;
/**
 *  重量维护 excel模板  
 * @author hgb
 * @date 2017-4-17
 */
@SuppressWarnings("serial")
public class WeighMaintenanceExcel implements Serializable{

	private String wip_entity_name ;
	private String status_type ; 
	private String segment1 ;
	private String prod_type;
	private Integer b_quantity ;  //包装数量
	private Double s_gross_weight; //标准毛重
	private Double gross_weight ; //实际毛重
	private Double pack_weight;//外箱重量
	private String creation_date ;//维护时间
	private String last_update_date;//最后更新时间
	
	
	public Double getPack_weight() {
		return pack_weight;
	}
	public void setPack_weight(Double pack_weight) {
		this.pack_weight = pack_weight;
	}
	public String getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}
	public String getWip_entity_name() {
		return wip_entity_name;
	}
	public void setWip_entity_name(String wip_entity_name) {
		this.wip_entity_name = wip_entity_name;
	}
	public String getStatus_type() {
		return status_type;
	}
	public void setStatus_type(String status_type) {
		this.status_type = status_type;
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public String getProd_type() {
		return prod_type;
	}
	public void setProd_type(String prod_type) {
		this.prod_type = prod_type;
	}
	public Integer getB_quantity() {
		return b_quantity;
	}
	public void setB_quantity(Integer b_quantity) {
		this.b_quantity = b_quantity;
	}
	public Double getS_gross_weight() {
		return s_gross_weight;
	}
	public void setS_gross_weight(Double s_gross_weight) {
		this.s_gross_weight = s_gross_weight;
	}
	public Double getGross_weight() {
		return gross_weight;
	}
	public void setGross_weight(Double gross_weight) {
		this.gross_weight = gross_weight;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}

 
 
	 
}
