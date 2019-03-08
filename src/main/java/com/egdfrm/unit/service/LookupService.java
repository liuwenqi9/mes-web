/**
 * LookupService.java
 * Created at 2016-12-01
 * Created by 兰继明
 * Copyright (C) unit
 */
package com.egdfrm.unit.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
import com.egdfrm.unit.mapper.standard.MesLookupsMapper;
import com.egdfrm.unit.mapper.standard.MesLookupsTypeMapper;
import com.egdfrm.unit.model.standard.MesLookups;
import com.egdfrm.unit.model.standard.MesLookupsCriteria;
import com.egdfrm.unit.model.standard.MesLookupsType;
import com.egdfrm.unit.model.standard.MesLookupsTypeCriteria;

/**
 * <p>
 * ClassName: LookupService
 * </p>
 * <p>
 * Description: 数据字典设置
 * </p>
 * <p>
 * Author: 兰继明
 * </p>
 * <p>
 * Date: 2016年12月01日
 * </p>
 */
@Service
public class LookupService extends BaseService {

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

	/**
	 * <p>
	 * Description: 加载数据字典类型清单
	 * </p>
	 * 
	 * @return 结果集
	 */
	public Map<String, Object> getLookupTypeList(int page,int rows,String lookupType) throws Exception {
		StringBuffer sql = new StringBuffer();
		
//		sql.append(" select * ");
		
		sql.append(" select SOURCE_TYPE,");

		sql.append("LOOKUP_TYPE,");

		sql.append("DESCRIPTION,");

		sql.append("CREATED_BY,");

		sql.append("to_char(CREATION_DATE,'YYYY-MM-DD') CREATION_DATE,");

		sql.append("LAST_UPDATED_BY,");

		sql.append("to_char(LAST_UPDATE_DATE,'YYYY-MM-DD') LAST_UPDATE_DATE");

		sql.append(" from mes_lookups_type ");
		
		sql.append("    WHERE 1=1    ");
        if (!StringUtils.isEmpty(lookupType)) {
            sql.append("    AND LOOKUP_TYPE LIKE '%" + lookupType + "%'    ");
        }
		PageResult pr = this.getPrs().pageQuery(sql.toString(), rows, page);
        Map<String, Object> rv = new HashMap<String, Object>();

        rv.put("total", pr.getTotalRecords());
        rv.put("rows", pr.getRecords());
        return rv;

	}

	/**
	 * <p>
	 * Description: 加载数据字典清单
	 * </p>
	 * 
	 * @return 结果集
	 */
	public List<Map<String, Object>> getLookupList(String lookupType) {
		
StringBuffer sql = new StringBuffer();
		

		
		sql.append(" select LOOKUP_TYPE,");

		sql.append("LOOKUP_CODE,");

		sql.append("DESCRIPTION,");

		sql.append("ENABLED_FLAG,");

		sql.append("to_char(START_DATE_ACTIVE,'YYYY-MM-DD') START_DATE_ACTIVE,");

		

		sql.append("to_char(END_DATE_ACTIVE,'YYYY-MM-DD') END_DATE_ACTIVE,");

		sql.append("CREATED_BY,");
		
		
		sql.append("to_char(CREATION_DATE,'YYYY-MM-DD') CREATION_DATE,");

		
		sql.append("LAST_UPDATED_BY,");
		
		
		sql.append("to_char(LAST_UPDATE_DATE,'YYYY-MM-DD') LAST_UPDATE_DATE");

		  
		
		sql.append(" from mes_lookups where LOOKUP_TYPE = '");
		sql.append(lookupType);
		sql.append("'");

		return this.getImqm().queryMap(sql.toString());
	}

