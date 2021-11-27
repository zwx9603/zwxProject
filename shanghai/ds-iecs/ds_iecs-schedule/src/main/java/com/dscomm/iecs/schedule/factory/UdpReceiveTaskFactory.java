package com.dscomm.iecs.schedule.factory;

import org.mx.StringUtils;
import org.mx.error.UserInterfaceSystemErrorException;
import org.mx.spring.task.BaseTask;
import org.mx.spring.task.TaskFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 描述： udp 接收消息 工厂
 *
 */
public class UdpReceiveTaskFactory {
    private static final Logger logger = LoggerFactory.getLogger(UdpReceiveTaskFactory.class);
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
    public UdpReceiveTaskFactory(ApplicationContext context, Environment env ) {
        super();
        this.context = context;
        this.env = env;
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
        int num = env.getProperty("udps.num", Integer.class, 0);
        if (num > 0) {
            taskFactory = new TaskFactory();
            for (int index = 1; index <= num; index++) {
                String name = env.getProperty(String.format("udps.%d.name", index));
                if (StringUtils.isBlank(name) ) {
                    if (logger.isErrorEnabled()) {
                        logger.error(String.format("The udps's configuration invalid, tasks name: %s", name ) );
                    }
                    throw new UserInterfaceSystemErrorException(
                            UserInterfaceSystemErrorException.SystemErrors.SYSTEM_ILLEGAL_PARAM
                    );
                }
                // udp 推送消息接收 任务添加
                BaseTask task=context.getBean(name , BaseTask.class);
                taskFactory.addSingleAsyncTask( task );
            }
            taskFactory.runSerialTasks();

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