package com.egdfrm.unit.model.barcodeManagement;

/**
 *  客户MO要求
 * @author hgb
 * @date 2018/11/8
 * Email xhy18650@sina.com
 */
public class CustomerMoRequire {

    private Integer file_id;
    private Integer line_num;
    private String cust_po_number;
    private String type;
    private String others_type;
    private String comments;
    private String file_name;

    public Integer getFile_id() {
        return file_id;
    }

    public void setFile_id(Integer file_id) {
        this.file_id = file_id;
    }

    public Integer getLine_num() {
        return line_num;
    }

    public void setLine_num(Integer line_num) {
        this.line_num = line_num;
    }

    public String getCust_po_number() {
        return cust_po_number;
    }

    public void setCust_po_number(String cust_po_number) {
        this.cust_po_number = cust_po_number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
