package com.egdfrm.unit.service.productionManagement;

  
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.core.service.BaseService;
import com.egdfrm.unit.mapper.productionManagement.DocumentSupplementMapper;

@Service
public class DocumentSupplementService extends BaseService{
	 
	
	@Autowired
	private DocumentSupplementMapper documentSupplementMapper;
	
	 
	
	/**
	 * @author sjf
	 * @date 2017年2月8日 
	 * @param inspectionNumber
	 * @return
	 * 根据报检单号获取报检单
	 */
	public List<Map<String,Object>> getInspectionByInspectionNumber(String inspectionNumber){ 
		return  documentSupplementMapper.getInspectionByInspectionNumber(inspectionNumber);
	}
	
	 
	
}
