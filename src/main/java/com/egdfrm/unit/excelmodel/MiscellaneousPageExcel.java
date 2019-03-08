package com.egdfrm.unit.excelmodel;

import java.io.Serializable;
/**
 * 杂项交易导出模板
 * @author hgb
 * @date 2017-6-7
 */
@SuppressWarnings("serial")
public class MiscellaneousPageExcel implements Serializable{
	
	private String barcode_text;//条码
    private String item_no;//物料
    private String transaction_date;//使用时间
    private String transaction_quantity;//数量
    private String description;//账户别名类型
    private String segment1;//来源 
	public String getBarcode_text() {
		return barcode_text;
	}
	public void setBarcode_text(String barcode_text) {
		this.barcode_text = barcode_text;
	}
	public String getItem_no() {
		return item_no;
	}
	public void setItem_no(String item_no) {
		this.item_no = item_no;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public String getTransaction_quantity() {
		return transaction_quantity;
	}
	public void setTransaction_quantity(String transaction_quantity) {
		this.transaction_quantity = transaction_quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
    

}
