package com.egdfrm.unit.service;

import com.egdfrm.core.common.Constants;
import com.egdfrm.core.common.JsonResult;
import com.egdfrm.core.mapper.standard.TtApplicationUserMapper;
import com.egdfrm.core.model.expand.PageResult;
import com.egdfrm.core.model.standard.TtApplicationUser; 
import com.egdfrm.core.service.BaseService; 
import com.egdfrm.unit.mapper.standard.MesCapacityPackingMapper;
import com.egdfrm.unit.model.standard.MesCapacityPacking;  

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  

@Service
public class MesCapacityPackingService extends BaseService
{

  @Autowired
  private MesCapacityPackingMapper mapper;
  @Autowired
  private TtApplicationUserMapper userMapper;
  
  /**
   * <p>
   * Description: 包装箱容量设置列表
   * </p>  
   * @param page 当前页
   * @param rows 每页行数
   * @param segment1 物料编号   
   * @return 包装箱容量设置列表
   * @throws Exception
   */
  public Map<String, Object> getMesCapacityPackingList(int page, int rows, String segment1)
    throws Exception
  {	//select
    StringBuffer sql = new StringBuffer();
    sql.append(" select ms.segment1, ");
    sql.append(" ms.description, ");
    sql.append(" mv.segment2 prod_type, ");
    sql.append(" msc.s_quantity, ");
    sql.append(" msc.b_quantity, ");
    sql.append(" msc.last_update_date, ");
    sql.append(" msct.login_name, ");
    sql.append(" msc.HEADER_ID, ");
    sql.append(" msc.ORGANIZATION_ID, ");
    sql.append(" msc.INVENTORY_ITEM_ID "); 
    //from
    sql.append(" from inv.mtl_system_items_b ms, ");
    sql.append(" apps.mtl_item_categories_v mv, ");
    sql.append(" mes.mes_capacity_packing msc,");
    sql.append(" mes.tt_application_user msct");
    //where
    sql.append(" where mv.category_set_name(+) = '销售类别集' ");
    sql.append(" and mv.inventory_item_id(+) = ms.inventory_item_id ");
    sql.append(" and mv.INVENTORY_ITEM_ID = msc.inventory_item_id ");
    sql.append(" and msc.last_updated_by = msct.user_id ");
    sql.append(" and ms.organization_id = 101 ");
    sql.append(" and mv.organization_id(+) = 102 ");
    sql.append(" and substr(ms.segment1, 1, 2) in ('18', '19') ");
    sql.append(" and ms.inventory_item_status_code <> 'Inactive' ");

    if (!StringUtils.isEmpty(segment1)) {
      this.log.info("SEGMENT1 is not null");
      sql.append(" and ms.segment1 like '%" + segment1 + "%' ");
    }
    sql.append(" order by msc.header_id desc ");
    PageResult pr = getPrs().pageQuery(sql.toString(), rows, page);
    Map<String, Object> rv = new HashMap<String, Object>();
    rv.put("total", Integer.valueOf(pr.getTotalRecords()));
    rv.put("rows", pr.getRecords());
    return rv;
  }
  
  /**
   * 选择页面的数据
   * @param page 当前页
   * @param rows 每页行数
   * @param segment1 物料编号 
   * @param prodType 产品类型     
   * @return easyui 列表 分页数据
   * @throws Exception 
   */ 
  public Map<String, Object> getSegment1(int page, int rows, String segment1,String prodType) throws Exception{
	  StringBuffer sql = new StringBuffer();
	    sql.append(" select ms.organization_id, ");
	    sql.append(" ms.inventory_item_id, ");
	    sql.append(" ms.segment1, ");
	    sql.append(" ms.description, ");
	    sql.append(" mv.segment2 prod_type "); 
		//from
	    sql.append(" from inv.mtl_system_items_b ms, ");
	    sql.append(" apps.mtl_item_categories_v mv ");
		//where
	    //where
	    sql.append(" where mv.category_set_name(+) = '销售类别集' ");
	    sql.append(" and mv.inventory_item_id(+) = ms.inventory_item_id "); 
	    sql.append(" and ms.organization_id = 101 ");
	    sql.append(" and mv.organization_id(+) = 102 ");
	    sql.append(" and substr(ms.segment1, 1, 2) in ('18', '19') ");
	    sql.append(" and ms.inventory_item_status_code <> 'Inactive' ");
	    
	    if (!StringUtils.isEmpty(segment1)) { 
	        sql.append(" and ms.segment1 like '%" + segment1 + "%' ");
	      }
	    
	    if (!StringUtils.isEmpty(prodType)) { 
	        sql.append(" and mv.segment2 like '%" + prodType + "%' ");
	      }
		PageResult pr = getPrs().pageQuery(sql.toString(), rows, page);
	    Map<String, Object> rv = new HashMap<String, Object>();
	    rv.put("total", Integer.valueOf(pr.getTotalRecords()));
	    rv.put("rows", pr.getRecords());
	    return rv;
 }
  
