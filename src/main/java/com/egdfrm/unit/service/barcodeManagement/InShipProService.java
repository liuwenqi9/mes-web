package com.egdfrm.unit.service.barcodeManagement;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.core.model.common.Json;
import com.egdfrm.unit.common.Pagination;
import com.egdfrm.unit.mapper.barcodeManagement.InShipProMapper;
import com.egdfrm.unit.model.barcodeManagement.InPro;
 

@Service
public class InShipProService {
	
	@Autowired
	private InShipProMapper inShipProMapper;
	
	/**
	 *  分页查询
	 */
	public Pagination getModalTableLists(Pagination pagination,InPro inPro){
		Long total = inShipProMapper.getModalTableCount(inPro);
		List<InPro> rows = inShipProMapper.getModalTableList(pagination, inPro);
		pagination.setTotal(total);
        pagination.setRows(rows);
        return pagination;
	} 
	
	/**
	 *  批量添加
	 */
	public Json batchInsertToShip(String uid,Json json,InPro inPro){ 
		String[] barcodeTexts = inPro.getBarcodeTexts().split(",");
		String[] inventoryItemIds = inPro.getInventoryItemIds().split(","); 
		//插入
		for (int i = 0; i < barcodeTexts.length; i++) {
			InPro model = new InPro();
			model.setBarcodeText(barcodeTexts[i]);
			model.setInventoryItemId(Integer.parseInt(inventoryItemIds[i]));
			int result = inShipProMapper.insertToShip(model, uid);
			if (result > 0) {
				json.setMessage("导入成功！");
				json.setSuccess(true);
			}
		} 
		return json; 
		
	}
	/**
	 *  批量更新
	 */
	public Json batchUpdateToShip(String uid,Json json,InPro inPro){
		String[] barcodeTexts = inPro.getBarcodeTexts().split(",");
		String[] inventoryItemIds = inPro.getInventoryItemIds().split(",");
		//更新
		for (int i = 0; i < barcodeTexts.length; i++) {
			InPro model = new InPro();
			model.setBarcodeText(barcodeTexts[i]);
			model.setInventoryItemId(Integer.parseInt(inventoryItemIds[i]));
			int result = inShipProMapper.updateToShip(model, uid);
			if (result > 0) {
				json.setMessage("更新成功！");
				json.setSuccess(true);
			}
		}
		return json;

	}
	/**
	 * S 通过，E不通过
	 *  验证产品条码是否存在
	 */
	public String[] isNull(InPro inPro) {
		String[] msg = new String[2];
		msg[0] = "S";
		msg[1] = "";
		String[] barcodeTexts = inPro.getBarcodeTexts().split(",");
		// 验证
		for (int i = 0; i < barcodeTexts.length; i++) {
			
			InPro model = new InPro();
			model.setBarcodeText(barcodeTexts[i]); 
			List<String> list = inShipProMapper.isNull(model);
			if (list != null && list.size()>0) { 
				msg[0] = "E";
				msg[1] +=model.getBarcodeText()+" ";
			}
		}
		return msg;
	}

	/**
	 * 验证条码状态是否有效，
	 * select wb.* from mes.mes_wip_barcodes wb.wip_entity_id is null and wb.primary_item_idis null and wb.status_code='PROD' and wb.barcode_text=''
	 * @param inPro
	 * @return
	 */
	public String[] isSatus(InPro inPro) {
		String[] msg = new String[2];
		msg[0] = "S";
		msg[1] = "";
		String[] barcodeTexts = inPro.getBarcodeTexts().split(",");
		// 验证
		for (int i = 0; i < barcodeTexts.length; i++) {

			InPro model = new InPro();
			model.setBarcodeText(barcodeTexts[i]);
			List<Map<String,Object>> list = inShipProMapper.isStatus(model);
			if (list == null ) {
				msg[0] = "E";
				msg[1] +=model.getBarcodeText()+" ";
			}else if (list.size()==0){
				msg[0] = "E";
				msg[1] +=model.getBarcodeText()+" ";
			}
		}
		return msg;
	}

	/**
	 *  导入退货订单
	 * @param inPro
	 * @param uid
	 * @return
	 */
	public  int updateShip(InPro inPro,String uid){
		return  inShipProMapper.updateToShip(inPro,uid);
	}
}
