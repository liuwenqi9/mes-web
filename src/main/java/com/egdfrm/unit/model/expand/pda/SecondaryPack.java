package com.egdfrm.unit.model.expand.pda;

import java.io.Serializable;
import java.util.List;

public class SecondaryPack implements Serializable { 

    /**
	 * @author sjf
	 * @date 2016年12月16日 
	 * 
	 */
	private static final long serialVersionUID = -6410698199104790663L;
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
	//包装条码
	private String packNo;
	//产品条码
    private String snNo;
    //产品编号
    private String snID;
    //数量
    private String num; 
    private List<SecondaryPack> secondaryPack;

    public SecondaryPack() {
		super();
		
	}
	public SecondaryPack(List<SecondaryPack> secondaryPack) {
		super();
		this.setSecondaryPack(secondaryPack);
	}
 
 
	public String getPackNo() {
		return packNo;
	}
	public void setPackNo(String packNo) {
		this.packNo = packNo;
	}
	public String getSnNo() {
		return snNo;
	}
	public void setSnNo(String snNo) {
		this.snNo = snNo;
	}
	public String getSnID() {
		return snID;
	}
	public void setSnID(String snID) {
		this.snID = snID;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public List<SecondaryPack> getSecondaryPack() {
		return secondaryPack;
	}
	public void setSecondaryPack(List<SecondaryPack> secondaryPack) {
		this.secondaryPack = secondaryPack;
	}  
	
}