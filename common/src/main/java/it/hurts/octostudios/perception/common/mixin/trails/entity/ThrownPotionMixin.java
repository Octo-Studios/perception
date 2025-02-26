package it.hurts.octostudios.perception.common.mixin.trails.entity;

import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.alchemy.PotionUtils;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ThrownPotion.class)
public abstract class ThrownPotionMixin extends EntityMixin {
    @Override
    public int getTrailFadeInColor() {
        var entity = (ThrownPotion) (Object) this;
        var stack = entity.getItem();

        return PotionUtils.getColor(stack);
    }
}