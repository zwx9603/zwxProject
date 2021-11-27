package com.dscomm.iecs.basedata.dal.repository;

import com.dscomm.iecs.basedata.dal.po.EquipmentVehicleEntity;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.transform.Transformers;
import org.mx.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * 描述： 车辆数据底层服务
 */
@Repository
public class EquipmentVehicleRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<EquipmentVehicleEntity> findEquipmentVehicleCondition(String orgId, String orgSearchPath,Boolean cascade,String keyword, List<String> vehicleTypeCodes,
                                                                      List<String> vehicleStatusCodes,
                                                                      String vehicleId ,List<String> vehicleIds , String  vehicleNumber ,
                                                                      String  natureLike , int page, int size) {
        String sql="select vehicle , garage  from EquipmentVehicleEntity vehicle left join  VehicleGarageMappingEntity garage   on vehicle.id = garage.mappingObjectId " +
                " left join OrganizationEntity t on t.id = vehicle.organizationId where vehicle.valid = 1 and t.valid = 1 ";
        StringBuilder condition = buildCondition(orgId, orgSearchPath, cascade, keyword, vehicleTypeCodes, vehicleStatusCodes, vehicleId, vehicleIds, vehicleNumber, natureLike);
        if (page<1){
            page=1;
        }
        if (size<1){
            size=20;
        }
        Query query = entityManager.createQuery(sql+condition.toString()+" order by t.organizationOrderNum,vehicle.vehicleOrderNum");
        query.setFirstResult((page-1)*size);
        query.setMaxResults(size);
        List resultList = query.getResultList();
        return resultList;

    }

    private StringBuilder buildCondition(String orgId, String orgSearchPath, Boolean cascade, String keyword, List<String> vehicleTypeCodes, List<String> vehicleStatusCodes, String vehicleId, List<String> vehicleIds, String vehicleNumber, String natureLike) {
        StringBuilder condition=new StringBuilder();
        //过滤单位
        if (cascade!=null&&cascade){
            if (!StringUtils.isBlank(orgSearchPath)){
                condition.append(" and t.searchPath like '").append(orgSearchPath).append("%' ");
            }
        }else {
            if (!StringUtils.isBlank(orgId)){
                condition.append(" and t.id='").append(orgId).append("'");
            }
        }
        //过滤关键字
        if (!StringUtils.isBlank(keyword)){
            condition.append(" and (vehicle.vehicleName  like '%").append(keyword).append("%' or vehicle.vehicleShortName like '%").append(keyword).append("%' or vehicle.vehicleNumber like '%").append(keyword).append("%'").append(") ");
        }
        //过滤车辆类型
        if (vehicleTypeCodes!=null&&!vehicleTypeCodes.isEmpty()){
            condition.append(" and vehicle.vehicleTypeCode in ('").append(org.apache.commons.lang3.StringUtils.join(vehicleTypeCodes,"','")).append("') ");
        }
        //过滤车辆状态
        if (vehicleStatusCodes!=null&&!vehicleStatusCodes.isEmpty()){
            condition.append(" and vehicle.vehicleStatusCode in ('").append(org.apache.commons.lang3.StringUtils.join(vehicleStatusCodes,"','")).append("') ");
        }
        //过滤消防机构性质代码
        if (!StringUtils.isBlank(natureLike)){
            condition.append(" and t.organizationNatureCode like '").append(natureLike).append("%'");
        }
        //过滤车辆id
        if (!StringUtils.isBlank(vehicleId)){
            condition.append(" and vehicle.id='").append(vehicleId).append("'");
        }
        if (vehicleIds!=null&&!vehicleIds.isEmpty()){
            condition.append(" and vehicle.id in ('").append(org.apache.commons.lang3.StringUtils.join(vehicleIds,"','")).append("')");
        }
        //过滤车牌号（模糊匹配）
        if (!StringUtils.isBlank(vehicleNumber)){
            condition.append(" and vehicle.vehicleNumber like '%").append(vehicleNumber).append("%'");
        }
        return condition;
    }

    @Transactional(readOnly = true)
    public Integer findEquipmentVehicleConditionTotal(String orgId, String orgSearchPath,Boolean cascade,String keyword, List<String> vehicleTypeCodes,
                                                      List<String> vehicleStatusCodes,
                                                      String vehicleId ,List<String> vehicleIds , String  vehicleNumber ,
                                                      String  natureLike) {
        String headsql = "  select count(1) as  NUM  from EquipmentVehicleEntity vehicle left join OrganizationEntity t on t.id = vehicle.organizationId  where vehicle.valid = 1  ";
        StringBuilder condition = buildCondition(orgId, orgSearchPath, cascade, keyword, vehicleTypeCodes, vehicleStatusCodes, vehicleId, vehicleIds, vehicleNumber, natureLike);
        Query query = entityManager.createQuery(headsql + condition.toString());
        Object total = query.getSingleResult();
        return Integer.valueOf(total.toString());
    }


    public  List<String> findVehicleIdByExpandCondition  (
            String  keyword , //关键字
            Float maxNum1 ,  //载液量 高值
            Float minNum1,   //载液量 低值
            Float maxNum2 ,   //泵流量 高值
            Float minNum2 ,   //泵流量 低值
            Float maxNum3 ,  //举升高度 高值
            Float minNum3 ,   //举升高度 低值
            Float maxNum4 ,   //消防炮流量 高值
            Float minNum4 ,  //消防炮流量 低值
            Float maxNum5 ,   //牵引力 高值
            Float minNum5 ,   //牵引力 低值
            Float maxNum6 ,   //吊机 高值
            Float minNum6     //吊机 低值
    ){
        String headsql = "  select vehicle.id  from EquipmentVehicleEntity vehicle  where vehicle.valid = 1  ";
        String conditionsql = "";
        if( Strings.isNotBlank( keyword )){
            conditionsql = conditionsql + " and   ( vehicle.foam  like :GZJ or  vehicle.equipment  like :GZJ )    ";
        }
        //载液量
        if( maxNum1 != null ){
            conditionsql = conditionsql + " and    vehicle.carrierBubble  <= :MAXNUM1   ";
        }
        if( minNum1 != null ){
            conditionsql = conditionsql + " and    vehicle.carrierBubble  >= :MINNUM1     ";
        }
        //泵流量
        if( maxNum2 != null ){
            conditionsql = conditionsql + " and  vehicle.pumpFlow  <= :MAXNUM2   ";
        }
        if( minNum2 != null ){
            conditionsql = conditionsql + " and    vehicle.pumpFlow  >= :MINNUM2   ";
        }
        //举升高度
        if( maxNum3 != null ){
            conditionsql = conditionsql + " and  vehicle.maxLiftingHeight  <= :MAXNUM3   ";
        }
        if( minNum3 != null ){
            conditionsql = conditionsql + " and    vehicle.maxLiftingHeight  >= :MINNUM3   ";
        }
        //消防炮流量
        if( maxNum4 != null ){
            conditionsql = conditionsql + " and  vehicle.fireMonitorFlow  <= :MAXNUM4   ";
        }
        if( minNum4!= null ){
            conditionsql = conditionsql + " and    vehicle.fireMonitorFlow  >= :MINNUM4   ";
        }
        //牵引力
        if( maxNum5 != null ){
            conditionsql = conditionsql + " and  vehicle.traction  <= :MAXNUM5  ";
        }
        if( minNum5!= null ){
            conditionsql = conditionsql + " and    vehicle.traction  >= :MINNUM5   ";
        }
        //吊机
        if( maxNum6 != null ){
            conditionsql = conditionsql + " and  vehicle.liftingWeight  <= :MAXNUM6  ";
        }
        if( minNum6!= null ){
            conditionsql = conditionsql + " and    vehicle.liftingWeight  >= :MINNUM6   ";
        }
        String endsql = "  ";
        String ordersql = "    ";
        String sql = headsql + conditionsql + endsql + ordersql;
        Query query = entityManager.createQuery(sql);

        if( Strings.isNotBlank( keyword )){
            query.setParameter("GZJ", "%"+keyword+"%" );
        }
        //载液量
        if( maxNum1 != null ){
            query.setParameter("MAXNUM1", maxNum1);
        }
        if( minNum1 != null ){
            query.setParameter("MINNUM1", minNum1);
        }
        //泵流量
        if( maxNum2 != null ){
            query.setParameter("MAXNUM2", maxNum2);
        }
        if( minNum2 != null ){
            query.setParameter("MINNUM2", minNum2);
        }
        //举升高度
        if( maxNum3 != null ){
            query.setParameter("MAXNUM3", maxNum3);
        }
        if( minNum3 != null ){
            query.setParameter("MINNUM3", minNum3);
        }
        //消防炮流量
        if( maxNum4 != null ){
            query.setParameter("MAXNUM4", maxNum4);
        }
        if( minNum4!= null ){
            query.setParameter("MINNUM4", minNum4);
        }
        //牵引力
        if( maxNum5 != null ){
            query.setParameter("MAXNUM5", maxNum5);
        }
        if( minNum5!= null ){
            query.setParameter("MINNUM5", minNum5);
        }
        //吊机
        if( maxNum6 != null ){
            query.setParameter("MAXNUM6", maxNum6);
        }
        if( minNum6!= null ){
            query.setParameter("MINNUM6", minNum6);
        }

        return query.getResultList();

    }

}
