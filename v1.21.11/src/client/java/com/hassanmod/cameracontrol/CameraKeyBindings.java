package com.hassanmod.cameracontrol;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class CameraKeyBindings {

    public static final String CATEGORY = "category.camera-control.camera";

    public static KeyMapping lookUp;
    public static KeyMapping lookDown;
    public static KeyMapping lookLeft;
    public static KeyMapping lookRight;
    public static KeyMapping speedUp;
    public static KeyMapping speedDown;

    public static void register() {
        lookUp    = register("key.camera-control.look_up",    GLFW.GLFW_KEY_I);
        lookDown  = register("key.camera-control.look_down",  GLFW.GLFW_KEY_K);
        lookLeft  = register("key.camera-control.look_left",  GLFW.GLFW_KEY_J);
        lookRight = register("key.camera-control.look_right", GLFW.GLFW_KEY_L);
        speedUp   = register("key.camera-control.speed_up",   GLFW.GLFW_KEY_RIGHT_BRACKET);
        speedDown = register("key.camera-control.speed_down", GLFW.GLFW_KEY_LEFT_BRACKET);
    }

    private static KeyMapping register(String id, int defaultKey) {
        return KeyBindingHelper.registerKeyBinding(
                new KeyMapping(id, InputConstants.Type.KEYSYM, defaultKey, CATEGORY)
        );
    }
}
