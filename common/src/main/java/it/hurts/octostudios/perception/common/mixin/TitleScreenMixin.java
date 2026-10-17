package it.hurts.octostudios.perception.common.mixin;

import it.hurts.octostudios.perception.common.Perception;
import it.hurts.shatterbyte.shatterlib.client.config.MultipleConfigScreen;
import it.hurts.shatterbyte.shatterlib.platform.ShatterLibServices;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Component title) {
        super(title);
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void injected(CallbackInfo ci) {
        if (!ShatterLibServices.platform().isDevelopmentEnvironment()) {
            return;
        }

        TitleScreen thisScreen = (TitleScreen) (Object) this;
        addRenderableWidget(Button.builder(
                Component.literal("Perception Config"),
                button -> Minecraft.getInstance().gui.setScreen(new MultipleConfigScreen(Perception.MODID, thisScreen))
        ).bounds(thisScreen.width / 2 - 50, 2, 100, 20).build());
    }
}
