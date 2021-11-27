package com.dscomm.iecs.base.config.cas;

import org.jasig.cas.client.authentication.AuthenticationExtFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.mx.service.tomcat.config.TomcatServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import java.util.EnumSet;

@Configuration
public class CasFilterConfig {

    private final Logger logger = LoggerFactory.getLogger(CasFilterConfig.class);

    /**
     * cas配置类
     *
     * @return 返回cas配置类
     */
    @Bean
    public CasConfigBean casConfigBean() {
        return new CasConfigBean();
    }

    @Bean
    @ConditionalOnBean( TomcatServerConfig.class )
    public ServletContext buildCasFilter(WebApplicationContext webApplicationConnect, CasConfigBean casConfigBean) {

        logger.info("start build cas filter ！");
        ServletContext servletContext = webApplicationConnect.getServletContext();
        String isOpenCas = casConfigBean.getIsOpenCas();
        String casUrl = casConfigBean.getCasUrl();
        String serverUrl = casConfigBean.getServerUrl();
        String excludePaths = casConfigBean.getExcludePaths();
        String appWebUrl = casConfigBean.getAppWebUrl();
        String clusterNode = casConfigBean.getClusterNode() ;

        logger.info("isOpenCas: " + isOpenCas);
        logger.info("CAS Server: " + casUrl);
        logger.info("server path: " + serverUrl);
        logger.info("exclude Paths : " + excludePaths);
        logger.info("exclude Paths : " + appWebUrl);
        servletContext.setInitParameter("appWebUrl", appWebUrl);
        if ("true".equals(isOpenCas)) {


            FilterRegistration.Dynamic signOutFilter = servletContext.addFilter("CASSingle Sign OutFilter",
                    SingleSignOutFilter.class);
            if (signOutFilter != null) {
                signOutFilter.setInitParameter("casServerLogoutUrl", casUrl + "/ds-portal-cas-web/logout");
                signOutFilter.setInitParameter("clusterNode", clusterNode );
                signOutFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
                signOutFilter.setAsyncSupported(true);
            }

            FilterRegistration.Dynamic casFilter = servletContext.addFilter("CASFilter", AuthenticationExtFilter.class);
            if (casFilter != null) {
                casFilter.setInitParameter("casServerLoginUrl", casUrl + "/ds-portal-cas-web/login");
                casFilter.setInitParameter("serverName", serverUrl);
                casFilter.setInitParameter("excludePaths", excludePaths);
                casFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
                casFilter.setAsyncSupported(true);
            }

            FilterRegistration.Dynamic casValidationFilter = servletContext.addFilter("CASValidationFilter",
                    Cas20ProxyReceivingTicketValidationFilter.class);
            if (casValidationFilter != null) {
                casValidationFilter.setInitParameter("casServerUrlPrefix", casUrl + "/ds-portal-cas-web");
                casValidationFilter.setInitParameter("serverName", serverUrl);
                casValidationFilter.setInitParameter("excludePaths", excludePaths);
                casValidationFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
                casValidationFilter.setAsyncSupported(true);
            }

            FilterRegistration.Dynamic casWrapperFilter = servletContext.addFilter("CASHttpServletRequest WrapperFilter",
                    HttpServletRequestWrapperFilter.class);
            if (casWrapperFilter != null) {
                casWrapperFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
                casWrapperFilter.setAsyncSupported(true);
            }

            FilterRegistration.Dynamic casLocalFilter = servletContext.addFilter("CASAssertion Thread LocalFilter",
                    AssertionThreadLocalFilter.class);
            if (casLocalFilter != null) {
                casLocalFilter.addMappingForUrlPatterns(null, true, "/*");
                casLocalFilter.setAsyncSupported(true);
            }
        }
        logger.info("end build cas filter ！");
        return servletContext;
    }


//
//    @Bean
//    public FilterRegistrationBean signOutFilter( ) {
////        FilterRegistration.Dynamic signOutFilter = servletContext.addFilter("CASSingle Sign OutFilter",
////                SingleSignOutFilter.class);
////        if (signOutFilter != null) {
////            signOutFilter.setInitParameter("casServerLogoutUrl", casUrl + "/ds-portal-cas-web/logout");
////            signOutFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
////            signOutFilter.setAsyncSupported(true);
////        }
//
//        FilterRegistrationBean  signOutFilterbean = new FilterRegistrationBean();
//        org.jasig.cas.client.session.SingleSignOutFilter signOutFilter = new org.jasig.cas.client.session.SingleSignOutFilter();
//        signOutFilterbean.setName(  "CASSingle Sign OutFilter"  );
//        signOutFilterbean.setFilter( signOutFilter );
//        String casUrl = environment.getProperty("casUrl") ;
//        logger.info("CAS Server: " + casUrl );
//        signOutFilterbean.addInitParameter( "casServerLogoutUrl", casUrl + "/ds-portal-cas-web/logout" );
//        signOutFilterbean.setDispatcherTypes( EnumSet.of(DispatcherType.REQUEST) );
//        signOutFilterbean.addUrlPatterns("/*");
//        signOutFilterbean.setMatchAfter(false);
//        signOutFilterbean.setAsyncSupported( true );
//        signOutFilterbean.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE - 5);
//        return signOutFilterbean;
//    }
//
//    @Bean
//    public FilterRegistrationBean casFilter( ) {
////        FilterRegistration.Dynamic casFilter = servletContext.addFilter("CASFilter", AuthenticationExtFilter.class);
////        if (casFilter != null) {
////            casFilter.setInitParameter("casServerLoginUrl", casUrl + "/ds-portal-cas-web/login");
////            casFilter.setInitParameter("serverName", cocFireUrl );
////            casFilter.setInitParameter("excludePaths", excludePaths );
////            casFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
////            casFilter.setAsyncSupported(true);
////        }
//
//        FilterRegistrationBean  casFilterbean = new FilterRegistrationBean();
//        org.jasig.cas.client.authentication.AuthenticationExtFilter casFilter = new org.jasig.cas.client.authentication.AuthenticationExtFilter();
//        casFilterbean.setName(  "CASFilter"  );
//        casFilterbean.setFilter( casFilter );
//        String casUrl = environment.getProperty("casUrl") ;
//        logger.info("CAS Server: " + casUrl );
//        casFilterbean.addInitParameter( "casServerLoginUrl", casUrl + "/ds-portal-cas-web/login" );
//        casFilterbean.setDispatcherTypes( EnumSet.of(DispatcherType.REQUEST) );
//        casFilterbean.addUrlPatterns("/*");
//        casFilterbean.setMatchAfter(true);
//        casFilterbean.setAsyncSupported( true );
//        casFilterbean.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE - 4);
//        return casFilterbean;
//    }
//
//
//    @Bean
//    public FilterRegistrationBean casValidationFilter( ) {
////        FilterRegistration.Dynamic casValidationFilter = servletContext.addFilter("CASValidationFilter",
////                Cas20ProxyReceivingTicketValidationFilter.class);
////        if (casValidationFilter != null) {
////            casValidationFilter.setInitParameter("casServerUrlPrefix", casUrl + "/ds-portal-cas-web");
////            casValidationFilter.setInitParameter("serverName", cocFireUrl);
////            casValidationFilter.setInitParameter("excludePaths", excludePaths );
////            casValidationFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
////            casValidationFilter.setAsyncSupported(true);
////        }
//
//        FilterRegistrationBean  casValidationFilterBean = new FilterRegistrationBean();
//        org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter casValidationFilter = new org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter();
//        casValidationFilterBean.setName(  "CASValidationFilter"  );
//        casValidationFilterBean.setFilter( casValidationFilter );
//        String casUrl = environment.getProperty("casUrl") ;
//        String cocFireUrl =  environment.getProperty("cocFireUrl") ;
//        String excludePaths = environment.getProperty("excludePaths") ;
//        logger.info("CAS Server: " + casUrl );
//        logger.info("COC FIRE Server: " + cocFireUrl );
//        logger.info("exclude Paths : " + excludePaths ) ;
//        casValidationFilterBean.addInitParameter( "casServerUrlPrefix", casUrl + "/ds-portal-cas-web" );
//        casValidationFilterBean.addInitParameter( "serverName", cocFireUrl );
//        casValidationFilterBean.addInitParameter( "excludePaths", excludePaths );
//        casValidationFilterBean.setDispatcherTypes( EnumSet.of(DispatcherType.REQUEST) );
//        casValidationFilterBean.addUrlPatterns("/*");
//        casValidationFilterBean.setMatchAfter(true);
//        casValidationFilterBean.setAsyncSupported( true );
//        casValidationFilterBean.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE - 3);
//        return casValidationFilterBean ;
//    }
//
//    @Bean
//    public FilterRegistrationBean casWrapperFilter( ) {
//
////        FilterRegistration.Dynamic casWrapperFilter = servletContext.addFilter("CASHttpServletRequest WrapperFilter",
////                HttpServletRequestWrapperFilter.class);
////        if (casWrapperFilter != null) {
////            casWrapperFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
////            casWrapperFilter.setAsyncSupported(true);
////        }
//
//        FilterRegistrationBean  casWrapperFilterbean = new FilterRegistrationBean();
//        org.jasig.cas.client.util.HttpServletRequestWrapperFilter casWrapperFilter = new org.jasig.cas.client.util.HttpServletRequestWrapperFilter();
//        casWrapperFilterbean.setName(  "CASHttpServletRequest WrapperFilter"  );
//        casWrapperFilterbean.setFilter( casWrapperFilter );
//        casWrapperFilterbean.setDispatcherTypes( EnumSet.of(DispatcherType.REQUEST) );
//        casWrapperFilterbean.addUrlPatterns("/*");
//        casWrapperFilterbean.setMatchAfter(true);
//        casWrapperFilterbean.setAsyncSupported( true );
//        casWrapperFilterbean.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE - 2);
//        return casWrapperFilterbean ;
//    }
//
//
//    @Bean
//    public FilterRegistrationBean casLocalFilter( ) {
////        FilterRegistration.Dynamic casLocalFilter = servletContext.addFilter("CASAssertion Thread LocalFilter",
////                AssertionThreadLocalFilter.class);
////        if (casLocalFilter != null) {
////            casLocalFilter.addMappingForUrlPatterns(null, true, "/*");
////            casLocalFilter.setAsyncSupported(true);
////        }
//
//        FilterRegistrationBean  casLocalFilterbean = new FilterRegistrationBean();
//        org.jasig.cas.client.session.SingleSignOutFilter casLocalFilter = new org.jasig.cas.client.session.SingleSignOutFilter();
//        casLocalFilterbean.setName(  "CASAssertion Thread LocalFilter"  );
//        casLocalFilterbean.setFilter( casLocalFilter );
//        casLocalFilterbean.setDispatcherTypes( EnumSet.of(DispatcherType.REQUEST) );
//        casLocalFilterbean.addUrlPatterns("/*");
//        casLocalFilterbean.setMatchAfter(true);
//        casLocalFilterbean.setAsyncSupported( true );
//        casLocalFilterbean.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE - 1);
//        return casLocalFilterbean ;
//    }


}
