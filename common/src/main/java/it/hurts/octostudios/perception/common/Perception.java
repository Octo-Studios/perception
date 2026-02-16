package it.hurts.octostudios.perception.common;

import it.hurts.octostudios.perception.common.init.ConfigRegistry;

public class Perception {
    public static final String MODID = "perception";

    public static void init() {
        ConfigRegistry.registerCommon();
    }
}