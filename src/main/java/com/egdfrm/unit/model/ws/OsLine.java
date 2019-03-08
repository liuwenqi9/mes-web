package com.egdfrm.unit.model.ws;

import java.io.Serializable;

/**
 *  海外 订单行
 * Created by hgb on 2018/8/8
 * Email xhy18650@sina.com
 **/


public class OsLine implements Serializable {
    private String crm_order_number; //CRM订单号
    private String org_id;//OU ID
    private String line_number; //行号
    private String order_source_id;//订单来源
    private String inventory_item_id;//物料ID
    private String order_quantity; //订单数量
    private String order_quantity_uom;//单位
    private String unit_list_pric;//价目表价格
    private String unit_selling_pric;//实际销售价格
    private String ship_org;//发货组织
    private String booked_flag;
    private String line_remarks;//备注
    private String process_status;//Y：为成功，导入失败就为错误信息
    private String transfer_flag;//取数标识
    private String required_date;//要货日期
    private String row_mo;

    public String getRow_mo() {
        return row_mo;
    }

    public void setRow_mo(String row_mo) {
        this.row_mo = row_mo;
    }

    public String getRequired_date() {
        return required_date;
    }

    public void setRequired_date(String required_date) {
        this.required_date = required_date;
    }

    public String getCrm_order_number() {
        return crm_order_number;
    }

    public void setCrm_order_number(String crm_order_number) {
        this.crm_order_number = crm_order_number;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getLine_number() {
        return line_number;
    }

    public void setLine_number(String line_number) {
        this.line_number = line_number;
    }

    public String getOrder_source_id() {
        return order_source_id;
    }

    public void setOrder_source_id(String order_source_id) {
        this.order_source_id = order_source_id;
    }

    public String getInventory_item_id() {
        return inventory_item_id;
    }

    public void setInventory_item_id(String inventory_item_id) {
        this.inventory_item_id = inventory_item_id;
    }

    public String getOrder_quantity() {
        return order_quantity;
    }

    public void setOrder_quantity(String order_quantity) {
        this.order_quantity = order_quantity;
    }

    public String getOrder_quantity_uom() {
        return order_quantity_uom;
    }

    public void setOrder_quantity_uom(String order_quantity_uom) {
        this.order_quantity_uom = order_quantity_uom;
    }

    public String getUnit_list_pric() {
        return unit_list_pric;
    }

    public void setUnit_list_pric(String unit_list_pric) {
        this.unit_list_pric = unit_list_pric;
    }

    public String getUnit_selling_pric() {
        return unit_selling_pric;
    }

    public void setUnit_selling_pric(String unit_selling_pric) {
        this.unit_selling_pric = unit_selling_pric;
    }

    public String getShip_org() {
        return ship_org;
    }

    public void setShip_org(String ship_org) {
        this.ship_org = ship_org;
    }

    public String getBooked_flag() {
        return booked_flag;
    }

    public void setBooked_flag(String booked_flag) {
        this.booked_flag = booked_flag;
    }

    public String getLine_remarks() {
        return line_remarks;
    }

    public void setLine_remarks(String line_remarks) {
        this.line_remarks = line_remarks;
    }

    public String getProcess_status() {
        return process_status;
    }

    public void setProcess_status(String process_status) {
        this.process_status = process_status;
    }

    public String getTransfer_flag() {
        return transfer_flag;
    }

    public void setTransfer_flag(String transfer_flag) {
        this.transfer_flag = transfer_flag;
    }
}
