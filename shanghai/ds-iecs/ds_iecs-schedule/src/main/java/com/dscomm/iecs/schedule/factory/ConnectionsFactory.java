package com.dscomm.iecs.schedule.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.mx.dal.config.PasswordReader;
import org.mx.dal.error.UserInterfaceDalErrorException;
import org.mx.dbcp.Dbcp2DataSourceConfigBean;
import org.mx.dbcp.Dbcp2DataSourceFactory;
import org.mx.error.UserInterfaceSystemErrorException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述： 使用DBCP构建的DI多连接池工厂
 *
 * @author john peng
 * Date time 2018/7/15 下午4:33
 */
public class ConnectionsFactory implements ApplicationContextAware {
    private static final Log logger = LogFactory.getLog(ConnectionsFactory.class);

    private Environment env;
    private ApplicationContext context ;
    private Map<String, Dbcp2DataSourceFactory> dataSources;

    @Override
    public void setApplicationContext( ApplicationContext applicationContext) throws BeansException {
        context = applicationContext ;
    }


    /**
     * 构造函数
     *
     * @param env Spring IoC上下文环境
     */
    public ConnectionsFactory(Environment env) {
        super();
        this.env = env;
        this.dataSources = new HashMap<>();
    }

    /**
     * 根据指定的Key，获取对应的数据库连接
     *
     * @param key Key
     * @return 数据库连接池
     */
    public Connection getConnection(String key) {
        if (!dataSources.containsKey(key)) {
            if (logger.isErrorEnabled()) {
                logger.error(String.format("The connection[%s] not found.", key));
            }
            throw new UserInterfaceSystemErrorException(
                    UserInterfaceSystemErrorException.SystemErrors.SYSTEM_ILLEGAL_PARAM
            );
        }
        try {
            return dataSources.get(key).getConnection();
        } catch (SQLException ex) {
            if (logger.isErrorEnabled()) {
                logger.error(String.format("Fetch the connection[%s] fail.",key),ex);
            }
            throw new UserInterfaceDalErrorException(
                    UserInterfaceDalErrorException.DalErrors.DB_OPERATE_FAIL
            );
        }
    }

    /**
     * 初始化连接池
     */
    public void init() {
        int num = env.getProperty("connections.num", Integer.class, 0);
        if (num > 0) {
            String passwordReaderName = env.getProperty("general.password.reader") ;
            PasswordReader passwordReader = null ;
            if(Strings.isNotBlank(passwordReaderName ) ){
                passwordReader = context.getBean( passwordReaderName , PasswordReader.class ) ;
            }

            for (int index = 1; index <= num; index++) {
                String prefix = String.format("connections.%d", index);
                String key = env.getProperty(String.format("%s.key", prefix));
                if (StringUtils.isBlank(key)) {
                    if (logger.isErrorEnabled()) {
                        logger.error("The connection's key is blank.");
                    }
                    throw new UserInterfaceSystemErrorException(
                            UserInterfaceSystemErrorException.SystemErrors.SYSTEM_ILLEGAL_PARAM
                    );
                }
                try {
                    Dbcp2DataSourceConfigBean dbcp2DataSourceConfigBean=new Dbcp2DataSourceConfigBean(env , prefix  ,  passwordReader );
                    Dbcp2DataSourceFactory factory = new Dbcp2DataSourceFactory(dbcp2DataSourceConfigBean);
                    factory.init();
                    dataSources.put(key, factory);
                } catch (SQLException ex) {
                    if (logger.isErrorEnabled()) {
                        logger.error(String.format("Initialize connection[%s] fail.", key), ex);
                    }
                    throw new UserInterfaceDalErrorException(
                            UserInterfaceDalErrorException.DalErrors.DB_OPERATE_FAIL
                    );
                }
            }
        } else {
            if (logger.isWarnEnabled()) {
                logger.warn("You may not configure the connections.");
            }
        }
    }

    /**
     * 销毁连接池
     */
    public void destroy() {
        if (dataSources != null && !dataSources.isEmpty()) {
            for (String key : dataSources.keySet()) {
                try {
                    dataSources.get(key).close();
                } catch (SQLException ex) {
                    if (logger.isErrorEnabled()) {
                        logger.error(String.format("Close the data source[%s] fail.", key));
                    }
                }
            }
        }
        if (logger.isInfoEnabled()) {
            logger.info("Close the connections factory successfully.");
        }
    }
}
