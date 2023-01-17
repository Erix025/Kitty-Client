package com.example.kittyfx.Controllers;

import com.example.kittyfx.manager.StagesManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SettingController extends MovableController {
    public static final String KEY = "Setting";
    @FXML
    public Button but_exit;
    @FXML
    public VBox panel;
    @FXML
    public TextField txt_username;
    @FXML
    public TextField txt_password;
    @FXML
    public Button but_login;
    @FXML
    public Button but_register;

    public SettingController() {
        StagesManager.putController(KEY, this);
    }

    public void Show(Scene scene) {
        super.Show();
        Initialize(scene);
        stage.show();
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
        but_exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                StagesManager.dispose(KEY);
            }
        });
        but_login.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });
        but_register.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String username = txt_username.getText();
                String password = txt_password.getText();
            }
        });
    }
}
