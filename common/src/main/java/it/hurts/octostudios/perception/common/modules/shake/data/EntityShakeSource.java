package it.hurts.octostudios.perception.common.modules.shake.data;

import it.hurts.octostudios.perception.common.modules.shake.data.base.ShakeSource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

@Data
@EqualsAndHashCode(callSuper = true)
public class EntityShakeSource extends ShakeSource {
    private final Entity source;

    @Override
    public Vec3 getPos() {
        return source.position();
    }
}