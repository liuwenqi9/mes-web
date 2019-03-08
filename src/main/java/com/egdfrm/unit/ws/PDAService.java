package com.egdfrm.unit.ws;

import javax.jws.WebService;

@WebService
public interface PDAService {
	//登录界面初始化
	public String[] init();
	//用户登录
	public String[] login(String[] userNamePassWord);
	//获取PDA权限菜单
	public String[] initPdaMenu(String userName);
	//PDA二次包装/发运包装-扫描包装条码 
	public String[] packagingPackBarcode(String[] receiveVal);
	//PDA二次包装/发运包装-扫描产品条码  
	public String[] packagingWIPBarcode(String[] input);
	//PDA二次包装-提交
	public String[] packagingCommit(String jsonString,String status);
	//PDA发运包装-提交
	public String[] shipPackagingCommit(String jsonString);
	//PDA拆包-扫描
	public String[] packagingUnpack(String[] input);
	//PDA拆包-提交
	public String[] packagingUnpackCommit(String jsonString);
	//PDA工单完工入库-获取建议货位
	public String[] completeAdviseLocations (String[] receiveVal);
	 //PDA工单完工入库-较验货位（全部调用pl/sql） 
	public String[] completeCheckLocations (String[] input);
	 //PDA工单完工入库-扫描货位 
	public String[] completeScanLocations (String[] input);
	 //PDA工单完工入库-提交
	public String[] completeCommit (String jsonString);
	 //PDA子库转移-扫描
	public String[] inventoryTransferScan (String[] input);
	 //PDA子库转移-提交
	public String[] inventoryTransferCommit (String jsonString); 
	 //PDA挑库-验证挑库单号
	public String[] pickingCheckNumber(String[] input);
	 //PDA挑库-扫描条码
	public String[] pickingScanBarcode(String[] input);
	 //PDA挑库-提交
	public String[] pickingCommit(String jsonString);
	 //PDA发运确认-扫描
	public String[] dispatchScan(String[] input);
	 //PDA发运确认-提交
	public String[] dispatchCommit(String jsonString); 
	 //PDA库存盘点-初始化获取序列号
	public String[] stocktakingInitSeq(String[] input);
	 //PDA库存盘点-扫描货位
	public String[] stocktakingScanLocattion(String[] input); 
	 //PDA库存盘点-扫描包装/产品条码
	public String[] stocktakingScanWip(String[] input);
	 //PDA库存盘点-提交
	public String[] stocktakingCommit(String jsonString);
	//PDA上线返修-扫描领料单
	public String[] onLinerepairMaterialRequisitionScan(String[] input);
	 //PDA上线返修-扫描包装/产品条码
	public String onLinerepairScan(String[] input);
	 //PDA上线返修-提交
	public String[] onLinerepairCommit(String jsonString);
	//PDA上线返修退回-扫描包装/产品条码
	public String reOnLinerepairScan(String[] input);
	//PDA上线返修退回-提交
	public String[] reOnLinerepairCommit(String jsonString);

	//PDA根据产品条码查询产品信息
	public String[] getProductInfoByBarCode(String[] barCodeAndorgId); 
	//PDA根据包装箱条码查询包装箱信息
	public String[] getPackInfoByBarCode(String[] barCodeAndorgId); 

	//PDA销售退货-扫描RMA订单
	public String[] saleReturnsScanRMAOrder(String[] input);
	//PDA销售退货-扫描货位
	public String[] saleReturnsScanLocations (String[] input);
	//PDA销售退货-扫描条码
	public String[] saleReturnsScanBarCode (String[] input);
	//PDA销售退货-提交
	public String[] saleReturnsCommit (String jsonString);

	//（销售退货确认）提交
	public String[] salesReturnConfirm(String input[]);


	public String[] afterSaleReturnGetProductInfo(String[] barcode) throws Exception;
	
	public String[] afterSaleReturnCommit(String[] productInfo);
	
	public String[] afterSaleDeliverGetProductInfo(String[] barcode);
	
	public String[] afterSaleDeliverCommit(String[] productInfo);
	 //检查入库
	public String[] checkStockStatus(String[] stockNumber);
	//检查入库单是否存在
	public String[] checkIsHaveInStockBill(String[] stockNumber);
	//检查某个箱子是否属于某个入库单
	public String[] checkPackIsBelongInStockBill(String[] stockNumber_and_pack_num);
	//借机--检查借机单号
	public String[] borrowProductCheckBorrowProductBill(String[] parameter);
	//借机--检查产品条码
	public String[] borrowProductCheckProduct(String[] parameter);
	//借机--提交
	public String[] borrowProductCommit(String parameter);
	//外购机拼箱--检查包装箱条码
	public String[] outsourcingPackCheckPack(String[] parameter);
	//外购机拼箱--检查产品条码
	public String[] outsourcingPackCheckProduct(String[] parameter);
	//外购机拼箱--提交
	public String[] outsourcingPackCommit(String parameter);

    //外购机拆箱--检查包装箱条码
    public String[] outsourcingSplitCheckPack(String[] parameter);
    //外购机拆箱--检查产品条码
    public String[] outsourcingSplitCheckProduct(String[] parameter);
    //外购机拆箱--提交
    public String[] outsourcingSplitCommit(String parameter);
	//外销发货--校验出货通知单
	public String[] shipConfirmWXCheckOutSn(String out_sn);
	//外销发货--校验包装箱
	public String[] shipConfirmWXCheckPackNo(String[] input);
	//外销发货--提交
	public String[] shipConfirmWXCommit(String input);
    //(大包装拼装)验证大包装箱有效
	public String[] beingBigPack(String parameter);
	//(大包装拼装)验证小包装箱子的有效
	public String[] beingSmallPack(String[] parameters);
	//(大包装拼装)大包装拼装提交
	public String[] bigPackCommit(String input);
//	//（借机归还）借机单号扫描
//	public String[] opportunityNumber(String parameter);
	//（借机归还）新货位扫描
	public String[] opportunityNewPosition(String parameter);
	//（借机归还）产品条码扫码
	public String[] opportunityBarcodeText(String[] parameters);
	//（借机归还）提交
	public String[] opportunityCommit(String parameter);




}
