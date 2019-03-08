package com.egdfrm.unit.service.sale;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.PickingExcelModel;
import com.egdfrm.unit.mapper.sale.PickingCheckMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 挑库拼箱检查接口实现
 * Created by tyq on 17/2/14.
 */
@Service
public class PickingCheckService implements IPickingCheckService {

    @Autowired
    private PickingCheckMapper pickingCheckMapper;

    /**
     * 挑库拼箱检查查询
     * @param pagination 分页数据
     * @param map 查询条件
     * @return 数据集
     */
    @Override
    public Pagination queryPages(Pagination pagination, Map<String, Object> map) {
        long count = pickingCheckMapper.queryCount(map);
        List<Map<String,Object>> list = pickingCheckMapper.queryPages(pagination,map);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }

	@Override
	public List<PickingExcelModel> exportExcel(Map<String, Object> map) {
		
		return pickingCheckMapper.exportExcel(map);
	}
}
