package com.dscomm.iecs.basedata.service.impl;


import com.dscomm.iecs.base.utils.ObtainIPUtils;
import com.dscomm.iecs.basedata.service.NotifyNotesService;
import com.dscomm.iecs.basedata.tasks.NotifyTask;
import org.mx.StringUtils;
import org.mx.spring.task.Task;
import org.mx.spring.task.TaskFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述:通知其他节点更新缓存服务
 *
 * @author YangFuxi
 * Date Time 2020/3/12 16:23
 */
@Component("notifyNotesServiceImpl")
public class NotifyNotesServiceImpl implements NotifyNotesService {
    private static final Logger logger = LoggerFactory.getLogger(NotifyNotesServiceImpl.class);
    private TaskFactory taskFactory;
    @Value("${notifyOtherNodes:0}")
    private String notifyOtherNodes;
    @Value("${nodes:}")
    private String nodes;
    // 缓存前缀
    @Value("${cacheKeyPrefix:cache}")
    private String cacheKeyPrefix;

    @Autowired
    @Lazy(true)
    public NotifyNotesServiceImpl(@Qualifier("taskFactory") TaskFactory taskFactory) {
        this.taskFactory = taskFactory;
    }

    /**
     * {@inheritDoc}
     *
     * @see #notifyNodes(String, Boolean, Object)
     */
    @Override
    public void notifyNodes(String path, Boolean transmit, Object obj) {
        try {
            if (transmit && "1".equals(notifyOtherNodes) && !StringUtils.isBlank(nodes) && !StringUtils.isBlank(path)) {
                String[] allNodes = nodes.split(",");
                if (allNodes != null && allNodes.length > 1) {
                    List<String> localIpPorts = ObtainIPUtils.getLocalTomcatIpAndPortList();
                    String serverName = "";
                    if ("cad".equals(cacheKeyPrefix)) {
                        serverName = "ds-iecs-web";
                    } else if ("mdc".equals(cacheKeyPrefix)) {
                        serverName = "ds-iecs-mdc";
                    }
                    path = String.format("%s/%s", serverName, path);
                    Task task = new NotifyTask(allNodes, localIpPorts, path, obj);
                    taskFactory.addSingleAsyncTask(task);
                }
            }
        } catch (Exception ex) {
            logger.error(String.format("notify other nodes fail,path:%s", path), ex);
        }
    }




}
