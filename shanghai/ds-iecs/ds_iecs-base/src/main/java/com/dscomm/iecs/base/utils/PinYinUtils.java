package com.dscomm.iecs.base.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PinYinUtils {

    private static HanyuPinyinOutputFormat format = null;
    static {
        format = new HanyuPinyinOutputFormat();
        //拼音小写
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        //无音标方式；WITH_TONE_NUMBER：1-4数字表示英标；WITH_TONE_MARK：直接用音标符（必须WITH_U_UNICODE否则异常
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //用v表示ü
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
    }


    /**
     * @param hanziChar
     * @return
     * @Author:lulei
     * @Description: 返回汉字的拼音
     */
    private static String[] getCharPinYinHanzi(char hanziChar ) {
        try {
            //返回字符hanziChar的拼音
            return PinyinHelper.toHanyuPinyinStringArray( hanziChar , format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 获取汉字拼音的方法。如： 张三 zhangsan
     * 说明：暂时解决不了多音字的问题，只能使用取多音字的第一个音的方案
     * @param hanzi 汉子字符串
     * @return 汉字拼音; 如果都转换失败,那么返回null
     */
    private static String getHanziPinYin(String hanzi) {
        String result = null;
        if(null != hanzi && !"".equals(hanzi)) {
            char[] charArray = hanzi.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (char ch : charArray) {
                // 逐个汉字进行转换， 每个汉字返回值为一个String数组（因为有多音字）
                String[] stringArray = getCharPinYinHanzi(ch);
                if(null != stringArray) {
                    // 把第几声这个数字给去掉
                    sb.append(stringArray[0].replaceAll("\\d", ""));
                }
            }
            if(sb.length() > 0) {
                result = sb.toString();
            }
        }
        return result;
    }

    /**
     * 获取汉字拼音的方法。如： 张三 ZHANGSAN
     * 说明：暂时解决不了多音字的问题，只能使用取多音字的第一个音的方案
     * @param hanzi 汉子字符串
     * @return 大写汉子首字母; 如果都转换失败,那么返回null
     */
    public static String getHanziInitials(String hanzi) {
        String result = getHanziPinYin( hanzi );
        return result;
    }

        /**
         * 获取汉字拼音的方法。如： 张三 ZHANGSAN
         * 说明：暂时解决不了多音字的问题，只能使用取多音字的第一个音的方案
         * @param hanzi 汉子字符串
         * @return 大写汉子首字母; 如果都转换失败,那么返回null
         */
    public static String getHanziInitialsUpper(String hanzi) {
        String result = getHanziPinYin( hanzi );
        if(Strings.isNotBlank( result) ) {
           result = result.toUpperCase();
        }
        return result;
    }

    /**
     * 获取汉字拼音的方法。如： 张三 zhangsan
     * 说明：暂时解决不了多音字的问题，只能使用取多音字的第一个音的方案
     * @param hanzi 汉子字符串
     * @return 大写汉子首字母; 如果都转换失败,那么返回null
     */
    public static String getHanziInitialsLower(String hanzi) {
        String result = getHanziPinYin( hanzi );
        if(Strings.isNotBlank( result) ) {
            result = result.toLowerCase();
        }
        return result;
    }



    /**
     * 获取字母 数字  汉字首字母
     * 说明：暂时解决不了多音字的问题，只能使用取多音字的第一个音的方案 所有的数字，符号，字母不变化 所有的汉字以小写音序输出
     * @param hanzi 汉子字符串
     * @return 大写汉子首字母; 如果都转换失败,那么返回null
     */
    private static String getHanziHeaderNumber(String hanzi ){
        String result = null;
        if(null != hanzi && !"".equals(hanzi)) {
            char[] charArray = hanzi.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (char ch : charArray) {
                // 逐个汉字进行转换， 每个汉字返回值为一个String数组（因为有多音字）
                String[] stringArray = getCharPinYinHanzi(ch);
                if(null != stringArray) {
                    sb.append(stringArray[0].charAt(0));
                }else{
                    sb.append( ch );
                }
            }
            if(sb.length() > 0) {
                result = sb.toString() ;
            }
        }
        return result ;
    }

    /**
     * 获取汉字首字母的方法。如： 张三   ZS
     * 说明：暂时解决不了多音字的问题，只能使用取多音字的第一个音的方案 所有的数字，符号不变化 所有的音序，字母以大写输出
     * @param hanzi 汉子字符串
     * @return 大写汉子首字母; 如果都转换失败,那么返回null
     */
    public static String getHanziHeaderNumberInitials(String hanzi) {
        String result = getHanziHeaderNumber( hanzi );
        return result;
    }

    /**
     * 获取汉字首字母的方法。如： 张三   ZS
     * 说明：暂时解决不了多音字的问题，只能使用取多音字的第一个音的方案 所有的数字，符号不变化 所有的音序，字母以大写输出
     * @param hanzi 汉子字符串
     * @return 大写汉子首字母; 如果都转换失败,那么返回null
     */
    public static String getHanziHeaderNumberInitialsUpper(String hanzi) {
        String result = getHanziHeaderNumber( hanzi );
        if(Strings.isNotBlank( result) ) {
            result = result.toUpperCase();
        }
        return result;
    }

    /**
     * 获取 字母 数字  汉字首字母
     * 说明：暂时解决不了多音字的问题，只能使用取多音字的第一个音的方案 有的数字，符号不变化 所有的音序，字母以小写输出
     * @param hanzi 汉子字符串
     * @return 大写汉子首字母; 如果都转换失败,那么返回null
     */
    public static String getHanziHeaderNumberInitialsLower(String hanzi) {
        String result = getHanziHeaderNumber( hanzi );
        if(Strings.isNotBlank( result) ) {
            result = result.toLowerCase();
        }
        return result;
    }




    /**
     * 多音字 汉字拼音
     * @param haizi
     * @param list
     * @return
     * @Author:lulei
     * @Description: 将字符ch的拼音拼接到list中的记录中
     */
    private static List<String> getDuoYinHanziPinYin( char haizi , List<String> list) {
        String[] strs = getCharPinYinHanzi( haizi );
        List<String> result = new ArrayList<String>();
        // 解析出的拼音为空 直接返回历史
        if ( strs == null ) {
            return list;
        }
        //将字符C的拼音首和已存在的拼音首组合成新的记录
        for (String str : strs) {
            if (list == null || list.size() == 0) {
                result.add(str);
            } else {
                for (String s : list) {
                    result.add(s + str);
                }
            }
        }
        return result;
    }

    /**
     *  多音字 汉字拼音
     * @param haizi
     * @return
     * @Author:lulei
     * @Description: 返回字符串的拼音
     */
    public static String[] getDuoYinHanziPinYin( String haizi ) {
        if ( haizi == null || haizi .length() < 1) {
            return null;
        }
        List<String> result = new ArrayList<String>();
        //对字符串中的记录逐个分析
        for (int i = 0; i < haizi.length(); i++ ) {
            result = getDuoYinHanziPinYin( haizi .charAt(i), result );
        }
        return result.toArray(new String[result.size()]);
    }


    /**
     * 获取 字母 数字  多音字 汉字拼音 首字母
     */
    public static String getDuoYinInitials(String hanzi) {
        String[] duoyinzi   = getDuoYinHanziPinYin( hanzi ) ;
        String result = "" ;
        if( duoyinzi != null && duoyinzi.length > 0 ){
            Set<String> setDyz = new HashSet<>();
            for( String dyz :  duoyinzi ){
                setDyz.add( dyz ) ;
            }
            List<String> dyzlist = new ArrayList<>( setDyz ) ;
            for( int i = 0 ; i < dyzlist.size() ; i ++ ){
                if( i == dyzlist.size() - 1 ){
                    result  = result + dyzlist.get(i) ;
                }else {
                    result  = result + dyzlist.get(i)  + ",";
                }
            }
        }
        return  result ;
    }

    /**
     * 获取 字母 数字  多音字 汉字拼音 首字母
     */
    public static String getDuoYinInitialsUpper(String hanzi) {
        String result = getDuoYinInitials( hanzi );
        if( Strings.isNotBlank( result ) ){
            result = result.toUpperCase() ;
        }
        return  result  ;
    }

    /**
     * 获取 字母 数字  多音字 汉字拼音 首字母
     */
    public static String getDuoYinInitialsLower(String hanzi) {
        String result = getDuoYinInitials( hanzi );
        if( Strings.isNotBlank( result ) ){
            result = result.toLowerCase() ;
        }
        return  result  ;
    }

    /**
     * @param hanzi
     * @param list
     * @return
     * @Author:lulei
     * @Description: 将字符c的拼音首字母拼接到list中的记录中
     */
    private static List<String> getDuoYinHeaderNumber(char hanzi , List<String> list) {
        char[] chars = getDuoYinHeader( hanzi );
        List<String> result = new ArrayList<String>();
        //非汉字也添加
        if (chars == null) {
            if (list == null || list.size() == 0) {
                result.add( hanzi + "" );
            } else {
                for (String s : list) {
                    result.add( s +  hanzi );
                }
            }
            return result;
        }
        //将字符C的拼音首字母和已存在的拼音首字母组合成新的记录
        for (char ch : chars) {
            if (list == null || list.size() == 0) {
                result.add(ch + "");
            } else {
                for (String s : list) {
                    result.add(s + ch);
                }
            }
        }
        return result;
    }

    /**
     * @param hanzi
     * @return
     * @Author:lulei
     * @Description:返回汉字拼音首字母
     */
    private static char[] getDuoYinHeader(char hanzi ) {
        //字符C的拼音
        String[] strs = getCharPinYinHanzi( hanzi );
        if (strs != null) {
            //截取拼音的首字母
            char[] chars = new char[strs.length];
            for(int i = 0; i <chars.length; i++) {
                chars[i] = strs[i].charAt(0);
            }
            return chars;
        }
        return null;
    }



    /**
     * @param hanzi
     * @return
     * @Author:lulei
     * @Description: 返回字符串的拼音的首字母
     */
    private static String[] getDuoYinHeaderNumber ( String hanzi ) {
        if ( hanzi == null || hanzi.length() < 1) {
            return null;
        }
        List<String> result = new ArrayList<String>();
        //对字符串中的记录逐个分析
        for (int i = 0; i < hanzi.length(); i++) {
            result = getDuoYinHeaderNumber( hanzi .charAt(i), result );
        }
        return result.toArray(new String[result.size()]);
    }


    /**
     * 获取 字母 数字  多音字 汉字拼音 首字母  拼接
     */
    public static String getDuoYinHeaderNumberInitials(String hanzi) {
        String[] duoyinzi   = getDuoYinHeaderNumber( hanzi ) ;
        String result = "" ;
        if( duoyinzi != null && duoyinzi.length > 0 ){
            Set<String> setDyz = new HashSet<>();
            for( String dyz :  duoyinzi ){
                setDyz.add( dyz ) ;
            }
            List<String> dyzlist = new ArrayList<>( setDyz ) ;
            for( int i = 0 ; i < dyzlist.size() ; i ++ ){
                if( i == dyzlist.size() - 1 ){
                    result  = result + dyzlist.get(i) ;
                }else {
                    result  = result + dyzlist.get(i)  + ",";
                }
            }
        }
        return  result ;
    }

    /**
     * 获取 字母 数字  多音字 汉字拼音 首字母 大写
     */
    public static String getDuoYinHeaderNumberInitialsUpper(String hanzi) {
        String result = getDuoYinHeaderNumberInitials( hanzi );
        if( Strings.isNotBlank( result ) ){
            result = result.toUpperCase() ;
        }
        return  result  ;
    }

    /**
     * 获取 字母 数字  多音字 汉字拼音 首字母 小写
     */
    public static String getDuoYinHeaderNumberInitialsLower(String hanzi) {
        String result = getDuoYinHeaderNumberInitials( hanzi );
        if( Strings.isNotBlank( result ) ){
            result = result.toLowerCase() ;
        }
        return  result  ;
    }

//    public static void main(String[] args) throws Exception {
//        //只有汉字 多音字取第一个  全拼
//        String asd = getHanziPinYin("重庆,行庆,123SERT!@#$%^&*()123455678900") ;
//        System.out.println( asd);
//        String asd2 = getHanziInitialsLower ("重庆,行庆,123SERT!@#$%^&*()123455678900") ;
//        System.out.println( asd2);
//        String asd3 = getHanziInitialsUpper("重庆,行庆,123SERT!@#$%^&*()123455678900") ;
//        System.out.println( asd3);
//        //汉字 多音字取第一个 字母 数据
//        String asd4 = getHanziHeaderNumberInitials("重庆,行庆,123SERT!@#$%^&*()123455678900") ;
//        System.out.println( asd4);
//        String asd5 = getHanziHeaderNumberInitialsLower("重庆,行庆,123SERT!@#$%^&*()123455678900") ;
//        System.out.println( asd5);
//        String asd6 = getHanziHeaderNumberInitialsUpper("重庆,行庆,123SERT!@#$%^&*()123455678900") ;
//        System.out.println( asd6);
//        //只有汉字 多音字
//        String asd7 = getDuoYinInitials("重庆,行庆,123SERT!@#$%^&*()123455678900") ;
//        System.out.println( asd7);
//        String asd8 = getDuoYinInitialsLower("重庆,行庆,123SERT!@#$%^&*()123455678900") ;
//        System.out.println( asd8);
//        String asd9 = getDuoYinInitialsUpper("重庆,行庆,123SERT!@#$%^&*()123455678900") ;
//        System.out.println( asd9);
//        //只有汉字 多音字 字母 数据
//        String asd11 = getDuoYinHeaderNumberInitials("重庆,行庆,123SERT!@#$%^&*()123455678900") ;
//        System.out.println( asd11);
//        String asd12 = getDuoYinHeaderNumberInitialsLower("重庆,行庆,123SERT!@#$%^&*()123455678900") ;
//        System.out.println( asd12);
//        String asd13 = getDuoYinHeaderNumberInitialsUpper("新湾专职队") ;
//        System.out.println( asd13);
//    }


}
