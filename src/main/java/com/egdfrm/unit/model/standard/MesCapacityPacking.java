package com.egdfrm.unit.model.standard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MesCapacityPacking implements Serializable {
    private BigDecimal headerId;

    private BigDecimal organizationId;

    private BigDecimal inventoryItemId;

    private BigDecimal bQuantity;

    private BigDecimal sQuantity;

    private Date lastUpdateDate;

    private BigDecimal lastUpdatedBy;

    private Date creationDate;

    private BigDecimal createdBy;

    private static final long serialVersionUID = 1L;

    public BigDecimal getHeaderId() {
        return headerId;
    }

    public void setHeaderId(BigDecimal headerId) {
        this.headerId = headerId;
    }

    public BigDecimal getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(BigDecimal organizationId) {
        this.organizationId = organizationId;
    }

    public BigDecimal getInventoryItemId() {
        return inventoryItemId;
    }

    public void setInventoryItemId(BigDecimal inventoryItemId) {
        this.inventoryItemId = inventoryItemId;
    }

    public BigDecimal getbQuantity() {
        return bQuantity;
    }

    public void setbQuantity(BigDecimal bQuantity) {
        this.bQuantity = bQuantity;
    }

    public BigDecimal getsQuantity() {
        return sQuantity;
    }

    public void setsQuantity(BigDecimal sQuantity) {
        this.sQuantity = sQuantity;
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
        MesCapacityPacking other = (MesCapacityPacking) that;
        return (this.getHeaderId() == null ? other.getHeaderId() == null : this.getHeaderId().equals(other.getHeaderId()))
            && (this.getOrganizationId() == null ? other.getOrganizationId() == null : this.getOrganizationId().equals(other.getOrganizationId()))
            && (this.getInventoryItemId() == null ? other.getInventoryItemId() == null : this.getInventoryItemId().equals(other.getInventoryItemId()))
            && (this.getbQuantity() == null ? other.getbQuantity() == null : this.getbQuantity().equals(other.getbQuantity()))
            && (this.getsQuantity() == null ? other.getsQuantity() == null : this.getsQuantity().equals(other.getsQuantity()))
            && (this.getLastUpdateDate() == null ? other.getLastUpdateDate() == null : this.getLastUpdateDate().equals(other.getLastUpdateDate()))
            && (this.getLastUpdatedBy() == null ? other.getLastUpdatedBy() == null : this.getLastUpdatedBy().equals(other.getLastUpdatedBy()))
            && (this.getCreationDate() == null ? other.getCreationDate() == null : this.getCreationDate().equals(other.getCreationDate()))
            && (this.getCreatedBy() == null ? other.getCreatedBy() == null : this.getCreatedBy().equals(other.getCreatedBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getHeaderId() == null) ? 0 : getHeaderId().hashCode());
        result = prime * result + ((getOrganizationId() == null) ? 0 : getOrganizationId().hashCode());
        result = prime * result + ((getInventoryItemId() == null) ? 0 : getInventoryItemId().hashCode());
        result = prime * result + ((getbQuantity() == null) ? 0 : getbQuantity().hashCode());
        result = prime * result + ((getsQuantity() == null) ? 0 : getsQuantity().hashCode());
        result = prime * result + ((getLastUpdateDate() == null) ? 0 : getLastUpdateDate().hashCode());
        result = prime * result + ((getLastUpdatedBy() == null) ? 0 : getLastUpdatedBy().hashCode());
        result = prime * result + ((getCreationDate() == null) ? 0 : getCreationDate().hashCode());
        result = prime * result + ((getCreatedBy() == null) ? 0 : getCreatedBy().hashCode());
        return result;
    }

	@Override
	public String toString() {
		return "MesCapacityPacking [headerId=" + headerId + ", organizationId="
				+ organizationId + ", inventoryItemId=" + inventoryItemId
				+ ", bQuantity=" + bQuantity + ", sQuantity=" + sQuantity
				+ ", lastUpdateDate=" + lastUpdateDate + ", lastUpdatedBy="
				+ lastUpdatedBy + ", creationDate=" + creationDate
				+ ", createdBy=" + createdBy + "]";
	}
}