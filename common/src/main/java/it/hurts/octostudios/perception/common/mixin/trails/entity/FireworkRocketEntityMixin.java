package it.hurts.octostudios.perception.common.mixin.trails.entity;

import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;

@Mixin(FireworkRocketEntity.class)
public abstract class FireworkRocketEntityMixin extends EntityMixin {
    @Shadow
    public abstract ItemStack getItem ();

    @Override
    public int getTrailFadeInColor() {
        var entity = (FireworkRocketEntity) (Object) this;

        var stack = getItem();
        if (stack.isEmpty()) return 0xFFFFFFFF;

        var tag = stack.getTagElement("Fireworks");
        if (tag == null) return 0xFFFFFFFF;

        var explosions = tag.getList("Explosions", 10);
        int maxSize = explosions.size();
        if (maxSize == 0) return 0xFFFFFFFF;

        var colors = new ArrayList<Integer>();
        for (int i = 0; i < maxSize; i++) {
            var explosion = explosions.getCompound(i);
            int[] explosionColors = explosion.getIntArray("Colors");
            for (var color : explosionColors) {
                colors.add(color);
            }
        }

        int count = colors.size();
        if (count == 0) return 0xFFFFFFFF;
        if (count < 2)
            return (colors.get(0) & 0x00FFFFFF) | (0xFF << 24);

        var totalTime = count * 3;
        var tick = entity.tickCount % totalTime;
        var t = (float) tick / totalTime * count;
        var index = (int) Math.floor(t) % count;
        var fraction = t - (int) t;

        var color1 = colors.get(index);
        var color2 = colors.get((index + 1) % count);

        int r1 = (color1 >> 16) & 0xFF;
        int g1 = (color1 >> 8) & 0xFF;
        int b1 = color1 & 0xFF;

        int r2 = (color2 >> 16) & 0xFF;
        int g2 = (color2 >> 8) & 0xFF;
        int b2 = color2 & 0xFF;

        int r = (int) (r1 + (r2 - r1) * fraction);
        int g = (int) (g1 + (g2 - g1) * fraction);
        int b = (int) (b1 + (b2 - b1) * fraction);

        return ((r << 16) | (g << 8) | b) | (0xFF << 24);
    }
}
