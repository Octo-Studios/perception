package it.hurts.octostudios.perception.common.modules.base.config;

import it.hurts.octostudios.perception.common.config.PerceptionConfigData;
import it.hurts.shatterbyte.shatterlib.module.config.ConfigSide;
import it.hurts.shatterbyte.shatterlib.module.config.ShatterConfig;

public abstract class ModuleConfig extends ShatterConfig {
    @Override
    public ConfigSide getSide() {
        return ConfigSide.CLIENT;
    }

    @Override
    public int getSchemaVersion() {
        return PerceptionConfigData.SCHEMA_VERSION;
    }
}