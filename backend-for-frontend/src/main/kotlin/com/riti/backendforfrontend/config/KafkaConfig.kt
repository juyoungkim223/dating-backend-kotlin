package com.riti.backendforfrontend.config


import com.google.firebase.messaging.*
import com.riti.data.enums.ResultCode
import com.riti.data.exception.ApiRuntimeException
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.kafka.receiver.KafkaReceiver
import reactor.kafka.receiver.ReceiverOptions
import reactor.kafka.receiver.internals.ConsumerFactory
import reactor.kafka.receiver.internals.DefaultKafkaReceiver
import reactor.kafka.sender.KafkaSender
import reactor.kafka.sender.SenderOptions
import reactor.kafka.sender.internals.DefaultKafkaSender
import reactor.kafka.sender.internals.ProducerFactory
import java.time.Duration
import java.util.*


/**
 *
 */
@Configuration
class KafkaConfig {
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapServerUrl: String? = null
    @Value("\${spring.kafka.consumer.group-id}")
    private val consumerGroupId: String? = null
    @Value("\${message.topic}")
    private val messageTopic: String? = null
    var logger: Logger = LoggerFactory.getLogger(this::class.java)

    @Bean
    fun kafkaSenderOptions(): SenderOptions<String, String> {
        val props: MutableMap<String, Any> = HashMap()
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = listOf(bootstrapServerUrl)
        props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name

        return SenderOptions.create(props)
    }

    @Bean
    fun kafkaReceiverOptions(): ReceiverOptions<String, String> {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = listOf(bootstrapServerUrl)
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        return ReceiverOptions.create<String, String>(props)
            .subscription(setOf(messageTopic))
            .commitInterval(Duration.ZERO)
            .commitBatchSize(0)
    }


    @Bean
    fun createKafkaReceiver(): KafkaReceiver<String, String> {
        return DefaultKafkaReceiver(ConsumerFactory.INSTANCE, kafkaReceiverOptions()
            .consumerProperty(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId as Any))
    }

    @Bean
    fun createKafkaSender(): KafkaSender<String?, String?> {
        return DefaultKafkaSender(ProducerFactory.INSTANCE, kafkaSenderOptions())
    }
}