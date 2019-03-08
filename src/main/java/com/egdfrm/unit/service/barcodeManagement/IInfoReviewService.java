package com.egdfrm.unit.service.barcodeManagement;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.BackPageExcel;
import com.egdfrm.unit.excelmodel.BasicPageExcel;
import com.egdfrm.unit.excelmodel.MiscellaneousPageExcel;
import com.egdfrm.unit.excelmodel.RepairPageExcel;
import com.egdfrm.unit.excelmodel.ShipmentPageExcel;

import java.util.List;
import java.util.Map;

/**
 * 条码信息追溯-接口
 * Created by tyq on 17/2/20.
 */
public interface IInfoReviewService {

    /**
     * 基本信息查询
     * @param pagination 分页信息
     * @param map 查询条件
     * @return 结果集
     */
    Pagination getBasicPage(Pagination pagination, Map<String, Object> map);
    
    /**
     * 基本信息 excel
     * @param map 查询条件 
     */
    List<BasicPageExcel> basicPageExportExcel(Map<String, Object> map);
    

    /**
     * 出货信息查询
     * @param pagination 分页信息
     * @param map 查询条件
     * @return 结果集
     */
    Pagination getShipmentPage(Pagination pagination, Map<String, Object> map);
    
    /**
     * 出货信息 excel
     * @param map    
     * @author	hgb
     * @date 2017-6-7
     */
    List<ShipmentPageExcel> shipmentPageExportExcel(Map<String, Object> map);

    /**
     * 退货信息查询
     * @param pagination 分页信息
     * @param map 查询条件
     * @return 结果集
     */
    Pagination getBackPage(Pagination pagination, Map<String, Object> map);
    
    /**
     * 退货信息excel 
     * @param map 查询条件 
     */
    List<BackPageExcel> backPageExportExcel(Map<String, Object> map);

    /**
     * 返修信息查询
     * @param pagination 分页信息
     * @param map 查询条件
     * @return 结果集
     */
    Pagination getRepairPage(Pagination pagination, Map<String, Object> map);

    /**
     * 返修信息excel 
     * @param map 查询条件 
     */
    List<RepairPageExcel> repairPageExportExcel(Map<String, Object> map);
    
    /**
     * 杂项交易查询
     * @param pagination 分页信息
     * @param map 查询条件
     * @return 结果集
     */
    Pagination getMiscellaneousPage(Pagination pagination, Map<String, Object> map);
    
    /**
     * 杂项交易excel 
     * @param map 查询条件 
     */
    List<MiscellaneousPageExcel> miscellaneousPageExportExcel(Map<String, Object> map);
}
