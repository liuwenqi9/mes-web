/*****
 * 包装条码信息查询界面JS
 */

//查询包装条码信息
function searchPackageBarcode(){
	var packageBarcodeVal=$('#packageBarcodeId').val();
	if(eGdUtil.isEmpty(packageBarcodeVal.trim())){
		alert('请输入包装条码');
		return;
	}
	var params = { packageBarcode : packageBarcodeVal };
	var url = basePath + 'packageBarcodeController/packageBarcodeSearch';
	var success = function(datass) { 
		$('#packageBarcodeTable').bootstrapTable('load', {
			data : strToJson(datass)
		});
	};
	eGdUtil.requestJsonAjax(url, params, success);
}
//初始化
function init(){
	//回车提交
	$('#packageBarcodeId').keydown(function(e){
		if (e.keyCode == 13) {
			searchPackageBarcode();
         } 
	}); 
	// 查询按钮事件
	$('#packageBarcodeSearchBtn').click(
			function() {
				searchPackageBarcode();
			});
}
$(function() {
	//初始化表格
	$('#packageBarcodeTable').bootstrapTable();
	// 初始化控件
	init();

})
