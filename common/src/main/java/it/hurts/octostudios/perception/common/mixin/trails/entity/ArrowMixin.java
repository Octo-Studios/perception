package it.hurts.octostudios.perception.common.mixin.trails.entity;

import net.minecraft.world.entity.projectile.Arrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Arrow.class)
public abstract class ArrowMixin extends EntityMixin {
    @Shadow
    public abstract int getColor();

    @Override
    public int getTrailFadeInColor() {
        return getColor();
    }
}