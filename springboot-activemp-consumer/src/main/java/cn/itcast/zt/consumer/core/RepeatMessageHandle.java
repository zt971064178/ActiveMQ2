package cn.itcast.zt.consumer.core;

import cn.itcast.zt.consumer.domain.N1Record;
import cn.itcast.zt.consumer.domain.N2Record;
import cn.itcast.zt.consumer.service.N1RecordService;
import cn.itcast.zt.consumer.service.N2RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by zhangtian on 2017/6/2.
 */
public class RepeatMessageHandle {
    @Autowired
    private N1RecordService n1RecordService ;

    @Autowired
    private N2RecordService n2RecordService ;

    /**
     * 判断消息是否重复
     * @param params 参数
     * @param messageMethodHandle
     * @param n2 是否是n2级别
     * @throws Exception
     */
    @Transactional
    public void repeatHandle(Map<String, Object> params, MessageMethodHandle messageMethodHandle, boolean n2) throws  Exception {
        String data = (String) params.get("data");
        String messageId = (String) params.get("messageId");
        Long timeStamp = Long.valueOf((String) params.get("timeStamp"));
        String topic = (String) params.get("destination");
        params.put("destName" ,params.get("destination")) ;

        StringEvent event = new StringEvent(messageId, topic, data);
        // n1级别的消息
        if (!n2) {
            N1Record n1Record = n1RecordService.selectN1Record(messageId);
            //消息不重复
            if (n1Record == null) {
                n1Record = new N1Record();
                n1Record.setMessageId(messageId);
                n1Record.setTimeStamp(timeStamp);
                //添加消息记录
                n1RecordService.addN1Record(n1Record);
                //业务处理
                messageMethodHandle.invokeMethod(event);
            }
        }else {
            //n2级别消息
            N2Record n2Record = n2RecordService.selectN2Record(params);
            //业务唯一标识
            String businessMark = (String) params.get("businessMark");
            //没有消息记录
            if (n2Record == null) {
                n2Record = new N2Record();
                n2Record.setBusinessMark(businessMark);
                n2Record.setTimeStamp(timeStamp);
                n2Record.setDestName(topic);
                //添加消息记录
                n2RecordService.addN2Record(n2Record);
                messageMethodHandle.invokeMethod(event);
            } else if (timeStamp > n2Record.getTimeStamp()) {
                n2Record.setTimeStamp(timeStamp);
                //更新消息记录
                n2RecordService.updateN2Record(n2Record);
                messageMethodHandle.invokeMethod(event);
            }
        }
    }
}
