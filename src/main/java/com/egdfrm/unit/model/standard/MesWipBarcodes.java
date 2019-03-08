package com.egdfrm.unit.model.standard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MesWipBarcodes implements Serializable {
    private BigDecimal wipBarcodeId;

    private BigDecimal organizationId;

    private BigDecimal wipEntityId;

    private BigDecimal primaryItemId;

    private String barcodeType;

    private String barcodeText;

    private String subinventoryCode;

    private String locattionCode;

    private BigDecimal startQuantity;

    private BigDecimal onhandQuantity;

    private String packingFlag;

    private String statusCode;

    private Date lastUpdateDate;

    private BigDecimal lastUpdatedBy;

    private Date creationDate;

    private BigDecimal createdBy;

    private String barcodeClob;

    private static final long serialVersionUID = 1L;

    public BigDecimal getWipBarcodeId() {
        return wipBarcodeId;
    }

    public void setWipBarcodeId(BigDecimal wipBarcodeId) {
        this.wipBarcodeId = wipBarcodeId;
    }

    public BigDecimal getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(BigDecimal organizationId) {
        this.organizationId = organizationId;
    }

    public BigDecimal getWipEntityId() {
        return wipEntityId;
    }

    public void setWipEntityId(BigDecimal wipEntityId) {
        this.wipEntityId = wipEntityId;
    }

    public BigDecimal getPrimaryItemId() {
        return primaryItemId;
    }

    public void setPrimaryItemId(BigDecimal primaryItemId) {
        this.primaryItemId = primaryItemId;
    }

    public String getBarcodeType() {
        return barcodeType;
    }

    public void setBarcodeType(String barcodeType) {
        this.barcodeType = barcodeType;
    }

    public String getBarcodeText() {
        return barcodeText;
    }

    public void setBarcodeText(String barcodeText) {
        this.barcodeText = barcodeText;
    }

    public String getSubinventoryCode() {
        return subinventoryCode;
    }

    public void setSubinventoryCode(String subinventoryCode) {
        this.subinventoryCode = subinventoryCode;
    }

    public String getLocattionCode() {
        return locattionCode;
    }

    public void setLocattionCode(String locattionCode) {
        this.locattionCode = locattionCode;
    }

    public BigDecimal getStartQuantity() {
        return startQuantity;
    }

    public void setStartQuantity(BigDecimal startQuantity) {
        this.startQuantity = startQuantity;
    }

    public BigDecimal getOnhandQuantity() {
        return onhandQuantity;
    }

    public void setOnhandQuantity(BigDecimal onhandQuantity) {
        this.onhandQuantity = onhandQuantity;
    }

    public String getPackingFlag() {
        return packingFlag;
    }

    public void setPackingFlag(String packingFlag) {
        this.packingFlag = packingFlag;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public BigDecimal getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(BigDecimal lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BigDecimal createdBy) {
        this.createdBy = createdBy;
    }

    public String getBarcodeClob() {
        return barcodeClob;
    }

    public void setBarcodeClob(String barcodeClob) {
        this.barcodeClob = barcodeClob;
    }

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
        MesWipBarcodes other = (MesWipBarcodes) that;
        return (this.getWipBarcodeId() == null ? other.getWipBarcodeId() == null : this.getWipBarcodeId().equals(other.getWipBarcodeId()))
            && (this.getOrganizationId() == null ? other.getOrganizationId() == null : this.getOrganizationId().equals(other.getOrganizationId()))
            && (this.getWipEntityId() == null ? other.getWipEntityId() == null : this.getWipEntityId().equals(other.getWipEntityId()))
            && (this.getPrimaryItemId() == null ? other.getPrimaryItemId() == null : this.getPrimaryItemId().equals(other.getPrimaryItemId()))
            && (this.getBarcodeType() == null ? other.getBarcodeType() == null : this.getBarcodeType().equals(other.getBarcodeType()))
            && (this.getBarcodeText() == null ? other.getBarcodeText() == null : this.getBarcodeText().equals(other.getBarcodeText()))
            && (this.getSubinventoryCode() == null ? other.getSubinventoryCode() == null : this.getSubinventoryCode().equals(other.getSubinventoryCode()))
            && (this.getLocattionCode() == null ? other.getLocattionCode() == null : this.getLocattionCode().equals(other.getLocattionCode()))
            && (this.getStartQuantity() == null ? other.getStartQuantity() == null : this.getStartQuantity().equals(other.getStartQuantity()))
            && (this.getOnhandQuantity() == null ? other.getOnhandQuantity() == null : this.getOnhandQuantity().equals(other.getOnhandQuantity()))
            && (this.getPackingFlag() == null ? other.getPackingFlag() == null : this.getPackingFlag().equals(other.getPackingFlag()))
            && (this.getStatusCode() == null ? other.getStatusCode() == null : this.getStatusCode().equals(other.getStatusCode()))
            && (this.getLastUpdateDate() == null ? other.getLastUpdateDate() == null : this.getLastUpdateDate().equals(other.getLastUpdateDate()))
            && (this.getLastUpdatedBy() == null ? other.getLastUpdatedBy() == null : this.getLastUpdatedBy().equals(other.getLastUpdatedBy()))
            && (this.getCreationDate() == null ? other.getCreationDate() == null : this.getCreationDate().equals(other.getCreationDate()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()))
            && (this.getBarcodeClob() == null ? other.getBarcodeClob() == null : this.getBarcodeClob().equals(other.getBarcodeClob()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWipBarcodeId() == null) ? 0 : getWipBarcodeId().hashCode());
        result = prime * result + ((getOrganizationId() == null) ? 0 : getOrganizationId().hashCode());
        result = prime * result + ((getWipEntityId() == null) ? 0 : getWipEntityId().hashCode());
        result = prime * result + ((getPrimaryItemId() == null) ? 0 : getPrimaryItemId().hashCode());
        result = prime * result + ((getBarcodeType() == null) ? 0 : getBarcodeType().hashCode());
        result = prime * result + ((getBarcodeText() == null) ? 0 : getBarcodeText().hashCode());
        result = prime * result + ((getSubinventoryCode() == null) ? 0 : getSubinventoryCode().hashCode());
        result = prime * result + ((getLocattionCode() == null) ? 0 : getLocattionCode().hashCode());
        result = prime * result + ((getStartQuantity() == null) ? 0 : getStartQuantity().hashCode());
        result = prime * result + ((getOnhandQuantity() == null) ? 0 : getOnhandQuantity().hashCode());
        result = prime * result + ((getPackingFlag() == null) ? 0 : getPackingFlag().hashCode());
        result = prime * result + ((getStatusCode() == null) ? 0 : getStatusCode().hashCode());
        result = prime * result + ((getLastUpdateDate() == null) ? 0 : getLastUpdateDate().hashCode());
        result = prime * result + ((getLastUpdatedBy() == null) ? 0 : getLastUpdatedBy().hashCode());
        result = prime * result + ((getCreationDate() == null) ? 0 : getCreationDate().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        result = prime * result + ((getBarcodeClob() == null) ? 0 : getBarcodeClob().hashCode());
        return result;
    }
}