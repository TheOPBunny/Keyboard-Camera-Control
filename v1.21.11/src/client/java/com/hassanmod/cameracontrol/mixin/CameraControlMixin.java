package com.hassanmod.cameracontrol.mixin;

import com.hassanmod.cameracontrol.CameraControlClient;
import com.hassanmod.cameracontrol.CameraKeyBindings;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class CameraControlMixin {

    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(DeltaTracker deltaTracker, boolean tick, CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();
        if (client.player == null || client.screen != null) return;

        float speed = CameraControlClient.config.cameraSpeed;

        if (CameraKeyBindings.lookUp.isDown())    client.player.setXRot(client.player.getXRot() - speed);
        if (CameraKeyBindings.lookDown.isDown())  client.player.setXRot(client.player.getXRot() + speed);
        if (CameraKeyBindings.lookLeft.isDown())  client.player.setYRot(client.player.getYRot() - speed);
        if (CameraKeyBindings.lookRight.isDown()) client.player.setYRot(client.player.getYRot() + speed);

        float pitch = client.player.getXRot();
        if (pitch < -90f) client.player.setXRot(-90f);
        if (pitch > 90f)  client.player.setXRot(90f);

        if (CameraKeyBindings.speedUp.consumeClick()) {
            CameraControlClient.config.cameraSpeed = Math.min(
                    CameraControlClient.config.cameraSpeed + CameraControlClient.SPEED_STEP,
                    CameraControlClient.SPEED_MAX
            );
            CameraControlClient.config.save();
            showSpeed(client, CameraControlClient.config.cameraSpeed);
        }
        if (CameraKeyBindings.speedDown.consumeClick()) {
            CameraControlClient.config.cameraSpeed = Math.max(
                    CameraControlClient.config.cameraSpeed - CameraControlClient.SPEED_STEP,
                    CameraControlClient.SPEED_MIN
            );
            CameraControlClient.config.save();
            showSpeed(client, CameraControlClient.config.cameraSpeed);
        }
    }

    private void showSpeed(Minecraft client, float speed) {
        if (client.gui != null) {
            client.gui.getChat().addMessage(
                    Component.literal(String.format("[Camera Control] Speed: %.1f", speed))
            );
        }
    }
}
