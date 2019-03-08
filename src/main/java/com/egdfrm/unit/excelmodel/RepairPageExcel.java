package com.egdfrm.unit.excelmodel;

import java.io.Serializable;
/**
 * 返修信息excel模板
 * @author hgb
 * @date 2017-6-7
 */
@SuppressWarnings("serial")
public class RepairPageExcel implements Serializable{
	
	private String barcode_text; //条码
    private String wip_entity_name;//生产工单
    private String wip_date;// 生产时间
    private String transaction_date; //返修时间
	public String getBarcode_text() {
		return barcode_text;
	}
	public void setBarcode_text(String barcode_text) {
		this.barcode_text = barcode_text;
	}
	public String getWip_entity_name() {
		return wip_entity_name;
	}
	public void setWip_entity_name(String wip_entity_name) {
		this.wip_entity_name = wip_entity_name;
	}
	public String getWip_date() {
		return wip_date;
	}
	public void setWip_date(String wip_date) {
		this.wip_date = wip_date;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
    

}
