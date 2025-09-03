package it.hurts.octostudios.perception.common.modules.trail.misc.wrapper;

import it.hurts.octostudios.perception.common.modules.trail.config.data.TrailConfigData;
import it.hurts.octostudios.perception.common.modules.trail.misc.wrapper.base.TrailWrapper;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;

import java.util.ArrayList;

public class FireworkRocketTrailWrapper extends TrailWrapper<FireworkRocketEntity> {
    public FireworkRocketTrailWrapper(FireworkRocketEntity entity, TrailConfigData data) {
        super(entity, data);
    }

    @Override
    public int getTrailFadeInColor() {
        var explosions = entity.getExplosions();

        var maxSize = explosions.size();

        if (maxSize == 0)
            return 0xFFFFFFFF;

        var colors = new ArrayList<Integer>();

        for (var explosion : explosions)
            colors.addAll(explosion.colors());

        int count = colors.size();

        if (count < 2)
            return colors.isEmpty() ? 0xFFFFFFFF : (colors.getFirst() & 0x00FFFFFF) | (0xFF << 24);

        var totalTime = count * 3;
        var tick = entity.tickCount % totalTime;

        var t = (float) tick / totalTime * colors.size();
        var index = (int) Math.floor(t) % colors.size();
        var fraction = t - (int) t;

        var color1 = colors.get(index);
        var color2 = colors.get((index + 1) % colors.size());

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