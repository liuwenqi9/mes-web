package com.egdfrm.unit.excelmodel;

import java.io.Serializable;
/**
 * 挑库冰箱检查 excel模板
 * @author hgb
 * @date 2017-5-3
 */
@SuppressWarnings("serial")
public class PickingExcelModel implements Serializable{
	
	
	private String requestNumber;
	
	private String segment1;
	
	private Integer onhandQuantity;
	
	private String barcodeText;
	
	private String prodType;
	
	private String description;//条码状态
	
	private String packBarcodeText;
	
	private String transaction_date;

	public String getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
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

	public Integer getOnhandQuantity() {
		return onhandQuantity;
	}

	public void setOnhandQuantity(Integer onhandQuantity) {
		this.onhandQuantity = onhandQuantity;
	}

	public String getBarcodeText() {
		return barcodeText;
	}

	public void setBarcodeText(String barcodeText) {
		this.barcodeText = barcodeText;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPackBarcodeText() {
		return packBarcodeText;
	}

	public void setPackBarcodeText(String packBarcodeText) {
		this.packBarcodeText = packBarcodeText;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
