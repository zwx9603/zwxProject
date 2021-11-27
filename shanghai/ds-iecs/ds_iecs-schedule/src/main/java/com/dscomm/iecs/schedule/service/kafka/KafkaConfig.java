package com.dscomm.iecs.schedule.service.kafka;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Configuration
@EnableKafka
public class KafkaConfig {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

    @Value("${spring.kafka.bootstrap-servers}")
    private String servers; //kafka 服务

    //生产者配置

    @Value("${spring.kafka.producer.retries}")
    private String retrie;

    @Value("${spring.kafka.producer.batch-size}")
    private String batchSize;

    @Value("${spring.kafka.producer.acks}")
    private String ascks;

    @Value("${spring.kafka.producer.buffer-memory}")
    private String bufferMemory;


    @Value("${spring.kafka.producer.key-deserializer}")
    private String producerKey;

    @Value("${spring.kafka.producer.value-deserializer}")
    private String producerValue;

    //消费者配置
    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private boolean enableAutoCommit;

    @Value("${spring.kafka.consumer.auto-commit-interval}")
    private Integer autoCommitIntervalMs;

    @Value("${spring.kafka.consumer.session.timeout-ms}")
    private Integer sessionTimeoutMs;

    @Value("${spring.kafka.consumer.key-deserializer}")
    private String consumerKey;

    @Value("${spring.kafka.consumer.value-deserializer}")
    private String consumerValue;

    /**
     * 生产者 配置信息
     * @return
     */
    @Bean
    public KafkaTemplate<String , String > kafkaTemplate() throws  Exception {
        return  new KafkaTemplate< String , String >( producerFactory() ) ;
    }

    @Bean
    public ProducerFactory<String, String>  producerFactory() throws  Exception{
        return  new DefaultKafkaProducerFactory<>( producerConfigs()   ) ;
    }

    @Bean
    public  Map<String, Object> producerConfigs() throws  Exception {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(ProducerConfig.RETRIES_CONFIG, retrie );
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize );
        props.put(ProducerConfig.LINGER_MS_CONFIG, ascks );
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory );
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, Class.forName( producerKey ) );
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Class.forName( producerValue ) );
        return props;

    }



    /**
     *  kafka 消费者监听对象
     * @return
     */
    @Bean
   public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer, String>> kafkaListenerContainerFactory() throws  Exception  {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @Bean
    public ConsumerFactory<Integer, String> consumerFactory() throws  Exception  {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }


    @Bean
    public Map<String, Object> consumerConfigs() throws  Exception  {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitIntervalMs);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeoutMs);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, Class.forName( consumerKey ) );
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, Class.forName( consumerValue ) );
        return props;
    }


    @KafkaListener(topics = "#{'${kafkaTopicName}'.split(',')}" ,containerFactory="kafkaListenerContainerFactory",autoStartup = "${sapi.api.kafka.autostartup}")
    public void consumeMessage(  ConsumerRecord<Integer, String> record  ) {

        logger.info("----------kafka consumeMessage start ----------");

        Optional<?> kafkaMessage = Optional.ofNullable( record.value() );
        if (kafkaMessage.isPresent()) {
            Object message =kafkaMessage.get();
            String topic = record.topic();
            logger.info("====topic=="+topic+"========message =" + message);
            //todo 业务逻辑

            logger.info("----------kafka consumeMessage handle successful --------");
        }

        logger.info("----------kafka consumeMessage end ---------- " );

    }



}