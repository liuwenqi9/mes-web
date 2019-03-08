package com.egdfrm.unit.service.productionManagement;

  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.egdfrm.core.mapper.standard.TtApplicationUserMapper;  
import com.egdfrm.core.service.BaseService; 
import com.egdfrm.extend.common.DbReturnParameter;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.expand.UserMapper;
import com.egdfrm.unit.mapper.standard.InspectionMapper;

@Service
public class InspectionService extends BaseService{
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private TtApplicationUserMapper ttUserMapper;
	@Autowired
	private InspectionMapper inspectionMapper;
	
	
	/**
	 * 报检页面的分页数据 
	 * @return   
	 * @author	hgb
	 * @date 2017-2-6
	 */
	public Pagination getInspectionListNew(String loginName,Pagination pagination,Map<String, Object> map){
		map.put("flag", this.isRelevancePlanLineCode(loginName)==true?"true":"false");
		if(this.isRelevancePlanLineCode(loginName)){
			List<Map<String, Object>> list = this.getPlanLines(loginName);
			String pl = "";
			if(list!=null&&!list.isEmpty()){
				//获取当前登录的产线
				pl = list.get(0).get("FLEX_VALUE").toString();
				map.put("line", pl);
			}
		}
		
		//查询数据总数
		long count = this.inspectionMapper.getInspectionCount(map);
		List<Map<String, Object>> list = this.inspectionMapper.getInspectionList(pagination, map);
		pagination.setTotal(count);
		pagination.setRows(list);
		return pagination;
	}
	
	
	/**
	 * 报检单的分页数据 (作废)
	 * @param productionLine 生产线
	 * @param workOrderNumber 工单号
	 * @param fullPackIdentify 满包标识
	 * @param inspectionIdentify 报检标识
	 * @return
	 */
	public List<Map<String,Object>> getInspectionList(String loginName, String productionLine,
			String workOrderNumber,String fullPackIdentify,String inspectionIdentify){
		//获取登录用户的角色
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select rownum,v.* ");
		sql.append(" ,case CHECK_STATUS when 'N' then '未报检' when 'R' then '已提交' when 'P' then  '已通过'");
		sql.append("  when 'E' then '被拒绝' end as CHECK_STATUS_F");
		sql.append("  ");
		//form
		sql.append(" from apps.mes_packing_headers_check_v v ");
		//where
		sql.append(" where 1=1 ");
		if(this.isRelevancePlanLineCode(loginName)){ //是否是
			List<Map<String, Object>> list = this.getPlanLines(loginName);
			String pl = "";
			if(list!=null&&!list.isEmpty()){
				//获取当前登录的产线
				pl = list.get(0).get("FLEX_VALUE").toString();
			}
			sql.append(" and v.plan_line = '"+pl+"' ");
		}else{//FLEX_VALUE
			if(!StringUtils.isEmpty(productionLine)&&!productionLine.equals("0")){
				sql.append(" and v.plan_line = '"+productionLine+"' ");
			}
		} 
		
		if(!StringUtils.isEmpty(workOrderNumber)){
			sql.append(" and v.WIP_ENTITY_NAME like '%"+workOrderNumber+"%' ");
		}
		if(!StringUtils.isEmpty(fullPackIdentify)&&!fullPackIdentify.equals("0")){
			sql.append(" and v.pack_flag ='"+fullPackIdentify+"' ");
		}
		if(!StringUtils.isEmpty(inspectionIdentify)&&!inspectionIdentify.equals("0")){
			sql.append(" and v.check_status ='"+inspectionIdentify+"' ");
		} 
		
		sql.append(" ORDER BY v.plan_line,v.WIP_ENTITY_NAME,v.barcode_text ");
		return super.getImqm().queryMap(sql.toString());
	}
	
	/**
	 * 根据登录名 判断是否关联生产线，
	 * @param loginName
	 * @return   false：不是, true：是
	 * @author	hgb
	 * @date 2017-1-12
	 */
	private boolean isRelevancePlanLineCode(String loginName){ 
		boolean b = false;
		List<Map<String, Object>> lm =  userMapper.getPlanLineCodeCodeByLoginName(loginName);
		if(lm!=null && !lm.isEmpty()){
			Object o = lm.get(0).get("PLAN_LINE_CODE");
			//不等0   已关联 
			if(o!=null){
				String PlanLineCode = o.toString();
				if(!PlanLineCode.equals("0")){
					b=true;
				}
			} 
			
		}
		return b;
	}
	
