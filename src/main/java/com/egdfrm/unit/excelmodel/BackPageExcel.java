package com.egdfrm.unit.excelmodel;

import java.io.Serializable;
/**
 * 退货货信息excel 模板
 * @author hgb
 * @date 2017-6-7
 */
@SuppressWarnings("serial")
public class BackPageExcel implements Serializable{

	private String barcode_text; //条码
	private String segment1; //物料编码
    private String transaction_date;//退货时间
    private Integer transaction_quantity;//退货数量
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
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public Integer getTransaction_quantity() {
		return transaction_quantity;
	}
	public void setTransaction_quantity(Integer transaction_quantity) {
		this.transaction_quantity = transaction_quantity;
	}
	 
    
}
