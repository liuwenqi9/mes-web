//挑库检查 JS
$(function () {
    //初始化数据列表
    creatDataTable();

    $("#picking_search_btn").on("click",function () {
        creatDataTable("refresh");
    });

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

    /*
     * Execl导出
     */
    $("#exportExcel").on("click",function () {
        //挑库单
        var pickingNum = $("#pickingNum").val().trim();
        //搬运单日期
        var startDate = $("#startDate").val().trim();
        //搬运单日期
        var endDate = $("#endDate").val().trim();
        window.open(basePath+"/picking/exportExcel?pickingNum="+pickingNum+"&startDate="+startDate+"&endDate="+endDate);
    });
});


/**
 * 创建dataTable
 */
function creatDataTable(refresh) {
    var url = basePath + 'picking/queryPages';
    var $table = $('#pickingDataTable');
    if(eGdUtil.isEmpty(refresh)){
        $table.bootstrapTable({
            queryParams : function(params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    pickingNum:$('#pickingNum').val(),
                    startDate:$('#startDate').val(),
                    endDate:$('#endDate').val()
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
            pageSize : 100,
            //每页显示数据条数
            pageList : [100,200,500,1000],
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
                }, {
                    title: '需求数量',
                    field: 'PRIMARY_QUANTITY',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: 'ERP挑库数',
                    field: 'QUANTITY_DELIVERED',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '条码挑库数',
                    field: 'MES_PICK_QUANTITY',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: 'ERP挑库时间',
                    field: 'CREATION_DATE',
                    align: 'center',
                    valign: 'middle',
                    formatter: function (value, row, index) {
                    	if (row.CREATION_DATE !='')
                    		return row.CREATION_DATE.substr(0,10);
                        return '';
                    } 
                }, {
                    title: '产品型号',
                    field: 'PROD_TYPE',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '销售订单',
                    field: 'SOURCE_HEADER_NUMBER',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '客户',
                    field: 'CUSTOMER_NAME',
                    align: 'left',
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
                    pickingNum:$('#pickingNum').val(),
                    startDate:$('#startDate').val(),
                    endDate:$('#endDate').val()
                };
            }
        });
    }
}