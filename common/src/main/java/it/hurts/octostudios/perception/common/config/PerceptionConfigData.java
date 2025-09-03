package it.hurts.octostudios.perception.common.config;

import it.hurts.octostudios.octolib.module.config.annotation.Prop;
import it.hurts.octostudios.octolib.module.config.impl.OctoConfig;
import lombok.Data;

@Data
public class PerceptionConfigData implements OctoConfig {
    @Prop(comment = """
            Toggles advanced configuration files, allowing customization of most of the mod's functionality. May contain WIP content that may change in the future.
            
            Activating this feature may lead to unintended consequences, so use it only if you know what you're doing. If any part of the mod update involves changes to the configuration file values, these changes will not be applied automatically. You will need to manually update the necessary sections or reset them to their original state.
            """)
    private boolean enabledExtendedConfigs = false;

    @Prop(comment = "Enables or disables the screen shake module.")
    private boolean enabledShakesModule = true;
    @Prop(comment = "Enables or disables the entities/particles trails module.")
    private boolean enabledTrailsModule = true;
}