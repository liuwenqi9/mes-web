package com.egdfrm.unit.excelmodel;
 
/**
 * 待备货 excel
 * @author hgb
 * @date 2017-3-21
 */ 
public class StockupExcel{
	
	private String accountName;
	private Integer sourceHeaderNumber;
	private Integer requestNumber;
	private String segment1;
	private String prodType;
	private Integer quantity;
	private String transactionDate;
	private String description;
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Integer getSourceHeaderNumber() {
		return sourceHeaderNumber;
	}
	public void setSourceHeaderNumber(Integer sourceHeaderNumber) {
		this.sourceHeaderNumber = sourceHeaderNumber;
	}
	public Integer getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(Integer requestNumber) {
		this.requestNumber = requestNumber;
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	} 
	
}
