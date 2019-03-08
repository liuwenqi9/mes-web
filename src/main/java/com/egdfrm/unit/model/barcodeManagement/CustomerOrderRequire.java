package com.egdfrm.unit.model.barcodeManagement;

/**
 *  客户订单要求
 * @author hgb
 * @date 2018/11/8
 * Email xhy18650@sina.com
 */
public class CustomerOrderRequire {

    private String cust_po_number;
    private Integer line_num;
    private Integer order_number;
    private String item;
    private String item_model;
    private String customer_model;
    private String logo;
    private Integer ordered_qty;
    private String schedule_ship_date;
    private String status;
    private String makeorbuy ;
    private String description;

    public String getCust_po_number() {
        return cust_po_number;
    }

    public void setCust_po_number(String cust_po_number) {
        this.cust_po_number = cust_po_number;
    }

    public Integer getLine_num() {
        return line_num;
    }

    public void setLine_num(Integer line_num) {
        this.line_num = line_num;
    }

    public Integer getOrder_number() {
        return order_number;
    }

    public void setOrder_number(Integer order_number) {
        this.order_number = order_number;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItem_model() {
        return item_model;
    }

    public void setItem_model(String item_model) {
        this.item_model = item_model;
    }

    public String getCustomer_model() {
        return customer_model;
    }

    public void setCustomer_model(String customer_model) {
        this.customer_model = customer_model;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getOrdered_qty() {
        return ordered_qty;
    }

    public void setOrdered_qty(Integer ordered_qty) {
        this.ordered_qty = ordered_qty;
    }

    public String getSchedule_ship_date() {
        return schedule_ship_date;
    }

    public void setSchedule_ship_date(String schedule_ship_date) {
        this.schedule_ship_date = schedule_ship_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMakeorbuy() {
        return makeorbuy;
    }

    public void setMakeorbuy(String makeorbuy) {
        this.makeorbuy = makeorbuy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
