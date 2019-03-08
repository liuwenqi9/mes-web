package com.egdfrm.unit.service.barcodeManagement;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.barcodeManagement.ShipmentMapper;
import com.egdfrm.unit.model.barcodeManagement.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 发运清单条码接口
 * Created by tyq on 17/1/19.
 */
@Service
public class ShipmentService implements IShipmentService {

    @Autowired
    private ShipmentMapper shipmentMapper;


    /**
     * 发运清单条码分页查询
     * @param pagination 分页条件
     * @param shipment 查询条件
     * @return 数据集
     */
    @Override
    public Pagination findPage(Pagination pagination, Shipment shipment) {
        long count = shipmentMapper.getCount(shipment);
        List<Shipment> list = shipmentMapper.findPage(pagination,shipment);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }

    /**
     * 根据条码查询打印数据
     * @param codes 条码
     * @return
     */
    @Override
    public List<ShipmentPrint> getCodeByData(String codes) {
        return shipmentMapper.getCodeByData(codes);
    }


    /**
     * 修改打印状态
     * @param codes 条码
     * @return
     */
    @Override
    public int updatePrintState(String[] codes) {
        return shipmentMapper.updatePrintState(codes);
    }


    @Override
    public void callCheckMo(Map<String, Object> paramMap) {
        shipmentMapper.callCheckMo(paramMap);
    }

    @Override
    public CusHeader cusHeader(CusHeader cusHeader) {
        List<CusHeader>  list = shipmentMapper.getHeader(cusHeader);
        return (list!=null&&!list.isEmpty())?list.get(0):null;
    }


    @Override
    public List<CustomerMoRequire> getMoList(CustomerMoRequire customerMoRequire) {
        return shipmentMapper.getMoList(customerMoRequire);
    }

    @Override
    public List<CustomerOrderRequire> getOrderList(CustomerOrderRequire customerOrderRequire) {
        return shipmentMapper.getOrderList(customerOrderRequire);
    }

    @Override
    public Pagination moPage(Pagination pagination, CustomerSop customerSop) {
        long count = shipmentMapper.getMoCount(customerSop);
        List<CustomerSop> list = shipmentMapper.findMoPage(pagination,customerSop);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }

    @Override
    public Pagination orderPage(Pagination pagination, CustomerSop customerSop) {
        long count = shipmentMapper.getOrderCount(customerSop);
        List<CustomerSop> list = shipmentMapper.findOrderPage(pagination,customerSop);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }

    @Override
    public FileData getFiles(String FILE_ID) {
        return shipmentMapper.getFiles(FILE_ID);
    }
}
