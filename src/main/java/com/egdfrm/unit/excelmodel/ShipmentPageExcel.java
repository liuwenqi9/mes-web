package com.egdfrm.unit.excelmodel;

import java.io.Serializable;
/**
 * 出货信息 模板
 * @author hgb
 * @date 2017-6-7
 */
@SuppressWarnings("serial")
public class ShipmentPageExcel implements Serializable{
	
	private String barcode_text;//条码
    private String segment1;//物料编码
    private String source_header_number;//销售订单
    private String transaction_date;//出售时间
    private String quantity;//出售数量
    private String customer_name;//客户
    private String area;//区域
    private String request_number;//搬运单号
	public String getBarcode_text() {
		return barcode_text;
	}
	public void setBarcode_text(String barcode_text) {
		this.barcode_text = barcode_text;
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public String getSource_header_number() {
		return source_header_number;
	}
	public void setSource_header_number(String source_header_number) {
		this.source_header_number = source_header_number;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getRequest_number() {
		return request_number;
	}
	public void setRequest_number(String request_number) {
		this.request_number = request_number;
	}
    

}
