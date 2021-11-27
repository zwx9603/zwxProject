package com.dscomm.iecs.schedule.service.kafka;

import com.dscomm.iecs.schedule.config.IecsScheduleConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 *  kafka 生产
 */
@Component
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(IecsScheduleConfig.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public  KafkaProducer( @Qualifier("kafkaTemplate") KafkaTemplate kafkaTemplate ){
        this.kafkaTemplate = kafkaTemplate ;
    }

    public void produceMessage( String topic , String message   ) {

        logger.info("----------kafka produceMessage start ----------");

        logger.info("====topic=="+topic+"========message =" + message);

        kafkaTemplate.send(topic , message  );

        logger.info("----------kafka produceMessage end ---------- " );

    }


}
