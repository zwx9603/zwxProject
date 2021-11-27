package com.dscomm.iecs.basedata.utils;

import com.dscomm.iecs.basedata.dal.po.LeaderEntity;
import com.dscomm.iecs.basedata.dal.po.PersonEntity;
import com.dscomm.iecs.basedata.dal.po.PersonMailEntity;
import com.dscomm.iecs.basedata.graphql.typebean.LeaderBean;
import com.dscomm.iecs.basedata.graphql.typebean.PersonBean;
import com.dscomm.iecs.basedata.graphql.typebean.PersonMailBean;
import org.mx.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述 : 人员、通讯录  等类数据转换工具
 */
public class PersonTransformUtil {



    /**
     * 描述：转换 人员
     *
     * @param source 人员 PO
     * @return 人员BO
     */
    public static PersonBean transform(PersonEntity source , Map<String, Map<String, String>> dicsMap , Map<String, String> organizationNameMap  ) {
        if(organizationNameMap==null){
            organizationNameMap=new HashMap<>();
        }

        if( source != null ){
            PersonBean target = new PersonBean();
            target.setId( source.getId() );
            target.setName( source.getName() );
            target.setIDCard( source.getIDCard() );
            target.setSexCode( source.getSexCode() );
            if(!StringUtils.isBlank(source.getSexCode() )&&  dicsMap.get("XB")!=null){
                target.setSexName( dicsMap.get("XB").get( source.getSexCode() ) );
            }


            target.setAuthorizedOrganizationId( source.getAuthorizedOrganizationId() );
            if(!StringUtils.isBlank( source.getAuthorizedOrganizationId()  )){
                target.setAuthorizedOrganizationName( organizationNameMap.get( source.getAuthorizedOrganizationId() ) );
            }


            target.setActualOrganizationId( source.getActualOrganizationId() );
            if(!StringUtils.isBlank( source.getActualOrganizationId() )){
                target.setActualOrganizationName(  organizationNameMap.get( source.getActualOrganizationId() )   );
            }


            target.setNationCode( source.getNationCode() );
            if(!StringUtils.isBlank( source.getNationCode())&&dicsMap.get("MZ")!=null){
                target.setNationName( dicsMap.get("MZ").get( source.getNationCode() ) );
            }


            target.setNativePlaceCode( source.getNativePlaceCode() );
            if(!StringUtils.isBlank(source.getNativePlaceCode() )&& dicsMap.get("JG")!=null){
                target.setNativePlaceName(  dicsMap.get("JG").get( source.getNativePlaceCode() )  );
            }


            target.setPoliticalStatusCode( source.getPoliticalStatusCode() );
            if(!StringUtils.isBlank(source.getPoliticalStatusCode() )&&  dicsMap.get("ZZMM")!=null){
                target.setPoliticalStatusName(  dicsMap.get("ZZMM").get( source.getPoliticalStatusCode() ) );
            }


            target.setPartyTime( source.getPartyTime() );
            target.setDateBirth( source.getDateBirth() );
            target.setEducationCode( source.getEducationCode() );
            if(!StringUtils.isBlank( source.getEducationCode() )&& dicsMap.get("XL")!=null){
                target.setEducationName(  dicsMap.get("XL").get( source.getEducationCode() ) );
            }


            target.setAcademicDegreeCode( source.getAcademicDegreeCode() );
            if(!StringUtils.isBlank( source.getAcademicDegreeCode() )&&  dicsMap.get("XW")!=null){
                target.setAcademicDegreeName( dicsMap.get("XW").get( source.getAcademicDegreeCode() ) );
            }


            target.setProfessionCode( source.getProfessionCode() );
            if(!StringUtils.isBlank( source.getProfessionCode() )&&  dicsMap.get("ZJLY")!=null){
                target.setProfessionName(   dicsMap.get("ZJLY").get( source.getProfessionCode() ) );
            }


            target.setMaritalStatusCode( source.getMaritalStatusCode() );
            if(!StringUtils.isBlank( source.getMaritalStatusCode() )&&  dicsMap.get("HYZK")!=null){
                target.setMaritalStatusName(  dicsMap.get("HYZK").get( source.getMaritalStatusCode() )  );
            }


            target.setAddress( source.getAddress() );
            target.setPostalCode( source.getPostalCode() );
            target.setPersonCategoryCode( source.getPersonCategoryCode() );
            if(!StringUtils.isBlank( source.getPersonCategoryCode() )&&   dicsMap.get("RYLB")!=null){
                target.setPersonCategoryName(  dicsMap.get("RYLB").get( source.getPersonCategoryCode() )  );
            }



            target.setReignStatusCode( source.getReignStatusCode() );
            if(!StringUtils.isBlank(source.getReignStatusCode() )&&    dicsMap.get("ZWQK")!=null){
                target.setReignStatusName(  dicsMap.get("ZWQK").get( source.getReignStatusCode() )  );
            }


            target.setQuartersCode( source.getQuartersCode() );
            if(!StringUtils.isBlank(source.getQuartersCode())&&   dicsMap.get("GW")!=null){
                target.setQuartersName(   dicsMap.get("GW").get( source.getQuartersCode() )  );
            }





            target.setDutiesCode( source.getDutiesCode() );
            if(!StringUtils.isBlank(source.getDutiesCode() )&&    dicsMap.get("ZYJSZW")!=null){
                target.setDutiesName(  dicsMap.get("ZYJSZW").get( source.getDutiesCode() ) );
            }



            target.setRankCode( source.getRankCode() );
            if(!StringUtils.isBlank(source.getRankCode()  )&&    dicsMap.get("ZW")!=null){
                target.setRankName(   dicsMap.get("ZW").get( source.getRankCode() )  );
            }



            target.setCredentialsType( source.getCredentialsType() );
            if(!StringUtils.isBlank( source.getCredentialsType() )&&   dicsMap.get("ZJLX")!=null){
                target.setCredentialsTypeName( dicsMap.get("ZJLX").get( source.getCredentialsType() )  );
            }


            target.setCredentialsNumber( source.getCredentialsNumber() );

            target.setPoliceRankCode( source.getPoliceRankCode() );
            if(!StringUtils.isBlank( source.getPoliceRankCode()  )&&  dicsMap.get("JX")!=null){
                target.setPoliceRankName(  dicsMap.get("JX").get( source.getPoliceRankCode() )   );
            }


            target.setWhetherExpert( source.getWhetherExpert() );
            target.setPicture( source.getPicture() );
            target.setRemarks( source.getRemarks() );
            target.setPersonStatusCode( source.getPersonStatusCode() );
            if(!StringUtils.isBlank(  source.getPersonStatusCode() )&&dicsMap.get("RYZWQK")!=null){
                target.setPersonStatusName( dicsMap.get("RYZWQK").get( source.getPersonStatusCode() )   );
            }


            target.setPersonOrderNum( source.getPersonOrderNum() );
            target.setPersonReignStatusCode( source.getPersonReignStatusCode() );
            if(!StringUtils.isBlank(source.getPersonReignStatusCode() )&&dicsMap.get("RYZK")!=null){
                target.setPersonReignStatusName( dicsMap.get("RYZK").get( source.getPersonReignStatusCode() )   );
            }


            target.setBaseParentOrganizationId( source.getBaseParentOrganizationId() );
            if(!StringUtils.isBlank(source.getBaseParentOrganizationId())){
                target.setBaseParentOrganizationName(  organizationNameMap.get( source.getBaseParentOrganizationId() )  );
            }


            target.setWhetherLoad( source.getWhetherLoad() );
            return target;
        }
        return  null ;
    }


