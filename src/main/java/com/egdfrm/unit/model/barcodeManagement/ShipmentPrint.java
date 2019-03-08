package com.egdfrm.unit.model.barcodeManagement;

/**
 * 发运清单条码打印实体
 * Created by tyq on 17/1/19.
 */
public class ShipmentPrint {

    private String segment1;//装配件
    private String prod_type;//型号
    private int quantity;//数量


    public String getSegment1() {
        return segment1;
    }

    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    public String getProd_type() {
        return prod_type;
    }

    public void setProd_type(String prod_type) {
        this.prod_type = prod_type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
