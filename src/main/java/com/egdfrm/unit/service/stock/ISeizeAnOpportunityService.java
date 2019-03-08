package com.egdfrm.unit.service.stock;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.stock.SeizeAnOpportunity;

import java.util.List;
import java.util.Map;

/**
 * 成品借机_接口
 * Created by tyq on 17/3/15.
 */
public interface ISeizeAnOpportunityService {

    /**
     * 部门查询
     * @return
     */
    List<Map<String,Object>> queryDepts();

    /**
     * 用途查询
     * @return
     */
    List<Map<String,Object>> queryPurposes();


    /**
     * 获取借机单号
     * @return
     */
    void getNumber(Map<String,Object> map);

    /**
     * 子库查询
     * @return 数据集
     */
    List<Map<String,Object>> queryLibrarys();

    /**
     * 根据目的子库获取目的货位
     * @param goalSubLibrary 目的子库
     * @return 目的货位数据集
     */
    List<Map<String,Object>> queryGoalLocations(String goalSubLibrary);

    /**
     * 根据编码查询物料信息
     * @param code 编码
     * @return 物料信息
     */
    Map<String,Object> queryDescribe(String code);

    /**
     * 借机新增
     * @param saoty 数据
     * @return
     */
    int insert(SeizeAnOpportunity saoty);

    /**
     * 借机汇总分页查询
     * @param page 分页条件
     * @param seaoty 查询条件
     * @return
     */
    Pagination findSummaryPage(Pagination page, SeizeAnOpportunity seaoty);

    /**
     * 借机详情分页查询
     * @param page 分页条件
     * @param seaoty 查询条件
     * @return
     */
    Pagination findDetailedPage(Pagination page, SeizeAnOpportunity seaoty);

    /**
     * 根据借机单号查询头信息
     * @param num 借机单号
     * @return
     */
    SeizeAnOpportunity findByNumber(String num);

    /**
     * 根据借机单号查询借机行
     * @param num 借机单号
     * @return
     */
    List<SeizeAnOpportunity> findLines(String num);

    /**
     * 修改物流信息
     * @param seaoty
     * @return
     */
    int updateHeader(SeizeAnOpportunity seaoty);

    /**
     * 物流公司查询
     * @return
     */
    List<Map<String,Object>> queryExpress();

    /**
     * 销售
     * @param ids
     * @param evaluation 参考
     * @param date 实际归还时间
     * @return
     */
    String[] updateSale(int[] ids,String[] boacodeId,String userId,String evaluation,String date); 
    /**
     * 删除借机头表
     * @param headerId 
     * @return s[0]=s;s[1]=message   
     * @author	hgb
     * @date 2017-4-18
     */
    String[] deleteHeaders(String[] headerId);
    
    
    /**
     * 删除借机行表
     * @param lineId 
     * @return s[0]=s;s[1]=message   
     * @author	hgb
     * @date 2017-4-18
     */
    String[] deleteLines(String[] lineId);
}
