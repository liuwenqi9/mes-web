package com.egdfrm.unit.mapper.sale;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.PickingExcelModel;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 挑库拼箱检查-Mapper
 * Created by tyq on 17/2/14.
 */
public interface PickingCheckMapper {


    /**
     * 查询数据总数
     * @param map 查询条件
     * @return
     */
    long queryCount(@Param("map") Map<String, Object> map);

    /**
     * 挑库拼箱检查查询
     * @param pagination 分页数据
     * @param map 查询条件
     * @return 数据集
     */
    List<Map<String,Object>> queryPages(@Param("page") Pagination pagination, @Param("map") Map<String, Object> map);


    List<PickingExcelModel>  exportExcel(@Param("map") Map<String, Object> map);
    
}