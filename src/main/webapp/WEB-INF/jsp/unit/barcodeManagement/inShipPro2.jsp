<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="/WEB-INF/jsp/egdfrm/base/head.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.css">
    <!-- jQuery 2.1.4 -->
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jquery-form/jquery-form.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript" charset="UTF-8" src="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/input-mask/jquery.inputmask.js"></script>
    <title>导入ship条码</title>
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
             folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/css/skins/_all-skins.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/iCheck/flat/blue.css">
    <!-- Morris chart -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/morris/morris.css">
    <!-- jvectormap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <!-- Date Picker -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/datepicker/datepicker3.css">
    <!-- Daterange picker -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/daterangepicker/daterangepicker-bs3.css">
    <!-- bootstrap wysihtml5 - text editor -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/egdfrm/dist/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <!--[if lt IE 9]>
    <script src="dist/js/html5shiv.min.js"></script>
    <script src="dist/js/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<section class="content">
    <div class="row">
        <div class="col-xs-12">
             <div class="box">
                  <ul>
                       <ol>
                           <li><span style="font-size:12px;font-weight:400;">销售退货: 机器上找不到条码</span></li>
                           <li><span style="font-size:12px;font-weight:400;">请生成条码，选择物料，导入系统</span></li>
                       </ol>
                   </ul>
               <span style="font-size:18px;font-weight: bold;">生成条码:</span>
                 <form class="form-inline" style="margin-top: 30px;">
                     <div class="form-group">
                         <label for="barcodeType">条码类型</label>
                         <input type="text" class="form-control" id="barcodeType" value="内销产品条码" disabled="disabled"/>
                     </div>
                     <div class="form-group">
                         <label for="productBarcode">当前条码</label>
                         <input type="text" class="form-control" id="productBarcode" placeholder="当前条码" readonly="readonly"/>
                     </div>
                     <div class="form-group">
                         <label for="printNum">打印张数</label>
                         <input type="text" class="form-control" id="printNum" value="1" readonly="readonly" placeholder="打印张数" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                     </div>
                     <button type="button" class="btn btn-info btn-sm" style="margin-left: 30px;" id='barcodeOK' data-backdrop="static"/>
                     <i class="glyphicon glyphicon-search"></i>&nbsp;确&nbsp;认&nbsp;
                     </button>
                 </form>
                 <div style="width: 100%;margin-top: 30px;padding-bottom: 30px;">
                    <%-- <span style="font-size:18px;font-weight: bold;">条码打印:</span>--%>
                     <div style="width: 100%;">
                         <%--<form class="form-inline" style="margin-top: 10px;">
                             <div class="form-group">
                                 <label for="startCode">产品条码</label>
                                 <input type="text" class="form-control" id="startCode" placeholder="产品条码" readonly="readonly"/>
                             </div>
                             <button type="button" class="btn btn-success btn-sm" style="margin-left: 50px;" id='printBarcode' data-backdrop="static" data-target="#packageBarcode_generate_modal"/>
                             &nbsp;&nbsp;打印(二维码)&nbsp;&nbsp;
                             </button>
                         </form>--%>
                         <table cellspacing="0" style="width: 100%;margin-top: 50px;" id="lineTable">
                             <thead>
                             <tr>
                                 <th style="text-align: center; width: 130px;height: 50px; font-size:14px;font-weight: bold;">产品条码</th>
                                 <th style="text-align: center; width: 130px;height: 50px; font-size:14px;font-weight: bold;">物料描述</th>
                                 <th style="text-align: center; width: 10px;height: 50px; font-size:14px;font-weight: bold;"></th>
                                 <th style="text-align: center; width: 130px;height: 50px; font-size:14px;font-weight: bold;">物料编码</th>
                                 <th style="text-align: center; width: 130px;height: 50px; font-size:14px;font-weight: bold;">产品型号</th>
                                 <%--<th style="text-align: center; width: 130px;height: 50px; font-size:14px;font-weight: bold;">操 &nbsp;&nbsp;作</th>--%>
                             </tr>
                             </thead>
                             <tbody>
                             <tr style="text-align: center; width: 150px;height: 30px;">
                                 <td >
                                     <input name="barcodeText" id="barcodeText" class="form-control" style="margin-left:80px;width: 150px;" readonly="readonly"/>
                                 </td>
                                 <td>
                                     <input name="description"  id='description'class="form-control" style="margin-left:50px;width: 300px;" readonly="readonly"/>
                                     <input type="hidden" name="inventoryItemId" id="inventoryItemId" >

                                 </td>
                                 <td>
                                     <button class="btn btn-success btn-sm" name="choose" onclick="clicks(this)"; data-backdrop="static" data-toggle="modal" readonly>
                                         <i class="glyphicon glyphicon-search"></i>选择
                                     </button>
                                 </td>
                                 <td>
                                     <input name="segment1"  id="segment1" class="form-control" style="margin-left:50px;width: 150px;"  readonly="readonly"/>
                                 </td>
                                 <td>
                                     <input name="prodType"  id="prodType" class="form-control" style="margin-left:18px;width: 150px;"  readonly="readonly"/>
                                 </td>
                                 <%--<td>
                                     <a href="javascript:void(0);" onclick="plus(this)"><i class="glyphicon glyphicon-plus"></i></a>
                                     &nbsp;
                                     <a href="javascript:void(0);" onclick="minus(this)"><i class="glyphicon glyphicon-minus"></i></a>
                                 </td>--%>
                             </tr>
                             </tbody>
                         </table>
                         <div style="width: 100%;margin-top: 50px;padding-bottom: 30px;">
                             <div  style="margin-left: 500px" >
                                 <button type="button" class="btn btn-success btn-sm" style="margin-left: 500px;" id='save' data-backdrop="static" data-target="#packageBarcode_generate_modal"/>
                                 &nbsp;&nbsp;保存&nbsp;&nbsp;
                                 </button>
                             </div>
                         </div>

                     </div>

                 </div>
        </div>
    </div>
