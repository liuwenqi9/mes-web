package com.egdfrm.unit.mapper.stock;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.stock.Finished;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 成品库存Mapper
 * Created by tyq on 17/1/17.
 */
public interface FinishedMapper {


    /**
     * 查询数据总数
     * @param finished 查询条件
     * @return
     */
    long getCount(@Param("finished") Finished finished);

    /**
     * 成品库存分页查询
     * @param pagination 分页条件
     * @param finished 查询条件
     * @return 分页数据
     */
    List<Finished> findPage(@Param("page")Pagination pagination, @Param("finished")Finished finished);

    /**
     * 查询所有数据
     * @param finished 查询条件
     * @return 数据集
     */
    List<Finished> getAll(@Param("finished")Finished finished);

    /**
     * 获取子库分页查询总数
     * @param id 子库ID
     * @return 数据总数
     */
    long getZKByCount(@Param("id") String id);

    /**
     * 子库分页查询
     * @param pagination 分页信息
     * @param id 子库ID
     * @return 分页集合
     */
    List<Map<String,Object>> getZKByPage(@Param("page")Pagination pagination, @Param("id")String id);

    /**
     * 获取货位分页查询总数
     * @param id 货位ID
     * @param code 子库ID
     * @return 数据总数
     */
    long getHWByCount(@Param("id")String id,@Param("code") String code);

    /**
     * 货位分页查询
     * @param pagination 分页信息
     * @param id 货位ID
     * @param code 子库ID
     * @return 分页集合
     */
    List<Map<String,Object>> getHWByPage(@Param("page")Pagination pagination, @Param("id")String id,@Param("code")String code);
}
