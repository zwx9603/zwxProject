package com.dscomm.iecs.accept.service.pushData;

import com.dscomm.iecs.accept.graphql.typebean.HandleBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentBean;
import com.dscomm.iecs.accept.graphql.typebean.IncidentStatusChangeBean;
import com.dscomm.iecs.accept.graphql.typebean.InstructionBean;
import com.dscomm.iecs.accept.service.impl.LocaleServiceImpl;
import com.dscomm.iecs.base.service.log.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Component("mobileService")
public class MobileServiceImpl implements MobileService {


    private static final Logger logger = LoggerFactory.getLogger(LocaleServiceImpl.class);
    private LogService logService;
    private Environment env;
    private MobileChangeService mobileChangeService  ;


    @Autowired
    public MobileServiceImpl(  LogService logService , Environment env ,
                               MobileChangeService mobileChangeService   )  {
        this.logService = logService ;
        this.env = env;
        this.mobileChangeService = mobileChangeService ;
    }


    @Transactional
    @Override
    public void dispatchVehicle(IncidentBean incidentBean,  HandleBean handleBean )  {

        long logStart = System.currentTimeMillis();
        logger.info("start thread to push  MobileMessage");

        mobileChangeService.dispatchVehicle( incidentBean , handleBean );

        long logEnd = System.currentTimeMillis();
        logger.info(String.format("end thread to push  MobileMessage ,totalTime:%sms", logEnd - logStart));

    }


    @Transactional
    @Override
    public void instruction (String incidentId , List<String> receivingObjectIds ,
                             InstructionBean instructionBean )    {

        long logStart = System.currentTimeMillis();
        logger.info("start thread to push  MobileMessage");

        mobileChangeService.instruction( incidentId , receivingObjectIds ,instructionBean  );

        long logEnd = System.currentTimeMillis();
        logger.info(String.format("end thread to push  MobileMessage ,totalTime:%sms", logEnd - logStart));

    }



    @Transactional
    @Override
    public  void modifyCase (IncidentBean incidentBean  )    {

        long logStart = System.currentTimeMillis();
        logger.info("start thread to push  MobileMessage");

        mobileChangeService.modifyCase( incidentBean    );

        long logEnd = System.currentTimeMillis();
        logger.info(String.format("end thread to push  MobileMessage ,totalTime:%sms", logEnd - logStart));

    }





    @Transactional
    @Override
    public void caseStat( IncidentBean incidentBean  ,
                          IncidentStatusChangeBean incidentStatusChangeBean   )   {

        long logStart = System.currentTimeMillis();
        logger.info("start thread to push  MobileMessage");

        mobileChangeService.caseStat( incidentBean  ,incidentStatusChangeBean  );

        long logEnd = System.currentTimeMillis();
        logger.info(String.format("end thread to push  MobileMessage ,totalTime:%sms", logEnd - logStart));

    }




    @Transactional
    @Override
    public   void carStatus(  String incidentId , String handleId ,   String  vehicleId  ,
                              String vehicleStatusCode     ) {

        long logStart = System.currentTimeMillis();
        logger.info("start thread to push  MobileMessage");

        mobileChangeService.carStatus( incidentId  , handleId  ,  vehicleId  , vehicleStatusCode );

        long logEnd = System.currentTimeMillis();
        logger.info(String.format("end thread to push  MobileMessage ,totalTime:%sms", logEnd - logStart));

    }

    @Override
    public void carStatus(String incidentId, String handleId, List<String> vehicleIds, String vehicleStatusCode) {
        long logStart = System.currentTimeMillis();
        logger.info("start thread to push  MobileMessage");

        mobileChangeService.carStatus( incidentId  , handleId  ,  vehicleIds  , vehicleStatusCode );

        long logEnd = System.currentTimeMillis();
        logger.info(String.format("end thread to push  MobileMessage ,totalTime:%sms", logEnd - logStart));

    }
}
