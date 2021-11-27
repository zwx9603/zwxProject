package com.dscomm.iecs.garage.dal.repository;

import com.dscomm.iecs.garage.dal.po.FictitiousEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 描述:值班信息 数据库持久层服务
 */
@Repository
public interface OnDutyRepository extends JpaRepository<FictitiousEntity, String> {

    @Query(value = " SELECT   " +
            "    a.id,  " +
            "    a.ssjg,  " +
            "    a.ssdw,  " +
            "    ( SELECT c.jgmc FROM jgxx_xfjg c WHERE a.ssdw = c.id ) AS ssdwmc,  " +
            "    a.zbrq,  " +
            "    a.zblx,  " +
            "    ( SELECT b.lxmc FROM swgl_zbgl_zblx b WHERE b.id = a.zblx  ) AS gwmc,  " +
            "    ( SELECT b.gwzz FROM swgl_zbgl_zblx b WHERE b.id = a.zblx  )AS zbzz,  " +
            "    a.yhbh AS zbrid,  " +
            "    a.yhbh AS zbrbh,  " +
            "    a.yhxm AS zbrxm,  " +
            "    ( SELECT e.yddh FROM ryxx_rytxl e WHERE e.ryid= a.yhbh ) AS lxdh,  " +
            "    ( SELECT b.zbrc FROM swgl_zbgl_zblx b  WHERE b.id = a.zblx ) AS zbrc,  " +
            "    ( SELECT b.xsxh FROM swgl_zbgl_zblx b WHERE b.id = a.zblx ) AS xsxh   " +
            "      " +
            "   FROM swgl_zbgl_mrzb a  " +
            "  WHERE a.jlzt = 1  and  a.zbrq >=?1 and   a.zbrq <?2 and ssdw in ( select  id  from JGXX_XFJG where ZZJG=1 and JLZT=1 and JGTREE like ?3 ) " +
            "  ORDER BY a.ssjg,  xsxh " , nativeQuery =  true )
    List<Object[]> findAllOrganizationOnDuty(Date startTime, Date endTime, String searchPath );

    @Query(value = " SELECT   " +
            "    a.id,  " +
            "    a.ssjg,  " +
            "    a.ssdw,  " +
            "    ( SELECT c.jgmc FROM jgxx_xfjg c WHERE a.ssdw = c.id ) AS ssdwmc,  " +
            "    a.zbrq,  " +
            "    a.zblx,  " +
            "    ( SELECT b.lxmc FROM swgl_zbgl_zblx b WHERE b.id = a.zblx  ) AS gwmc,  " +
            "    ( SELECT b.gwzz FROM swgl_zbgl_zblx b WHERE b.id = a.zblx  )AS zbzz,  " +
            "    a.yhbh AS zbrid,  " +
            "    a.yhbh AS zbrbh,  " +
            "    a.yhxm AS zbrxm,  " +
            "    ( SELECT e.yddh FROM ryxx_rytxl e WHERE e.ryid= a.yhbh ) AS lxdh,  " +
            "    ( SELECT b.zbrc FROM swgl_zbgl_zblx b  WHERE b.id = a.zblx ) AS zbrc,  " +
            "    ( SELECT b.xsxh FROM swgl_zbgl_zblx b WHERE b.id = a.zblx ) AS xsxh   " +
            "      " +
            "   FROM swgl_zbgl_mrzb a  " +
            "  WHERE a.jlzt = 1  and  a.zbrq >=?1 and   a.zbrq <?2 and ssdw = ?3  " +
            "  ORDER BY a.ssjg,  xsxh " , nativeQuery =  true )
    List<Object[]> findOrganizationOnDuty(Date startTime, Date endTime, String organizationId);

    @Query(value = " SELECT   " +
            "    a.id,  " +
            "    a.ssjg,  " +
            "    a.ssdw,  " +
            "    ( SELECT c.jgmc FROM jgxx_xfjg c WHERE a.ssdw = c.id ) AS ssdwmc,  " +
            "    a.zbrq,  " +
            "    a.zblx,  " +
            "    ( SELECT b.lxmc FROM swgl_zbgl_zblx b WHERE b.id = a.zblx  ) AS gwmc,  " +
            "    ( SELECT b.gwzz FROM swgl_zbgl_zblx b WHERE b.id = a.zblx  )AS zbzz,  " +
            "    a.yhbh AS zbrid,  " +
            "    a.yhbh AS zbrbh,  " +
            "    a.yhxm AS zbrxm,  " +
            "    ( SELECT e.yddh FROM ryxx_rytxl e WHERE e.ryid= a.yhbh ) AS lxdh,  " +
            "    ( SELECT b.zbrc FROM swgl_zbgl_zblx b  WHERE b.id = a.zblx ) AS zbrc,  " +
            "    ( SELECT b.xsxh FROM swgl_zbgl_zblx b WHERE b.id = a.zblx ) AS xsxh   " +
            "      " +
            "   FROM swgl_zbgl_mrzb a  " +
            "  WHERE a.jlzt = 1  and  a.zbrq >=?1 and   a.zbrq <?2 and ssdw in ( select  id  from JGXX_XFJG where ZZJG=1 and JLZT=1 and SJJGID = ?3 ) " +
            "  ORDER BY a.ssjg,  xsxh " , nativeQuery =  true )
    List<Object[]> findChildrenOrganizationOnDuty(Date startTime, Date endTime, String organizationId );

    @Query(value = " SELECT   " +
            "    a.id,  " +
            "    a.ssjg,  " +
            "    a.ssdw,  " +
            "    ( SELECT c.jgmc FROM jgxx_xfjg c WHERE a.ssdw = c.id ) AS ssdwmc,  " +
            "    a.zbrq,  " +
            "    a.zblx,  " +
            "    ( SELECT b.lxmc FROM swgl_zbgl_zblx b WHERE b.id = a.zblx  ) AS gwmc,  " +
            "    ( SELECT b.gwzz FROM swgl_zbgl_zblx b WHERE b.id = a.zblx  )AS zbzz,  " +
            "    a.yhbh AS zbrid,  " +
            "    a.yhbh AS zbrbh,  " +
            "    a.yhxm AS zbrxm,  " +
            "    ( SELECT e.yddh FROM ryxx_rytxl e WHERE e.ryid= a.yhbh ) AS lxdh,  " +
            "    ( SELECT b.zbrc FROM swgl_zbgl_zblx b  WHERE b.id = a.zblx ) AS zbrc,  " +
            "    ( SELECT b.xsxh FROM swgl_zbgl_zblx b WHERE b.id = a.zblx ) AS xsxh   " +
            "      " +
            "   FROM swgl_zbgl_mrzb a  " +
            "  WHERE a.jlzt = 1  and  a.zbrq >=?1 and   a.zbrq <?2 and ssdw in ( select  id  from JGXX_XFJG where ZZJG=1 and JLZT=1 and JGTREE like ?3 and jgxzdm like '09%'  ) " +
            "  ORDER BY a.ssjg,  xsxh " , nativeQuery =  true )
    List<Object[]> findSquadronOrganizationOnDuty(Date startTime, Date endTime, String searchPath );

}
