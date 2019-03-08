/**
 * LookupService.java
 * Created at 2016-12-01
 * Created by 兰继明
 * Copyright (C) unit
 */
package com.egdfrm.unit.service.pda;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egdfrm.core.common.Constants;
import com.egdfrm.core.common.JsonResult;
import com.egdfrm.core.common.SystemParam;
import com.egdfrm.core.common.UUID;
import com.egdfrm.core.mapper.standard.MdSupplierMapper;
import com.egdfrm.core.mapper.standard.TtApplicationUserMapper;
import com.egdfrm.core.mapper.standard.TtFunctionMapper;
import com.egdfrm.core.mapper.standard.TtOrganizationMapper;
import com.egdfrm.core.mapper.standard.TtPurviewMapper;
import com.egdfrm.core.mapper.standard.TtUserFunctionMapper;
import com.egdfrm.core.mapper.standard.TtUserJobMapper;
import com.egdfrm.core.mapper.standard.TtUserRoleMapper;
import com.egdfrm.core.model.expand.PageResult;
import com.egdfrm.core.model.standard.MdSupplier;
import com.egdfrm.core.model.standard.TtApplicationUser;
import com.egdfrm.core.model.standard.TtApplicationUserCriteria;
import com.egdfrm.core.model.standard.TtFunction;
import com.egdfrm.core.model.standard.TtOrganization;
import com.egdfrm.core.model.standard.TtOrganizationCriteria;
import com.egdfrm.core.model.standard.TtPurview;
import com.egdfrm.core.model.standard.TtPurviewKey;
import com.egdfrm.core.model.standard.TtUserFunction;
import com.egdfrm.core.model.standard.TtUserFunctionCriteria;
import com.egdfrm.core.model.standard.TtUserJob;
import com.egdfrm.core.model.standard.TtUserJobCriteria;
import com.egdfrm.core.model.standard.TtUserRole;
import com.egdfrm.core.model.standard.TtUserRoleCriteria;
import com.egdfrm.core.service.BaseService;
import com.egdfrm.extend.common.JsonObjectConverTools;
import com.egdfrm.unit.common.Utils;
import com.egdfrm.unit.mapper.standard.MesAfterSaleMapper;
import com.egdfrm.unit.mapper.standard.MesLookupsMapper;
import com.egdfrm.unit.mapper.standard.MesLookupsTypeMapper;
import com.egdfrm.unit.model.standard.MesAfterSale;
import com.egdfrm.unit.model.standard.MesLookups;
import com.egdfrm.unit.model.standard.MesLookupsCriteria;
import com.egdfrm.unit.model.standard.MesLookupsType;
import com.egdfrm.unit.model.standard.MesLookupsTypeCriteria;

/**
 * <p>
 * ClassName: AfterSaleService
 * </p>
 * <p>
 * Description: 售后退货/发货
 * </p>
 * <p>
 * Author: 兰继明
 * </p>
 * <p>
 * Date: 2016年12月20日
 * </p>
 */
@Service
public class AfterSaleService extends BaseService {

	/**
	 * <p>
	 * Field taum: 用户
	 * </p>
	 */
	@Autowired
	private TtApplicationUserMapper taum;

	@Autowired
	private TtUserFunctionMapper tufm;
	
	@Autowired
	private MesLookupsTypeMapper mesLookupsTypeMapper;
	@Autowired
	private MesLookupsMapper mesLookupsMapper;
	
	@Autowired
	private MesAfterSaleMapper mesAfterSaleMapper;