</section>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    查询
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-inline" style="margin-bottom: 10px;">
                    <div class="form-group">
                        <input type="text" class="form-control" id="find_name" placeholder="输入型号，编码，描述" />
                    </div>
                    <button type="button" class="btn btn-info btn-sm" id='search' data-backdrop="static"/>
                        <i class="glyphicon glyphicon-search"></i>查询
                    </button>
                </div>
                <table id="modalTable"></table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="choose_ok">确认选择</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    $(function(){
        //获取当前条码
        get_current_barcode();

        /**
         * 确认选择
         */
        $("#choose_ok").on("click",function(){
            var selectRows = $('#modalTable').bootstrapTable('getSelections');
            if(selectRows.length == 0){
                alert("请选择数据!");
                return;
            }
            $("#segment1").val(selectRows[0].segment1);
            $("#description").val(selectRows[0].description);
            $("#prodType").val(selectRows[0].prodType);
            $("#inventoryItemId").val(selectRows[0].inventoryItemId);
            //关闭模态框
            $("#myModal").modal("hide");
        });
        /**
         * 搜索
         */
        $("#search").on("click",function(){
            getModalTable("refresh");
        });

        $('#save').on('click',function () {
            var barcodeText = $('#barcodeText').val();
            var inventoryItemId = $('#inventoryItemId').val();
            if(barcodeText==''){
                alert('条码不能为空');
                return ;
            }
            if(inventoryItemId==''){
                alert('请选择物料编码');
                return ;
            }
            $.ajax({
                type : 'post',
                url : basePath + "inShipProController/save2",
                data : {
                    barcodeText:barcodeText,
                    inventoryItemId : inventoryItemId
                },
                success : function (msg) {
                    var $msg = $.parseJSON(msg);
                    if($msg.success){
                        $.messager.alert("提示",$msg.message,"info",function () {
                           // $('#lineTable').empty();
                            $("#lineTable input").each(function () {
                                $(this).val("");
                            });

                        });
                    }else{
                        $.messager.alert("提示",$msg.message,"info",function () {
                        });
                    }
                }
            });
        });
        //确认
        $("#barcodeOK").on("click",function(){
            //当前条码
            var barcode = $("#productBarcode").val();
            //打印张数
            var printNum = $("#printNum").val();
            if(printNum != 1){
                alert("打印张数必须等于1");
                return;
            }
            $.ajax({
                type : 'post',
                url : basePath + 'inShipProController/getSEcode',
                data : {
                    printNum : printNum
                },
                success : function(msg){
                    var $msg = $.parseJSON(msg);
                    if($msg.success){
                        $("#lineTable input").each(function () {
                            $(this).val("");
                        });
                        var codes = $msg.message;
                        codes = codes.split(",");
                        //当前条码
                        $("#productBarcode").val(codes[0]);
                        //起始条码
                        $("#startCode").val(codes[0]);
                        $("#barcodeText").val(codes[0]);

                    }
                }
            });
        });
        /**
         * 打印二维码
         */
        $("#printBarcode").on("click",function(){
            //起始条码
            var startCode = $("#startCode").val();
            if(startCode == null || startCode == ''){
                alert("条码不能为空!");
                return;
            }

            $.ajax({
                type : 'post',
                url : basePath + 'inShipProController/toBarCodeByQRCode',
                data : {
                    startCode : startCode,
                    endCode : startCode
                },
                success : function(msg){
                    msg = $.parseJSON(msg);
                    var message = msg.message;
                    if(msg.success){
                        window.open(message);
                    }else {
                        alert(message);
                    }
                }
            });
        });
    });


    /**
     * 获取当前条码
     */
    function get_current_barcode(){
        $.ajax({
            type : 'post',
            url : basePath + 'inShipProController/getCurrentBarcode',
            success : function(msg){
                var $msg = $.parseJSON(msg);
                if(!$msg.success){
                    alert($msg.message);
                    return;
                }
                $("#productBarcode").val($msg.message);
                //起始条码
                $("#startCode").val("");
            }
        });
    }

    /**
     * 点击选择按钮
     * @param th
     */
    function clicks(th) {
        //打开模态框
        $("#myModal").modal();
        getModalTable();
    }
    //物料查询
    function getModalTable(refresh) {
        var url = basePath + 'inShipProController/getModalTableLists';
        var $table = $('#modalTable');
        if(typeof refresh == 'undefined'){
            $table.bootstrapTable({
                url : url,
                queryParams : function(params) {
                    return {
                        offset : params.offset + 1,
                        limit : params.limit,
                        findName : $("#find_name").val().trim()
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
                //height: 30,
                //是否只能选择单行
                singleSelect:true,
                //是否启用点击选中行
                clickToSelect: true,
                //中文支持
                dataLocale:"zh-US",
                //默认加载条数
                pageSize : 10,
                //每页显示数据条数
                pageList : [10,20,50,100],
                // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                cache : false,
                // 是否启用排序
                sortable : false,
                // 排序方式
                sortOrder : "desc",
                columns: [
                    {
                        checkbox: true,
                        align: 'center',
                        valign: 'middle'
                    }, {
                        title: '产品型号',
                        field: 'prodType',
                        align: 'center',
                        valign: 'middle'
                    }, {
                        title: '物料编码',
                        field: 'segment1',
                        align: 'center',
                        valign: 'middle'
                    }, {
                        title: '物料描述',
                        field: 'description',
                        align: 'center',
                        valign: 'middle'
                    }
                ]
            });
        }else {
            $table.bootstrapTable('refreshOptions',{
                queryParams : function(params) {
                    return {
                        offset : params.offset + 1,
                        limit : params.limit,
                        findName : $("#find_name").val().trim()
                    };
                }
            });
        }
    }
</script>
