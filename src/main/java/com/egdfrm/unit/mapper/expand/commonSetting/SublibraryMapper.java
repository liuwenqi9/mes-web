package com.egdfrm.unit.mapper.expand.commonSetting;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * @author sjf
 * @date 2016年12月19日 
 * 子库一览MAPPER
 */
public interface SublibraryMapper {


	/**
	 * @author sjf
	 * @date 2016年12月30日 
	 * @return
	 * 
	 * 获取子库列表
	 * 
	 */
	public List<Map<String, Object>> getSublibraryList(@Param("sublibraryName") String sublibraryName); 
	
	
}