//挑库检查 JS
$(function () {
    //初始化数据列表
    creatDataTable();

    //时间控件初始化
    $('#startDate,#endDate').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format:"yyyy-mm-dd"
    });
    
    $("#search_btn").on("click",function () {
        creatDataTable("refresh");
    }); 
    
    $("#exportExcel").on("click",function () { 
    	var pickNum = $('#pickNum').val().trim();
        var productBarcode = $('#product_barcode').val().trim();
        var sourceHeaderNumber=$('#source_header_number').val().trim();
        var customerName=$('#customer_name').val().trim();
        var startDate=$('#startDate').val().trim();
        var endDate=$('#endDate').val().trim();
        var segment1 = $('#segment1').val().trim();
        window.open(basePath+"saleShipController/saleShipExportExcel" +
        		"?pickNum=" +pickNum+"&productBarcode="+productBarcode+"&sourceHeaderNumber="+sourceHeaderNumber
        		+"&customerName="+customerName+"&startDate="+startDate+"&endDate="+endDate+"&segment1="+segment1);
    }); 
    
    
});


/**
 * 创建dataTable
 */
function creatDataTable(refresh) {
    var url = basePath + 'saleShipController/getSaleShipLists';
    var $table = $('#saleShipTable');
    if(eGdUtil.isEmpty(refresh)){
        $table.bootstrapTable({
            queryParams : function(params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    pickNum:$('#pickNum').val().trim(),
                    productBarcode:$('#product_barcode').val().trim(),
                    sourceHeaderNumber:$('#source_header_number').val().trim(),
                    customerName:$('#customer_name').val().trim(),
                    startDate:$('#startDate').val().trim(),
                    endDate:$('#endDate').val().trim(),
                    segment1:$('#segment1').val().trim()
                };
            },
            //分页方式：client客户端分页，server服务端分页（*）
            sidePagination : "server",
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
            //是否只能选择单行
            singleSelect:false,
            //是否启用点击选中行
            clickToSelect: true,
            //中文支持
            dataLocale:"zh-US",
            //默认加载条数
            pageSize : 200,
            //每页显示数据条数
            pageList : [200,500,1000],
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache : false,
            // 是否显示分页（*）
            pagination : true,
            paginationPreText:'上一页',
            paginationNextText:'下一页',
            columns: [
                {
                    checkbox: true,
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '挑库单',
                    field: 'REQUEST_NUMBER',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '物料编码',
                    field: 'SEGMENT1',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '型号',
                    field: 'PROD_TYPE',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '需求</br>数量',
                    field: 'PRIMARY_QUANTITY',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '条码</br>发运数',
                    field: 'BARCODE_SHIP_QUANTITY',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: 'ERP</br>发运数',
                    field: 'ERP_SHIP_QUANTITY',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '产品条码',
                    field: 'BARCODE_TEXT',
                    align: 'center',
                    valign: 'middle'
                },
                {
                    title: '条码</br>状态',
                    field: 'DESCRIPTION',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '销售订单',
                    field: 'SOURCE_HEADER_NUMBER',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '挑库日期',
                    field: 'TRANSACTION_DATE',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                    	if (row.TRANSACTION_DATE !='')
                    		return row.TRANSACTION_DATE.substr(0,10);
                        return '';
                    }
                }, {
                    title: '客户',
                    field: 'CUSTOMER_NAME',
                    align: 'center',
                    valign: 'middle'
                },{
                    title: '包装条码',
                    field: 'BC',
                    align: 'center',
                    valign: 'middle'
                }
            ]
        });
    }else {
        $table.bootstrapTable('refreshOptions',{
            url : url,
            queryParams : function(params) {
                return {
                	offset: params.offset + 1,
                    limit: params.limit,
                    pickNum:$('#pickNum').val().trim(),
                    productBarcode:$('#product_barcode').val().trim(),
                    sourceHeaderNumber:$('#source_header_number').val().trim(),
                    customerName:$('#customer_name').val().trim(),
                    startDate:$('#startDate').val().trim(),
                    endDate:$('#endDate').val().trim(),
                    segment1:$('#segment1').val().trim()
                };
            }
        });
    }
}