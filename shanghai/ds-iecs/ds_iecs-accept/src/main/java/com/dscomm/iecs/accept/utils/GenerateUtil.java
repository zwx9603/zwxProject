package com.dscomm.iecs.accept.utils;

import org.apache.logging.log4j.util.Strings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成各类编号的工具类
 *
 */
public class GenerateUtil {
    private static int idSeed = 10;
    private static String backup = "0";//默认为0

    public static Long Create(String id, int agentID) {
        //  String id =ds.com.lgc .StartUpClientModel .Instance .GetSysDate ().ToString("yyyyMMddHHmmssfff");
        String strAgentID = String.valueOf(agentID);
        strAgentID = strAgentID.substring(strAgentID.length() <= 3 ? 0 : strAgentID.length() - 3);
        strAgentID = PadLeft(strAgentID, 3, "0");
        id += strAgentID;
        id += String.valueOf(idSeed++);
        if (idSeed > 98) idSeed = 10;
        return Long.parseLong(id);
    }

    public static String PadLeft(String target, int number, String fillString) {
        String result = target;
        if (target.length() >= number)
            return result;
        for (int i = 0; i < number - target.length(); i++) {
            result += fillString;
        }
        return result;
    }

    /**
     * 产生呼入编号
     *
     * @param dateTime 呼入时间
     * @param th       台号
     * @return
     */
    public static String GenerateInboundBH(Date dateTime, String th) {
        String sjbh = "";
        sjbh = GetFormatTime(dateTime);
        return sjbh + Fill(th, 3);
    }

    public static String GenerateIncidentNumber(Date dateTime,String districtCode, String th) {
        String sjbh = "";
        sjbh = GetFormatTime(dateTime);
        return sjbh + districtCode + Fill(th, 3);
    }

    /**
     * 产生事件单编号
     *
     * @param dateTime 呼入时间
     * @param th       台号
     * @return
     */
    public static String GenerateSJDBH(Date dateTime, String th) {
        return GenerateInboundBH(dateTime, th);
    }

    /**
     * 产生呼入编号
     *
     * @param dateTime 呼入时间
     * @param th       台号
     * @return
     */
    public static String GenerateInboundBHSZ(Date dateTime, String th, Integer generateNumber, String region) {
        String sjbh = "";
        sjbh = GetFormatTimeSZ(dateTime) + Fill(String.valueOf(generateNumber),5);
        return region + sjbh + Fill(th, 3) + backup;
    }

    /**
     * 产生事件单编号（深圳）
     *
     * @param dateTime 呼入时间
     * @param th       台号
     * @return
     */
    public static String GenerateSJDBHSZ(Date dateTime, String th, Integer generateNumber, String region) {
        return GenerateInboundBHSZ(dateTime, th, generateNumber, region);
    }

    /**
     * 产生受理单编号
     *
     * @param dateTime 呼入时间
     * @param th       台号
     * @return
     */
    public static String GenerateSLDBH(Date dateTime, String th) {
        return GenerateInboundBH(dateTime, th);
    }

    /**
     * 产生处警单编号
     *
     * @param dateTime 呼入时间
     * @param th       台号
     * @param dwbh     单位编号
     * @return
     */
    public static String GenerateCJDBH(Date dateTime, String th, String dwbh) {
        String sjbh = "";
        sjbh = GetFormatTime(dateTime);
        dwbh = Fill(dwbh, 9);
        return sjbh + dwbh.substring(0, 8) + Fill(th, 3);
    }

    /**
     * 产生处警指令单编号
     *
     * @param dateTime 呼入时间
     * @param th       台号
     * @param dwbh     单位编号
     * @param xh       序号
     * @return
     */
    public static String GenerateZLDBH(Date dateTime, String th, String dwbh, String xh) {
        String sjbh = "";
        sjbh = GetFormatTime(dateTime);
        dwbh = Fill(dwbh, 9);
        return sjbh + dwbh.substring(0, 8) + Fill(String.valueOf(xh), 3) + Fill(th, 3);
    }

    /**
     * 产生反馈单编号
     *
     * @param dateTime 呼入时间
     * @param th       台号
     * @param dwbh     单位编号
     * @return
     */
    public static String GenerateFKDBH(Date dateTime, String th, String dwbh) {
        return GenerateCJDBH(dateTime, th, dwbh);
    }

    /**
     * 产生回访单编号
     *
     * @param dateTime 呼入时间
     * @param th       台号
     * @return
     */
    public static String GenerateFollowUpBH(Date dateTime, String th) {
        String sjbh = "";
        sjbh = GetFormatTime(dateTime);
        return sjbh + Fill(th, 3);
    }


    private static String Fill(String xh, int length) {
        String returnValue = "";
        if ( Strings.isNotBlank( xh )    ) {
            if(   xh.length() >= length ){
                returnValue = xh;
                return  returnValue.substring( 0 , length ) ;
            }else{
                returnValue = xh;
            }
        }
        try {
            if ( Strings.isBlank( xh  ) ||  xh.length() < length) {
                for (int j = 0; j < length - ( Strings.isBlank( xh  ) ? 0 : xh.length() ) ; j++) {
                    returnValue = "0" + returnValue;
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        return returnValue;
    }


    private static String GetFormatTime(Date dateTime) {
        DateFormat dFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return dFormat.format(dateTime);
    }

    private static String GetFormatTimeSZ(Date dateTime) {
        DateFormat dFormat = new SimpleDateFormat("yyyyMMddHH");
        return dFormat.format(dateTime);
    }
}
