package com.egdfrm.unit.mapper.stock;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.stock.SeizeAnOpportunity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 成品借机_Mapper
 * Created by tyq on 17/3/15.
 */
public interface SeizeAnOpportunityMapper { 

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
     * @param map
     */
    void getNumber(@Param("map") Map<String,Object> map);

    /**
     * 获取子库
     * @return
     */
    List<Map<String,Object>> queryLibrarys();

    /**
     * 根据目的子库获取目的货位
     * @param goalSubLibrary 目的子库
     * @return 目的货位数据集
     */
    List<Map<String,Object>> queryGoalLocations(@Param("goalSubLibrary") String goalSubLibrary);

    /**
     * 根据编码查询物料信息
     * @param code 编码
     * @return 物料信息
     */
    Map<String,Object> queryDescribe(@Param("code") String code);

    /**
     * 借机头信息添加
     * @param saoty
     * @return
     */
    int insertHead(SeizeAnOpportunity saoty);

    /**
     * 获取借机头信息ID
     * @return
     */
    int getHeadId();

    /**
     * 获取借机行ID
     * @return
     */
    int getLineId();

    /**
     * 添加借机行
     * @param list
     * @return
     */
    int insertLine(@Param("list") List<SeizeAnOpportunity> list);

    /**
     * 借机数据总数查询
     * @param seaoty 查询条件
     * @return
     */
    long findSummaryPageCount(@Param("seaoty") SeizeAnOpportunity seaoty);

    /**
     * 借机汇总分页查询
     * @param page 分页条件
     * @param seaoty 查询条件
     * @return
     */
    List<SeizeAnOpportunity> findSummaryPage(@Param("page") Pagination page, @Param("seaoty") SeizeAnOpportunity seaoty);

    /**
     * 借机详情数据总数查询
     * @param seaoty 查询条件
     * @return
     */
    long findDetailedPageCount(@Param("seaoty") SeizeAnOpportunity seaoty);

    /**
     * 借机详情数据分页查询
     * @param page 分页条件
     * @param seaoty 查询条件
     * @return
     */
    List<SeizeAnOpportunity> findDetailedPage(@Param("page") Pagination page, @Param("seaoty") SeizeAnOpportunity seaoty);

    /**
     * 根据借机单号查询头信息
     * @param num 借机单号
     * @return
     */
    SeizeAnOpportunity findByNumber(@Param("num") String num);

    /**
     * 根据借机单号查询借机行
     * @param num 借机单号
     * @return
     */
    List<SeizeAnOpportunity> findLines(@Param("num")String num);

    /**
     * 修改物流信息
     * @param seaoty
     * @return
     */
    int updateHeader(@Param("seaoty") SeizeAnOpportunity seaoty);

    /**
     * 物流公司查询
     * @return
     */
    List<Map<String,Object>> queryExpress();

    /**
     * 销售
     * @param ids
     * @param evaluation 参考
     * @return
     */
    int updateSale(@Param("ids") int[] ids, @Param("evaluation") String evaluation,@Param("date") String date);
    /**
     * 销售 的添加方法
     */
    void callSaleShip(Map<String, Object> map);
    
    void deleteHeader(Map<String, Object> map);
    
    void deleteLine( Map<String, Object> map);
}
