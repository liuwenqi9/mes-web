package com.egdfrm.unit.model.report;

/**
 * 生产统计日报表
 * @author hgb
 * @date 2017-4-20
 */
public class DailyStatistics {

    private String plan_line_code;//生产线
    private String wip_entity_name;//工单号
    private String segment1;//物料编码
    private String segment2;//型号
    private String mo_order;//MO单
    private Integer start_quantity;//工单数量
    private Integer transaction_quantity;//生产数量
    private Integer check_quantity;//工单数量
    private Integer inv_quantity;//生产数量
    private String description;//描述
	public String getPlan_line_code() {
		return plan_line_code;
	}
	public void setPlan_line_code(String plan_line_code) {
		this.plan_line_code = plan_line_code;
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
	public String getMo_order() {
		return mo_order;
	}
	public void setMo_order(String mo_order) {
		this.mo_order = mo_order;
	}
	public Integer getStart_quantity() {
		return start_quantity;
	}
	public void setStart_quantity(Integer start_quantity) {
		this.start_quantity = start_quantity;
	}
	public Integer getTransaction_quantity() {
		return transaction_quantity;
	}
	public void setTransaction_quantity(Integer transaction_quantity) {
		this.transaction_quantity = transaction_quantity;
	}
	public Integer getCheck_quantity() {
		return check_quantity;
	}
	public void setCheck_quantity(Integer check_quantity) {
		this.check_quantity = check_quantity;
	}
	public Integer getInv_quantity() {
		return inv_quantity;
	}
	public void setInv_quantity(Integer inv_quantity) {
		this.inv_quantity = inv_quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
