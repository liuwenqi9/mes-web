package com.egdfrm.unit.model.standard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MesTransactionsBarcodeCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MesTransactionsBarcodeCriteria() {
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

        public Criteria andLineBarcodeIdIsNull() {
            addCriterion("LINE_BARCODE_ID is null");
            return (Criteria) this;
        }

        public Criteria andLineBarcodeIdIsNotNull() {
            addCriterion("LINE_BARCODE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLineBarcodeIdEqualTo(BigDecimal value) {
            addCriterion("LINE_BARCODE_ID =", value, "lineBarcodeId");
            return (Criteria) this;
        }

        public Criteria andLineBarcodeIdNotEqualTo(BigDecimal value) {
            addCriterion("LINE_BARCODE_ID <>", value, "lineBarcodeId");
            return (Criteria) this;
        }

        public Criteria andLineBarcodeIdGreaterThan(BigDecimal value) {
            addCriterion("LINE_BARCODE_ID >", value, "lineBarcodeId");
            return (Criteria) this;
        }

        public Criteria andLineBarcodeIdGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LINE_BARCODE_ID >=", value, "lineBarcodeId");
            return (Criteria) this;
        }

        public Criteria andLineBarcodeIdLessThan(BigDecimal value) {
            addCriterion("LINE_BARCODE_ID <", value, "lineBarcodeId");
            return (Criteria) this;
        }

        public Criteria andLineBarcodeIdLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LINE_BARCODE_ID <=", value, "lineBarcodeId");
            return (Criteria) this;
        }

        public Criteria andLineBarcodeIdIn(List<BigDecimal> values) {
            addCriterion("LINE_BARCODE_ID in", values, "lineBarcodeId");
            return (Criteria) this;
        }

        public Criteria andLineBarcodeIdNotIn(List<BigDecimal> values) {
            addCriterion("LINE_BARCODE_ID not in", values, "lineBarcodeId");
            return (Criteria) this;
        }

        public Criteria andLineBarcodeIdBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LINE_BARCODE_ID between", value1, value2, "lineBarcodeId");
            return (Criteria) this;
        }

        public Criteria andLineBarcodeIdNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LINE_BARCODE_ID not between", value1, value2, "lineBarcodeId");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNull() {
            addCriterion("QUANTITY is null");
            return (Criteria) this;
        }

        public Criteria andQuantityIsNotNull() {
            addCriterion("QUANTITY is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityEqualTo(BigDecimal value) {
            addCriterion("QUANTITY =", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotEqualTo(BigDecimal value) {
            addCriterion("QUANTITY <>", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThan(BigDecimal value) {
            addCriterion("QUANTITY >", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("QUANTITY >=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThan(BigDecimal value) {
            addCriterion("QUANTITY <", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityLessThanOrEqualTo(BigDecimal value) {
            addCriterion("QUANTITY <=", value, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityIn(List<BigDecimal> values) {
            addCriterion("QUANTITY in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotIn(List<BigDecimal> values) {
            addCriterion("QUANTITY not in", values, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("QUANTITY between", value1, value2, "quantity");
            return (Criteria) this;
        }

        public Criteria andQuantityNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("QUANTITY not between", value1, value2, "quantity");
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