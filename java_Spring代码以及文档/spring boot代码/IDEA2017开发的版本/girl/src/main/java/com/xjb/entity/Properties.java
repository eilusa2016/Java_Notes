package com.xjb.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Repository;

/**
 * 读取配置文件
 * 获得前缀是person的配置属性
 */
@Repository
@ConfigurationProperties(prefix = "person")
public class Properties {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
