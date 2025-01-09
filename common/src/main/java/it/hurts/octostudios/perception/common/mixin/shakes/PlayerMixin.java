package it.hurts.octostudios.perception.common.mixin.shakes;

import it.hurts.octostudios.perception.common.init.ConfigRegistry;
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
    private static float perception$getPlayerSpeed(Player player) {
        var motion = player.getDeltaMovement();

        return (float) Math.abs(motion.multiply(0.15F, motion.y() >= 0F ? 0.5F : 1F, 0.15F).length());
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void onPlayerTick(CallbackInfo info) {
        var player = (Player) (Object) this;

        if (!player.level().isClientSide())
            return;

        var config = ConfigRegistry.SHAKE_CONFIG.getFallShakes();

        var baseIntensity = config.getIntensity();
        var minSpeed = config.getMinSpeed();

        if (baseIntensity <= 0F)
            return;

        var multiplier = 1F;

        if (player.isFallFlying())
            multiplier *= 0.2F;

        if (perception$getPlayerSpeed(player) > minSpeed && !ShakeManager.SHAKES.containsKey(perception$UUID)) {
            var intensity = multiplier * baseIntensity;

            ShakeManager.add(Shake.builder(player)
                    .amplitude(() -> (float) (Math.tanh(((Math.min(perception$getPlayerSpeed(player) - minSpeed, 0.75F) * 0.05F) + (player.fallDistance * 0.0005F))) * intensity))
                    .speed(() -> (float) (4F + Math.log1p(((perception$getPlayerSpeed(player) - minSpeed) * 0.0075F) + (player.fallDistance * 0.0005F)) * intensity))
                    .removeCondition(() -> perception$getPlayerSpeed(player) < minSpeed)
                    .duration(Integer.MAX_VALUE)
                    .uuid(perception$UUID)
                    .fadeOutTime(0)
                    .radius(1F)
                    .build());
        }
    }
}