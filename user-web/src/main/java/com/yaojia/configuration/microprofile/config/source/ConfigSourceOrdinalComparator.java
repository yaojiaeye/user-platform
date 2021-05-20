package com.yaojia.configuration.microprofile.config.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Comparator;

public class ConfigSourceOrdinalComparator implements Comparator<ConfigSource> {

    private ConfigSourceOrdinalComparator(){

    }

    public static final Comparator<ConfigSource> INSTANCE = new ConfigSourceOrdinalComparator();

    @Override
    public int compare(ConfigSource o1, ConfigSource o2) {
        return Integer.compare(o2.getOrdinal(), o1.getOrdinal());
    }
}
