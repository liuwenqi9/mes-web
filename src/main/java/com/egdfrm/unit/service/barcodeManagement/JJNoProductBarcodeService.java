package com.egdfrm.unit.service.barcodeManagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egdfrm.core.model.common.Json;
import com.egdfrm.unit.mapper.barcodeManagement.JJNoProductBarcodeMapper;
import com.egdfrm.unit.model.barcodeManagement.JJNoproductBarcode;

@Service
public class JJNoProductBarcodeService {
	
	@Autowired
	private JJNoProductBarcodeMapper jjNoProductBarcodeMapper;
	
	public String[] verifyProductBarcodes(String productBarcodes){
		String[] re = new String[2];
		re[0] = "S";
		re[1] = "";
		String[] productBarcode = productBarcodes.split(",");
		for (int i = 0; i < productBarcode.length; i++) {
		 List<String> list =jjNoProductBarcodeMapper.verifyProductBarcode(productBarcode[i]);
		 if(list!=null && list.size()>0){//符合 更新条件
		 }else{
			 re[0] = "E";
			 re[1]+=productBarcode[i]+",";
		 }
		}
		return re ;
	}

	public String[] verifyProductBarcodesAdd(String productBarcodes){
		String[] re = new String[2];
		re[0] = "S";
		re[1] = "";
		String[] productBarcode = productBarcodes.split(",");
		for (int i = 0; i < productBarcode.length; i++) {
		 List<String> list =jjNoProductBarcodeMapper.verifyProductBarcodeAdd(productBarcode[i]);
		 if(list!=null && list.size()>0){
			 re[0] = "E";
			 re[1]+=productBarcode[i]+",";
		 }else{//符合 添加条件
			 
		 }
		}
		return re ;
	}
	 /** 
	  *  验证条码是否为上线返修
	  */
	public String[] verifyProductBarcodeIsReprod(String productBarcodes){
		String[] re = new String[2];
		re[0] = "S";
		re[1] = "";
		String[] productBarcode = productBarcodes.split(",");
		for (int i = 0; i < productBarcode.length; i++) {
		 List<String> list =jjNoProductBarcodeMapper.verifyProductBarcodeIsReprod(productBarcode[i]);
		 if(list!=null && list.size()>0){
			 re[0] = "E";
			 re[1]+=productBarcode[i]+",";
		 }else{//符合 更新条件
			 
		 }
		}
		return re ;
	}
	
	public int batchUpdate(Integer userId,JJNoproductBarcode models){
		String barcodes[] = models.getBarcodeText().split(",");
		String inventoryItemIds[] = models.getInventoryItemId().split(","); 
		String sourceSubLibrarys[] = models.getSourceSubLibrary().split(",");
		String sourceLocations[] = models.getSourceLocation().split(",");
		int result = 0;
		for (int i = 0; i < barcodes.length; i++) {
			JJNoproductBarcode model = new JJNoproductBarcode();
			model.setBarcodeText(barcodes[i]); 
			model.setCodeId(Integer.parseInt(inventoryItemIds[i]));
			model.setSourceSubLibrary(sourceSubLibrarys[i]);
			model.setSourceLocation(sourceLocations[i]);
			result = jjNoProductBarcodeMapper.update(userId,model);
		}
		return result;
	}
	
	public int batchInsert(Integer userId,JJNoproductBarcode models){
		String barcodes[] = models.getBarcodeText().split(",");
		String inventoryItemIds[] = models.getInventoryItemId().split(","); 
		String sourceSubLibrarys[] = models.getSourceSubLibrary().split(",");
		String sourceLocations[] = models.getSourceLocation().split(",");
		int result = 0;
		for (int i = 0; i < barcodes.length; i++) {
			JJNoproductBarcode model = new JJNoproductBarcode();
			model.setBarcodeText(barcodes[i]); 
			model.setCodeId(Integer.parseInt(inventoryItemIds[i]));
			model.setSourceSubLibrary(sourceSubLibrarys[i]);
			model.setSourceLocation(sourceLocations[i]);
			result = jjNoProductBarcodeMapper.insert(userId,model);
		}
		return result;
	}
	
	public int batchInsertToShip(Integer userId,JJNoproductBarcode models){
		String barcodes[] = models.getBarcodeText().split(",");
		String inventoryItemIds[] = models.getInventoryItemId().split(","); 
		String sourceSubLibrarys[] = models.getSourceSubLibrary().split(",");
		String sourceLocations[] = models.getSourceLocation().split(",");
		int result = 0;
		for (int i = 0; i < barcodes.length; i++) {
			JJNoproductBarcode model = new JJNoproductBarcode();
			model.setBarcodeText(barcodes[i]); 
			model.setCodeId(Integer.parseInt(inventoryItemIds[i]));
			model.setSourceSubLibrary(sourceSubLibrarys[i]);
			model.setSourceLocation(sourceLocations[i]);
			result = jjNoProductBarcodeMapper.insertToShip(userId,model);
		}
		return result;
	}
	
	public int batchUpdateToShip(Integer userId,JJNoproductBarcode models){
		String barcodes[] = models.getBarcodeText().split(",");
		String inventoryItemIds[] = models.getInventoryItemId().split(","); 
		String sourceSubLibrarys[] = models.getSourceSubLibrary().split(",");
		String sourceLocations[] = models.getSourceLocation().split(",");
		int result = 0;
		for (int i = 0; i < barcodes.length; i++) {
			JJNoproductBarcode model = new JJNoproductBarcode();
			model.setBarcodeText(barcodes[i]); 
			model.setCodeId(Integer.parseInt(inventoryItemIds[i]));
			model.setSourceSubLibrary(sourceSubLibrarys[i]);
			model.setSourceLocation(sourceLocations[i]);
			result = jjNoProductBarcodeMapper.updateToShip(userId,model);
		}
		return result;
	}
	
	public Json verifyCodeId(String segment1){
		Json json = new Json();
		List<JJNoproductBarcode> list = jjNoProductBarcodeMapper.verifyCodeId(segment1);
		if(list!=null && list.size()>0){//存在  取物料型号
			json.setSuccess(true); 
			json.setResult(list.get(0));
		}else{
			json.setSuccess(false);
			json.setMessage("物料编码"+segment1+"不存在");
		} 
		return json;
	}
	public Json getSecondaryInventoryName(){
		Json json = new Json();
		List<JJNoproductBarcode> list = jjNoProductBarcodeMapper.getSecondaryInventoryName();
		if(list!=null && list.size()>0){//取到子库
			json.setSuccess(true); 
			json.setResult(list);
		} 
		return json;
	}
	public Json getLocattionCode(String locattionCode){
		Json json = new Json();
		List<JJNoproductBarcode> list = jjNoProductBarcodeMapper.getLocattionCode(locattionCode);
		if(list!=null && list.size()>0){//取到货位
			json.setSuccess(true); 
			json.setResult(list);
		} 
		return json;
	}
	
	/**
	 *  验证条码是否存在于Barcode表
	 * @date 2017-4-26
	 */
	public Json verifyBarcode(String barcode){
		Json json = new Json();
		List<String> list = jjNoProductBarcodeMapper.verifyBarcode(barcode);
		if(list!=null && list.size()>0){//存在 
			json.setSuccess(true); 
		}else{
			json.setSuccess(false);
			json.setMessage("产品条码"+barcode+"不存在");
		} 
		return json;
	}
}
