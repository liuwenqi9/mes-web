package com.egdfrm.unit.model.board;

/**
 * 待入库实体
 * Created by tyq on 17/1/17.
 */
public class Storage {

	private String wip_count;//工单数
	private String inv_date;//入库单日期 
	
	//以下为导出Excel模板类 对应的列
    private String plan_line;//生产线
    private String inv_number; 
    private String wip_entity_name;
    private String segment1;
    private String segment2;
    private Integer pack_count;//总箱数
    private Integer pack_quantity;//总数量
    private String customer_type;
    
	public String getWip_count() {
		return wip_count;
	}
	public void setWip_count(String wip_count) {
		this.wip_count = wip_count;
	}
	public String getInv_date() {
		return inv_date;
	}
	public void setInv_date(String inv_date) {
		this.inv_date = inv_date;
	}
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
	public String getWip_entity_name() {
		return wip_entity_name;
	}
	public void setWip_entity_name(String wip_entity_name) {
		this.wip_entity_name = wip_entity_name;
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
	public Integer getPack_count() {
		return pack_count;
	}
	public void setPack_count(Integer pack_count) {
		this.pack_count = pack_count;
	}
	public Integer getPack_quantity() {
		return pack_quantity;
	}
	public void setPack_quantity(Integer pack_quantity) {
		this.pack_quantity = pack_quantity;
	}
	public String getCustomer_type() {
		return customer_type;
	}
	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	} 
     
    
    
	 
    
    
}
