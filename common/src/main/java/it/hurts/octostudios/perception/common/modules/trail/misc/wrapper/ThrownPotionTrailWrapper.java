package it.hurts.octostudios.perception.common.modules.trail.misc.wrapper;

import it.hurts.octostudios.perception.common.modules.trail.config.data.TrailConfigData;
import it.hurts.octostudios.perception.common.modules.trail.misc.wrapper.base.TrailWrapper;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.alchemy.PotionContents;

public class ThrownPotionTrailWrapper extends TrailWrapper<ThrownPotion> {
    public ThrownPotionTrailWrapper(ThrownPotion entity, TrailConfigData data) {
        super(entity, data);
    }

    @Override
    public int getTrailFadeInColor() {
        return entity.getItem().getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).getColor();
    }
}