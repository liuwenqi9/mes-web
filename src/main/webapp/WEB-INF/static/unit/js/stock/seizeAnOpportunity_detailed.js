//借机详情
$(function () {

    dataTableInit();

    /**
     * 查询
     */
    $("#detailed_search").on("click",function () {
        var url = basePath + 'seizeAnOpportunity/findDetailedPage';
        $('#seizeAnOpportunityTable').bootstrapTable('refreshOptions', {
            url: url,
            queryParams: function (params) {
                return {
                    offset: params.offset + 1,
                    limit: params.limit,
                    number: $("#jj_number").val().trim(),
                    dept: $("#jj_dept").val(),
                    person: $("#jj_person").val().trim(),
                    purpose: $("#jj_purpose").val().trim(),
                    describe: $("#describe").val().trim()
                };
            }
        });
    });
});

function dataTableInit() {
    var $seizeAnOpportunityTable = $('#seizeAnOpportunityTable');
    $seizeAnOpportunityTable.bootstrapTable({
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
        pageSize: 10,
        //每页显示数据条数
        pageList: [10, 20, 50, 100],
        // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        cache: false,
        columns: [
            {
                checkbox: true,
                align: 'center',
                valign: 'middle'
            }, {
                title: '编码',
                field: 'code',
                align: 'center',
                valign: 'middle'
            }, {
                title: '型号',
                field: 'model',
                align: 'center',
                valign: 'middle'
            }, {
                title: '申请数量',
                field: 'quantity',
                align: 'center',
                valign: 'middle'
            }, {
                title: '实际数量',
                field: 'lendQuantity',
                align: 'center',
                valign: 'middle'
            }, {
                title: '产品条码',
                field: 'productCode',
                align: 'center',
                valign: 'middle'
            }, {
                title: '来源子库',
                field: 'sourceSubLibrary',
                align: 'center',
                valign: 'middle'
            }, {
                title: '目的子库',
                field: 'goalSubLibrary',
                align: 'center',
                valign: 'middle'
            }, {
                title: '目的货位',
                field: 'goalLocation',
                align: 'center',
                valign: 'middle'
            }, {
                title: '计划归还日期',
                field: 'returnTime',
                align: 'center',
                valign: 'middle'
            }, {
                title: '实际归还日期',
                field: 'actualTime',
                align: 'center',
                valign: 'middle'
            }, {
                title: '物料描述',
                field: 'describe',
                align: 'left',
                valign: 'middle'
            }
        ]
    });
}