package com.egdfrm.unit.model.standard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MesTransactionsCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MesTransactionsCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andTransactionIdIsNull() {
            addCriterion("TRANSACTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andTransactionIdIsNotNull() {
            addCriterion("TRANSACTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionIdEqualTo(BigDecimal value) {
            addCriterion("TRANSACTION_ID =", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdNotEqualTo(BigDecimal value) {
            addCriterion("TRANSACTION_ID <>", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdGreaterThan(BigDecimal value) {
            addCriterion("TRANSACTION_ID >", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRANSACTION_ID >=", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdLessThan(BigDecimal value) {
            addCriterion("TRANSACTION_ID <", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRANSACTION_ID <=", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdIn(List<BigDecimal> values) {
            addCriterion("TRANSACTION_ID in", values, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdNotIn(List<BigDecimal> values) {
            addCriterion("TRANSACTION_ID not in", values, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRANSACTION_ID between", value1, value2, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRANSACTION_ID not between", value1, value2, "transactionId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIsNull() {
            addCriterion("ORGANIZATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIsNotNull() {
            addCriterion("ORGANIZATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdEqualTo(BigDecimal value) {
            addCriterion("ORGANIZATION_ID =", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotEqualTo(BigDecimal value) {
            addCriterion("ORGANIZATION_ID <>", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdGreaterThan(BigDecimal value) {
            addCriterion("ORGANIZATION_ID >", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ORGANIZATION_ID >=", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdLessThan(BigDecimal value) {
            addCriterion("ORGANIZATION_ID <", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ORGANIZATION_ID <=", value, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdIn(List<BigDecimal> values) {
            addCriterion("ORGANIZATION_ID in", values, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotIn(List<BigDecimal> values) {
            addCriterion("ORGANIZATION_ID not in", values, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORGANIZATION_ID between", value1, value2, "organizationId");
            return (Criteria) this;
        }

        public Criteria andOrganizationIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ORGANIZATION_ID not between", value1, value2, "organizationId");
            return (Criteria) this;
        }

        public Criteria andTransactionTypeIsNull() {
            addCriterion("TRANSACTION_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTransactionTypeIsNotNull() {
            addCriterion("TRANSACTION_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionTypeEqualTo(String value) {
            addCriterion("TRANSACTION_TYPE =", value, "transactionType");
            return (Criteria) this;
        }

        public Criteria andTransactionTypeNotEqualTo(String value) {
            addCriterion("TRANSACTION_TYPE <>", value, "transactionType");
            return (Criteria) this;
        }

        public Criteria andTransactionTypeGreaterThan(String value) {
            addCriterion("TRANSACTION_TYPE >", value, "transactionType");
            return (Criteria) this;
        }

        public Criteria andTransactionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TRANSACTION_TYPE >=", value, "transactionType");
            return (Criteria) this;
        }

        public Criteria andTransactionTypeLessThan(String value) {
            addCriterion("TRANSACTION_TYPE <", value, "transactionType");
            return (Criteria) this;
        }

        public Criteria andTransactionTypeLessThanOrEqualTo(String value) {
            addCriterion("TRANSACTION_TYPE <=", value, "transactionType");
            return (Criteria) this;
        }

        public Criteria andTransactionTypeLike(String value) {
            addCriterion("TRANSACTION_TYPE like", value, "transactionType");
            return (Criteria) this;
        }

        public Criteria andTransactionTypeNotLike(String value) {
            addCriterion("TRANSACTION_TYPE not like", value, "transactionType");
            return (Criteria) this;
        }

        public Criteria andTransactionTypeIn(List<String> values) {
            addCriterion("TRANSACTION_TYPE in", values, "transactionType");
            return (Criteria) this;
        }

        public Criteria andTransactionTypeNotIn(List<String> values) {
            addCriterion("TRANSACTION_TYPE not in", values, "transactionType");
            return (Criteria) this;
        }

        public Criteria andTransactionTypeBetween(String value1, String value2) {
            addCriterion("TRANSACTION_TYPE between", value1, value2, "transactionType");
            return (Criteria) this;
        }

        public Criteria andTransactionTypeNotBetween(String value1, String value2) {
            addCriterion("TRANSACTION_TYPE not between", value1, value2, "transactionType");
            return (Criteria) this;
        }

        public Criteria andSourceIdIsNull() {
            addCriterion("SOURCE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSourceIdIsNotNull() {
            addCriterion("SOURCE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSourceIdEqualTo(BigDecimal value) {
            addCriterion("SOURCE_ID =", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdNotEqualTo(BigDecimal value) {
            addCriterion("SOURCE_ID <>", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdGreaterThan(BigDecimal value) {
            addCriterion("SOURCE_ID >", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SOURCE_ID >=", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdLessThan(BigDecimal value) {
            addCriterion("SOURCE_ID <", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SOURCE_ID <=", value, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdIn(List<BigDecimal> values) {
            addCriterion("SOURCE_ID in", values, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdNotIn(List<BigDecimal> values) {
            addCriterion("SOURCE_ID not in", values, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SOURCE_ID between", value1, value2, "sourceId");
            return (Criteria) this;
        }

        public Criteria andSourceIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SOURCE_ID not between", value1, value2, "sourceId");
            return (Criteria) this;
        }

        public Criteria andPackingTypeIsNull() {
            addCriterion("PACKING_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPackingTypeIsNotNull() {
            addCriterion("PACKING_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPackingTypeEqualTo(String value) {
            addCriterion("PACKING_TYPE =", value, "packingType");
            return (Criteria) this;
        }

        public Criteria andPackingTypeNotEqualTo(String value) {
            addCriterion("PACKING_TYPE <>", value, "packingType");
            return (Criteria) this;
        }

        public Criteria andPackingTypeGreaterThan(String value) {
            addCriterion("PACKING_TYPE >", value, "packingType");
            return (Criteria) this;
        }

        public Criteria andPackingTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PACKING_TYPE >=", value, "packingType");
            return (Criteria) this;
        }

        public Criteria andPackingTypeLessThan(String value) {
            addCriterion("PACKING_TYPE <", value, "packingType");
            return (Criteria) this;
        }

        public Criteria andPackingTypeLessThanOrEqualTo(String value) {
            addCriterion("PACKING_TYPE <=", value, "packingType");
            return (Criteria) this;
        }

        public Criteria andPackingTypeLike(String value) {
            addCriterion("PACKING_TYPE like", value, "packingType");
            return (Criteria) this;
        }

        public Criteria andPackingTypeNotLike(String value) {
            addCriterion("PACKING_TYPE not like", value, "packingType");
            return (Criteria) this;
        }

        public Criteria andPackingTypeIn(List<String> values) {
            addCriterion("PACKING_TYPE in", values, "packingType");
            return (Criteria) this;
        }

        public Criteria andPackingTypeNotIn(List<String> values) {
            addCriterion("PACKING_TYPE not in", values, "packingType");
            return (Criteria) this;
        }

        public Criteria andPackingTypeBetween(String value1, String value2) {
            addCriterion("PACKING_TYPE between", value1, value2, "packingType");
            return (Criteria) this;
        }

        public Criteria andPackingTypeNotBetween(String value1, String value2) {
            addCriterion("PACKING_TYPE not between", value1, value2, "packingType");
            return (Criteria) this;
        }

        public Criteria andPackingBarcodeIdIsNull() {
            addCriterion("PACKING_BARCODE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPackingBarcodeIdIsNotNull() {
            addCriterion("PACKING_BARCODE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPackingBarcodeIdEqualTo(BigDecimal value) {
            addCriterion("PACKING_BARCODE_ID =", value, "packingBarcodeId");
            return (Criteria) this;
        }

        public Criteria andPackingBarcodeIdNotEqualTo(BigDecimal value) {
            addCriterion("PACKING_BARCODE_ID <>", value, "packingBarcodeId");
            return (Criteria) this;
        }

        public Criteria andPackingBarcodeIdGreaterThan(BigDecimal value) {
            addCriterion("PACKING_BARCODE_ID >", value, "packingBarcodeId");
            return (Criteria) this;
        }

        public Criteria andPackingBarcodeIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PACKING_BARCODE_ID >=", value, "packingBarcodeId");
            return (Criteria) this;
        }

        public Criteria andPackingBarcodeIdLessThan(BigDecimal value) {
            addCriterion("PACKING_BARCODE_ID <", value, "packingBarcodeId");
            return (Criteria) this;
        }

        public Criteria andPackingBarcodeIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PACKING_BARCODE_ID <=", value, "packingBarcodeId");
            return (Criteria) this;
        }

        public Criteria andPackingBarcodeIdIn(List<BigDecimal> values) {
            addCriterion("PACKING_BARCODE_ID in", values, "packingBarcodeId");
            return (Criteria) this;
        }

        public Criteria andPackingBarcodeIdNotIn(List<BigDecimal> values) {
            addCriterion("PACKING_BARCODE_ID not in", values, "packingBarcodeId");
            return (Criteria) this;
        }

        public Criteria andPackingBarcodeIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PACKING_BARCODE_ID between", value1, value2, "packingBarcodeId");
            return (Criteria) this;
        }

        public Criteria andPackingBarcodeIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PACKING_BARCODE_ID not between", value1, value2, "packingBarcodeId");
            return (Criteria) this;
        }

        public Criteria andBarcodeTextIsNull() {
            addCriterion("BARCODE_TEXT is null");
            return (Criteria) this;
        }

        public Criteria andBarcodeTextIsNotNull() {
            addCriterion("BARCODE_TEXT is not null");
            return (Criteria) this;
        }

        public Criteria andBarcodeTextEqualTo(String value) {
            addCriterion("BARCODE_TEXT =", value, "barcodeText");
            return (Criteria) this;
        }

        public Criteria andBarcodeTextNotEqualTo(String value) {
            addCriterion("BARCODE_TEXT <>", value, "barcodeText");
            return (Criteria) this;
        }

        public Criteria andBarcodeTextGreaterThan(String value) {
            addCriterion("BARCODE_TEXT >", value, "barcodeText");
            return (Criteria) this;
        }

        public Criteria andBarcodeTextGreaterThanOrEqualTo(String value) {
            addCriterion("BARCODE_TEXT >=", value, "barcodeText");
            return (Criteria) this;
        }

        public Criteria andBarcodeTextLessThan(String value) {
            addCriterion("BARCODE_TEXT <", value, "barcodeText");
            return (Criteria) this;
        }

        public Criteria andBarcodeTextLessThanOrEqualTo(String value) {
            addCriterion("BARCODE_TEXT <=", value, "barcodeText");
            return (Criteria) this;
        }

        public Criteria andBarcodeTextLike(String value) {
            addCriterion("BARCODE_TEXT like", value, "barcodeText");
            return (Criteria) this;
        }

        public Criteria andBarcodeTextNotLike(String value) {
            addCriterion("BARCODE_TEXT not like", value, "barcodeText");
            return (Criteria) this;
        }

        public Criteria andBarcodeTextIn(List<String> values) {
            addCriterion("BARCODE_TEXT in", values, "barcodeText");
            return (Criteria) this;
        }

        public Criteria andBarcodeTextNotIn(List<String> values) {
            addCriterion("BARCODE_TEXT not in", values, "barcodeText");
            return (Criteria) this;
        }

        public Criteria andBarcodeTextBetween(String value1, String value2) {
            addCriterion("BARCODE_TEXT between", value1, value2, "barcodeText");
            return (Criteria) this;
        }

        public Criteria andBarcodeTextNotBetween(String value1, String value2) {
            addCriterion("BARCODE_TEXT not between", value1, value2, "barcodeText");
            return (Criteria) this;
        }

        public Criteria andInventoryItemIdIsNull() {
            addCriterion("INVENTORY_ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andInventoryItemIdIsNotNull() {
            addCriterion("INVENTORY_ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInventoryItemIdEqualTo(BigDecimal value) {
            addCriterion("INVENTORY_ITEM_ID =", value, "inventoryItemId");
            return (Criteria) this;
        }

        public Criteria andInventoryItemIdNotEqualTo(BigDecimal value) {
            addCriterion("INVENTORY_ITEM_ID <>", value, "inventoryItemId");
            return (Criteria) this;
        }

        public Criteria andInventoryItemIdGreaterThan(BigDecimal value) {
            addCriterion("INVENTORY_ITEM_ID >", value, "inventoryItemId");
            return (Criteria) this;
        }

        public Criteria andInventoryItemIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("INVENTORY_ITEM_ID >=", value, "inventoryItemId");
            return (Criteria) this;
        }

        public Criteria andInventoryItemIdLessThan(BigDecimal value) {
            addCriterion("INVENTORY_ITEM_ID <", value, "inventoryItemId");
            return (Criteria) this;
        }

        public Criteria andInventoryItemIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("INVENTORY_ITEM_ID <=", value, "inventoryItemId");
            return (Criteria) this;
        }

        public Criteria andInventoryItemIdIn(List<BigDecimal> values) {
            addCriterion("INVENTORY_ITEM_ID in", values, "inventoryItemId");
            return (Criteria) this;
        }

        public Criteria andInventoryItemIdNotIn(List<BigDecimal> values) {
            addCriterion("INVENTORY_ITEM_ID not in", values, "inventoryItemId");
            return (Criteria) this;
        }

        public Criteria andInventoryItemIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("INVENTORY_ITEM_ID between", value1, value2, "inventoryItemId");
            return (Criteria) this;
        }

        public Criteria andInventoryItemIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("INVENTORY_ITEM_ID not between", value1, value2, "inventoryItemId");
            return (Criteria) this;
        }

        public Criteria andSubinventoryCodeIsNull() {
            addCriterion("SUBINVENTORY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSubinventoryCodeIsNotNull() {
            addCriterion("SUBINVENTORY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSubinventoryCodeEqualTo(String value) {
            addCriterion("SUBINVENTORY_CODE =", value, "subinventoryCode");
            return (Criteria) this;
        }

        public Criteria andSubinventoryCodeNotEqualTo(String value) {
            addCriterion("SUBINVENTORY_CODE <>", value, "subinventoryCode");
            return (Criteria) this;
        }

        public Criteria andSubinventoryCodeGreaterThan(String value) {
            addCriterion("SUBINVENTORY_CODE >", value, "subinventoryCode");
            return (Criteria) this;
        }

        public Criteria andSubinventoryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SUBINVENTORY_CODE >=", value, "subinventoryCode");
            return (Criteria) this;
        }

        public Criteria andSubinventoryCodeLessThan(String value) {
            addCriterion("SUBINVENTORY_CODE <", value, "subinventoryCode");
            return (Criteria) this;
        }

        public Criteria andSubinventoryCodeLessThanOrEqualTo(String value) {
            addCriterion("SUBINVENTORY_CODE <=", value, "subinventoryCode");
            return (Criteria) this;
        }

        public Criteria andSubinventoryCodeLike(String value) {
            addCriterion("SUBINVENTORY_CODE like", value, "subinventoryCode");
            return (Criteria) this;
        }

        public Criteria andSubinventoryCodeNotLike(String value) {
            addCriterion("SUBINVENTORY_CODE not like", value, "subinventoryCode");
            return (Criteria) this;
        }

        public Criteria andSubinventoryCodeIn(List<String> values) {
            addCriterion("SUBINVENTORY_CODE in", values, "subinventoryCode");
            return (Criteria) this;
        }

        public Criteria andSubinventoryCodeNotIn(List<String> values) {
            addCriterion("SUBINVENTORY_CODE not in", values, "subinventoryCode");
            return (Criteria) this;
        }

        public Criteria andSubinventoryCodeBetween(String value1, String value2) {
            addCriterion("SUBINVENTORY_CODE between", value1, value2, "subinventoryCode");
            return (Criteria) this;
        }

        public Criteria andSubinventoryCodeNotBetween(String value1, String value2) {
            addCriterion("SUBINVENTORY_CODE not between", value1, value2, "subinventoryCode");
            return (Criteria) this;
        }

        public Criteria andLocattionCodeIsNull() {
            addCriterion("LOCATTION_CODE is null");
            return (Criteria) this;
        }

        public Criteria andLocattionCodeIsNotNull() {
            addCriterion("LOCATTION_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andLocattionCodeEqualTo(String value) {
            addCriterion("LOCATTION_CODE =", value, "locattionCode");
            return (Criteria) this;
        }

        public Criteria andLocattionCodeNotEqualTo(String value) {
            addCriterion("LOCATTION_CODE <>", value, "locattionCode");
            return (Criteria) this;
        }

        public Criteria andLocattionCodeGreaterThan(String value) {
            addCriterion("LOCATTION_CODE >", value, "locattionCode");
            return (Criteria) this;
        }

        public Criteria andLocattionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("LOCATTION_CODE >=", value, "locattionCode");
            return (Criteria) this;
        }

        public Criteria andLocattionCodeLessThan(String value) {
            addCriterion("LOCATTION_CODE <", value, "locattionCode");
            return (Criteria) this;
        }

        public Criteria andLocattionCodeLessThanOrEqualTo(String value) {
            addCriterion("LOCATTION_CODE <=", value, "locattionCode");
            return (Criteria) this;
        }

        public Criteria andLocattionCodeLike(String value) {
            addCriterion("LOCATTION_CODE like", value, "locattionCode");
            return (Criteria) this;
        }

        public Criteria andLocattionCodeNotLike(String value) {
            addCriterion("LOCATTION_CODE not like", value, "locattionCode");
            return (Criteria) this;
        }

        public Criteria andLocattionCodeIn(List<String> values) {
            addCriterion("LOCATTION_CODE in", values, "locattionCode");
            return (Criteria) this;
        }

        public Criteria andLocattionCodeNotIn(List<String> values) {
            addCriterion("LOCATTION_CODE not in", values, "locattionCode");
            return (Criteria) this;
        }

        public Criteria andLocattionCodeBetween(String value1, String value2) {
            addCriterion("LOCATTION_CODE between", value1, value2, "locattionCode");
            return (Criteria) this;
        }

        public Criteria andLocattionCodeNotBetween(String value1, String value2) {
            addCriterion("LOCATTION_CODE not between", value1, value2, "locattionCode");
            return (Criteria) this;
        }

        public Criteria andTransferSubinventoryIsNull() {
            addCriterion("TRANSFER_SUBINVENTORY is null");
            return (Criteria) this;
        }

        public Criteria andTransferSubinventoryIsNotNull() {
            addCriterion("TRANSFER_SUBINVENTORY is not null");
            return (Criteria) this;
        }

        public Criteria andTransferSubinventoryEqualTo(String value) {
            addCriterion("TRANSFER_SUBINVENTORY =", value, "transferSubinventory");
            return (Criteria) this;
        }

        public Criteria andTransferSubinventoryNotEqualTo(String value) {
            addCriterion("TRANSFER_SUBINVENTORY <>", value, "transferSubinventory");
            return (Criteria) this;
        }

        public Criteria andTransferSubinventoryGreaterThan(String value) {
            addCriterion("TRANSFER_SUBINVENTORY >", value, "transferSubinventory");
            return (Criteria) this;
        }

        public Criteria andTransferSubinventoryGreaterThanOrEqualTo(String value) {
            addCriterion("TRANSFER_SUBINVENTORY >=", value, "transferSubinventory");
            return (Criteria) this;
        }

        public Criteria andTransferSubinventoryLessThan(String value) {
            addCriterion("TRANSFER_SUBINVENTORY <", value, "transferSubinventory");
            return (Criteria) this;
        }

        public Criteria andTransferSubinventoryLessThanOrEqualTo(String value) {
            addCriterion("TRANSFER_SUBINVENTORY <=", value, "transferSubinventory");
            return (Criteria) this;
        }

        public Criteria andTransferSubinventoryLike(String value) {
            addCriterion("TRANSFER_SUBINVENTORY like", value, "transferSubinventory");
            return (Criteria) this;
        }

        public Criteria andTransferSubinventoryNotLike(String value) {
            addCriterion("TRANSFER_SUBINVENTORY not like", value, "transferSubinventory");
            return (Criteria) this;
        }

        public Criteria andTransferSubinventoryIn(List<String> values) {
            addCriterion("TRANSFER_SUBINVENTORY in", values, "transferSubinventory");
            return (Criteria) this;
        }

        public Criteria andTransferSubinventoryNotIn(List<String> values) {
            addCriterion("TRANSFER_SUBINVENTORY not in", values, "transferSubinventory");
            return (Criteria) this;
        }

        public Criteria andTransferSubinventoryBetween(String value1, String value2) {
            addCriterion("TRANSFER_SUBINVENTORY between", value1, value2, "transferSubinventory");
            return (Criteria) this;
        }

        public Criteria andTransferSubinventoryNotBetween(String value1, String value2) {
            addCriterion("TRANSFER_SUBINVENTORY not between", value1, value2, "transferSubinventory");
            return (Criteria) this;
        }

        public Criteria andTransferOrganizationIdIsNull() {
            addCriterion("TRANSFER_ORGANIZATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andTransferOrganizationIdIsNotNull() {
            addCriterion("TRANSFER_ORGANIZATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTransferOrganizationIdEqualTo(BigDecimal value) {
            addCriterion("TRANSFER_ORGANIZATION_ID =", value, "transferOrganizationId");
            return (Criteria) this;
        }

        public Criteria andTransferOrganizationIdNotEqualTo(BigDecimal value) {
            addCriterion("TRANSFER_ORGANIZATION_ID <>", value, "transferOrganizationId");
            return (Criteria) this;
        }

        public Criteria andTransferOrganizationIdGreaterThan(BigDecimal value) {
            addCriterion("TRANSFER_ORGANIZATION_ID >", value, "transferOrganizationId");
            return (Criteria) this;
        }

        public Criteria andTransferOrganizationIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRANSFER_ORGANIZATION_ID >=", value, "transferOrganizationId");
            return (Criteria) this;
        }

        public Criteria andTransferOrganizationIdLessThan(BigDecimal value) {
            addCriterion("TRANSFER_ORGANIZATION_ID <", value, "transferOrganizationId");
            return (Criteria) this;
        }

        public Criteria andTransferOrganizationIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRANSFER_ORGANIZATION_ID <=", value, "transferOrganizationId");
            return (Criteria) this;
        }

        public Criteria andTransferOrganizationIdIn(List<BigDecimal> values) {
            addCriterion("TRANSFER_ORGANIZATION_ID in", values, "transferOrganizationId");
            return (Criteria) this;
        }

        public Criteria andTransferOrganizationIdNotIn(List<BigDecimal> values) {
            addCriterion("TRANSFER_ORGANIZATION_ID not in", values, "transferOrganizationId");
            return (Criteria) this;
        }

        public Criteria andTransferOrganizationIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRANSFER_ORGANIZATION_ID between", value1, value2, "transferOrganizationId");
            return (Criteria) this;
        }

        public Criteria andTransferOrganizationIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRANSFER_ORGANIZATION_ID not between", value1, value2, "transferOrganizationId");
            return (Criteria) this;
        }

        public Criteria andTransferLocatorIsNull() {
            addCriterion("TRANSFER_LOCATOR is null");
            return (Criteria) this;
        }

        public Criteria andTransferLocatorIsNotNull() {
            addCriterion("TRANSFER_LOCATOR is not null");
            return (Criteria) this;
        }

        public Criteria andTransferLocatorEqualTo(String value) {
            addCriterion("TRANSFER_LOCATOR =", value, "transferLocator");
            return (Criteria) this;
        }

        public Criteria andTransferLocatorNotEqualTo(String value) {
            addCriterion("TRANSFER_LOCATOR <>", value, "transferLocator");
            return (Criteria) this;
        }

        public Criteria andTransferLocatorGreaterThan(String value) {
            addCriterion("TRANSFER_LOCATOR >", value, "transferLocator");
            return (Criteria) this;
        }

        public Criteria andTransferLocatorGreaterThanOrEqualTo(String value) {
            addCriterion("TRANSFER_LOCATOR >=", value, "transferLocator");
            return (Criteria) this;
        }

        public Criteria andTransferLocatorLessThan(String value) {
            addCriterion("TRANSFER_LOCATOR <", value, "transferLocator");
            return (Criteria) this;
        }

        public Criteria andTransferLocatorLessThanOrEqualTo(String value) {
            addCriterion("TRANSFER_LOCATOR <=", value, "transferLocator");
            return (Criteria) this;
        }

        public Criteria andTransferLocatorLike(String value) {
            addCriterion("TRANSFER_LOCATOR like", value, "transferLocator");
            return (Criteria) this;
        }

        public Criteria andTransferLocatorNotLike(String value) {
            addCriterion("TRANSFER_LOCATOR not like", value, "transferLocator");
            return (Criteria) this;
        }

        public Criteria andTransferLocatorIn(List<String> values) {
            addCriterion("TRANSFER_LOCATOR in", values, "transferLocator");
            return (Criteria) this;
        }

        public Criteria andTransferLocatorNotIn(List<String> values) {
            addCriterion("TRANSFER_LOCATOR not in", values, "transferLocator");
            return (Criteria) this;
        }

        public Criteria andTransferLocatorBetween(String value1, String value2) {
            addCriterion("TRANSFER_LOCATOR between", value1, value2, "transferLocator");
            return (Criteria) this;
        }

        public Criteria andTransferLocatorNotBetween(String value1, String value2) {
            addCriterion("TRANSFER_LOCATOR not between", value1, value2, "transferLocator");
            return (Criteria) this;
        }

        public Criteria andTransactionDateIsNull() {
            addCriterion("TRANSACTION_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTransactionDateIsNotNull() {
            addCriterion("TRANSACTION_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionDateEqualTo(Date value) {
            addCriterionForJDBCDate("TRANSACTION_DATE =", value, "transactionDate");
            return (Criteria) this;
        }

        public Criteria andTransactionDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("TRANSACTION_DATE <>", value, "transactionDate");
            return (Criteria) this;
        }

        public Criteria andTransactionDateGreaterThan(Date value) {
            addCriterionForJDBCDate("TRANSACTION_DATE >", value, "transactionDate");
            return (Criteria) this;
        }

        public Criteria andTransactionDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("TRANSACTION_DATE >=", value, "transactionDate");
            return (Criteria) this;
        }

        public Criteria andTransactionDateLessThan(Date value) {
            addCriterionForJDBCDate("TRANSACTION_DATE <", value, "transactionDate");
            return (Criteria) this;
        }

        public Criteria andTransactionDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("TRANSACTION_DATE <=", value, "transactionDate");
            return (Criteria) this;
        }

        public Criteria andTransactionDateIn(List<Date> values) {
            addCriterionForJDBCDate("TRANSACTION_DATE in", values, "transactionDate");
            return (Criteria) this;
        }

        public Criteria andTransactionDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("TRANSACTION_DATE not in", values, "transactionDate");
            return (Criteria) this;
        }

        public Criteria andTransactionDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("TRANSACTION_DATE between", value1, value2, "transactionDate");
            return (Criteria) this;
        }

        public Criteria andTransactionDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("TRANSACTION_DATE not between", value1, value2, "transactionDate");
            return (Criteria) this;
        }

        public Criteria andTransactionQuantityIsNull() {
            addCriterion("TRANSACTION_QUANTITY is null");
            return (Criteria) this;
        }

        public Criteria andTransactionQuantityIsNotNull() {
            addCriterion("TRANSACTION_QUANTITY is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionQuantityEqualTo(BigDecimal value) {
            addCriterion("TRANSACTION_QUANTITY =", value, "transactionQuantity");
            return (Criteria) this;
        }

        public Criteria andTransactionQuantityNotEqualTo(BigDecimal value) {
            addCriterion("TRANSACTION_QUANTITY <>", value, "transactionQuantity");
            return (Criteria) this;
        }

        public Criteria andTransactionQuantityGreaterThan(BigDecimal value) {
            addCriterion("TRANSACTION_QUANTITY >", value, "transactionQuantity");
            return (Criteria) this;
        }

        public Criteria andTransactionQuantityGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("TRANSACTION_QUANTITY >=", value, "transactionQuantity");
            return (Criteria) this;
        }

        public Criteria andTransactionQuantityLessThan(BigDecimal value) {
            addCriterion("TRANSACTION_QUANTITY <", value, "transactionQuantity");
            return (Criteria) this;
        }

        public Criteria andTransactionQuantityLessThanOrEqualTo(BigDecimal value) {
            addCriterion("TRANSACTION_QUANTITY <=", value, "transactionQuantity");
            return (Criteria) this;
        }

        public Criteria andTransactionQuantityIn(List<BigDecimal> values) {
            addCriterion("TRANSACTION_QUANTITY in", values, "transactionQuantity");
            return (Criteria) this;
        }

        public Criteria andTransactionQuantityNotIn(List<BigDecimal> values) {
            addCriterion("TRANSACTION_QUANTITY not in", values, "transactionQuantity");
            return (Criteria) this;
        }

        public Criteria andTransactionQuantityBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRANSACTION_QUANTITY between", value1, value2, "transactionQuantity");
            return (Criteria) this;
        }

        public Criteria andTransactionQuantityNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("TRANSACTION_QUANTITY not between", value1, value2, "transactionQuantity");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateIsNull() {
            addCriterion("LAST_UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateIsNotNull() {
            addCriterion("LAST_UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_UPDATE_DATE =", value, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_UPDATE_DATE <>", value, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateGreaterThan(Date value) {
            addCriterionForJDBCDate("LAST_UPDATE_DATE >", value, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_UPDATE_DATE >=", value, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateLessThan(Date value) {
            addCriterionForJDBCDate("LAST_UPDATE_DATE <", value, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("LAST_UPDATE_DATE <=", value, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateIn(List<Date> values) {
            addCriterionForJDBCDate("LAST_UPDATE_DATE in", values, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("LAST_UPDATE_DATE not in", values, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LAST_UPDATE_DATE between", value1, value2, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdateDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("LAST_UPDATE_DATE not between", value1, value2, "lastUpdateDate");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByIsNull() {
            addCriterion("LAST_UPDATED_BY is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByIsNotNull() {
            addCriterion("LAST_UPDATED_BY is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByEqualTo(BigDecimal value) {
            addCriterion("LAST_UPDATED_BY =", value, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByNotEqualTo(BigDecimal value) {
            addCriterion("LAST_UPDATED_BY <>", value, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByGreaterThan(BigDecimal value) {
            addCriterion("LAST_UPDATED_BY >", value, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LAST_UPDATED_BY >=", value, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByLessThan(BigDecimal value) {
            addCriterion("LAST_UPDATED_BY <", value, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LAST_UPDATED_BY <=", value, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByIn(List<BigDecimal> values) {
            addCriterion("LAST_UPDATED_BY in", values, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByNotIn(List<BigDecimal> values) {
            addCriterion("LAST_UPDATED_BY not in", values, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LAST_UPDATED_BY between", value1, value2, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andLastUpdatedByNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LAST_UPDATED_BY not between", value1, value2, "lastUpdatedBy");
            return (Criteria) this;
        }

        public Criteria andCreationDateIsNull() {
            addCriterion("CREATION_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreationDateIsNotNull() {
            addCriterion("CREATION_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreationDateEqualTo(Date value) {
            addCriterionForJDBCDate("CREATION_DATE =", value, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("CREATION_DATE <>", value, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateGreaterThan(Date value) {
            addCriterionForJDBCDate("CREATION_DATE >", value, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATION_DATE >=", value, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateLessThan(Date value) {
            addCriterionForJDBCDate("CREATION_DATE <", value, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("CREATION_DATE <=", value, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateIn(List<Date> values) {
            addCriterionForJDBCDate("CREATION_DATE in", values, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("CREATION_DATE not in", values, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATION_DATE between", value1, value2, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreationDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("CREATION_DATE not between", value1, value2, "creationDate");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNull() {
            addCriterion("CREATED_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreatedByIsNotNull() {
            addCriterion("CREATED_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedByEqualTo(BigDecimal value) {
            addCriterion("CREATED_BY =", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotEqualTo(BigDecimal value) {
            addCriterion("CREATED_BY <>", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThan(BigDecimal value) {
            addCriterion("CREATED_BY >", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("CREATED_BY >=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThan(BigDecimal value) {
            addCriterion("CREATED_BY <", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByLessThanOrEqualTo(BigDecimal value) {
            addCriterion("CREATED_BY <=", value, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByIn(List<BigDecimal> values) {
            addCriterion("CREATED_BY in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotIn(List<BigDecimal> values) {
            addCriterion("CREATED_BY not in", values, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CREATED_BY between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andCreatedByNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("CREATED_BY not between", value1, value2, "createdBy");
            return (Criteria) this;
        }

        public Criteria andTransactionTypeLikeInsensitive(String value) {
            addCriterion("upper(TRANSACTION_TYPE) like", value.toUpperCase(), "transactionType");
            return (Criteria) this;
        }

        public Criteria andPackingTypeLikeInsensitive(String value) {
            addCriterion("upper(PACKING_TYPE) like", value.toUpperCase(), "packingType");
            return (Criteria) this;
        }

        public Criteria andBarcodeTextLikeInsensitive(String value) {
            addCriterion("upper(BARCODE_TEXT) like", value.toUpperCase(), "barcodeText");
            return (Criteria) this;
        }

        public Criteria andSubinventoryCodeLikeInsensitive(String value) {
            addCriterion("upper(SUBINVENTORY_CODE) like", value.toUpperCase(), "subinventoryCode");
            return (Criteria) this;
        }

        public Criteria andLocattionCodeLikeInsensitive(String value) {
            addCriterion("upper(LOCATTION_CODE) like", value.toUpperCase(), "locattionCode");
            return (Criteria) this;
        }

        public Criteria andTransferSubinventoryLikeInsensitive(String value) {
            addCriterion("upper(TRANSFER_SUBINVENTORY) like", value.toUpperCase(), "transferSubinventory");
            return (Criteria) this;
        }

        public Criteria andTransferLocatorLikeInsensitive(String value) {
            addCriterion("upper(TRANSFER_LOCATOR) like", value.toUpperCase(), "transferLocator");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}