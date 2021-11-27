package com.dscomm.iecs.accept.utils;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * RestfulClient的相关描述
 *
 * <p>
 * <b>History:</b>
 * <table border="1">
 * <tr>
 * <th>Date</th>
 * <th>Operator</th>
 * <th>Memo</th>
 * </tr>
 * <tr>
 * <td>2019年1月15日</td>
 * <td>YXF</td>
 * <td>Create</td>
 * </tr>
 * </table>
 *
 * @author YXF
 * @version 1.0.5
 * @since 1.0.5
 */
public class RestfulClient {

    private static final Logger LOGGER = LogManager
            .getLogger(RestfulClient.class);

    private static RestTemplate restTemplate;

    private final static Object syncLock = new Object();

    private static void initRestTemplate() {
        // 长连接保持30秒
        PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(
                30, TimeUnit.SECONDS);
        // 总连接数
        pollingConnectionManager.setMaxTotal(1000);
        // 同路由的并发数
        pollingConnectionManager.setDefaultMaxPerRoute(1000);

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(pollingConnectionManager);
        // 重试次数，默认是3次，没有开启
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(2,
                true));
        // 保持长连接配置，需要在头添加Keep-Alive
        httpClientBuilder
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN"));
        headers.add(new BasicHeader("Connection", "Keep-Alive"));

        httpClientBuilder.setDefaultHeaders(headers);

        HttpClient httpClient = httpClientBuilder.build();

        // httpClient配置RequestConfig
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                httpClient);
        // 连接超时-5000
        clientHttpRequestFactory.setConnectTimeout(5000);
        // 读取超时-5000
        clientHttpRequestFactory.setReadTimeout(5000);
        // 连接请求超时-200
        clientHttpRequestFactory.setConnectionRequestTimeout(200);
        // http消息转换器
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter(Charset
                .forName("UTF-8")));
        messageConverters.add(new FormHttpMessageConverter());
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays
                .asList(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML));
        messageConverters.add(mappingJackson2HttpMessageConverter);
        restTemplate = new RestTemplate(messageConverters);
        restTemplate.setRequestFactory(clientHttpRequestFactory);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());

        LOGGER.info("RestClient initialization completed");
    }

    public static RestTemplate getClient() {
        if (restTemplate == null) {
            synchronized (syncLock) {
                if (restTemplate == null) {
                    initRestTemplate();
                }
            }
        }
        return restTemplate;
    }
}
