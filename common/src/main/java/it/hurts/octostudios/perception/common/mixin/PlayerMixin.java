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

    @Unique
    private float perception$getPlayerMotion(Player player) {
        return (float) (player.getDeltaMovement().multiply(0.25F, 1F, 0.25F).length() * (player.getDeltaMovement().y() > 0 ? 1F : -1F));
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onPlayerTick(CallbackInfo info) {
        var player = (Player) (Object) this;

        if (!player.level().isClientSide())
            return;

        var minMotion = 0.5F;

        if (Math.abs(perception$getPlayerMotion(player)) > minMotion && !ShakeManager.SHAKES.containsKey(perception$UUID))
            ShakeManager.add(Shake.builder(player)
                    .amplitude(() -> Math.abs(perception$getPlayerMotion(player)) * (player.isFallFlying() ? 0.035F : 0.05F) * (perception$getPlayerMotion(player) > 0 ? 0.5F : 1F))
                    .removeCondition(() -> Math.abs(perception$getPlayerMotion(player)) < minMotion)
                    .duration(Integer.MAX_VALUE)
                    .uuid(perception$UUID)
                    .fadeOutTime(0)
                    .radius(1F)
                    .speed(5F)
                    .build());
    }
}