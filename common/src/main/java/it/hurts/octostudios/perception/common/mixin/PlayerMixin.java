package it.hurts.octostudios.perception.common.mixin;

import it.hurts.octostudios.perception.common.modules.shake.Shake;
import it.hurts.octostudios.perception.common.modules.shake.ShakeManager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(Player.class)
public class PlayerMixin {
    @Unique
    private static final UUID perception$UUID = UUID.fromString("dbf928a5-6efe-43ea-acfd-3e0d2d6eaead");

    @Inject(method = "tick", at = @At("HEAD"))
    private void onPlayerTick(CallbackInfo info) {
        var player = (Player) (Object) this;

        if (!player.level().isClientSide())
            return;

        var minFallDistance = 1F;
        var maxFallDistance = 20F;

        if (player.fallDistance > minFallDistance && !ShakeManager.SHAKES.containsKey(perception$UUID))
            ShakeManager.add(Shake.builder(player)
                    .speed(() -> 5F + Math.min(maxFallDistance, player.fallDistance - minFallDistance) * 0.1F)
                    .amplitude(() -> Math.min(maxFallDistance, player.fallDistance - minFallDistance) * 0.015F)
                    .removeCondition(() -> player.fallDistance < minFallDistance)
                    .duration(Integer.MAX_VALUE)
                    .uuid(perception$UUID)
                    .fadeOutTime(0)
                    .radius(1F)
                    .build());
    }
}