function getProductBarcode(refresh) {
	
	var url = basePath + 'cargospaceBarcodeController/getCargospaceBarcode';
	//var url = basePath + 'cargospace.json';
	// var url = basePath +
	//var url = basePath + 'afterSaleController/getAfterSaleList';//
	var success;
	if (eGdUtil.isEmpty(refresh)) {
		// 初始化
		$('#cargospaceBarcodeTable').bootstrapTable({
			// data : strToJson(datas.rows),
			url : url,
			// 默认值为 'limit',传给服务端的参数为：limit, offset, search, sort, order Else
			// queryParamsType:'',
			// //查询参数,每次调用是会带上这个参数，可自定义

			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit,
					subinventoryCode : $("#input_sublib").val().trim(),
					locattionCode : $("#input_cargospace").val().trim()
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
			//singleSelect:true,//是否全选
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
					title : '货位',
					field : 'locattionCode',
					align : 'center',
					valign: 'middle',
				}, {
				title : '货位描述',
				field : 'description',
				align : 'center',
				valign: 'middle',
				
			// valign: 'middle',
				},{
					title : '子库',
					field : 'subinventoryCode',
					align : 'center',
					valign: 'middle',
				}
			, {
				title : '状态',
				field : 'data2',
				align : 'center',
				width : '200px',
				valign: 'middle',
				formatter : function(value, row, index) {
					var ok='有效';
					var ng='失效';
					if(value==null||value==''){
						return ok;
					}
					var current_time=new Date();
					var current_time_second=current_time.getTime();
					return value>current_time_second?ok:ng;
				}
			}
		]

		});

	} else {
		$('#cargospaceBarcodeTable').bootstrapTable('refresh',{
			// data : strToJson(datas.rows),
			url : url,
			queryParams : function(params) {
				return {
					offset : params.offset + 1,
					limit : params.limit,
					subinventoryCode : $("#input_sublib").val().trim(),
					locattionCode : $("#input_cargospace").val().trim()
				};
			},
		});
	}
	// eGdUtil.requestJsonAjax(url, params, success);
	
	
	

} 
// 
$(function() {
	getProductBarcode();  
	var table_falg = 0;
	var check_row = [];
	//查询btn
	$('#cargospaceBarcode_search_btn').on('click',function(){
		
		getProductBarcode('refresh');
	});
		
	

	//打印
	$("#cargospaceBarcode_print_btn").on("click",function(){
		var selectRows = $('#cargospaceBarcodeTable').bootstrapTable('getSelections');
		if(selectRows.length == 0){
			alert("请选择数据!");
			return;
		}
		var codes = [];
		for(var i = 0;i<selectRows.length;i++){
			codes.push(selectRows[i].locattionCode);
		}
		$.ajax({
			type : 'post',
			url : basePath + 'cargospaceBarcodeController/printCargospaceCode',
			data : {
				codes : codes
			},
			success : function(msg){
				msg = $.parseJSON(msg);
				var message = msg.message;
				if(msg.success){
					window.open(message);
				}else {
					alert(message);
				}
			}
		});
	});
	
	  
}); 
 
 
