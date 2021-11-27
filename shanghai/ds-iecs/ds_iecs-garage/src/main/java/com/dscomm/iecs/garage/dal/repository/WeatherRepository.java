package com.dscomm.iecs.garage.dal.repository;

import com.dscomm.iecs.garage.dal.po.FictitiousEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 描述:天气信息 数据库持久层服务
 */
@Repository
public interface WeatherRepository extends JpaRepository<FictitiousEntity, String> {

    /**
     * 根据 行政区编码 获得天气信息
     *
     * @param districtCode 行政区编码
     * @return 天气信息
     */
    @Query(value = "  select    " +
            "   id , YBFBSJ , YBQSSJ  , YBJSSJ , XFJGDM ,   " +
            "   ( select JGMC FROM  JGXX_XFJG a with (nolock) where a.id = xfjgdm ) as XFJGMC ,  " +
            "   XZQY ,  " +
            "   ( SELECT DMMC FROM DM_SJZD b with (nolock) WHERE b.DM_SJZD_ID = '61CFA4E7B188490A873395B4FB237C84' AND b.DMZ = XZQY )  AS XZQYMC ,  " +
            "   ZDWD , ZGWD ,   " +
            "   TQDM1,   " +
            "    ( SELECT DMMC FROM DM_SJZD b with (nolock) WHERE b.DM_SJZD_ID = '5579DDBB3AC04CFE8A5F44DBA53AA0E3' AND b.DMZ = TQDM1 )  AS TQDM1MC ,  " +
            "   TQDM2 ,  " +
            "    ( SELECT DMMC FROM DM_SJZD b with (nolock) WHERE b.DM_SJZD_ID = '5579DDBB3AC04CFE8A5F44DBA53AA0E3' AND b.DMZ = TQDM2 )  AS TQDM2MC ,  " +
            "   ZDFL ,   " +
            "   ( SELECT DMMC FROM DM_SJZD b with (nolock) WHERE b.DM_SJZD_ID = '821D6BF5EAC941CEBEBB7EDDFF4241C4' AND b.DMZ = ZDFL )  AS ZDFLMC ,  " +
            "   ZGFL,   " +
            "   ( SELECT DMMC FROM DM_SJZD b with (nolock) WHERE b.DM_SJZD_ID = '821D6BF5EAC941CEBEBB7EDDFF4241C4' AND b.DMZ = ZGFL )  AS ZGFLMC ,  " +
            "   FX1 ,   " +
            "   ( SELECT DMMC FROM DM_SJZD b with (nolock) WHERE b.DM_SJZD_ID = 'E9E720898B0045A6AC946DC9A34E4140' AND b.DMZ = FX1 )  AS FX1MC ,  " +
            "   FX2,    " +
            "   ( SELECT DMMC FROM DM_SJZD b with (nolock) WHERE b.DM_SJZD_ID = 'E9E720898B0045A6AC946DC9A34E4140' AND b.DMZ = FX2 )  AS FX2MC ,  " +
            "   JSGL,   " +
            "   ZDXDSD , ZGXDSD, ZHYB , BZ  from  ZBZB_QXXX  with (nolock) " +
            "   where  XZQY = ?1 and  YBQSSJ < ?2 and YBJSSJ > ?2 order by YBFBSJ desc " , nativeQuery =  true )
    List<Object[]> findWeatherDistrictCode(String districtCode, Date currentTime);

}
