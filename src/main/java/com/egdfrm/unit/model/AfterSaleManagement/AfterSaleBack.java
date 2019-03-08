package com.egdfrm.unit.model.AfterSaleManagement;

import java.io.Serializable;
/**
 * 售后 服务 模型
 * @author hgb
 * @date 2017-6-15
 */
@SuppressWarnings("serial")
public class AfterSaleBack implements Serializable{
	
	 private int falg;//1表示已完成 
	 private String IDs;//批量更新用于传参
	 private Integer[] arrayId;//批量更新用于传参
	 private String OTHER_CUSTOM_NAME;//其他客户
	 private String OUTER_CONTACT;  //外箱是否有联系方式
	 private String INTER_NOTE;  //内箱是否有送货单
	 private String REMAKR;  //REMAKR=备注
	 private String RETURN_OPERATION_TIME_START;
	 private String RETURN_OPERATION_TIME_END;
	 private Integer ID;
     private String PRODUCT_BARCODE;
     private String MATERIAL_ID;
     private String DESCRIPTION;
     private String PRODUCTION_TIMES;
     private String ORDER_DELIVERY_TIMES;
     private String STATUS;
     private String RETURN_OPERATOR;
     private String RETURN_OPERATION_TIME;
     private String DELIVERY_OPERATOR;
     private String DELIVERY_OPERATION_TIME;
     private String EXPRESS_NO;
     private String PRODUCT_CATEGORY;
     private String BARCODEID;
     private String WORKORDER;
     private String SEGMENT1;
     private String ADDRESS;
     private String PHONE;
     private String CONTACT_NAME;
     private String MODEL;
     private String RETURN_QTY;
     private String RE_LOGI_COM;
     private String SH_LOGI_COM;
     private String SH_EXP_NO;
     private String RE_EXP_NO;
     private String SHIP_QTY;
     private String SO_NO;
     private String REP_PEOPLE;
     private String REP_REASON;
     private String REP_TYPE;
     private String ISSUE_DESCRIPTION;
     private String CUSTOM_NAME;
     private String REP_WORK_ORDER; 
     private String CUSTOMER_FEEDBACK;
     private String SUPPLIER_DELIVERY_INFO;//供应商快递信息
     private String HAND_OVER_DATE;//交仓库日期
     private String PRINTER_STATUS;//打印状态
     
     
	
