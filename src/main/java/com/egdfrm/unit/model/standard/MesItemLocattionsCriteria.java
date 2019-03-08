package com.egdfrm.unit.model.standard;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MesItemLocattionsCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MesItemLocattionsCriteria() {
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

        public Criteria andDescriptionIsNull() {
            addCriterion("DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("DESCRIPTION =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("DESCRIPTION <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("DESCRIPTION >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("DESCRIPTION <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("DESCRIPTION like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("DESCRIPTION not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("DESCRIPTION in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("DESCRIPTION not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("DESCRIPTION between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPTION not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDisableDateIsNull() {
            addCriterion("DISABLE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andDisableDateIsNotNull() {
            addCriterion("DISABLE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andDisableDateEqualTo(Date value) {
            addCriterionForJDBCDate("DISABLE_DATE =", value, "disableDate");
            return (Criteria) this;
        }

        public Criteria andDisableDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("DISABLE_DATE <>", value, "disableDate");
            return (Criteria) this;
        }

        public Criteria andDisableDateGreaterThan(Date value) {
            addCriterionForJDBCDate("DISABLE_DATE >", value, "disableDate");
            return (Criteria) this;
        }

        public Criteria andDisableDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DISABLE_DATE >=", value, "disableDate");
            return (Criteria) this;
        }

        public Criteria andDisableDateLessThan(Date value) {
            addCriterionForJDBCDate("DISABLE_DATE <", value, "disableDate");
            return (Criteria) this;
        }

        public Criteria andDisableDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("DISABLE_DATE <=", value, "disableDate");
            return (Criteria) this;
        }

        public Criteria andDisableDateIn(List<Date> values) {
            addCriterionForJDBCDate("DISABLE_DATE in", values, "disableDate");
            return (Criteria) this;
        }

        public Criteria andDisableDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("DISABLE_DATE not in", values, "disableDate");
            return (Criteria) this;
        }

        public Criteria andDisableDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DISABLE_DATE between", value1, value2, "disableDate");
            return (Criteria) this;
        }

        public Criteria andDisableDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("DISABLE_DATE not between", value1, value2, "disableDate");
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

        public Criteria andLocattionCodeLikeInsensitive(String value) {
            addCriterion("upper(LOCATTION_CODE) like", value.toUpperCase(), "locattionCode");
            return (Criteria) this;
        }

        public Criteria andSubinventoryCodeLikeInsensitive(String value) {
            addCriterion("upper(SUBINVENTORY_CODE) like", value.toUpperCase(), "subinventoryCode");
            return (Criteria) this;
        }

        public Criteria andDescriptionLikeInsensitive(String value) {
            addCriterion("upper(DESCRIPTION) like", value.toUpperCase(), "description");
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