	/** 
	 * 
	 * @param id 
	 * @return  获取客户机型
	 * @author	hgb
	 * @date 2017-1-4
	 */
	public String getClientInfo(String[] id){
		/*//map存放报检单上的数据
		Map<String, Object> map = new HashMap<String, Object>();
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select v.*, ( ");
		sql.append(" select sum(v.pack_quantity)  from apps.mes_packing_headers_check_v v ");
		sql.append(" where 1 = 1 "); 	
		if(id!=null)
		{    
			String sqlin = "";
			int i=0;
			for (String str : id) {
				if(i++==0)
				   sqlin+=str;
				else
					sqlin+=","+str;
			} 
			this.log.info("test_sqlin="+sqlin);
			sql.append(" and v.packing_barcode_id in("+sqlin+") "); 	
		}
		sql.append(" ) pack_quantity1"); 
		sql.append(" from apps.mes_packing_headers_check_v v ");
		 
		sql.append(" where 1=1 "); 
		if(id!=null)
		{	
			sql.append(" and v.packing_barcode_id="+id[0]);
		} 
		 map = super.getImqm().queryMap(sql.toString()).get(0);*/
		 
		/*//获取客户机型
		String sql1="SELECT c.CUSTOM_MODEL CLIENT FROM apps.cux_item_perporty c where c.inventory_item_id in (" +
				"select M.Inventory_Item_Id from  mes.mes_packing_headers M where M.PACKING_BARCODE_ID=" +
				id[0]+")"; */
		String sql1 = "SELECT nvl(c.CUSTOM_MODEL,'0') CLIENT FROM apps.cux_item_perporty c ,mes.mes_packing_headers M where c.inventory_item_id(+) = M.Inventory_Item_Id  and rownum = 1 and M.PACKING_BARCODE_ID="+id[0];
		 Map<String, Object> m = super.getImqm().queryMap(sql1).get(0);
		 String CLIENT = m.get("CLIENT").toString();
		 
		
		return CLIENT;
	} 
	 /**
	  *  生成报检单
	  * @param id  PACKING_BARCODE_ID
	  * @param loginName    
	  * @author	hgb
	  * @date 2017-1-4
	  */
	public String generateInspection(String[] id,String loginName){
		String inspectionNumber = "";
		//生成报检单号
		Map<String, Object> paramsOut = new HashMap<String, Object>(0);
		paramsOut.put("inspectionNumberOut", null);
		inspectionMapper.callGenerateInspection(paramsOut);
		Object o = paramsOut.get("inspectionNumberOut");
		inspectionNumber = o.toString(); 
		return inspectionNumber;
	}
	
	/**
	 *  去报检
	 * @param id
	 * @param loginName
	 * @param inspectionNumber   
	 * @author	hgb
	 * @date 2017-1-4
	 */
	public void toInspection(String[] id,String loginName,String inspectionNumber){
		// 报检
		for (int i = 0; i < id.length; i++) {
			DbReturnParameter dbreturn = new DbReturnParameter();
			Map<String, Object> params = new HashMap<String, Object>(0);
			params.put("userId", ttUserMapper.selectByPrimaryKey(loginName)
					.getUserId());
			params.put("packingBarcodeId", id[i]);
			params.put("inspectionNumber", inspectionNumber);
			params.put(MesConstants.DBRETURN, dbreturn);
			inspectionMapper.callInspections(params);
			if (!MesConstants.SUCCESS.equals(dbreturn.getxStatus())) {
				log.debug("xxxxxxxx");
				TransactionAspectSupport.currentTransactionStatus()
						.setRollbackOnly();
			}
		}
	}
	
	public List<Map<String,Object>> getInspectionByKey(String id){ 
		StringBuffer sql = new StringBuffer();
		sql.append(" select v.* ");
		sql.append("  ");
		//form
		sql.append(" from apps.mes_packing_headers_check_v v ");
		//where
		sql.append(" where 1=1 ");
		if(id!=null && !id.equals("")){
			sql.append(" and v.packing_barcode_id="+id);
		}
		return super.getImqm().queryMap(sql.toString());
	}
	
	
	/**
	 * 检验页面的分页数据 
	 * @return   
	 * @author	hgb
	 * @date 2017-2-5
	 */
	public Pagination getInspectionsListNew(Pagination pagination,Map<String, Object> map){
		//查询数据总数
		long count = this.inspectionMapper.getInspectionsCount(map);
		List<Map<String, Object>> list = this.inspectionMapper.getInspectionsList(pagination, map);
		pagination.setTotal(count);
		pagination.setRows(list);
		return pagination;
	}
	
