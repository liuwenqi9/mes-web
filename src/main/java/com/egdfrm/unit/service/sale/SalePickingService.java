package com.egdfrm.unit.service.sale;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.sale.SalePickingMapper;
import com.egdfrm.unit.model.sale.Picking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 挑库检查接口实现
 * Created by tyq on 17/2/23.
 */
@Service
public class SalePickingService implements ISalePickingService {

    @Autowired
    private SalePickingMapper pMapper; 

    /**
     * 挑库检查分页查询
     * @param pagination 分页信息
     * @param map 查询条件
     * @return 数据集
     */
    @Override
    public Pagination queryPages(Pagination pagination, Map<String, Object> map) {
        //数据总数查询
        long count = pMapper.queryPagesCount(map);
        List<Map> list = pMapper.queryPages(pagination,map);
        pagination.setRows(list);
        pagination.setTotal(count);
        return pagination;
    }

    /**
     * 挑库检查查询
     * @param map 查询条件
     * @return 数据集
     */
    @Override
    public List<Picking> getAll(Map<String,Object> map) {
        return pMapper.getAll(map);
    }
}
