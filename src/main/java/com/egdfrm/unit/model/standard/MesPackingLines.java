package com.egdfrm.unit.model.standard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MesPackingLines implements Serializable {
    private BigDecimal packingBarcodeId;

    private BigDecimal packingLineId;

    private BigDecimal organizationId;

    private BigDecimal lineBarcodeId;

    private BigDecimal wipEntityId;

    private String packingType;

    private BigDecimal quantity;

    private String statusFlag;

    private Date lastUpdateDate;

    private BigDecimal lastUpdatedBy;

    private Date creationDate;

    private BigDecimal createdBy;

    private static final long serialVersionUID = 1L;

    public BigDecimal getPackingBarcodeId() {
        return packingBarcodeId;
    }

    public void setPackingBarcodeId(BigDecimal packingBarcodeId) {
        this.packingBarcodeId = packingBarcodeId;
    }

    public BigDecimal getPackingLineId() {
        return packingLineId;
    }

    public void setPackingLineId(BigDecimal packingLineId) {
        this.packingLineId = packingLineId;
    }

    public BigDecimal getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(BigDecimal organizationId) {
        this.organizationId = organizationId;
    }

    public BigDecimal getLineBarcodeId() {
        return lineBarcodeId;
    }

    public void setLineBarcodeId(BigDecimal lineBarcodeId) {
        this.lineBarcodeId = lineBarcodeId;
    }

    public BigDecimal getWipEntityId() {
        return wipEntityId;
    }

    public void setWipEntityId(BigDecimal wipEntityId) {
        this.wipEntityId = wipEntityId;
    }

    public String getPackingType() {
        return packingType;
    }

    public void setPackingType(String packingType) {
        this.packingType = packingType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(String statusFlag) {
        this.statusFlag = statusFlag;
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
        MesPackingLines other = (MesPackingLines) that;
        return (this.getPackingBarcodeId() == null ? other.getPackingBarcodeId() == null : this.getPackingBarcodeId().equals(other.getPackingBarcodeId()))
            && (this.getPackingLineId() == null ? other.getPackingLineId() == null : this.getPackingLineId().equals(other.getPackingLineId()))
            && (this.getOrganizationId() == null ? other.getOrganizationId() == null : this.getOrganizationId().equals(other.getOrganizationId()))
            && (this.getLineBarcodeId() == null ? other.getLineBarcodeId() == null : this.getLineBarcodeId().equals(other.getLineBarcodeId()))
            && (this.getWipEntityId() == null ? other.getWipEntityId() == null : this.getWipEntityId().equals(other.getWipEntityId()))
            && (this.getPackingType() == null ? other.getPackingType() == null : this.getPackingType().equals(other.getPackingType()))
            && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
            && (this.getStatusFlag() == null ? other.getStatusFlag() == null : this.getStatusFlag().equals(other.getStatusFlag()))
            && (this.getLastUpdateDate() == null ? other.getLastUpdateDate() == null : this.getLastUpdateDate().equals(other.getLastUpdateDate()))
            && (this.getLastUpdatedBy() == null ? other.getLastUpdatedBy() == null : this.getLastUpdatedBy().equals(other.getLastUpdatedBy()))
            && (this.getCreationDate() == null ? other.getCreationDate() == null : this.getCreationDate().equals(other.getCreationDate()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPackingBarcodeId() == null) ? 0 : getPackingBarcodeId().hashCode());
        result = prime * result + ((getPackingLineId() == null) ? 0 : getPackingLineId().hashCode());
        result = prime * result + ((getOrganizationId() == null) ? 0 : getOrganizationId().hashCode());
        result = prime * result + ((getLineBarcodeId() == null) ? 0 : getLineBarcodeId().hashCode());
        result = prime * result + ((getWipEntityId() == null) ? 0 : getWipEntityId().hashCode());
        result = prime * result + ((getPackingType() == null) ? 0 : getPackingType().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        result = prime * result + ((getStatusFlag() == null) ? 0 : getStatusFlag().hashCode());
        result = prime * result + ((getLastUpdateDate() == null) ? 0 : getLastUpdateDate().hashCode());
        result = prime * result + ((getLastUpdatedBy() == null) ? 0 : getLastUpdatedBy().hashCode());
        result = prime * result + ((getCreationDate() == null) ? 0 : getCreationDate().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        return result;
    }
}