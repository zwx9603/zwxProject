package com.dscomm.ecs.gateway.dsnetcomm.service;

import com.alibaba.fastjson.JSONObject;
import com.dscomm.ecs.gateway.dsnetcomm.config.DsNetCommonConfigBean;
import com.dscomm.ecs.gateway.dsnetcomm.exception.DsNotifyException;
import com.dscomm.ecs.gateway.dsnetcomm.service.bo.Ds21MsgHeader;
import com.dscomm.ecs.gateway.dsnetcomm.service.bo.EventProc;
import com.dscomm.ecs.gateway.dsnetcomm.service.bo.MemoryBlock;
import com.dscomm.ecs.gateway.dsnetcomm.service.bo.MessageData;
import com.dscomm.ecs.gateway.dsnetcomm.service.xml.Body;
import com.dscomm.ecs.gateway.dsnetcomm.service.xml.MainMsg;
import com.sun.jna.ptr.ShortByReference;
import org.mx.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * 描述:
 *
 * @author YangFuxi
 * Date Time 2020/9/12 15:58
 */
public class DsNetCommonManger {
    private static final Logger logger = LoggerFactory.getLogger(DsNetCommonManger.class);
    private ApplicationContext ctx;
    private DsNetCommonConfigBean configBean;
    private DsNetCommonLibrary library;
    private ReceiveChain receiveChain;

    private ReceiveMessageThread thread;

    private int headerLength = new Ds21MsgHeader().getHeaderLength();

    public DsNetCommonManger(ApplicationContext ctx, DsNetCommonConfigBean configBean, ReceiveChain receiveChain) {
        this.ctx = ctx;
        this.configBean = configBean;
        this.receiveChain = receiveChain;
        this.library = DsNetCommonLibrary.LIBRARY;
    }

    public void init() {
        try {
            logger.info("new a DsNetCommonManger....");
            logger.info("now init 21 library");
            short result = library.pb_NET_LoadLibrary(configBean.getCfgPath(), configBean.getWriteLog());
            logger.info("end init 21 library，result:" + result);
            logger.info("now start regsvr 21 node");

            short result2 = library.pb_NET_DSRegSvr(new ShortByReference((short) Integer.parseInt(configBean.getType().substring(2), 16)), new ShortByReference((short) Short.valueOf(configBean.getId())), (EventProc) null,
                    new ShortByReference((short) Short.valueOf(configBean.getRemoteId())), configBean.getLocalDev(), configBean.getSvrPortAddr(), configBean.getPrimary() ? (byte) 0x1 : (byte) 0x2);
            logger.info("end regsvr 21 node,result:" + result2);
//            sendMessage("初始化测试消息");
            System.out.println("start listen 21 message");
            thread = new ReceiveMessageThread();
            thread.start();
            logger.info("success new  DsNetCommonManger！");
            //TODO RECONN
        } catch (Exception ex) {
            logger.error("init 21 library fail", ex);
        }
    }


    public void destroy() {
        System.out.println("start destroy DsNetCommonManger...");
        if (thread != null) {
            thread.close();
            if (library != null) {
                System.out.println("start free 21 library");
                short result = library.pb_NET_FreeLibrary();
                System.out.println("end free 21 library,result:" + result);

            }
        }
        System.out.println("destroy DsNetCommonManger");
    }

    public Boolean sendMessage(int dstType, int dstId, int transType, int msgId, int reserved, String data) {
        try {
            logger.info(String.format("start send 21message：dstType:%s,dsId:%s,transType:%s,msgId:%s,data:%s", dstType, dstId, transType, msgId,data));
            Ds21MsgHeader header = new Ds21MsgHeader();
            header.setDestId(dstId);
            header.setDestType(dstType);
            header.setMsgId(msgId);
            header.setReserved(reserved);
            header.setMsgType(Byte.valueOf(configBean.getMsgType()));
            header.setSrcId(Integer.valueOf(configBean.getId()));
            header.setSrcType(Integer.parseInt(configBean.getType().substring(2), 16));
            header.setTransType(transType);
            MemoryBlock.ByReference memoryBlock = header.createMemoryBlock(data.getBytes());
            short rs = library.pb_NET_DSSendData(memoryBlock);
            if (rs > 0) {
                logger.info("send 21 message success：" + data);
                return true;
            } else {
                logger.error("send 21 message fail：" + data);
                throw new DsNotifyException(DsNotifyException.DsNetErrors.SEND_21_MESSAGE_FAIL);
            }

        } catch (Exception ex) {
            logger.error(String.format("send 21 message fail：%s", data), ex);
            throw new DsNotifyException(DsNotifyException.DsNetErrors.SEND_21_MESSAGE_FAIL);
        }
    }

