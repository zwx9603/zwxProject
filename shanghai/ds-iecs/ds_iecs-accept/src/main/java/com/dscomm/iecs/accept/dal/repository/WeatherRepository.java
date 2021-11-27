package com.dscomm.iecs.accept.dal.repository;

import com.dscomm.iecs.accept.dal.po.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 描述:天气信息 数据库持久层服务
 */
@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, String> {

    /**
     * 根据 行政区编码 获得天气信息
     *
     * @param districtCode 行政区编码
     * @return 天气信息
     */
    @Query(" select t from WeatherEntity t  where  t.valid = 1 and t.districtCode = ?1 and  " +
            " t.forecastStartTime <=  ?2 and  t.forecastEndTime > ?2  order by  t.forecastTime desc   ")
    List<WeatherEntity> findWeatherDistrictCode(String districtCode , Long currentTime  );


}
