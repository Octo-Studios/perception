package it.hurts.octostudios.perception.common.mixin.trails.entity;

import net.minecraft.world.entity.ExperienceOrb;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ExperienceOrb.class)
public abstract class ExperienceOrbMixin extends EntityMixin {
    @Override
    public double getTrailScale() {
        var entity = (ExperienceOrb) (Object) this;

        var base = super.getTrailScale();

        return base + (base * entity.getIcon() * 0.015F);
    }
}