package com.dscomm.iecs.accept.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * 描述:打印访问记录
 * author:YangFuXi
 * Date:2021/5/28 Time:10:14
 **/
public class PrintAccessRecordUtils {
    private static final Logger logger = LoggerFactory.getLogger(PrintAccessRecordUtils.class);
    private static Boolean printAccess;

    static {
        try (InputStream resourceAsStream = PrintAccessRecordUtils.class.getClassLoader().getResourceAsStream("changeability.properties")) {
            if (resourceAsStream != null) {
                Properties properties = new Properties();
                properties.load(resourceAsStream);
                String printAccessRecord = properties.getProperty("printAccessRecord");
                if ("true".equals(printAccessRecord)) {
                    printAccess = true;
                } else {
                    printAccess = false;
                }
            }
        } catch (IOException ex) {
            logger.error("init printAccess. fail", ex);
            printAccess = false;
        }
    }

    public static void printRequest(String method,String token, JSON requestData, Long start) {
        if (printAccess) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long endTime = System.currentTimeMillis();
            logger.info(String.format("success to excue %s,start time:%s,end time:%s,total time:%sms,token:%s,request data:%s",method, sdf.format(start), sdf.format(endTime), endTime - start, token, requestData));
        }
    }

    public static void printRequestFailRecord(String method,String token, JSON requestData, Exception ex) {
        if (printAccess) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long endTime = System.currentTimeMillis();
            logger.error(String.format("fail to excue %s,token:%s,request data:%s",method, token, requestData), ex);
        }
    }
}
