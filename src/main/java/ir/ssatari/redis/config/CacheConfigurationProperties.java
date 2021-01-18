package ir.ssatari.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cache")
@Data
public class CacheConfigurationProperties {
    private int redisPort = 6379;
    private String redisHost = "localhost";
}
