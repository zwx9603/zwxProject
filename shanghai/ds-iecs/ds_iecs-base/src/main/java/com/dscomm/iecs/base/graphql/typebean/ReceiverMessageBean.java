package com.dscomm.iecs.base.graphql.typebean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

/**
 * 描述:websocket发送者和接收者定义
 *
 * @author YangFuxi
 * Date Time 2019/7/19 17:27
 */
@XmlAccessorType(XmlAccessType.NONE)
public class ReceiverMessageBean implements Serializable {

    private static final long serialVersionUID = 3670631330987592735L;

    private String id;

    private String system = "HJSL";

    /**
     * type：发送对象类型 : 1.user用户 2.org行政单位 3.org.role行政单位下的角色
     * 4.businessOrg.role业务单位下的角色 5.system : 本系统全部角色 id：发送对象id,id所代表的含义取决于type值
     * type=“user”id为用户名 type=”org” id为单位code type=”org.role” id=”行政单位code|角色id”
     * type=” businessOrg.role” id=”业务单位code|角色id” type=”system” id = " *"
     * system : 发送系统的系统代码: ices代码为HJSL
     */

    private String type;

    /**
     * 默认构造方法
     */
    public ReceiverMessageBean() {
        super();
    }

    /**
     * 默认构造方法
     *
     * @param type 类型
     * @param id   id
     */
    public ReceiverMessageBean(String type, String id) {
        super();
        this.type = type;
        this.id = id;
    }

    /**
     * 构造方法
     *
     * @param id     用户号
     * @param system 所属系统
     * @param type   用户类型
     */
    public ReceiverMessageBean(String id, String system, String type) {
        this.id = id;
        this.system = system;
        this.type = type;
    }

    /**
     * 获取类成员id
     *
     * @return {@link #id}
     */
    @XmlAttribute(name = "id")
    public String getId() {
        return this.id;
    }

    /**
     * 获取类成员system
     *
     * @return {@link #system}
     */
    @XmlAttribute(name = "system")
    public String getSystem() {
        return this.system;
    }

    /**
     * 获取类成员type
     *
     * @return {@link #type}
     */
    @XmlAttribute(name = "type")
    public String getType() {
        return this.type;
    }

    /**
     * 设定类成员id
     *
     * @param id 要设定的{@link #id}
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 设定类成员system
     *
     * @param system 要设定的{@link #system}
     */
    public void setSystem(String system) {
        this.system = system;
    }

    /**
     * 设定类成员type
     *
     * @param type 要设定的{@link #type}
     */
    public void setType(String type) {
        this.type = type;
    }

}
