package com.egdfrm.unit.service.board;

import java.util.List;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.StockSendExcel;

/**
 * 待发货接口
 * Created by tyq on 17/1/18.
 */
public interface IToDeliveryService {


    /**
     * 待发货分页查询
     * @param pagination 分页条件
     * @return 数据集
     */
    Pagination findPage(Pagination pagination, int orgId);
    
    Pagination findPage1(Pagination pagination, int orgId);
    
    
    List<StockSendExcel> findPageExcel(int orgId);
}
