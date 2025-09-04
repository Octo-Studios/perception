package it.hurts.octostudios.perception.common;

import it.hurts.octostudios.octolib.module.particle.trail.EntityTrailProvider;
import it.hurts.octostudios.octolib.module.particle.trail.EntityTrailRegistry;
import it.hurts.octostudios.perception.common.init.ConfigRegistry;
import it.hurts.octostudios.perception.common.modules.trail.misc.TrailProviderFactory;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.stream.Collectors;

public class PerceptionClient {
    public static void init() {
        var entities = BuiltInRegistries.ENTITY_TYPE.keySet().stream().collect(Collectors.toMap(ResourceLocation::toString, BuiltInRegistries.ENTITY_TYPE::get));

        for (var entry : ConfigRegistry.TRAIL_CONFIG.getEntityTrails().entrySet()) {
            var key = entry.getKey();
            var data = entry.getValue();

            var type = entities.get(key);

            if (type == null || type.isEmpty())
                continue;

            EntityTrailRegistry.registerProvider(type.get().value(), entity -> (EntityTrailProvider) TrailProviderFactory.create(entity, data));
        }
    }
}