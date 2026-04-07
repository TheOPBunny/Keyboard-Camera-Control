package com.hassanmod.cameracontrol.mixin;

import com.hassanmod.cameracontrol.CameraControlClient;
import com.hassanmod.cameracontrol.CameraKeyBindings;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class CameraControlMixin {

    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(RenderTickCounter tickCounter, boolean tick, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.currentScreen != null) return;

        float speed = CameraControlClient.config.cameraSpeed;

        if (CameraKeyBindings.lookUp.isPressed())    client.player.setPitch(client.player.getPitch() - speed);
        if (CameraKeyBindings.lookDown.isPressed())  client.player.setPitch(client.player.getPitch() + speed);
        if (CameraKeyBindings.lookLeft.isPressed())  client.player.setYaw(client.player.getYaw() - speed);
        if (CameraKeyBindings.lookRight.isPressed()) client.player.setYaw(client.player.getYaw() + speed);

        float pitch = client.player.getPitch();
        if (pitch < -90f) client.player.setPitch(-90f);
        if (pitch > 90f)  client.player.setPitch(90f);

        if (CameraKeyBindings.speedUp.wasPressed()) {
            CameraControlClient.config.cameraSpeed = Math.min(
                    CameraControlClient.config.cameraSpeed + CameraControlClient.SPEED_STEP,
                    CameraControlClient.SPEED_MAX
            );
            CameraControlClient.config.save();
            showSpeed(client, CameraControlClient.config.cameraSpeed);
        }
        if (CameraKeyBindings.speedDown.wasPressed()) {
            CameraControlClient.config.cameraSpeed = Math.max(
                    CameraControlClient.config.cameraSpeed - CameraControlClient.SPEED_STEP,
                    CameraControlClient.SPEED_MIN
            );
            CameraControlClient.config.save();
            showSpeed(client, CameraControlClient.config.cameraSpeed);
        }
    }

    private void showSpeed(MinecraftClient client, float speed) {
        if (client.inGameHud != null) {
            client.inGameHud.getChatHud().addMessage(
                    Text.literal(String.format("[Camera Control] Speed: %.1f", speed))
            );
        }
    }
}
