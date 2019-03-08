package com.egdfrm.unit.timer;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.TimerTask;

/**
 * Created by tyq on 17/1/7.
 */
public class PdfTimer extends TimerTask {


    private ServletContext context;

    public PdfTimer(ServletContext context){
        this.context = context;
    }


    @Override
    public void run() {
        String path = context.getRealPath("/");
        File file = new File(path + "/pdf");
        if(file.exists()){
            File[] list = file.listFiles();
            if(list.length > 0){
                for (File f : list) {
                    f.delete();
                }
            }
        }
    }
}
