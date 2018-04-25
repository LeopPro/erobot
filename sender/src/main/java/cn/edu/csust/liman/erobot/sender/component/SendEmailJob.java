package cn.edu.csust.liman.erobot.sender.component;

import cn.edu.csust.liman.erobot.sender.entity.Email;
import cn.edu.csust.liman.erobot.sender.util.BeanUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SendEmailJob implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailJob.class);
    private static final BeanUtil.LazyLoader<JobErrorHandler> JOB_ERROR_HANDLER = BeanUtil.getLazyLoader(JobErrorHandler.class);


    @Override
    public void execute(JobExecutionContext context) {
        try {
            Email email = (Email) context.getScheduler().getContext().get("email");
            email.send();
        } catch (Exception e) {
            LOGGER.error("邮件任务执行失败", e);
            JOB_ERROR_HANDLER.get().handle(e);
        }
    }
}
