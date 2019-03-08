package com.egdfrm.unit.model.barcodeManagement;
 
 
public class InPro {
	
	/**
	 * 返回参数
	 */
	private String barcodeText;//产品条码
	private Integer inventoryItemId;//物料id 
	private String prodType; //产品型号
	private String segment1;// 物料编码
	private String description;//物料描述
	
	/**
	 * 页面参数
	 */
	private String barcodeTexts;//产品条码
	private String inventoryItemIds;//物料id 
	private String findName ; //
	
	public String getFindName() {
		return findName;
	}
	public void setFindName(String findName) {
		this.findName = findName;
	}
	public String getBarcodeText() {
		return barcodeText;
	}
	public void setBarcodeText(String barcodeText) {
		this.barcodeText = barcodeText;
	}
	public Integer getInventoryItemId() {
		return inventoryItemId;
	}
	public void setInventoryItemId(Integer inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBarcodeTexts() {
		return barcodeTexts;
	}
	public void setBarcodeTexts(String barcodeTexts) {
		this.barcodeTexts = barcodeTexts;
	}
	public String getInventoryItemIds() {
		return inventoryItemIds;
	}
	public void setInventoryItemIds(String inventoryItemIds) {
		this.inventoryItemIds = inventoryItemIds;
	}
	 
	 
}
