package com.egdfrm.unit.model.stock;

/**
 * 成品库存_实体
 * Created by tyq on 17/1/16.
 */
public class Finished {

    private Integer organization_id;//组织机构ID
    private String subinventory_code;//子库
    private String locattion_code;//货位
    private String mo_order;//MO单
    private String item_no;//物料编码
    private String item_desc;//描述
    private String prod_tpye;//产品型号
    private Integer barcode_quantity;//现有量
    private Integer erp_onhand_quantity;//ERP子库库存
    private Integer pack_count; //包装箱数
    
    public Integer getPack_count() {
		return pack_count;
	}

	public void setPack_count(Integer pack_count) {
		this.pack_count = pack_count;
	}

	public String getSubinventory_code() {
        return subinventory_code;
    }

    public void setSubinventory_code(String subinventory_code) {
        this.subinventory_code = subinventory_code;
    }

    public String getLocattion_code() {
        return locattion_code;
    }

    public void setLocattion_code(String locattion_code) {
        this.locattion_code = locattion_code;
    }

    public String getItem_no() {
        return item_no;
    }

    public void setItem_no(String item_no) {
        this.item_no = item_no;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }

    public String getProd_tpye() {
        return prod_tpye;
    }

    public void setProd_tpye(String prod_tpye) {
        this.prod_tpye = prod_tpye;
    }


    public String getMo_order() {
        return mo_order;
    }

    public void setMo_order(String mo_order) {
        this.mo_order = mo_order;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    public Integer getBarcode_quantity() {
        return barcode_quantity;
    }

    public void setBarcode_quantity(Integer barcode_quantity) {
        this.barcode_quantity = barcode_quantity;
    }

    public Integer getErp_onhand_quantity() {
        return erp_onhand_quantity;
    }

    public void setErp_onhand_quantity(Integer erp_onhand_quantity) {
        this.erp_onhand_quantity = erp_onhand_quantity;
    }
}
