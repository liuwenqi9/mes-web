//挑库拼箱检查 JS

$(function () {
    //初始化dataTable
    getDataTable();
    
    $("#picking_search_btn").on("click",function () {
        getDataTable("refresh");
    });
    
    /*
     * Execl导出
     */
    $("#exportExcel").on("click",function () {
    	
        var pickingNum =  $("#pickingNum").val().trim();
        var  checkType = $("#checkType").val(); 
        window.open(basePath+"picking/check/exportExcel?pickingNum="+pickingNum+"&checkType="+checkType );
    });
});


/**
 * 创建dataTable
 */
function getDataTable(refresh) {
    var url = basePath + 'picking/check/queryPages';
    var $table = $('#pickingCheckDataTable');
    if(eGdUtil.isEmpty(refresh)){
        $table.bootstrapTable({
            queryParams : function(params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    pickingNum : $("#pickingNum").val().trim(),
                    checkType : $("#checkType").val()

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
            //toolbar: '',
            //默认加载条数
            pageSize : 100,
            //每页显示数据条数
            pageList : [100,200,500,1000],
            // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            cache : false,
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
                    title: '数量',
                    field: 'ONHAND_QUANTITY',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '产品条码',
                    field: 'BARCODE_TEXT',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '产品型号',
                    field: 'PROD_TYPE',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '条码状态',
                    field: 'DESCRIPTION',
                    align: 'center',
                    valign: 'middle'
                }, {
                    title: '包装箱号',
                    field: 'PACK_BARCODE_TEXT',
                    align: 'center',
                    valign: 'middle'
                } , {
                    title: 'ERP挑库时间',
                    field: 'TRANSACTION_DATE',
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
                    pickingNum : $("#pickingNum").val().trim(),
                    checkType : $("#checkType").val()
                };
            }
        });
    }
}