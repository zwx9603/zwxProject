package com.dscomm.iecs.base.task.factory;

import org.mx.StringUtils;
import org.mx.error.UserInterfaceSystemErrorException;
import org.mx.spring.task.BaseTask;
import org.mx.spring.task.Task;
import org.mx.spring.task.TaskFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 描述： base 调度工厂
 *
 */
public class IecsScheduleTaskFactory {
    private static final Logger logger = LoggerFactory.getLogger(IecsScheduleTaskFactory.class);
    private ApplicationContext context;
    private Environment env;

    private Map<String, TimeUnit> timeUnits = new HashMap<>();
    private TaskFactory taskFactory;

    /**
     * 构造函数
     *
     * @param context Spring IoC上下文
     * @param env     Spring IoC上下文环境
     */
    public IecsScheduleTaskFactory(ApplicationContext context, Environment env ) {
        super();
        this.context = context;
        this.env = env;
        timeUnits.put("second", TimeUnit.SECONDS);
        timeUnits.put("minute", TimeUnit.MINUTES);
        timeUnits.put("hour", TimeUnit.HOURS);
        timeUnits.put("day", TimeUnit.DAYS);
    }

    /**
     * 获取任务工厂
     *
     * @return 任务工厂
     */
    public TaskFactory getTaskFactory() {
        return taskFactory;
    }

    /**
     * 初始化任务工厂
     */
    public void init() {
        int num = env.getProperty("tasks.num", Integer.class, 0);
        if (num > 0) {
            taskFactory = new TaskFactory();
            for (int index = 1; index <= num; index++) {
                String name = env.getProperty(String.format("tasks.%d.name", index));
                int delay = env.getProperty(String.format("tasks.%d.delay", index), Integer.class, 0);
                int period = env.getProperty(String.format("tasks.%d.period", index), Integer.class, -1);
                String unit = env.getProperty(String.format("tasks.%d.timeUnit", index), "sec");
                if (StringUtils.isBlank(name) || delay < 0 || period < 0 || !timeUnits.containsKey(unit)) {
                    if (logger.isErrorEnabled()) {
                        logger.error(String.format("The tasks's configuration invalid, tasks name: %s, delay: %d, " +
                                "period: %d, time unit: %s.", name, delay, period, unit));
                    }
                    throw new UserInterfaceSystemErrorException(
                            UserInterfaceSystemErrorException.SystemErrors.SYSTEM_ILLEGAL_PARAM
                    );
                }

                BaseTask task = context.getBean(name, BaseTask.class);
                Task.ScheduledConfig scheduledConfig = new Task.ScheduledConfig(false, delay, period,
                        timeUnits.get(unit));
                taskFactory.addScheduledTask(task, scheduledConfig);
            }
            if (logger.isInfoEnabled()) {
                logger.info(String.format("Initialize %d scheduled tasks successfully.",
                        taskFactory.getScheduledTasks().size()));
            }
        } else {
            if (logger.isWarnEnabled()) {
                logger.warn("You may not configure any scheduled tasks.");
            }
        }
    }

    /**
     * 销毁任务工厂
     */
    public void destroy() {
        if (taskFactory != null) {
            taskFactory.shutdown();
            taskFactory = null;
        }
        if (logger.isInfoEnabled()) {
            logger.info("Shutdown the tasks factory successfully.");
        }
    }
}