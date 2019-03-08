//待检验报表 JS
$(function () {
    dataTable();

    /**
     * 打印
     */
    $("#printer_btn").on("click",function () {
        window.open(basePath+"to/be/test/report/exportExcel");
    });
});

function dataTable() {
    var url = basePath + 'to/be/test/report//findPage2';
    var $tobeTestTable = $('#tobeTestTable');
    //创建DataTable
    $tobeTestTable.bootstrapTable({
        url: url,
        queryParams: function (params) {
            return {
                offset: params.offset + 1,
                limit: params.limit
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
        showRefresh: true,
        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        //height: 500,
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
        //头信息
        //toolbar: '#productBarcode_toolbar',
        //默认加载条数
        pageSize: 100,
        //每页显示数据条数
        pageList: [100, 200, 500],
        // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        cache: false,
        columns: [
            {
                checkbox: true,
                align: 'center',
                valign: 'middle'
            },
            {
                title: '产线',
                field: 'plan_line',
                align: 'center',
                valign: 'middle'
            }, {
                title: '报检单号',
                field: 'inspect_number',
                align: 'center',
                valign: 'middle'
            }, {
                title: '工单号',
                field: 'wip_entity_name',
                align: 'center',
                valign: 'middle'
            }, {
                title: '编码',
                field: 'segment1',
                align: 'center',
                valign: 'middle'
            }, {
                title: '报检时间',
                field: 'inspect_date',
                align: 'center',
                valign: 'middle'
            }, {
                title: '型号',
                field: 'segment2',
                align: 'center',
                valign: 'middle'
            }, {
                title: '箱数',
                field: 'pack_count',
                align: 'center',
                valign: 'middle'
            }, {
                title: '数量',
                field: 'pack_quantity',
                align: 'center',
                valign: 'middle'
            }
        ]
    });
}