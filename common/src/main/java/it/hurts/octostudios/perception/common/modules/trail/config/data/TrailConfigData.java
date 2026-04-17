package it.hurts.octostudios.perception.common.modules.trail.config.data;

import it.hurts.shatterbyte.shatterlib.util.ShatterColor;
import lombok.*;
import org.joml.Vector3f;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrailConfigData {
    @Builder.Default
    private float size = 0.1F;

    @Builder.Default
    private int maxPoints = 5;

    @Builder.Default
    private float minSpeed = 0.025F;

    @Builder.Default
    private int updateFrequency = 1;

    @Builder.Default
    private ShatterColor fadeInColor = new ShatterColor(0xFFFFFFFF);

    @Builder.Default
    private ShatterColor fadeOutColor = new ShatterColor(0xFFFFFFFF);

    @Builder.Default
    private Vector3f positionOffset = new Vector3f(0F, 0F, 0F);

    @Builder.Default
    private float backwardShift = 0F;

    @Builder.Default
    private float motionShift = 0.25F;
}