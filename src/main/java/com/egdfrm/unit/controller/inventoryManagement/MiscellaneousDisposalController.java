package com.egdfrm.unit.controller.inventoryManagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egdfrm.core.controller.base.BaseController;
import com.egdfrm.core.security.annotation.CurrentLoginInfo;
import com.egdfrm.core.security.realm.UserAuthenRealm.ShiroUser;
import com.egdfrm.unit.common.MesConstants;
import com.egdfrm.unit.model.standard.MesTransactionsInterface;
import com.egdfrm.unit.service.inventoryManagement.MiscellaneousDisposalService;


/**
 * @author sjf
 * @date 2017年1月12日 
 * 杂项事务处理
 */
@Controller
@RequestMapping("miscellaneousDisposalController")
public class MiscellaneousDisposalController extends BaseController {
	
	/**
     * <p>
     * Field cs: 杂项事务处理service
     * </p>
     */
    @Autowired
    private MiscellaneousDisposalService mds;
	
	/**
     * <p>
     * Description: 初始化方法
     * </p>
     * 
     * @return 主页面
     */
    @RequiresPermissions("miscellaneousDisposalController:view")
    @RequestMapping("init")
    public String init() {
        return "unit/inventoryManagement/miscellaneousDisposal";
    }  

	/**
	 * @author sjf
	 * @date 2017年1月12日 
	 * @return
     * 获取【事务处理类型】列表
	 */
    @RequiresPermissions("miscellaneousDisposalController:view")
    @RequestMapping("getTransactionTypeList")
    @ResponseBody
    public List<Map<String, Object>> getTransactionTypeList() throws Exception  {
    	return mds.getTransactionTypeList();
    }
	/**
	 * @author sjf
	 * @date 2017年1月12日 
	 * @param orgId
	 * @return
	 * 搜索【来源】
	 */
    @RequiresPermissions("miscellaneousDisposalController:view")
    @RequestMapping("searchSourceByName")
    @ResponseBody
    public List<Map<String, Object>> searchSourceByName(@CurrentLoginInfo ShiroUser su,String sourceName) throws Exception  {
    	return mds.searchSourceByName(su,sourceName);
    }   
    /**
     * @author sjf
     * @date 2017年1月12日 
     * @param barCode
     * @return
     * @throws Exception
     * 根据产品条码获取物料信息
     */
    @RequiresPermissions("miscellaneousDisposalController:view")
    @RequestMapping("searchWipInfoByBarCode")
    @ResponseBody
    public Map<String, Object> searchWipInfoByBarCode(@CurrentLoginInfo ShiroUser su,String barCode) throws Exception  {
    	return mds.searchWipInfoByBarCode(su,barCode);
    } 
    /**
     * @author sjf
     * @date 2017年1月15日 
     * @param su
     * @param barCode
     * @return
     * @throws Exception
     * 杂项事务处理提交事件//由于写到MES成功则需要commit，所以对MES和EBS两系统的写入分两个方法写
     */
    @RequiresPermissions("miscellaneousDisposalController:view")
    @RequestMapping("miscellaneousDisposalSave")
    @ResponseBody
    public Map<String, Object> miscellaneousDisposalSave(@CurrentLoginInfo ShiroUser su, String params) throws Exception  {
    	Map<String, Object> result= new HashMap<String, Object>();
    	//插入接口表mesTransactionsInterface
    	// <!--帐户别名三步1检查写入事务接口表结果(验证表单)-->
    			// 保存后，用这个包检查，如果x_return_status<>'S' 就报出错误，界面保留不动
    	// <!--帐户别名三步2写入MES-->
    			// 如果x_return_status<>'S' 就报出错误，界面保留不动，成功就commit;
    	result=mds.miscellaneousDisposalSave(su,params);
    	if (!MesConstants.SUCCESS.equals(result.get("flag"))) {
			return result;
		}
    	// <!--帐户别名三步3写入EBS-->
    			// 写EBS，如果不成功就返回警告EBS未处理成功，请联系IT处理。commmit;
    	result=mds.miscellaneousDisposalSave2((String)result.get("processId"));
    	if (!MesConstants.SUCCESS.equals(result.get("flag"))) {
			return result;
		}
    	result.put("flag", "S");
		return result;
    } 
    
}
