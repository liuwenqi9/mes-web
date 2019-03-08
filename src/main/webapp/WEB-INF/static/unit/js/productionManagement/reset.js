 
$(function() { 
	
	 
	$('#inspection_reset_btn').click(function(){
		//获取报检单号
		var inspectionNum=$('#inspectionNumId').val().trim();
		if(eGdUtil.isEmpty(inspectionNum)){
			alert('请输入报检单号！');
			return;
		}
		if(confirm('确认是否重置报检单号为“'+inspectionNum+'”的数据吗？')){ 
			$.ajax({
	             type : 'post',
	             url : basePath+'reset/inspect',
	             data : {
	            	 inspectNum:inspectionNum 
	             },
	             success : function (msg) {
	                 var $msg = $.parseJSON(msg);
	                 if($msg.success){
		                	showInfoMsg($msg.message);
		                }else{
		                	showErrorMsg($msg.message);
		                }
	             }
	         });
		} 
	});

	//入库单重置
	$("#stock_reset_btn").on("click",function (e) {
		//入库单重置
		var stockNum = $("#stockNum").val().trim();
		if(eGdUtil.isEmpty(stockNum)){
			alert('请输入入库单号！');
			return;
		}
		if(confirm('确认是否重置入库单号为“'+stockNum+'”的数据吗？')){
	        $.ajax({
	            type : 'post',
	            url : basePath + 'reset/stock',
	            data : {
	                stockNum : stockNum
	            },
	            success : function (msg) { 
	                var $msg = $.parseJSON(msg); 
	                if($msg.success){
	                	showInfoMsg($msg.message);
	                }else{
	                	showErrorMsg($msg.message);
	                }
	                
	                
	            }
	        });
		}
    });

}); 
 
 
