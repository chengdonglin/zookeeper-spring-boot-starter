package com.ssn.zookeeper.core.defaults;

import com.ssn.zookeeper.config.ZookeeperProperties;
import com.ssn.zookeeper.core.Session;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Author linchengdong
 * @Date 2024-05-28 19:51
 * @PackageName:com.ssn.zookeeper.core.defaults
 * @ClassName: DefaultZookeeperSession
 * @Description: TODO
 * @Version 1.0
 */
public class DefaultZookeeperSession implements Session {

    private final ZookeeperProperties properties;

    public DefaultZookeeperSession(ZookeeperProperties properties) {
        this.properties = properties;
    }

    @Override
    public CuratorFramework openSession() {
        CuratorFramework framework = CuratorFrameworkFactory.builder()
                .connectString(properties.getHost())
                .connectionTimeoutMs(properties.getConnectionTimeout())
                .sessionTimeoutMs(properties.getSessionTimeout())
                .retryPolicy(new ExponentialBackoffRetry(properties.getSleepTimeMs(), properties.getMaxRetries()))
                .build();
        framework.start();
        return framework;
    }
}
