package com.egdfrm.unit.service.productionManagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.excelmodel.WeighMaintenanceExcel;
import com.egdfrm.unit.mapper.productionManagement.WeighMaintenanceMapper;
/**
 * 包装箱重量维护 --服务类
 * @author hgb
 * @date 2017-4-17
 */
@Service
public class WeighMaintenanceService {
	
	@Autowired
	private WeighMaintenanceMapper weighMaintenanceMapper;
	
	public Pagination getList(Pagination pagination,Map<String, Object> map){
		//查询数据总数
		long count = weighMaintenanceMapper.getCount(map);
		List<WeighMaintenanceExcel> list = weighMaintenanceMapper.getWeighMainList(pagination,map);
		pagination.setTotal(count);
		pagination.setRows(list);
		return pagination;
	}

	/**
	 *  确认
	 * @date 2017-4-20
	 */
	public Map<String, Object> getObjectWipEntityName(Map<String, Object> map){
		List<Map<String, Object>> list = weighMaintenanceMapper.getObjectWipEntityName(map);
		Map<String, Object> map2 = null;
		if(list!=null&&list.size()>0){
			map2=list.get(0);
		}
		return map2;
	}
	
	/**
	 *  工单选择（无效了）
	 * @date 2017-4-17
	 */ 
	public Pagination getWipEntityName(Pagination pagination,Map<String, Object> map){
		 
		long count = weighMaintenanceMapper.getWipEntityNameCount(map);
		List<Map<String, Object>> list = weighMaintenanceMapper.getWipEntityNameList(pagination,map);
		pagination.setTotal(count);
		pagination.setRows(list);
		return pagination;
	}
	
	/**
	 * 验证是否存在 存在返回 true
	 */
	private  boolean isNull(String wip_entity_id){ 
		List<String> ls = weighMaintenanceMapper.getListByWipEntityId(wip_entity_id);
		 if(ls!=null&&ls.size()>0){ 
			 return ls.get(0)==null?false:true;
		 }
		return false;
		
	}
	/**
	 *  saveOrUpdate
	 */
	public boolean saveOrUpdate(String wip_entity_id,String gross_weight,String pack_weight,String uid){
		boolean b = false;
		Map<String, Object> map  = new HashMap<String, Object>();
		map.put("wip_entity_id", wip_entity_id);
		map.put("gross_weight", gross_weight);
		map.put("pack_weight", pack_weight);
		map.put("uid", uid);
		if(this.isNull(wip_entity_id)){//修改
			weighMaintenanceMapper.update(map);
			b = true;
		}else {//添加
			weighMaintenanceMapper.add(map);
			b = true;
		}
		return b;
	}
	
	/**
	 * 导出excel
	 * @param map
	 * @return   
	 * @author	hgb
	 * @date 2017-4-18
	 */
	public List<WeighMaintenanceExcel> exportExcel(Map<String, Object> map){
		return weighMaintenanceMapper.exportExcel(map);
	}
}
