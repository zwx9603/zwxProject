package com.dscomm.iecs.accept.service.outside;

import com.dscomm.iecs.accept.dal.po.UdpMessageEntity;
import com.dscomm.iecs.accept.dal.po.UnTrafficAlarmEntity;
import com.dscomm.iecs.accept.dal.repository.UnTrafficAlarmRepository;
import com.dscomm.iecs.accept.exception.AcceptException;
import com.dscomm.iecs.accept.restful.vo.UdpVO.UdpHelpVO;
import com.dscomm.iecs.accept.restful.vo.UdpVO.UdpMessageTypeVO;
import com.dscomm.iecs.agent.enums.RoleEnum;
import com.dscomm.iecs.base.websocket.WebsocketCodeEnum;
import com.dscomm.iecs.base.service.NotifyActionService;
import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.service.OrganizationService;
import com.dscomm.iecs.keydata.service.ServletService;
import org.mx.dal.service.GeneralAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("outSideOtherServiceImpl")
public class OutSideOtherServiceImpl implements   OutSideOtherService  {

    private static final Logger logger = LoggerFactory.getLogger(OutsideChangeServiceImpl.class);
    private Environment env;
    private LogService logService;
    private GeneralAccessor accessor;
    private OrganizationService organizationService;
    private NotifyActionService notifyActionService ;
    private UnTrafficAlarmRepository unTrafficAlarmRepository ;
    private ServletService servletService ;

    /**
     * 默认的构造函数
     */
    @Autowired
    public OutSideOtherServiceImpl(LogService logService, @Qualifier("generalAccessor") GeneralAccessor accessor,
                                   Environment env ,OrganizationService organizationService , NotifyActionService notifyActionService ,
                                   UnTrafficAlarmRepository unTrafficAlarmRepository ,  ServletService servletService

    ) {
        this.env = env;
        this.accessor = accessor;
        this.logService = logService;
        this.organizationService = organizationService;
        this.notifyActionService = notifyActionService ;
        this.unTrafficAlarmRepository = unTrafficAlarmRepository ;
        this.servletService = servletService ;
    }


    /**
     *  接收udp 消息
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public Boolean analyse( UdpMessageTypeVO udpMessage  ) {
           try {
            if ( udpMessage != null){
                //非心跳报文需要存入数据库
                UdpMessageEntity entity = new UdpMessageEntity();
                entity.setMsgType(udpMessage.getMsgType());
                entity.setMsgReceiver(udpMessage.getMsgReceiver());
                entity.setMsgSender(udpMessage.getMsgReceiver());
                entity.setMsgSendTime( servletService.getSystemTime() );
                entity.setMsgContent(udpMessage.getMsgContent());
                accessor.save(entity);
            }
        } catch (Exception ex) {
               logService.erorLog( logger, "service", "transformHelp", " transformHelp message" , ex );
        }
        return  false ;
    }


    /**
     *  公安请求协助
     */
    @Transactional( rollbackFor =  Exception.class )
    @Override
    public Boolean  udpHelp(    UdpHelpVO udpHelp    ) {

        try {
            if( udpHelp != null ){
                String incidentId = udpHelp.getMsgContent().getJJDBH();
                List<UnTrafficAlarmEntity> entities = unTrafficAlarmRepository.findUnTrafficAlarmByOriginalIncidentNumber(incidentId);
                if( entities != null && entities.size() > 0 ){
                    String helpMessage = entities.get(0).getCrimeAddress()+"发生灾情，请求协助";

                    Set<String> orgIdCodeSet = new HashSet<>();
                    // 获取机构id集合
                    Set<String> orgIdSet = new HashSet<>();
                    orgIdCodeSet.add( organizationService.getRootOrgId() ) ;
                    List<String> orgIds = new ArrayList<>(orgIdSet) ;
                    orgIdCodeSet.addAll( orgIds ) ;
                    //根据机构ids获取机构编码
                    List<String> orgCodes = organizationService.findOrganizationCodesByIds(orgIds) ;
                    orgIdCodeSet.addAll( orgCodes ) ;
                    //接警角色
                    Set<String> roles = new HashSet<>() ;
                    roles.add( String.valueOf(RoleEnum.AGENT_PERSONROLE_CALLTAKING_SUPERVISOR.getCode() ) ) ; //接警班长
                    notifyActionService.pushMessageToDefaultSystemBusinessOrgRole( WebsocketCodeEnum.POLICE_REINFORCEMENT_ASK.getCode() ,
                            helpMessage   ,orgIdCodeSet , roles );
                }else{
                    logService.erorLog(logger, "service", "udpHelp", "udpHelp is fail " ,
                            new AcceptException(AcceptException.AccetpErrors.FIND_DATA_NULL));
                }
            }
        }catch (Exception ex){
            logService.erorLog( logger, "service", "transformHelp", " transformHelp message" , ex );
        }
        return  false ;

    }


}
