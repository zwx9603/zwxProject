package com.dscomm.iecs.accept.service.impl;


import com.dscomm.iecs.accept.dal.po.IncidentEntity;
import com.dscomm.iecs.accept.dal.repository.QueryUnitRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.inputbean.OrgUnitInfo;
import com.dscomm.iecs.accept.graphql.typebean.QueryUnitBean;
import com.dscomm.iecs.accept.service.QueryUnitService;
import com.dscomm.iecs.accept.utils.transform.FireTransformUtil;
import com.dscomm.iecs.base.service.log.LogService;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.mx.error.UserInterfaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：联勤联动单位查询
 */
@Component
public class QueryUnitServiceImpl implements QueryUnitService {
	private static final Logger logger = LoggerFactory.getLogger(QueryUnitServiceImpl.class);
	private QueryUnitRepository queryUnitRepository;
	private GeneralAccessor accessor;
	private LogService logService;

	@Autowired
	public QueryUnitServiceImpl(QueryUnitRepository queryUnitRepository,LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor) {
		this.queryUnitRepository = queryUnitRepository;
		this.accessor=accessor;
		this.logService = logService;
	}

	/**
	 * 通报对象列表查询/联勤保障单位查询
	 * @param info 输入
	 * @return
	 */
	@Transactional(readOnly = true)
	@Override
	public List<QueryUnitBean> queryUnitList(OrgUnitInfo info) {
		try {
			logService.infoLog(logger, "service", "queryUnitList", "service is started...");
			Long logStart = System.currentTimeMillis();

			if (info == null) {
				throw new AcceptException(AcceptException.AccetpErrors.DATA_NULL);
			}


			logService.infoLog(logger, "service", "getById", "service is started...");
			Long start2 = System.currentTimeMillis();

			IncidentEntity entity=accessor.getById(info.getIncidentId(), IncidentEntity.class);

			Long end2 = System.currentTimeMillis();
			logService.infoLog(logger, "repository", "getById", String.format("repository is finished,execute time is :%sms", end2 - start2));


			if (entity == null) {
				logService.infoLog(logger, "service", "queryUnitList", "incident is null.");
				throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL);
			}

			logService.infoLog(logger, "service", "queryUnitList", "service is started...");
			Long start1 = System.currentTimeMillis();

			List<Object[]> objects = queryUnitRepository.queryUnitList(info.getType());
			Long end1 = System.currentTimeMillis();

			logService.infoLog(logger, "repository", "queryUnitList", String.format("repository is finished,execute time is :%sms", end1 - start1));



			List<QueryUnitBean> list = new ArrayList<QueryUnitBean>();
			for (Object[] objects2 : objects) {
				// 依据经纬度计算两点之间的距离 distance
				double a = Strings.isBlank( FireTransformUtil.toString(objects2[8]) ) ? 0.0d : Double.parseDouble(FireTransformUtil.toString(objects2[8])) ;
				double b =  Strings.isBlank( FireTransformUtil.toString(objects2[9]) ) ? 0.0d :  Double.parseDouble(FireTransformUtil.toString(objects2[9]));

				double c = Strings.isBlank( entity.getLongitude()) ? 0.0d : Double.parseDouble(entity.getLongitude());
				double d = Strings.isBlank( entity.getLatitude() )  ? 0.0d : Double.parseDouble(entity.getLatitude());
				double distance=FireTransformUtil.getDistance( a,b,c ,d);
				//保留两位小数
				DecimalFormat df = new DecimalFormat("#.0");
				String getDistance=String.valueOf(df.format(distance));
				// 内存转换
				QueryUnitBean queryUnitBean = FireTransformUtil.transfrom(objects2,getDistance);
				list.add(queryUnitBean);
			}
			// 按照案发坐标与单位之间距离的排序
			list.sort((o1, o2) -> {
                if (Double.parseDouble(o1.getDistance()) > (Double.parseDouble(o2.getDistance()))) {
                    return 1;
                }
                if (Double.parseDouble(o1.getDistance()) < (Double.parseDouble(o2.getDistance()))) {
                    return -1;
                }
                return 0;
            });

			Long logEnd = System.currentTimeMillis();
			logService.infoLog(logger, "service", "queryUnitList", String.format("service is finished,execute time is :%sms", logEnd - logStart));

			return list;
		} catch (UserInterfaceException ex) {
			if (logger.isErrorEnabled()) {
				logger.error(String.format("query queryUnitList fail by OrgUnitInfo:%s.", info), ex);
			}
			throw ex;
		} catch (Exception ex) {
			if (logger.isErrorEnabled()) {
				logger.error(String.format("query queryUnitList fail by OrgUnitInfo:%s.", info), ex);
			}
			throw new AcceptException(AcceptException.AccetpErrors.OTHER_FAIL);
		}

	}





}