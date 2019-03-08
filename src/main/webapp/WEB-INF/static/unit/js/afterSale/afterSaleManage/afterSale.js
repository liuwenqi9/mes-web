var loadTableCount = 1;
// 加载数据字典类型
function getAfterSaleList(refresh) {
	// var url = basePath +
	// 'afterSaleController/getAfterSaleList?rows=9999&page=1';//
	var url = basePath + 'afterSaleController/getAfterSaleList';//
	var success;
	if (eGdUtil.isEmpty(refresh)) {
		// 初始化
		// success = function(datas) {
		// for ( var i in datas.rows) {
		// var data = datas.rows[i];
		// if (data.status == "return") {
		// data.status = "售后退货";
		// }
		// if (data.status == "delivery") {
		// data.status = "售后发货";
		// }
		// }
		//
		// //
		// };

		$('#afterSaleTable').bootstrapTable({
			// data : strToJson(datas.rows),
			url : url,
			// 默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
			// queryParamsType:'',
			// //查询参数,每次调用是会带上这个参数，可自定义

			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit,
					productBarcode : $("#barcode").val().trim(),
					expressNo : $("#expressNo").val().trim(),
					status : $("#choose_status").val(),
					timeType : $("#choose_time_type").val(),
					time_from : $("#date_from").val(),
					time_to : $("#date_to").val()
				};
			},
			// 分页方式：client客户端分页，server服务端分页（*）
			sidePagination : "server",
			pagination : true,
			// 请求方法//改成post会自动把参数封装为json格式
			method : 'get',
			//dataType : "json",//服务器返回的数据类型
			// 是否显示行间隔色
			striped : true,
			// toolbar: '#toolbar',
			// sortable: false, //是否启用排序//用的是服务器的排序，本地排序在列中定义
			// sortOrder: "asc", //排序方式
			showColumns : true, // 是否显示所有的列
			//showRefresh : true, // 是否显示刷新按钮
			// height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
			// uniqueId: "ID", //每一行的唯一标识，一般为主键列
			// showToggle:true, //是否显示详细视图和列表视图的切换按钮
			// cardView: false, //是否显示详细视图
			// detailView: false, //是否显示父子表
			//smartDisplay:true,
			 singleSelect:true,
			 clickToSelect: true,//是否启用点击选中行
			 dataLocale:"zh-US",

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
			// pageNumber:1,
			// 每页的记录行数（*）
			pageSize : 10,
			// 可供选择的每页的行数（*）
			pageList : [ 10, 25, 50, 100 ],


			columns : [

			{
				checkbox : true,
				align : 'center',
				valign: 'middle',
			}
			, {
				title : '条码',
				field : 'productBarcode',
				align : 'center',
				valign: 'middle',
			}, {
				title : '物料编码',
				field : 'materialId',
				align : 'center',
				valign: 'middle',
				
			// valign: 'middle',
			}, {
				title : '物料描述',
				field : 'description',
				align : 'center',
				width : '200px',
				valign: 'middle',
			}, {
				title : '生产日期',
				field : 'productionTimes',
				align : 'center',
				valign: 'middle',
			}, {
				title : '订单发货时间',
				field : 'orderDeliveryTimes',
				align : 'center',
				valign: 'middle',
			}
			,
			{
				title : '状态',
				field : 'status',
				align : 'center',
				valign: 'middle',
				formatter : function(value, row, index) {
					return statusFormatter(value, row);
				}
			}
			,
			{
				title : '退货操作者',
				field : 'returnOperator',
				align : 'center',
				valign: 'middle',
			}, {
				title : '退货时间',
				field : 'returnOperationTime',
				align : 'center',
				valign: 'middle',
			}, {
				title : '发货操作者',
				field : 'deliveryOperator',
				align : 'center',
				sortable : true,
				valign: 'middle',
			}, {
				title : '发货时间',
				field : 'deliveryOperationTime',
				align : 'center',
				sortable : true,
				valign: 'middle',
			}, {
				title : '快递单号',
				field : 'expressNo',
				align : 'center',
				valign: 'middle',
			}, {
				title : '产品分类',
				field : 'productCategory',
				align : 'center',
				valign: 'middle',
			},
//			{
//				title : '条码ID',
//				field : 'barcodeid',
//				align : 'center',
//				valign: 'middle',
//			}, 
			{
				title : '工单号',
				field : 'workorder',
				align : 'center',
				valign: 'middle',
			} 
		]

		});

	} else {
		$('#afterSaleTable').bootstrapTable('refresh',{
			// data : strToJson(datas.rows),
			url : url,
			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit,
					productBarcode : $("#barcode").val().trim(),
					expressNo : $("#expressNo").val().trim(),
					status : $("#choose_status").val(),
					timeType : $("#choose_time_type").val(),
					time_from : $("#date_from").val(),
					time_to : $("#date_to").val()
				};
			},
		});
	}
	// eGdUtil.requestJsonAjax(url, params, success);
}
function statusFormatter(value, row) {
	if (value == 'delivery') {
		return "已发货 ";
	} else {
		return "售后中";
	}
}
function addTimeSelect() {
	// 加时间选择器
	$('#date_from').datetimepicker({
		language : 'zh-CN',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		forceParse : 0
	});
	$('#date_to').datetimepicker({
		language : 'zh-CN',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		forceParse : 0
	});
}
function search() {
	// var
	// params="productBarcode="+$("#barcode").val().trim()+"&expressNo="+$("#expressNo").val().trim()+"&status="+$("#choose_status").val()+"&timeType="+$("#choose_time_type").val()+"&time_from="+$("#date_from").val()+"&time_to="+$("#date_to").val();

	getAfterSaleList('refresh');
}

$(function() {
	// 初始化数据字典类型列表
	addTimeSelect();
	getAfterSaleList();
	//刚开始时间选择控件是禁用的
	$("#date_from").attr("disabled", true);
	$("#date_to").attr("disabled", true);
	$("#choose_time_type").change(function() {
		if ($("#choose_time_type").val() == "none") {
			// $("#date_from").attr("readonly","readonly");
			// $("#date_to").attr("readonly","readonly");
			$("#date_from").attr("disabled", true);
			$("#date_to").attr("disabled", true);
		} else {
			// $("#date_from").removeAttr("readonly");
			// $("#date_to").removeAttr("readonly");
			$("#date_from").attr("disabled", false);
			$("#date_to").attr("disabled", false);
		}
	});

});
