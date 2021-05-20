package com.yaojia.configuration.microprofile.config.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class MapBasedConfigSource implements ConfigSource {


    private final String name;

    private final int ordinal;

    private final Map<String,String> configData;

    public MapBasedConfigSource(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
        this.configData = new HashMap<>();
    }

    @Override
    public Map<String, String> getProperties() {
        try {
            prepareConfigData(configData);
        } catch (Throwable e) {
            throw new IllegalArgumentException("准备配置数据发生错误", e);
        }
        return Collections.unmodifiableMap(configData);
    }


    public abstract void prepareConfigData(Map configData) throws Throwable;


    @Override
    public Set<String> getPropertyNames() {
        return null;
    }

    @Override
    public String getValue(String propertyName) {
        return null;
    }

    @Override
    public final String getName() {
        return name;
    }


    @Override
    public final int getOrdinal() {
        return ordinal;
    }
}
