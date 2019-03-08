package com.egdfrm.unit.model.barcodeManagement;

/**
 * 工单生产进度_实体
 * Created by tyq on 17/1/11.
 */
public class WorkOrderProgress {

    private String workOrderNumber;//工单
    private String productionLine;//生产线
    private String parts;//装配件
    private String moCode;//MO单
    private String startTime;//计划开工 开始时间
    private String endTime;//结束时间
    private String MOStartTime;//MO交期 开始时间
    private String MOEndTime;//MO交期 结束时间 
    private String wipStatus;//工单状态

    

	public String getWipStatus() {
		return wipStatus;
	}

	public void setWipStatus(String wipStatus) {
		this.wipStatus = wipStatus;
	}

	public String getMOStartTime() {
		return MOStartTime;
	}

	public void setMOStartTime(String mOStartTime) {
		MOStartTime = mOStartTime;
	}

	public String getMOEndTime() {
		return MOEndTime;
	}

	public void setMOEndTime(String mOEndTime) {
		MOEndTime = mOEndTime;
	}

	public String getWorkOrderNumber() {
        return workOrderNumber;
    }

    public void setWorkOrderNumber(String workOrderNumber) {
        this.workOrderNumber = workOrderNumber;
    }

    public String getProductionLine() {
        return productionLine;
    }

    public void setProductionLine(String productionLine) {
        this.productionLine = productionLine;
    }

    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts;
    }

    public String getMoCode() {
        return moCode;
    }

    public void setMoCode(String moCode) {
        this.moCode = moCode;
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
}
