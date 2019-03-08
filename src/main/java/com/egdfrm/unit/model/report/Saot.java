package com.egdfrm.unit.model.report;

/**
 * 借机统计报表
 * @author hgb
 * @date 2017-5-12
 */
public class Saot {

    private String startTime;
    private String endTime;
    private String planReturnStartTime;
    private String planReturnEndTime;
    private String state;//状态

    private String jjNumber;//借机单
    private String jjDept;//借机部门
    private String jjPurpose;//借机用途
    private String jjPersion;//借机人
    private String code;//编码
    private String jjDate;//借机时间
    private String model;//型号
    private Integer actualQuantity;//实际数量
    private String barcode;//产品条码
    private String planReturnDate;//计划归还时间
    private String planActualDate;//实际归还时间
    private String evaluation;//参考
    private String describe;//描述
    private String remark;//备注

    
    
    
    public String getPlanReturnStartTime() {
		return planReturnStartTime;
	}

	public void setPlanReturnStartTime(String planReturnStartTime) {
		this.planReturnStartTime = planReturnStartTime;
	}

	public String getPlanReturnEndTime() {
		return planReturnEndTime;
	}

	public void setPlanReturnEndTime(String planReturnEndTime) {
		this.planReturnEndTime = planReturnEndTime;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getJjNumber() {
        return jjNumber;
    }

    public void setJjNumber(String jjNumber) {
        this.jjNumber = jjNumber;
    }

    public String getJjDept() {
        return jjDept;
    }

    public void setJjDept(String jjDept) {
        this.jjDept = jjDept;
    }

    public String getJjPurpose() {
        return jjPurpose;
    }

    public void setJjPurpose(String jjPurpose) {
        this.jjPurpose = jjPurpose;
    }

    public String getJjPersion() {
        return jjPersion;
    }

    public void setJjPersion(String jjPersion) {
        this.jjPersion = jjPersion;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJjDate() {
        return jjDate;
    }

    public void setJjDate(String jjDate) {
        this.jjDate = jjDate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(Integer actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getPlanReturnDate() {
        return planReturnDate;
    }

    public void setPlanReturnDate(String planReturnDate) {
        this.planReturnDate = planReturnDate;
    }

    public String getPlanActualDate() {
        return planActualDate;
    }

    public void setPlanActualDate(String planActualDate) {
        this.planActualDate = planActualDate;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
