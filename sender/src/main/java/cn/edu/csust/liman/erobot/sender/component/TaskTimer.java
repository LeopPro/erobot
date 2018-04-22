package cn.edu.csust.liman.erobot.sender.component;

import cn.edu.csust.liman.erobot.sender.entity.Task;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TaskTimer {
    private final static Map<Long, Scheduler> SCHEDULER_MAP = new ConcurrentHashMap<>();

    public void start(Task task) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(String.valueOf(task.getId()))
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(task.getCron()))
                .build();

        JobDetail job = JobBuilder.newJob(SendEmailJob.class)
                .withIdentity(String.valueOf(task.getId()))
                .build();

        scheduler.scheduleJob(job, trigger);
        scheduler.getContext().put("email", task.getEmail());
        scheduler.start();
        SCHEDULER_MAP.put(task.getId(), scheduler);
    }

    public void shutdown(Task task) throws SchedulerException {
        Scheduler scheduler = SCHEDULER_MAP.get(task.getId());
        if (scheduler == null) {
            return;
        }
        scheduler.shutdown(true);

    }


}
