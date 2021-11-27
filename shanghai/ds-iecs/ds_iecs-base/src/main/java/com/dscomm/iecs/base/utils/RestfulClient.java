package com.dscomm.iecs.base.utils;

import com.alibaba.fastjson.JSON;
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
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class RestfulClient {

	private static final Logger LOGGER = LogManager.getLogger(RestfulClient.class);

	private static RestTemplate restTemplate;

	private final static Object syncLock = new Object();

	/**
	 * 根据 dataStore 获得查询条件参数
	 *
	 * @param dataStore 传入参数
	 * @param key 查询key
	 * @return 查询条件参数
	 */
	public static String getDatas(DataStore dataStore, String key) {
		String param = null;
		if (dataStore != null && dataStore.getData() != null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> params = (Map<String, Object>) dataStore.getData();
			param = params.get(key) == null ? null : params.get(key).toString();
		}
		return param;
	}

	/**
	 * 根据 dataStore 获得查询条件参数
	 *
	 * @param dataStore 传入参数
	 * @param key 查询key
	 * @return 查询条件参数
	 */
	//TODO unused
	public static String getDatasFormJSON(DataStore dataStore, String key) {
		String param = null;
		if (dataStore != null && dataStore.getData() != null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> params = (Map<String, Object>) dataStore.getData();
			param = params.get(key) == null ? JSON.toJSONString("") : JSON.toJSONString(params.get(key));

		}
		return param;
	}

	/**
	 * 根据 dataStore 查询参数map
	 *
	 * @param dataStore 参数对象
	 * @return 查询参数map
	 */
	//TODO unused
	public static Map<String, Object> getSearchParams(DataStore dataStore) {
		Map<String, Object> queryForm = new HashMap<String, Object>();
		if (dataStore != null && dataStore.getQuery() != null) {
			queryForm = dataStore.getQuery().getQueryForm();
		}
		return queryForm;

	}

	/**
	 * 根据 dataStore 获得分页条件参数
	 *
	 * @param dataStore 查询条件
	 * @return 分页条件参数结果
	 */
	//TODO unused
	public static PageForm getPageParams(DataStore dataStore) {
		PageForm pageForm = new PageForm();
		if (dataStore != null && dataStore.getQuery() != null && dataStore.getQuery().getPageForm() != null) {
			pageForm = dataStore.getQuery().getPageForm();
			pageForm.setCurrentPage(Strings.isBlank(pageForm.getCurrentPage()) ? null : pageForm.getCurrentPage());
			pageForm.setPageSize(Strings.isBlank(pageForm.getPageSize()) ? null : pageForm.getPageSize());
		}
		return pageForm;
	}

	private static void initRestTemplate() {
		// 长连接保持30秒
		PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager(30,
				TimeUnit.SECONDS);
		// 总连接数
		pollingConnectionManager.setMaxTotal(1000);
		// 同路由的并发数
		pollingConnectionManager.setDefaultMaxPerRoute(1000);

		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		httpClientBuilder.setConnectionManager(pollingConnectionManager);
		// 重试次数，默认是3次，没有开启
		httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(2, true));
		// 保持长连接配置，需要在头添加Keep-Alive
		httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
		List<Header> headers = new ArrayList<>();
		headers.add(new BasicHeader("User-Agent",
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
		messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		messageConverters.add(new FormHttpMessageConverter());
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter
				.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_HTML));
		messageConverters.add(mappingJackson2HttpMessageConverter);
		restTemplate = new RestTemplate(messageConverters);
		restTemplate.setRequestFactory(clientHttpRequestFactory);
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler(){
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
				if( response.getStatusCode() != HttpStatus.UNAUTHORIZED){
					super.handleError(response );
				}
			}
		});

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
