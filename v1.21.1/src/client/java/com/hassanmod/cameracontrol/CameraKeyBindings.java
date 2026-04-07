package com.hassanmod.cameracontrol;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class CameraKeyBindings {

    public static final String CATEGORY = "category.camera-control.camera";

    public static KeyBinding lookUp;
    public static KeyBinding lookDown;
    public static KeyBinding lookLeft;
    public static KeyBinding lookRight;
    public static KeyBinding speedUp;
    public static KeyBinding speedDown;

    public static void register() {
        lookUp = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.camera-control.look_up",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_I,
                CATEGORY
        ));
        lookDown = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.camera-control.look_down",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                CATEGORY
        ));
        lookLeft = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.camera-control.look_left",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_J,
                CATEGORY
        ));
        lookRight = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.camera-control.look_right",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_L,
                CATEGORY
        ));
        speedUp = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.camera-control.speed_up",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_BRACKET,
                CATEGORY
        ));
        speedDown = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.camera-control.speed_down",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_BRACKET,
                CATEGORY
        ));
    }
}
