package com.egdfrm.unit.model.barcodeManagement;

/**
 * 入库单打印相关实体
 * Created by tyq on 17/1/13.
 */
public class StockInPrintRow {

    private String mo_order;//订单号
    private String wip_entity_name;//任务号
    private String plan_line;//线别
    private String segment2;//型号
    private String customer_type;//客户机型
    private String segment1;//物料编码
    private String start_quantity;//订单数量
    private String pack_quantity;//入库数量
    private String locattion_code;//建议货位

    public String getMo_order() {
        return mo_order;
    }

    public void setMo_order(String mo_order) {
        this.mo_order = mo_order;
    }

    public String getWip_entity_name() {
        return wip_entity_name;
    }

    public void setWip_entity_name(String wip_entity_name) {
        this.wip_entity_name = wip_entity_name;
    }

    public String getPlan_line() {
        return plan_line;
    }

    public void setPlan_line(String plan_line) {
        this.plan_line = plan_line;
    }

    public String getSegment2() {
        return segment2;
    }

    public void setSegment2(String segment2) {
        this.segment2 = segment2;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public String getSegment1() {
        return segment1;
    }

    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    public String getPack_quantity() {
        return pack_quantity;
    }

    public void setPack_quantity(String pack_quantity) {
        this.pack_quantity = pack_quantity;
    }

    public String getLocattion_code() {
        return locattion_code;
    }

    public void setLocattion_code(String locattion_code) {
        this.locattion_code = locattion_code;
    }

    public String getStart_quantity() {
        return start_quantity;
    }

    public void setStart_quantity(String start_quantity) {
        this.start_quantity = start_quantity;
    }

	@Override
	public String toString() {
		return "StockInPrintRow [mo_order=" + mo_order + ", wip_entity_name="
				+ wip_entity_name + ", plan_line=" + plan_line + ", segment2="
				+ segment2 + ", customer_type=" + customer_type + ", segment1="
				+ segment1 + ", start_quantity=" + start_quantity
				+ ", pack_quantity=" + pack_quantity + ", locattion_code="
				+ locattion_code + "]";
	}
    
}
