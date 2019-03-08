package com.egdfrm.core.controller.upload;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.egdfrm.core.controller.base.BaseController;

@Controller
@RequestMapping("uploadController")
public class UploadController extends BaseController {
	@RequestMapping("init")
	public String init() {
		return "egdfrm/upload/upload";
	}

	@RequestMapping("upload")
	public void upload(HttpServletRequest request, HttpServletResponse response) {
		try {
			String savePath = "C://uploads/";
			File dirPath = new File(savePath);  
			if (!dirPath.exists()) {  
				dirPath.mkdirs();  
	        } 
			// 解析器解析request的上下文
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			// 先判断request中是否包涵multipart类型的数据，
			if (multipartResolver.isMultipart(request)) {
				// 再将request中的数据转化成multipart类型的数据
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				@SuppressWarnings("rawtypes")
				Iterator iter = multiRequest.getFileNames();

				while (iter.hasNext()) {
					MultipartFile file = multiRequest.getFile((String) iter.next());
					if (file != null) {
						String fileName = file.getOriginalFilename();
						System.out.println(fileName);
						File localFile = new File(savePath + fileName);
						// 写文件到本地
						file.transferTo(localFile);
						response.getWriter().print(fileName+"        上传成功！");
					}
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
