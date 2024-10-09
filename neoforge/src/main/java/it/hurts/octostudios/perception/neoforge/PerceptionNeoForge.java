package it.hurts.octostudios.perception.neoforge;

import it.hurts.octostudios.perception.common.Perception;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(Perception.MODID)
public final class PerceptionNeoForge {
    public PerceptionNeoForge(IEventBus modBus) {
        Perception.init();

        if (FMLEnvironment.dist == Dist.CLIENT)
            new PerceptionNeoForgeClient(modBus);
    }
}
