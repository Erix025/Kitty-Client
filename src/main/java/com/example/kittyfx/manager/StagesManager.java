package com.example.kittyfx.manager;

import javafx.stage.Stage;

import java.util.HashMap;

public class StagesManager {
    private static final HashMap<String, Stage> STAGE = new HashMap<>();
    private static final HashMap<String, Object> CONTROLLER = new HashMap<>();

    public static Stage getStage(String key) {
        return STAGE.get(key);
    }

    public static Object getController(String key) {
        return CONTROLLER.get(key);
    }

    public static void putStage(String key, Stage stage) {
        STAGE.put(key, stage);
    }

    public static void putController(String key, Object controller) {
        CONTROLLER.put(key, controller);
    }

    public static void dispose(String key) {
        Stage stage = getStage(key);
        stage.close();
        STAGE.remove(key);
        CONTROLLER.remove(key);
    }

    public static boolean IsStageShowed(String key) {
        return STAGE.containsKey(key);
    }
}
