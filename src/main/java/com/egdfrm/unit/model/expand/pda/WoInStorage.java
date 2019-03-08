package com.egdfrm.unit.model.expand.pda;

import java.io.Serializable;
import java.util.List;

public class WoInStorage implements Serializable {

	/**
	 * @author sjf
	 * @date 2016年12月20日
	 * 
	 */
	private static final long serialVersionUID = -4668901057547698289L; 

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
	//货位
	private String wareLoca;
	//工单号
	private String workNo;
	//包装条码
	private String packNo;
	//数量
	private String num;
	//物料编码
	private String lotNo;
	
	private List<WoInStorage> woInStorage;
	public WoInStorage() {
		super();
	}

	public String getWareLoca() {
		return wareLoca;
	}

	public void setWareLoca(String wareLoca) {
		this.wareLoca = wareLoca;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
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

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	} 

	public List<WoInStorage> getWoInStorage() {
		return woInStorage;
	}

	public void setWoInStorage(List<WoInStorage> woInStorage) {
		this.woInStorage = woInStorage;
	} 

}