package com.egdfrm.unit.model.AfterSaleManagement;

import java.io.Serializable;

/**
 * Created by hgb on 2018/3/29
 * Email xhy18650@sina.com
 **/
public class AfterSalePeople implements Serializable {

    private Integer id;
    private Double flexValue;
    private String repPeople;
    private String activeDate;
    private String createBy;
    private String createDate;
    private String updateBy;
    private String updateDate;
    private String createLoginName;
    private String updateLoginName;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getFlexValue() {
        return flexValue;
    }

    public void setFlexValue(Double flexValue) {
        this.flexValue = flexValue;
    }

    public String getRepPeople() {
        return repPeople;
    }

    public void setRepPeople(String repPeople) {
        this.repPeople = repPeople;
    }

    public String getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(String activeDate) {
        this.activeDate = activeDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateLoginName() {
        return createLoginName;
    }

    public void setCreateLoginName(String createLoginName) {
        this.createLoginName = createLoginName;
    }

    public String getUpdateLoginName() {
        return updateLoginName;
    }

    public void setUpdateLoginName(String updateLoginName) {
        this.updateLoginName = updateLoginName;
    }
}
