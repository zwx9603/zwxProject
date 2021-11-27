package com.dscomm.iecs.accept.graphql.inputbean;

import java.util.List;

/**
 * 坐席查询参数
 */
public class SeatQueryInputInfo {

    private String scopeSquadronId ; //查询范围辖区机构Id

    private List<String> seatTypeCodes ; //坐席类型编码集合

    public String getScopeSquadronId() {
        return scopeSquadronId;
    }

    public void setScopeSquadronId(String scopeSquadronId) {
        this.scopeSquadronId = scopeSquadronId;
    }

    public List<String> getSeatTypeCodes() {
        return seatTypeCodes;
    }

    public void setSeatTypeCodes(List<String> seatTypeCodes) {
        this.seatTypeCodes = seatTypeCodes;
    }
}
