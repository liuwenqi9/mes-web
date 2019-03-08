/*********************************************************************************
 * 
 * 共通选择器JS
 * 
 *********************************************************************************/
/*================================================================================
 * 子库查询 START
 *================================================================================
 */
//初始化表格
function initSublibrarys() {
	var getSublibrarys_url = basePath + 'sublibraryController/searchSublibrary';
	var getSublibrarys_suc;
	// 初始化
	getSublibrarys_suc = function(datass) {
		$('#sublibrarySelectTable').bootstrapTable({
			data : strToJson(datass)
		});
	}
	eGdUtil.requestJsonAjax(getSublibrarys_url, null, getSublibrarys_suc);
};

//查询子库
function searchSublibrary(){
	var params = { sublibraryName : $('#sublibraryName_id').val() };
	var url = basePath + 'sublibraryController/searchSublibrary';
	var success = function(datass) { 
		$('#sublibrarySelectTable').bootstrapTable('load', {
			data : strToJson(datass)
		});
	};
	eGdUtil.requestJsonAjax(url, params, success);
}
//控件初始化
function initSublibrary(subCodeId,subDesrId){
	//将回传的id存入隐藏框
	$('#subCodeId').val(subCodeId);
	$('#subDesrId').val(subDesrId);
}
//弹出选择子库框事件
function subinventorySearchBtnClick(subCodeId,subDesrId){
	//初始化查询
	initSublibrarys();
	//初始化控件
	initSublibrary(subCodeId,subDesrId);
	//弹出框
	$('#subinventorySelectModal').modal({
		show : true,
		backdrop : false
	});
}
//绑定子库选择器事件
function initSublibraryEvent(){
	// 选择子库界面键盘事件
	$('#sublibraryName_id').keydown(function(e) {
		if (e.keyCode == 13) {
			searchSublibrary();
		}
	});
	// 查询按钮事件
	$('#sublibrary_search_btn').click(
			function() {
				searchSublibrary();
			});
	//选择子库界面click选中，值返回 父页面
	$('#sublibrarySelectTable').click(function() {
		var subCodeId=$('#subCodeId').val();
		var subDesrId=$('#subDesrId').val();
		
		var row = $('#sublibrarySelectTable').bootstrapTable('getSelections');
		if (row.length > 0) {
			//初始化add表单数据
			if(subCodeId){
				$('#'+subCodeId).val(row[0].SECONDARY_INVENTORY_NAME);
			}
			if(subDesrId){
				$('#'+subDesrId).val(row[0].DESCRIPTION);
			}
			$('#subinventorySelectModal').modal('hide');
			$('#sublibrarySelectTable').bootstrapTable('destroy');
		}
	});
}
/*================================================================================
 * 子库查询 END
 *================================================================================
 */
/*================================================================================
 * 货位查询 START
 *================================================================================
 */
//初始化查询
function getCargospace(sublibraryName) {
	var params = { 
			sublibraryName : sublibraryName 
		};
	var getCargospace_url = basePath + 'cargospaceController/searchCargospace';
	var getCargospace_suc;
	// 初始化
	getCargospace_suc = function(datass) {
		$('#cargospaceSelectTable').bootstrapTable({
			data : strToJson(datass)
		});
	}
	eGdUtil.requestJsonAjax(getCargospace_url, params, getCargospace_suc);
};
//初始化控件
function initCargospace(cargospaceId,sublibraryName){
	$('#cargospaceId').val(cargospaceId);
	$('#sublibraryName').val(sublibraryName);
}
//弹出选择货位框事件
function cargospaceSearchBtnClick(cargospaceId,sublibraryName){
	//弹出框
	$('#cargospaceSelectModal').modal({
		show : true,
		backdrop : false
	});
	//初始化查询
	getCargospace(sublibraryName);
	//初始化控件
	initCargospace(cargospaceId,sublibraryName);
}

//绑定货位选择器事件
function initCargospaceEvent(){
	// 查询按钮事件
	$('#cargospace_search_btn').click(
		function() {
			var params = { 
				sublibraryName : $('#sublibraryName').val() ,
				cargospaceName : $('#cargospaceName_sid').val() 
					
			};
			var url = basePath + 'cargospaceController/searchCargospace';
			var success = function(datass) { 
				$('#cargospaceSelectTable').bootstrapTable('load', {
					data : strToJson(datass)
				});
			};
			eGdUtil.requestJsonAjax(url, params, success);
		});

	//选择货位界面click选中，值返回 父页面
	$('#cargospaceSelectTable').click(function() {
		var cargospaceId=$('#cargospaceId').val();
		
		var row = $('#cargospaceSelectTable').bootstrapTable('getSelections');
		if (row.length > 0) {
			//初始化add表单数据
			if(cargospaceId){
				$('#'+cargospaceId).val(row[0].LOCATTION_CODE);
			}
			$('#cargospaceSelectModal').modal('hide');
			$('#cargospaceSelectTable').bootstrapTable('destroy');
		}
	});
}
/*================================================================================
 * 货位查询 END
 *================================================================================
 */
/*================================================================================
 * 来源查询 START
 *================================================================================
 */
//初始化查询
function getSource() {
	var getSource_url = basePath + 'miscellaneousDisposalController/searchSourceByName';
	var getSource_suc;
	// 初始化
	getSource_suc = function(datass) {
		$('#sourceTable').bootstrapTable({
			data : strToJson(datass)
		});
	}
	eGdUtil.requestJsonAjax(getSource_url, null, getSource_suc);
};
//初始化控件
function initSource(sourceId){
	$('#sourceId').val(sourceId);
}
//弹出选择来源框事件
function sourceSearchBtnClick(sourceId){
	//初始化查询
	getSource();
	//初始化控件
	initSource(sourceId);
	//弹出框
	$('#sourceSelectModal').modal({
		show : true,
		backdrop : false
	});
}

//绑定来源选择器事件
function initSourceEvent(){
	// 查询按钮事件
	$('#source_search_btn').click(
		function() {

			var params = { 
				sublibraryName : $('#sublibraryName_sid').val() ,
				sourceName : $('#sourceName_sid').val()
			};
			var url = basePath + 'miscellaneousDisposalController/searchSourceByName';
			var success = function(datass) { 
				$('#sourceTable').bootstrapTable('load', {
					data : strToJson(datass)
				});
			};
			eGdUtil.requestJsonAjax(url, params, success);
		});

	//选择来源界面click选中，值返回 父页面
	$('#sourceTable').click(function() {
		var sourceId=$('#sourceId').val();
		
		var row = $('#sourceTable').bootstrapTable('getSelections');
		if (row.length > 0) {
			//初始化add表单数据
			if(sourceId){
				$('#'+sourceId).val(row[0].SEGMENT1);
			}
			$('#sourceSelectModal').modal('hide');
			$('#sourceTable').bootstrapTable('destroy');
		}
	});
}
/*================================================================================
 * 来源查询 END
 *================================================================================
 */
$(function() {
	//绑定子库选择器事件
	initSublibraryEvent();
	//绑定货位选择器事件
	initCargospaceEvent();
	//绑定来源选择器事件
	initSourceEvent();
});


