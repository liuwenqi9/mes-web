package com.egdfrm.unit.mapper.sale;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.sale.Picking;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 挑库检查-Mapper
 * Created by tyq on 17/2/23.
 */
public interface SalePickingMapper {

    /**
     * 数据总数查询
     * @param map 查询条件
     * @return 数据集
     */
    long queryPagesCount(@Param("map") Map<String, Object> map);

    /**
     * 挑库检查分页查询
     * @param page 分页数据
     * @param map 查询条件
     * @return
     */
    List<Map> queryPages(@Param("page") Pagination page, @Param("map") Map<String, Object> map);

    /**
     * 挑库检查查询
     * @param map 查询条件
     * @return 数据集
     */
    List<Picking> getAll(@Param("map") Map<String, Object> map);
}
