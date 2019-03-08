package com.egdfrm.unit.model.expand.pda;

import java.io.Serializable;
import java.util.List;

public class SaleOutStorage implements Serializable { 

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
	//挑库单号
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
    private List<SaleOutStorage> saleOutStorage;

    public SaleOutStorage() {
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
	public List<SaleOutStorage> getSaleOutStorage() {
		return saleOutStorage;
	}
	public void setSaleOutStorage(List<SaleOutStorage> saleOutStorage) {
		this.saleOutStorage = saleOutStorage;
	}
	public String getDesr() {
		return desr;
	}
	public void setDesr(String desr) {
		this.desr = desr;
	}
	
}