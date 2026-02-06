package it.hurts.octostudios.perception.common.modules.shake.config.data;

import it.hurts.shatterbyte.shatterlib.module.config.type.annotation.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FallShakeConfigData {
    @Comment("Generic intensity of the screen shake during a fall. If the value is 0 or below, the effect is disabled.")
    private float intensity = 0F;
    @Comment("Min player's vertical speed required to trigger the screen shake effect.")
    private float minSpeed = 0.5F;
}