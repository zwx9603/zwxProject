/**
 *
 */
package com.dscomm.ecs.gateway.dsnetcomm.exception;


/**
 * MainMsg生成XML文本过程发生异常。
 *
 * @author Josh Peng
 */
@SuppressWarnings("serial")
public class XmlGenerateFail extends DsNetCommException {
    private static final String errorMessage = "The MainMsg bean generate the XML string fail.";

    /**
     * 默认的构造函数
     */
    public XmlGenerateFail() {
        super(errorMessage);
    }

    /**
     * 默认的构造函数
     *
     * @param cause 导致异常的源
     */
    public XmlGenerateFail(Throwable cause) {
        super(errorMessage, cause);
    }

}
