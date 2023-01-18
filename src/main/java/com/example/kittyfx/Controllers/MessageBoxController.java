package com.example.kittyfx.Controllers;

import com.example.kittyfx.manager.StagesManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MessageBoxController extends MovableController {
    @FXML
    Label lab_heading;
    @FXML
    Label lab_context;
    @FXML
    Button but_exit;
    int pre_x = 0, pre_y = 0;
    public static final String KEY = "MessageBox";

    public MessageBoxController() {
        StagesManager.putController(KEY, this);
    }

    public void Show(String string, Scene scene) {
        lab_context.setText(string);
        Initialize(scene);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stage.show();
            }
        });

    }

    public void Initialize(Scene scene) {
        this.scene = scene;
        scene.setFill(null);//Scene透明化

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);//Stage透明化
                StagesManager.putStage(KEY, stage);
            }
        });
        super.Initialize();
        //控件事件初始化
        //退出按钮事件初始化
        but_exit.setOnMouseClicked(mouseEvent -> {
            StagesManager.dispose(KEY);
        });
    }
}