  /**
   * 添加包装箱
   * @param loginName 当前登录名 
   * @param mcp model
   * @return x
   */ 
  public JsonResult<String> addCapacityPacking(String loginName,MesCapacityPacking mcp){
	 //this.log.info("6666"+mcp.toString());
	 //this.log.info(loginName);
	 TtApplicationUser user =  userMapper.selectByPrimaryKey(loginName); 
	 mcp.setLastUpdatedBy(user.getUserId()); 
	 mcp.setCreatedBy(user.getUserId());
	 this.mapper.insert(mcp); 
	 return new JsonResult<String>(Constants.SUCCESS, "保存成功");
  }


  public JsonResult<String>  editCapacityPacking(String loginName,MesCapacityPacking mcp){
	  TtApplicationUser user =  userMapper.selectByPrimaryKey(loginName);
	  mcp.setLastUpdatedBy(user.getUserId());
	  this.mapper.updateByPrimaryKey(mcp);
	 // this.log.info("6666"+mcp.toString());
	  return new JsonResult<String>(Constants.SUCCESS, "修改成功");
  }
  
  public List<Map<String,Object>> getCapacityPackings(String segment1){
	  	StringBuffer sql = new StringBuffer();
	    sql.append(" select ms.segment1, ");
	    sql.append(" ms.description, ");
	    sql.append(" mv.segment2 prod_type, ");
	    sql.append(" msc.s_quantity, ");
	    sql.append(" msc.b_quantity, ");
	    sql.append(" to_char(msc.last_update_date,'YYYY-MM-DD') last_update_date, ");
	    sql.append(" msct.login_name, ");
	    sql.append(" msc.HEADER_ID, ");
	    sql.append(" msc.ORGANIZATION_ID, ");
	    sql.append(" msc.INVENTORY_ITEM_ID "); 
	    //from
	    sql.append(" from inv.mtl_system_items_b ms, ");
	    sql.append(" apps.mtl_item_categories_v mv, ");
	    sql.append(" mes.mes_capacity_packing msc,");
	    sql.append(" mes.tt_application_user msct");
	    //where
	    sql.append(" where mv.category_set_name(+) = '销售类别集' ");
	    sql.append(" and mv.inventory_item_id(+) = ms.inventory_item_id ");
	    sql.append(" and mv.INVENTORY_ITEM_ID = msc.inventory_item_id ");
	    sql.append(" and msc.last_updated_by = msct.user_id ");
	    sql.append(" and ms.organization_id = 101 ");
	    sql.append(" and mv.organization_id(+) = 102 ");
	    sql.append(" and substr(ms.segment1, 1, 2) in ('18', '19') ");
	    sql.append(" and ms.inventory_item_status_code <> 'Inactive' ");
	    

	    if (!StringUtils.isEmpty(segment1)) {
	      this.log.info("SEGMENT1 is not null");
	      sql.append(" and ms.segment1 like '%" + segment1 + "%' ");
	    }
	    return this.getImqm().queryMap(sql.toString());
  }
  
  public List<Map<String,Object>> getCapacityPackingselects(String segment1,String prodType){
	  StringBuffer sql = new StringBuffer();
	    sql.append(" select ms.organization_id, ");
	    sql.append(" ms.inventory_item_id, ");
	    sql.append(" ms.segment1, ");
	    sql.append(" ms.description, ");
	    sql.append(" mv.segment2 prod_type "); 
		//from
	    sql.append(" from inv.mtl_system_items_b ms, ");
	    sql.append(" apps.mtl_item_categories_v mv ");
		//where
	    //where
	    sql.append(" where mv.category_set_name(+) = '销售类别集' ");
	    sql.append(" and mv.inventory_item_id(+) = ms.inventory_item_id "); 
	    sql.append(" and ms.organization_id = 101 ");
	    sql.append(" and mv.organization_id(+) = 102 ");
	    sql.append(" and substr(ms.segment1, 1, 2) in ('18', '19') ");
	    sql.append(" and ms.inventory_item_status_code <> 'Inactive' ");
	    sql.append(" and ms.inventory_item_id not in(select mes.inventory_item_id from mes_capacity_packing mes) ");
	    
	    if (!StringUtils.isEmpty(segment1)) { 
	        sql.append(" and ms.segment1 like '%" + segment1 + "%' ");
	      }
	    
	    if (!StringUtils.isEmpty(prodType)) { 
	        sql.append(" and mv.segment2 like '%" + prodType + "%' ");
	      }
	    return this.getImqm().queryMap(sql.toString());   
  }
  
  public boolean deleteCapacityPacking(String headerId){
  	return this.mapper.deleteByPrimaryKey(headerId)>0?true:false;
  }
  
}