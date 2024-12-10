package it.hurts.octostudios.perception.common.mixin.trails.entity;

import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ExperienceOrb.class)
public abstract class ExperienceOrbMixin extends EntityMixin {
    @Override
    public double getTrailScale() {
        var entity = (ExperienceOrb) (Object) this;

        return super.getTrailScale() * ((entity.getIcon() + 1) / 3F);
    }
}