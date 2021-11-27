package com.dscomm.iecs.basedata.service.impl;

import com.dscomm.iecs.base.service.log.LogService;
import com.dscomm.iecs.base.service.tree.TreeUtil;
import com.dscomm.iecs.basedata.dal.po.DictionaryEntity;
import com.dscomm.iecs.basedata.dal.repository.DictionaryRepository;
import com.dscomm.iecs.basedata.exception.BasedataException;
import com.dscomm.iecs.basedata.graphql.typebean.DictionaryBean;
import com.dscomm.iecs.basedata.graphql.typebean.DictionaryKeyBean;
import com.dscomm.iecs.basedata.service.DictionaryCacheService;
import com.dscomm.iecs.basedata.service.DictionaryService;
import com.dscomm.iecs.basedata.utils.DictionaryTransformUtil;
import org.apache.logging.log4j.util.Strings;
import org.mx.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 描述：字典服务类实现
 */
@Component("dictionaryServiceImpl")
public class DictionaryServiceImpl implements DictionaryService {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryServiceImpl.class);
    private LogService logService;
    private Environment env;
    private DictionaryRepository dictionaryRepository;
    private DictionaryCacheService dictionaryCacheService;

    //设置字典数据缓存数据
//    private static  Map<String,Map<String,String>> dicKeyValueMap = new HashMap<>();
//    private static  Map<String,List<DictionaryBean>> dicBeanMap = new HashMap<>();

    //全部字典key 与 数据库 字典类型对应关系
    private Map<String, String> dicKeyMap = new HashMap<>();
    //前端使用字典可以
    private List<String> dicKeys = new ArrayList<>();

    /**
     * 默认的构造函数
     */
    @Autowired
    public DictionaryServiceImpl(LogService logService, Environment env,
                                 DictionaryRepository dictionaryRepository,
                                 DictionaryCacheService dictionaryCacheService) {
        this.logService = logService;
        this.env = env;
        this.dictionaryRepository = dictionaryRepository;
        this.dictionaryCacheService = dictionaryCacheService;
    }


    private Long lastTime = 0l; //系统默认为0 加载全部

    /**
     * 根据上次数据最新时间  本次数据最新时间
     * 判断是否从数据库更新数据 如果本次数据最新时间 大于 上次数据时间 则需要更新数据
     */
    private void updateCacheDictionary() {
        logService.infoLog(logger, "service", "UpdateCacheDictionary", "service is started...");
        Long logStart = System.currentTimeMillis();

        Long latestTime = dictionaryRepository.findDataLatestTime(lastTime);
        latestTime = latestTime == null ? lastTime : latestTime;
        //判断是否需要更新数据
        if (latestTime > lastTime) {

            logService.infoLog(logger, "service", "UpdateCacheDictionary", " update cache data");
            //此处为全量更新数据
            //获得全部的字典类型编码
            if (null != dicKeyMap && dicKeyMap.size() > 0) {

                for (String key : dicKeyMap.keySet()) {

                    List<DictionaryBean> dictionaryBeans = new ArrayList<>();
                    //key value 数据缓存
                    String type = dicKeyMap.get(key);
                    Map<String, String> dicKeyValue = new HashMap<>();
                    List<DictionaryEntity> dictionarys = dictionaryRepository.findDictionaryByType(type);
                    if (dictionarys != null && dictionarys.size() > 0) {
                        for (DictionaryEntity en : dictionarys) {
                            DictionaryBean bean = DictionaryTransformUtil.transform(en);

                            dictionaryBeans.add(bean);

                            //添加缓存key value 数据缓存
                            dicKeyValue.put(en.getCode(), en.getName());
                        }
                    }
                    //添加缓存数据
                    dictionaryCacheService.modifyDicBeanCache(key, dictionaryBeans);
                    dictionaryCacheService.modifyDickeyValueCache(key, dicKeyValue);
                }
            }

            lastTime = latestTime;
        }

        Long logEnd = System.currentTimeMillis();
        logService.infoLog(logger, "service", "UpdateCacheSystemConfiguration", String.format("service is finished,execute time is :%sms", logEnd - logStart));


    }


    private void buildDictionaryMap() {

        dicKeyMap.put("XZQX", env.getProperty("XZQX", "")); //行政区划
        dicKeyMap.put("JLDW", env.getProperty("JLDW", "")); //计量单位
        dicKeyMap.put("YS", env.getProperty("YS", "")); //颜色


        dicKeyMap.put("XB", env.getProperty("XB", "")); //性别
        dicKeyMap.put("ZJLX", env.getProperty("ZJLX", "")); //证件类型
        dicKeyMap.put("MZ", env.getProperty("MZ", "")); //民族
        dicKeyMap.put("JG", env.getProperty("JG", "")); //籍贯
        dicKeyMap.put("ZZMM", env.getProperty("ZZMM", "")); //政治面貌
        dicKeyMap.put("XL", env.getProperty("XL", "")); //学历
        dicKeyMap.put("XW", env.getProperty("XW", "")); //学位
        dicKeyMap.put("ZJLY", env.getProperty("ZJLY", "")); //专家领域类别代码
        dicKeyMap.put("HYZK", env.getProperty("HYZK", "")); //婚姻状况
        dicKeyMap.put("RYLB", env.getProperty("RYLB", "")); //人员类别代码
        dicKeyMap.put("RYZK", env.getProperty("RYZK", "")); //人员状态代码
        dicKeyMap.put("RYZWQK", env.getProperty("RYZWQK", "")); //人员在位情况代码
        dicKeyMap.put("GW", env.getProperty("GW", "")); //消防岗位分类与代码
        dicKeyMap.put("ZW", env.getProperty("ZW", "")); // 职位
        dicKeyMap.put("ZYJSZW", env.getProperty("ZYJSZW", "")); //专业技术职务类别代码
        dicKeyMap.put("JX", env.getProperty("JX", "")); //警衔
        dicKeyMap.put("ZWQK", env.getProperty("ZWQK", "")); //在位情况代码

        dicKeyMap.put("ZBLXXX", env.getProperty("ZBLXXX", "")); //值班类型信息
        dicKeyMap.put("ZBGWXX", env.getProperty("ZBGWXX", "")); //值班岗位信息

        dicKeyMap.put("JGLB", env.getProperty("JGLB", "")); //机构类别
        dicKeyMap.put("JGLX", env.getProperty("JGLX", "")); //机构类型
        dicKeyMap.put("JGXZ", env.getProperty("JGXZ", "")); //机构性质

        dicKeyMap.put("WLCLLX", env.getProperty("WLCLLX", "")); //车辆类型
        dicKeyMap.put("WLCLDJ", env.getProperty("WLCLDJ", "")); //车辆等级
        dicKeyMap.put("WLCLZT", env.getProperty("WLCLZT", "")); //车辆状态
        dicKeyMap.put("WLZZGN", env.getProperty("WLZZGN", "")); //车辆功能


        dicKeyMap.put("ZBLX", env.getProperty("ZBLX", "")); //装备类型
        dicKeyMap.put("ZBZT", env.getProperty("ZBZT", "")); //装备状态
        dicKeyMap.put("QCLX", env.getProperty("QCLX", "")); //器材类型
        dicKeyMap.put("PMLX", env.getProperty("PMLX", "")); //泡沫类型


        dicKeyMap.put("DWDJ", env.getProperty("DWDJ", "")); //单位等级
        dicKeyMap.put("DWXZ", env.getProperty("DWXZ", "")); //单位性质
        dicKeyMap.put("DWLX", env.getProperty("DWLX", "")); //单位类型

        dicKeyMap.put("YJLDDWLBD", env.getProperty("YJLDDWLBD", "")); //应急单位类型
        dicKeyMap.put("LQBZLB", env.getProperty("LQBZLB", "")); //联勤保障类别
        dicKeyMap.put("YYDJ", env.getProperty("YYDJ", "")); //医院等级代码

        dicKeyMap.put("ZDDWFL", env.getProperty("ZDDWFL", "")); //重点单位分类
        dicKeyMap.put("ZDDWLB", env.getProperty("ZDDWLB", "")); //重点单位类别
        dicKeyMap.put("ZDDWLX", env.getProperty("ZDDWLX", "")); //重点单位类型
        dicKeyMap.put("JJSYZ", env.getProperty("JJSYZ", ""));//重点单位 经济所有制类型
        dicKeyMap.put("HZWHXFL", env.getProperty("HZWHXFL", ""));//重点单位 火灾危害性分类

        dicKeyMap.put("JZJGLX", env.getProperty("JZJGLX", ""));//重点单位 重点部位 建筑结构类型
        dicKeyMap.put("JZWSYXZ", env.getProperty("JZWSYXZ", ""));//重点单位 重点部位 建筑物使用性质
        dicKeyMap.put("JZWNHDJ", env.getProperty("JZWNHDJ", ""));//重点单位 重点部位 建筑物耐火等级

        dicKeyMap.put("HXPZTLB", env.getProperty("HXPZTLB", ""));//重点单位 危化品 化学品状态类别
        dicKeyMap.put("HXPWXXLB", env.getProperty("HXPWXXLB", ""));//重点单位 危化品 化学品危险性类别
        dicKeyMap.put("WXHXPFL", env.getProperty("WXHXPFL", ""));//重点单位 危化品 危险化学品分类

        dicKeyMap.put("SPJKLX", env.getProperty("SPJKLX", ""));//视频监控类型
        dicKeyMap.put("SPJRFS", env.getProperty("SPJRFS", ""));//视频接入方式
        dicKeyMap.put("SPQXD", env.getProperty("SPQXD", ""));//视频清晰度类型

        dicKeyMap.put("BQLX", env.getProperty("BQLX", "")); //标签类型
        dicKeyMap.put("BQMX", env.getProperty("BQMX", "")); //标签明细

        dicKeyMap.put("HJFX", env.getProperty("HJFX", "")); //呼叫方向

        dicKeyMap.put("AJDJ", env.getProperty("AJDJ", "")); //案件等级
        dicKeyMap.put("AJLX", env.getProperty("AJLX", "")); //案件类型
        dicKeyMap.put("AJLXZX", env.getProperty("AJLXZX", "")); //案件类型子项
        dicKeyMap.put("AJZT", env.getProperty("AJZT", "")); //案件状态
        dicKeyMap.put("AJXZ", env.getProperty("AJXZ", "")); //案件性质
        dicKeyMap.put("BJFS", env.getProperty("BJFS", "")); //报警方式
        dicKeyMap.put("CZDX", env.getProperty("CZDX", "")); //处置对象
        dicKeyMap.put("JZJG", env.getProperty("JZJG", "")); //建筑结构
        dicKeyMap.put("YWQK", env.getProperty("YWQK", "")); //烟雾情况
        dicKeyMap.put("LAFS", env.getProperty("LAFS", "")); //立案方式
        dicKeyMap.put("CLLX", env.getProperty("CLLX", "")); //处置类型
        dicKeyMap.put("ZHCS", env.getProperty("ZHCS", "")); //灾害场所
        dicKeyMap.put("JQBQ", env.getProperty("JQBQ", "")); //警情标签
        dicKeyMap.put("JQDX", env.getProperty("JQDX", "")); //警情对象


        dicKeyMap.put("YAZL", env.getProperty("YAZL", "")); //预案分类
        dicKeyMap.put("YALX", env.getProperty("YALX", "")); //预案类型
        dicKeyMap.put("YAZT", env.getProperty("YAZT", "")); //预案状态
        dicKeyMap.put("YADXLX", env.getProperty("YADXLX", "")); //预案状态

        dicKeyMap.put("ZLZT", env.getProperty("ZLZT", "")); //指令状态
        dicKeyMap.put("ZLLX", env.getProperty("ZLLX", "")); //指令类型
        dicKeyMap.put("ZLLB", env.getProperty("ZLLB", "")); //指令类别
        dicKeyMap.put("ZLDP", env.getProperty("ZLDP", "")); //指令调派

        dicKeyMap.put("TQQX", env.getProperty("TQQX", "")); //天气气象
        dicKeyMap.put("FLDJ", env.getProperty("FLDJ", "")); //风力等级
        dicKeyMap.put("FX", env.getProperty("FX", "")); //风向

        dicKeyMap.put("CLCKGXLX", env.getProperty("CLCKGXLX", "")); //车辆对应关系类型
        dicKeyMap.put("CKMH", env.getProperty("CKMH", "")); //车库门号
        dicKeyMap.put("CAD", env.getProperty("CAD", "")); //CAD组
        dicKeyMap.put("ROOM", env.getProperty("ROOM", "")); //CAD 调度室信息

        dicKeyMap.put("QQXZLX", env.getProperty("QQXZLX", "")); //请求协助类型

        dicKeyMap.put("WGLX", env.getProperty("WGLX", "")); //违规类型

        dicKeyMap.put("HMDSZSL", env.getProperty("HMDSZSL", "")); //黑名单设置时长
        dicKeyMap.put("ZBCYC", env.getProperty("ZBCYC", "")); //装备常用词

        dicKeyMap.put("WSLX", env.getProperty("WSLX", "")); //文书类型

        dicKeyMap.put("SYLX", env.getProperty("SYLX", ""));//水源类型
        dicKeyMap.put("XHSLX", env.getProperty("XHSLX", ""));//消防栓类型
        dicKeyMap.put("JKXS", env.getProperty("JKXS", ""));//接口类型
        dicKeyMap.put("GWXS", env.getProperty("GWXS", ""));//管网形式
        dicKeyMap.put("QSXS", env.getProperty("QSXS", ""));//取水形式
        dicKeyMap.put("FZXS", env.getProperty("FZXS", ""));//放置形式
        dicKeyMap.put("KYZT", env.getProperty("KYZT", ""));//可用状态
        dicKeyMap.put("SYZT", env.getProperty("SYZT", ""));//水源状态
        dicKeyMap.put("TRSYLX", env.getProperty("TRSYLX", ""));//天然水源类型

        dicKeyMap.put("FJLX", env.getProperty("FJLX", "")); //电子文件类型 附件类型
        dicKeyMap.put("TPGSLX", env.getProperty("TPGSLX", "")); //图片格式类型

        dicKeyMap.put("TXLX", env.getProperty("TXLX", "")); // 提醒类型

        dicKeyMap.put("WZZT", env.getProperty("WZZT", "")); // 微站状态
        dicKeyMap.put("WZZBLX", env.getProperty("WZZBLX", "")); // 微站值班类型
        dicKeyMap.put("WZDPZT", env.getProperty("WZDPZT", "")); // 微站调派状态

        dicKeyMap.put("DPFS", env.getProperty("DPFS", "")); // 调派方式
        dicKeyMap.put("DPDXLX", env.getProperty("DPDXLX", "")); // 调派对象类型

        dicKeyMap.put("WLRYZW", env.getProperty("WLRYZW", "")); // 车辆人员职务
        dicKeyMap.put("WLCLXZ", env.getProperty("WLCLXZ", "")); // 车辆性质 主战 支援等

        //前端 页面使用key
        String keyCode = env.getProperty("dicKey");
        if (Strings.isNotBlank(keyCode)) {
            String[] keys = keyCode.split(",");
            dicKeys = Arrays.asList(keys);
            if (dicKeys!=null&&!dicKeys.isEmpty()){
                dicKeys.forEach(key->{
                    try {
                        if (!StringUtils.isBlank(key)&&!dicKeyMap.containsKey(key)){
                            dicKeyMap.put(key, env.getProperty(key, "")); // 新增扩展字典
                        }
                    }catch (Exception ex){
                        logger.error(String.format("add dictionary fail,key:%s",key),ex);
                    }
                });
            }
        } else {
            dicKeys = new ArrayList<>(dicKeyMap.keySet());
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see DictionaryService#forceUpdateCacheDictionary()
     */
    @Transactional(readOnly = true)
    @Override
    public void forceUpdateCacheDictionary() {
        try {
            logService.infoLog(logger, "service", "forceUpdateCacheDictionary", "service is started...");
            Long logStart = System.currentTimeMillis();

            buildDictionaryMap();

            updateCacheDictionary();

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "forceUpdateCacheDictionary", String.format("service is finished,execute time is :%sms", logEnd - logStart));


        } catch (Exception ex) {
            logService.erorLog(logger, "service", "forceCacheDictionary", String.format("force cache Dictionary fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DICTIONARY_FAIL);
        }
    }


    /**
     * {@inheritDoc}
     *
     * @see com.dscomm.iecs.basedata.service.DictionaryService#findGridDictionary(String, Boolean)
     */
    @Transactional(readOnly = true)
    @Override
    public List<DictionaryBean> findGridDictionary(String type, Boolean whetherSearch) {

        if (Strings.isBlank(type)) {
            logService.infoLog(logger, "service", "findGridDictionary", "type is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {

            logService.infoLog(logger, "service", "findGridDictionary", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<DictionaryBean> dictionaryList = dictionaryCacheService.findDicBeanCache(type);
            if (dictionaryList == null) {
                dictionaryList = new ArrayList<>();
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findGridDictionary", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return dictionaryList;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findGridDictionary", String.format("grid dictionary fail from type: %s.", type), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DICTIONARY_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see DictionaryService#findTreeDictionary(String, Boolean)
     */
    @Transactional(readOnly = true)
    @Override
    public List<DictionaryBean> findTreeDictionary(String type, Boolean whetherSearch) {

        if (Strings.isBlank(type)) {
            logService.infoLog(logger, "service", "findTreeDictionary", "type is blank.");
            throw new BasedataException(BasedataException.BasedataErrors.DATA_NULL);
        }
        try {

            logService.infoLog(logger, "service", "findTreeDictionary", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<DictionaryBean> dictionaryBeans = dictionaryCacheService.findDicBeanCache(type);
            if (dictionaryBeans == null) {
                dictionaryBeans = new ArrayList<>();
            }
            List<DictionaryBean> temp = new ArrayList<>();
            for (DictionaryBean dictionaryBean : dictionaryBeans) {
                DictionaryBean bean = DictionaryTransformUtil.transform(dictionaryBean);
                temp.add(bean);
            }

            List<DictionaryBean> dictionaryTree = TreeUtil.bulidTree(temp);
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findTreeDictionary", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return dictionaryTree;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findTreeDictionary", String.format("tree dictionary fail from type: %s.", type), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DICTIONARY_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see DictionaryService#findAllDictionary()
     */
    @Transactional(readOnly = true)
    @Override
    public List<DictionaryKeyBean> findAllDictionary() {
        try {

            logService.infoLog(logger, "service", "findAllDictionary", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<DictionaryKeyBean> dicKeyBeans = new ArrayList<>();

            if (null != dicKeys && dicKeys.size() > 0) {
                for (String dicKey : dicKeys) {
                    DictionaryKeyBean dictionaryKeyBean = new DictionaryKeyBean();
                    dictionaryKeyBean.setKey(dicKey);
                    List<DictionaryBean> list = dictionaryCacheService.findDicBeanCache(dicKey);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    dictionaryKeyBean.setDictionaryBeanList(list);
                    dicKeyBeans.add(dictionaryKeyBean);
                }
            }
            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findAllDictionary", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return dicKeyBeans;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findAllDictionary", String.format("map dictionary key beans fail from type: %s.", ""), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DICTIONARY_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see DictionaryService#findDictionaryType(List)
     */
    @Transactional(readOnly = true)
    @Override
    public List<DictionaryKeyBean> findDictionaryType(List<String> dicsType) {
        try {

            logService.infoLog(logger, "service", "findDictionaryType", "service is started...");
            Long logStart = System.currentTimeMillis();

            List<DictionaryKeyBean> dicKeyBeans = new ArrayList<>();

            if (null != dicsType && dicsType.size() > 0) {
                for (String dicKey : dicsType) {
                    DictionaryKeyBean dictionaryKeyBean = new DictionaryKeyBean();
                    dictionaryKeyBean.setKey(dicKey);
                    List<DictionaryBean> list = dictionaryCacheService.findDicBeanCache(dicKey);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    dictionaryKeyBean.setDictionaryBeanList(list);
                    dicKeyBeans.add(dictionaryKeyBean);
                }
            }

            Long logEnd = System.currentTimeMillis();
            logService.infoLog(logger, "service", "findDictionaryType", String.format("service is finished,execute time is :%sms", logEnd - logStart));

            return dicKeyBeans;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findDictionaryType", String.format("map dictionary key beans fail from type: %s.", ""), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DICTIONARY_FAIL);
        }

    }


    /**
     * {@inheritDoc}
     *
     * @see #findDictionaryMap(List)
     */
    @Transactional(readOnly = true)
    @Override
    public Map<String, Map<String, String>> findDictionaryMap(List<String> dicsType) {
        try {

            Map<String, Map<String, String>> dicsMap = new HashMap<>();
            if (dicsType != null && dicsType.size() > 0) {
                for (String type : dicsType) {
                    Map<String, String> dic = dictionaryCacheService.findDickeyValueCache(type);
                    if (dic != null) {
                        dicsMap.put(type, dic);
                    } else {
                        dic = new HashMap<>();
                        dicsMap.put(type, dic);
                    }
                }
            }
            return dicsMap;
        } catch (Exception ex) {
            logService.erorLog(logger, "service", "findDictionaryMap", String.format("find dictionary code Name Map fail "), ex);
            throw new BasedataException(BasedataException.BasedataErrors.FIND_DICTIONARY_FAIL);
        }
    }

    @Override
    public Map<String, String> findDictionary(String type) {
        try {
            Map<String, String> dic = dictionaryCacheService.findDickeyValueCache(type);
            if (dic==null){
                dic=new HashMap<>();
            }
            return dic;
        }catch (Exception ex){
            logService.erorLog(logger, "service", "findDictionaryMap", String.format("find dictionary code Name Map fail "), ex);
            return new HashMap<>();
        }
    }
}
