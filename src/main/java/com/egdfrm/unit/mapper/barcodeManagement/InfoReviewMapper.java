package com.egdfrm.unit.mapper.barcodeManagement;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.BackPageExcel;
import com.egdfrm.unit.excelmodel.BasicPageExcel;
import com.egdfrm.unit.excelmodel.MiscellaneousPageExcel;
import com.egdfrm.unit.excelmodel.RepairPageExcel; 
import com.egdfrm.unit.excelmodel.ShipmentPageExcel;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 条码信息追溯-Mapper
 * Created by tyq on 17/2/20.
 */
public interface InfoReviewMapper {

    /**
     * 基本信息数据总数查询
     * @param map 查询提条件
     * @return
     */
    long getBasicPageCount(@Param("map") Map<String, Object> map);

    /**
     * 基本信息查询
     * @param pagination 分页信息
     * @param map 查询条件
     * @return 结果集
     */
    List<Map<String,Object>> getBasicPage(@Param("page") Pagination pagination, @Param("map") Map<String, Object> map);
    
    /**
     * 基本信息导出excel
     * @param map 查询条件 
     */
    List<BasicPageExcel> basicPageExportExcel(@Param("map") Map<String, Object> map);
    
    /**
     * 出货信息数据总数查询
     * @param map 查询提条件
     * @return
     */
    long getShipmentPageCount(@Param("map")Map<String, Object> map);

    /**
     * 出货信息查询
     * @param pagination 分页信息
     * @param map 查询条件
     * @return 结果集
     */
    List<Map<String,Object>> getShipmentPage(@Param("page")Pagination pagination, @Param("map")Map<String, Object> map);
 
    /**
     * 出货信息excel 
     * @param map 查询条件 
     */
    List<ShipmentPageExcel> shipmentPageExportExcel(@Param("map")Map<String, Object> map);

    
    /**
     * 退货信息数据总数查询
     * @param map 查询提条件
     * @return
     */
    long getBackPageCount(@Param("map")Map<String, Object> map);

    /**
     * 退货信息查询
     * @param pagination 分页信息
     * @param map 查询条件
     * @return 结果集
     */
    List<Map<String,Object>> getBackPage(@Param("page")Pagination pagination, @Param("map")Map<String, Object> map);

    /**
     * 退货信息excel
     * @param pagination 分页信息  
     */
    List<BackPageExcel> backPageExportExcel(@Param("map")Map<String, Object> map);
    
    /**
     * 返修信息数据总数查询
     * @param map 查询条件
     * @return
     */
    long getRepairPageCount(@Param("map")Map<String, Object> map);

    /**
     * 返修信息查询
     * @param pagination 分页信息
     * @param map 查询条件
     * @return 结果集
     */
    List<Map<String,Object>> getRepairPage(@Param("page")Pagination pagination, @Param("map")Map<String, Object> map);
 
    /**
     * 返修信息导出excel 
     * @param map 查询条件 
     */
    List<RepairPageExcel> repairPageExportExcel(@Param("map")Map<String, Object> map);
 
    /**
     * 杂项交易数据总数查询
     * @param map 查询条件
     * @return
     */
    long getMiscellaneousPageCount(@Param("map")Map<String, Object> map);

    /**
     * 杂项交易查询
     * @param pagination 分页信息
     * @param map 查询条件
     * @return 结果集
     */
    List<Map<String,Object>> getMiscellaneousPage(@Param("page")Pagination pagination, @Param("map")Map<String, Object> map);

    /**
     * 杂项交易导出excel
     * @param map 查询条件 
     */
    List<MiscellaneousPageExcel> miscellaneousPageExcel(@Param("map")Map<String, Object> map);

}
