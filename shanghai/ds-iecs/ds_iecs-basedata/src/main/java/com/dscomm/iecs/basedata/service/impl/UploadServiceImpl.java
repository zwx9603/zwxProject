package com.dscomm.iecs.basedata.service.impl;

import com.alibaba.fastjson.JSON;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.typebean.FdfsDataBean;
import com.dscomm.iecs.basedata.graphql.typebean.FdfsResponseBean;
import com.dscomm.iecs.basedata.service.UploadService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.net.InetAddress;

/**
 * 描述: 上传 文件服务 实现类
 *
 * @author YangFuxi
 * Date Time 2019/8/9 9:40
 */
@Component("UploadServiceImpl")
public class UploadServiceImpl implements UploadService {
    private static final Logger logger = LoggerFactory.getLogger(UploadServiceImpl.class);
    private LogService logService;
    private Environment env;


    @Autowired
    public UploadServiceImpl(LogService logService , Environment env  ) {
        this.logService = logService;
        this.env = env ;

    }



    /**
     * {@inheritDoc}
     *
     * @see #uploadFile( InputStream , String )
     */
    @Transactional
    @Override
    public FdfsDataBean uploadFile(InputStream fileSource, String fileName ) {

        try {
            logService.infoLog(logger, "service", "uploadFile", "service is started...");
            Long logStart = System.currentTimeMillis();

            FdfsDataBean res = null ;

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();

            String url = env.getProperty("fdfsUploadUrl") ;
            HttpPost uploadFile = new HttpPost(url);
            builder.addTextBody("uploadUser", "HJSL" );
            builder.addTextBody("uploadSystem", "HJSL" );
            builder.addTextBody("srcIpAddr", InetAddress.getLocalHost().getHostAddress());
            builder.addBinaryBody(
                    "files",
                    fileSource,
                    ContentType.APPLICATION_OCTET_STREAM,
                    fileName
            );
            HttpEntity multipart = builder.build();
            uploadFile.setEntity(multipart);
            CloseableHttpResponse response = httpClient.execute(uploadFile);
            HttpEntity responseEntity = response.getEntity();
            String result= EntityUtils.toString(responseEntity, "UTF-8");
            FdfsResponseBean responseData = JSON.parseObject(result, FdfsResponseBean.class);

            if (responseData != null && responseData.getDataStore() != null
                    && responseData.getDataStore().size() > 0) {
                FdfsDataBean dataBean = responseData.getDataStore().get(0);
                res =  dataBean ;
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "uploadFile", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "uploadFile", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.UPLOAD_FDFS_FILE_FAIL);
        }
    }



    /**
     * {@inheritDoc}
     *
     * @see #uploadFile( InputStream , String )
     */
    @Transactional
    @Override
    public String  getFilePath ( String  id  ) {

        try {
            logService.infoLog(logger, "service", "getFilePath", "service is started...");
            Long logStart = System.currentTimeMillis();

            String res = null ;

            CloseableHttpClient httpClient = HttpClients.createDefault();

            String url  = env.getProperty("fdfsFilePathUrl") + "?id=" + id ;

            HttpGet filePathUrl  = new HttpGet(url);

            CloseableHttpResponse response = httpClient.execute(filePathUrl);
            HttpEntity responseEntity = response.getEntity();
            String result= EntityUtils.toString(responseEntity, "UTF-8");
            FdfsResponseBean responseData = JSON.parseObject(result, FdfsResponseBean.class);

            if (responseData != null && responseData.getDataStore() != null
                    && responseData.getDataStore().size() > 0) {
                FdfsDataBean dataBean = responseData.getDataStore().get(0);
                if( dataBean != null ){
                    res =  dataBean.getPath()  ;
                }

            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "getFilePath", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return  res ;

        }catch (Exception ex){
            logService.erorLog(logger, "service", "getFilePath", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_FILE_PATH_FAIL);
        }
    }







}
