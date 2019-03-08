package com.egdfrm.unit.service.stock;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.stock.Finished;

import java.util.List;

/**
 * 成品库存接口
 * Created by tyq on 17/1/17.
 */
public interface IFinishedService {

    /**
     * 成品库存分页查询
     * @param pagination 分页条件
     * @param finished 查询条件
     * @return 分页数据
     */
    Pagination findPage(Pagination pagination, Finished finished);


    /**
     * 查询所有数据
     * @param finished 查询条件
     * @return 数据集
     */
    List<Finished> getAll(Finished finished);

    /**
     * 子库分页查询
     * @param pagination 分页信息
     * @param id 子库ID
     * @return 分页集合
     */
    Pagination getZKByPage(Pagination pagination, String id);


    /**
     * 货位分页查询
     * @param pagination 分页信息
     * @param id 货位ID
     * @param code 子库ID
     * @return 分页集合
     */
    Pagination getHWByPage(Pagination pagination, String id, String code);
}
