package com.egdfrm.unit.model.expand.pda;

import java.io.Serializable;
import java.util.List;

public class StorageTransfer implements Serializable {
  
	/**
	 * @author sjf
	 * @date 2016年12月21日 
	 * PDA子库存转移
	 * 
	 */
	private static final long serialVersionUID = 4805888676386913328L;
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
	//旧货位
	private String oldWareLoca;
	//包装条码
	private String snNo;
	//物料编码
	private String lotNo;
	//描述
	private String desr;
	//新货位
	private String newWareLoca; 
	
	private List<StorageTransfer> storageTransfer;
	public StorageTransfer() {
		super();
	}
	public String getOldWareLoca() {
		return oldWareLoca;
	}
	public void setOldWareLoca(String oldWareLoca) {
		this.oldWareLoca = oldWareLoca;
	}
	public String getNewWareLoca() {
		return newWareLoca;
	}
	public void setNewWareLoca(String newWareLoca) {
		this.newWareLoca = newWareLoca;
	}
	public String getSnNo() {
		return snNo;
	}
	public void setSnNo(String snNo) {
		this.snNo = snNo;
	}
	public String getDesr() {
		return desr;
	}
	public void setDesr(String desr) {
		this.desr = desr;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public List<StorageTransfer> getStorageTransfer() {
		return storageTransfer;
	}
	public void setStorageTransfer(List<StorageTransfer> storageTransfer) {
		this.storageTransfer = storageTransfer;
	}
	public StorageTransfer(String oldWareLoca, String snNo, String lotNo,
			String desr, String newWareLoca) {
		super();
		this.oldWareLoca = oldWareLoca;
		this.snNo = snNo;
		this.lotNo = lotNo;
		this.desr = desr;
		this.newWareLoca = newWareLoca;
	}
	

}