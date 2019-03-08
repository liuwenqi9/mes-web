package com.egdfrm.unit.model.expand.pda;

import java.io.Serializable;
import java.util.List;

/**
 * @author sjf
 * @date 2016年12月21日 
 * PDA销售退货
 * 
 */ 
public class BackInStorage implements Serializable {
   
	private static final long serialVersionUID = -907272057246883758L;
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

	private String rmaNo;
	//货位
	private String wareLoca;
	//条码
	private String snNo;
	//物料编码
	private String lotNo;
	//描述
	private String desr;
	//数量
	private String num;

	public String getRmaNo() {
		return rmaNo;
	}

	public void setRmaNo(String rmaNo) {
		this.rmaNo = rmaNo;
	}

	private List<BackInStorage> backInStorage;
	public BackInStorage() {
		super();
	}
	public String getWareLoca() {
		return wareLoca;
	}
	public void setWareLoca(String wareLoca) {
		this.wareLoca = wareLoca;
	}
	public String getSnNo() {
		return snNo;
	}
	public void setSnNo(String snNo) {
		this.snNo = snNo;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getDesr() {
		return desr;
	}
	public void setDesr(String desr) {
		this.desr = desr;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public List<BackInStorage> getBackInStorage() {
		return backInStorage;
	}
	public void setBackInStorage(List<BackInStorage> backInStorage) {
		this.backInStorage = backInStorage;
	} 
	

}