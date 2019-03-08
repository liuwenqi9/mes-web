package com.egdfrm.unit.excelmodel;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SaleShipExcel implements Serializable {
	private String requestNumber;//挑库单号
	private String segment1;
	private String prodType;
	private Integer primaryQuantity;//需求数
	private Integer barcodeShipQuantity;
	private Integer erpShipQuantity;//ERP发运数
	private String barcodeText;
	private String description;//条码状态
	private String sourceHeaderNumber;//销售订单
	private String transactionDate;
	private String customerName;//客户
	private String bc;//包装条码
	
	public String getBc() {
		return bc;
	}
	public void setBc(String bc) {
		this.bc = bc;
	}
	public String getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(String requestNumber) {
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
	public Integer getPrimaryQuantity() {
		return primaryQuantity;
	}
	public void setPrimaryQuantity(Integer primaryQuantity) {
		this.primaryQuantity = primaryQuantity;
	}
	public Integer getBarcodeShipQuantity() {
		return barcodeShipQuantity;
	}
	public void setBarcodeShipQuantity(Integer barcodeShipQuantity) {
		this.barcodeShipQuantity = barcodeShipQuantity;
	}
	public Integer getErpShipQuantity() {
		return erpShipQuantity;
	}
	public void setErpShipQuantity(Integer erpShipQuantity) {
		this.erpShipQuantity = erpShipQuantity;
	}
	public String getBarcodeText() {
		return barcodeText;
	}
	public void setBarcodeText(String barcodeText) {
		this.barcodeText = barcodeText;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSourceHeaderNumber() {
		return sourceHeaderNumber;
	}
	public void setSourceHeaderNumber(String sourceHeaderNumber) {
		this.sourceHeaderNumber = sourceHeaderNumber;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
}
