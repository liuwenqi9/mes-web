package com.egdfrm.unit.model.barcodeManagement;

/**
 * @author hgb
 * @date 2018/10/11
 * Email xhy18650@sina.com
 */
public class CustomerSop {

    private String type;
    private String customer_name;
    private String others_type;
    private String comments;
    private String file_name;
    private Integer file_id;

    private String cust_po_number;

    private Integer org_id;
    private String order_number;
    private Integer line_num;
    private String item;
    private String description;
    private Integer ordered_qty;
    private String status;
    private String schedule_ship_date;
    private String item_model;
    private String customer_model;
    private String logo;
    private String makeorbuy;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getOthers_type() {
        return others_type;
    }

    public void setOthers_type(String others_type) {
        this.others_type = others_type;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public Integer getFile_id() {
        return file_id;
    }

    public void setFile_id(Integer file_id) {
        this.file_id = file_id;
    }

    public String getCust_po_number() {
        return cust_po_number;
    }

    public void setCust_po_number(String cust_po_number) {
        this.cust_po_number = cust_po_number;
    }

    public Integer getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
    }

    public String getOrder_number() {
        return order_number;
    }

    public void setOrder_number(String order_number) {
        this.order_number = order_number;
    }

    public Integer getLine_num() {
        return line_num;
    }

    public void setLine_num(Integer line_num) {
        this.line_num = line_num;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrdered_qty() {
        return ordered_qty;
    }

    public void setOrdered_qty(Integer ordered_qty) {
        this.ordered_qty = ordered_qty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchedule_ship_date() {
        return schedule_ship_date;
    }

    public void setSchedule_ship_date(String schedule_ship_date) {
        this.schedule_ship_date = schedule_ship_date;
    }

    public String getItem_model() {
        return item_model;
    }

    public void setItem_model(String item_model) {
        this.item_model = item_model;
    }

    public String getMakeorbuy() {
        return makeorbuy;
    }

    public void setMakeorbuy(String makeorbuy) {
        this.makeorbuy = makeorbuy;
    }
}
