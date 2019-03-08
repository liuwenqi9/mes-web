package com.egdfrm.unit.excelmodel;

import java.io.Serializable;
/**
 *  盘点明细 EXCEL 模板
 */
@SuppressWarnings("serial")
public class PDDetailExcel implements Serializable {
	private Object pdNumber;
	private Object pdDate;
	private Object subinventoryCode;
	private Object locattionCode;
	private Object wipEntityName;
	private Object segment1;
	private Object description;
	private Object prodType;
	private Object packBarcode;
	private Object barcodeText;
	private Object typeDescription;
	private Object statusDescription;
	private Integer pdQuantity;
	private Integer barcodeQuantity;
	private Integer quantity;
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
	public Object getWipEntityName() {
		return wipEntityName;
	}
	public void setWipEntityName(Object wipEntityName) {
		this.wipEntityName = wipEntityName;
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
	public Object getPackBarcode() {
		return packBarcode;
	}
	public void setPackBarcode(Object packBarcode) {
		this.packBarcode = packBarcode;
	}
	public Object getBarcodeText() {
		return barcodeText;
	}
	public void setBarcodeText(Object barcodeText) {
		this.barcodeText = barcodeText;
	}
	public Object getTypeDescription() {
		return typeDescription;
	}
	public void setTypeDescription(Object typeDescription) {
		this.typeDescription = typeDescription;
	}
	public Object getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(Object statusDescription) {
		this.statusDescription = statusDescription;
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
