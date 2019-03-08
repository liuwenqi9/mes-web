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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.mapper.expand.pda.GetProductInfoMapper;
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
 * Description: 根据产品条码查询产品信息
 * </p>
 * <p>
 * Author: 兰继明
 * </p>
 * <p>
 * Date: 2016年12月28日
 * </p>
 */
@Service
public class GetProductInfoService extends BaseService {

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
	@Autowired
	private GetProductInfoMapper getProductInfoMapper;

	/**
	 * <p>
	 * Description: 查询产品信息
	 * </p>
	 * 
	 * @return 成功与否，产品编码，料号描述，型号，工单，生产线，包装条码
	 */
	public String[] getProductInfo(String[] barCodeAndorgId) throws Exception {
		String[] results=new String[8];
		
		try {
			
			StringBuffer sql = new StringBuffer();
			
			sql.append("select ");
			//工单
			sql.append("we.wip_entity_name \"workOrder\",");
			//产品条码
			sql.append("mb.barcode_text \"productBarcode\",");
			//生产线
			sql.append("mb.LINE_CODE \"lineCode\",");
			//物料编码
			sql.append("ms.segment1 \"materialCode\",");
			//物料描述
			sql.append("ms.description \"description\",");
			//型号
			sql.append("mv.SEGMENT2 \"model\",");
			//状态
			sql.append("mb.status_code \"statusCode\",");
			
			//最上层包装条码
			sql.append("apps.mes_barcodes_pd_pkg.get_packing_barcode_text(mb.wip_barcode_id) \"packingHeaderBarcodeText\"");
			
			sql.append(" from mes.mes_wip_barcodes mb,");
			sql.append("apps.mtl_system_items_b   ms,");
			sql.append("wip.wip_entities   we,");
			sql.append("apps.mtl_item_categories_v   mv");
			
			sql.append(" where ms.inventory_item_id = mb.primary_item_id");
			sql.append(" and ms.organization_id = mb.organization_id");
			sql.append(" and we.wip_entity_id(+)=mb.wip_entity_id");
//		sql.append(" and mb.barcode_text = 'c20150003246' ");
			sql.append(" and mb.barcode_text = ");
			
			sql.append("'");
			sql.append(barCodeAndorgId[0]);
			sql.append("'");
			//校验证库存组织
			sql.append(" and mb.organization_Id = ");
			
			sql.append(barCodeAndorgId[1]);
			
			sql.append(" and mv.category_set_name(+) = '销售类别集' ");
			sql.append(" and mv.organization_id(+) = ");
			sql.append("'");
			sql.append(barCodeAndorgId[1]);
			sql.append("'");
			sql.append(" and mv.inventory_item_id(+) = ms.INVENTORY_ITEM_ID");
			System.out.println("getProductInfo==>SQL语句==>"+sql.toString());
			
			List<Map<String, Object>> resultMaps=this.getImqm().queryMap(sql.toString());
			
			if (resultMaps!=null&&resultMaps.size()>0) {
				results[0]="S";
				Map<String, Object> resultMap = resultMaps.get(0);
				results[1]=(String) resultMap.get("materialCode");
				results[2]=(String)resultMap.get("description");
				//报过异常BigDecimal cannot be cast to java.lang.String
				results[3]=resultMap.get("model")!=null?resultMap.get("model").toString():"";
				results[4]=resultMap.get("workOrder")!=null?resultMap.get("workOrder").toString():"";
				results[5]=resultMap.get("lineCode")!=null?resultMap.get("lineCode").toString():"";
				results[6]=resultMap.get("packingHeaderBarcodeText")!=null?resultMap.get("packingHeaderBarcodeText").toString():"";
				results[7]=resultMap.get("statusCode")!=null?resultMap.get("statusCode").toString():"";
			}else {
				results[0]="W";
				results[1]="查询失败！无结果";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			results[0]="E";
			results[1]="服务器异常";
		}
		return results;
	}
	/**
	 * <p>
	 * Description: 查询包装箱信息
	 * </p>
	 * 
	 * @return 成功与否，包装箱编码，料号描述，型号，工单，数量，货位
	 */
	public String[] getPackInfoByBarCode(String[] barCodeAndorgId) throws Exception {
		String[] results=new String[7];
		
		try {
			//假数据
//			results[0]="S";
//			results[1]="123456789";
//			results[2]="iphone12";
//			results[3]="9999";
//			results[4]="SH123456";
//			results[5]="8888";
//			results[6]="L999";
			//要查询的列
			//以下三列请参考PDA查询产品信息
			//产品编码：
			//料号描述：
			//产品型号：
			//以下一列在包装箱表查
			//工       单：
			//以下二列在包装表查
			//数       量：
			//货       位：
			String barCode=barCodeAndorgId[0];
			String organizationId=barCodeAndorgId[1];
			if (StringUtils.isEmpty(barCode)) {
				results[0] = MesConstants.ERROR;
				results[1] = "包装箱条码为空，请联系管理员";
				return results;
			}
			if (StringUtils.isEmpty(organizationId)) {
				results[0] = MesConstants.ERROR;
				results[1] = "登录组织为空，请联系管理员";
				return results;
			}
			
			Map<String, String> resultMap=getProductInfoMapper.getPackInfoByBarCode(barCode, organizationId);
			if (resultMap!=null) {
				resultToArray(results,resultMap);
				//如果没有生成包装或者已经发运，则提示“无查询结果”
			}else{
				
				results[0] = "E";
				results[1] = "无查询结果";
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			results[0]="E";
			results[1]="服务器异常";
		}
		return results;
	}
	public void resultToArray(String[] results,Map<String, String> resultMap ){
		results[0]="S";
		results[1]=resultMap.get("materialCode")!=null?resultMap.get("materialCode").toString():"";
		results[2]=resultMap.get("description")!=null?resultMap.get("description").toString():"";
		//报过异常BigDecimal cannot be cast to java.lang.String
		results[3]=resultMap.get("model")!=null?resultMap.get("model").toString():"";
		results[4]=resultMap.get("workOrder")!=null?resultMap.get("workOrder").toString():"";
		
		results[5]=resultMap.get("quantity")!=null?String.valueOf(resultMap.get("quantity")):"";
		
		results[6]=resultMap.get("locattionCode")!=null?resultMap.get("locattionCode").toString():"";
	}
		
	
}
