package it.hurts.octostudios.perception.common.modules.shake.data;

import it.hurts.octostudios.perception.common.modules.shake.data.base.ShakeSource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minecraft.world.phys.Vec3;

@Data
@EqualsAndHashCode(callSuper = true)
public class PositionShakeSource extends ShakeSource {
    private final Vec3 source;

    @Override
    public Vec3 getPos() {
        return source;
    }
}