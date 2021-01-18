package ir.ssatari.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
@EnableConfigurationProperties(CacheConfigurationProperties.class)
@Slf4j
public class TwoLayerRedisCacheLocalConfig extends CachingConfigurerSupport {

    private final CacheConfigurationProperties properties;

    public TwoLayerRedisCacheLocalConfig(CacheConfigurationProperties properties) {
        this.properties = properties;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(properties.getRedisHost(), properties.getRedisPort());
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    @Bean
    public RedisCacheManager cacheManager() {
        return RedisCacheManager.create(jedisConnectionFactory());
    }
}