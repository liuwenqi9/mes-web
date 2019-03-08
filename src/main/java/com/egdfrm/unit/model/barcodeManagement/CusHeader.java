package com.egdfrm.unit.model.barcodeManagement;

/**
 * @author hgb
 * @date 2018/10/26
 * Email xhy18650@sina.com
 */
public class CusHeader {

    private String customer_id;
    private String party_number;
    private String party_name;
    private String cust_po_number;
    private String sop_yh;
    private String sop_battery;
    private String sop_box;
    private String sop_rh;
    //
    private String your_po;
    private String our_pi;
    private String ps_number; //评审单号
    private String country;
    private String ps_version;
    private String vs_date;
    private String remark;
    private String yh_date;//验货日期
    private String creation_date;//创建日期

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getParty_number() {
        return party_number;
    }

    public void setParty_number(String party_number) {
        this.party_number = party_number;
    }

    public String getParty_name() {
        return party_name;
    }

    public void setParty_name(String party_name) {
        this.party_name = party_name;
    }

    public String getCust_po_number() {
        return cust_po_number;
    }

    public void setCust_po_number(String cust_po_number) {
        this.cust_po_number = cust_po_number;
    }

    public String getSop_yh() {
        return sop_yh;
    }

    public void setSop_yh(String sop_yh) {
        this.sop_yh = sop_yh;
    }

    public String getSop_battery() {
        return sop_battery;
    }

    public void setSop_battery(String sop_battery) {
        this.sop_battery = sop_battery;
    }

    public String getSop_box() {
        return sop_box;
    }

    public void setSop_box(String sop_box) {
        this.sop_box = sop_box;
    }

    public String getSop_rh() {
        return sop_rh;
    }

    public void setSop_rh(String sop_rh) {
        this.sop_rh = sop_rh;
    }

    public String getYour_po() {
        return your_po;
    }

    public void setYour_po(String your_po) {
        this.your_po = your_po;
    }

    public String getOur_pi() {
        return our_pi;
    }

    public void setOur_pi(String our_pi) {
        this.our_pi = our_pi;
    }

    public String getPs_number() {
        return ps_number;
    }

    public void setPs_number(String ps_number) {
        this.ps_number = ps_number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPs_version() {
        return ps_version;
    }

    public void setPs_version(String ps_version) {
        this.ps_version = ps_version;
    }

    public String getVs_date() {
        return vs_date;
    }

    public void setVs_date(String vs_date) {
        this.vs_date = vs_date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getYh_date() {
        return yh_date;
    }

    public void setYh_date(String yh_date) {
        this.yh_date = yh_date;
    }
}