	/**
	 * <p>
	 * Description: 售后退货_查询产品信息
	 * </p>
	 * 
	 * @return 结果集
	 */
	public String[] afterSaleReturnGetProductInfo(String[] barcode) throws Exception {
		String[] results=new String[3];

		try {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select mb.wip_barcode_id \"barcodeId\",");
		sql.append("we.wip_entity_name \"workOrder\",");
		sql.append("mb.barcode_text \"productBarcode\",");
		sql.append("ms.segment1 \"materialId\",");
		sql.append("ms.description \"description\",");
		sql.append("apps.mes_wip_barcodes_pub_pkg.get_barcode_prod_date(mb.wip_barcode_id) \"productionTimes\",");
		sql.append("apps.mes_wip_barcodes_pub_pkg.get_barcode_ship_date(mb.wip_barcode_id) \"orderDeliveryTimes\"");
		sql.append(" from mes.mes_wip_barcodes mb,");
		sql.append("apps.mtl_system_items_b   ms,");
		sql.append("wip.wip_entities we ");
		sql.append(" where ms.inventory_item_id = mb.primary_item_id");
		sql.append(" and ms.organization_id = mb.organization_id");
		sql.append(" and we.wip_entity_id=mb.wip_entity_id ");
//		sql.append(" and mb.barcode_text = 'c20150003246' ");
		sql.append(" and mb.barcode_text = ");
	   
		sql.append("'");
		sql.append(barcode[0]);
		sql.append("'");
		//System.out.println("SQL语句==>"+sql.toString());
		// TODO 检查是否已经发过货

		List<Map<String, Object>> resultMaps=this.getImqm().queryMap(sql.toString());
		
		if (resultMaps!=null&&resultMaps.size()>0) {
			results[0]="200";
			results[1]="查询成功！";
			results[2]=JsonObjectConverTools.objectToJson(resultMaps.get(0));
		}else {
			results[0]="400";
			results[1]="查询失败！无结果";
		}
		
		} catch (Exception e) {
			e.printStackTrace();
			results[0]="500";
			results[1]="服务器异常";
			this.log.error(e.getMessage()); 
		}
		return results;
	}
	/**
     * <p>
     * Field log: 日志
     * </p>
     */
    public Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * <p>
	 * Description: 售后退货_退货_提交
	 * </p>
	 * 
	 */
	public String[] afterSaleReturnCommit(String[] productInfo) {
	
		String[] results=new String[3];
		try {
		List<MesAfterSale> productInfos=JsonObjectConverTools.jsonToList(productInfo[1], MesAfterSale.class);
		//根据用户登录名查用户名
		String loginName=productInfo[0];
		String queryUserNameSql="select USER_NAME from tt_application_user where LOGIN_NAME='"+loginName+"'";
		String userName=getImqm().queryOneRowOneValue(queryUserNameSql);
		
		// 检查是否重复录入
		StringBuilder queryParams=new StringBuilder();
		Iterator<MesAfterSale> productInfosIterator1=productInfos.iterator();
		while (productInfosIterator1.hasNext()) {
			MesAfterSale mesAfterSale = (MesAfterSale) productInfosIterator1.next();
			queryParams.append("'"+mesAfterSale.getProductBarcode()+"',");
		}
		queryParams.deleteCharAt(queryParams.lastIndexOf(","));
		
		StringBuilder queryProductInfos=new StringBuilder();
		queryProductInfos.append("select STATUS from mes_after_sale where PRODUCT_BARCODE in ("+queryParams.toString()+")");
		//System.out.println("SQL语句==>"+queryProductInfos.toString());
		 List<Map<String, Object>>  resultmaps=this.getImqm().queryMap(queryProductInfos.toString());
		 if (resultmaps!=null&&resultmaps.size()>0) {
			 for(int i=0;i<resultmaps.size();i++){
				 if(resultmaps.get(i).get("STATUS").equals("return")){
					 results[0]="400";
					 results[1]="产品重复录入！";
					return results;
				 }
			 }
			 
		}
		
		Iterator<MesAfterSale> productInfosIterator2=productInfos.iterator();
		while (productInfosIterator2.hasNext()) {
			MesAfterSale mesAfterSale = (MesAfterSale) productInfosIterator2.next();
			//加操作者，操作时间，状态
			mesAfterSale.setReturnOperator(userName);
			mesAfterSale.setReturnOperationTime(new Date());
			mesAfterSale.setStatus("return");
			mesAfterSaleMapper.insertSelective(mesAfterSale);
		}
		results[0]="200";
		results[1]="操作成功！";
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus()
			.setRollbackOnly();
			results[0]="500";
			results[1]="服务器异常";
			e.printStackTrace();
			this.log.error(e.getMessage()); 
		}
		return results;
	}


	public String[] afterSaleDeliverGetProductInfo(String[] barcode) {
		String[] results=new String[3];

		try {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select mas.ID          \"id\",");
		sql.append(" mas.PRODUCT_BARCODE          \"productBarcode\",");
		sql.append(" mas.MATERIAL_ID              \"materialId\",");
		sql.append(" mas.DESCRIPTION              \"description\",");
		sql.append(" mas.PRODUCTION_TIMES         \"productionTimes\",");
		sql.append(" mas.ORDER_DELIVERY_TIMES     \"orderDeliveryTimes\",");
		sql.append(" mas.STATUS                   \"status\",");
		sql.append(" mas.RETURN_OPERATOR          \"returnOperator\",");
		sql.append(" mas.RETURN_OPERATION_TIME    \"returnOperationTime\",");
		sql.append(" mas.DELIVERY_OPERATOR        \"deliveryOperator\",");
		sql.append(" mas.DELIVERY_OPERATION_TIME  \"deliveryOperationTime\",");
		sql.append(" mas.EXPRESS_NO               \"expressNo\",");
		sql.append(" mas.PRODUCT_CATEGORY         \"productCategory\",");
		sql.append(" mas.BARCODEID                \"barcodeid\",");
		sql.append(" mas.WORKORDER                \"workorder\" ");
		
		sql.append(" from mes_after_sale mas ");
		
		sql.append(" where mas.PRODUCT_BARCODE = ");
	   
		sql.append("'");
		sql.append(barcode[0]);
		sql.append("'");
		sql.append(" and mas.STATUS='return'");
		//System.out.println("afterSaleDeliverGetProductInfo()==>SQL语句==>"+sql.toString());
		// TODO 检查是否已经发过货
		//要查出"没有发货"状态的那一条数据
		List<Map<String, Object>> resultMaps=this.getImqm().queryMap(sql.toString());
		
		if (resultMaps!=null&&resultMaps.size()>0) {
			results[0]="200";
			results[1]="查询成功！";
			results[2]=JsonObjectConverTools.objectToJson(resultMaps.get(0));
		}else {
			results[0]="400";
			results[1]="查询失败！无结果,请先作售后退货！";
		}
		
		} catch (Exception e) {
			e.printStackTrace();
			results[0]="500";
			results[1]="服务器异常";
			this.log.error(e.getMessage()); 
		}
		return results;
	} 
	public String[] afterSaleDeliverCommit(String[] productInfo) {
		String[] results=new String[3];
		try {
		List<MesAfterSale> productInfos=JsonObjectConverTools.jsonToList(productInfo[1], MesAfterSale.class);
		//根据用户登录名查用户名
		String loginName=productInfo[0];
		String queryUserNameSql="select USER_NAME from tt_application_user where LOGIN_NAME='"+loginName+"'";
		String userName=getImqm().queryOneRowOneValue(queryUserNameSql);
		
		// 检查是否重复录入
		StringBuilder queryParams=new StringBuilder();
		Iterator<MesAfterSale> productInfosIterator1=productInfos.iterator();
		while (productInfosIterator1.hasNext()) {
			MesAfterSale mesAfterSale = (MesAfterSale) productInfosIterator1.next();
			queryParams.append("'"+mesAfterSale.getProductBarcode()+"',");
		}
		queryParams.deleteCharAt(queryParams.lastIndexOf(","));
		
		StringBuilder queryProductInfos=new StringBuilder();
		queryProductInfos.append("select STATUS,PRODUCT_BARCODE from mes_after_sale where PRODUCT_BARCODE in ("+queryParams.toString()+")");
		//System.out.println("afterSaleDeliverCommit()==>SQL语句==>"+queryProductInfos.toString());
		 List<Map<String, Object>>  resultmaps=this.getImqm().queryMap(queryProductInfos.toString());
		 //如果按所有条码查询出来数据条数小于的客户端传过来的数据条数，说明有数据还没有执行退货操作，所以不能执行发货操作
		 if (resultmaps!=null&&resultmaps.size()>=productInfos.size()) {
			 //每条要插入的条码数据对应一个是否重复插入标记
			 List<Boolean> isCommitflags=new ArrayList<Boolean>();
			 for(int i=0;i<resultmaps.size();i++){
				 //初始化此条标记为可插入的，不重复的
				 boolean isCommitflag=true;
				 //如果某个条码有已经发货的数据，先把是否可插入标记设为false,再次循环，查看是否有同个条码的，未发货的状态，有，则可否提交标记为true，无则提交标记不更改
				 if(resultmaps.get(i).get("STATUS").equals("delivery")){//delivery//return
					 isCommitflag=false;
					 String productBarcode=(String) resultmaps.get(i).get("PRODUCT_BARCODE");
					 for(int j=0;j<resultmaps.size();j++){
						 if(((String) resultmaps.get(j).get("PRODUCT_BARCODE")).equals(productBarcode)){
							 if (resultmaps.get(j).get("STATUS").equals("return")) {
								 isCommitflag=true;
							};
						 }
					 }
				 }
				 isCommitflags.add(isCommitflag);
			 }
			 //全部条码的可提交标记都为true时才可插入这些数据
			 if(isCommitflags.contains(false)){
			 results[0]="400";
			 results[1]="产品重复录入！";
			return results;
			 }
			 
		}else{
			results[0]="400";
			 results[1]="有未做售后退货的数据，请先作售后退货！";
			return results;
		}
		
		Iterator<MesAfterSale> productInfosIterator2=productInfos.iterator();
		while (productInfosIterator2.hasNext()) {
			MesAfterSale mesAfterSale = (MesAfterSale) productInfosIterator2.next();
			//加操作者，操作时间，更新为发货状态
			mesAfterSale.setDeliveryOperator(userName);
			mesAfterSale.setDeliveryOperationTime(new Date());
			mesAfterSale.setStatus("delivery");
			mesAfterSaleMapper.updateByPrimaryKeySelective(mesAfterSale);
		}
		results[0]="200";
		results[1]="操作成功！";
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus()
			.setRollbackOnly();
			results[0]="500";
			results[1]="服务器异常";
			this.log.error(e.getMessage()); 
		}
		return results;
	} 
	
	/**
     * <p>
     * Description: 管理后台获取售后退货/发货列表
     * </p>
     * 
     * @return 
     */
    
	public Map<String, Object> getAfterSaleList(int limit,int offset,String expressNo,String status,String timeType,String productBarcode,String time_from,String time_to) throws Exception {
    		
		int page;
		if (offset==0) {
			page=0;
		}else{
			
			page=offset/limit+1;
		}
		int rows=limit;
    	StringBuffer sql = new StringBuffer();
		
		sql.append("select mas.ID          \"id\",");
		sql.append(" mas.PRODUCT_BARCODE          \"productBarcode\",");
		sql.append(" mas.MATERIAL_ID              \"materialId\",");
		sql.append(" mas.DESCRIPTION              \"description\",");
		sql.append(" to_char(mas.PRODUCTION_TIMES,'YYYY-MM-DD')         \"productionTimes\",");
		sql.append(" to_char(mas.ORDER_DELIVERY_TIMES,'YYYY-MM-DD')     \"orderDeliveryTimes\",");
		sql.append(" mas.STATUS                   \"status\",");
		sql.append(" mas.RETURN_OPERATOR          \"returnOperator\",");
		sql.append(" to_char(mas.RETURN_OPERATION_TIME,'YYYY-MM-DD')    \"returnOperationTime\",");
		sql.append(" mas.DELIVERY_OPERATOR        \"deliveryOperator\",");
		sql.append(" to_char(mas.DELIVERY_OPERATION_TIME,'YYYY-MM-DD')  \"deliveryOperationTime\",");
		sql.append(" mas.EXPRESS_NO               \"expressNo\",");
		sql.append(" mas.PRODUCT_CATEGORY         \"productCategory\",");
		sql.append(" mas.BARCODEID                \"barcodeid\",");
		sql.append(" mas.WORKORDER                \"workorder\" ");
		
		sql.append(" from mes_after_sale mas ");
		
		sql.append(" WHERE 1=1 ");
    	if (!StringUtils.isEmpty(expressNo)) {
    		
    		sql.append("    AND EXPRESS_NO = '" + expressNo.trim() + "'     ");
			
    	}
    	if (!StringUtils.isEmpty(status)) {
    		if (status.equals("return")||status.equals("delivery")) {
    			sql.append("    AND STATUS = '" + status + "'    ");
    		}
    	}
    	if (!StringUtils.isEmpty(productBarcode)) {
//    		sql.append("    AND PRODUCT_BARCODE LIKE '%" + productBarcode.trim() + "%'    ");
    		sql.append("    AND PRODUCT_BARCODE = '" + productBarcode.trim() + "'    ");
    	}
    	if (!StringUtils.isEmpty(timeType)&&!timeType.equals("none")) {
    		//按两种时间类型来加时间范围条件：returnTime,deliveryTime
    		String temp_time_from="";
			String temp_time_to="";
			if (StringUtils.isEmpty(time_from)) {
				temp_time_from="1970-1-1 0:0";
			}else {
				//不能用时间戳
				//temp_time_from=dateToStamp(time_from);
				temp_time_from=time_from;
			}
			if (StringUtils.isEmpty(time_to)) {
//				temp_time_to=String.valueOf(System.currentTimeMillis());
				temp_time_to=stampToDate(String.valueOf(System.currentTimeMillis()));
			}else {
				temp_time_to=time_to;
			}
			
    		if (timeType.equals("returnTime")) {
    			sql.append("    AND RETURN_OPERATION_TIME between to_date('"+temp_time_from+"','yyyy-MM-dd HH24:mi')  AND to_date('"+temp_time_to+"','yyyy-MM-dd HH24:mi')");
			}else if (timeType.equals("deliveryTime")) {
				sql.append("    AND DELIVERY_OPERATION_TIME between to_date('"+temp_time_from+"','yyyy-MM-dd HH24:mi')  AND to_date('"+temp_time_to+"','yyyy-MM-dd HH24:mi')");
			}
    	}
    	//按退货时间排序，反序
    	sql.append(" ORDER BY RETURN_OPERATION_TIME DESC ");

		System.out.println("getAfterSaleList()==>SQL语句==>"+sql.toString());
    	
		PageResult pr = this.getPrs().pageQuery(sql.toString(), rows, page);
        Map<String, Object> rv = new HashMap<String, Object>();

        rv.put("total", pr.getTotalRecords());
        rv.put("rows", pr.getRecords());
        return rv;
    }
	/* 
     * 将时间转换为时间戳
     */    
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
