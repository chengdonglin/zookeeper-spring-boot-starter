package com.ssn.zookeeper.config;

import com.ssn.zookeeper.core.Session;
import com.ssn.zookeeper.core.ZookeeperTemplate;
import com.ssn.zookeeper.core.defaults.DefaultZookeeperSession;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author linchengdong
 * @Date 2024-05-28 19:41
 * @PackageName:com.ssn.zookeeper.config
 * @ClassName: ZookeeperAutoConfiguration
 * @Description: TODO
 * @Version 1.0
 */
@Configuration
@ConditionalOnProperty(prefix = ZookeeperProperties.PREFIX, name = "enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(ZookeeperProperties.class)
public class ZookeeperAutoConfiguration {

    @Bean
    public Session session(ZookeeperProperties properties) {
        return new DefaultZookeeperSession(properties);
    }

    @Bean
    public ZookeeperTemplate zookeeperTemplate(Session session) {
        return new ZookeeperTemplate(session.openSession());
    }
}
