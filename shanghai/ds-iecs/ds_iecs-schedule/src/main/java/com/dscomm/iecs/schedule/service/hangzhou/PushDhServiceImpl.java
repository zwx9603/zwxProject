package com.dscomm.iecs.schedule.service.hangzhou;

import com.alibaba.fastjson.JSONObject;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.schedule.exception.ScheduleException;
import com.dscomm.iecs.schedule.factory.ConnectionsFactory;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("pushDhServiceImpl")
public class PushDhServiceImpl implements PushDhService {

    private static final Logger logger = LoggerFactory.getLogger(PushDhServiceImpl.class);
    private LogService logService;
    private Environment env ;
    private ConnectionsFactory connectionsFactory;

    @Autowired
    public PushDhServiceImpl(LogService logService , Environment env , ConnectionsFactory connectionsFactory
    ){
        this.logService = logService ;
        this.env = env ;
        this.connectionsFactory = connectionsFactory ;
    }

    /**
     * 推送账号信息
     * @return
     */
    @Override
    public List<JSONObject> PushDh() {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<JSONObject> list = new ArrayList<>();
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("oauser") ) ) {


            String sql = " SELECT a.yhbh ,a.xm ,a.sjhm ,a.zw ,a.zwid ,b.id ,a.yhzh , a.ssjg FROM  t_tyqx_yt_zhxx a    LEFT JOIN " +
                    " t_tyqx_fire_jgxx_xfjg b  ON b.jgnbid = a.ssjg  " +
                    " WHERE a.deleted = 0 " +
                    " and to_char(a.cjsj, 'YYYY-MM-DD') = " +
                    " to_char(NOW(), 'YYYY-MM-DD') OR to_char(a.gxsj, 'YYYY-MM-DD') = to_char(NOW(), 'YYYY-MM-DD')  " +
                    " order by  yhbh asc    " ;
            pstat = conn.prepareStatement( sql );
            rs = pstat.executeQuery();

            while (rs.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("isOa", "1");
                jsonObject.put("isOnJob", "1");
                String ssjg = rs.getString( 8 ) ;
                String ssjgid = rs.getString( 6 ) ;
                if( Strings.isBlank( ssjgid ) ){
                    ssjgid = ssjg ;
                }
//                jsonObject.put("orgId", 1); //ssjg 1
//                jsonObject.put("orgCode", rs.getString(6));
//                jsonObject.put("orgId", ssjgid ); //ssjg
                jsonObject.put("orgCode", ssjgid );
                jsonObject.put("cardNum", rs.getString(1));
                jsonObject.put("isAutoGnrtNum", "1");
                jsonObject.put("userAccountType", "0");
                jsonObject.put("name", rs.getString(2));
                jsonObject.put("userName", rs.getString(7));
                jsonObject.put("userType", "1");
                jsonObject.put("userTypeKey", "dict.user.type.pc");
                jsonObject.put("ownerIp", "");
//                jsonObject.put("userTel", rs.getString(3));
                jsonObject.put("userTel", null );
                if ( Strings.isNotBlank(rs.getString(4))) {
                    JSONObject j = new JSONObject();
                    j.put("jobType", rs.getString(5));
                    j.put("jobTypeName", rs.getString(4));
                    jsonObject.put("jobTypeDtos", Arrays.asList(j));
                }
                list.add(jsonObject);
            }
            return list;
        } catch ( Exception  ex ) {
            logService.erorLog(logger, "service", "PushDh", String.format(" find PushDh table fail  , target time :%s.", new Timestamp( System.currentTimeMillis()  ) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstat != null) {
                    pstat.close();
                }
            }catch ( Exception ex ){
                logService.erorLog(logger, "service", "PushDh", String.format(" find PushDh table  fail  , target time :%s.", new Timestamp( System.currentTimeMillis() ) ), ex);
                throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
            }
        }
    }



    /**
     * 推送消防机构
     * @return
     */
    @Override
    public List<JSONObject> PushDh2() {
        PreparedStatement pstat = null;
        ResultSet rs = null;
        List<JSONObject> list = new ArrayList<>();
        try ( Connection conn = connectionsFactory.getConnection( env.getProperty("dhxfjg") ) ) {

            String sql = "select id,dwmc,dwjc,lxdh,dqjd,dqwd,ZHZ_JYQK as jgms,jglb,px,glid,dwjc as jgjc,txdz,zhz_jyqk,lxr_xm,xzqhdm as xzqdm," +
                    "case when   substr(XFJYJGXZDM, 0, 3)  =  '03' THEN '1' " +
                    "  when   substr(XFJYJGXZDM, 0, 3)  =  '05' THEN '2' " +
                    " when   substr(XFJYJGXZDM, 0, 3)  =  '09' THEN '3'" +
                    " ELSE null END as level, sjjgid ," +
                    " ( select dm.mc   from jcj_dm dm where zdlx = 'BASIC_XZQY' AND  dm.bm = xzqhdm ) AS XZDMC  " +
                    " FROM jgxx_xfjg    WHERE " +
                    "  ( " +
                    " cxmlj like (   select cxmlj || '%'  from jgxx_xfjg  where id = '85a307b64dbd469a93271b542cbbb075')  or " +
                    " jgtree like (   select jgtree || '%'  from jgxx_xfjg  where id = '85a307b64dbd469a93271b542cbbb075') " +
                    " ) " +
                    " and  substr( XFJYJGXZDM , 0 , 3 ) != '20' and  substr( XFJYJGXZDM , 0 , 4 ) != '152' " +
                    " and  to_char(to_timestamp(gxsj/1000), 'YYYY-MM-DD') = " +
                    " to_char(NOW(), 'YYYY-MM-DD') OR to_char(to_timestamp(gxsj/1000), 'YYYY-MM-DD') = to_char(NOW(), 'YYYY-MM-DD') " +
                    "   order by    XFJYJGXZDM asc   ";
            pstat = conn.prepareStatement( sql );
            rs = pstat.executeQuery();

            while (rs.next()) {

                /**
                 * parentCode	是	String	上级部门code；最大长度32
                 * deptName	是	String	部门名称；最大长度128
                 * deptSN	是	String	部门唯一标识码；最大长度64
                 * deptBrief	否	String	部门简称；最大长度128
                 * deptTel	否	String	联系电话；最大长度32
                 * gpsX	否	double	所在经度；小数位数1-12
                 * gpsY	否	double	所在纬度；小数位数1-12
                 * memo	否	String	描述；最大长度256
                 * needWatch	否	Integer	是否需要值班 0不需要 1需要；默认0
                 * deptType	否	Integer	部门类型
                 * deptTypeKey	否	String	部门类型字典key
                 * sort	否	Integer	排序码
                 * domainId	否	Integer	级联域ID
                 * deptLinkId	否	String	部门外部关联ID
                 * category	否	String	类别；最大长度64
                 * mechnaismType	否	String	字典  现役消防队 政府专职队（中队一个级别的）企业专职队（中队一个级别的）其它
                 * mechnaismTypeKey	否	String	机构类型名称；最大长度20
                 * level	否	String	级别 1支队 2大队 3中队
                 * unitNumber	否	String	单位编号；最大长度128
                 * unitAbbreviation	否	String	单位缩写；最大长度64
                 * unitAddress	否	String	单位地址；最大长度256
                 * iunitDescription	否	String	单位描述；最大长度500
                 * affiliation	否	String	隶属关系；最大长度300
                 * unitInternalNumber	否	String	单位内部编号；最大长度128
                 * contactPersonnel	否	String	联系人员；最大长度128
                 * corpsId	否	Integer	总队ID
                 * corpsName	否	String	总队名称；最大长度128
                 * detachmentId	否	Integer	支队ID
                 * detachmentName	否	String	支队名称；最大长度128
                 * regionCode	否	String	区域编码；最大长度64
                 * regionName	否	String	区域名称；最大长度256
                 * platformCode	否	String	物联网平台code；最大长度64
                 * recordCode	否	String	物联网平台主键code；最大长度64
                 */
                JSONObject jsonObject = new JSONObject();
                String id = rs.getString(1) ;
                String sjjgid = rs.getString(17) ;
                if( Strings.isBlank( sjjgid ) &&   !"85a307b64dbd469a93271b542cbbb075".equals( id ) ){
                    sjjgid =  "85a307b64dbd469a93271b542cbbb075" ;
                }
                jsonObject.put("parentCode", sjjgid );
                jsonObject.put("deptSN", id );
                jsonObject.put("deptName", rs.getString(2));
                jsonObject.put("deptBrief", rs.getString(3));
                jsonObject.put("deptTel", rs.getString(4));
                jsonObject.put("gpsX", rs.getString(5));
                jsonObject.put("gpsY", rs.getString(6));
                jsonObject.put("memo", rs.getString(7));
                jsonObject.put("needWatch", null);
                jsonObject.put("deptType", rs.getString(8));
                jsonObject.put("deptTypeKey", null);
                jsonObject.put("sort", rs.getString(9));
                jsonObject.put("domainId", null);
                jsonObject.put("deptLinkId", rs.getString(10));
                jsonObject.put("category", null);
                jsonObject.put("mechnaismType", null);
                jsonObject.put("mechnaismTypeKey", null);
                jsonObject.put("level", rs.getString(16));
                jsonObject.put("unitNumber", null);
                jsonObject.put("unitAbbreviation", rs.getString(11));
                jsonObject.put("unitAddress", rs.getString(12));
                jsonObject.put("iunitDescription", rs.getString(13));
                jsonObject.put("affiliation", null);
                jsonObject.put("unitInternalNumber", null);
                jsonObject.put("contactPersonnel", rs.getString(14));
                jsonObject.put("corpsId", null);
                jsonObject.put("corpsName", null);
                jsonObject.put("detachmentId", null);
                jsonObject.put("detachmentName", null);
                jsonObject.put("regionCode", rs.getString(15 ) );
                jsonObject.put("regionName",  rs.getString(18 ) );
                jsonObject.put("platformCode", null);
                jsonObject.put("recordCode", null);

                list.add(jsonObject);
            }

            return list;
        } catch ( Exception  ex ) {
            logService.erorLog(logger, "service", "PushDh2", String.format(" find PushDh 2 table fail  , target time :%s.", new Timestamp(  System.currentTimeMillis() ) ), ex);
            throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstat != null) {
                    pstat.close();
                }
            }catch ( Exception ex ){
                logService.erorLog(logger, "service", "PushDh2", String.format(" find PushDh 2 table  fail  , target time :%s.", new Timestamp( System.currentTimeMillis() ) ), ex);
                throw new ScheduleException(ScheduleException.ScheduleErrors.SCHEDULE_DATA_FAIL);
            }
        }

    }






}
