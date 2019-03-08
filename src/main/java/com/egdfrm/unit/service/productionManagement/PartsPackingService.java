package com.egdfrm.unit.service.productionManagement;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.egdfrm.unit.mapper.productionManagement.PartsPackingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配件包装接口实现
 * Created by tyq on 17/2/15.
 */
@Service
public class PartsPackingService implements IPartsPackingService {

    @Autowired
    private PartsPackingMapper partsPackingMapper;

    /**
     * 根据工单号查询
     * @param workOrderNumber 工单号
     * @return
     */
    @Override
    public List<Map<String, Object>> queryAll(String workOrderNumber) {
        return partsPackingMapper.queryAll(workOrderNumber);
    }

    /**
     * 保存包装数量
     * @param userId 当前用户
     * @param sipEntityIds 工单ID
     * @param inventoryItemIds 物料ID
     * @param pacgingNums 包装数量
     * @return
     */
    @Override
    public int savePackingNumber(BigDecimal userId, String[] sipEntityIds, String[] inventoryItemIds, String[] pacgingNums) throws Exception{
        //获取包装箱号
        int count = 0;
        for (String pacgingNum : pacgingNums) {
            count += Integer.parseInt(pacgingNum);
        }
        Map<String,Object> map = new HashMap();
        map.put("p_user_id",userId);
        map.put("p_wip_id",BigDecimal.valueOf(Integer.parseInt(sipEntityIds[0])));
        map.put("p_quantity",BigDecimal.valueOf(count));
        map.put("x_barcode_id","");
        map.put("x_return_status","");
        map.put("x_msg_data","");
        partsPackingMapper.getPackageNum(map);
        if("S".equals(map.get("x_return_status"))){
            //包装箱号
            String barcodeId = map.get("x_barcode_id").toString();
            for (int i = 0;i < inventoryItemIds.length;i++) {
                System.out.println(i);
                map = new HashMap();
                map.put("p_user_id",userId);
                map.put("p_wip_id",BigDecimal.valueOf(Integer.parseInt(sipEntityIds[i])));
                map.put("p_item_id",BigDecimal.valueOf(Integer.parseInt(inventoryItemIds[i])));
                map.put("p_quantity",BigDecimal.valueOf(Integer.parseInt(pacgingNums[i])));
                map.put("p_barcode_id",BigDecimal.valueOf(Integer.parseInt(barcodeId)));
                map.put("x_return_status","");
                map.put("x_msg_data","");
                partsPackingMapper.savePackingNumber(map);
                if("E".equals(map.get("x_return_status"))) throw new Exception("保存包装数量");
            }
        }
        return 1;
    }

    /**
     * 包装箱查询
     * @param workOrderNumber
     * @return
     */
    @Override
    public List<Map<String, Object>> queryPackingAll(String workOrderNumber) {
        return partsPackingMapper.queryPackingAll(workOrderNumber);
    }

    /**
     * 根据包装ID查询包装信息
     * @param barcodeId 包装ID
     * @return
     */
    @Override
    public Map<String, Object> findPackingById(String barcodeId) {
        return partsPackingMapper.findPackingById(barcodeId);
    }

    /**
     * 包装打印数据查询
     * @param barcodeId 包装ID
     * @return
     */
    @Override
    public List<Map<String, Object>> getRows(String barcodeId) {
        return partsPackingMapper.getRows(barcodeId);
    }

    /**
     * 修改打印状态
     * @param barcodeIds 包装箱ID
     * @return
     */
    @Override
    public int updateStatus(String[] barcodeIds) {
        return partsPackingMapper.updateStatus(barcodeIds);
    }
}
