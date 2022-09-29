package com.pkslow.batch.config;

import com.pkslow.batch.service.JobLauncherService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import java.util.Map;

/**
 * @author admin
 * @date 2022/9/27
 */
public class JobUtil {
    /**
     * 构建JobParameters
     * @return
     */
    private static JobParameters makeJobParameters(){
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time",System.currentTimeMillis())
                .toJobParameters();
        return jobParameters;
    }

    public static ReturnT<String> runJob4Executor(String jobName, JobLauncherService jobLauncherService, Job job) throws Exception{
        XxlJobLogger.log("start sync "+jobName +" data");
        Map<String,Object> resultMap = jobLauncherService.startJob(job,makeJobParameters());
        String resultStr = (String)resultMap.get(SyncConstants.STR_RETURN_RESULT);
        XxlJobLogger.log("end sync "+jobName+" data, result："+System.lineSeparator()+resultStr);

        ExitStatus exitStatus = (ExitStatus) resultMap.get(SyncConstants.STR_RETURN_EXITSTATUS);
        String exitCode = exitStatus.getExitCode();
        //若成功,返回SUCCESS
        if(ExitStatus.COMPLETED.getExitCode().equals(exitCode)){
            return IJobHandler.SUCCESS;
        }else{
            return IJobHandler.FAIL;
        }

    }
}
