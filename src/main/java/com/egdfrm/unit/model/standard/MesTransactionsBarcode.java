package com.egdfrm.unit.model.standard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MesTransactionsBarcode implements Serializable {
    private BigDecimal transactionId;

    private BigDecimal packingBarcodeId;

    private BigDecimal lineBarcodeId;

    private BigDecimal quantity;

    private Date lastUpdateDate;

    private BigDecimal lastUpdatedBy;

    private Date creationDate;

    private BigDecimal createdBy;

    private static final long serialVersionUID = 1L;

    public BigDecimal getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(BigDecimal transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getPackingBarcodeId() {
        return packingBarcodeId;
    }

    public void setPackingBarcodeId(BigDecimal packingBarcodeId) {
        this.packingBarcodeId = packingBarcodeId;
    }

    public BigDecimal getLineBarcodeId() {
        return lineBarcodeId;
    }

    public void setLineBarcodeId(BigDecimal lineBarcodeId) {
        this.lineBarcodeId = lineBarcodeId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
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
        MesTransactionsBarcode other = (MesTransactionsBarcode) that;
        return (this.getTransactionId() == null ? other.getTransactionId() == null : this.getTransactionId().equals(other.getTransactionId()))
            && (this.getPackingBarcodeId() == null ? other.getPackingBarcodeId() == null : this.getPackingBarcodeId().equals(other.getPackingBarcodeId()))
            && (this.getLineBarcodeId() == null ? other.getLineBarcodeId() == null : this.getLineBarcodeId().equals(other.getLineBarcodeId()))
            && (this.getQuantity() == null ? other.getQuantity() == null : this.getQuantity().equals(other.getQuantity()))
            && (this.getLastUpdateDate() == null ? other.getLastUpdateDate() == null : this.getLastUpdateDate().equals(other.getLastUpdateDate()))
            && (this.getLastUpdatedBy() == null ? other.getLastUpdatedBy() == null : this.getLastUpdatedBy().equals(other.getLastUpdatedBy()))
            && (this.getCreationDate() == null ? other.getCreationDate() == null : this.getCreationDate().equals(other.getCreationDate()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTransactionId() == null) ? 0 : getTransactionId().hashCode());
        result = prime * result + ((getPackingBarcodeId() == null) ? 0 : getPackingBarcodeId().hashCode());
        result = prime * result + ((getLineBarcodeId() == null) ? 0 : getLineBarcodeId().hashCode());
        result = prime * result + ((getQuantity() == null) ? 0 : getQuantity().hashCode());
        result = prime * result + ((getLastUpdateDate() == null) ? 0 : getLastUpdateDate().hashCode());
        result = prime * result + ((getLastUpdatedBy() == null) ? 0 : getLastUpdatedBy().hashCode());
        result = prime * result + ((getCreationDate() == null) ? 0 : getCreationDate().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        return result;
    }
}