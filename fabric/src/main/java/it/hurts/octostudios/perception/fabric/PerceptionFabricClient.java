package it.hurts.octostudios.perception.fabric;

import it.hurts.octostudios.perception.common.PerceptionClient;
import net.fabricmc.api.ClientModInitializer;

public final class PerceptionFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PerceptionClient.init();
    }
}
