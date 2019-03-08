package com.egdfrm.unit.service.commonSetting;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.core.common.Constants;
import com.egdfrm.core.common.JsonResult;
import com.egdfrm.core.exception.CommonExceptionType;
import com.egdfrm.core.exception.MesThrowException;
import com.egdfrm.core.mapper.standard.TtApplicationUserMapper;
import com.egdfrm.core.mapper.standard.TtUserFunctionMapper;
import com.egdfrm.core.model.standard.TtApplicationUser;
import com.egdfrm.core.security.realm.UserAuthenRealm.ShiroUser;
import com.egdfrm.core.service.BaseService;
import com.egdfrm.unit.mapper.expand.commonSetting.CargospaceMapper;
import com.egdfrm.unit.mapper.standard.MesItemLocattionsMapper;
import com.egdfrm.unit.mapper.standard.MtlSubinventoriesAllVMapper;
import com.egdfrm.unit.model.standard.MesItemLocattions;
import com.egdfrm.unit.model.standard.MesItemLocattionsCriteria;
import com.egdfrm.unit.model.standard.MtlSubinventoriesAllV;
import com.egdfrm.unit.model.standard.MtlSubinventoriesAllVCriteria;

/**
 * <p>
 * Description: 货位SERVICE
 * </p>
 * <p>
 * Author: sjf
 * </p>
 * <p>
 * Date: 2016年12月30日
 * </p>
 */
@Service
public class CargospaceService extends BaseService {

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
	private CargospaceMapper cm;

	@Autowired
	private MesItemLocattionsMapper milm;

	@Autowired
	private MtlSubinventoriesAllVMapper msavm;

	/**
	 * @author sjf
	 * @date 2017年1月3日
	 * @param sublibraryName
	 * @return 货位查询
	 */
	public List<Map<String, Object>> searchCargospace(String sublibraryName,
			String cargospaceName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String sbName = (StringUtils.isEmpty(sublibraryName))
				? null
				: sublibraryName.trim().toUpperCase();
		String csName = (StringUtils.isEmpty(cargospaceName)) ? null : "%"
				+ cargospaceName.trim() + "%";
		paramMap.put("sublibraryName", sbName);
		paramMap.put("cargospaceName", csName);
//		return cm.searchCargospaceList(sbName, csName);
		return cm.searchCargospaceList(paramMap);
	}
	/**
	 * @author sjf
	 * @date 2017年1月4日
	 * @param su
	 *            .getLoginName()
	 * @param mil
	 * @return 货位新增
	 */
	public JsonResult<String> addCargospace(ShiroUser su, MesItemLocattions mil) {
		/**
		 * 验证子库、货位状态
		 */
		checkSubinventorie(mil, su);

		/*
		 * 验证货位名重复
		 */
		MesItemLocattionsCriteria milc = new MesItemLocattionsCriteria();
		milc.createCriteria().andOrganizationIdEqualTo(su.getOrgId())
				.andLocattionCodeEqualTo(mil.getLocattionCode());
		List<MesItemLocattions> milList = new ArrayList<MesItemLocattions>();
		milList = milm.selectByExample(milc);
		if (!milList.isEmpty()) {
			// 该货位名已存在！
			throw new MesThrowException(CommonExceptionType.LOCATTIONS_IS_EXISTS);
		}
		/**
		 * 保存货位信息
		 */
		TtApplicationUser user = taum.selectByPrimaryKey(su.getLoginName());
		mil.setLastUpdatedBy(user.getUserId());
		mil.setCreatedBy(user.getUserId());
		mil.setCreationDate(new Date());
		mil.setLastUpdateDate(new Date());
		mil.setOrganizationId(su.getOrgId());
		this.milm.insert(mil);
		return new JsonResult<String>(Constants.SUCCESS, "保存成功");
	}
	/**
	 * @author sjf
	 * @date 2017年1月5日
	 * @param mil
	 * 验证子库、货位状态
	 */
	private void checkSubinventorie(MesItemLocattions mil, ShiroUser su) {
		// 如果子库名为空则报错
		if (mil.getSubinventoryCode().isEmpty()) {
			// 子库名不能为空！
			throw new MesThrowException(CommonExceptionType.SUBINVENTORIE_CODE_IS_NULL);
		}
		// 如果货位名为空则报错
		if (mil.getLocattionCode().isEmpty()) {
			// 货位名不能为空！
			throw new MesThrowException(CommonExceptionType.LOCATTION_CODE_IS_NULL);
		}
		//子库名转大写
		mil.setSubinventoryCode(mil.getSubinventoryCode().toUpperCase());
		/*
		 * 取得子库信息
		 */
		List<MtlSubinventoriesAllV> msavList = new ArrayList<MtlSubinventoriesAllV>();
		MtlSubinventoriesAllV msav = new MtlSubinventoriesAllV();
		MtlSubinventoriesAllVCriteria msavc = new MtlSubinventoriesAllVCriteria();
		msavc.createCriteria()
				.andSecondaryInventoryNameEqualTo(mil.getSubinventoryCode())
				.andOrganizationIdEqualTo(su.getOrgId());
		msavList = msavm.selectByExample(msavc);
		if (msavList.isEmpty()) {
			// 子库不存在！
			throw new MesThrowException(CommonExceptionType.SUBINVENTORIE_NOT_EXISTS);
		}
		msav = msavList.get(0);
		// 如果子库状态为无效，则报错
		if (msav.getDisableDate() != null
				&& msav.getDisableDate().before(new Date())) {
			// 该子库已失效，请重新选择！
			throw new MesThrowException(CommonExceptionType.SUBINVENTORIE_DISABLED);
		}
		// 如果子库ERP控制已开，则报错
		if (!new BigDecimal(1).equals(msav.getLocatorType())) {
			// 该子库为ERP控制，不可选择！
			throw new MesThrowException(CommonExceptionType.ERP_CONTROLLER_ERROR);
		}
		
	}
	/**
	 * @author sjf
	 * @date 2017年1月5日
	 * @param su
	 * @param mil
	 * @return 货位修改保存
	 */
	public JsonResult<String> editCargospace(ShiroUser su, MesItemLocattions mil) {
		/**
		 * 验证子库、货位状态
		 */
		checkSubinventorie(mil,su);
		/**
		 * 保存货位信息
		 */
		TtApplicationUser user = taum.selectByPrimaryKey(su.getLoginName());
		MesItemLocattionsCriteria milc = new MesItemLocattionsCriteria();
		milc.createCriteria().andOrganizationIdEqualTo(su.getOrgId())
				.andLocattionCodeEqualTo(mil.getLocattionCode());
		/*
		 * 创建货位对象
		 */
		List<MesItemLocattions> newMilList = new ArrayList<MesItemLocattions>();
		MesItemLocattions newMil = new MesItemLocattions();
		newMilList = milm.selectByExample(milc);
		if (!newMilList.isEmpty()) {
			newMil = newMilList.get(0);
		}
		newMil.setDescription(mil.getDescription());
		newMil.setDisableDate(mil.getDisableDate());
		newMil.setLastUpdatedBy(user.getUserId());
		newMil.setLastUpdateDate(new Date());
		this.milm.updateByExample(newMil, milc);
		return new JsonResult<String>(Constants.SUCCESS, "保存成功");
	}

}
