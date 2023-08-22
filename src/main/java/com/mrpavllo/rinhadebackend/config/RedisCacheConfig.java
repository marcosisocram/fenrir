package com.mrpavllo.rinhadebackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.mrpavllo.rinhadebackend.constants.Constants.CACHE_NAME;

@Configuration
public class RedisCacheConfig {

    @Value( "${spring.redis.host}" )
    private String host;

    @Value( "${spring.redis.port}" )
    private int port;

    @Value( "${spring.redis.ttl}" )
    private long ttl;

    @Bean
    public RedisCacheConfiguration cacheConfiguration( ) {
        return RedisCacheConfiguration.defaultCacheConfig( )
                .disableCachingNullValues( )
                .serializeValuesWith( RedisSerializationContext.SerializationPair.fromSerializer( new GenericJackson2JsonRedisSerializer( ) ) );
    }

    @Bean
    public CacheManager cacheManagerRedis( @Autowired RedisConnectionFactory connectionFactory,
                                           @Autowired RedisCacheConfiguration redisCacheConfiguration ) {
        var redisConfigMap = new HashMap< String, RedisCacheConfiguration >( );
        redisConfigMap.put( CACHE_NAME,
                redisCacheConfiguration.entryTtl( Duration.ofMillis( ttl ) ) );

        return RedisCacheManager.builder( connectionFactory )
                .cacheDefaults( redisCacheConfiguration )
                .withInitialCacheConfigurations( redisConfigMap )
                .build( );
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory( new RedisStandaloneConfiguration( host, port ) );
    }
}
