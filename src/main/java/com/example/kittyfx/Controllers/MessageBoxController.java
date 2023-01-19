package com.example.kittyfx.Controllers;

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
    public static int count = 0;
    int number;
    public static final String KEY = "MessageBox";

    public MessageBoxController() {
        count++;
        number = count;
        StagesManager.putController(KEY + number, this);
    }

    public void Show(String string, Scene scene) {
        lab_context.setText(string);
        Initialize(scene);
        stage.show();
    }

    public void Show(String head, String content, Scene scene) {
        lab_heading.setText(head);
        Show(content, scene);
    }

    public void Initialize(Scene scene) {
        this.scene = scene;
        scene.setFill(null);//Scene透明化
        stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);//Stage透明化
        StagesManager.putStage(KEY + number, stage);
        super.Initialize();
        //控件事件初始化
        //退出按钮事件初始化
        but_exit.setOnMouseClicked(mouseEvent -> {
            StagesManager.dispose(KEY + number);
        });
    }
}
