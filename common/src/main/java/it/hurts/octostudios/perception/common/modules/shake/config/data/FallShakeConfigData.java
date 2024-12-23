package it.hurts.octostudios.perception.common.modules.shake.config.data;

import it.hurts.octostudios.octolib.modules.config.annotations.Prop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FallShakeConfigData {
    @Prop(comment = "Generic intensity of the screen shake during a fall. If the value is 0 or below, the effect is disabled.")
    private float intensity = 1F;
    @Prop(comment = "Min player's vertical speed required to trigger the screen shake effect.")
    private float minSpeed = 0.5F;
}