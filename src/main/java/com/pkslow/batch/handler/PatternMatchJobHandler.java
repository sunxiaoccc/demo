package com.pkslow.batch.handler;

import com.pkslow.batch.config.JobUtil;
import com.pkslow.batch.config.SpringContextUtil;
import com.pkslow.batch.service.JobLauncherService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PatternMatchJobHandler extends IJobHandler {
    private static Logger logger = LoggerFactory.getLogger(PatternMatchJobHandler.class);

    @Autowired
    private JobLauncherService jobLauncherService;

    @XxlJob(value = "patternMatchJobHandler")
    @Override
    public ReturnT<String> execute(String s) throws Exception{
        return JobUtil.runJob4Executor("patternMatchJob",jobLauncherService,
            SpringContextUtil.getBean("patternMatchJob", Job.class));
    }


}
