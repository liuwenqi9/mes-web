package com.egdfrm.unit.service.pagequery;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.egdfrm.core.model.expand.PageResult;
/**
 * <p>
 * ClassName: CustomPageQueryInterface
 * </p>
 * <p>
 * Description: 自定义分页查询接口
 * </p>
 * <p>
 * Author: 兰继明
 * </p>
 * <p>
 * Date: 2017年01月07日
 * </p>
 */
public interface CustomPageQueryInterface<M> {


	    /**
	     * <p>
	     * Description: 分页
	     * </p>
	     * 
	     * @param model 模型
	     * @param pageSize 页大小
	     * @param curPage 当前页
	     * @return 分页数据
	     * @throws Exception 异常
	     */
	    public List<Map<String, Object>> pageQuery(@Param("model")M model,@Param("pageSize") int pageSize,@Param("curPage") int curPage) throws Exception;
	    
	    public int getCount(@Param("model")M model) throws Exception;

	
}
