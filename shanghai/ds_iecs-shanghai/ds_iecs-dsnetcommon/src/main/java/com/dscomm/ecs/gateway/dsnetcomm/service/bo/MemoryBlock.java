/**
 * 
 */
package com.dscomm.ecs.gateway.dsnetcomm.service.bo;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

/**
 * 针对DS11和DS21通讯平台中使用的MemoryBlock结构定义
 * 
 * @author Josh Peng
 * 
 */
public class MemoryBlock extends Structure {

	/**
	 * 实际的命令消息内容，包含底层的消息头。
	 */
	public Pointer addr;
	/**
	 * 本命令消息的大小。
	 */
	public NativeLong size;

	/**
	 * MemoryBlock的传址模式（引用模式）
	 */
	public static class ByReference extends MemoryBlock implements
			Structure.ByReference {
	};

	/**
	 * MemoryBlock的传值模式
	 */
	public static class ByValue extends MemoryBlock implements
			Structure.ByValue {
	};

}
