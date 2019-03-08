package com.egdfrm.unit.model.expand.pda;

import java.io.Serializable;
import java.util.List;

/**
 * @author sjf
 * @date 2016年12月16日 
 * PDA上线返修
 */ 
public class OnLineRepair implements Serializable { 
	/**
	 * @author sjf
	 * @date 2016年12月26日 
	 * 
	 */
	private static final long serialVersionUID = 1335261224154090089L;
	//登录用户名
	private String userid;
	//组织ID
	private String warehouse;
	//结果，返回成功/失败
	private String result;
	//错误信息
	private String errMsg;
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

	private String docNumber;
	//包装条码
	private String snPackNo;
	//物料号
	private String lotNo;
	//型号
	private String styleNo;
	//数量
	private String num;

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	private List<OnLineRepair> onLineRepair;
	public String getSnPackNo() {
		return snPackNo;
	}
	public void setSnPackNo(String snPackNo) {
		this.snPackNo = snPackNo;
	}
	public String getLotNo() {
		return lotNo;
	}
	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	public String getStyleNo() {
		return styleNo;
	}
	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public List<OnLineRepair> getOnLineRepair() {
		return onLineRepair;
	}
	public void setOnLineRepair(List<OnLineRepair> onLineRepair) {
		this.onLineRepair = onLineRepair;
	}
	
	
	
}