//特殊业务处理 JS
$(function () {

    //产品条码重置
    $("#product_reset").on("click",function () {
        //工单号
        var product_word_order = $("#product_word_order").val();
        $.ajax({
            type : 'post',
            url : basePath + 'special/business/productNumReset',
            data : {
                productWordOrder : product_word_order
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                alert($msg.message);
            }
        });
    });
    
    //包装条码重置
    $("#package_reset").on("click",function () {
        //工单号
        var package_word_order = $("#package_word_order").val();
        //包装类型
        var packageType = $("#package_type").val();
        $.ajax({
            type : 'post',
            url : basePath + 'special/business/packageNumReset',
            data : {
                packageWordOrder : package_word_order,
                packageType : packageType
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                alert($msg.message);
            }
        });
    });

    //包装尾数重置
    $("#mantissa_reset").on("click",function () {
        //包装箱
        var packing_box = $("#packing_box").val();
        //修正数量
        var updateNumber = $("#updateNumber").val();
        $.ajax({
            type : 'post',
            url : basePath + 'special/business/packageMantissaReset',
            data : {
                packingBox : packing_box,
                updateNumber : updateNumber
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                alert($msg.message);
            }
        });
    });

    //额外新增工单包装箱
    $("#additional_ok").on("click",function () {
        //工单号
        var additional_work_order = $("#additional_work_order").val();
        //包装类型
        var additional_types = $("#additional_types").val();
        $.ajax({
            type : 'post',
            url : basePath + 'special/business/additional',
            data : {
                additionalWorkOrder : additional_work_order,
                additionalTypes : additional_types
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                alert($msg.message);
            }
        });
    });

    //工单转产
    $("#the_single_turn_ok").on("click",function () {
        //包装箱
        var the_single_turn = $("#the_single_turn").val();
        $.ajax({
            type : 'post',
            url : basePath + 'special/business/theSingleTurn',
            data : {
                theSingleTurn : the_single_turn
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                alert($msg.message);
            }
        });
    });
    
    getPlanLines();
    //生产线重置
    $('#lind_code_btn').on('click',function(){
    	var pack_barcode =$('#pack_barcode').val();
    	var line_code = $('#productionLine_id').val(); 
    	if(pack_barcode==''){
    		 alert('包装箱不能为空！');
    		 return;
    	}
    	if(line_code=='0'){
   		 alert('请选择生产线！');
   		 return;
 	 	}
   	
    	$.ajax({
            type : 'post',
            url : basePath + 'special/business/planLineReset',
            data : {
                packBarcode : pack_barcode,
                lineCode: line_code
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                alert($msg.message);
            }
        });
    	
    });

    //工单数量修改
    $('#cm_btn').on('click',function(){
        var wipName =$('#wipName').val();
        var number = $('#number').val();
        if(wipName==''){
            alert('工单号不能为空！');
            return;
        }
        if(number==''){
            alert('数量不能为空');
            return;
        }

        $.ajax({
            type : 'post',
            url : basePath + 'special/business/wipNumReset',
            data : {
                wipName : wipName,
                number: number
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
                alert($msg.message);
            }
        });

    });

});


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
    };
    eGdUtil.requestJsonAjax(url, null, suc);
};