	/**
	 *  检验页面的分页数据(作废)
	 * @param planLine 生产线
	 * @param workOrderNumber 工单号
	 * @param inspectNumber 报检单号
	 * @param checkStatus 报检结果
	 * @return
	 */
	public List<Map<String, Object>> getInspectionsList(String planLine,
			String workOrderNumber,String inspectNumber,String checkStatus){
		StringBuffer sql = new StringBuffer();
		sql.append(" select v.* ");
		sql.append(" ,to_char(CHECK_DATE,'YYYY-MM-DD HH:MM:SS') CHECK_DATE_F ");
		sql.append(" ,to_char(INSPECT_DATE,'YYYY-MM-DD HH:MM') INSPECT_DATE_F ");
		sql.append(" ,case CHECK_STATUS when 'N' then '未报检' when 'R' then '已提交' when 'P' then  '已通过'");
		sql.append("  when 'E' then '被拒绝' end as CHECK_STATUS_F");
		//form
		sql.append(" from apps.mes_packing_headers_check_v v ");
		//where
		sql.append(" where 1=1  AND (v.check_status='R' OR v.check_status='P') ");
		
		if(!StringUtils.isEmpty(planLine)&&!planLine.equals("0")){
			sql.append(" and v.plan_line = '"+planLine+"' ");
		}
		if(!StringUtils.isEmpty(workOrderNumber)){
			sql.append(" and v.WIP_ENTITY_NAME like '%"+workOrderNumber+"%' ");
		}  
		if(!StringUtils.isEmpty(inspectNumber)){
			sql.append(" and v.inspect_number like '%"+inspectNumber+"%' ");
		} 
		if(!StringUtils.isEmpty(checkStatus)&&!checkStatus.equals("0")){
			sql.append(" and v.check_status ='"+checkStatus+"' ");
		}
		return super.getImqm().queryMap(sql.toString());
	}
	
	/**
	 * <P>非系统管理员 要按loginName 去查询生产线
	 * @param loginName
	 * @return   
	 * @author	hgb
	 * @date 2017-1-5
	 */
	public List<Map<String, Object>> getPlanLines(String loginName){
		if(this.isRelevancePlanLineCode(loginName)){
			return userMapper.getPlanLineByLoginName(loginName);
		} 
		return userMapper.getPlanLines();
	}
	/**
	 *  去查询生产线
	 * @return   
	 * @author	hgb
	 * @date 2017-1-6
	 */
	public List<Map<String, Object>> getPlanLines(){  
		return userMapper.getPlanLines();
	}
	
	/** 
	 * 去检验（）
	 * @param checkStatus （P或E）
	 * @param packingBarcodeId
	 * @param checkMsg 不良现象
	 * @param speQty 严重
	 * @param majorQty 主要
	 * @param secQty 次要
	 * @param checkRemark 备注
	 * @author	hgb
	 * @date 2017-1-3
	 */
	public Boolean toInspection(String checkStatus,String[] packingBarcodeId,String checkMsg,
			String speQty,String majorQty,String secQty, String checkRemark,String loginName ){
		boolean b = true; 
		for (int i = 0; i < packingBarcodeId.length; i++) {
			DbReturnParameter dbreturn = new DbReturnParameter();
			Map<String, Object> params = new HashMap<String, Object>(0);
			params.put("userId",ttUserMapper.selectByPrimaryKey(loginName).getUserId()); 
			params.put("packingBarcodeId", packingBarcodeId[i]);
			params.put("checkStatus", checkStatus);
			params.put("checkMsg", checkMsg);
			params.put("speQty", speQty);
			params.put("majorQty", majorQty);
			params.put("secQty", secQty);
			params.put("checkRemark", checkRemark);
			params.put(MesConstants.DBRETURN, dbreturn); 
			inspectionMapper.callInspection(params); 
			String s = dbreturn.getxStatus();
			if(!MesConstants.SUCCESS.equals(s)){
				b = false;
				log.debug("xxxxxxxx");
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}
		return b;  
	}
	
	/**
	 * 验证数据是否有效 <P> 
	 * @return   true：有效, false：无效
	 * @author	hgb
	 * @date 2017-1-5
	 */
	public Boolean isValid(String[] id){
		boolean falg = true;
		for (int i = 0; i < id.length; i++) { 
			List<String> checkStatus=inspectionMapper.getCheckStatusById(id[i]);
		    if(checkStatus!=null && !checkStatus.isEmpty()){//存在
	    	String m=checkStatus.get(0);
	    	if(m!=null&& (m.equals("P") || m.equals("R")) ){
	    		falg = false;
	    		break;  
	    		}
		    }
		} 
		return falg;
	}
	
	
	/**
	 * 验证检验数据是否有效 <P> 
	 * @return   true：有效, false：无效
	 * @author	hgb
	 * @date 2017-1-5
	 */
	public Boolean isValid2(String[] id){
		boolean falg = true;
		for (int i = 0; i < id.length; i++) { 
			List<String> checkStatus=inspectionMapper.getCheckStatusById(id[i]);
		    if(checkStatus!=null && !checkStatus.isEmpty()){//存在
	    	String m=checkStatus.get(0);
	    	if(m!=null&& (m.equals("P")) ){
	    		falg = false;
	    		break;  
	    		}
		    }
		} 
		return falg;
	}
	
}
