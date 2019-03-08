/*****
 * 货位管理界面JS
 */
//读取货位信息
function initCargospaces(load) {
	var initCargospace_url = basePath + 'cargospaceController/searchCargospace';
	var initCargospace_suc;
	if (eGdUtil.isEmpty(load)) {
		// 初始化
		initCargospace_suc = function(datass) {
			$('#cargospaceTable').bootstrapTable({
				data : strToJson(datass)
			});
		}
	} else {
		initCargospace_suc = function(datass) {
			$('#cargospaceTable').bootstrapTable('load', {
				data : strToJson(datass)
			});
		}
	}
	eGdUtil.requestJsonAjax(initCargospace_url, null, initCargospace_suc);
};
//初始化控件
function init(){
	//回车查询
	$('#sublibraryNameCid').keydown(function(e){
		if (e.keyCode == 13) {
            $('#cargospaceSearchBtn').click();
         } 
	});
	//回车查询
	$('#cargospaceNameCid').keydown(function(e){
		if (e.keyCode == 13) {
            $('#cargospaceSearchBtn').click();
         } 
	});
	// 查询按钮事件
	$('#cargospaceSearchBtn').click(
			function() {
				var params = { 
					sublibraryName : $('#sublibraryNameCid').val() ,
					cargospaceName : $('#cargospaceNameCid').val() 
						
				};
				var url = basePath + 'cargospaceController/searchCargospace';
				var success = function(datass) { 
					$('#cargospaceTable').bootstrapTable('load', {
						data : strToJson(datass)
					});
				};
				eGdUtil.requestJsonAjax(url, params, success);
			});
	
	//货位新增按钮
	$('#cargospace_add_btn').click(function() {
		//新增页面弹出
		$('#addCargospaceModal').modal({
			show : true,
			backdrop : false
		});
		//失效日期控件初始化
		$('#DISABLE_DATE_add').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
	    });
	});

	//弹出选择子库框事件
	$('#subinventory_search_btn').click(function() {
		subinventorySearchBtnClick('SUBINVENTORY_CODE_add','SUBINVENTORY_DESR_add');
	});

	//货位新增保存按钮事件
	$('#add_cargospace_save_btn').click(function() {
		var subInvVal = $('#SUBINVENTORY_CODE_add').val();
		var LocCodeVal = $('#LOCATTION_CODE_add').val();
		if(eGdUtil.isEmpty(subInvVal)){
			alert('请选择子库！');
			return;
		} 
		if(eGdUtil.isEmpty(LocCodeVal)){
			alert('请输入货位名！');
			return;
		} 
		if(LocCodeVal.trim()==''){
			alert('货位名不能空');
			return;
		} 
		var url = basePath + 'cargospaceController/addCargospace';
		var params = $('#addCargospaceForm').serializeObject();
		var success = function(datass) {
			$('#addCargospaceModal').modal('hide');
			$(':input', '#addCargospaceForm').not(':button, :submit, :reset, :hidden')
			.val('').removeAttr('checked').removeAttr('selected');
			initCargospaces('load');
		};
		eGdUtil.requestJsonAjax(url, params, success);
	});

	//货位修改按钮
	$('#cargospace_edit_btn').click(function() {
		//获取选择行
		var row = $('#cargospaceTable').bootstrapTable('getSelections');
		if (row.length > 0) { 
			//失效日期控件初始化
			$('#DISABLE_DATE_edit').datetimepicker({
		        language:  'zh-CN',
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0
		    });
			var disableDate=row[0].DISABLE_DATE?new Date(row[0].DISABLE_DATE).Format("yyyy-MM-dd hh:mm:ss"):'';
			//初始化编辑页面
			$('#SUBINVENTORY_CODE_edit').val(row[0].SUBINVENTORY_CODE);
			$('#SUBINVENTORY_DESR_edit').val(row[0].SUBINVENTORY_DESR);
			$('#LOCATTION_CODE_edit').val(row[0].LOCATTION_CODE);
			$('#DESCRIPTION_edit').val(row[0].DESCRIPTION);
			$('#DISABLE_DATE_edit').val(disableDate);

			//新增页面弹出
			$('#editCargospaceModal').modal({
				show : true,
				backdrop : false
			});
		} else {
			alert("请选择需要修改的数据");
		}
	});

	//货位修改保存按钮事件
	$('#edit_cargospace_save_btn').click(function() {
		var val = $('#SUBINVENTORY_CODE_edit').val();
		if (val != '') {
			var url = basePath + 'cargospaceController/editCargospace';
			var params = $('#editCargospaceForm').serializeObject();
			var success = function(datass) {
				$('#editCargospaceModal').modal('hide');
				$(':input', '#addCargospaceForm').not(':button, :submit, :reset, :hidden')
				.val('').removeAttr('checked').removeAttr('selected');
				initCargospaces('load');
			};
			eGdUtil.requestJsonAjax(url, params, success);
		} else {
			alert('请选择子库！');
		}
	});
}
$(function() {
	//初始化查询
	initCargospaces(); 
	//初始化控件
	init();
})
