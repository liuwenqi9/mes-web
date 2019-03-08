package com.egdfrm.unit.model.expand.pda;

import java.io.Serializable;
import java.util.List;
/**
 * 借机PDA往服务端传输提交数据的模型
 * @author 兰继明
 * @date 2017年03月14日 
 * 
 */
public class BorrowProduct implements Serializable { 

    
	//登录用户名
	private String userid;
	//组织ID
	private String warehouse;
	
    
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	} 
	
	
	//借机单号
	private String outNo;
	//条码
    private String snNo;
    //数量
    private String num;
    //物料号
    private String lotNo;
    //产品类别
    private String style; 
    //物料描述
	private String desr;
	// 快递单号
	private String expressNumber;
	private List<BorrowProduct> borrowProduct;

    public BorrowProduct() {
		super();	
	}
	public String getOutNo() {
		return outNo;
	}
	public void setOutNo(String outNo) {
		this.outNo = outNo;
	}
	public String getSnNo() {
		return snNo;
	}
	public void setSnNo(String snNo) {
		this.snNo = snNo;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getDesr() {
		return desr;
	}
	public void setDesr(String desr) {
		this.desr = desr;
	}
	public String getExpressNumber() {
		return expressNumber;
	}
	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}
	public List<BorrowProduct> getBorrowProduct() {
		return borrowProduct;
	}
	public void setBorrowProduct(List<BorrowProduct> borrowProduct) {
		this.borrowProduct = borrowProduct;
	}
	
}