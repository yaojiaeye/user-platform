package com.yaojia.configuration.microprofile.config;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

/**
 * @author yaojia
 */
public class DefaultConfigProviderResolver extends ConfigProviderResolver {

    @Override
    public Config getConfig() {
        return null;
    }

    @Override
    public Config getConfig(ClassLoader classLoader) {
        return null;
    }

    @Override
    public ConfigBuilder getBuilder() {
        return null;
    }

    @Override
    public void registerConfig(Config config, ClassLoader classLoader) {

    }

    @Override
    public void releaseConfig(Config config) {

    }
}
