package com.egdfrm.unit.model.standard;

import java.io.Serializable;
import java.math.BigDecimal;

public class CuxOrderLine implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUX_ORDER_LINE.CRM_ORDER_NUMBER
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    private String crmOrderNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUX_ORDER_LINE.ORG_ID
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    private BigDecimal orgId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUX_ORDER_LINE.LINE_NUMBER
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    private String lineNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUX_ORDER_LINE.ORDER_SOURCE_ID
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    private BigDecimal orderSourceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUX_ORDER_LINE.INVENTORY_ITEM_ID
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    private BigDecimal inventoryItemId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUX_ORDER_LINE.ORDER_QUANTITY
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    private BigDecimal orderQuantity;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUX_ORDER_LINE.ORDER_QUANTITY_UOM
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    private String orderQuantityUom;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUX_ORDER_LINE.UNIT_LIST_PRIC
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    private BigDecimal unitListPric;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUX_ORDER_LINE.UNIT_SELLING_PRIC
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    private BigDecimal unitSellingPric;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUX_ORDER_LINE.BOOKED_FLAG
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    private String bookedFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUX_ORDER_LINE.LINE_REMARKS
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    private String lineRemarks;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUX_ORDER_LINE.LINE_SHIP_TO
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    private String lineShipTo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column CUX_ORDER_LINE.PROCESS_STATUS
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    private String processStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table CUX_ORDER_LINE
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUX_ORDER_LINE.CRM_ORDER_NUMBER
     *
     * @return the value of CUX_ORDER_LINE.CRM_ORDER_NUMBER
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public String getCrmOrderNumber() {
        return crmOrderNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUX_ORDER_LINE.CRM_ORDER_NUMBER
     *
     * @param crmOrderNumber the value for CUX_ORDER_LINE.CRM_ORDER_NUMBER
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public void setCrmOrderNumber(String crmOrderNumber) {
        this.crmOrderNumber = crmOrderNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUX_ORDER_LINE.ORG_ID
     *
     * @return the value of CUX_ORDER_LINE.ORG_ID
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public BigDecimal getOrgId() {
        return orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUX_ORDER_LINE.ORG_ID
     *
     * @param orgId the value for CUX_ORDER_LINE.ORG_ID
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public void setOrgId(BigDecimal orgId) {
        this.orgId = orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUX_ORDER_LINE.LINE_NUMBER
     *
     * @return the value of CUX_ORDER_LINE.LINE_NUMBER
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public String getLineNumber() {
        return lineNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUX_ORDER_LINE.LINE_NUMBER
     *
     * @param lineNumber the value for CUX_ORDER_LINE.LINE_NUMBER
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUX_ORDER_LINE.ORDER_SOURCE_ID
     *
     * @return the value of CUX_ORDER_LINE.ORDER_SOURCE_ID
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public BigDecimal getOrderSourceId() {
        return orderSourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUX_ORDER_LINE.ORDER_SOURCE_ID
     *
     * @param orderSourceId the value for CUX_ORDER_LINE.ORDER_SOURCE_ID
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public void setOrderSourceId(BigDecimal orderSourceId) {
        this.orderSourceId = orderSourceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUX_ORDER_LINE.INVENTORY_ITEM_ID
     *
     * @return the value of CUX_ORDER_LINE.INVENTORY_ITEM_ID
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public BigDecimal getInventoryItemId() {
        return inventoryItemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUX_ORDER_LINE.INVENTORY_ITEM_ID
     *
     * @param inventoryItemId the value for CUX_ORDER_LINE.INVENTORY_ITEM_ID
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public void setInventoryItemId(BigDecimal inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUX_ORDER_LINE.ORDER_QUANTITY
     *
     * @return the value of CUX_ORDER_LINE.ORDER_QUANTITY
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUX_ORDER_LINE.ORDER_QUANTITY
     *
     * @param orderQuantity the value for CUX_ORDER_LINE.ORDER_QUANTITY
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUX_ORDER_LINE.ORDER_QUANTITY_UOM
     *
     * @return the value of CUX_ORDER_LINE.ORDER_QUANTITY_UOM
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public String getOrderQuantityUom() {
        return orderQuantityUom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUX_ORDER_LINE.ORDER_QUANTITY_UOM
     *
     * @param orderQuantityUom the value for CUX_ORDER_LINE.ORDER_QUANTITY_UOM
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public void setOrderQuantityUom(String orderQuantityUom) {
        this.orderQuantityUom = orderQuantityUom;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUX_ORDER_LINE.UNIT_LIST_PRIC
     *
     * @return the value of CUX_ORDER_LINE.UNIT_LIST_PRIC
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public BigDecimal getUnitListPric() {
        return unitListPric;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUX_ORDER_LINE.UNIT_LIST_PRIC
     *
     * @param unitListPric the value for CUX_ORDER_LINE.UNIT_LIST_PRIC
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public void setUnitListPric(BigDecimal unitListPric) {
        this.unitListPric = unitListPric;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUX_ORDER_LINE.UNIT_SELLING_PRIC
     *
     * @return the value of CUX_ORDER_LINE.UNIT_SELLING_PRIC
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public BigDecimal getUnitSellingPric() {
        return unitSellingPric;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUX_ORDER_LINE.UNIT_SELLING_PRIC
     *
     * @param unitSellingPric the value for CUX_ORDER_LINE.UNIT_SELLING_PRIC
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public void setUnitSellingPric(BigDecimal unitSellingPric) {
        this.unitSellingPric = unitSellingPric;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUX_ORDER_LINE.BOOKED_FLAG
     *
     * @return the value of CUX_ORDER_LINE.BOOKED_FLAG
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public String getBookedFlag() {
        return bookedFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUX_ORDER_LINE.BOOKED_FLAG
     *
     * @param bookedFlag the value for CUX_ORDER_LINE.BOOKED_FLAG
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public void setBookedFlag(String bookedFlag) {
        this.bookedFlag = bookedFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUX_ORDER_LINE.LINE_REMARKS
     *
     * @return the value of CUX_ORDER_LINE.LINE_REMARKS
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public String getLineRemarks() {
        return lineRemarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUX_ORDER_LINE.LINE_REMARKS
     *
     * @param lineRemarks the value for CUX_ORDER_LINE.LINE_REMARKS
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public void setLineRemarks(String lineRemarks) {
        this.lineRemarks = lineRemarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUX_ORDER_LINE.LINE_SHIP_TO
     *
     * @return the value of CUX_ORDER_LINE.LINE_SHIP_TO
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public String getLineShipTo() {
        return lineShipTo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUX_ORDER_LINE.LINE_SHIP_TO
     *
     * @param lineShipTo the value for CUX_ORDER_LINE.LINE_SHIP_TO
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public void setLineShipTo(String lineShipTo) {
        this.lineShipTo = lineShipTo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column CUX_ORDER_LINE.PROCESS_STATUS
     *
     * @return the value of CUX_ORDER_LINE.PROCESS_STATUS
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public String getProcessStatus() {
        return processStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column CUX_ORDER_LINE.PROCESS_STATUS
     *
     * @param processStatus the value for CUX_ORDER_LINE.PROCESS_STATUS
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUX_ORDER_LINE
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CuxOrderLine other = (CuxOrderLine) that;
        return (this.getCrmOrderNumber() == null ? other.getCrmOrderNumber() == null : this.getCrmOrderNumber().equals(other.getCrmOrderNumber()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getLineNumber() == null ? other.getLineNumber() == null : this.getLineNumber().equals(other.getLineNumber()))
            && (this.getOrderSourceId() == null ? other.getOrderSourceId() == null : this.getOrderSourceId().equals(other.getOrderSourceId()))
            && (this.getInventoryItemId() == null ? other.getInventoryItemId() == null : this.getInventoryItemId().equals(other.getInventoryItemId()))
            && (this.getOrderQuantity() == null ? other.getOrderQuantity() == null : this.getOrderQuantity().equals(other.getOrderQuantity()))
            && (this.getOrderQuantityUom() == null ? other.getOrderQuantityUom() == null : this.getOrderQuantityUom().equals(other.getOrderQuantityUom()))
            && (this.getUnitListPric() == null ? other.getUnitListPric() == null : this.getUnitListPric().equals(other.getUnitListPric()))
            && (this.getUnitSellingPric() == null ? other.getUnitSellingPric() == null : this.getUnitSellingPric().equals(other.getUnitSellingPric()))
            && (this.getBookedFlag() == null ? other.getBookedFlag() == null : this.getBookedFlag().equals(other.getBookedFlag()))
            && (this.getLineRemarks() == null ? other.getLineRemarks() == null : this.getLineRemarks().equals(other.getLineRemarks()))
            && (this.getLineShipTo() == null ? other.getLineShipTo() == null : this.getLineShipTo().equals(other.getLineShipTo()))
            && (this.getProcessStatus() == null ? other.getProcessStatus() == null : this.getProcessStatus().equals(other.getProcessStatus()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CUX_ORDER_LINE
     *
     * @mbggenerated Fri Jan 20 08:32:43 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCrmOrderNumber() == null) ? 0 : getCrmOrderNumber().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getLineNumber() == null) ? 0 : getLineNumber().hashCode());
        result = prime * result + ((getOrderSourceId() == null) ? 0 : getOrderSourceId().hashCode());
        result = prime * result + ((getInventoryItemId() == null) ? 0 : getInventoryItemId().hashCode());
        result = prime * result + ((getOrderQuantity() == null) ? 0 : getOrderQuantity().hashCode());
        result = prime * result + ((getOrderQuantityUom() == null) ? 0 : getOrderQuantityUom().hashCode());
        result = prime * result + ((getUnitListPric() == null) ? 0 : getUnitListPric().hashCode());
        result = prime * result + ((getUnitSellingPric() == null) ? 0 : getUnitSellingPric().hashCode());
        result = prime * result + ((getBookedFlag() == null) ? 0 : getBookedFlag().hashCode());
        result = prime * result + ((getLineRemarks() == null) ? 0 : getLineRemarks().hashCode());
        result = prime * result + ((getLineShipTo() == null) ? 0 : getLineShipTo().hashCode());
        result = prime * result + ((getProcessStatus() == null) ? 0 : getProcessStatus().hashCode());
        return result;
    }
}