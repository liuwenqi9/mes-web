package com.egdfrm.unit.service.board;

import java.util.List;

import com.egdfrm.unit.common.Pagination; 
import com.egdfrm.unit.excelmodel.StockupExcel;

/**
 * 待备货接口
 * Created by tyq on 17/1/18.
 */
public interface IToStockingService {

    /**
     * 待备货分页查询
     * @param pagination 分页条件
     * @param orgId 组织ID
     * @return 数据集
     */
    Pagination findPage(Pagination pagination, int orgId);
    
    Pagination findPage1(Pagination pagination, int orgId);
    
    List<StockupExcel> findPageExcel(int orgId);
}
