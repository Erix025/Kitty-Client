package com.example.kittyfx.Controllers;

import com.example.kittyfx.Main;
import com.example.kittyfx.manager.StagesManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainController extends MovableController {

    public static final String KEY = "Main";

    public MainController() {
        StagesManager.putController(KEY, this);
    }

    public void Show(Scene scene) {
        Initialize(scene);
        stage.show();
    }

    public void Initialize(Scene scene) {
        this.scene = scene;
        stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        super.Initialize();
        StagesManager.putStage(KEY, stage);
        scene.setFill(Color.TRANSPARENT);
        scene.setOnMousePressed(event -> {
            //initialize location
            pre_x = (int) event.getScreenX();
            pre_y = (int) event.getScreenY();
            //关闭ToolBox
            if (StagesManager.IsStageShowed(MessageBoxController.KEY)) {
                return;
            }
            if (StagesManager.IsStageShowed(ToolBoxController.KEY)) {
                StagesManager.dispose(ToolBoxController.KEY);
            }
            if (event.getClickCount() == 2) {
                Scene scene_toolbox = null;
                try {
                    scene_toolbox = new Scene(new FXMLLoader(Main.class.getResource("ToolBox.fxml")).load(), 320, 200);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ((ToolBoxController) StagesManager.getController(ToolBoxController.KEY)).Show(scene_toolbox, event.getScreenX(), event.getScreenY());
            }
        });
    }
}