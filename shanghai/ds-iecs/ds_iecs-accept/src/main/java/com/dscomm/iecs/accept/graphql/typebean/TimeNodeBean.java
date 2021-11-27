package com.dscomm.iecs.accept.graphql.typebean;



import java.util.List;

public class TimeNodeBean {

	//时间
    private Long time;
    //包围圈半径
    private Double surroundingCircleRadius;
    //出动力量
    private List<VehiclePowerBean> dispatchPower;
  //任务部署，集结点
    private List<RallyPointBean> taskDeployment;
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	public Double getSurroundingCircleRadius() {
		return surroundingCircleRadius;
	}
	public void setSurroundingCircleRadius(Double surroundingCircleRadius) {
		this.surroundingCircleRadius = surroundingCircleRadius;
	}
	public List<VehiclePowerBean> getDispatchPower() {
		return dispatchPower;
	}
	public void setDispatchPower(List<VehiclePowerBean> dispatchPower) {
		this.dispatchPower = dispatchPower;
	}
	public List<RallyPointBean> getTaskDeployment() {
		return taskDeployment;
	}
	public void setTaskDeployment(List<RallyPointBean> taskDeployment) {
		this.taskDeployment = taskDeployment;
	}
    
    
}
