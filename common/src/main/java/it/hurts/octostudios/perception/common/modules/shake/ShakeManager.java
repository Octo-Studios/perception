package it.hurts.octostudios.perception.common.modules.shake;

import java.util.*;

public class ShakeManager {
    public static final Map<UUID, Shake> SHAKES = new HashMap<>();

    public static void add(Shake shake) {
        SHAKES.put(shake.getUuid(), shake);
    }
}