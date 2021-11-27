package com.dscomm.iecs.basedata.tasks;

import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mx.spring.task.BaseTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 描述： 数据缓存初始化任务，在服务器启动过程中乙部加载。
 *
 * @author john peng
 * Date time 2018/5/4 下午1:27
 */
@Component("initializeDataCacheTask")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)  //单例
public class InitializeDataCacheTask extends BaseTask {
    private static final Log logger = LogFactory.getLog(InitializeDataCacheTask.class);


    private ServletService servletService ;


    /**
     * 默认的构造函数
     *  @param dictionaryService 字典服务
     */
    @Autowired
    public InitializeDataCacheTask(DictionaryService dictionaryService , OrganizationService organizationService  ,
                                   ServletService servletService ) {
        super("Initialize the dictionary data into cache.", true);

        this.servletService = servletService ;
    }

    /**
     * {@inheritDoc}
     *
     * @see BaseTask#invoke()
     */
    @Override
    public void invoke() {

        servletService.updateSystemTime(); ;

    }
}
