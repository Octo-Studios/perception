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
    private Supplier<Float> rotationAmplitude;
    @Builder.Default
    private Supplier<Float> offsetAmplitude;
    @Builder.Default
    private Supplier<Float> fovAmplitude;
    @Builder.Default
    private Supplier<Float> rotationSpeed;
    @Builder.Default
    private Supplier<Float> offsetSpeed;
    @Builder.Default
    private Supplier<Float> fovSpeed;
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

        float currentTime = elapsedTime / 20F;

        var rotationAmplitude = getCumulativeRotationAmplitude(player);
        var rotationSpeed = getCumulativeRotationSpeed(player);

        if (rotationAmplitude > 0F && rotationSpeed > 0F)
            currentTickRotation.set(computeRotationForTick(player, rotationAmplitude, rotationSpeed, currentTime));
        else {
            currentTickRotation.set(0, 0, 0);
            lastTickRotation.set(0, 0, 0);
        }

        var offsetAmplitude = getCumulativeOffsetAmplitude(player);
        var offsetSpeed = getCumulativeOffsetSpeed(player);

        if (offsetAmplitude > 0F && offsetSpeed > 0F)
            currentTickOffset.set(computeOffsetForTick(player, offsetAmplitude, offsetSpeed, currentTime));
        else {
            currentTickOffset.set(0, 0, 0);
            lastTickOffset.set(0, 0, 0);
        }
    }

    public Vector3f getShakeOffset(Player player, float partialTicks) {
        var x = Mth.lerp(partialTicks, lastTickOffset.x(), currentTickOffset.x());
        var y = Mth.lerp(partialTicks, lastTickOffset.y(), currentTickOffset.y());
        var z = Mth.lerp(partialTicks, lastTickOffset.z(), currentTickOffset.z());

        return new Vector3f(x, y, z);
    }

    public Vector3f getShakeRotation(Player player, float partialTicks) {
        var x = Mth.lerp(partialTicks, lastTickRotation.x(), currentTickRotation.x());
        var y = Mth.lerp(partialTicks, lastTickRotation.y(), currentTickRotation.y());
        var z = Mth.lerp(partialTicks, lastTickRotation.z(), currentTickRotation.z());

        return new Vector3f(x, y, z);
    }

    public float getShakeFOV(Player player, float partialTicks) {
        return getCumulativeFovAmplitude(player);
    }

    private Vector3f computeOffsetForTick(Player player, float amplitude, float speed, float currentTime) {
        var wave = Math.sin(2 * Math.PI * speed * currentTime);

        var direction = player.position().add(0, player.getEyeHeight(), 0)
                .subtract(source.getPos())
                .normalize();

        var offsetX = (float) (direction.x * amplitude * wave);
        var offsetY = (float) (direction.y * amplitude * wave);
        var offsetZ = (float) (direction.z * amplitude * wave);

        return new Vector3f(offsetX, offsetY, offsetZ);
    }

    private Vector3f computeRotationForTick(Player player, float amplitude, float speed, float currentTime) {
        var playerView = player.getLookAngle();

        var pitchFactor = (float) playerView.dot(new Vec3(0, 1, 0));
        var yawFactor = (float) playerView.cross(new Vec3(0, 1, 0)).dot(source.getPos().subtract(player.position()).normalize());

        var angleX = (float) Math.sin(2 * Math.PI * speed * frequency.x() * currentTime) * amplitude * pitchFactor;
        var angleY = (float) Math.sin(2 * Math.PI * speed * frequency.y() * currentTime) * amplitude * yawFactor;
        var angleZ = (float) Math.sin(2 * Math.PI * speed * frequency.z() * currentTime) * amplitude;

        return new Vector3f(angleX, angleY, angleZ);
    }

    public float getCumulativeRotationAmplitude(Player player) {
        return getCumulativeAmplitude(player, getRotationAmplitude());
    }

    public float getCumulativeOffsetAmplitude(Player player) {
        return getCumulativeAmplitude(player, getOffsetAmplitude());
    }

    public float getCumulativeFovAmplitude(Player player) {
        return getCumulativeAmplitude(player, getFovAmplitude());
    }

    private float getCumulativeAmplitude(Player player, float amplitude) {
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

        return amplitude * distanceFactor * timeFactor;
    }

    public float getCumulativeRotationSpeed(Player player) {
        return getCumulativeSpeed(player, getRotationSpeed());
    }

    public float getCumulativeOffsetSpeed(Player player) {
        return getCumulativeSpeed(player, getOffsetSpeed());
    }

    public float getCumulativeFovSpeed(Player player) {
        return getCumulativeSpeed(player, getFovSpeed());
    }

    private float getCumulativeSpeed(Player player, float speed) {
        var distance = player.position().distanceTo(source.getPos());

        var radius = getRadius();

        if (distance > radius)
            return 0F;

        var distanceFactor = (float) (1F - (distance / radius));

        return speed * distanceFactor;
    }

    public float getRadius() {
        return radius.get();
    }

    public float getRotationAmplitude() {
        return rotationAmplitude.get();
    }

    public float getOffsetAmplitude() {
        return offsetAmplitude.get();
    }

    public float getFovAmplitude() {
        return fovAmplitude.get();
    }

    public float getRotationSpeed() {
        return rotationSpeed.get();
    }

    public float getOffsetSpeed() {
        return offsetSpeed.get();
    }

    public float getFovSpeed() {
        return fovSpeed.get();
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
        private Supplier<Float> rotationAmplitude = () -> 1F;
        private Supplier<Float> offsetAmplitude = () -> 1F;
        private Supplier<Float> fovAmplitude = () -> 1F;
        private Supplier<Float> rotationSpeed = () -> 5F;
        private Supplier<Float> offsetSpeed = () -> 5F;
        private Supplier<Float> fovSpeed = () -> 5F;
        private Supplier<Integer> duration = () -> 20;
        private Supplier<Integer> fadeInTime = () -> 0;
        private Supplier<Integer> fadeOutTime = () -> -1;

        public ShakeBuilder radius(Supplier<Float> radius) {
            this.radius = radius;

            return this;
        }

        public ShakeBuilder amplitude(Supplier<Float> rotationAmplitude, Supplier<Float> offsetAmplitude, Supplier<Float> fovAmplitude) {
            this.rotationAmplitude = rotationAmplitude;
            this.offsetAmplitude = offsetAmplitude;
            this.fovAmplitude = fovAmplitude;

            return this;
        }

        public ShakeBuilder amplitude(Supplier<Float> amplitude) {
            return amplitude(amplitude, amplitude, amplitude);
        }

        public ShakeBuilder rotationAmplitude(Supplier<Float> amplitude) {
            this.rotationAmplitude = amplitude;

            return this;
        }

        public ShakeBuilder offsetAmplitude(Supplier<Float> amplitude) {
            this.offsetAmplitude = amplitude;

            return this;
        }

        public ShakeBuilder fovAmplitude(Supplier<Float> amplitude) {
            this.fovAmplitude = amplitude;

            return this;
        }

        public ShakeBuilder speed(Supplier<Float> rotationSpeed, Supplier<Float> offsetSpeed, Supplier<Float> fovSpeed) {
            this.rotationSpeed = rotationSpeed;
            this.offsetSpeed = offsetSpeed;
            this.fovSpeed = fovSpeed;

            return this;
        }

        public ShakeBuilder speed(Supplier<Float> speed) {
            return speed(speed, speed, speed);
        }

        public ShakeBuilder rotationSpeed(Supplier<Float> speed) {
            this.rotationSpeed = speed;

            return this;
        }

        public ShakeBuilder offsetSpeed(Supplier<Float> speed) {
            this.offsetSpeed = speed;

            return this;
        }

        public ShakeBuilder fovSpeed(Supplier<Float> speed) {
            this.fovSpeed = speed;

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

        private ShakeBuilder source(ShakeSource source) {
            this.source = source;

            return this;
        }

        public ShakeBuilder radius(float radius) {
            this.radius = () -> radius;

            return this;
        }

        public ShakeBuilder amplitude(float rotationAmplitude, float offsetAmplitude, float fovAmplitude) {
            return amplitude(() -> rotationAmplitude, () -> offsetAmplitude, () -> fovAmplitude);
        }

        public ShakeBuilder amplitude(float amplitude) {
            return amplitude(amplitude, amplitude, amplitude);
        }

        public ShakeBuilder rotationAmplitude(float amplitude) {
            return rotationAmplitude(() -> amplitude);
        }

        public ShakeBuilder offsetAmplitude(float amplitude) {
            return offsetAmplitude(() -> amplitude);
        }

        public ShakeBuilder fovAmplitude(float amplitude) {
            return fovAmplitude(() -> amplitude);
        }

        public ShakeBuilder speed(float rotationSpeed, float offsetSpeed, float fovSpeed) {
            return speed(() -> rotationSpeed, () -> offsetSpeed, () -> fovSpeed);
        }

        public ShakeBuilder speed(float speed) {
            return speed(speed, speed, speed);
        }

        public ShakeBuilder rotationSpeed(float speed) {
            return rotationSpeed(() -> speed);
        }

        public ShakeBuilder offsetSpeed(float speed) {
            return offsetSpeed(() -> speed);
        }

        public ShakeBuilder fovSpeed(float speed) {
            return fovSpeed(() -> speed);
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