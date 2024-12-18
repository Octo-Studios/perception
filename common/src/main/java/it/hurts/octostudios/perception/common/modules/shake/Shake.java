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

    private final Vector3f lastTickOffset = new Vector3f();
    private final Vector3f currentTickOffset = new Vector3f();

    private final Vector3f lastTickRotation = new Vector3f();
    private final Vector3f currentTickRotation = new Vector3f();

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

    public void update(Player player) {
        elapsedTime++;

        lastTickOffset.set(currentTickOffset);
        lastTickRotation.set(currentTickRotation);

        float amplitude = getCurrentAmplitude(player);
        float speed = getCurrentSpeed(player);

        float currentTime = elapsedTime / 20F;

        if (amplitude > 0F && speed > 0F) {
            currentTickOffset.set(computeOffsetForTick(player, amplitude, speed, currentTime));
            currentTickRotation.set(computeRotationForTick(player, amplitude, speed, currentTime));
        } else {
            currentTickOffset.set(0, 0, 0);
            lastTickOffset.set(0, 0, 0);

            currentTickRotation.set(0, 0, 0);
            lastTickRotation.set(0, 0, 0);
        }
    }

    public Vector3f getShakeOffset(Player player, float partialTicks) {
        float x = Mth.lerp(partialTicks, lastTickOffset.x(), currentTickOffset.x());
        float y = Mth.lerp(partialTicks, lastTickOffset.y(), currentTickOffset.y());
        float z = Mth.lerp(partialTicks, lastTickOffset.z(), currentTickOffset.z());

        return new Vector3f(x, y, z);
    }

    public Vector3f getShakeRotation(Player player, float partialTicks) {
        float x = Mth.lerp(partialTicks, lastTickRotation.x(), currentTickRotation.x());
        float y = Mth.lerp(partialTicks, lastTickRotation.y(), currentTickRotation.y());
        float z = Mth.lerp(partialTicks, lastTickRotation.z(), currentTickRotation.z());

        return new Vector3f(x, y, z);
    }

    private Vector3f computeOffsetForTick(Player player, float amplitude, float speed, float currentTime) {
        double wave = Math.sin(2 * Math.PI * speed * currentTime);
        Vec3 direction = player.position().add(0, player.getEyeHeight(), 0)
                .subtract(source.getPos())
                .normalize();

        float offsetX = (float) (direction.x * amplitude * wave);
        float offsetY = (float) (direction.y * amplitude * wave);
        float offsetZ = (float) (direction.z * amplitude * wave);

        return new Vector3f(offsetX, offsetY, offsetZ);
    }

    private Vector3f computeRotationForTick(Player player, float amplitude, float speed, float currentTime) {
        float angleX = (float) Math.sin(2 * Math.PI * speed * frequency.x() * currentTime) * amplitude;
        float angleY = (float) Math.sin(2 * Math.PI * speed * frequency.y() * currentTime) * amplitude;
        float angleZ = (float) Math.sin(2 * Math.PI * speed * frequency.z() * currentTime) * amplitude;

        return new Vector3f(angleX, angleY, angleZ);
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