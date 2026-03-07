package it.hurts.octostudios.perception.common.modules.chromatic_aberration.config.data;

import it.hurts.octostudios.octolib.module.config.annotation.Prop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChromaticAberrationConfigData {
    @Prop
    @Builder.Default
    private float rangeMultiplier = 1F;

    @Prop
    @Builder.Default
    private int duration = 10;

    @Prop
    @Builder.Default
    private int fadeInTime = 0;
    @Prop
    @Builder.Default
    private int fadeOutTime = -1;

    @Prop
    @Builder.Default
    private float strength = 0.025F;
}