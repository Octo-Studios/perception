package it.hurts.octostudios.perception.common;

import dev.architectury.event.events.common.LifecycleEvent;
import it.hurts.octostudios.perception.common.init.ConfigRegistry;

public class Perception {
    public static final String MODID = "perception";

    public static void init() {
        LifecycleEvent.SETUP.register(ConfigRegistry::registerCommon);
    }
}