package com.egdfrm.unit.model.expand.pda;

import java.util.List;

/**
 *  @author sjf
 *  @date 2016年12月26日 
 *	PDA上线返修-扫描返回给PDA的对象
 **/
public class OnLineRepairData {
	//执行结果
	private String resultCode;
	//错误信息	
	private String resultMsg;

	private List<OnLineRepairData> resultData;
	//物料号
	private String lotNo;
	//型号
	private String styleNo;
	//数量
	private String num;
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public List<OnLineRepairData> getResultData() {
		return resultData;
	}
	public void setResultData(List<OnLineRepairData> resultData) {
		this.resultData = resultData;
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
	
	
	
	
}
