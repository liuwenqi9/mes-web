package com.egdfrm.unit.service.pda;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.egdfrm.core.service.BaseService;
import com.egdfrm.extend.common.JsonObjectConverTools;
import com.egdfrm.unit.mapper.expand.pda.CheckStockStatusMapper;
import com.egdfrm.unit.mapper.expand.pda.InStockPreparingMapper;

/**
 * 入库备货---service
 * @author 兰继明
 * @date 2017-2-16
 */
@Service
public class InStockPreparingService extends BaseService{
	
	@Autowired
	private CheckStockStatusMapper checkStockStatusMapper;
	@Autowired
	private InStockPreparingMapper inStockPreparingMapper;
	//检查入库单是否存在
	public String[] checkIsHaveInStockBill(String[] stockNumber){
	    String[] result = new String[200];
		try {
			//验证入库单号是否存在
			List<String> lists = checkStockStatusMapper.verificationStockNum(stockNumber[0]);
			if(lists==null || lists.isEmpty()){
				result[0] = "E";
				result[1] = "不存在此入库单！";
				return result;
			}
			result[0]="S";
			result[1]="查询入库单成功，存在此入库单";
			return result;
		} catch (Exception e) { 
			e.printStackTrace();
			result[0]="E";
			result[1]="服务器异常!";
			return result;
		}
	}
	//检查某个箱子是否属于某个入库单
	public String[] checkPackIsBelongInStockBill(String[] stockNumber_and_pack_num){
		String[] result = new String[200];
		String stockNumber=stockNumber_and_pack_num[0];
		String pack_num=stockNumber_and_pack_num[1];
		String organizationId=stockNumber_and_pack_num[2];
		if (StringUtils.isEmpty(stockNumber)) {
			result[0] = "E";
			result[1] = "入库单不能为空！";
			return result;
		}
		if (StringUtils.isEmpty(pack_num)) {
			result[0] = "E";
			result[1] = "包装箱号不能为空！";
			return result;
		}
		if (StringUtils.isEmpty(organizationId)) {
			result[0] = "E";
			result[1] = "组织ID不能为空！";
			return result;
		}
		try {
			//验证入库单号是否存在
			String[] checkIsHaveInStockBill_result=checkIsHaveInStockBill(stockNumber_and_pack_num);
			if(checkIsHaveInStockBill_result[0]!="S"){
				result[0] = "E";
				result[1] = "不存在此入库单！";
				return result;
			}
			//验证包装箱是否存在，并且是否属于此入库单
			//是则返回产品编码和包装数量
			List<Map<String,String>> query_results=inStockPreparingMapper.checkPackIsBelongInStockBill(stockNumber, pack_num, organizationId);
			//无查询结果
			if (query_results==null||query_results.size()<=0) {
				result[0] = "E";
				result[1] = "此包装箱不属于此入库单！";
				return result;
			}
			resultToArray(result,query_results.get(0),pack_num);
			return result;
		} catch (Exception e) { 
			e.printStackTrace();
			result[0]="E";
			result[1]="服务器异常!";
			return result;
		}
	}
	public void resultToArray(String[] results,Map<String, String> resultMap ,String pack_num){
		results[0]="S";
		results[1]=pack_num;
		results[2]=resultMap.get("materialCode")!=null?resultMap.get("materialCode").toString():"";
		//报过异常BigDecimal cannot be cast to java.lang.String
		results[3]=resultMap.get("quantity")!=null?String.valueOf(resultMap.get("quantity")):"";
	}
	

}
