package it.hurts.octostudios.perception.common.modules.shake.config.data;

import it.hurts.octostudios.octolib.modules.config.annotations.Prop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShakeConfigData {
    @Prop(comment = "The radius in blocks within which the shaking effect spreads. A value of -1 sets the shaking radius equal to the sound's playback radius.")
    private float radius;

    @Prop(comment = "The intensity of the screen shaking.")
    private float amplitude;

    @Prop(comment = "The speed of the screen shaking.")
    private float speed;

    @Prop(comment = "The duration of the screen shaking.")
    private int duration;

    @Prop(comment = "The time in ticks for interpolating the screen shaking from the minimum to the maximum value. Applied at the start of the shaking effect.")
    private int fadeInTime;

    @Prop(comment = "The time in ticks for interpolating the screen shaking from the maximum to the minimum value. Applied at the end of the shaking effect. A value of -1 sets the interpolation duration equal to the effect's total duration.")
    private int fadeOutTime;
}