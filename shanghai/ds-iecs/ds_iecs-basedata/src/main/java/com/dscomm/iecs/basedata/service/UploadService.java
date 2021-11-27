package com.dscomm.iecs.basedata.service;

import com.dscomm.iecs.basedata.graphql.typebean.FdfsDataBean;

import java.io.InputStream;

/**
 *  上传 附件信息
 */
public interface UploadService {

    /**
     *  fdfs 上传 附件信息
     * @param fileSource
     * @param fileName
     * @return
     */
    FdfsDataBean uploadFile(InputStream fileSource, String fileName ) ;



    /**
     *  fdfs 根据id 附件访问路径
     * @param id
     * @return
     */
    String getFilePath ( String  id  ) ;
}
