package com.egdfrm.unit.model.expand.pda;

import java.io.Serializable;
import java.util.List;

public class BigPackModel implements Serializable {

    private static final long serialVersionUID = -6410698199104790663L;

    //登录用户名
    private String userid;
    //组织ID
    private String warehouse;

    private  String bigBarcodeText;

    private String smallPackBarcodeText;

    private  String prodType;

    private  String segment1;

    private String num;//当前行包装数量

    private List<BigPackModel> bigPackModelList;


    public BigPackModel() {
        super();
    }

    public BigPackModel(List<BigPackModel> bigPackModelList) {
        super();
        this.setBigPackModelList(bigPackModelList);
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getSegment1() {
        return segment1;
    }

    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    public List<BigPackModel> getBigPackModelList() {
        return bigPackModelList;
    }

    public void setBigPackModelList(List<BigPackModel> bigPackModelList) {
        this.bigPackModelList = bigPackModelList;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

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

    public String getBigBarcodeText() {
        return bigBarcodeText;
    }

    public void setBigBarcodeText(String bigBarcodeText) {
        this.bigBarcodeText = bigBarcodeText;
    }

    public String getSmallPackBarcodeText() {
        return smallPackBarcodeText;
    }

    public void setSmallPackBarcodeText(String smallPackBarcodeText) {
        this.smallPackBarcodeText = smallPackBarcodeText;
    }
}
