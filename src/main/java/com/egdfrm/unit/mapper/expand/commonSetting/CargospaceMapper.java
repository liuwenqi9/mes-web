package com.egdfrm.unit.mapper.expand.commonSetting;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * @author sjf
 * @date 2016年12月19日 
 * 货位管理MAPPER
 */
public interface CargospaceMapper {


	/**
	 * @author sjf
	 * @date 2017年1月3日 
	 * @param trimSublibraryName
	 * @return
	 * 货位查询
	 */
//	public List<Map<String, Object>> searchCargospaceList(@Param("sublibraryName")String sublibraryName,@Param("cargospaceName")String cargospaceName);

	public List<Map<String, Object>> searchCargospaceList(Map<String, Object> paramMap);
	
	
}