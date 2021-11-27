package com.dscomm.iecs.base.service;

/**
 * 描述:第三方日志服务
 *
 */
public interface SubAuditService {
    /**
     * 审计日志输出
     *
     * @param account    账号名
     * @param userName    用户名
     * @param organizationCode    机构code
     * @param organizationName    机构名称
     * @param operateType    操作类型
     *                       Add：添加操作
     *                       Edit：修改操作
     *                       Delete：删除操作
     *                       Query：查询操作
     *                       Disable：禁用操作
     *                       Authorize：认证操作
     *                       Login：登陆操作
     *                       Logout：登出操作
     * @param operateResult  操作结果
     *                       Ok
     *                       Error
     * @param operateContent 操作内容
     */
    void buildSubAuditLog(String account, String userName, String organizationCode, String organizationName,
                          String operateType, String operateResult, String operateContent);
}
