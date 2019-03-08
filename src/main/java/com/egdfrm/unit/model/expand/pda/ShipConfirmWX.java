package com.egdfrm.unit.model.expand.pda;

import java.io.Serializable;
import java.util.List;

public class ShipConfirmWX implements Serializable { 
 
	/**
	 * @author 兰继明
	 * @date 2017年04月28日 
	 * 
	 */
	private static final long serialVersionUID = -4009211850516575564L;
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
	//出货通知单号
	private String out_sn;
	//包装条码
	private String pack_no;
	//数量
	private String num;
	
	private List<ShipConfirmWX> shipConfirmWX;
	public String getOut_sn() {
		return out_sn;
	}
	public void setOut_sn(String out_sn) {
		this.out_sn = out_sn;
	}
	public String getPack_no() {
		return pack_no;
	}
	public void setPack_no(String pack_no) {
		this.pack_no = pack_no;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public List<ShipConfirmWX> getShipConfirmWX() {
		return shipConfirmWX;
	}
	public void setShipConfirmWX(List<ShipConfirmWX> shipConfirmWX) {
		this.shipConfirmWX = shipConfirmWX;
	}
	
	
}