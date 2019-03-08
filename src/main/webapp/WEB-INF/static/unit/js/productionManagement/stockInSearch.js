//报检单数据
$(function () {
    //初始化DataTable
    getStockSearch();
    //模糊查询
    $("#stock_search_btn").on("click", function (e) {
        getStockSearch('refresh');
    });
    
	//导出excel
	  $("#exportExcel").on("click",function () { 
		 var productionLine = $('#search_productionLine').val();
         var workOrderNumber = $('#workOrderNumber').val();
         var stockInNumber = $('#stockInNumber').val();
         var stockMark = $("#stockMark").val();
	     window.open(basePath+"stockInController/exportExcel?productionLine="+productionLine+"&workOrderNumber="+workOrderNumber
	    	+"&stockInNumber="+stockInNumber+"&stockMark="+stockMark	 
	     	);
	    }); 
    
});

function getStockSearch(refresh) {
    var url = basePath + 'stockInController/getStockInPrinters';
    var $table = $('#stockInTable');
    if (eGdUtil.isEmpty(refresh)) {
        $table.bootstrapTable({
            queryParams: function (params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    productionLine: $('#search_productionLine').val(),
                    workOrderNumber: $('#workOrderNumber').val(),
                    stockInNumber: $('#stockInNumber').val(),
                    stockMark: $("#stockMark").val(),
                    mark: 'search'
                };
            },
            //分页方式：client客户端分页，server服务端分页（*）
            sidePagination: "server",
            //是否分页
            pagination: true,
            //请求方法 post方式会自动把参数封装为json格式
            method: 'get',
            //服务器返回的数据类型
            //dataType : "json",
            //是否显示行间隔色
            striped: true,
            //是否显示所有的列
            showColumns: true,
            // 是否显示刷新按钮
            //showRefresh : true,
            //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            //height: 30,
            //是否只能选择单行
            //singleSelect:false,
            //是否启用点击选中行
            clickToSelect: true,
            //中文支持
            dataLocale: "zh-US",
            // 是否启用排序
            sortable: true,
            // 排序方式
            sortOrder: "desc",
            //默认加载条数
            pageSize: 100,
            //每页显示数据条数
            pageList: [100, 200, 500, 1000],
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache: false,
            paginationPreText: '上一页',
            paginationNextText: '下一页',
            columns: [
                {
                    checkbox: true,
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '生产线',
                    field: 'PLAN_LINE',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '入库单号',
                    field: 'INV_NUMBER',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '报检单号',
                    field: 'INSPECT_NUMBER',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '包装箱号',
                    field: 'BARCODE_TEXT',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '工单号',
                    field: 'WIP_ENTITY_NAME',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: 'MO单号',
                    field: 'MO_ORDER',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '产品编码',
                    field: 'SEGMENT1',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '产品型号',
                    field: 'SEGMENT2',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '入库子库',
                    field: 'SUBINVENTORY_CODE',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '入库货位',
                    field: 'LOCATTION_CODE',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '包装数量',
                    field: 'PACK_QUANTITY',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '入库标识',
                    field: 'STATUS_CODE',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                        return (value == 'WIP_COM' ? "未入库" : "已入库");
                    }
                }, {
                    title: '描述',
                    field: 'DESCRIPTION',
                    align: 'left',
                    valign: 'middle'
                }
            ]
        });
    } else {
        $table.bootstrapTable('refreshOptions', {
            url: url,
            queryParams: function (params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    productionLine: $('#search_productionLine').val(),
                    workOrderNumber: $('#workOrderNumber').val(),
                    stockInNumber: $('#stockInNumber').val(),
                    stockMark: $("#stockMark").val(),
                    mark: 'search'
                };
            }
        });
    }
}