    public String getPRINTER_STATUS() {
		return PRINTER_STATUS;
	}
	public void setPRINTER_STATUS(String pRINTER_STATUS) {
		PRINTER_STATUS = pRINTER_STATUS;
	}
	public Integer[] getArrayId() {
		return arrayId;
	}
	public void setArrayId(Integer[] arrayId) {
		this.arrayId = arrayId;
	}
	public String getHAND_OVER_DATE() {
		return HAND_OVER_DATE;
	}
	public void setHAND_OVER_DATE(String hAND_OVER_DATE) {
		HAND_OVER_DATE = hAND_OVER_DATE;
	}
	public String getCUSTOMER_FEEDBACK() {
		return CUSTOMER_FEEDBACK;
	}
	public void setCUSTOMER_FEEDBACK(String cUSTOMER_FEEDBACK) {
		CUSTOMER_FEEDBACK = cUSTOMER_FEEDBACK;
	}
	public String getOUTER_CONTACT() {
		return OUTER_CONTACT;
	}
	public void setOUTER_CONTACT(String oUTER_CONTACT) {
		OUTER_CONTACT = oUTER_CONTACT;
	}
	public String getINTER_NOTE() {
		return INTER_NOTE;
	}
	public void setINTER_NOTE(String iNTER_NOTE) {
		INTER_NOTE = iNTER_NOTE;
	}
	public String getREMAKR() {
		return REMAKR;
	}
	public void setREMAKR(String rEMAKR) {
		REMAKR = rEMAKR;
	}
	public String getOTHER_CUSTOM_NAME() {
		return OTHER_CUSTOM_NAME;
	}
	public void setOTHER_CUSTOM_NAME(String oTHER_CUSTOM_NAME) {
		OTHER_CUSTOM_NAME = oTHER_CUSTOM_NAME;
	}
	public String getSH_EXP_NO() {
		return SH_EXP_NO;
	}
	public void setSH_EXP_NO(String sH_EXP_NO) {
		SH_EXP_NO = sH_EXP_NO;
	}
	public String getIDs() {
		return IDs;
	}
	public void setIDs(String iDs) {
		IDs = iDs;
	}
	public int getFalg() {
		return falg;
	}
	public void setFalg(int falg) {
		this.falg = falg;
	}
	public String getRETURN_OPERATION_TIME_START() {
		return RETURN_OPERATION_TIME_START;
	}
	public void setRETURN_OPERATION_TIME_START(String rETURN_OPERATION_TIME_START) {
		RETURN_OPERATION_TIME_START = rETURN_OPERATION_TIME_START;
	}
	public String getRETURN_OPERATION_TIME_END() {
		return RETURN_OPERATION_TIME_END;
	}
	public void setRETURN_OPERATION_TIME_END(String rETURN_OPERATION_TIME_END) {
		RETURN_OPERATION_TIME_END = rETURN_OPERATION_TIME_END;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getPRODUCT_BARCODE() {
		return PRODUCT_BARCODE;
	}
	public void setPRODUCT_BARCODE(String pRODUCT_BARCODE) {
		PRODUCT_BARCODE = pRODUCT_BARCODE;
	}
	public String getMATERIAL_ID() {
		return MATERIAL_ID;
	}
	public void setMATERIAL_ID(String mATERIAL_ID) {
		MATERIAL_ID = mATERIAL_ID;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	public String getPRODUCTION_TIMES() {
		return PRODUCTION_TIMES;
	}
	public void setPRODUCTION_TIMES(String pRODUCTION_TIMES) {
		PRODUCTION_TIMES = pRODUCTION_TIMES;
	}
	public String getORDER_DELIVERY_TIMES() {
		return ORDER_DELIVERY_TIMES;
	}
	public void setORDER_DELIVERY_TIMES(String oRDER_DELIVERY_TIMES) {
		ORDER_DELIVERY_TIMES = oRDER_DELIVERY_TIMES;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public String getRETURN_OPERATOR() {
		return RETURN_OPERATOR;
	}
	public void setRETURN_OPERATOR(String rETURN_OPERATOR) {
		RETURN_OPERATOR = rETURN_OPERATOR;
	}
	public String getRETURN_OPERATION_TIME() {
		return RETURN_OPERATION_TIME;
	}
	public void setRETURN_OPERATION_TIME(String rETURN_OPERATION_TIME) {
		RETURN_OPERATION_TIME = rETURN_OPERATION_TIME;
	}
	public String getDELIVERY_OPERATOR() {
		return DELIVERY_OPERATOR;
	}
	public void setDELIVERY_OPERATOR(String dELIVERY_OPERATOR) {
		DELIVERY_OPERATOR = dELIVERY_OPERATOR;
	}
	public String getDELIVERY_OPERATION_TIME() {
		return DELIVERY_OPERATION_TIME;
	}
	public void setDELIVERY_OPERATION_TIME(String dELIVERY_OPERATION_TIME) {
		DELIVERY_OPERATION_TIME = dELIVERY_OPERATION_TIME;
	}
	public String getEXPRESS_NO() {
		return EXPRESS_NO;
	}
	public void setEXPRESS_NO(String eXPRESS_NO) {
		EXPRESS_NO = eXPRESS_NO;
	}
	public String getPRODUCT_CATEGORY() {
		return PRODUCT_CATEGORY;
	}
	public void setPRODUCT_CATEGORY(String pRODUCT_CATEGORY) {
		PRODUCT_CATEGORY = pRODUCT_CATEGORY;
	}
	public String getBARCODEID() {
		return BARCODEID;
	}
	public void setBARCODEID(String bARCODEID) {
		BARCODEID = bARCODEID;
	}
	public String getWORKORDER() {
		return WORKORDER;
	}
	public void setWORKORDER(String wORKORDER) {
		WORKORDER = wORKORDER;
	}
	public String getSEGMENT1() {
		return SEGMENT1;
	}
	public void setSEGMENT1(String sEGMENT1) {
		SEGMENT1 = sEGMENT1;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	public String getCONTACT_NAME() {
		return CONTACT_NAME;
	}
	public void setCONTACT_NAME(String cONTACT_NAME) {
		CONTACT_NAME = cONTACT_NAME;
	}
	public String getMODEL() {
		return MODEL;
	}
	public void setMODEL(String mODEL) {
		MODEL = mODEL;
	}
	public String getRETURN_QTY() {
		return RETURN_QTY;
	}
	public void setRETURN_QTY(String rETURN_QTY) {
		RETURN_QTY = rETURN_QTY;
	}
	public String getRE_LOGI_COM() {
		return RE_LOGI_COM;
	}
	public void setRE_LOGI_COM(String rE_LOGI_COM) {
		RE_LOGI_COM = rE_LOGI_COM;
	}
	public String getSH_LOGI_COM() {
		return SH_LOGI_COM;
	}
	public void setSH_LOGI_COM(String sH_LOGI_COM) {
		SH_LOGI_COM = sH_LOGI_COM;
	}
	public String getRE_EXP_NO() {
		return RE_EXP_NO;
	}
	public void setRE_EXP_NO(String rE_EXP_NO) {
		RE_EXP_NO = rE_EXP_NO;
	}
	public String getSHIP_QTY() {
		return SHIP_QTY;
	}
	public void setSHIP_QTY(String sHIP_QTY) {
		SHIP_QTY = sHIP_QTY;
	}
	public String getSO_NO() {
		return SO_NO;
	}
	public void setSO_NO(String sO_NO) {
		SO_NO = sO_NO;
	}
	public String getREP_PEOPLE() {
		return REP_PEOPLE;
	}
	public void setREP_PEOPLE(String rEP_PEOPLE) {
		REP_PEOPLE = rEP_PEOPLE;
	}
	public String getREP_REASON() {
		return REP_REASON;
	}
	public void setREP_REASON(String rEP_REASON) {
		REP_REASON = rEP_REASON;
	}
	public String getREP_TYPE() {
		return REP_TYPE;
	}
	public void setREP_TYPE(String rEP_TYPE) {
		REP_TYPE = rEP_TYPE;
	}
	public String getISSUE_DESCRIPTION() {
		return ISSUE_DESCRIPTION;
	}
	public void setISSUE_DESCRIPTION(String iSSUE_DESCRIPTION) {
		ISSUE_DESCRIPTION = iSSUE_DESCRIPTION;
	}
	public String getCUSTOM_NAME() {
		return CUSTOM_NAME;
	}
	public void setCUSTOM_NAME(String cUSTOM_NAME) {
		CUSTOM_NAME = cUSTOM_NAME;
	}
	public String getREP_WORK_ORDER() {
		return REP_WORK_ORDER;
	}
	public void setREP_WORK_ORDER(String rEP_WORK_ORDER) {
		REP_WORK_ORDER = rEP_WORK_ORDER;
	}
	public String getSUPPLIER_DELIVERY_INFO() {
		return SUPPLIER_DELIVERY_INFO;
	}
	public void setSUPPLIER_DELIVERY_INFO(String sUPPLIER_DELIVERY_INFO) {
		SUPPLIER_DELIVERY_INFO = sUPPLIER_DELIVERY_INFO;
	}
     
}
