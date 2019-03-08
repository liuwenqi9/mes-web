package com.egdfrm.unit.mapper.reportManagement;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.board.Storage;
import com.egdfrm.unit.model.report.Saot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 借机统计报表
 * Created by tyq on 17/3/29.
 */
public interface SaotMapper {

    /**
     * 查询借机统计报表总数据量
     * @param saot 查询条件
     * @return
     */
    long findPageCount(@Param("saot") Saot saot);

    /**
     * 查询借机统计报表
     * @param pagination 分页数据
     * @param saot 查询条件
     * @return
     */
    List<Saot> findPage(@Param("page") Pagination pagination,@Param("saot") Saot saot);

    /**
     * 借机统计数据查询
     * @param saot
     * @return
     */
    List<Saot> finAll(@Param("saot") Saot saot);
}
