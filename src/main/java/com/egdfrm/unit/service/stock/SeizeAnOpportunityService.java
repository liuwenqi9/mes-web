package com.egdfrm.unit.service.stock;

import com.egdfrm.extend.common.DbReturnParameter;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.stock.SeizeAnOpportunityMapper;
import com.egdfrm.unit.model.stock.SeizeAnOpportunity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

/**
 * 成品借机_接口实现
 * Created by tyq on 17/3/15.
 */
@Service
public class SeizeAnOpportunityService implements ISeizeAnOpportunityService {

    @Autowired
    private SeizeAnOpportunityMapper seizeAnOpportunityMapper;

    /**
     * 查询部门
     * @return
     */
    @Override
    public List<Map<String, Object>> queryDepts() {
        return seizeAnOpportunityMapper.queryDepts();
    }

    /**
     * 用途查询
     * @return
     */
    @Override
    public List<Map<String, Object>> queryPurposes() {
        return seizeAnOpportunityMapper.queryPurposes();
    }

    /**
     * 获取借机单号
     */
    @Override
    public void getNumber(Map<String,Object> map) {
        seizeAnOpportunityMapper.getNumber(map);
    }

    /**
     * 获取子库
     * @return
     */
    @Override
    public List<Map<String, Object>> queryLibrarys() {
        return seizeAnOpportunityMapper.queryLibrarys();
    }

    /**
     * 根据目的子库获取目的货位
     * @param goalSubLibrary 目的子库
     * @return 目的货位数据集
     */
    @Override
    public List<Map<String, Object>> queryGoalLocations(String goalSubLibrary) {
        return seizeAnOpportunityMapper.queryGoalLocations(goalSubLibrary);
    }

    /**
     * 根据编码查询物料信息
     * @param code 编码
     * @return 物料信息
     */
    @Override
    public Map<String, Object> queryDescribe(String code) {
        return seizeAnOpportunityMapper.queryDescribe(code);
    }

    /**
     * 借机新增
     * @param saoty 数据
     * @return
     */
    @Override
    public int insert(SeizeAnOpportunity saoty) {
        //获取头ID
        int headId = seizeAnOpportunityMapper.getHeadId();
        Date date = Calendar.getInstance().getTime();
        saoty.setId(headId);
        saoty.setUpdateTime(date);
        saoty.setCreateTime(date);
        //添加头信息
        int result = seizeAnOpportunityMapper.insertHead(saoty);
        String[] codeIds = saoty.getCodeId().split(",");
        String[] quantitys = saoty.getQuantity().split(",");
        String[] returnTimes = saoty.getReturnTime().split(",");
        String[] sourceSubLibrarys = saoty.getSourceSubLibrary().split(",");
        String[] goalSubLibrarys = saoty.getGoalSubLibrary().split(",");
        String[] goalLocations = saoty.getGoalLocation().split(",");
        List<SeizeAnOpportunity> list = new ArrayList<>();
        for (int i = 0; i < codeIds.length; i++) {
            SeizeAnOpportunity seao = new SeizeAnOpportunity();
            seao.setId(headId);
            seao.setOrgId(saoty.getOrgId());
            seao.setUserId(saoty.getUserId());
            seao.setCreateTime(date);
            seao.setUpdateTime(date);
            seao.setCodeId(codeIds[i]);
            seao.setQuantity(quantitys[i]);
            seao.setReturnTime(returnTimes[i]);
            seao.setSourceSubLibrary(sourceSubLibrarys[i]);
            seao.setGoalSubLibrary(goalSubLibrarys[i]);
            seao.setGoalLocation(goalLocations[i]);
            list.add(seao);
        }
        //添加行信息
        result = seizeAnOpportunityMapper.insertLine(list);
        return result;
    }

    /**
     * 借机汇总分页查询
     * @param page 分页条件
     * @param seaoty 查询条件
     * @return
     */
    @Override
    public Pagination findSummaryPage(Pagination page, SeizeAnOpportunity seaoty) {
        long count = seizeAnOpportunityMapper.findSummaryPageCount(seaoty);
        List<SeizeAnOpportunity> list = seizeAnOpportunityMapper.findSummaryPage(page,seaoty);
        page.setTotal(count);
        page.setRows(list);
        return page;
    }

