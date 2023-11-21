package com.riti.backendforfrontend.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration

class ReactiveRedisConfig {
    @Value("\${spring.redis.url}")
    private val serverUrl: String = ""
    @Value("\${spring.redis.port}")
    private val serverPort: Int = 0
    @Bean
    fun reactiveRedisConnectionFactory(): ReactiveRedisConnectionFactory {
        return LettuceConnectionFactory(serverUrl, serverPort)
    }
    /*
    @Bean
    fun redisOperations(factory: ReactiveRedisConnectionFactory): ReactiveRedisOperations<String?, String?> {
        val serializer: Jackson2JsonRedisSerializer<String> = Jackson2JsonRedisSerializer(String::class.java)
        val builder: RedisSerializationContext.RedisSerializationContextBuilder<String, String> = RedisSerializationContext.newSerializationContext(StringRedisSerializer())
        val context: RedisSerializationContext<String, String> = builder.value(serializer).build()
        return ReactiveRedisTemplate(factory, context)
    }

     */
}
