package com.egdfrm.unit.model.stock;

import java.util.Date;

/**
 * 借机实体
 * Created by tyq on 17/3/15.
 */
public class SeizeAnOpportunity {

    private int id;
    private Integer line_id;
    private Integer wip_barcode_id;

    private String codeId;//物料ID
    private String number;//借机单号
    private String dept;//借机部门
    private String deptName;//部门名称
    private String person;//借机人员
    private String purpose;//借机用途
    private String typeName;//类型名称
    private String remark;//备注
    private String express;//物料公司
    private String expressNumber;//物料单号
    private int userId;//用户ID
    private int orgId;//组织ID
    private Date updateTime;//修改时间
    private Date createTime;//创建时间
    private String code;//编码
    private String model;//型号
    private String quantity;//申请数量
    private String lendQuantity;//实际数量
    private String productCode;//产品条码
    private String returnTime;//计划归还时间
    private String actualTime;//实际归还时间
    private String sourceSubLibrary;//来源子库
    private String goalSubLibrary;//目的子库
    private String goalLocation;//目的货位
    private String describe;//物料描述
    private String startTime;
    private String endTime;

 
    public int getId() {
        return id;
    }

    public Integer getWip_barcode_id() {
		return wip_barcode_id;
	}

	public void setWip_barcode_id(Integer wip_barcode_id) {
		this.wip_barcode_id = wip_barcode_id;
	}

	public Integer getLine_id() {
		return line_id;
	}

	public void setLine_id(Integer line_id) {
		this.line_id = line_id;
	}

	public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getSourceSubLibrary() {
        return sourceSubLibrary;
    }

    public void setSourceSubLibrary(String sourceSubLibrary) {
        this.sourceSubLibrary = sourceSubLibrary;
    }

    public String getGoalSubLibrary() {
        return goalSubLibrary;
    }

    public void setGoalSubLibrary(String goalSubLibrary) {
        this.goalSubLibrary = goalSubLibrary;
    }

    public String getGoalLocation() {
        return goalLocation;
    }

    public void setGoalLocation(String goalLocation) {
        this.goalLocation = goalLocation;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLendQuantity() {
        return lendQuantity;
    }

    public void setLendQuantity(String lendQuantity) {
        this.lendQuantity = lendQuantity;
    }

    public String getActualTime() {
        return actualTime;
    }

    public void setActualTime(String actualTime) {
        this.actualTime = actualTime;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(String expressNumber) {
        this.expressNumber = expressNumber;
    }
}
