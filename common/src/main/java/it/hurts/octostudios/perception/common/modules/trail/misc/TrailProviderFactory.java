package it.hurts.octostudios.perception.common.modules.trail.misc;

import it.hurts.octostudios.octolib.module.particle.trail.EntityTrailProvider;
import it.hurts.octostudios.perception.common.modules.trail.config.data.TrailConfigData;
import it.hurts.octostudios.perception.common.modules.trail.misc.wrapper.ArrowTrailWrapper;
import it.hurts.octostudios.perception.common.modules.trail.misc.wrapper.ExperienceOrbTrailWrapper;
import it.hurts.octostudios.perception.common.modules.trail.misc.wrapper.FireworkRocketTrailWrapper;
import it.hurts.octostudios.perception.common.modules.trail.misc.wrapper.ThrownPotionTrailWrapper;
import it.hurts.octostudios.perception.common.modules.trail.misc.wrapper.base.TrailWrapper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.ThrownPotion;

public final class TrailProviderFactory {
    public static EntityTrailProvider<?> create(Entity entity, TrailConfigData data) {
        return switch (entity) {
            case Arrow arrow -> new ArrowTrailWrapper(arrow, data);
            case ExperienceOrb orb -> new ExperienceOrbTrailWrapper(orb, data);
            case ThrownPotion potion -> new ThrownPotionTrailWrapper(potion, data);
            case FireworkRocketEntity rocket -> new FireworkRocketTrailWrapper(rocket, data);
            default -> new TrailWrapper<>(entity, data);
        };
    }
}