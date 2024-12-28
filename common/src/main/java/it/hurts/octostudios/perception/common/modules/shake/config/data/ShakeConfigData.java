package it.hurts.octostudios.perception.common.modules.shake.config.data;

import it.hurts.octostudios.octolib.modules.config.annotations.Prop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShakeConfigData {
    @Prop
    @Builder.Default
    private float radius = -1F;

    @Prop
    @Builder.Default
    private float rotationAmplitude = 0.1F;
    @Prop
    @Builder.Default
    private float offsetAmplitude = 0.1F;
    @Prop
    @Builder.Default
    private float fovAmplitude = 0.1F;

    @Prop
    @Builder.Default
    private float rotationSpeed = 5F;
    @Prop
    @Builder.Default
    private float offsetSpeed = 5F;
    @Prop
    @Builder.Default
    private float fovSpeed = 5F;

    @Prop
    @Builder.Default
    private int duration = 10;

    @Prop
    @Builder.Default
    private int fadeInTime = 0;
    @Prop
    @Builder.Default
    private int fadeOutTime = -1;

    public static class ShakeConfigDataBuilder {
        private float rotationAmplitude = 0.1F;
        private float offsetAmplitude = 0.1F;
        private float fovAmplitude = 0.1F;

        private float rotationSpeed = 5F;
        private float offsetSpeed = 5F;
        private float fovSpeed = 5F;

        public ShakeConfigDataBuilder amplitude(float rotationAmplitude, float offsetAmplitude, float fovAmplitude) {
            this.rotationAmplitude = rotationAmplitude;
            this.offsetAmplitude = offsetAmplitude;
            this.fovAmplitude = fovAmplitude;

            return this;
        }

        public ShakeConfigDataBuilder amplitude(float amplitude) {
            return amplitude(amplitude, amplitude, amplitude);
        }

        public ShakeConfigDataBuilder speed(float rotationSpeed, float offsetSpeed, float fovSpeed) {
            this.rotationSpeed = rotationSpeed;
            this.offsetSpeed = offsetSpeed;
            this.fovSpeed = fovSpeed;

            return this;
        }

        public ShakeConfigDataBuilder speed(float speed) {
            return speed(speed, speed, speed);
        }
    }
}