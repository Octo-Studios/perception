package it.hurts.octostudios.perception.common.mixin;

import it.hurts.octostudios.perception.common.modules.shake.Shake;
import it.hurts.octostudios.perception.common.modules.shake.ShakeManager;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundExplodePacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
    @Inject(method = "handleExplosion", at = @At("HEAD"))
    private void onExplode(ClientboundExplodePacket clientboundExplodePacket, CallbackInfo ci) {
        float g = 0.5f;
        ShakeManager.add(Shake.builder(clientboundExplodePacket.center())
                .amplitude(0.5F + (g * 0.1F))
                .radius(7F + g * 2.5F)
                .duration(10)
                .speed(7F)
                .build());
    }
}
