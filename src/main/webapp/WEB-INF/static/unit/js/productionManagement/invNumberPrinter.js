$(function(){
	alert('234'); 
	$.ajax({
		//async:false,//同步 
		type : 'POST',
		url : basePath + "stockInController/getInvNumberByInvNumber?invNumber="+$('#invNumber').val(),
		timeout:5000, 
		success : function(data) {
			$('#table').bootstrapTable({ 
				data : strToJson(datas)
			});
				
		}
	});	
	
});
function printdiv()  
{   
var opo = 	window.open('','oPop');
var headstr = "<html><head>"; 
headstr +='<meta charset="UTF-8">';
headstr +='<title>打印页面</title></head><body>';
var footstr = "</body></html>";
document.getElementById("print_btn").setAttribute('type', 'hidden'); 
var printData = document.getElementById("print_div1").innerHTML; //获得 div 里的所有 html 数据 
document.getElementById("print_btn").setAttribute('type', 'button');
var str= headstr+printData+footstr;
opo.document.write(str); 
opo.print();
opo.close();  
} 