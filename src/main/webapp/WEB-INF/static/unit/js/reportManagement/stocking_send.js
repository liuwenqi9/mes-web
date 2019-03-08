//待发货 JS
$(function () {
    //初始化DataTable
	dataTable();
	$('#search_btn').on('click',function(){
		dataTable('refresh');
	}); 
	
		//导出excel
	  $("#exportExcel").on("click",function () {  
	        window.open( basePath + 'stockingSendController/exportStockingSendList');
	    }); 
    //dataTableStorage(); 
});

//创建DataTable fa货
function dataTable(refresh) {
    var url = basePath + 'stockingSendController/getStockingSendList';
    var $finishedTable = $('#stocking_send');
    if(typeof refresh == 'undefined'){
    	 
        $finishedTable.bootstrapTable({ 
        	//url : url,
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
            showColumns : false,
            // 是否显示刷新按钮
            showRefresh : true,
            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            //height: 500,
            //是否只能选择单行
            singleSelect:false,
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
            pageSize : 50,
            //每页显示数据条数
            pageList : [50,100,200,500],
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache : false,
            columns: [
			   {
			    title: '客户',
			    field: 'ACCOUNT_NAME',
			    align: 'center',
			    valign: 'middle', 
				},{
                    title: '销售订单',
                    field: 'SOURCE_HEADER_NUMBER',
                    align: 'center',
                    valign: 'middle',
                     
                }, {
                    title: '挑库单',
                    field: 'REQUEST_NUMBER',
                    align: 'center',
                    valign: 'middle', 
                }, {
                    title: '物料',
                    field: 'SEGMENT1',
                    align: 'center',
                    valign: 'middle',  
                }, {
                    title: '型号',
                    field: 'PROD_TYPE',
                    align: 'center',
                    valign: 'middle',
                     
                }, {
                    title: '数量',
                    field: 'QUANTITY',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: '挑库时间',
                    field: 'TRANSACTION_DATE',
                    align: 'center',
                    valign: 'middle', 
                },{
                    title: '物料描述',
                    field: 'DESCRIPTION',
                    align: 'left',
                    valign: 'middle', 
                }
            ]
        });
    }else {
        $finishedTable.bootstrapTable('refreshOptions',{
        	url:url,
             queryParams : function(params) {
                 return {
                     offset : params.offset + 1,
                     limit : params.limit
                 };
             },
        });
    }
}

//in
function dataTableStorage(refresh) {
    var url = basePath + 'board/delivery/findPage';
    var $finishedTable = $('#to_delivery');
    if(typeof refresh == 'undefined'){
        $finishedTable.bootstrapTable({
            url : url,
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
            showColumns : false,
            // 是否显示刷新按钮
            showRefresh : false,
            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            //height: 500,
            //是否只能选择单行
            singleSelect:false,
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
            pageSize : 12,
            //每页显示数据条数
            pageList : [12],
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache : false,
            columns: [
                {
                    title: '销售订单',
                    field: 'SOURCE_HEADER_NUMBER',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value,row,index) {
                        return "<div style='font-size: 14px;font-weight: bold;'>"+value+"</div>";
                    }
                }, {
                    title: '挑库单',
                    field: 'REQUEST_NUMBER',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value,row,index) {
                        return "<div style='font-size: 14px;font-weight: bold;'>"+value+"</div>";
                    }
                }, {
                    title: '物料',
                    field: 'SEGMENT1',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value,row,index) {
                        return "<div style='font-size: 14px;font-weight: bold;'>"+value+"</div>";
                    }
                }, {
                    title: '型号',
                    field: 'PROD_TYPE',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value,row,index) {
                        return "<div style='font-size: 14px;font-weight: bold;'>"+value+"</div>";
                    }
                }, {
                    title: '数量',
                    field: 'QUANTITY',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value,row,index) {
                        return "<div style='font-size: 14px;font-weight: bold;'>"+value+"</div>";
                    }
                }
            ]
        });
    }else {
        $finishedTable.bootstrapTable('refreshOptions',{
        	url : url,
            queryParams : function(params) {
                return {
                    offset : params.offset + 1,
                    limit : params.limit
                };
            }
        });
    }
}