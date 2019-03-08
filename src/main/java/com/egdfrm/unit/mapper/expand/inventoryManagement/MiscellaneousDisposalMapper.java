package com.egdfrm.unit.mapper.expand.inventoryManagement;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


/**
 * @author sjf
 * @date 2016年12月19日 
 * 杂项事务处理MAPPER
 */
public interface MiscellaneousDisposalMapper {
 
	/**
	 * @author sjf
	 * @date 2017年1月12日 
	 * @return
	 * 获取【事务处理类型】列表
	 */
	public List<Map<String, Object>> getTransactionTypeList(); 
	/**
	 * @author 兰继明
	 * @date 2017年2月12日 
	 * @return
	 * 校验【事务处理类型】
	 */
	public List<Map<String, Object>> checkTransactionType(@Param("type") String type); 
	/**
	 * @author sjf
	 * @date 2017年1月12日 
	 * @param orgId
	 * @return
	 * 搜索【来源】
	 */
	public List<Map<String, Object>> searchSourceByName(@Param("orgId") BigDecimal orgId,@Param("sourceLike") String sourceLike); 
	/**
	 * @author 兰继明
	 * @date 2017年2月12日 
	 * @param orgId
	 * @return
	 * 校验表单【来源】
	 */
	public List<Map<String, Object>> checkSourceByNames(@Param("orgId") BigDecimal orgId,@Param("source") String source); 
	/**
	 * @author 兰继明
	 * @date 2017年2月12日 
	 * @param orgId
	 * @return
	 * <!--帐户别名三步1检查写入事务接口表结果(验证表单)-->
	 */
	public void callCheckTransactionInsert(Map<String, Object> paramMap); 
	/**
	 * @author 兰继明
	 * @date 2017年2月12日 
	 * @param orgId
	 * @return
	 * <!--帐户别名三步2写入MES-->
	 */
	public void callTransactionInsertMES(Map<String, Object> paramMap); 
	/**
	 * @author 兰继明
	 * @date 2017年2月12日 
	 * @param orgId
	 * @return
	 * <!--帐户别名三步3写入EBS-->
	 */
	public void callTransactionInsertEBS(Map<String, Object> paramMap); 
	
	
}