package com.egdfrm.unit.service.reportManagement;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.board.Storage;
import com.egdfrm.unit.model.report.Saot;

import java.util.List;

/**
 * 借机统计报表-接口
 * Created by tyq on 17/3/29.
 */
public interface ISaotService {

    /**
     * 借机统计报表分页查询
     * @param pagination 分页数据
     * @param saot 查询条件
     * @return 数据集
     */
    Pagination findPage(Pagination pagination, Saot saot);

    /**
     * 查询所有
     * @param saot
     * @return
     */
    List<Saot> finAll(Saot saot);
}