    public Boolean sendMessage(String dstType, String dstId, int transType, String msgId, String reserved, Body body) {
        try {
            MainMsg mainMsg = new MainMsg();
            mainMsg.setBody(body);
            mainMsg.getHead().getDst().setId(dstId);
            mainMsg.getHead().getDst().setType(dstType.substring(2));
            mainMsg.getHead().getMsg().setCode(StringUtils.isBlank(reserved)?msgId.substring(2):reserved.substring(2));
            return sendMessage(Integer.parseInt(dstType.substring(2), 16), Integer.parseInt(dstId), transType, Integer.parseInt(msgId.substring(2), 16), Integer.parseInt(reserved.substring(2), 16), mainMsg);
        } catch (Exception ex) {
            logger.error("发送21消息失败：" + JSONObject.toJSONString(body),ex);
            return false;
        }
    }

    public Boolean sendMessage(int dstType, int dstId, int transType, int msgId, int reserved, MainMsg mainMsg) {
        try {
            String xmlMsg = mainMsg.toXmlString();
            xmlMsg=xmlMsg.replaceAll("&lt;","<").replaceAll("&gt;",">");
            return sendMessage(dstType, dstId, transType, msgId, reserved, xmlMsg);
        } catch (Exception ex) {
            logger.error("发送21消息失败：" + JSONObject.toJSONString(mainMsg),ex);
            return false;
        }
    }


//    public void sendMessage(String msg) {
//        try {
//            System.out.println(String.format("开始发送21消息...，消息内容:%s", msg));
//            Ds21MsgHeader header = new Ds21MsgHeader();
//            header.setDestId(3);
//            header.setDestType(0x8700);
//            header.setMsgId(0xAA00);
//            header.setMsgType((byte) 0);
//            header.setReserved(0);
//            header.setSrcId(2);
//            header.setSrcType(Integer.parseInt(configBean.getType().substring(2),16));
//            header.setTransType(2);
//            MemoryBlock.ByReference memoryBlock = header.createMemoryBlock(msg.getBytes());
//            short sendResult = library.pb_NET_DSSendData(memoryBlock);
//            System.out.println(String.format("结束发送21消息，发送结果:%s,消息内容:%s", sendResult, msg));
//        } catch (Exception ex) {
//            System.out.println(String.format("发送21消息失败,消息:%s", msg));
//            ex.printStackTrace();
//        }
//    }

    public class ReceiveMessageThread extends Thread {
        private Thread thread = null;
        private MemoryBlock.ByReference pBlk = null;

        public ReceiveMessageThread() {
            super();
            this.thread = this;
            System.out.println("new a thread！");
        }

        public void close() {
            if (thread != null) {
                thread = null;
            }
            System.out.println("thread exit!");
        }

        @Override
        public void run() {
            logger.info("start 21 message thread to listen message(run start)...");
            Thread currentThread = Thread.currentThread();
            while (this.thread == currentThread) {
                short result = 0;
                //收消息，并处理消息
                do {
                    logger.info("start poll message once:" + System.currentTimeMillis());
                    pBlk = new MemoryBlock.ByReference();
                    result = library.pb_NET_DSWaitForEvt(pBlk);
                    if (result > 0) {
                        try {
                            long size = pBlk.size.longValue();
                            if (size < headerLength) {
                                logger.info(String.format("this message is empty，size:%s,headerSize:%s", size, headerLength));
                            } else {
                                Ds21MsgHeader header = new Ds21MsgHeader();
                                logger.info("start transfprm 21 header");
                                header.fromMemory(pBlk.addr);
                                logger.info(String.format("21 message header：%s", JSONObject.toJSONString(header)));
                                byte[] buffer = pBlk.addr.getByteArray(header.getHeaderLength(), pBlk.size.intValue() - header.getHeaderLength());
                                int end = 0;
                                for (; end < buffer.length; end++) {
                                    if (buffer[end] == '\0') {
                                        break;
                                    }
                                }
                                byte[] data = new byte[end];
                                System.arraycopy(buffer, 0, data, 0, end);
                                MessageData messageData = new MessageData();
                                messageData.setHeader(header);
                                messageData.setData(data);
                                logger.info(String.format("receive 21 message ，content:%s,msgId:%s", new String(messageData.getData(), configBean.getCharset()), messageData.getHeader().getMsgID()));
                                receiveChain.onReceive(messageData);
                            }
                        } catch (Exception ex) {
                            logger.error("receive 21 message fail", ex);
                        }
                    } else {
                        logger.info("this time not have a 21 message");
                        break;
                    }
                    library.pb_NET_FreeMemoryBlock(pBlk);
                    pBlk = null;
                    logger.info("end polling 21 message:" + System.currentTimeMillis());
                } while (result > 0);
                try {
                    logger.info("thread start sleep....");
                    Thread.sleep(configBean.getPollingDelay());
                    System.out.println("thread end sleep");
                } catch (Exception ex) {
                    logger.error("thread sleep fail", ex);
                }
            }

            logger.info("end 21 message thread to listen message(run start)...");
        }
    }
}