	public JsonResult<String> addLookupType(MesLookupsType mesLookupsType) {

//        Long useId=request.getSession().get
        mesLookupsType.setCreatedBy(3l);
        mesLookupsType.setCreationDate(new Date());
        mesLookupsType.setLastUpdatedBy(3l);
        mesLookupsType.setLastUpdateDate(new Date());
        
        this.mesLookupsTypeMapper.insertSelective(mesLookupsType);
        
        
        return new JsonResult<String>(Constants.SUCCESS, "保存成功");
    }
	public boolean lookupTypeUniqueCheck(String lookupType) {
		
		
		MesLookupsTypeCriteria mesLookupsTypeCriteria=new MesLookupsTypeCriteria();
		mesLookupsTypeCriteria.createCriteria().andLookupTypeEqualTo(lookupType);
		return mesLookupsTypeMapper.countByExample(mesLookupsTypeCriteria)>0?false:true;
		
	}
	
	public JsonResult<String> editLookupType(MesLookupsType mesLookupsType) {
		MesLookupsTypeCriteria mesLookupsTypeCriteria=new MesLookupsTypeCriteria();
		mesLookupsTypeCriteria.createCriteria().andLookupTypeEqualTo(mesLookupsType.getLookupType());
		List<MesLookupsType> mesLookupsTypes=mesLookupsTypeMapper.selectByExample(mesLookupsTypeCriteria);
		MesLookupsType mesLookupsType2=mesLookupsTypes.get(0);
		mesLookupsType2.setSourceType(mesLookupsType.getSourceType());
		mesLookupsType2.setDescription(mesLookupsType.getDescription());
        mesLookupsType2.setLastUpdatedBy(3l);
        mesLookupsType2.setLastUpdateDate(new Date());
        
        this.mesLookupsTypeMapper.updateByExample(mesLookupsType2,mesLookupsTypeCriteria);
        
        
        return new JsonResult<String>(Constants.SUCCESS, "更新成功");
    }

	public JsonResult<String> addLookup(MesLookups lookup) {
		lookup.setCreatedBy(3l);
		lookup.setCreationDate(new Date());
		lookup.setLastUpdatedBy(3l);
		lookup.setLastUpdateDate(new Date());
        
        this.mesLookupsMapper.insertSelective(lookup);
        
        
        return new JsonResult<String>(Constants.SUCCESS, "保存成功");
	}

	public JsonResult<String> editLookup(MesLookups lookup) {
		MesLookupsCriteria mesLookupsCriteria=new MesLookupsCriteria();
		mesLookupsCriteria.createCriteria().andLookupCodeEqualTo(lookup.getLookupCode());
		List<MesLookups> mesLookups=mesLookupsMapper.selectByExample(mesLookupsCriteria);
		MesLookups mesLookups2=mesLookups.get(0);
		mesLookups2.setEnabledFlag(lookup.getEnabledFlag());
		mesLookups2.setDescription(lookup.getDescription());
		mesLookups2.setStartDateActive(lookup.getStartDateActive());
		mesLookups2.setEndDateActive(lookup.getEndDateActive());
        mesLookups2.setLastUpdatedBy(3l);
        mesLookups2.setLastUpdateDate(new Date());
        
        this.mesLookupsMapper.updateByExample(mesLookups2,mesLookupsCriteria);
        
        
        return new JsonResult<String>(Constants.SUCCESS, "更新成功");
	}

	public List<Map<String, Object>> getLookup(String lookupCode) {
StringBuffer sql = new StringBuffer();
		

		
		sql.append(" select LOOKUP_TYPE,");

		sql.append("LOOKUP_CODE,");

		sql.append("DESCRIPTION,");

		sql.append("ENABLED_FLAG,");

		sql.append("to_char(START_DATE_ACTIVE,'YYYY-MM-DD HH:MM') START_DATE_ACTIVE,");

		

		sql.append("to_char(END_DATE_ACTIVE,'YYYY-MM-DD HH:MM') END_DATE_ACTIVE,");

		sql.append("CREATED_BY,");
		
		
		sql.append("to_char(CREATION_DATE,'YYYY-MM-DD') CREATION_DATE,");

		
		sql.append("LAST_UPDATED_BY,");
		
		
		sql.append("to_char(LAST_UPDATE_DATE,'YYYY-MM-DD') LAST_UPDATE_DATE");

		  
		sql.append(" from mes_lookups where LOOKUP_CODE = '");
		sql.append(lookupCode);
		sql.append("'");

		return this.getImqm().queryMap(sql.toString());
	}
}
