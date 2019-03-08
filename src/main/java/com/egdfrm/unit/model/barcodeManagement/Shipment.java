package com.egdfrm.unit.model.barcodeManagement;

/**
 * 发运清单条码实体
 * Created by tyq on 17/1/19.
 */
public class Shipment {

    private  String barcode_text;//包装条码
    private int quantity;//数量
    private String ship_print_flag;//打印标志
    private String logistics;//物流
    private String address1;//地址
    private String packing_type;//条码类型
    private String city;//目的城市
    private String account_name;//客户名称

    public String getBarcode_text() {
        return barcode_text;
    }

    public void setBarcode_text(String barcode_text) {
        this.barcode_text = barcode_text;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShip_print_flag() {
        return ship_print_flag;
    }

    public void setShip_print_flag(String ship_print_flag) {
        this.ship_print_flag = ship_print_flag;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getPacking_type() {
        return packing_type;
    }

    public void setPacking_type(String packing_type) {
        this.packing_type = packing_type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }
}
