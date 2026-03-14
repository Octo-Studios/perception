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
            var type = entities.get(key);

            if (type == null)
                continue;

            EntityTrailRegistry.registerProvider(type, entity -> {
                var data = ConfigRegistry.TRAIL_CONFIG.getEntityTrails().get(key);

                if (data == null)
                    return null;

                return (EntityTrailProvider) TrailProviderFactory.create(entity, data);
            });
        }
    }
}
