package com.egdfrm.unit.service.barcodeManagement;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.core.exception.CommonException;
import com.egdfrm.core.exception.CommonExceptionType;
import com.egdfrm.core.exception.MesThrowException;
import com.egdfrm.core.security.realm.UserAuthenRealm.ShiroUser;
import com.egdfrm.core.service.BaseService;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.barcodeManagement.PackageBarcodeMapper;
import com.egdfrm.unit.model.barcodeManagement.PackagePrint;
import com.egdfrm.unit.model.standard.MesPackingHeaders;
import com.egdfrm.unit.service.pda.PDACommonService;

/**
 * 包装条码service类
 * @author hgb
 * @date 2016-12-22
 */
@Service
public class PackageBarcodeService extends BaseService implements IPackageBarcodeService{

    @Autowired
    private PackageBarcodeMapper packageBarcodeMapper;
    @Autowired
    private PDACommonService pdacs;

    
    /**
     * 包装条码分页查询
     * @param pagination
     * @param map
     * @return
     */
    public Pagination getProductBarcode(Pagination pagination, Map<String, Object> map) {
        Long total = packageBarcodeMapper.getConnt(map);
        List<Map<String,Object>> rows = packageBarcodeMapper.gePageData(pagination,map);
        pagination.setTotal(total);
        pagination.setRows(rows);
        return pagination;
    }

    /**
     * 获取包装类型
     * @return
     */
    @Override
    public List<Map<String, Object>> getPackageType() {
        return packageBarcodeMapper.getPackageType();
    }

    /**
     * 条码生成
     * @param map
     */
    @Override
    public void barcodePackage(Map<String, Object> map) {
        packageBarcodeMapper.barcodePackage(map);
    }

    /**
     * 获取包装条码打印数据
     * @param codes 学序列号
     * @return
     */
    @Override
    public List<PackagePrint> getPrintData(String[] codes) {
        return packageBarcodeMapper.getPrintData(codes);
    }

    /**
     * 修改打印状态
     * @param codes 序列号
     * @return
     */
    @Override
    public int updatePrintStatus(String[] codes) {
        return packageBarcodeMapper.updatePrintStatus(codes);
    }

    /**
     * 根据工单号获取贴箱标志
     * @param workOrder 工单号
     * @return
     */
    @Override
    public String getFlagMark(String workOrder) {
        return packageBarcodeMapper.getFlagMark(workOrder);
    }

	/* (non-Javadoc)
	 * @author sjf
	 * @date 2017年2月5日 
	 * @see com.egdfrm.unit.service.barcodeManagement.IPackageBarcodeService#searchPackageBarcode(java.lang.String)
     * 包装条码信息查询
	 *
	 */
	@Override
	public List<Map<String, Object>> searchPackageBarcode(ShiroUser su,String packageBarcode) {
		if(StringUtils.isEmpty(packageBarcode)){
			//包装箱号不能为空！
			throw new MesThrowException(CommonExceptionType.PACKAGEBARCODE_IS_NULL);
		}
		//查询包装箱
		Map<String, Object> retMap = pdacs.getPackingHeaders(packageBarcode,su.getOrgId().toString());
		String result=(String) retMap.get(MesConstants.RESULT);
		if(!MesConstants.SUCCESS.equals(result)){
			String[] retVal= (String[]) retMap.get(MesConstants.RETVAL);
			//包装箱错误提示
			throw new CommonException(MesConstants.UNKNOWN,retVal[1]);
		}
		MesPackingHeaders mph=(MesPackingHeaders) retMap.get("mph");
        return packageBarcodeMapper.searchPackageBarcode(mph.getPackingBarcodeId());
	}
}
