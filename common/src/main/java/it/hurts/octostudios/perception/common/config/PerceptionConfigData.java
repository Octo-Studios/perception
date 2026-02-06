package it.hurts.octostudios.perception.common.config;

import it.hurts.shatterbyte.shatterlib.module.config.ConfigSide;
import it.hurts.shatterbyte.shatterlib.module.config.ShatterConfig;
import it.hurts.shatterbyte.shatterlib.module.config.type.annotation.Comment;
import it.hurts.shatterbyte.shatterlib.module.config.type.annotation.Exclude;
import lombok.Getter;

@Getter
public class PerceptionConfigData extends ShatterConfig {
    @Exclude
    public static final int SCHEMA_VERSION = 0;

    @Comment("""
            Toggles advanced configuration files, allowing customization of most of the mod's functionality. May contain WIP content that may change in the future.
            
            Activating this feature may lead to unintended consequences, so use it only if you know what you're doing. If any part of the mod update involves changes to the configuration file values, these changes will not be applied automatically. You will need to manually update the necessary sections or reset them to their original state.
            """)
    private boolean enabledExtendedConfigs = false;

    @Comment("Enables or disables the screen shake module.")
    private boolean enabledShakesModule = true;
    @Comment("Enables or disables the entities/particles trails module.")
    private boolean enabledTrailsModule = true;

    @Override
    public String getName() {
        return "perception";
    }

    @Override
    public ConfigSide getSide() {
        return ConfigSide.CLIENT;
    }

    @Override
    public int getSchemaVersion() {
        return SCHEMA_VERSION;
    }
}