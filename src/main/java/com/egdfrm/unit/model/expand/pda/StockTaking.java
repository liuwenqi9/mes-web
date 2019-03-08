package com.egdfrm.unit.model.expand.pda;

import java.io.Serializable;
import java.util.List;

/**
 * @author sjf
 * @date 2016年12月16日 
 * PDA盘点
 */ 
public class StockTaking implements Serializable { 

	/**
	 * @author sjf
	 * @date 2016年12月26日 
	 * 
	 */
	private static final long serialVersionUID = 2531506685524326071L;
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
	//盘点序列号
	private String checkNo;
	//库存
    private String wareLoca;
    //条码
    private String snNo;
    //数量
    private String num;
    private List<StockTaking> stockTaking;

    public StockTaking() {
		super();	
	}
	public String getCheckNo() {
		return checkNo;
	}
	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
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
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public List<StockTaking> getStockTaking() {
		return stockTaking;
	}
	public void setStockTaking(List<StockTaking> stockTaking) {
		this.stockTaking = stockTaking;
	}
	
}