/**
 * 单据补打JS
 */
$(function() { 
	
	//生成报检单
	$('#inspection_generate_btn').click(function(){
		/*//获取报检单号
		var inspectionNum=$('#inspectionNumId').val().trim();
		if(eGdUtil.isEmpty(inspectionNum)){
			alert('请输入报检单号');
			return;
		}
		//打开打印页面
		window.open(basePath+'documentSupplementController/toInspectionPrintPage?inspectionNumber='+inspectionNum);*/
		

		var inspectionNum = $("#inspectionNumId").val().trim();
		if(eGdUtil.isEmpty(inspectionNum)){
			alert('请输入报检单号');
			return;
		}
        $.ajax({
            type : 'get',
            url : basePath + 'documentSupplementController/reprintInspect',
            data : {
            	inspectionNumber : inspectionNum
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
				if($msg.success){
                    window.open($msg.message);
				}else {
					alert($msg.message);
				}
            }
        });
	
	
	});

	//入库单打印
	$("#print_stock").on("click",function (e) {
		//入库单号
		var stockNum = $("#stockNum").val();
        $.ajax({
            type : 'post',
            url : basePath + 'stockInController/printerStock',
            data : {
                stockNum : stockNum
            },
            success : function (msg) {
                var $msg = $.parseJSON(msg);
				if($msg.success){
                    window.open($msg.message);
				}else {
					alert($msg.message);
				}
            }
        });
    });

}); 
 
 
