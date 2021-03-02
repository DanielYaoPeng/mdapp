package com.example.mdapp.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.producer.bootstrap.servers}")
    private String producerServer;

    @Value("${spring.kafka.producer.retries}")
    private Integer producerRetries;

    @Value("${spring.kafka.producer.buffer.memory}")
    private String producerBufferMemory;

    @Value("${spring.kafka.producer.acks}")
    private String producerAcks;

    @Value("${spring.kafka.consumer.bootstrap.servers}")
    private String consumerServer;

    @Value("${spring.kafka.consumer.enable.auto.commit}")
    private Boolean consumerAutoCommit;

    @Value("${spring.kafka.consumer.group.id}")
    private String consumerGroupId;

    @Value("${spring.kafka.consumer.auto.offset.reset}")
    private String consumerOffsetReset;

    @Value("${spring.kafka.consumer.max.poll.records}")
    private String consumerPollNum;

    /**
     * 生产者配置信息
     */
    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(ProducerConfig.ACKS_CONFIG, producerAcks);// 为0时，生产者不会等待返回消息发送结果
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerServer);
        props.put(ProducerConfig.RETRIES_CONFIG, producerRetries);// 发送失败时，重新发送消息次数
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);// 批量发送消息的间隔时间
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, producerBufferMemory);// 生产者缓存消息的内存字节数
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }

    /**
     * 生产者工厂
     */
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    /**
     * 生产者模板
     */
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /**
     * 消费者配置信息
     */
    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);// 消费者组ID
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerOffsetReset);// offser没有初始化或者不存在时默认的配置
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerServer);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, consumerPollNum);// 每次拉取记录的数量
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 120000);// 用于检测客户端故障的超时时间
        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 180000);// 请求响应的超时时间
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<String, String>(consumerConfigs());
    }

    /**
     * 默认消费端初始化
     * @return
     */
    @Bean
    public KafkaListenerContainerFactory<?> containerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> container = new ConcurrentKafkaListenerContainerFactory<>();
        container.setConsumerFactory(consumerFactory());
        container.setBatchListener(true);//批量拉取消息，与消费者的接收参数有关
        return container;
    }

    /**
     * KafkaListener 延迟启动监听工厂
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> delayContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> container = new ConcurrentKafkaListenerContainerFactory<String, String>();
        container.setConsumerFactory(new DefaultKafkaConsumerFactory<String, Object>(consumerConfigs()));
        // 禁止自动启动 true
        container.setAutoStartup(false);
        container.setBatchListener(true);
        return container;
    }
}
