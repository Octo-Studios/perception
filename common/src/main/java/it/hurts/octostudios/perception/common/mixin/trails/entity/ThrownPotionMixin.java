package it.hurts.octostudios.perception.common.mixin.trails.entity;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.alchemy.PotionContents;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ThrownPotion.class)
public abstract class ThrownPotionMixin extends EntityMixin {
    @Override
    public int getTrailFadeInColor() {
        var entity = (ThrownPotion) (Object) this;

        return entity.getItem().getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).getColor();
    }
}