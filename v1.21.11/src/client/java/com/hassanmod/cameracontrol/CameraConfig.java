package com.hassanmod.cameracontrol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class CameraConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance()
            .getConfigDir()
            .resolve("camera-control.json");

    public float cameraSpeed = 2.0f;

    public static CameraConfig load() {
        if (Files.exists(CONFIG_PATH)) {
            try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
                CameraConfig config = GSON.fromJson(reader, CameraConfig.class);
                if (config != null) {
                    config.cameraSpeed = Math.max(CameraControlClient.SPEED_MIN,
                            Math.min(CameraControlClient.SPEED_MAX, config.cameraSpeed));
                    return config;
                }
            } catch (IOException e) {
                CameraControlClient.LOGGER.error("Failed to load camera-control config", e);
            }
        }
        return new CameraConfig();
    }

    public void save() {
        try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {
            GSON.toJson(this, writer);
        } catch (IOException e) {
            CameraControlClient.LOGGER.error("Failed to save camera-control config", e);
        }
    }
}
