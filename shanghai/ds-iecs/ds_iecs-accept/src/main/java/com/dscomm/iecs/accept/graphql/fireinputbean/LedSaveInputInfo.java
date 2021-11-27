package com.dscomm.iecs.accept.graphql.fireinputbean;

import java.util.ArrayList;
import java.util.List;

public class LedSaveInputInfo {
    private String type;
    private List<ledInputInfo> inputInfos=new ArrayList<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ledInputInfo> getInputInfos() {
        return inputInfos;
    }

    public void setInputInfos(List<ledInputInfo> inputInfos) {
        this.inputInfos = inputInfos;
    }
}
