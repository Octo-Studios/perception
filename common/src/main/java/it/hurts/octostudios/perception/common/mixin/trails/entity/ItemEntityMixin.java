package it.hurts.octostudios.perception.common.mixin.trails.entity;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends EntityMixin {
    @Shadow
    public abstract ItemStack getItem();

    @Override
    public int getTrailFadeInColor() {
        var color = getItem().getRarity().color().getColor();

        return (color == null ? 0xFFFFFFFF : color & 0x00FFFFFF) | (0x80 << 24);
    }

    @Override
    public boolean isTrailGrowing() {
        return getItem().getRarity() != Rarity.COMMON && super.isTrailGrowing();
    }
}