    /**
     * 借机详情分页查询
     * @param page 分页条件
     * @param seaoty 查询条件
     * @return
     */
    @Override
    public Pagination findDetailedPage(Pagination page, SeizeAnOpportunity seaoty) {
        long count = seizeAnOpportunityMapper.findDetailedPageCount(seaoty);
        List<SeizeAnOpportunity> list = seizeAnOpportunityMapper.findDetailedPage(page,seaoty);
        page.setTotal(count);
        page.setRows(list);
        return page;
    }

    /**
     * 根据借机单号查询头信息
     * @param num 借机单号
     * @return
     */
    @Override
    public SeizeAnOpportunity findByNumber(String num) {
        return seizeAnOpportunityMapper.findByNumber(num);
    }

    /**
     * 根据借借机单号查询借机行
     * @param num 借机单号
     * @return
     */
    @Override
    public List<SeizeAnOpportunity> findLines(String num) {
        return seizeAnOpportunityMapper.findLines(num);
    }

    /**
     * 修改物流信息
     * @param seaoty
     * @return
     */
    @Override
    public int updateHeader(SeizeAnOpportunity seaoty) {
        return seizeAnOpportunityMapper.updateHeader(seaoty);
    }

    /**
     * 物流公司查询
     * @return
     */
    @Override
    public List<Map<String, Object>> queryExpress() {
        return seizeAnOpportunityMapper.queryExpress();
    }


    /**
     * 销售
     * @param ids
     * @param evaluation 参考
     * @param date 实际归还时间
     * @return
     */
    @Override
    public String[] updateSale(int[] ids,String[] barcodeId,String evaluation,String date,String userId) {
    	String[] re = new String[2]; 
    	int bb = seizeAnOpportunityMapper.updateSale(ids,evaluation,date);
    	if(bb<0){
    		re[0] = "E";
			re[1] = "无法更新！";
    		return re;
    	} 
    	for (int i = 0; i < barcodeId.length; i++) {
    		DbReturnParameter dbreturn = new DbReturnParameter();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(MesConstants.DBRETURN, dbreturn);
			map.put("userId", userId);
			map.put("barcodeId", barcodeId[i]);
			seizeAnOpportunityMapper.callSaleShip(map);
			String status = dbreturn.getxStatus();
			if(!MesConstants.SUCCESS.equals(status)){
				re[0] = status;
				re[1] = dbreturn.getxMessage();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				break;
			}else{
				re[0] = status;
				re[1] = dbreturn.getxMessage();
			}
		}
        return re;
    }

	@Override
	public String[] deleteHeaders(String[] headerId) { 
		String[] re = new String[2]; 
		for (int i = 0; i < headerId.length; i++) { 
			DbReturnParameter dbreturn = new DbReturnParameter();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(MesConstants.DBRETURN, dbreturn);
			map.put("headerId", headerId[i]);
			seizeAnOpportunityMapper.deleteHeader(map);  
			String status = dbreturn.getxStatus();
			if(!MesConstants.SUCCESS.equals(status)){
				re[0] = status;
				re[1] = dbreturn.getxMessage();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				break;
			}else{
				re[0] = status;
				re[1] = dbreturn.getxMessage();
			}
		}
		return re;
	}

	@Override
	public String[] deleteLines(String[] lineId) {  
		String[] re = new String[2]; 
		for (int i = 0; i < lineId.length; i++) { 
			DbReturnParameter dbreturn = new DbReturnParameter();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(MesConstants.DBRETURN, dbreturn);
			map.put("lineId", lineId[i]);
			seizeAnOpportunityMapper.deleteLine(map) ;
			String status = dbreturn.getxStatus();
			if(!MesConstants.SUCCESS.equals(status)){
				re[0] = status;
				re[1] = dbreturn.getxMessage();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				break;
			}else{
				re[0] = status;
				re[1] = dbreturn.getxMessage();
			}
		}
		return re; 
	}
}
