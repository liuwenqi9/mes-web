package com.egdfrm.unit.model.sale;

/**
 * 挑库实体
 * Created by tyq on 17/3/13.
 */
public class Picking {

    private String request_number;//挑库单
    private String segment1;//物料编码
    private Integer primary_quantity;//需求数量
    private Integer quantity_delivered;//ERP挑库数
    private Integer mes_pick_quantity; //条码挑库数
    private String creation_date; //ERP挑库时间
    private String prod_type;//产品型号
    private String source_header_number;//销售订单
    private String customer_name;//客户
	public String getRequest_number() {
		return request_number;
	}
	public void setRequest_number(String request_number) {
		this.request_number = request_number;
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public Integer getPrimary_quantity() {
		return primary_quantity;
	}
	public void setPrimary_quantity(Integer primary_quantity) {
		this.primary_quantity = primary_quantity;
	}
	public Integer getQuantity_delivered() {
		return quantity_delivered;
	}
	public void setQuantity_delivered(Integer quantity_delivered) {
		this.quantity_delivered = quantity_delivered;
	}
	public Integer getMes_pick_quantity() {
		return mes_pick_quantity;
	}
	public void setMes_pick_quantity(Integer mes_pick_quantity) {
		this.mes_pick_quantity = mes_pick_quantity;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public String getProd_type() {
		return prod_type;
	}
	public void setProd_type(String prod_type) {
		this.prod_type = prod_type;
	}
	public String getSource_header_number() {
		return source_header_number;
	}
	public void setSource_header_number(String source_header_number) {
		this.source_header_number = source_header_number;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	
    
    

}
