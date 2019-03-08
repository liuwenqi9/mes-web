package com.egdfrm.unit.service.commonSetting;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.core.mapper.standard.TtApplicationUserMapper;
import com.egdfrm.core.mapper.standard.TtUserFunctionMapper;
import com.egdfrm.core.service.BaseService;
import com.egdfrm.unit.mapper.expand.commonSetting.SublibraryMapper;


/** 
 * <p>
 * Description: 子库一览controller
 * </p>
 * <p>
 * Author: sjf
 * </p>
 * <p>
 * Date: 2016年12月30日
 * </p>
 */
@Service
public class SublibraryService extends BaseService {

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
	private SublibraryMapper sm;

	/**
	 * @author sjf
	 * @date 2017年1月3日 
	 * @param sublibraryName
	 * @return
     * 子库查询
	 */
	public List<Map<String, Object>> searchSublibrary(String sublibraryName) {
		String paramSubName;
		// 如果子库名不为空则大写后查询
		if (StringUtils.isEmpty(sublibraryName)||StringUtils.isEmpty(sublibraryName.trim())) {
			paramSubName=null;
		}else{
			paramSubName=sublibraryName.toUpperCase().trim();
		} 
		//ERP控制
//		String erpController=MesConstants.YES
		return sm.getSublibraryList(paramSubName);
	}

}
