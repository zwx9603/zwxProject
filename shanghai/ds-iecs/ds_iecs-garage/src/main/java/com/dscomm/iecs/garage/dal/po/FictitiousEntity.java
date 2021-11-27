package com.dscomm.iecs.garage.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 虚拟enitity  实际不存在
 */
@Entity
@Table(name = "JCJ_XN")
@DynamicInsert(true)
@DynamicUpdate(true)
public class FictitiousEntity extends BaseEntity  {


}
