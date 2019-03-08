package com.egdfrm.unit.service.barcodeManagement;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.barcodeManagement.ProductBarcodeMapper;
import com.egdfrm.unit.mapper.expand.CommonMapper;
import com.egdfrm.unit.mapper.expand.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.core.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 产品条码 的service
 * @author hgb
 * @date 2016-12-21
 */
@Service
public class ProductBarcodeService extends BaseService implements IProductBarcodeService{

    @Autowired
    private ProductBarcodeMapper productBarcodeMapper;

    /**
     * 获取产品条码列表
     * @param pagination 分页数据
     * @param map 查询条件
     * @return
     */
    @Override
    public Pagination getProductBarcode(Pagination pagination, Map<String, Object> map) {
        Long total = productBarcodeMapper.getProductBarcodeByCount(map);
        List<Map<String,Object>> rows = productBarcodeMapper.getProductBarcode(pagination,map);
        pagination.setTotal(total);
        pagination.setRows(rows);
        return pagination;
    }

    /**
     * 条码生成
     * @param map
     */
    @Override
    public void barcodeProduction(Map<String, Object> map) {
        productBarcodeMapper.barcodeProduction(map);
    }

    /**
     * 工单分页查询
     * @param pagination 分页查询
     * @param map 查询条件
     * @return
     */
    @Override
    public Pagination getWorkOrderByPage(Pagination pagination, Map<String, Object> map) {
        Long total = productBarcodeMapper.getWorkOrderByCount(map);
        List<Map<String,Object>> rows = productBarcodeMapper.getWorkOrderByPage(pagination,map);
        pagination.setTotal(total);
        pagination.setRows(rows);
        return pagination;
    }

    /**
     * 条码作废
     * @param map
     */
    @Override
    public void abolish(Map<String, Object> map) {
        productBarcodeMapper.abolish(map);
    }

    /**
     * 获取条码状态
     * @return
     */
    @Override
    public List<String> getBarcodeStatus() {
        return productBarcodeMapper.getBarcodeStatus();
    }

    /**
     * 修改条码打印状态
     * @param codes
     * @return
     */
    @Override
    public int updatePrintStatus(String[] codes) {
        return productBarcodeMapper.updatePrintStatus(codes);
    }
}
