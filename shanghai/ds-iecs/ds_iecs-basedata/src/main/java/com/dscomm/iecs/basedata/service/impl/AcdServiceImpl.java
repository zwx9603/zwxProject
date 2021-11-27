package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.AcdEntity;
import com.dscomm.iecs.basedata.dal.po.AgentAcdEntity;
import com.dscomm.iecs.basedata.dal.po.UserAcdEntity;
import com.dscomm.iecs.basedata.dal.repository.AcdRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.typebean.AcdBean;
import com.dscomm.iecs.basedata.graphql.typebean.AgentAcdBean;
import com.dscomm.iecs.basedata.graphql.typebean.UserAcdBean;
import com.dscomm.iecs.basedata.service.AcdService;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:acd 组
 *
 * @author YangFuxi
 * Date Time 2019/8/9 9:40
 */
@Component("acdServiceImpl")
public class AcdServiceImpl implements AcdService {
    private static final Logger logger = LoggerFactory.getLogger(AcdServiceImpl.class);
    private LogService logService;
    private AcdRepository acdRepository ;


    @Autowired
    public AcdServiceImpl(LogService logService , AcdRepository acdRepository  ) {
        this.logService = logService;
        this.acdRepository = acdRepository ;

    }



    /**
     * {@inheritDoc}
     *
     * @see #findAllAcd()
     */
    @Transactional(readOnly = true)
    @Override
    public List<AcdBean> findAllAcd() {
        try {
            logService.infoLog(logger, "repository", "findAllAcdPO()", "repository is started...");
            Long start = System.currentTimeMillis();

            List<AcdEntity> zhPos = acdRepository.findAllAcdPO();

            Long end = System.currentTimeMillis();
            logService.infoLog(logger, "repository", "findAllAcdPO()", String.format("repository is finished,execute time is :%sms", end - start));

            List<AcdBean> beans = new ArrayList<>();

            if (zhPos != null && !zhPos.isEmpty()) {
                for (AcdEntity entity : zhPos) {
                    AcdBean bean = transform(entity);
                    if (null != bean) {
//                        bean.setDescribe(I18nMessageUtils.getI18nMessage("t_tyqx_ywzdx.mc." + bean.getId(), bean.getDescribe()));
                        beans.add(bean);
                    }
                }
            }
            return beans;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllAcd", "execution error", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_ACD_FAIL);
        }
    }


    /**
     * acd转换
     *
     * @param source acd PO
     * @return acd BO
     */
    public static AcdBean transform(AcdEntity source) {
        if (null != source) {
            AcdBean target = new AcdBean();
            target.setId(source.getId());
            target.setNumber( source.getNumber() );
            target.setAcd(source.getAcd());
            target.setDescribe(source.getDescribe());
            target.setPoliceType(source.getPoliceType());
            target.setCallType(source.getCallType());
            target.setAlarmNumber(source.getAlarmNumber());
            target.setSjc(source.getSjc());
            target.setOrgCode(source.getOrgCode());
            return target;
        }
        return null;
    }





