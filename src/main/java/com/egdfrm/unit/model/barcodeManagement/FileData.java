package com.egdfrm.unit.model.barcodeManagement;

/**
 * @author hgb
 * @date 2018/10/11
 * Email xhy18650@sina.com
 */
public class FileData {

    private int file_id;
    private String file_name;
    private String file_content_type;
    private byte[] file_data;

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_content_type() {
        return file_content_type;
    }

    public void setFile_content_type(String file_content_type) {
        this.file_content_type = file_content_type;
    }

    public byte[] getFile_data() {
        return file_data;
    }

    public void setFile_data(byte[] file_data) {
        this.file_data = file_data;
    }
}
