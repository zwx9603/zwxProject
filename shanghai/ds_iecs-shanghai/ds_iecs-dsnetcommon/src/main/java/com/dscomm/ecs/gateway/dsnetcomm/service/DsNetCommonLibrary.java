package com.dscomm.ecs.gateway.dsnetcomm.service;



import com.dscomm.ecs.gateway.dsnetcomm.service.bo.EventProc;
import com.dscomm.ecs.gateway.dsnetcomm.service.bo.MemoryBlock;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.ShortByReference;

/**
 * description: 加载类库
 *
 * @author YangFuxi
 * Date Time 2020/8/28 11:20
 */
public interface DsNetCommonLibrary extends Library {
    DsNetCommonLibrary LIBRARY = (DsNetCommonLibrary) Native.loadLibrary("dsnetcomm", DsNetCommonLibrary.class);

    /**
     * 通信接口模块初始化
     *
     * @param lpCfgPathL 配置文件类库
     * @param bLog       是否打印日志      Integer _Z18pb_NET_LoadLibraryPKch(
     * @return 返回结果
     */
    short pb_NET_LoadLibrary(
            String lpCfgPathL,
            Boolean bLog
    );

    /**
     * 释放通信库
     *
     * @return 返回结果
     */
    short pb_NET_FreeLibrary();

    /**
     * 设置配置文件路径
     *
     * @param lpCfgPath 配置文件路径
     * @return 返回结果
     */
    Integer pb_NET_SetCfgPath(
            String lpCfgPath
    );

    /**
     * 应用程序调用该接口注册本地类型，通信模块会根据其中指定的类型进行如下操作：连接上级服务器（TCP长连接）；打开本地监听端口
     *
     * @param wType
     * @param wID
     * @param wRemoteID
     * @param lpLocalDev
     * @param lpSvrPortAddr _Z15pb_NET_DSRegSvrRKtS0_PFvPK8_MEM_BLKEtPKcS7_c
     * @param cStatus
     * @return
     */
    short pb_NET_DSRegSvr(
            ShortByReference wType,                //本地类型
            ShortByReference wID,                    //本地ID
            EventProc eventProc,                //数据响应回调接口
            ShortByReference wRemoteID,            //服务器的ID
            String lpLocalDev,            //本地网卡指定
            String lpSvrPortAddr,        //应用可指定服务器端口地址对Port:IP
            byte cStatus    //初始主备状态，默认备用.
    );

    void pb_NET_FreeMemoryBlock(MemoryBlock.ByReference pBlk);

    short pb_NET_DSSendData(MemoryBlock.ByReference pBlk);

    short pb_NET_DSWaitForEvt(MemoryBlock.ByReference pBlk);


}
