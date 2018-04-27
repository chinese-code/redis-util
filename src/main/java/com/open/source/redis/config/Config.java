package com.open.source.redis.config;


import com.open.source.redis.util.PropertyUtils;

/**
 * @author tumingjian
 */
public class Config {
    private String hostname;
    private Integer port;
    private String password;
    private Integer database;
    private Integer timeout;
    /**
     * 最大连接数
     */
    private Integer maxTotal;
    /**
     * 最大空闲连接数
     */
    private Integer maxIdle;
    /**
     * 最小空闲连接数
     */
    private Integer minIdle;

    public Config() {
        this.hostname = String.valueOf(PropertyUtils.getDefaultPropertiesProperty("redis.hostname"));
        this.port = PropertyUtils.getDefaultPropertiesProperty("redis.port", 6379);
        this.password = PropertyUtils.getDefaultPropertiesProperty("redis.password");
        this.database = PropertyUtils.getDefaultPropertiesProperty("redis.database", 10);
        this.timeout = PropertyUtils.getDefaultPropertiesProperty("redis.timeout", 5000);
        this.maxTotal = PropertyUtils.getDefaultPropertiesProperty("redis.maxTotal", 10);
        this.maxIdle = PropertyUtils.getDefaultPropertiesProperty("redis.maxIdle", 6);
        this.minIdle = PropertyUtils.getDefaultPropertiesProperty("redis.minIdle", 4);
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getDatabase() {
        return database;
    }

    public void setDatabase(Integer database) {
        this.database = database;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
