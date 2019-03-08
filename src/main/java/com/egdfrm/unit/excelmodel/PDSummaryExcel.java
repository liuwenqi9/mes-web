package com.egdfrm.unit.excelmodel;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PDSummaryExcel implements Serializable{  
 
	private Object pdNumber;
	private Object pdDate;
	private Object subinventoryCode;
	private Object locattionCode; 
	private Object segment1;
	private Object description;
	private Object mo;
	private Object prodType;
	private Integer erpOnhandQuantity;
	private Integer pdQuantity;
	private Integer barcodeQuantity;
	private Integer quantity;

	public Object getMo() {
		return mo;
	}

	public void setMo(Object mo) {
		this.mo = mo;
	}

	public Object getPdNumber() {
		return pdNumber;
	}
	public void setPdNumber(Object pdNumber) {
		this.pdNumber = pdNumber;
	}
	public Object getPdDate() {
		return pdDate;
	}
	public void setPdDate(Object pdDate) {
		this.pdDate = pdDate;
	}
	public Object getSubinventoryCode() {
		return subinventoryCode;
	}
	public void setSubinventoryCode(Object subinventoryCode) {
		this.subinventoryCode = subinventoryCode;
	}
	public Object getLocattionCode() {
		return locattionCode;
	}
	public void setLocattionCode(Object locattionCode) {
		this.locattionCode = locattionCode;
	}
	public Object getSegment1() {
		return segment1;
	}
	public void setSegment1(Object segment1) {
		this.segment1 = segment1;
	}
	public Object getDescription() {
		return description;
	}
	public void setDescription(Object description) {
		this.description = description;
	}
	public Object getProdType() {
		return prodType;
	}
	public void setProdType(Object prodType) {
		this.prodType = prodType;
	}
	public Integer getErpOnhandQuantity() {
		return erpOnhandQuantity;
	}
	public void setErpOnhandQuantity(Integer erpOnhandQuantity) {
		this.erpOnhandQuantity = erpOnhandQuantity;
	}
	public Integer getPdQuantity() {
		return pdQuantity;
	}
	public void setPdQuantity(Integer pdQuantity) {
		this.pdQuantity = pdQuantity;
	}
	public Integer getBarcodeQuantity() {
		return barcodeQuantity;
	}
	public void setBarcodeQuantity(Integer barcodeQuantity) {
		this.barcodeQuantity = barcodeQuantity;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	 
	 
 
	
	
	
}
