package com.dscomm.iecs.accept.service.bean;

import com.dscomm.iecs.accept.graphql.typebean.HierarchicalDispatchVehicleBean;

import java.util.List;

/**
 * 描述:推荐车辆信息
 *
 * @author YangFuxi
 * Date Time 2021/8/4 8:58
 */
public class SmartRecommendVehicleBean {
    private String id;
    private List<HierarchicalDispatchVehicleBean> recommendVehicleDetail;//推荐车辆信息
    private List<String> vehicleIds;//推荐给前端的车辆id，时recommendVehicleDetail中的汇总，方便前端快速获取
    private String tips;//提示信息，当可调派车辆有缺额时提示
    private PowerTransferQueryParam recommendParam;//智能推荐调用时的参数
    private PowerTransferResultBean recommendResult;//智能推荐的返回值结果
    private List<VehicleTypeAndNumBean> dispatchVehicles;//实际调派力量
    private String causeOfDifference;//差异化原因

    public List<HierarchicalDispatchVehicleBean> getRecommendVehicleDetail() {
        return recommendVehicleDetail;
    }

    public void setRecommendVehicleDetail(List<HierarchicalDispatchVehicleBean> recommendVehicleDetail) {
        this.recommendVehicleDetail = recommendVehicleDetail;
    }

    public List<String> getVehicleIds() {
        return vehicleIds;
    }

    public void setVehicleIds(List<String> vehicleIds) {
        this.vehicleIds = vehicleIds;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public PowerTransferQueryParam getRecommendParam() {
        return recommendParam;
    }

    public void setRecommendParam(PowerTransferQueryParam recommendParam) {
        this.recommendParam = recommendParam;
    }

    public PowerTransferResultBean getRecommendResult() {
        return recommendResult;
    }

    public void setRecommendResult(PowerTransferResultBean recommendResult) {
        this.recommendResult = recommendResult;
    }

    public List<VehicleTypeAndNumBean> getDispatchVehicles() {
        return dispatchVehicles;
    }

    public void setDispatchVehicles(List<VehicleTypeAndNumBean> dispatchVehicles) {
        this.dispatchVehicles = dispatchVehicles;
    }

    public String getCauseOfDifference() {
        return causeOfDifference;
    }

    public void setCauseOfDifference(String causeOfDifference) {
        this.causeOfDifference = causeOfDifference;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
