package com.dscomm.iecs.accept.service.impl;

import com.dscomm.iecs.accept.dal.po.GradeEntity;
import com.dscomm.iecs.accept.dal.repository.GradeRepository;
import com.dscomm.iecs.accept.dal.repository.VehicleNumRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.graphql.typebean.VehicleNumBean;
import com.dscomm.iecs.accept.service.VehicleNumService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component("vehicleNumServiceImpl")
public class VehicleNumServiceImpl implements VehicleNumService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleNumServiceImpl.class);
    private LogService logService;
    private VehicleNumRepository vehicleNumRepository;
    private DictionaryService dictionaryService;
    private GradeRepository gradeRepository;
    private List<String> dics;
    @Autowired
    public VehicleNumServiceImpl(LogService logService,VehicleNumRepository vehicleNumRepository ,DictionaryService dictionaryService,
                                 GradeRepository gradeRepository){
        this.logService = logService;
        this.vehicleNumRepository = vehicleNumRepository;
        this.dictionaryService = dictionaryService;
        this.gradeRepository = gradeRepository;
        dics = new ArrayList<>(Arrays.asList( "WLCLLX" ));
    }

    @Override
    public List<VehicleNumBean> findVehicleNumInfo(String id,String czdx,String ajdj,String ajlx) {
        try {
            logService.infoLog(logger, "service", "queryVehicleNumInfo", "service is started...");
            Long logStart = System.currentTimeMillis();
            Map<String, Map<String, String>>  dicsMap = dictionaryService.findDictionaryMap(dics);
            List<VehicleNumBean> list = new ArrayList();
            // 调用持久层
            // 应派车辆数
            GradeEntity result = gradeRepository.queryInfo(czdx, ajdj, ajlx);
            String cllx = result.getCllx(); // 车辆类型
            String sl = result.getSl(); // 车辆数量
            String[] split1 = cllx.split(",");
            String[] split2 = sl.split(",");
            for (int i = 0;i<split1.length;i++){
                String str1 = split1[i]; // 车辆类型
                String str2 = split2[i]; // 车辆数量
                // 参战车辆数
                int backNum = vehicleNumRepository.queryVehicleNumInfo(id,str1);
                VehicleNumBean result1 = new VehicleNumBean();
                result1.setCarTypeName(dicsMap.get( "WLCLLX").get(str1));
                result1.setCarNum(str2);
                result1.setCzNum(String.valueOf(backNum));
                result1.setDpNum(String.valueOf(Integer.valueOf(str2) -Integer.valueOf(backNum)));
                list.add(result1);
            }
            return list;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "queryVehicleNumInfo", "execution error", ex);
            throw new AcceptException(AcceptException.AccetpErrors.FIND_DATA_FAIL);
        }
    }
}