    /**
     * 描述：转换 人员通讯录
     *
     * @param source 人员通讯录PO
     * @return 人员通讯录BO
     */
    public static PersonMailBean transform(PersonMailEntity source) {
        if( source != null ){
            PersonMailBean target = new PersonMailBean();
            target.setPersonName(source.getPersonName());
            target.setPersonId(source.getPersonId());
            target.setUserId(source.getUserId());
            target.setMobilePhone(source.getMobilePhone());
            target.setHomePhone(source.getHomePhone());
            target.setOfficePhone(source.getOfficePhone());
            target.setMobilePhoneTwo(source.getMobilePhoneTwo());
            target.setMobilePhoneThree(source.getMobilePhoneThree());
            target.setInternetEmail(source.getInternetEmail());
            target.setSecurityEmail(source.getSecurityEmail());
            target.setShortNumber(source.getShortNumber());
            target.setRemarks(source.getRemarks());
            target.setId(source.getId());
            return target;
        }
        return  null ;
    }

    /**
     * 领导entity --bean
     * @param entity
     * @return
     */
    public static LeaderBean transform(LeaderEntity entity){
        LeaderBean bean = new LeaderBean();
        bean.setId(entity.getId());
        bean.setDuty(entity.getDuty());
        bean.setLeaderName(entity.getLeaderName());
        bean.setMobliPhone(entity.getMobliPhone());
        bean.setOfficeNumber(entity.getOfficeNumber());
        bean.setType(entity.getType());
        bean.setUnitName(entity.getUnitName());
        return bean;
    }

}
