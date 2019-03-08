package com.egdfrm.unit.mapper.expand.pda;

import java.util.List;



/**
 * @author sjf
 * @date 2016年12月29日 
 * PDA登录Mapper
 */
public interface LoginMapper {


 
	/**
	 * @author sjf
	 * @date 2016年12月29日 
	 * @param loginName
	 * 获取PDA权限
	 */
	public List<String> getPdaRoleFunction(String loginName); 
	
	
}