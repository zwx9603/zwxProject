package com.dscomm.iecs.basedata.utils;

import com.dscomm.iecs.basedata.dal.po.VideoEntity;
import com.dscomm.iecs.basedata.graphql.typebean.VideoBean;
import org.mx.StringUtils;

import java.util.Map;

public class VideoTransformUtil {

    /**
     * 视频监控 转换
     *
     * */
    public static VideoBean transform (VideoEntity source , Map<String, Map<String, String>> dicsMap ){
        if (source != null){
            VideoBean target = new VideoBean();
            target.setId(source.getId());
            target.setIdCode(source.getIdCode());
            target.setLatitude(source.getLatitude());
            target.setLongitude(source.getLongitude());
            target.setState(source.getState());

            target.setVideoAddress(source.getVideoAddress());
            target.setVideoCode(source.getVideoCode());

            target.setVideoDesc(source.getVideoDesc());
            target.setVideoName(source.getVideoName());
            if (!StringUtils.isBlank(source.getVideoTypeCode())){
                target.setVideoTypeCode(source.getVideoTypeCode());
                target.setVideoTypeName( dicsMap.get("SPJKLX").get( source.getVideoTypeCode())  );
            }

            if (!StringUtils.isBlank(source.getVideoAccessCode())){
                target.setVideoAccessCode(source.getVideoAccessCode());
                target.setVideoAccessName( dicsMap.get("SPJRFS").get( source.getVideoAccessCode())  );
            }

            if (!StringUtils.isBlank(source.getVideoDefinitionCode())){
                target.setVideoDefinitionCode(source.getVideoDefinitionCode());
                target.setVideoDefinitionName( dicsMap.get("SPQXD").get( source.getVideoDefinitionCode())  );
            }

            target.setVideoURL(source.getVideoURL());
            return target ;
        }
       return  null ;
    }
}
