package com.egdfrm.unit.model.barcodeManagement;

import java.io.Serializable;

public class BigPackingPrint implements Serializable{

    private Integer big_packing_barcode_id;

    private String big_barcode_text;

    private String big_pack_quantity;

    private Integer small_packing_barcode_id ;

    private String small_barcode_text;

    private String small_pack_quantity;

    private String small_prod_type;

    private String small_segment1;

    public Integer getBig_packing_barcode_id() {
        return big_packing_barcode_id;
    }

    public void setBig_packing_barcode_id(Integer big_packing_barcode_id) {
        this.big_packing_barcode_id = big_packing_barcode_id;
    }

    public String getBig_barcode_text() {
        return big_barcode_text;
    }

    public void setBig_barcode_text(String big_barcode_text) {
        this.big_barcode_text = big_barcode_text;
    }

    public String getBig_pack_quantity() {
        return big_pack_quantity;
    }

    public void setBig_pack_quantity(String big_pack_quantity) {
        this.big_pack_quantity = big_pack_quantity;
    }

    public String getSmall_pack_quantity() {
        return small_pack_quantity;
    }

    public void setSmall_pack_quantity(String small_pack_quantity) {
        this.small_pack_quantity = small_pack_quantity;
    }

    public Integer getSmall_packing_barcode_id() {
        return small_packing_barcode_id;
    }

    public void setSmall_packing_barcode_id(Integer small_packing_barcode_id) {
        this.small_packing_barcode_id = small_packing_barcode_id;
    }

    public String getSmall_barcode_text() {
        return small_barcode_text;
    }

    public void setSmall_barcode_text(String small_barcode_text) {
        this.small_barcode_text = small_barcode_text;
    }


    public String getSmall_prod_type() {
        return small_prod_type;
    }

    public void setSmall_prod_type(String small_prod_type) {
        this.small_prod_type = small_prod_type;
    }

    public String getSmall_segment1() {
        return small_segment1;
    }

    public void setSmall_segment1(String small_segment1) {
        this.small_segment1 = small_segment1;
    }
}
