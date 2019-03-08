package com.egdfrm.unit.model.standard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MesTransactions implements Serializable {
    private BigDecimal transactionId;

    private BigDecimal organizationId;

    private String transactionType;

    private BigDecimal sourceId;

    private String packingType;

    private BigDecimal packingBarcodeId;

    private String barcodeText;

    private BigDecimal inventoryItemId;

    private String subinventoryCode;

    private String locattionCode;

    private String transferSubinventory;

    private BigDecimal transferOrganizationId;

    private String transferLocator;

    private Date transactionDate;

    private BigDecimal transactionQuantity;

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

    public BigDecimal getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(BigDecimal organizationId) {
        this.organizationId = organizationId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getSourceId() {
        return sourceId;
    }

    public void setSourceId(BigDecimal sourceId) {
        this.sourceId = sourceId;
    }

    public String getPackingType() {
        return packingType;
    }

    public void setPackingType(String packingType) {
        this.packingType = packingType;
    }

    public BigDecimal getPackingBarcodeId() {
        return packingBarcodeId;
    }

    public void setPackingBarcodeId(BigDecimal packingBarcodeId) {
        this.packingBarcodeId = packingBarcodeId;
    }

    public String getBarcodeText() {
        return barcodeText;
    }

    public void setBarcodeText(String barcodeText) {
        this.barcodeText = barcodeText;
    }

    public BigDecimal getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(BigDecimal inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
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

    public String getTransferSubinventory() {
        return transferSubinventory;
    }

    public void setTransferSubinventory(String transferSubinventory) {
        this.transferSubinventory = transferSubinventory;
    }

    public BigDecimal getTransferOrganizationId() {
        return transferOrganizationId;
    }

    public void setTransferOrganizationId(BigDecimal transferOrganizationId) {
        this.transferOrganizationId = transferOrganizationId;
    }

    public String getTransferLocator() {
        return transferLocator;
    }

    public void setTransferLocator(String transferLocator) {
        this.transferLocator = transferLocator;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getTransactionQuantity() {
        return transactionQuantity;
    }

    public void setTransactionQuantity(BigDecimal transactionQuantity) {
        this.transactionQuantity = transactionQuantity;
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
        MesTransactions other = (MesTransactions) that;
        return (this.getTransactionId() == null ? other.getTransactionId() == null : this.getTransactionId().equals(other.getTransactionId()))
            && (this.getOrganizationId() == null ? other.getOrganizationId() == null : this.getOrganizationId().equals(other.getOrganizationId()))
            && (this.getTransactionType() == null ? other.getTransactionType() == null : this.getTransactionType().equals(other.getTransactionType()))
            && (this.getSourceId() == null ? other.getSourceId() == null : this.getSourceId().equals(other.getSourceId()))
            && (this.getPackingType() == null ? other.getPackingType() == null : this.getPackingType().equals(other.getPackingType()))
            && (this.getPackingBarcodeId() == null ? other.getPackingBarcodeId() == null : this.getPackingBarcodeId().equals(other.getPackingBarcodeId()))
            && (this.getBarcodeText() == null ? other.getBarcodeText() == null : this.getBarcodeText().equals(other.getBarcodeText()))
            && (this.getInventoryItemId() == null ? other.getInventoryItemId() == null : this.getInventoryItemId().equals(other.getInventoryItemId()))
            && (this.getSubinventoryCode() == null ? other.getSubinventoryCode() == null : this.getSubinventoryCode().equals(other.getSubinventoryCode()))
            && (this.getLocattionCode() == null ? other.getLocattionCode() == null : this.getLocattionCode().equals(other.getLocattionCode()))
            && (this.getTransferSubinventory() == null ? other.getTransferSubinventory() == null : this.getTransferSubinventory().equals(other.getTransferSubinventory()))
            && (this.getTransferOrganizationId() == null ? other.getTransferOrganizationId() == null : this.getTransferOrganizationId().equals(other.getTransferOrganizationId()))
            && (this.getTransferLocator() == null ? other.getTransferLocator() == null : this.getTransferLocator().equals(other.getTransferLocator()))
            && (this.getTransactionDate() == null ? other.getTransactionDate() == null : this.getTransactionDate().equals(other.getTransactionDate()))
            && (this.getTransactionQuantity() == null ? other.getTransactionQuantity() == null : this.getTransactionQuantity().equals(other.getTransactionQuantity()))
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
        result = prime * result + ((getOrganizationId() == null) ? 0 : getOrganizationId().hashCode());
        result = prime * result + ((getTransactionType() == null) ? 0 : getTransactionType().hashCode());
        result = prime * result + ((getSourceId() == null) ? 0 : getSourceId().hashCode());
        result = prime * result + ((getPackingType() == null) ? 0 : getPackingType().hashCode());
        result = prime * result + ((getPackingBarcodeId() == null) ? 0 : getPackingBarcodeId().hashCode());
        result = prime * result + ((getBarcodeText() == null) ? 0 : getBarcodeText().hashCode());
        result = prime * result + ((getInventoryItemId() == null) ? 0 : getInventoryItemId().hashCode());
        result = prime * result + ((getSubinventoryCode() == null) ? 0 : getSubinventoryCode().hashCode());
        result = prime * result + ((getLocattionCode() == null) ? 0 : getLocattionCode().hashCode());
        result = prime * result + ((getTransferSubinventory() == null) ? 0 : getTransferSubinventory().hashCode());
        result = prime * result + ((getTransferOrganizationId() == null) ? 0 : getTransferOrganizationId().hashCode());
        result = prime * result + ((getTransferLocator() == null) ? 0 : getTransferLocator().hashCode());
        result = prime * result + ((getTransactionDate() == null) ? 0 : getTransactionDate().hashCode());
        result = prime * result + ((getTransactionQuantity() == null) ? 0 : getTransactionQuantity().hashCode());
        result = prime * result + ((getLastUpdateDate() == null) ? 0 : getLastUpdateDate().hashCode());
        result = prime * result + ((getLastUpdatedBy() == null) ? 0 : getLastUpdatedBy().hashCode());
        result = prime * result + ((getCreationDate() == null) ? 0 : getCreationDate().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        return result;
    }
}