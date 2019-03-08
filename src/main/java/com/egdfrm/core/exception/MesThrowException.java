package com.egdfrm.core.exception;

import com.egdfrm.core.exception.CommonException;
import com.egdfrm.core.exception.CommonExceptionType;

public class MesThrowException extends CommonException {
	
	/**
	 * 通用设置
	 */
	private static final long serialVersionUID = 2352216978172581136L;

	public MesThrowException(CommonExceptionType errorType){
		
		super(errorType.getErrCode());		
	}
}
