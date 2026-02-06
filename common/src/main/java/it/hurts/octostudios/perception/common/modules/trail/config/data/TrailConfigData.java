package it.hurts.octostudios.perception.common.modules.trail.config.data;

import it.hurts.shatterbyte.shatterlib.module.config.type.annotation.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joml.Vector3f;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrailConfigData {
    @Comment("Trail thickness")
    private float size = 0.1F;
    @Comment("Maximum trail length, measured in points that are placed every N ticks, where N is the value of updateFrequency")
    private int maxPoints = 5;
    @Comment("Minimum object movement speed to place trail points")
    private float minSpeed = 0.001F;
    @Comment("Frequency of trail point placement")
    private int updateFrequency = 1;
    @Comment("Starting color of the trail in ARGB format")
    private String fadeInColor = "FFFFFFFF";
    @Comment("Ending color of the trail in ARGB format")
    private String fadeOutColor = "FFFFFFFF";
    @Comment("Position offset of the trail relative to the object in XYZ")
    private Vector3f positionOffset = new Vector3f(0F, 0F, 0F);
    @Comment("Position offset of the trail in the opposite direction from the player's standing point")
    private float backwardShift = 0F;
    @Comment("Position offset of the trail in the opposite direction from the object's movement")
    private float motionShift = 0.25F;
}