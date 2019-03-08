/*****************************************************************************
 *
 * 生产统计日报表JS
 *
 *****************************************************************************/
//全局变量 查询时的生产线
var searchProductionLine;
//全局变量 查询时的生产日期从
var searchDateFrom;
//全局变量 查询时的生产日期至
var searchDateTo;
//全局变量 查询结果数量
var resultLength = 0;

//初始化获取生产线列表
function getPlanLines() {
    var url = basePath + 'productionStatisticsDaliyReportController/getPlanLines';
    var suc = function (data) {
        var datas = strToJson(data);
        $('#productionLine_id').empty();
        $('#productionLine_id').append("<option value='0'>--请选择生产线--</option>");
        for (var i = 0; i < datas.length; i++) {
            if (datas[i].FLEX_VALUE) {
                $('#productionLine_id').append("<option value='" + datas[i].FLEX_VALUE + "'>" + datas[i].PLANLINEDESC + "</option>");
            }
        }
    }
    eGdUtil.requestJsonAjax(url, null, suc);
};
//初始化控件
function init() {
    //初始化查询日期
//	var startDate=eGdUtil.changeLongToDate(new Date()-90*24*60*60*1000); 90天前
    $('#date_from_id').val(new Date().Format('yyyy-MM-dd'));
    $('#date_to_id').val(new Date().Format('yyyy-MM-dd'));
    //日期控件初始化
    $('#date_from_id,#date_to_id').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        //语言选择中文
        format: "yyyy-mm-dd"
    });
}

$(function () {
    //获取生产线
    getPlanLines();
    // 初始化控件
    init();
    //初始化查询结果区域
    $('#productionStatisticsTable').bootstrapTable();
    // 查询按钮事件
    $('#productionStatistics_search_btn').click(function () {
        //生产线
        searchProductionLine = $('#productionLine_id').val();
        /*if(searchProductionLine == 0){
            $.messager.alert('提示', '请选择生产线');
            return;
        }*/
        //开始结束时间
        var dateFrom = new Date($('#date_from_id').val());
        var dateTo = new Date($('#date_to_id').val());
        if (dateFrom == 'Invalid Date' || dateTo == 'Invalid Date') {
            $.messager.alert('错误', '时间格式错误', 'error');
            return;
        }
        if (eGdUtil.compareTwoDate(dateFrom, dateTo) < 0) {
            $.messager.alert('错误', '开始时间不能大于结束时间', 'error');
            return;
        }
        searchDateFrom = dateFrom;
        searchDateTo = dateTo;
        var params = {
            productionLine: searchProductionLine,
            dateFrom: searchDateFrom,
            dateTo: searchDateTo
        };
        var url = basePath + 'productionStatisticsDaliyReportController/searchProductionStatistics';
        var success = function (datass) {
            resultLength = datass.length;
            //刷新结果
            $('#productionStatisticsTable').bootstrapTable('load', {
                data: strToJson(datass)
            });
        };
        eGdUtil.requestJsonAjax(url, params, success);
    });
    // 导出按钮事件
    /*$('#productionStatistics_export_btn').click(function () {
     /!**
     * 如果有查询结果，则可以导出excel
     *!/
     if (resultLength && resultLength >= 1) {
     //禁用按钮，避免重复点击
     this.disabled = true;
     //进度条
     $.messager.progress({title: '正在导出', msg: '生产统计日报表导出中... ', text: '正在导出，请稍等！ ', interval: 300});
     //导出-上传至服务器
     var params = {
     productionLine: searchProductionLine,
     dateFrom: searchDateFrom,
     dateTo: searchDateTo
     };
     var url = basePath + 'productionStatisticsDaliyReportController/exportProductionStatistics';
     var successFn = function (datass) {
     if (datass.flag == 'S') {
     /!**
     * 服务器生成EXCEL成功，下载到本地
     *!/
     window.location.href = basePath +
     'productionStatisticsDaliyReportController/downloadProductionStatistics?fileName=' + datass.fileName;
     showInfoMsg('导出成功！');
     } else {
     showErrorMsg('导出失败');
     }
     };
     var failureFn = function () {
     showErrorMsg('导出失败');
     }
     eGdUtil.requestJsonAjax(url, params, successFn, failureFn);
     //导出失败，重新启用按钮
     this.disabled = false;
     //隐藏进度条
     $.messager.progress('close');
     } else {
     showErrorMsg('查询结果列表中没有数据，请查询后再进行导出！！');
     }
     });*/

    /**
     * 导出EXCEL
     */
    $("#productionStatistics_export_btn").on("click", function () {
        var rows = $("#productionStatisticsTable").bootstrapTable("getData");
        if (rows.length == 0) {
            $.messager.alert('提示', '请先进行查询');
            return;
        }
        //生产线
        var proLine = $('#productionLine_id').val();
        //开始时间
        var startTime = $('#date_from_id').val().trim();
        //结束时间
        var endTime = $('#date_to_id').val().trim();
        /*if(proLine == 0){
            $.messager.alert('提示', '请选择生产线');
            return;
        }*/
        if(startTime == null || startTime == ''){
            $.messager.alert('提示', '请选择日期');
            return;
        }
        if(endTime == null || endTime == ''){
            $.messager.alert('提示', '请选择日期');
            return;
        }
        var url = basePath + "productionStatisticsDaliyReportController/exportExcel?proLine="+proLine+"&startTime="+startTime+"&endTime="+endTime;
        window.open(url);
    });

});
