package com.egdfrm.extend.mapper.standard;

import com.egdfrm.extend.common.DbReturnParameter;
import com.egdfrm.extend.model.standard.MdCategoryV;
import com.egdfrm.extend.model.standard.MdCategoryVCriteria;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MdCategoryVMapper {
	
	int countByExample(MdCategoryVCriteria example);
	
	int deleteByExample(MdCategoryVCriteria example);
	
	List<MdCategoryV> selectByExample(MdCategoryVCriteria example);
	
	int updateByExample(@Param("record") MdCategoryV record, @Param("example") MdCategoryVCriteria example);
	
	public void insertrow(@Param("record") MdCategoryV record, @Param("dbreturn") DbReturnParameter dbreturn);
	
	public void updaterow(@Param("record") MdCategoryV record, @Param("dbreturn") DbReturnParameter dbreturn);
	
	public void deleterow(@Param("categoryId") BigDecimal categoryId, @Param("dbreturn") DbReturnParameter dbreturn);
	
	public MdCategoryV selectrow(BigDecimal categoryId);
	
}