    /**
     * {@inheritDoc}
     *
     * @see #findUserAcdBO(String)
     */
    @Transactional(readOnly = true)
    @Override
    public List<UserAcdBean> findUserAcdBO(String userId){
        try {
            logService.infoLog(logger, "service", "findUserAcdBO", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<UserAcdBean> userAcdBOList = new ArrayList<>();
            List<UserAcdEntity> userAcdPOList ;
            if (!StringUtils.isBlank(userId)) {
                logService.infoLog(logger, "repository", "findUserAcdPOByUserId(userId)", "repository is started...");
                Long start = System.currentTimeMillis();
                userAcdPOList = acdRepository.findUserAcdPOByUserId(userId);
                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findUserAcdPOByUserId(userId)", String.format("repository is finished,execute time is :%s ms", end - start));
            } else {
                logService.infoLog(logger, "repository", "findAllUserAcdPO()", "repository is started...");
                Long start = System.currentTimeMillis();
                userAcdPOList = acdRepository.findAllUserAcdPO();
                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findAllUserAcdPO()", String.format("repository is finished,execute time is :%s ms", end - start));
            }

            if (null != userAcdPOList && userAcdPOList.size() > 0) {
                for (UserAcdEntity userAcdPO : userAcdPOList) {
                    UserAcdBean userAcdBO = transform(userAcdPO);
                    if (null != userAcdBO) {
                        userAcdBOList.add(userAcdBO);
                    }
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findUserAcdBO", String.format("service is finished,execute time is :%s ms", logEnd - logStart));

            return userAcdBOList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findUserAcdBO", "find userAcdBO fail.", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_USERACD_FAIL);
        }
    }


    /**
     * 用户acd转换
     *
     * @param source 用户acd PO
     * @return 用户acd BO
     */
    public static UserAcdBean transform(UserAcdEntity source) {
        if (null != source) {
            UserAcdBean target = new UserAcdBean();
            target.setId(source.getId());
            target.setUserId(source.getUserId());
            target.setAcd(source.getAcd());
            target.setSkillLevel(source.getSkillLevel());
            target.setSjc(source.getSjc());

            return target;
        }
        return null;
    }



    /**
     * {@inheritDoc}
     *
     * @see #findAgentAcdBO(String)
     */
    @Transactional(readOnly = true)
    @Override
    public  List<AgentAcdBean> findAgentAcdBO(String agentNumber) {
        try {
            logService.infoLog(logger, "service", "findAgentAcdBO", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<AgentAcdBean> agentAcdBOList = new ArrayList<>();
            List<AgentAcdEntity> agentAcdPOList;

            if (Strings.isNotBlank( agentNumber )) {
                logService.infoLog(logger, "repository", "findAgentAcdPOByAgentNumber(agentNumber)", "repository is started...");
                Long start = System.currentTimeMillis();
                agentAcdPOList = acdRepository.findAgentAcdPOByAgentNumber(agentNumber);
                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findAgentAcdPOByAgentNumber(agentNumber)", String.format("repository is finished,execute time is :%s ms", end - start));
            } else {
                logService.infoLog(logger, "repository", "findAllAgentAcdPO()", "repository is started...");
                Long start = System.currentTimeMillis();
                agentAcdPOList = acdRepository.findAllAgentAcdPO();
                Long end = System.currentTimeMillis();
                logService.infoLog(logger, "repository", "findAllAgentAcdPO()", String.format("repository is finished,execute time is :%s ms", end - start));
            }

            if (null != agentAcdPOList && agentAcdPOList.size() > 0) {
                for (AgentAcdEntity agentAcdPO : agentAcdPOList) {
                    if(!StringUtils.isBlank(agentAcdPO.getAcd())) {
                        String[] acdList = agentAcdPO.getAcd().split(",");//以逗号分割
                        for (String acd : acdList) {
                            agentAcdPO.setAcd(acd);
                            AgentAcdBean agentAcdBO = transform(agentAcdPO);
                            if (null != agentAcdBO) {
                                agentAcdBOList.add(agentAcdBO);
                            }
                        }
                    }
                    else {
                        AgentAcdBean agentAcdBO = transform(agentAcdPO);
                        if (null != agentAcdBO) {
                            agentAcdBOList.add(agentAcdBO);
                        }
                    }
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAgentAcdBO", String.format("service is finished,execute time is :%s ms", logEnd - logStart));

            return agentAcdBOList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAgentAcdBO", "find agentAcdBO fail.", ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_AGENTACD_FAIL);
        }
    }


    /**
     * 坐席acd转换
     *
     * @param source 坐席acd PO
     * @return 坐席acd BO
     */
    public static AgentAcdBean transform(AgentAcdEntity source) {
        if (null != source) {
            AgentAcdBean target = new AgentAcdBean();

            target.setId(source.getId());
            target.setAgentNumber(source.getAgentNumber());
            target.setAcd(source.getAcd());
            target.setSkillLevel(source.getSkillLevel());
            target.setSjc(source.getSjc());

            return target;
        }
        return null;
    }






}
