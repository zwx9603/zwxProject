package com.dscomm.iecs.out.restful;

import lombok.Data;


/**
 * @author fanghuilin
 * @version 1.00
 * @Time 2021/4/27
 * @Describe
 */
@Data
public class OutQueryVO {
    private String startTime;
    private String endTime;
    private String access_token;
    private String deptCode;
}
