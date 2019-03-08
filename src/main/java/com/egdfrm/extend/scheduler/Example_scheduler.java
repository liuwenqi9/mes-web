package com.egdfrm.extend.scheduler;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

import com.egdfrm.core.scheduler.AbstractBaseJob;

/**
 * <p>ClassName: Example-scheduler</p>
 * <p>Description: TODO</p>
 * <p>Author: Meng Min</p>
 * <p>Date: 2015-08-26</p>
 */
@DisallowConcurrentExecution
public class Example_scheduler extends AbstractBaseJob {

    
    
    /**
     * <p>
     * Field applicationContext: spring上下文
     * </p>
     */
    private ApplicationContext applicationContext;
    
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    
    
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    	//业务代码
//    	ActivityStatusUpdateService activityService = this.applicationContext.getBean(ActivityStatusUpdateService.class);
//        activityService.updateActivityStatusByTime();
        this.log.info("updateActivityStatusByTime executeInternal end");
    }


}
