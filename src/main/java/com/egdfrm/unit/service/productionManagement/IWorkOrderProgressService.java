package com.egdfrm.unit.service.productionManagement;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.WorkOrderProgressExcel;
import com.egdfrm.unit.model.barcodeManagement.WorkOrderProgress;

import java.util.List;
import java.util.Map;

/**
 * 工单生产进度接口
 * Created by tyq on 17/1/11.
 */
public interface IWorkOrderProgressService {

    /**
     * 工单生产进度分页查询
     * @param pagination 分页条件
     * @param wop 查询条件
     * @return
     */
    Pagination getwrokOrderList(Pagination pagination, WorkOrderProgress wop);

    /**
     * 获取生产线
     * @return
     */
    List<Map<String,Object>> getLines();

    /**
     * 获取装配件
      * @param pagination
     * @param map
     * @return
     */
    Pagination getPartsByPage(Pagination pagination, Map<String, Object> map);
    
    
    List<WorkOrderProgressExcel> exportExcel(WorkOrderProgress wop);
    
}
