package com.egdfrm.unit.service.productionManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.core.service.BaseService;
import com.egdfrm.unit.mapper.productionManagement.ResetMapper;

/**
 * 单据(报检单和入库单)重置-服务类
 * @author hgb
 * @date 2017-2-13
 */
@Service
public class ResetService extends BaseService{
	
	@Autowired
	private ResetMapper resetMapper;
	
	/**
	 * 验证报检单号
	 * @param inspectNum
	 * @return   true：存在，false：不存在 
	 * @date 2017-2-13
	 */
	public boolean verificationInspect(String inspectNum){
		List<String> listString = resetMapper.verificationInspect(inspectNum);
		if(listString!=null && !listString.isEmpty()){
			return true;
		}
		return false;
	}
	/**
	 *  重置报检单
	 * @date 2017-2-13
	 */
	public void resetInspect (String inspectNum){
		resetMapper.resetInspect(inspectNum);
	}
	
	
	
	/**
	 * 验证入库单
	 * @param stockNum
	 * @return   true：存在，false：不存在 
	 * @date 2017-2-13
	 */
	public boolean verificationStockNum(String stockNum){
		List<String> listString = resetMapper.verificationStock(stockNum);
		if(listString!=null && !listString.isEmpty()){
			return true;
		}
		return false;
	}
	/**
	 *  重置入库单
	 * @date 2017-2-13
	 */
	public void resetStock (String stockNum){
		resetMapper.resetStock(stockNum);
	}
}
