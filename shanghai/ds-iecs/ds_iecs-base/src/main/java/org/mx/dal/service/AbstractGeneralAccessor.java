//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.mx.dal.service;

import org.mx.DigestUtils;
import org.mx.StringUtils;
import org.mx.dal.entity.Base;
import org.mx.dal.entity.BaseDict;
import org.mx.dal.entity.BaseDictTree;
import org.mx.dal.entity.PO;
import org.mx.spring.session.SessionDataStore;

import java.util.Date;

public abstract class AbstractGeneralAccessor implements GeneralAccessor {
    protected SessionDataStore sessionDataStore;

    public AbstractGeneralAccessor() {
    }

    protected <T extends PO> T checkExist( T  t) {
        Class<T> clazz = ( Class<T> ) t.getClass();
        String id = t.getId();
        if (!StringUtils.isBlank(id)) {
            T check = this.getById(id, clazz);
            if (check != null) {
                return check;
            }
        }

        if (t instanceof BaseDict) {
            String code = ((BaseDict)t).getCode();
            if (!StringUtils.isBlank(code)) {
                T check = this.findOne(ConditionTuple.eq("code", code), clazz);
                if (check != null) {
                    return check;
                }
            }
        }

        return null;
    }

    protected ConditionTuple createValidCondition(boolean isValid) {
        return isValid ? ConditionTuple.eq("valid", 1) : null;
    }

    protected <T extends PO> T prepareSave(T t) {
        if (t instanceof Base) {
            Base base = (Base)t;
            if (base.getUpdatedTime() <= 0L) {
                base.setUpdatedTime((new Date()).getTime());
            }

            if (StringUtils.isBlank(base.getOperator()) || "NA".equalsIgnoreCase(base.getOperator())) {
                base.setOperator(this.sessionDataStore.getCurrentUserCode());
            }
        }

        Class<T> clazz =  ( Class<T> )  t.getClass();
        T old = this.checkExist(t);
        if (t instanceof BaseDictTree) {
            BaseDictTree parent = ((BaseDictTree)t).getParent();
            if (!StringUtils.isBlank(((BaseDictTree)t).getParentId())) {
                T p = this.getById(((BaseDictTree)t).getParentId(), clazz);
                ((BaseDictTree)t).setParent((BaseDictTree)p);
            }
        }

        if (old == null) {
            if (StringUtils.isBlank(t.getId())) {
                String uuid = DigestUtils.uuid() ;
                uuid = uuid.replaceAll("-","") ;
                t.setId( uuid );
            }

            if (t instanceof Base) {
                Base base = (Base)t;
                if (base.getCreatedTime() <= 0L) {
                    base.setCreatedTime(    this.sessionDataStore.get().get("currentSystemTime") == null   ? new Date().getTime() :  Long.parseLong(  String.valueOf( this.sessionDataStore.get().get("currentSystemTime") ) )  );
                    base.setUpdatedTime( this.sessionDataStore.get().get("currentSystemTime") == null   ? new Date().getTime() :  Long.parseLong(  String.valueOf( this.sessionDataStore.get().get("currentSystemTime") ) )  );

                }
            }
        } else {
            t.setId(old.getId());
            if (t instanceof Base) {
                ((Base)t).setCreatedTime(((Base)old).getCreatedTime());
                ((Base)t).setUpdatedTime( this.sessionDataStore.get().get("currentSystemTime") == null   ? new Date().getTime() :  Long.parseLong(  String.valueOf( this.sessionDataStore.get().get("currentSystemTime") ) )  );
                if (t instanceof BaseDict) {
                    ((BaseDict)t).setCode(((BaseDict)old).getCode());
                }
            }
        }

        return old;
    }
}
