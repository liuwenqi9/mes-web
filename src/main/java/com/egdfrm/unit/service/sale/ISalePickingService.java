package com.egdfrm.unit.service.sale;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.sale.Picking;

import java.util.List;
import java.util.Map;

/**
 * 挑库单接口
 * Created by tyq on 17/2/23.
 */
public interface ISalePickingService {

    /**
     * 挑库检查分页查询
     * @param pagination 分页信息
     * @param map 查询条件
     * @return 数据集
     */
    Pagination queryPages(Pagination pagination, Map<String, Object> map);

    /**
     * 挑库检查查询
     * @param map 查询条件
     * @return 数据集
     */
    List<Picking> getAll(Map<String,Object> map);
}
