package it.hurts.octostudios.perception.common.modules.chromatic_aberration.config.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChromaticAberrationConfigData {
    @Builder.Default
    private float rangeMultiplier = 1F;

    @Builder.Default
    private int duration = 10;

    @Builder.Default
    private int fadeInTime = 0;

    @Builder.Default
    private int fadeOutTime = -1;

    @Builder.Default
    private float strength = 0.025F;
}
