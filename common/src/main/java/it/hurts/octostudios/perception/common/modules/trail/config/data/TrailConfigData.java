package it.hurts.octostudios.perception.common.modules.trail.config.data;

import it.hurts.octostudios.octolib.modules.config.annotations.Prop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joml.Vector3f;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrailConfigData {
    @Prop(comment = "Trail thickness")
    private float size = 0.1F;
    @Prop(comment = "Maximum trail length, measured in points that are placed every N ticks, where N is the value of updateFrequency")
    private int maxPoints = 5;
    @Prop(comment = "Minimum object movement speed to place trail points")
    private float minSpeed = 0.001F;
    @Prop(comment = "Frequency of trail point placement")
    private int updateFrequency = 1;
    @Prop(comment = "Starting color of the trail in ARGB format")
    private String fadeInColor = "FFFFFFFF";
    @Prop(comment = "Ending color of the trail in ARGB format")
    private String fadeOutColor = "FFFFFFFF";
    @Prop(comment = "Position offset of the trail relative to the object in XYZ")
    private Vector3f positionOffset = new Vector3f(0F, 0F, 0F);
}