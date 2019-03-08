package com.egdfrm.unit.common;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * User: 谭永强
 * Date: 2017/01/17
 */
public class FileUtils {

    /**
     * 文件下载
     * @param response
     * @param filePath 文件夹+文件路径
     * @param fileName 显示在下载页的文件名称
     */
    public static void download(HttpServletResponse response, String filePath, String fileName){
        response.setCharacterEncoding("UTF-8");
        //设置文件ContentType类型,这样设置会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        InputStream input = null;
        OutputStream out = null;
        try {
            //设置文件头：最后一个参数是设置下载文件名
            response.setHeader("Content-Disposition","attachment;fileName=\"" + new String(fileName.getBytes("utf-8"), "iso8859-1") +"\"");
            File file = new File(filePath);
            if(!file.exists()) return;
            input = new FileInputStream(file);
            //通过response获取ServletOutputStream对象
            out = response.getOutputStream();
            int len = 0;
            byte[] b = new byte[1024*10];
            while ((len=input.read(b)) != -1){
                out.write(b,0,len);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(input != null) input.close();
                if(out != null) out.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件上传
     * @param input 流
     * @param path 项目路径下面的upload下面的公司ID下面+文件名
     * @param fileName 文件名（32位编码名）
     * @throws Exception
     */
    public static void handleFile(InputStream input, String path,String fileName) throws Exception {
        if (input == null) return;
        File file = new File(path);
        if (!file.exists()) file.mkdirs();
        OutputStream out = new FileOutputStream(new File(path,fileName));
        BufferedInputStream inputStream = new BufferedInputStream(input);
        BufferedOutputStream outputStream = new BufferedOutputStream(out);
        byte[] b = new byte[1024 * 1024 * 2];
        int len = -1;
        while ((len = inputStream.read(b)) != -1) {
            outputStream.write(b, 0, len);
        }
        inputStream.close();
        outputStream.close();
        input.close();
        out.close();
    }
}
