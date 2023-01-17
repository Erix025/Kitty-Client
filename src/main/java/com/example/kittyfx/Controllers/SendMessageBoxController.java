package com.example.kittyfx.Controllers;

import com.example.kittyfx.manager.StagesManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SendMessageBoxController extends MovableController {
    @FXML
    TextArea txt_message;
    @FXML
    VBox panel;
    @FXML
    Button but_exit;
    @FXML
    Button but_send;
    public final static String KEY = "SendMessageBox";

    public SendMessageBoxController() {
        StagesManager.putController(KEY, this);
    }

    @Override
    public void Initialize(Scene scene) {
        //create stage
        stage = new Stage();
        //binding scene
        stage.setScene(scene);
        this.scene = scene;
        super.Initialize();
        //put stage into manager
        StagesManager.putStage(KEY, stage);
        //set stage no border
        stage.initStyle(StageStyle.UNDECORATED);
        but_send.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        but_exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                StagesManager.dispose(KEY);
            }
        });
    }

    public void Show(Scene scene) {
        super.Show();
        Initialize(scene);
        stage.show();
    }
}
