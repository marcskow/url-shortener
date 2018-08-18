package com.marcskow.url.shortener.security

import com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.session.data.redis.config.ConfigureRedisAction
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import javax.annotation.PostConstruct


@ConditionalOnProperty(name = arrayOf("spring.session.store-type"), havingValue = "redis")
@EnableRedisHttpSession
class RedisSessionConfig {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(RedisSessionConfig::class.java)
    }

    @Value("\${spring.session.store-type}")
    private val sessionStoreType: String? = null

    @PostConstruct
    fun init() {
        LOGGER.info("spring.session.store-type=none turns spring session off.")
        LOGGER.info("Redis Session Replication is turned {}.", if (sessionStoreType == "redis")
            "ON"
        else
            "OFF")
    }

    @Bean
    fun configureRedisAction(): ConfigureRedisAction {
        LOGGER.info("Preventing auto-configuration in secured environments.")
        return ConfigureRedisAction.NO_OP
    }
}