package com.egdfrm.unit.service.barcodeManagement;

import com.egdfrm.core.security.realm.UserAuthenRealm.ShiroUser;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.model.barcodeManagement.PackagePrint;

import java.util.List;
import java.util.Map;

/**
 * 包装条码接口
 * Created by tyq on 17/1/9.
 */
public interface IPackageBarcodeService {

    /**
     * 包装条码分页查询
     * @param pagination
     * @param map
     * @return
     */
    Pagination getProductBarcode(Pagination pagination, Map<String, Object> map);

    /**
     * 获取包装类型
     * @return
     */
    List<Map<String,Object>> getPackageType();

    /**
     * 条码生成
     * @param map
     */
    void barcodePackage(Map<String, Object> map);

    /**
     * 获取包装条码打印数据
     * @param codes 学序列号
     * @return
     */
    List<PackagePrint> getPrintData(String[] codes);

    /**
     * 修改打印状态
     * @param codes 序列号
     * @return
     */
    int updatePrintStatus(String[] codes);

    /**
     * 根据工单号获取贴箱标志
     * @param workOrder 工单号
     * @return
     */
    String getFlagMark(String workOrder);

	/**
	 * @author sjf
	 * @param su 
	 * @date 2017年2月5日 
	 * @param packageBarcode
	 * @return
     * 包装条码信息查询
	 */
	List<Map<String, Object>> searchPackageBarcode(ShiroUser su, String packageBarcode);
}
