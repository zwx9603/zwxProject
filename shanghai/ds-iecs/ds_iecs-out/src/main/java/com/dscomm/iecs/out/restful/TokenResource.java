package com.dscomm.iecs.out.restful;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.basedata.dal.po.SystemUserEntity;
import com.dscomm.iecs.out.exception.OutException;
import com.dscomm.iecs.out.graphql.typebean.TokenDate;
import com.dscomm.iecs.out.utils.TokenUtils;
import org.apache.logging.log4j.util.Strings;
import org.mx.dal.service.GeneralAccessor;
import org.mx.service.rest.vo.DataVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangsheng
 * @version 1.00
 * @Time 2021/4/21 20:29
 * @Describe 获取token
 */

@Path("user/oauth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TokenResource {

    private static final Logger logger = LoggerFactory.getLogger(TokenResource.class);
    private LogService logService;
    private GeneralAccessor accessor;


    @Autowired
    public TokenResource(LogService logService,
                         @Qualifier("generalAccessor") GeneralAccessor accessor) {

        this.logService = logService;
        this.accessor = accessor;
    }


    /**
     * 生成token
     *
     * @param headers
     * @return
     */
    @Path("token")
    @POST
    public DataVO<TokenDate> getToken(@Context HttpHeaders headers,UserVO userVO
                                      ) {

        logService.infoLog(logger, "restful", "generatorToken", "restful is started...");
        Long start = System.currentTimeMillis();
        if (userVO==null||Strings.isBlank(userVO.getUsername()) || Strings.isBlank(userVO.getPassword())) {
            throw new OutException(OutException.AccetpErrors.LOGIN_FAIL);
        }

        try {
            //创建一个list 存放ConditionGroup
            List<GeneralAccessor.ConditionGroup> conditionGroupList = new ArrayList<>();
            conditionGroupList.add(GeneralAccessor.ConditionTuple.eq("userCode", userVO.getUsername()));//zh
            //筛选列:zh
            GeneralAccessor.ConditionGroup conditionGroup = GeneralAccessor.ConditionGroup.and(conditionGroupList);
            SystemUserEntity systemUserEntity = accessor.findOne(conditionGroup, SystemUserEntity.class);
            if (systemUserEntity == null) {
                logger.error("login fail,can not find user");
                throw new OutException(OutException.AccetpErrors.LOGIN_FAIL);
            }
        } catch (Exception ex) {
            logger.error("login fail",ex);
            throw new OutException(OutException.AccetpErrors.LOGIN_FAIL);
        }

        TokenDate res = TokenUtils.token(userVO.getUsername(), userVO.getPassword());

        // String res = token.getData().getAccess_token();

        Long end = System.currentTimeMillis();
        logService.infoLog(logger, "restful", "generatorToken", String.format("restful is finished,execute time is :%sms", end - start));

        return new DataVO<>(res);
    }


}
