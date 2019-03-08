package com.egdfrm.unit.model.expand.pda;

import java.io.Serializable;
import java.util.List;

/**
 * pda 借机归还
 */
public class Opportunity implements Serializable {


	private static final long serialVersionUID = 4805888676386913328L;

	//登录用户名
	private String userid;
	//组织ID
	private String warehouse;

	//包装条码
	private String snNo;
	//旧货位
	private String oldWareLoca;
	//新货位
	private String newWareLoca;
	//物料编码
	private String lotNo;
	//描述
	private String desr;

	private List<Opportunity> opportunityList;

	public Opportunity() {
		super();
	}

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

	public String getSnNo() {
		return snNo;
	}

	public void setSnNo(String snNo) {
		this.snNo = snNo;
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

	public List<Opportunity> getOpportunityList() {
		return opportunityList;
	}

	public void setOpportunityList(List<Opportunity> opportunityList) {
		this.opportunityList = opportunityList;
	}

	public Opportunity(List<Opportunity> opportunityList) {
		super();
		this.setOpportunityList(opportunityList);
	}
	

}