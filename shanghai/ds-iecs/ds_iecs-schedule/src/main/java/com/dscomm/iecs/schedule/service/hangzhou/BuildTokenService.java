package com.dscomm.iecs.schedule.service.hangzhou;

/**
 *  大华 视频数据
 */
public interface BuildTokenService {

    /**
     * 注册会话
     * @return
     */
    String  authorize( String tokenUrl , String userName , String  password ) ;

    /**
     * 保活会话
     * @return
     */
    Boolean keepalive(String tokenUrl , String  token ) ;

    /**
     * 销毁会话
     * @return
     */
    Boolean unauthorize( String tokenUrl , String  token ) ;


//    /**
//     * 视频测试
//     * @param token
//     */
//    void saveTest(String tokenUrl , String  token ) ;



    /**
     * 高频率 更新（ 无人机视频 ）
     * @param token
     */
    void saveDahuaVideoGaoWRJSP(String tokenUrl , String  token ) ;

    /**
     * 高频率 更新 （ 4G视频 or   营区监控视频）
     * @param token
     */
    void saveDahuaVideoGao4GSP( String tokenUrl ,String  token ) ;


    /**
     * 高频率 更新 （ 机器人视频 ）
     * @param token
     */
    void saveDahuaVideoGaoJQRSP( String tokenUrl ,String  token ) ;



    /**
     *  低频率 更新 （ 其他视频  ）
     * @param token
     */
    void saveDahuaVideoQTSP( String tokenUrl , String  token ) ;









}
