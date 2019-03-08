package com.egdfrm.unit.service.pda;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.core.service.BaseService;
import com.egdfrm.extend.common.JsonObjectConverTools;
import com.egdfrm.unit.mapper.expand.pda.CheckStockStatusMapper;

/**
 * 入库单检查 ---service
 * @author hgb
 * @date 2017-2-16
 */
@Service
public class CheckStockStatusService extends BaseService{
	
	@Autowired
	private CheckStockStatusMapper checkStockStatusMapper;
	
	public String[] getPackBarcodeByStockNum(String[] stockNumber){
	    String[] result = new String[200];
		try {
			//验证入库单号是否存在
			List<String> lists = checkStockStatusMapper.verificationStockNum(stockNumber[0]);
			if(lists==null || lists.isEmpty()){
				result[0] = "E";
				result[1] = "不存在入库单！";
				return result;
			} 
			List<Map<String, Object>> listMap = checkStockStatusMapper.getPackBarcodeByStockNum(stockNumber[0]);
			if(listMap !=null &&!listMap.isEmpty()){
				result[0] = "S";
				//未入库数量
				result[1] = String.valueOf(listMap.size());
				result[2] = JsonObjectConverTools.listToJson(listMap);
				return result;
			}else{
				result[0] = "E";
				result[1] = "该单已完全入库";
				return result;
			}
		} catch (Exception e) { 
			e.printStackTrace();
			result[0]="exception";
			result[1]="服务器异常!";
			return result;
		}
		
		
	}
	

}
