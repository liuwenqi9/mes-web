package com.egdfrm.unit.mapper.barcodeManagement;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.barcodeManagement.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 发运清单条码Mapper
 * Created by tyq on 17/1/19.
 */
public interface ShipmentMapper {

    /**
     * 查询总数据条数
     * @param shipment 查询条件
     * @return 数据总数
     */
    long getCount(@Param("shipment") Shipment shipment);

    /**
     * 发运清单条码分页查询
     * @param pagination 分页条件
     * @param shipment 查询条件
     * @return 数据集
     */
    List<Shipment> findPage(@Param("page")Pagination pagination, @Param("shipment") Shipment shipment);

    /**
     * 根据条码查询打印数据
     * @param codes 条码
     * @return
     */
    List<ShipmentPrint> getCodeByData(@Param("codes") String codes);

    /**
     * 修改打印状态
     * @param codes 条码
     * @return
     */
    int updatePrintState(@Param("codes")String[] codes);


    //======================================////

    void callCheckMo(Map<String, Object> paramMap);

    List<CusHeader> getHeader(@Param("cusHeader") CusHeader cusHeader);

    List<CustomerMoRequire> getMoList(@Param("customerMoRequire") CustomerMoRequire customerMoRequire);

    List<CustomerOrderRequire> getOrderList(@Param("customerOrderRequire") CustomerOrderRequire customerOrderRequire);

    long getMoCount(@Param("customerSop") CustomerSop customerSop);

    List<CustomerSop> findMoPage(@Param("page")Pagination pagination, @Param("customerSop") CustomerSop customerSop);

    long getOrderCount(@Param("customerSop") CustomerSop customerSop);

    List<CustomerSop> findOrderPage(@Param("page")Pagination pagination, @Param("customerSop") CustomerSop customerSop);

    FileData getFiles(@Param("FILE_ID") String FILE_ID);
}
