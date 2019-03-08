package com.egdfrm.unit.model.barcodeManagement;

import java.io.Serializable;

public class BigPacking implements Serializable{

    private Integer big_packing_barcode_id;

    private String big_barcode_text;

    private String big_pack_quantity;


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
}
