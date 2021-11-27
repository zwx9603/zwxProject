package com.dscomm.iecs.basedata.graphql.typebean;


import java.util.List;

/**
 * 描述：全部字典 返回
 *
 */
public class DictionaryKeyBean   {

    private String key; //字典key

    private List<DictionaryBean> dictionaryBeanList ; //对应字典数据

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<DictionaryBean> getDictionaryBeanList() {
        return dictionaryBeanList;
    }

    public void setDictionaryBeanList(List<DictionaryBean> dictionaryBeanList) {
        this.dictionaryBeanList = dictionaryBeanList;
    }
}
