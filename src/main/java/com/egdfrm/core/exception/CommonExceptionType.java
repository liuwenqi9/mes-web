package com.egdfrm.core.exception;

public enum CommonExceptionType {

	// 该子库已失效，请重新选择！
	SUBINVENTORIE_DISABLED("subinventorieDisabled"),

	// 该子库为ERP控制，不可选择！
	ERP_CONTROLLER_ERROR("ERPControllerError"), 
	// 子库不存在！
	SUBINVENTORIE_NOT_EXISTS("subinventorieNotExists"),
	// 子库名不能为空！
	SUBINVENTORIE_CODE_IS_NULL("subinventorieCodeIsNull"),
	// 货位名不能为空！
	LOCATTION_CODE_IS_NULL("locattionCodeIsNull"),
	//该货位名已存在！
	LOCATTIONS_IS_EXISTS("locattionIsExists"),
	

	// 您只能操作三级菜单！
	ROLE_NOT_LEV3("canOnlyOperateThreeMenu"),
	// 旧密码错误！
	OLD_PASSWORD_WRONG("oldPasswordWrong"),

	//包装箱号不能为空！
	PACKAGEBARCODE_IS_NULL("packagebarcodeIsNull"),
	//包装箱不存在
	PACKAGE_NOT_EXISTS("packageIsNull"),
	
	//此条码已存在，无法包装
	BARCODE_EXISTS("barcodeExists"),
	//报检单不存在！
	INSPECTIONNUMBER_NOT_EXISTS("inspectionNumberNotExists"),

	//找不到对应的数据！
	NO_DATA_FOUND("noDataFound"),
	//传入参数不能为空！
	PARAMETER_NOT_NULL("parameterNotNull"),
	
	// 参数错误
	PARAMETER_ERROR("parameterError") ;

	private String errCode;

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	private CommonExceptionType(String errorCode) {
		this.errCode = errorCode;
	}

}
