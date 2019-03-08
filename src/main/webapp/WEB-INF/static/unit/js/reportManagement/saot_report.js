//借机统计报表 JS
$(function () {
    dataTableInit();

    $("#startTime,#endTime").datetimepicker({
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
    $("#planReturnStartTime,#planReturnEndTime").datetimepicker({
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

    /**
     * 查询
     */
    $("#search_btn").on("click",function () {
        var url =  basePath + "saot/findPage";
        $('#soatTable').bootstrapTable('refreshOptions', {
            url: url,
            queryParams: function (params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    startTime: $('#startTime').val(),
                    endTime: $('#endTime').val(),
                    state: $('#state').val(),
                    planReturnStartTime: $('#planReturnStartTime').val(),
                    planReturnEndTime: $('#planReturnEndTime').val(),
                    code: $('#code').val(),
                    jjNumber: $('#jjNumber').val()
                };
            }
        });
    });

    /**
     * 打印
     */
    $("#exportExcel").on("click",function () {
        var startTime = $('#startTime').val();
        var endTime = $('#endTime').val();
        var state = $('#state').val();
        var code = $('#code').val();
        var jjNumber = $('#jjNumber').val();
        window.open(basePath+"saot/exportExcel?startTime="+startTime+"&endTime="+endTime+"&state="+state+"&code="+code+"&jjNumber="+jjNumber);
    });
});


function dataTableInit() {
    var $soatTable = $('#soatTable');
    $soatTable.bootstrapTable({
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
        showRefresh: false,
        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        //height: 30,
        //是否只能选择单行
        singleSelect: false,
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
        columns: [
            {
                checkbox: true,
                align: 'center',
                valign: 'middle'
            }, {
                title: '借机单',
                field: 'jjNumber',
                align: 'center',
                valign: 'middle'
            }, {
                title: '借机部门',
                field: 'jjDept',
                align: 'center',
                valign: 'middle'
            }, {
                title: '借机用途',
                field: 'jjPurpose',
                align: 'center',
                valign: 'middle'
            }, {
                title: '借机人',
                field: 'jjPersion',
                align: 'center',
                valign: 'middle'
            }, {
                title: '编码',
                field: 'code',
                align: 'center',
                valign: 'middle'
            }, {
                title: '借机日期',
                field: 'jjDate',
                align: 'center',
                valign: 'middle'
            }, {
                title: '型号',
                field: 'model',
                align: 'center',
                valign: 'middle'
            },{
                title: '实际数量',
                field: 'actualQuantity',
                align: 'center',
                valign: 'middle'
            }, {
                title: '产品条码',
                field: 'barcode',
                align: 'center',
                valign: 'middle'
            }, {
                title: '计划归还日期',
                field: 'planReturnDate',
                align: 'center',
                valign: 'middle',
                formatter:function (value,row,index) {
                    if(value != null && value != ''){
                        return value.substring(0,10);
                    }
                }
            }, {
                title: '实际归还日期',
                field: 'planActualDate',
                align: 'center',
                valign: 'middle'
            }, {
                title: '参考',
                field: 'evaluation',
                align: 'left',
                valign: 'middle'
            }, {
                title: '物料描述',
                field: 'describe',
                align: 'left',
                valign: 'middle'
            }, {
                title: '备注',
                field: 'remark',
                align: 'left',
                valign: 'middle'
            }
        ]
    });
}