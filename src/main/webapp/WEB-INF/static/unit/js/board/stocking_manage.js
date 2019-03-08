//待备货待发货 JS
$(function () {
    //初始化DataTable
    dataTable();
    dataTableStorage();

    setInterval(function () { 
        var pageNexts = $(".page-next");
        for(var i = 0;i < pageNexts.length;i++){
            $(pageNexts[i]).click();  
        } 
    },20000);
});

//创建DataTable
function dataTable(refresh) {
    var url = basePath + 'board/stocking/findPage1';
    var $finishedTable = $('#to_stocking');
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
            pageSize : 10,
            //每页显示数据条数
            pageList : [10],
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache : false,
            columns: [
                {
                	title: '挑库日期',
                    field: 'TRANSACTION_DATE',
                    align: 'center',
                    valign: 'middle', 
                    formatter: function (value,row,index) {
                    	if(value==""||value==null){return "";}
                        return "<div style='font-size: 14px;font-weight: bold;'>"+value+"</div>";
                    }
                }, {
                    title: '客户',
                    field: 'ACCOUNT_NAME',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value,row,index) {
                    	if(value==""||value==null){return "";}
                        return "<div style='font-size: 14px;font-weight: bold;'>"+value+"</div>";
                    }
                }, {
                    title: '挑库单张数',
                    field: 'MO_COUNT',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value,row,index) {
                    	if(value==""||value==null){return "";}
                        return "<div style='font-size: 14px;font-weight: bold;'>"+value+"</div>";
                    }
                }, {
                    title: '型号项数',
                    field: 'ITEM_COUNT',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value,row,index) {
                    	if(value==""||value==null){return "";}
                        return "<div style='font-size: 14px;font-weight: bold;'>"+value+"</div>";
                    }
                }, {
                    title: '数量',
                    field: 'QUANTITY',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value,row,index) {
                    	if(value==""||value==null){return "";}
                        return "<div style='font-size: 14px;font-weight: bold;'>"+value+"</div>";
                    }
                }
            ]
        });
    }else {
        $finishedTable.bootstrapTable('refreshOptions',{
            queryParams : function(params) {
                return {
                    offset : params.offset + 1,
                    limit : params.limit
                };
            }
        });
    }
}

//待入库
function dataTableStorage(refresh) {
    var url = basePath + 'board/delivery/findPage1';
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
            pageSize : 10,
            //每页显示数据条数
            pageList : [10],
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache : false,
            columns: [
				{
					title: '备货日期',
				    field: 'TRANSACTION_DATE',
				    align: 'center',
				    valign: 'middle', 
				    formatter: function (value,row,index) { 
				    	if(value==""||value==null){return "";}
				        return "<div style='font-size: 14px;font-weight: bold;'>"+value+"</div>";
				    }
				}, {
				    title: '客户',
				    field: 'ACCOUNT_NAME',
				    align: 'center',
				    valign: 'middle',
				    formatter: function (value,row,index) {
				    	if(value==""||value==null){return "";}
				        return "<div style='font-size: 14px;font-weight: bold;'>"+value+"</div>";
				    }
				}, {
				    title: '挑库单张数',
				    field: 'MO_COUNT',
				    align: 'center',
				    valign: 'middle',
				    formatter: function (value,row,index) {
				    	if(value==""||value==null){return "";}
				        return "<div style='font-size: 14px;font-weight: bold;'>"+value+"</div>";
				    }
				}, {
				    title: '型号项数',
				    field: 'ITEM_COUNT',
				    align: 'center',
				    valign: 'middle',
				    formatter: function (value,row,index) {
				    	if(value==""||value==null){return "";}
				        return "<div style='font-size: 14px;font-weight: bold;'>"+value+"</div>";
				    }
				}, {
				    title: '数量',
				    field: 'QUANTITY',
				    align: 'center',
				    valign: 'middle',
				    formatter: function (value,row,index) {
				    	if(value==""||value==null){return "";}
				        return "<div style='font-size: 14px;font-weight: bold;'>"+value+"</div>";
				    }
				}
            ]
        });
    }else {
        $finishedTable.bootstrapTable('refreshOptions',{
            queryParams : function(params) {
                return {
                    offset : params.offset + 1,
                    limit : params.limit
                };
            }
        });
    }
}