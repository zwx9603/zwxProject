package com.dscomm.iecs.basedata.tasks;

import org.mx.StringUtils;
import org.mx.service.client.rest.RestClientInvoke;
import org.mx.spring.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 描述:通知任务
 *
 * @author YangFuxi
 * Date Time 2020/3/16 15:04
 */
public class NotifyTask extends BaseTask {
    private static final Logger logger = LoggerFactory.getLogger(NotifyTask.class);
    private String[] nodes;
    private List<String> localIpPorts;
    private String path;
    private Object obj;

    public NotifyTask(String[] nodes, List<String> localIpPorts, String path, Object obj) {
        this.nodes = nodes;
        this.localIpPorts = localIpPorts;
        this.path = path;
        this.obj = obj;
    }

    @Override
    public void invoke() {
        if (nodes != null && nodes.length > 0 && !StringUtils.isBlank(path)) {
            RestClientInvoke invoke = new RestClientInvoke();
            for (String node : nodes) {
                if (!StringUtils.isBlank(node)) {
                    String server = node.replace(" ", "");
                    if (localIpPorts != null && !localIpPorts.contains(server)) {
                        try {
                            String restfulUrl = String.format("http://%s/%s", server, path) ;
                            String result = invoke.post(restfulUrl, obj, String.class);
//                            logger.info(String.format("success to notify other node:http://%s/%s,result:%s", server, path,result));
                            logger.info(String.format("success to notify other node"));
                        } catch (Exception ex) {
//                            logger.error(String.format("fail to notify other node:http://%s/%s", server, path), ex);
                            logger.error(String.format("fail to notify other node"), ex);
                            logger.error(String.format("fail to notify other node restfull : http://%s/%s "  , server, path  ) );
                        }

                    }
                }
            }
            invoke.close();
        }
    }
}
