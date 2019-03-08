$(function() {
	 $("#file_upload").uploadify({
	       'auto' : false,
	       'method' : "get",
	       'formData' : {'folder' : 'file'},
	          'height' : 30,
	          'swf' : basePath+'static/egdfrm/common/uploadify/uploadify.swf', // flash
	          'uploader' : basePath+'uploadController/upload', // 数据处理url
	          'width' : 120,
	          'fileTypeDesc' : '只能是xls...',
	          'fileTypeExts' : '*.xls',
	          'fileSizeLimit' : '10500KB',
	          'buttonText' : '选择文件',
	          'uploadLimit' : 5,
	          'successTimeout' : 5,
	          'requeueErrors' : false,
	          'removeTimeout' : 10,
	          'removeCompleted' : false,
	          'queueSizeLimit' :10,
	          'queueID'  : 'uploader_queue',
	          'progressData' : 'speed',
	          'onInit' : function (){},
	       // 单个文件上传成功时的处理函数
	       'onUploadSuccess' : function(file, data, response){
	     $("#uploader_view").append('<div height="60" >'+data+'</div>');
	    },
	          'onQueueComplete' : function(queueData) {
	     $('#uploader_msg').html(queueData.uploadsSuccessful + ' 个文件上传成功。');
	    }      
	      });
	
});
