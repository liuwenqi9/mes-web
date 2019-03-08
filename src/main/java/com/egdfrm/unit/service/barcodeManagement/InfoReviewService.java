package com.egdfrm.unit.service.barcodeManagement;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.BackPageExcel;
import com.egdfrm.unit.excelmodel.BasicPageExcel;
import com.egdfrm.unit.excelmodel.MiscellaneousPageExcel;
import com.egdfrm.unit.excelmodel.RepairPageExcel;
import com.egdfrm.unit.excelmodel.ShipmentPageExcel;
import com.egdfrm.unit.mapper.barcodeManagement.InfoReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 条码信息追溯-接口实现
 * Created by tyq on 17/2/20.
 */
@Service
public class InfoReviewService implements IInfoReviewService {

    @Autowired
    private InfoReviewMapper infoReviewMapper;

    /**
     * 基本信息查询
     * @param pagination 分页信息
     * @param map 查询条件
     * @return 结果集
     */
    @Override
    public Pagination getBasicPage(Pagination pagination, Map<String, Object> map) {
        //数据总数查询
        long count = infoReviewMapper.getBasicPageCount(map);
        List<Map<String,Object>> list = infoReviewMapper.getBasicPage(pagination,map);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }

    /**
     * 出货信息查询
     * @param pagination 分页信息
     * @param map 查询条件
     * @return 结果集
     */
    @Override
    public Pagination getShipmentPage(Pagination pagination, Map<String, Object> map) {
        //数据总数查询
        long count = infoReviewMapper.getShipmentPageCount(map);
        List<Map<String,Object>> list = infoReviewMapper.getShipmentPage(pagination,map);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }

    /**
     * 退货信息查询
     * @param pagination 分页信息
     * @param map 查询条件
     * @return 结果集
     */
    @Override
    public Pagination getBackPage(Pagination pagination, Map<String, Object> map) {
        //数据总数查询
        long count = infoReviewMapper.getBackPageCount(map);
        List<Map<String,Object>> list = infoReviewMapper.getBackPage(pagination,map);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }

    /**
     * 返修信息查询
     * @param pagination 分页信息
     * @param map 查询条件
     * @return 结果集
     */
    @Override
    public Pagination getRepairPage(Pagination pagination, Map<String, Object> map) {
        //数据总数查询
        long count = infoReviewMapper.getRepairPageCount(map);
        List<Map<String,Object>> list = infoReviewMapper.getRepairPage(pagination,map);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }

    /**
     * 杂项交易查询
     * @param pagination 分页信息
     * @param map 查询条件
     * @return 结果集
     */
    @Override
    public Pagination getMiscellaneousPage(Pagination pagination, Map<String, Object> map) {
        //数据总数查询
        long count = infoReviewMapper.getMiscellaneousPageCount(map);
        List<Map<String,Object>> list = infoReviewMapper.getMiscellaneousPage(pagination,map);
        pagination.setTotal(count);
        pagination.setRows(list);
        return pagination;
    }

	@Override
	public List<BasicPageExcel> basicPageExportExcel(Map<String, Object> map) { 
		return infoReviewMapper.basicPageExportExcel(map);
	}

	@Override
	public List<ShipmentPageExcel> shipmentPageExportExcel(Map<String, Object> map) { 
		return infoReviewMapper.shipmentPageExportExcel(map);
	}

	@Override
	public List<BackPageExcel> backPageExportExcel(Map<String, Object> map) { 
		return infoReviewMapper.backPageExportExcel(map);
	}

	@Override
	public List<RepairPageExcel> repairPageExportExcel(Map<String, Object> map) { 
		return infoReviewMapper.repairPageExportExcel(map);
	}

	@Override
	public List<MiscellaneousPageExcel> miscellaneousPageExportExcel(
			Map<String, Object> map) { 
		return infoReviewMapper.miscellaneousPageExcel(map);
	}
}
