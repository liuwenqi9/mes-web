$(function() {
	getDetailTable();

	$('#excel_btn,#excel_btn1').on('click', function() {
		// confirmAndCancelModal
		$('#confirmAndCancelModal').modal();
	});

	$('#confirm_btn').on(
			'click',
			function() {
				$('#confirmAndCancelModal').modal('hide');
				window.open(basePath
						+ 'stocktakingController/exportDetailExcel?headerId='
						+ $('#headerId').val() + '&organizationId='
						+ $('#organizationId').val());

			});

	// 盘点差异处理
	$('#differ_btn,#differ_btn1').on('click', function() {
		var rows = $('#detailTable').bootstrapTable('getSelections');
		if (rows.length > 0) {
			alert('网络异常！');
		} else {
			alert('请选择需要的数据！');
		}
	});

});

// 盘点明细信息table
function getDetailTable() {
	var $detailTable = $('#detailTable');
	$detailTable.bootstrapTable("destroy");
	$detailTable.bootstrapTable({
		url : basePath + 'stocktakingController/getDetailList?headerId='
				+ $('#headerId').val() + '&organizationId='
				+ $('#organizationId').val(),
		queryParams : function(params) {
			return {
				offset : params.offset + 1,
				limit : params.limit
			};
		},
		//分页方式：client客户端分页，server服务端分页（*）
		sidePagination : "server",
		//是否分页
		pagination : true,
		//请求方法 post方式会自动把参数封装为json格式
		method : 'get',
		//服务器返回的数据类型
		//dataType : "json",
		//是否显示行间隔色
		striped : true,
		//是否显示所有的列
		showColumns : true,
		// 是否显示刷新按钮
		showRefresh : false,
		//行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		//height: 30,
		//是否只能选择单行
		singleSelect:true,
		//是否启用点击选中行
		clickToSelect: true,
		//中文支持
		dataLocale:"zh-US",
		// 是否启用排序
		sortable : true,
		// 排序方式
		sortOrder : "desc",
		//头信息
		//toolbar: '#productBarcode_toolbar',
		//默认加载条数
		pageSize : 500,
		//每页显示数据条数
		pageList : [100,200,500,1000],
		// 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		cache : false,
		// 是否显示分页（*）
		pagination : true,
		// 是否启用排序
		sortable : false,
		// 排序方式
		sortOrder : "desc",
		// 初始化加载第一页，默认第一页
		// 我设置了这一项，但是貌似没起作用，而且我这默认是0,- -
		//pageNumber:1,
		columns : [ /*{
			checkbox : true,
			align : 'center',
			valign : 'middle'
		},*/ {
			title : '盘点序列号',
			field : 'PD_NUMBER',
			align : 'center',
			valign : 'middle'
		}, {
			title : '盘点时间',
			field : 'PD_DATE',
			align : 'center',
			valign : 'middle'
		}, {
			title : '子库',
			field : 'SUBINVENTORY_CODE',
			align : 'center',
			valign : 'middle'
		}, {
			title : '货位',
			field : 'LOCATTION_CODE',
			align : 'center',
			valign : 'middle'
		},{
			title : '物料编号',
			field : 'SEGMENT1',
			align : 'center',
			valign : 'middle'
		}, {
			title : '规格型号',
			field : 'PROD_TYPE',
			align : 'center',
			valign : 'middle'
		}, {
			title : '包装箱',
			field : 'PACK_BARCODE',
			align : 'center',
			valign : 'middle'
		}, {
			title : '序列号',
			field : 'BARCODE_TEXT',
			align : 'center',
			valign : 'middle'
		},{
			title : '工单号',
			field : 'WIP_ENTITY_NAME',
			align : 'center',
			valign : 'middle'
		},{
			title : '条码</br>类型',
			field : 'TYPE_DESCRIPTION',
			align : 'center',
			valign : 'middle'
		}, {
			title : '条码状态',
			field : 'STATUS_DESCRIPTION',
			align : 'center',
			valign : 'middle'
		}, {
			title : '条码库</br>存数',
			field : 'PD_QUANTITY',
			align : 'center' 
		}, {
			title : '条码盘</br>存数',
			field : 'BARCODE_QUANTITY',
			align : 'center' 
		}, {
			title : '差异</br>数',
			field : 'XX',
			align : 'center', 
			formatter : function(value, row, index) {
				var e = row.BARCODE_QUANTITY - row.PD_QUANTITY;
				return e;
			}
		},{
			title : '物料描述',
			field : 'DESCRIPTION',
			align : 'left',
			valign : 'middle'
		} ]
	});
	
}
