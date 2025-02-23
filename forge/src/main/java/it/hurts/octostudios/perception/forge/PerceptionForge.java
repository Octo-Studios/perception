package it.hurts.octostudios.perception.forge;

import it.hurts.octostudios.perception.common.Perception;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(Perception.MODID)
public final class PerceptionForge {
    public PerceptionForge() {
        Perception.init();

        if (FMLEnvironment.dist == Dist.CLIENT)
            new PerceptionForgeClient();
    }
}
