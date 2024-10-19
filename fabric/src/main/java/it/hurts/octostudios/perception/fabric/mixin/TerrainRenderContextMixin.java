package it.hurts.octostudios.perception.fabric.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.fabricmc.fabric.impl.client.indigo.renderer.render.TerrainRenderContext;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(TerrainRenderContext.class)
public class TerrainRenderContextMixin {
    @Inject(method = "tessellateBlock", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/level/block/state/BlockState;getOffset(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/phys/Vec3;"), locals = LocalCapture.CAPTURE_FAILSOFT)
    public void injectBlockRotation(BlockState blockState, BlockPos blockPos, BakedModel model, PoseStack matrixStack, CallbackInfo ci, Vec3 offset) {
        if (offset.equals(Vec3.ZERO))
            return;

        matrixStack.translate(0.5 + offset.x, 0.5 + offset.y, 0.5 + offset.z);

        matrixStack.mulPose(Axis.YP.rotation((float) (blockPos.atY(0).asLong() * offset.length())));

        matrixStack.translate(-0.5 - offset.x, -0.5 - offset.y, -0.5 - offset.z);
    }
}