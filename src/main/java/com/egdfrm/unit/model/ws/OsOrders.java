package com.egdfrm.unit.model.ws;

import java.io.Serializable;
import java.util.List;

/**
 * 海外订单头
 * Created by hgb on 2018/8/8
 * Email xhy18650@sina.com
 **/
public class OsOrders implements Serializable {

   private String  crm_order_number;//CRM订单号
   private String  erp_order_number;//ERP订单号
   private String  process_status;//状态
   private String  err_messages;//错误描述
   private String  org_id;//OU id
   private String  order_source_id;//订单来源
   private String  order_date;//订单日期
   private String  order_type;//订单类型
   private String  salesrep_id;//销售人员ID
   private String  sold_to_org_id;//客户ID
   private String  sold_from_org_id;//销售OU
   private String  ship_to_org_id;//发运地址ID
   private String  transactional_curr_code;//货币单位
   private String  booked_flag;//登记
   private String  customer_po;
   private String  remark;//备注信息
   private String  price_list_id;//价目表ID
   private String  ship_method;
   private String  country;//收货地国家
   private String  address;
   private String  fob;//港口
   private String  ship_org;//发运组织-区分是东莞还是香港订单
   private String  payment_term;//付款条件
   private String  contacts_name;
   private String  phone;
   private String  transfer_flag;//取数标识
   private String  creation_time;
   private String  pi_number;
   private String po_number;
   private List<OsLine> osLines; //订单行

    public String getPi_number() {
        return pi_number;
    }

    public void setPi_number(String pi_number) {
        this.pi_number = pi_number;
    }

    public String getPo_number() {
        return po_number;
    }

    public void setPo_number(String po_number) {
        this.po_number = po_number;
    }

    public String getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(String creation_time) {
        this.creation_time = creation_time;
    }

    public List<OsLine> getOsLines() {
        return osLines;
    }

    public void setOsLines(List<OsLine> osLines) {
        this.osLines = osLines;
    }

    public String getCrm_order_number() {
        return crm_order_number;
    }

    public void setCrm_order_number(String crm_order_number) {
        this.crm_order_number = crm_order_number;
    }

    public String getErp_order_number() {
        return erp_order_number;
    }

    public void setErp_order_number(String erp_order_number) {
        this.erp_order_number = erp_order_number;
    }

    public String getProcess_status() {
        return process_status;
    }

    public void setProcess_status(String process_status) {
        this.process_status = process_status;
    }

    public String getErr_messages() {
        return err_messages;
    }

    public void setErr_messages(String err_messages) {
        this.err_messages = err_messages;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getOrder_source_id() {
        return order_source_id;
    }

    public void setOrder_source_id(String order_source_id) {
        this.order_source_id = order_source_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getSalesrep_id() {
        return salesrep_id;
    }

    public void setSalesrep_id(String salesrep_id) {
        this.salesrep_id = salesrep_id;
    }

    public String getSold_to_org_id() {
        return sold_to_org_id;
    }

    public void setSold_to_org_id(String sold_to_org_id) {
        this.sold_to_org_id = sold_to_org_id;
    }

    public String getSold_from_org_id() {
        return sold_from_org_id;
    }

    public void setSold_from_org_id(String sold_from_org_id) {
        this.sold_from_org_id = sold_from_org_id;
    }

    public String getShip_to_org_id() {
        return ship_to_org_id;
    }

    public void setShip_to_org_id(String ship_to_org_id) {
        this.ship_to_org_id = ship_to_org_id;
    }

    public String getTransactional_curr_code() {
        return transactional_curr_code;
    }

    public void setTransactional_curr_code(String transactional_curr_code) {
        this.transactional_curr_code = transactional_curr_code;
    }

    public String getBooked_flag() {
        return booked_flag;
    }

    public void setBooked_flag(String booked_flag) {
        this.booked_flag = booked_flag;
    }

    public String getCustomer_po() {
        return customer_po;
    }

    public void setCustomer_po(String customer_po) {
        this.customer_po = customer_po;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPrice_list_id() {
        return price_list_id;
    }

    public void setPrice_list_id(String price_list_id) {
        this.price_list_id = price_list_id;
    }

    public String getShip_method() {
        return ship_method;
    }

    public void setShip_method(String ship_method) {
        this.ship_method = ship_method;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFob() {
        return fob;
    }

    public void setFob(String fob) {
        this.fob = fob;
    }

    public String getShip_org() {
        return ship_org;
    }

    public void setShip_org(String ship_org) {
        this.ship_org = ship_org;
    }

    public String getPayment_term() {
        return payment_term;
    }

    public void setPayment_term(String payment_term) {
        this.payment_term = payment_term;
    }

    public String getContacts_name() {
        return contacts_name;
    }

    public void setContacts_name(String contacts_name) {
        this.contacts_name = contacts_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTransfer_flag() {
        return transfer_flag;
    }

    public void setTransfer_flag(String transfer_flag) {
        this.transfer_flag = transfer_flag;
    }
}


