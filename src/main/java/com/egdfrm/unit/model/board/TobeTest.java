package com.egdfrm.unit.model.board;

/**
 * 待检验实体
 * Created by tyq on 17/1/17.
 */
public class TobeTest {

	private Integer wip_count;//工单张数
	
	
	private String plan_line;//生产线
	private String inspect_number;
	private String wip_entity_name;
	private String segment1;
	private String inspect_date;//报检时间 
	private String segment2;
    private Integer pack_count;//总箱数
    private Integer pack_quantity;//总数量
	public Integer getWip_count() {
		return wip_count;
	}
	public void setWip_count(Integer wip_count) {
		this.wip_count = wip_count;
	}
	
	public String getSegment2() {
		return segment2;
	}
	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}
	public String getPlan_line() {
		return plan_line;
	}
	public void setPlan_line(String plan_line) {
		this.plan_line = plan_line;
	}
	public String getInspect_number() {
		return inspect_number;
	}
	public void setInspect_number(String inspect_number) {
		this.inspect_number = inspect_number;
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
	public String getInspect_date() {
		return inspect_date;
	}
	public void setInspect_date(String inspect_date) {
		this.inspect_date = inspect_date;
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
	
    
	 
    
}
