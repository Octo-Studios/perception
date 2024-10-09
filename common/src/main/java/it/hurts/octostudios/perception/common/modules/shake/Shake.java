package it.hurts.octostudios.perception.common.modules.shake;

import it.hurts.octostudios.perception.common.modules.shake.data.EntityShakeSource;
import it.hurts.octostudios.perception.common.modules.shake.data.PositionShakeSource;
import it.hurts.octostudios.perception.common.modules.shake.data.base.ShakeSource;
import lombok.Builder;
import lombok.Data;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

@Data
@Builder
public class Shake {
    private static final Random RANDOM = new Random();

    private ShakeSource source;

    @Builder.Default
    private Supplier<Float> radius;
    @Builder.Default
    private Supplier<Float> amplitude;
    @Builder.Default
    private Supplier<Float> speed;
    @Builder.Default
    private Supplier<Integer> duration;
    @Builder.Default
    private Supplier<Integer> fadeInTime;
    @Builder.Default
    private Supplier<Integer> fadeOutTime;

    @Nullable
    @Builder.Default
    private Supplier<Boolean> removeCondition = null;

    @Builder.Default
    private UUID uuid = UUID.randomUUID();

    private final Vec3 frequency = new Vec3(0.8F + RANDOM.nextFloat() * 0.4F, 0.8F + RANDOM.nextFloat() * 0.4F, 0.8F + RANDOM.nextFloat() * 0.4F);

    private final Vector3f lastShakeOffset = new Vector3f();

    private int elapsedTime;

    public static ShakeBuilder builder(ShakeSource source) {
        var builder = new ShakeBuilder();

        builder.source(source);

        return builder;
    }

    public static ShakeBuilder builder(Entity entity) {
        return builder(new EntityShakeSource(entity));
    }

    public static ShakeBuilder builder(Vec3 position) {
        return builder(new PositionShakeSource(position));
    }

    public boolean isFinished() {
        return removeCondition == null ? elapsedTime >= getDuration() : getRemoveCondition();
    }

    public void update() {
        elapsedTime++;
    }

    public Vector3f getShakeOffset(Player player, float partialTicks) {
        float amplitude = getCurrentAmplitude(player);
        float speed = getCurrentSpeed(player);

        if (amplitude <= 0F || speed <= 0F)
            return new Vector3f();

        float currentTime = (elapsedTime + partialTicks) / 20F;

        float shakeX = (float) Math.cos(2 * Math.PI * speed * frequency.x() * currentTime);
        float shakeY = (float) Math.cos(2 * Math.PI * speed * frequency.y() * currentTime);
        float shakeZ = (float) Math.cos(2 * Math.PI * speed * frequency.z() * currentTime);

        Vec3 direction = player.getEyePosition(partialTicks).subtract(source.getPos()).normalize();

        float offsetX = (float) (direction.x * shakeX * amplitude);
        float offsetY = (float) (direction.y * shakeY * amplitude);
        float offsetZ = (float) (direction.z * shakeZ * amplitude);

        float smoothingFactor = 0F;

        offsetX = Mth.lerp(smoothingFactor + partialTicks, lastShakeOffset.x(), offsetX);
        offsetY = Mth.lerp(smoothingFactor + partialTicks, lastShakeOffset.y(), offsetY);
        offsetZ = Mth.lerp(smoothingFactor + partialTicks, lastShakeOffset.z(), offsetZ);

        lastShakeOffset.set(offsetX, offsetY, offsetZ);

        return new Vector3f(offsetX, offsetY, offsetZ);
    }

    private float getCurrentAmplitude(Player player) {
        var distance = player.position().distanceTo(source.getPos());

        var duration = getDuration();

        var radius = getRadius();

        var fadeInTime = getFadeInTime();
        var fadeOutTime = getFadeOutTime();

        if (distance > radius)
            return 0F;

        var distanceFactor = (float) (1F - (distance / radius));

        float timeFactor;

        if (elapsedTime < fadeInTime)
            timeFactor = (float) elapsedTime / fadeInTime;
        else if (elapsedTime > duration - fadeOutTime)
            timeFactor = (float) (duration - elapsedTime) / fadeOutTime;
        else
            timeFactor = 1F;

        return getAmplitude() * distanceFactor * timeFactor;
    }

    private float getCurrentSpeed(Player player) {
        var distance = player.position().distanceTo(source.getPos());

        var radius = getRadius();

        if (distance > radius)
            return 0F;

        var distanceFactor = (float) (1F - (distance / radius));

        return getSpeed() * distanceFactor;
    }

    public float getRadius() {
        return radius.get();
    }

    public float getAmplitude() {
        return amplitude.get();
    }

    public float getSpeed() {
        return speed.get();
    }

    public int getDuration() {
        return duration.get();
    }

    public int getFadeInTime() {
        return fadeInTime.get();
    }

    public int getFadeOutTime() {
        return fadeOutTime.get() == -1 ? getDuration() - getFadeInTime() : fadeOutTime.get();
    }

    public boolean getRemoveCondition() {
        return removeCondition == null || removeCondition.get();
    }

    public static class ShakeBuilder {
        private Supplier<Float> radius = () -> 10F;
        private Supplier<Float> amplitude = () -> 1F;
        private Supplier<Float> speed = () -> 5F;
        private Supplier<Integer> duration = () -> 20;
        private Supplier<Integer> fadeInTime = () -> 0;
        private Supplier<Integer> fadeOutTime = () -> -1;

        // ===================================================
        // ==                  Lombok WTF?                  ==
        // ===================================================

        public ShakeBuilder radius(Supplier<Float> radius) {
            this.radius = radius;

            return this;
        }

        public ShakeBuilder amplitude(Supplier<Float> amplitude) {
            this.amplitude = amplitude;

            return this;
        }

        public ShakeBuilder speed(Supplier<Float> speed) {
            this.speed = speed;

            return this;
        }

        public ShakeBuilder duration(Supplier<Integer> duration) {
            this.duration = duration;

            return this;
        }

        public ShakeBuilder fadeInTime(Supplier<Integer> fadeInTime) {
            this.fadeInTime = fadeInTime;

            return this;
        }

        public ShakeBuilder fadeOutTime(Supplier<Integer> fadeOutTime) {
            this.fadeOutTime = fadeOutTime;

            return this;
        }

        // ===================================================

        private ShakeBuilder source(ShakeSource source) {
            this.source = source;

            return this;
        }

        public ShakeBuilder radius(float radius) {
            this.radius = () -> radius;

            return this;
        }

        public ShakeBuilder amplitude(float amplitude) {
            this.amplitude = () -> amplitude;

            return this;
        }

        public ShakeBuilder speed(float speed) {
            this.speed = () -> speed;

            return this;
        }

        public ShakeBuilder duration(int duration) {
            this.duration = () -> duration;

            return this;
        }

        public ShakeBuilder fadeInTime(int fadeInTime) {
            this.fadeInTime = () -> fadeInTime;

            return this;
        }

        public ShakeBuilder fadeOutTime(int fadeOutTime) {
            this.fadeOutTime = () -> fadeOutTime;

            return this;
        }

        private ShakeBuilder elapsedTime(int elapsedTime) {
            return this;
        }
    }
}