package it.hurts.octostudios.perception.neoforge;

import it.hurts.octostudios.perception.common.PerceptionClient;
import net.neoforged.bus.api.IEventBus;

public final class PerceptionNeoForgeClient {
    public PerceptionNeoForgeClient(IEventBus modBus) {
        PerceptionClient.init();
    }
}
