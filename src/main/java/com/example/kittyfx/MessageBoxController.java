package com.example.kittyfx;

import com.example.kittyfx.manager.StagesManager;
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

    public void Show(String string) {
        lab_context.setText(string);
        stage.show();
    }

    void Initialize(Scene scene) {
        this.scene = scene;
        scene.setFill(null);//Scene透明化
        stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);//Stage透明化
        stage.setScene(scene);
        StagesManager.putStage(KEY, stage);
        MoveInitialize();
        //控件事件初始化
        //退出按钮事件初始化
        but_exit.setOnMouseClicked(mouseEvent -> {
            StagesManager.dispose(KEY);
        });
    }
}
