package com.egdfrm.unit.service.reportManagement;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.reportManagement.SaotMapper;
import com.egdfrm.unit.model.board.Storage;
import com.egdfrm.unit.model.report.Saot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 借机统计报表-接口实现
 * Created by tyq on 17/3/29.
 */
@Service
public class SaotService implements ISaotService {

    @Autowired
    private SaotMapper saotMapper;

    /**
     * 借机统计报表分页查询
     * @param pagination 分页数据
     * @param saot 查询条件
     * @return 数据集
     */
    @Override
    public Pagination findPage(Pagination pagination, Saot saot) {
        long count = saotMapper.findPageCount(saot);
        List<Saot> list = saotMapper.findPage(pagination,saot);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }

    /**
     * 借机统计数据查询
     * @param saot
     * @return
     */
    @Override
    public List<Saot> finAll(Saot saot) {
        return saotMapper.finAll(saot);
    }


}
