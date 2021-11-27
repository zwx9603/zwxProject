package com.dscomm.iecs.schedule.service.excel;

import com.dscomm.iecs.basedata.dal.po.MiniatureStationEntity;
import com.dscomm.iecs.basedata.dal.repository.OrganizationOtherRepository;
import com.dscomm.iecs.basedata.dal.repository.OrganizationRepository;
import com.dscomm.iecs.schedule.service.excel.bean.AddressBean;
import com.dscomm.iecs.schedule.service.excel.bean.AddressLocationBean;
import com.dscomm.iecs.schedule.utils.ExcelUtil;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.mx.StringUtils;
import org.mx.dal.service.GeneralAccessor;
import org.mx.service.client.rest.RestClientInvoke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.net.ssl.SSLContext;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("microStationServiceImpl")
public class MicroStationServiceImpl implements MicroStationService {

    private GeneralAccessor accessor;
    private OrganizationRepository organizationRepository;
    private OrganizationOtherRepository microRepository;
    private static CloseableHttpClient httpClient;

    @Autowired
    public MicroStationServiceImpl(@Qualifier("generalAccessor") GeneralAccessor accessor,
                                   OrganizationRepository organizationRepository,OrganizationOtherRepository microRepository) {
        this.accessor = accessor;
        this.organizationRepository = organizationRepository;
        this.microRepository = microRepository;
        try {
            SSLContext sslContext = SSLContextBuilder.create().useProtocol(SSLConnectionSocketFactory.SSL).loadTrustMaterial((x, y) -> true).build();
            RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();
            httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).setSSLContext(sslContext).setSSLHostnameVerifier((x, y) -> true).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void fromExcel() {

        List<Map<String, String>> microMaps = ExcelUtil.fromExcel("D:\\DS202011\\micro.xls");
        List<MiniatureStationEntity> entities = new ArrayList<>();

        for (Map<String, String> map : microMaps
        ) {
            MiniatureStationEntity entity = new MiniatureStationEntity();
            entity.setStationName(map.get("name"));
            switch (map.get("district")) {
                case "江干区":
                    entity.setDistrictCode("330104");
                    break;
                case "临安市":
                    entity.setDistrictCode("330185");
                    break;
                case "富阳市":
                    entity.setDistrictCode("330183");
                    break;
                case "西湖区":
                    entity.setDistrictCode("330106");
                    break;
                case "上城区":
                    entity.setDistrictCode("330102");
                    break;
                case "桐庐县":
                    entity.setDistrictCode("330122");
                    break;
                case "建德市":
                    entity.setDistrictCode("330182");
                    break;
                case "下城区":
                    entity.setDistrictCode("330103");
                    break;
                case "余杭区":
                    entity.setDistrictCode("330110");
                    break;
                case "淳安县":
                    entity.setDistrictCode("330127");
                    break;
                case "拱墅区":
                    entity.setDistrictCode("330105");
                    break;
                case "滨江区":
                    entity.setDistrictCode("330108");
                    break;
                case "萧山区":
                    entity.setDistrictCode("330109");
                    break;
                default:
                    entity.setDistrictCode(map.get("district"));
                    break;
            }

            entity.setStationAddress(map.get("address"));
            entity.setRoadSection(map.get("street"));

            String brigade = map.get("brigade");
            if (!StringUtils.isBlank(brigade)){
                brigade = brigade.substring(0,brigade.length()-2);
                List<String> brigadeIds = organizationRepository.findOrgIdByNameAndRootIdAndNature("%"+brigade+"%", "85a307b64dbd469a93271b542cbbb075%", "05%");
                if (brigadeIds != null && brigadeIds.size()>0){
                    entity.setBrigadeOrganizationId(brigadeIds.get(0));
                }else {
                    entity.setBrigadeOrganizationId(map.get("brigade"));
                }
            }


            String squadron = map.get("squadron");
            if (!StringUtils.isBlank(squadron)){
                squadron = squadron.substring(0,squadron.length()-1);
                if (!StringUtils.isBlank(squadron) && squadron.equals("富春")){
                    squadron = "公园西路";
                }
                List<String> squadronids = organizationRepository.findOrgIdByNameAndRootIdAndNature("%"+squadron+"%", "85a307b64dbd469a93271b542cbbb075%", "09%");
                if (squadronids != null && squadronids.size()>0){
                    entity.setSquadronOrganizationId(squadronids.get(0));
                }else {
                    entity.setSquadronOrganizationId(map.get("squadron"));
                }
            }

            if (map.get("status") != null && map.get("status").equals("正常")){
                entity.setStationStatus("0");
            }else if (map.get("status") != null && map.get("status").equals("关停")){
                entity.setStationStatus("1");
            }else if (map.get("status") != null && map.get("status").equals("其他")){
                entity.setStationStatus("2");
            }

            entity.setStationType(map.get("type"));
            if (!StringUtils.isBlank(map.get("onDuty"))){
                if (map.get("onDuty").equals("7*24小时值班")){
                    entity.setStationDutyType("0");
                }else if (map.get("onDuty").equals("非7*24小时值班")){
                    entity.setStationDutyType("1");
                }else if (map.get("onDuty").equals("无值班")){
                    entity.setStationDutyType("2");
                }
            }
            if (!StringUtils.isBlank(map.get("handle"))){
                if (map.get("handle").equals("可调派")){
                    entity.setStationDispatchStatus("0");
                }else if (map.get("handle").equals("不可调派")){
                    entity.setStationDispatchStatus("1");
                }
            }
            entity.setContactPersonName(map.get("contactPerson"));
            entity.setContactPersonPhone(map.get("contactPersonPhone"));
            entity.setDutyPhone(map.get("dutyPhone"));
            entity.setPersonNum(Integer.parseInt(map.get("personNum")));

            AddressLocationBean addressLocationBean = getLocation(entity);

            entities.add(entity);



            System.out.println(String.format("微站%s保存完成",entity.getStationName()));

//            if (addressLocationBean != null && addressLocationBean.getLat() != null && addressLocationBean.getLon() != null){
//                try {
//                    microRepository.updateGeomAndIdcode(addressLocationBean.getLon(),addressLocationBean.getLat(),entity.getId());
//                    System.out.println(String.format("微站%s图层保存完成",entity.getOrganizationName()));
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//            }



        }
        accessor.save(entities);
//        microRepository.updateGeomAndIdcode();
        System.out.println("微站图层保存完成");
    }

    private AddressLocationBean getLocation(MiniatureStationEntity entity){
        AddressLocationBean addressLocationBean = new AddressLocationBean();
        if (entity != null){
            if (!StringUtils.isBlank(entity.getStationAddress())){
                RestClientInvoke restClientInvoke = new RestClientInvoke();
                String url = "http://41.253.39.242:28001/zjzwfw/ime-server/rest/geocode/geo?address=%s";
                try {
                    String address = URLEncoder.encode(entity.getStationAddress(),"UTF-8");
//                    A addressVO = restClientInvoke.get(url,DataVO.class);
//                    if (addressVO != null){
//
//                    }
//                    AddressBean addressBean = (AddressBean)addressVO.getData();
                    AddressBean addressBean = restClientInvoke.get(String.format(url,address),AddressBean.class);
                    if (addressBean != null){
                        if (addressBean.getResults() != null && addressBean.getResults().size()>0){
                            entity.setLatitude(addressBean.getResults().get(0).getLocation().getLat().toString());
                            entity.setLongitude(addressBean.getResults().get(0).getLocation().getLon().toString());
                            addressLocationBean.setLon(addressBean.getResults().get(0).getLocation().getLon());
                            addressLocationBean.setLat(addressBean.getResults().get(0).getLocation().getLat());
                            return addressLocationBean;
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println(url);
                    return addressLocationBean;
                }
            }
        }
        return addressLocationBean;
    }



    public void testUrl (){
        String address = "南山路56号";

        RestClientInvoke restClientInvoke = new RestClientInvoke();
        Map<String,Object> map = new HashMap<>();
//        map.put("Content-Type","application/json");
        try {
            String urlStr = "http://41.253.39.242:28001/zjzwfw/ime-server/rest/geocode/geo?address="+URLEncoder.encode(address,"UTF-8");
//            url = URLEncoder.encode(url,"UTF-8");
          Object vo = restClientInvoke.get(urlStr,Object.class,map);
//            URL url = new URL(urlStr );
//            URI uri = url.toURI();
//            HttpGet httpGet = new HttpGet(uri);
//            CloseableHttpResponse execute = httpClient.execute(httpGet);
//            System.out.println("/n"+execute.getEntity().getContent()+"/n");
          System.out.println(vo);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void modify(){
        List<Map<String, String>> microMaps = ExcelUtil.fromExcel("D:\\DS202011\\micro.xls");
        List<MiniatureStationEntity> entities = new ArrayList<>();

        for (Map<String, String> map : microMaps
        ){
            MiniatureStationEntity entity = microRepository.findMiniatureStationByName(map.get("name")).get(0);
            if (!StringUtils.isBlank(map.get("onDuty"))){
                if (map.get("onDuty").equals("7*24小时值班")){
                    entity.setStationDutyType("0");
                }else if (map.get("onDuty").equals("非7*24小时值班")){
                    entity.setStationDutyType("1");
                }else if (map.get("onDuty").equals("无值班")){
                    entity.setStationDutyType("2");
                }
            }
            if (!StringUtils.isBlank(map.get("handle"))){
                if (map.get("handle").equals("可调派")){
                    entity.setStationDispatchStatus("0");
                }else if (map.get("handle").equals("不可调派")){
                    entity.setStationDispatchStatus("1");
                }
            }
            entities.add(entity);
            System.out.println(String.format("微站%s保存完成",entity.getStationName()));
        }
        accessor.save(entities);

    }

}
