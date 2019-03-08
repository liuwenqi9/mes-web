package com.egdfrm.core.exception;

import com.egdfrm.core.common.PropertiesLoad;

/**
 * @author sjf
 * 
 * 通用异常类
 *
 */
public class CommonException extends GeneralException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5752129642364952680L;

	public static String ExceptionByCode(String errorType) {
		return new PropertiesLoad().load("message",errorType);
	}

	public CommonException(String errorType) {
		super(ExceptionByCode(errorType));

		this.errCode = errorType;
	}

	public CommonException(String errorType,String errMsg) {
		super(errMsg);

		this.errCode = errorType;
	}
	
	public CommonException() {
		super();
	}
}
