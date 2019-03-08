package com.egdfrm.unit.model.expand.pda;

import java.io.Serializable;
import java.util.List;

public class ShipConfirm implements Serializable { 
 
	/**
	 * @author sjf
	 * @date 2016年12月23日 
	 * 
	 */
	private static final long serialVersionUID = -4009231850516575564L;
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
	//快递单号
	private String expressNo;
	//包装条码
	private String packNo;
	//数量
	private String num;
	
	private List<ShipConfirm> shipConfirm;
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getPackNo() {
		return packNo;
	}
	public void setPackNo(String packNo) {
		this.packNo = packNo;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public List<ShipConfirm> getShipConfirm() {
		return shipConfirm;
	}
	public void setShipConfirm(List<ShipConfirm> shipConfirm) {
		this.shipConfirm = shipConfirm;
	}
}