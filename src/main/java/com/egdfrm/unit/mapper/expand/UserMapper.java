package com.egdfrm.unit.mapper.expand;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * @author sjf
 * @date 2016年12月19日 
 * 共通MAPPER
 */
public interface UserMapper {

	/**
	 * @author sjf
	 * @date 2016年12月21日 
	 * @return
	 * 获取生产线列表
	 */
	public List<Map<String, Object>> getPlanLines(); 
	
	/**
	 * 根据登录名获取生产线
	 * @param loginName 登录名
	 * @return   
	 * @author	hgb
	 * @date 2017-1-5
	 */
	public List<Map<String, Object>> getPlanLineByLoginName(String loginName);
	
	
	/**
	 * 根据loginName获取角色
	 * @param loginName
	 * @return   
	 * @author	hgb
	 * @date 2017-1-12
	 */
	public List<Map<String, Object>> getRoleCodeByLoginName(String loginName);
	
	
	  
	public List<Map<String, Object>> getPlanLineCodeCodeByLoginName(String loginName);
	
}