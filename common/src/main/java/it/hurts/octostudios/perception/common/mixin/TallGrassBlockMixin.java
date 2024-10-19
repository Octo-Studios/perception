package it.hurts.octostudios.perception.common.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TallGrassBlock.class)
public class TallGrassBlockMixin {
    @Inject(method = "getShape", at = @At("RETURN"), cancellable = true)
    protected void getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        Vec3 offset = state.getOffset(level, pos);

        cir.setReturnValue(cir.getReturnValue().move(offset.x, offset.y, offset.z));
    }
}