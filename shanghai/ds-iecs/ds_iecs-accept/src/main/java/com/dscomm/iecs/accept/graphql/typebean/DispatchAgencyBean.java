package com.dscomm.iecs.accept.graphql.typebean;


import com.dscomm.iecs.basedata.graphql.typebean.OrganizationBean;

import java.util.List;

/**
 * 出动力量统计信息
 *
 * */
public class DispatchAgencyBean {

    private List<OrganizationBean> organization;//救援中队
    private Integer person;//救援人数
    private List<DispatchAgencyCarBean> cars;//救援车辆

    public List<OrganizationBean> getOrganization() {
        return organization;
    }

    public void setOrganization(List<OrganizationBean> organization) {
        this.organization = organization;
    }

    public Integer getPerson() {
        return person;
    }

    public void setPerson(Integer person) {
        this.person = person;
    }

    public List<DispatchAgencyCarBean> getCars() {
        return cars;
    }

    public void setCars(List<DispatchAgencyCarBean> cars) {
        this.cars = cars;
    }
}
