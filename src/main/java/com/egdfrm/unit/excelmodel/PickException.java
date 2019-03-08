package com.egdfrm.unit.excelmodel;

import java.io.Serializable;

/**
 * 挑库发运异常
 * @author hgb
 * @date 2017-5-18
 */
@SuppressWarnings("serial")
public class PickException implements Serializable{

	private String source_header_number ;// --销售订单  --
	private String customer_name ;//              --客户
	private String request_number;//              --挑库单号  ---
	private String segment1;//                    --物料编码
	private String description ;//                --描述
	private String prod_type ;//                  --型号
	private String pick_date ;    //               --MES挑库日期
	private Integer move_order_quantity  ;//       --需求数量
	private Integer mes_pick_quantity;//           --MES挑库数
	private Integer erp_picked_quantity ;//        --ERP挑库数
	private Integer mes_ship_quantity      ;//     --MES发运数
	private Integer erp_ship_quantity       ;//    --ERP发运数
	public String getSource_header_number() {
		return source_header_number;
	}
	public void setSource_header_number(String source_header_number) {
		this.source_header_number = source_header_number;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getRequest_number() {
		return request_number;
	}
	public void setRequest_number(String request_number) {
		this.request_number = request_number;
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProd_type() {
		return prod_type;
	}
	public void setProd_type(String prod_type) {
		this.prod_type = prod_type;
	}
	public String getPick_date() {
		return pick_date;
	}
	public void setPick_date(String pick_date) {
		this.pick_date = pick_date;
	}
	public Integer getMove_order_quantity() {
		return move_order_quantity;
	}
	public void setMove_order_quantity(Integer move_order_quantity) {
		this.move_order_quantity = move_order_quantity;
	}
	public Integer getMes_pick_quantity() {
		return mes_pick_quantity;
	}
	public void setMes_pick_quantity(Integer mes_pick_quantity) {
		this.mes_pick_quantity = mes_pick_quantity;
	}
	public Integer getErp_picked_quantity() {
		return erp_picked_quantity;
	}
	public void setErp_picked_quantity(Integer erp_picked_quantity) {
		this.erp_picked_quantity = erp_picked_quantity;
	}
	public Integer getMes_ship_quantity() {
		return mes_ship_quantity;
	}
	public void setMes_ship_quantity(Integer mes_ship_quantity) {
		this.mes_ship_quantity = mes_ship_quantity;
	}
	public Integer getErp_ship_quantity() {
		return erp_ship_quantity;
	}
	public void setErp_ship_quantity(Integer erp_ship_quantity) {
		this.erp_ship_quantity = erp_ship_quantity;
	}
	
}
