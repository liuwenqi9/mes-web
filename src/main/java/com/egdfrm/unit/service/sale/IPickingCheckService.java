package com.egdfrm.unit.service.sale;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.PickingExcelModel;

import java.util.List;
import java.util.Map;

/**
 * 挑库拼箱检查接口
 * Created by tyq on 17/2/14.
 */
public interface IPickingCheckService {

    /**
     * 挑库拼箱检查查询
     * @param pagination 分页数据
     * @param map 查询条件
     * @return 数据集
     */
    Pagination queryPages(Pagination pagination, Map<String, Object> map);
    
   List<PickingExcelModel> exportExcel(Map<String, Object> map);
}
