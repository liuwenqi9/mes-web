package com.egdfrm.unit.service.productionManagement;

import com.egdfrm.unit.mapper.productionManagement.SpecialBusinessMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 特殊业务处理接口实现
 * Created by tyq on 17/2/10.
 */
@Service
public class SpecialBusinessService implements ISpecialBusinessService {

    @Autowired
    private SpecialBusinessMapper specialBusinessMapper;

    /**
     * 产品条码重置
     * @param map
     */
    @Override
    public void productNumReset(Map<String, String> map) {
        specialBusinessMapper.productNumReset(map);
    }

    /**
     * 获取包装类型数据
     * @return 包装类型数据集
     */
    @Override
    public List<Map<String, Object>> getPackageTypes() {
        return specialBusinessMapper.getPackageTypes();
    }

    /**
     * 包装条码重置
     * @param map
     */
    @Override
    public void packageNumReset(Map<String, String> map) {
        specialBusinessMapper.packageNumReset(map);
    }

    /**
     * 包装尾数重置
     * @param map
     */
    @Override
    public void packageMantissaReset(Map<String, String> map) {
        specialBusinessMapper.packageMantissaReset(map);
    }

    /**
     * 额外新增工单包装箱
     * @param map
     */
    @Override
    public void additional(Map<String, Object> map) {
        specialBusinessMapper.additional(map);
    }

    /**
     * 工单转产
     * @param map
     */
    @Override
    public void theSingleTurn(Map<String, Object> map) {
        specialBusinessMapper.theSingleTurn(map);
    }

	@Override
	public boolean verifyPackBarcodeIsPlanLine(Map<String, Object> map) {
		List<String> ls =  specialBusinessMapper.verifyPackBarcodeIsPlanLine(map);
		if(ls==null||ls.isEmpty()){
			return false;
		} 
		return true;
	}

	@Override
	public boolean planLineReset(Map<String, Object> map) { 
		try {
			//包装箱
			specialBusinessMapper.planLineResetHeaders(map);
			//产品
			specialBusinessMapper.planLineResetBarcodes(map);
			return true;
		} catch (Exception e) { 
			e.printStackTrace();
			return false;
		}
		
	}

    @Override
    public boolean verifyWipName(Map<String, Object> map) {
        List<String> ls =  specialBusinessMapper.verifyWipName(map);
        if(ls==null||ls.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public boolean wipNumReset(Map<String, Object> map) {

        try {
            specialBusinessMapper.wipNumReset(map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
