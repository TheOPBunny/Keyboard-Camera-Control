package com.hassanmod.cameracontrol;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CameraControlClient implements ClientModInitializer {

    public static final String MOD_ID = "camera-control";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final float SPEED_MIN = 0.5f;
    public static final float SPEED_MAX = 20.0f;
    public static final float SPEED_STEP = 0.5f;

    public static CameraConfig config;

    @Override
    public void onInitializeClient() {
        config = CameraConfig.load();
        CameraKeyBindings.register();
        LOGGER.info("Camera Control v1.1 loaded! Speed: {}", config.cameraSpeed);
    }
}
