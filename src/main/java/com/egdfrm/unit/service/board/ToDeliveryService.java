package com.egdfrm.unit.service.board;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.StockSendExcel;
import com.egdfrm.unit.mapper.board.ToDeliveryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 代发货接口实现
 * Created by tyq on 17/1/18.
 */
@Service
public class ToDeliveryService implements IToDeliveryService {

    @Autowired
    private ToDeliveryMapper ToDeliveryMapper;


    /**
     * 待发货分页查询
     * @param pagination 分页条件
     * @return 数据集
     */
    @Override
    public Pagination findPage(Pagination pagination, int orgId) {
        long count = ToDeliveryMapper.getCount(orgId);
        List<Map<String,Object>> list = ToDeliveryMapper.findPage(pagination,orgId);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }

    @Override
    public Pagination findPage1(Pagination pagination, int orgId) {
        long count = ToDeliveryMapper.getCount1(orgId);
        List<Map<String,Object>> list = ToDeliveryMapper.findPage1(pagination,orgId);
        Map<String, Object> map = ToDeliveryMapper.sum_findPage1();
        map.put("TRANSACTION_DATE","汇总");     
        map.put("ACCOUNT_NAME", "");
        list.add(0, map);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }

	@Override
	public List<StockSendExcel> findPageExcel(int orgId) { 
		return ToDeliveryMapper.findPageExcel(orgId);
	}
}
