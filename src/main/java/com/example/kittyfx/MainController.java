package com.example.kittyfx;

import com.example.kittyfx.manager.StagesManager;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController extends MovableController {

    public static final String KEY = "Main";

    public MainController() {
        StagesManager.putController(KEY, this);
    }

    public void Show(Scene scene) {
        this.scene = scene;
        stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        StagesManager.putStage(KEY, stage);
        scene.setFill(Color.TRANSPARENT);
        Initialize();
        stage.show();
    }

    private void Initialize() {
        MoveInitialize();
        scene.setOnMousePressed(event -> {
            pre_x = (int) event.getScreenX();
            pre_y = (int) event.getScreenY();
            //关闭ToolBox
            if (StagesManager.IsStageShowed("ToolBox")) {
                StagesManager.dispose("ToolBox");
            }
        });
        scene.setOnMouseDragged(event -> {
            Stage stage = StagesManager.getStage(KEY);
            int x = (int) event.getScreenX() - pre_x + (int) stage.getX();
            int y = (int) event.getScreenY() - pre_y + (int) stage.getY();
            System.out.println((x + "," + y));
            pre_x = (int) event.getScreenX();
            pre_y = (int) event.getScreenY();
            stage.setX(x);
            stage.setY(y);
        });
    }
}