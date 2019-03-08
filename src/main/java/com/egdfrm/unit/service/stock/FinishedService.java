package com.egdfrm.unit.service.stock;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.stock.FinishedMapper;
import com.egdfrm.unit.model.stock.Finished;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 成品库存接口实现
 * Created by tyq on 17/1/17.
 */
@Service
public class FinishedService implements IFinishedService {

    @Autowired
    private FinishedMapper finishedMapper;

    /**
     * 成品库存分页查询
     * @param pagination 分页条件
     * @param finished 查询条件
     * @return 分页数据
     */
    @Override
    public Pagination findPage(Pagination pagination, Finished finished) {
        long count = finishedMapper.getCount(finished);
        List<Finished> list = finishedMapper.findPage(pagination,finished);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }

    /**
     * 查询所有数据
     * @param finished 查询条件
     * @return 数据集
     */
    @Override
    public List<Finished> getAll(Finished finished) {
        return finishedMapper.getAll(finished);
    }

    /**
     * 子库分页查询
     * @param pagination 分页信息
     * @param id 子库ID
     * @return 分页集合
     */
    @Override
    public Pagination getZKByPage(Pagination pagination, String id) {
        long count = finishedMapper.getZKByCount(id);
        List<Map<String,Object>> list = finishedMapper.getZKByPage(pagination,id);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }

    /**
     * 货位分页查询
     * @param pagination 分页信息
     * @param id 货位ID
     * @param code 子库ID
     * @return 分页集合
     */
    @Override
    public Pagination getHWByPage(Pagination pagination, String id, String code) {
        long count = finishedMapper.getHWByCount(id,code);
        List<Map<String,Object>> list = finishedMapper.getHWByPage(pagination,id,code);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }
}
