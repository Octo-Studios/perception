package it.hurts.octostudios.perception.quilt;

import it.hurts.octostudios.perception.common.PerceptionClient;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer;

public final class PerceptionQuiltClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(ModContainer mod) {
        PerceptionClient.init();
    }
}