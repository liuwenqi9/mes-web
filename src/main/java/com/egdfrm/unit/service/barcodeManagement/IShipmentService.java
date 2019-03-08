package com.egdfrm.unit.service.barcodeManagement;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.barcodeManagement.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 发运清单条码接口
 * Created by tyq on 17/1/19.
 */
public interface IShipmentService {

    /**
     * 发运清单条码分页查询
     * @param pagination 分页条件
     * @param shipment 查询条件
     * @return 数据集
     */
    Pagination findPage(Pagination pagination, Shipment shipment);

    /**
     * 根据条码查询打印数据
     * @param codes 条码
     * @return
     */
    List<ShipmentPrint> getCodeByData(String codes);

    /**
     * 修改打印状态
     * @param codes 条码
     * @return
     */
    int updatePrintState(String[] codes);



    //================= customerSopController================////

    void callCheckMo(Map<String, Object> paramMap);

    CusHeader cusHeader(CusHeader cusHeader);

    List<CustomerMoRequire> getMoList(CustomerMoRequire customerMoRequire);

    List<CustomerOrderRequire> getOrderList(CustomerOrderRequire customerOrderRequire);

    Pagination moPage(Pagination pagination, CustomerSop customerSop);

    Pagination orderPage(Pagination pagination, CustomerSop customerSop);

    FileData getFiles(String FILE_ID);
}
