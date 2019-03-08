package com.egdfrm.unit.model.standard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MesShipBarcodes implements Serializable {
    private BigDecimal shipBarcodeId;

    private BigDecimal organizationId;

    private String barcodeType;

    private String barcodeText;

    private String statusCode;

    private Date lastUpdateDate;

    private BigDecimal lastUpdatedBy;

    private Date creationDate;

    private BigDecimal createdBy;

    private static final long serialVersionUID = 1L;

    public BigDecimal getShipBarcodeId() {
        return shipBarcodeId;
    }

    public void setShipBarcodeId(BigDecimal shipBarcodeId) {
        this.shipBarcodeId = shipBarcodeId;
    }

    public BigDecimal getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(BigDecimal organizationId) {
        this.organizationId = organizationId;
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
        MesShipBarcodes other = (MesShipBarcodes) that;
        return (this.getShipBarcodeId() == null ? other.getShipBarcodeId() == null : this.getShipBarcodeId().equals(other.getShipBarcodeId()))
            && (this.getOrganizationId() == null ? other.getOrganizationId() == null : this.getOrganizationId().equals(other.getOrganizationId()))
            && (this.getBarcodeType() == null ? other.getBarcodeType() == null : this.getBarcodeType().equals(other.getBarcodeType()))
            && (this.getBarcodeText() == null ? other.getBarcodeText() == null : this.getBarcodeText().equals(other.getBarcodeText()))
            && (this.getStatusCode() == null ? other.getStatusCode() == null : this.getStatusCode().equals(other.getStatusCode()))
            && (this.getLastUpdateDate() == null ? other.getLastUpdateDate() == null : this.getLastUpdateDate().equals(other.getLastUpdateDate()))
            && (this.getLastUpdatedBy() == null ? other.getLastUpdatedBy() == null : this.getLastUpdatedBy().equals(other.getLastUpdatedBy()))
            && (this.getCreationDate() == null ? other.getCreationDate() == null : this.getCreationDate().equals(other.getCreationDate()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getShipBarcodeId() == null) ? 0 : getShipBarcodeId().hashCode());
        result = prime * result + ((getOrganizationId() == null) ? 0 : getOrganizationId().hashCode());
        result = prime * result + ((getBarcodeType() == null) ? 0 : getBarcodeType().hashCode());
        result = prime * result + ((getBarcodeText() == null) ? 0 : getBarcodeText().hashCode());
        result = prime * result + ((getStatusCode() == null) ? 0 : getStatusCode().hashCode());
        result = prime * result + ((getLastUpdateDate() == null) ? 0 : getLastUpdateDate().hashCode());
        result = prime * result + ((getLastUpdatedBy() == null) ? 0 : getLastUpdatedBy().hashCode());
        result = prime * result + ((getCreationDate() == null) ? 0 : getCreationDate().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        return result;
    }
}