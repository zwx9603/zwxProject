package com.dscomm.iecs.base.dal.po;

import org.mx.dal.entity.Base;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 基于Hibernate实现的基础实体
 *
 * @author : john.peng date : 2017/10/6
 * @see Base
 */
@MappedSuperclass
public class BaseEntity implements Base {
    @Id
    @Column(name = "ID", nullable = false, length = 40)
    private String id;

    @Column(name = "CJSJ" )
    private Long createdTime = -1L;

    @Column(name = "GXSJ" )
    private Long updatedTime = -1L;

    @Column(name = "CZZ" , length = 200 )
    private String operator;

    @Column(name = "YXX" )
    private int valid =  1 ;

    /**
     * {@inheritDoc}
     *
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "id='" + id + '\'' +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", operator='" + operator + '\'' +
                ", valid=" + valid;
    }

    /**
     * {@inheritDoc}
     *
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;
        if (id != null ? !id.equals(that.getId()) : that.getId() != null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @see Base#getId()
     */
    public String getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     *
     * @see Base#setId(String)
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     *
     * @see Base#getCreatedTime()
     */
    public long getCreatedTime() {
        return createdTime;
    }

    /**
     * {@inheritDoc}
     *
     * @see Base#setCreatedTime(long)
     */
    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * {@inheritDoc}
     *
     * @see Base#getUpdatedTime()
     */
    public long getUpdatedTime() {
        return updatedTime;
    }

    /**
     * {@inheritDoc}
     *
     * @see Base#setUpdatedTime(long)
     */
    public void setUpdatedTime(long updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * {@inheritDoc}
     *
     * @see Base#getOperator()
     */
    public String getOperator() {
        return operator;
    }

    /**
     * {@inheritDoc}
     *
     * @see Base#setOperator(String)
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * {@inheritDoc}
     *
     * @see Base#isValid()
     */
    @Override
    public boolean isValid() {
        return valid == 1;
    }

    /**
     * {@inheritDoc}
     *
     * @see Base#getValid()
     */
    @Override
    public int getValid() {
        return valid;
    }

    /**
     * {@inheritDoc}
     *
     * @see Base#setValid(int)
     */
    @Override
    public void setValid(int valid) {
        this.valid = valid;
    }

    /**
     * {@inheritDoc}
     *
     * @see Base#setValid(boolean)
     */
    @Override
    public void setValid(boolean valid) {
        this.valid = valid ? 1 : 0;
    }
}
