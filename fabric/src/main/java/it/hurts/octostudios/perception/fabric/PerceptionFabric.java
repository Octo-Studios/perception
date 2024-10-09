package it.hurts.octostudios.perception.fabric;

import it.hurts.octostudios.perception.common.Perception;
import net.fabricmc.api.ModInitializer;

public final class PerceptionFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Perception.init();
    }
}
