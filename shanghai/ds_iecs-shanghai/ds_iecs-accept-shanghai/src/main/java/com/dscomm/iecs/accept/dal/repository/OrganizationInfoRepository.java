package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.OrganizationInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationInfoRepository extends JpaRepository<OrganizationInformationEntity,String> {

    /*
    * 对消防力量机构信息进行修改
    * */
    @Modifying(clearAutomatically = true) // 暂时用所属机构做条件
    @Query("update OrganizationInformationEntity t set t.jgnbid =?1,t.jgjc = ?2,t.px = ?3,t.zhz_jyqk =?4,t.zzjg = ?5 where t.jgmc = ?6 and t.jgdz =?7")
    void updateOrganizationInfo(String jgbh,String ledmc,Integer organizationOrderNum,String organizationDesc,String organizationRepealStatus,String jgmc,String organizationAddress);

    /*
    * 对消防力量车辆信息进行修改
    * */
    @Modifying(clearAutomatically = true) //
    @Query("update VehicleNumEntity t set t.cljc = ?3,t.px=?4,t.dthh = ?5,t.sfdycdcl_tywysbm = ?6,t.gpsbh =?7,t.sb = ?8,t.fzr_lxdh = ?9,t.zs = ?10,t.zp = ?11 ,t.bll = ?12,t.zzalpm_rj =?13,t.stb =?14,t.ytgd =?15,t.bz= ?16,t.sfzb = ?17,t.sfbl = ?18 where t.zbmc =?1 and t.cphm =?2 ")
    void updateVehicleInfo(String cllx,String cphmm,String ledmc,Integer sx,String dthh,String tczhc,String gpsbh,String pdabh,String telNumber,Float zsl,Float zpl,Float bll,Float czpll,Float stbll,String ytgd,String bz,Integer drzb,Integer blcl);
}
