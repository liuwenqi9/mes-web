package com.egdfrm.unit.service.board;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.StockupExcel;
import com.egdfrm.unit.mapper.board.ToStockingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 待备货接口实现
 * Created by tyq on 17/1/18.
 */
@Service
public class ToStockingService implements IToStockingService {


    @Autowired
    private ToStockingMapper toStockingMapper;

    /**
     * 待备货分页查询
     * @param pagination 分页条件
     * @param orgId 组织ID
     * @return 数据集
     */
    @Override
    public Pagination findPage(Pagination pagination, int orgId) {
        long count = toStockingMapper.getCount(orgId);
        List<Map<String,Object>> list = toStockingMapper.findPage(pagination,orgId);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }

    @Override
    public Pagination findPage1(Pagination pagination, int orgId) {
        long count = toStockingMapper.getCount1(orgId);
        List<Map<String,Object>> list = toStockingMapper.findPage1(pagination,orgId); 
        Map<String, Object> map = toStockingMapper.sum_findPage1();
        map.put("TRANSACTION_DATE","汇总");     
        map.put("ACCOUNT_NAME", ""); 
        list.add(0, map);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }
    
	@Override
	public List<StockupExcel> findPageExcel(int orgId) { 
		return toStockingMapper.findPageExcel(orgId);
	}
 
}
