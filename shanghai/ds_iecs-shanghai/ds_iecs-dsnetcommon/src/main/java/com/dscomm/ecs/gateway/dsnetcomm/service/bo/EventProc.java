/**
 * 
 */
package com.dscomm.ecs.gateway.dsnetcomm.service.bo;

import com.sun.jna.Callback;

/**
 * 接收命令消息的事件回调接口
 * 
 * @author Josh Peng
 * 
 */
public interface EventProc extends Callback {

	/**
	 * 接收到一条命令消息
	 * 
	 * @param pMsg
	 *            命令消息内存块
	 */
	void callback(MemoryBlock.ByReference pMsg);

}
