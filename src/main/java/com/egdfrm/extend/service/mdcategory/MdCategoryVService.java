package com.egdfrm.extend.service.mdcategory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.extend.common.DbReturnParameter;
import com.egdfrm.extend.mapper.standard.MdCategoryVMapper;
import com.egdfrm.extend.model.standard.MdCategoryV;
import com.egdfrm.core.mapper.expand.IMapQueryMapper;
import com.egdfrm.core.model.expand.PageResult;
import com.egdfrm.core.service.BaseService;

@Service
public class MdCategoryVService extends BaseService {
	@Autowired
	private IMapQueryMapper imqm;
	@Autowired
	private MdCategoryVMapper mdcategoryMap;
	
	public MdCategoryVService() {
	}
	
	public Map<String, Object> getList(int page, int rows) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM MD_CATEGORY_V");
		PageResult pr = this.getPrs().pageQuery(sql.toString(), rows, page);
		Map<String, Object> rv = new HashMap<String, Object>();
		rv.put("total", pr.getTotalRecords());
		rv.put("rows", pr.getRecords());
		return rv;
	}
	
	public DbReturnParameter insertrow(MdCategoryV record) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		DbReturnParameter dbreturn = new DbReturnParameter();
		String seq = imqm.queryOneRowOneValue("SELECT MD_CATEGORS.NEXTVAL FROM DUAL");
		record.setCategoryId(new BigDecimal(seq));
		paramMap.put("record", record);
		paramMap.put("dbreturn", dbreturn);
		mdcategoryMap.insertrow(record, dbreturn);
		return dbreturn;
	}
	
	public DbReturnParameter updaterow(MdCategoryV record) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		DbReturnParameter dbreturn = new DbReturnParameter();
		paramMap.put("record", record);
		paramMap.put("dbreturn", dbreturn);
		mdcategoryMap.updaterow(record, dbreturn);
		return dbreturn;
	}
	
	public DbReturnParameter deleterow(BigDecimal categoryId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		DbReturnParameter dbreturn = new DbReturnParameter();
		paramMap.put("categoryId", categoryId);
		paramMap.put("dbreturn", dbreturn);
		mdcategoryMap.deleterow(categoryId, dbreturn);
		return dbreturn;
	}
	
	public MdCategoryV selectrow(BigDecimal categoryId) {
		return mdcategoryMap.selectrow(categoryId);
	}
}
