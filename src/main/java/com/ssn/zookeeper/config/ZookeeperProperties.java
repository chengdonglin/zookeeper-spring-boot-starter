package com.ssn.zookeeper.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author linchengdong
 * @Date 2024-05-28 19:37
 * @PackageName:com.ssn.zookeeper.config
 * @ClassName: ZookeeperProperties
 * @Description: TODO
 * @Version 1.0
 */
@ConfigurationProperties(prefix = ZookeeperProperties.PREFIX)
public class ZookeeperProperties {

    public static final String PREFIX = "zookeeper";

    private boolean enable = true;

    /**
     * zookeeper server 地址
     */
    private String host;

    /**
     * 重试休眠时间
     */
    private Integer sleepTimeMs = 30000;

    /**
     * 最大重试次数
     */
    private Integer maxRetries = 5;


    /**
     * 会话超时时间
     */
    private Integer sessionTimeout = 30000;

    private Integer connectionTimeout = 30000;

    public ZookeeperProperties() {
    }

    public ZookeeperProperties(String host, Integer sleepTimeMs, Integer maxRetries, Integer sessionTimeout, Integer connectionTimeout) {
        this.host = host;
        this.sleepTimeMs = sleepTimeMs;
        this.maxRetries = maxRetries;
        this.sessionTimeout = sessionTimeout;
        this.connectionTimeout = connectionTimeout;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getSleepTimeMs() {
        return sleepTimeMs;
    }

    public void setSleepTimeMs(Integer sleepTimeMs) {
        this.sleepTimeMs = sleepTimeMs;
    }

    public Integer getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(Integer maxRetries) {
        this.maxRetries = maxRetries;
    }

    public Integer getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Integer sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
}
