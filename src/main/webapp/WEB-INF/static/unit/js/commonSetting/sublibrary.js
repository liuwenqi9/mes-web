/*****
 * 子库一览界面JS
 */
//初始化表格
function initSublibrarys() {
	var getSublibrarys_url = basePath + 'sublibraryController/searchSublibrary';
	var getSublibrarys_suc;
	// 初始化
	getSublibrarys_suc = function(datass) {
		$('#sublibraryTable').bootstrapTable({
			data : strToJson(datass)
		});
	}
	eGdUtil.requestJsonAjax(getSublibrarys_url, null, getSublibrarys_suc);
};

//查询子库
function searchSublibrary(){
	var params = { sublibraryName : $('#sublibraryNameId').val() };
	var url = basePath + 'sublibraryController/searchSublibrary';
	var success = function(datass) { 
		$('#sublibraryTable').bootstrapTable('load', {
			data : strToJson(datass)
		});
	};
	eGdUtil.requestJsonAjax(url, params, success);
}
//初始化
function init(){
	//回车提交
	$('#sublibraryNameId').keydown(function(e){
		if (e.keyCode == 13) {
			searchSublibrary();
         } 
	}); 
	// 查询按钮事件
	$('#sublibrarySearchBtn').click(
			function() {
				searchSublibrary();
			});
}
$(function() {
	//初始化查询
	initSublibrarys(); 
	// 初始化控件
	init();

})
