package com.egdfrm.unit.model.standard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MesWipBarcodesCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MesWipBarcodesCriteria() {
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

        public Criteria andWipBarcodeIdIsNull() {
            addCriterion("WIP_BARCODE_ID is null");
            return (Criteria) this;
        }

        public Criteria andWipBarcodeIdIsNotNull() {
            addCriterion("WIP_BARCODE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWipBarcodeIdEqualTo(BigDecimal value) {
            addCriterion("WIP_BARCODE_ID =", value, "wipBarcodeId");
            return (Criteria) this;
        }

        public Criteria andWipBarcodeIdNotEqualTo(BigDecimal value) {
            addCriterion("WIP_BARCODE_ID <>", value, "wipBarcodeId");
            return (Criteria) this;
        }

        public Criteria andWipBarcodeIdGreaterThan(BigDecimal value) {
            addCriterion("WIP_BARCODE_ID >", value, "wipBarcodeId");
            return (Criteria) this;
        }

        public Criteria andWipBarcodeIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("WIP_BARCODE_ID >=", value, "wipBarcodeId");
            return (Criteria) this;
        }

        public Criteria andWipBarcodeIdLessThan(BigDecimal value) {
            addCriterion("WIP_BARCODE_ID <", value, "wipBarcodeId");
            return (Criteria) this;
        }

        public Criteria andWipBarcodeIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("WIP_BARCODE_ID <=", value, "wipBarcodeId");
            return (Criteria) this;
        }

        public Criteria andWipBarcodeIdIn(List<BigDecimal> values) {
            addCriterion("WIP_BARCODE_ID in", values, "wipBarcodeId");
            return (Criteria) this;
        }

        public Criteria andWipBarcodeIdNotIn(List<BigDecimal> values) {
            addCriterion("WIP_BARCODE_ID not in", values, "wipBarcodeId");
            return (Criteria) this;
        }

        public Criteria andWipBarcodeIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("WIP_BARCODE_ID between", value1, value2, "wipBarcodeId");
            return (Criteria) this;
        }

        public Criteria andWipBarcodeIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("WIP_BARCODE_ID not between", value1, value2, "wipBarcodeId");
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

        public Criteria andWipEntityIdIsNull() {
            addCriterion("WIP_ENTITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andWipEntityIdIsNotNull() {
            addCriterion("WIP_ENTITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWipEntityIdEqualTo(BigDecimal value) {
            addCriterion("WIP_ENTITY_ID =", value, "wipEntityId");
            return (Criteria) this;
        }

        public Criteria andWipEntityIdNotEqualTo(BigDecimal value) {
            addCriterion("WIP_ENTITY_ID <>", value, "wipEntityId");
            return (Criteria) this;
        }

        public Criteria andWipEntityIdGreaterThan(BigDecimal value) {
            addCriterion("WIP_ENTITY_ID >", value, "wipEntityId");
            return (Criteria) this;
        }

        public Criteria andWipEntityIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("WIP_ENTITY_ID >=", value, "wipEntityId");
            return (Criteria) this;
        }

        public Criteria andWipEntityIdLessThan(BigDecimal value) {
            addCriterion("WIP_ENTITY_ID <", value, "wipEntityId");
            return (Criteria) this;
        }

        public Criteria andWipEntityIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("WIP_ENTITY_ID <=", value, "wipEntityId");
            return (Criteria) this;
        }

        public Criteria andWipEntityIdIn(List<BigDecimal> values) {
            addCriterion("WIP_ENTITY_ID in", values, "wipEntityId");
            return (Criteria) this;
        }

        public Criteria andWipEntityIdNotIn(List<BigDecimal> values) {
            addCriterion("WIP_ENTITY_ID not in", values, "wipEntityId");
            return (Criteria) this;
        }

        public Criteria andWipEntityIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("WIP_ENTITY_ID between", value1, value2, "wipEntityId");
            return (Criteria) this;
        }

        public Criteria andWipEntityIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("WIP_ENTITY_ID not between", value1, value2, "wipEntityId");
            return (Criteria) this;
        }

        public Criteria andPrimaryItemIdIsNull() {
            addCriterion("PRIMARY_ITEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andPrimaryItemIdIsNotNull() {
            addCriterion("PRIMARY_ITEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPrimaryItemIdEqualTo(BigDecimal value) {
            addCriterion("PRIMARY_ITEM_ID =", value, "primaryItemId");
            return (Criteria) this;
        }

        public Criteria andPrimaryItemIdNotEqualTo(BigDecimal value) {
            addCriterion("PRIMARY_ITEM_ID <>", value, "primaryItemId");
            return (Criteria) this;
        }

        public Criteria andPrimaryItemIdGreaterThan(BigDecimal value) {
            addCriterion("PRIMARY_ITEM_ID >", value, "primaryItemId");
            return (Criteria) this;
        }

        public Criteria andPrimaryItemIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PRIMARY_ITEM_ID >=", value, "primaryItemId");
            return (Criteria) this;
        }

        public Criteria andPrimaryItemIdLessThan(BigDecimal value) {
            addCriterion("PRIMARY_ITEM_ID <", value, "primaryItemId");
            return (Criteria) this;
        }

        public Criteria andPrimaryItemIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PRIMARY_ITEM_ID <=", value, "primaryItemId");
            return (Criteria) this;
        }

        public Criteria andPrimaryItemIdIn(List<BigDecimal> values) {
            addCriterion("PRIMARY_ITEM_ID in", values, "primaryItemId");
            return (Criteria) this;
        }

        public Criteria andPrimaryItemIdNotIn(List<BigDecimal> values) {
            addCriterion("PRIMARY_ITEM_ID not in", values, "primaryItemId");
            return (Criteria) this;
        }

        public Criteria andPrimaryItemIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRIMARY_ITEM_ID between", value1, value2, "primaryItemId");
            return (Criteria) this;
        }

        public Criteria andPrimaryItemIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PRIMARY_ITEM_ID not between", value1, value2, "primaryItemId");
            return (Criteria) this;
        }

        public Criteria andBarcodeTypeIsNull() {
            addCriterion("BARCODE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andBarcodeTypeIsNotNull() {
            addCriterion("BARCODE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andBarcodeTypeEqualTo(String value) {
            addCriterion("BARCODE_TYPE =", value, "barcodeType");
            return (Criteria) this;
        }

        public Criteria andBarcodeTypeNotEqualTo(String value) {
            addCriterion("BARCODE_TYPE <>", value, "barcodeType");
            return (Criteria) this;
        }

        public Criteria andBarcodeTypeGreaterThan(String value) {
            addCriterion("BARCODE_TYPE >", value, "barcodeType");
            return (Criteria) this;
        }

        public Criteria andBarcodeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("BARCODE_TYPE >=", value, "barcodeType");
            return (Criteria) this;
        }

        public Criteria andBarcodeTypeLessThan(String value) {
            addCriterion("BARCODE_TYPE <", value, "barcodeType");
            return (Criteria) this;
        }

        public Criteria andBarcodeTypeLessThanOrEqualTo(String value) {
            addCriterion("BARCODE_TYPE <=", value, "barcodeType");
            return (Criteria) this;
        }

        public Criteria andBarcodeTypeLike(String value) {
            addCriterion("BARCODE_TYPE like", value, "barcodeType");
            return (Criteria) this;
        }

        public Criteria andBarcodeTypeNotLike(String value) {
            addCriterion("BARCODE_TYPE not like", value, "barcodeType");
            return (Criteria) this;
        }

        public Criteria andBarcodeTypeIn(List<String> values) {
            addCriterion("BARCODE_TYPE in", values, "barcodeType");
            return (Criteria) this;
        }

        public Criteria andBarcodeTypeNotIn(List<String> values) {
            addCriterion("BARCODE_TYPE not in", values, "barcodeType");
            return (Criteria) this;
        }

        public Criteria andBarcodeTypeBetween(String value1, String value2) {
            addCriterion("BARCODE_TYPE between", value1, value2, "barcodeType");
            return (Criteria) this;
        }

        public Criteria andBarcodeTypeNotBetween(String value1, String value2) {
            addCriterion("BARCODE_TYPE not between", value1, value2, "barcodeType");
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

        public Criteria andStartQuantityIsNull() {
            addCriterion("START_QUANTITY is null");
            return (Criteria) this;
        }

        public Criteria andStartQuantityIsNotNull() {
            addCriterion("START_QUANTITY is not null");
            return (Criteria) this;
        }

        public Criteria andStartQuantityEqualTo(BigDecimal value) {
            addCriterion("START_QUANTITY =", value, "startQuantity");
            return (Criteria) this;
        }

        public Criteria andStartQuantityNotEqualTo(BigDecimal value) {
            addCriterion("START_QUANTITY <>", value, "startQuantity");
            return (Criteria) this;
        }

        public Criteria andStartQuantityGreaterThan(BigDecimal value) {
            addCriterion("START_QUANTITY >", value, "startQuantity");
            return (Criteria) this;
        }

        public Criteria andStartQuantityGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("START_QUANTITY >=", value, "startQuantity");
            return (Criteria) this;
        }

        public Criteria andStartQuantityLessThan(BigDecimal value) {
            addCriterion("START_QUANTITY <", value, "startQuantity");
            return (Criteria) this;
        }

        public Criteria andStartQuantityLessThanOrEqualTo(BigDecimal value) {
            addCriterion("START_QUANTITY <=", value, "startQuantity");
            return (Criteria) this;
        }

        public Criteria andStartQuantityIn(List<BigDecimal> values) {
            addCriterion("START_QUANTITY in", values, "startQuantity");
            return (Criteria) this;
        }

        public Criteria andStartQuantityNotIn(List<BigDecimal> values) {
            addCriterion("START_QUANTITY not in", values, "startQuantity");
            return (Criteria) this;
        }

        public Criteria andStartQuantityBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("START_QUANTITY between", value1, value2, "startQuantity");
            return (Criteria) this;
        }

        public Criteria andStartQuantityNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("START_QUANTITY not between", value1, value2, "startQuantity");
            return (Criteria) this;
        }

        public Criteria andOnhandQuantityIsNull() {
            addCriterion("ONHAND_QUANTITY is null");
            return (Criteria) this;
        }

        public Criteria andOnhandQuantityIsNotNull() {
            addCriterion("ONHAND_QUANTITY is not null");
            return (Criteria) this;
        }

        public Criteria andOnhandQuantityEqualTo(BigDecimal value) {
            addCriterion("ONHAND_QUANTITY =", value, "onhandQuantity");
            return (Criteria) this;
        }

        public Criteria andOnhandQuantityNotEqualTo(BigDecimal value) {
            addCriterion("ONHAND_QUANTITY <>", value, "onhandQuantity");
            return (Criteria) this;
        }

        public Criteria andOnhandQuantityGreaterThan(BigDecimal value) {
            addCriterion("ONHAND_QUANTITY >", value, "onhandQuantity");
            return (Criteria) this;
        }

        public Criteria andOnhandQuantityGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ONHAND_QUANTITY >=", value, "onhandQuantity");
            return (Criteria) this;
        }

        public Criteria andOnhandQuantityLessThan(BigDecimal value) {
            addCriterion("ONHAND_QUANTITY <", value, "onhandQuantity");
            return (Criteria) this;
        }

        public Criteria andOnhandQuantityLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ONHAND_QUANTITY <=", value, "onhandQuantity");
            return (Criteria) this;
        }

        public Criteria andOnhandQuantityIn(List<BigDecimal> values) {
            addCriterion("ONHAND_QUANTITY in", values, "onhandQuantity");
            return (Criteria) this;
        }

        public Criteria andOnhandQuantityNotIn(List<BigDecimal> values) {
            addCriterion("ONHAND_QUANTITY not in", values, "onhandQuantity");
            return (Criteria) this;
        }

        public Criteria andOnhandQuantityBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ONHAND_QUANTITY between", value1, value2, "onhandQuantity");
            return (Criteria) this;
        }

        public Criteria andOnhandQuantityNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ONHAND_QUANTITY not between", value1, value2, "onhandQuantity");
            return (Criteria) this;
        }

        public Criteria andPackingFlagIsNull() {
            addCriterion("PACKING_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andPackingFlagIsNotNull() {
            addCriterion("PACKING_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andPackingFlagEqualTo(String value) {
            addCriterion("PACKING_FLAG =", value, "packingFlag");
            return (Criteria) this;
        }

        public Criteria andPackingFlagNotEqualTo(String value) {
            addCriterion("PACKING_FLAG <>", value, "packingFlag");
            return (Criteria) this;
        }

        public Criteria andPackingFlagGreaterThan(String value) {
            addCriterion("PACKING_FLAG >", value, "packingFlag");
            return (Criteria) this;
        }

        public Criteria andPackingFlagGreaterThanOrEqualTo(String value) {
            addCriterion("PACKING_FLAG >=", value, "packingFlag");
            return (Criteria) this;
        }

        public Criteria andPackingFlagLessThan(String value) {
            addCriterion("PACKING_FLAG <", value, "packingFlag");
            return (Criteria) this;
        }

        public Criteria andPackingFlagLessThanOrEqualTo(String value) {
            addCriterion("PACKING_FLAG <=", value, "packingFlag");
            return (Criteria) this;
        }

        public Criteria andPackingFlagLike(String value) {
            addCriterion("PACKING_FLAG like", value, "packingFlag");
            return (Criteria) this;
        }

        public Criteria andPackingFlagNotLike(String value) {
            addCriterion("PACKING_FLAG not like", value, "packingFlag");
            return (Criteria) this;
        }

        public Criteria andPackingFlagIn(List<String> values) {
            addCriterion("PACKING_FLAG in", values, "packingFlag");
            return (Criteria) this;
        }

        public Criteria andPackingFlagNotIn(List<String> values) {
            addCriterion("PACKING_FLAG not in", values, "packingFlag");
            return (Criteria) this;
        }

        public Criteria andPackingFlagBetween(String value1, String value2) {
            addCriterion("PACKING_FLAG between", value1, value2, "packingFlag");
            return (Criteria) this;
        }

        public Criteria andPackingFlagNotBetween(String value1, String value2) {
            addCriterion("PACKING_FLAG not between", value1, value2, "packingFlag");
            return (Criteria) this;
        }

        public Criteria andStatusCodeIsNull() {
            addCriterion("STATUS_CODE is null");
            return (Criteria) this;
        }

        public Criteria andStatusCodeIsNotNull() {
            addCriterion("STATUS_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andStatusCodeEqualTo(String value) {
            addCriterion("STATUS_CODE =", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeNotEqualTo(String value) {
            addCriterion("STATUS_CODE <>", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeGreaterThan(String value) {
            addCriterion("STATUS_CODE >", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS_CODE >=", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeLessThan(String value) {
            addCriterion("STATUS_CODE <", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeLessThanOrEqualTo(String value) {
            addCriterion("STATUS_CODE <=", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeLike(String value) {
            addCriterion("STATUS_CODE like", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeNotLike(String value) {
            addCriterion("STATUS_CODE not like", value, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeIn(List<String> values) {
            addCriterion("STATUS_CODE in", values, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeNotIn(List<String> values) {
            addCriterion("STATUS_CODE not in", values, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeBetween(String value1, String value2) {
            addCriterion("STATUS_CODE between", value1, value2, "statusCode");
            return (Criteria) this;
        }

        public Criteria andStatusCodeNotBetween(String value1, String value2) {
            addCriterion("STATUS_CODE not between", value1, value2, "statusCode");
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

        public Criteria andBarcodeTypeLikeInsensitive(String value) {
            addCriterion("upper(BARCODE_TYPE) like", value.toUpperCase(), "barcodeType");
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

        public Criteria andPackingFlagLikeInsensitive(String value) {
            addCriterion("upper(PACKING_FLAG) like", value.toUpperCase(), "packingFlag");
            return (Criteria) this;
        }

        public Criteria andStatusCodeLikeInsensitive(String value) {
            addCriterion("upper(STATUS_CODE) like", value.toUpperCase(), "statusCode");
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