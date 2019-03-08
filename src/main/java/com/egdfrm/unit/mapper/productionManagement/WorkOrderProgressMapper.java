package com.egdfrm.unit.mapper.productionManagement;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.WorkOrderProgressExcel;
import com.egdfrm.unit.model.barcodeManagement.WorkOrderProgress;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 工单生产进度Mapper
 * Created by tyq on 17/1/11.
 */
public interface WorkOrderProgressMapper {

    /**
     * 查询数据总数
     * @param wop
     * @return
     */
    long getCount(@Param("wop") WorkOrderProgress wop);

    /**
     * 分页查询
     * @param pagination
     * @param wop
     * @return
     */
    List<Map<String,Object>> getwrokOrderList(@Param("page")Pagination pagination, @Param("wop")WorkOrderProgress wop);

    /**
     * 查询生产线
     * @return
     */
    List<Map<String, Object>> getLines();

    /**
     * 查询装配件
     * @return
     */
    List<Map<String, Object>> getParts();


    /**
     * 获取装配件总数
     * @param map
     * @return
     */
    long getPartsByCount(@Param("map") Map<String, Object> map);

    /**
     * 装配件分页
     * @param pagination
     * @param map
     * @return
     */
    List<Map<String,Object>> getPartsByPage(@Param("page")Pagination pagination, @Param("map")Map<String, Object> map);

    /**
     *  导出excel
     */
    List<WorkOrderProgressExcel> exportExcel(@Param("wop")WorkOrderProgress wop);
}
