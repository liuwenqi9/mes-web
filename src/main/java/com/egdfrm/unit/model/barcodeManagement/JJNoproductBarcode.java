package com.egdfrm.unit.model.barcodeManagement;
 
 
public class JJNoproductBarcode {
	
	private String type;//操作类型
	private String barcodeText;
	private String inventoryItemId;//物料id
	private Integer codeId;
	private String segment1;// 物料编码
	private String prodType; //产品型号
	private String sourceSubLibrary;//来源子库
	private String sourceLocation;//来源货位
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getCodeId() {
		return codeId;
	}
	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}
	public String getBarcodeText() {
		return barcodeText;
	}
	public void setBarcodeText(String barcodeText) {
		this.barcodeText = barcodeText;
	}
	public String getInventoryItemId() {
		return inventoryItemId;
	}
	public void setInventoryItemId(String inventoryItemId) {
		this.inventoryItemId = inventoryItemId;
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
	public String getSourceSubLibrary() {
		return sourceSubLibrary;
	}
	public void setSourceSubLibrary(String sourceSubLibrary) {
		this.sourceSubLibrary = sourceSubLibrary;
	}
	public String getSourceLocation() {
		return sourceLocation;
	}
	public void setSourceLocation(String sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	 
	 
}
