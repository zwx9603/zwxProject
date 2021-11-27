package com.dscomm.iecs.accept.dal.po;


import com.dscomm.iecs.base.dal.po.BaseEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 描述:受理单（报警记录）
 *
 */
@Entity
@Table(name = "JCJ_SLD_119KZ")
@DynamicInsert(true)
@DynamicUpdate(true)
public class AcceptanceExtend119Entity extends BaseEntity {




}
