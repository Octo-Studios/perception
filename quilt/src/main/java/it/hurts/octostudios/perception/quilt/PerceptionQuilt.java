package it.hurts.octostudios.perception.quilt;

import it.hurts.octostudios.perception.common.Perception;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public final class PerceptionQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        Perception.init();
    }
}