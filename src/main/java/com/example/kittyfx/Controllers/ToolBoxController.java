package com.example.kittyfx.Controllers;

import com.example.kittyfx.Main;
import com.example.kittyfx.manager.StagesManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ToolBoxController extends MovableController {
    public static final String KEY = "ToolBox";

    public ToolBoxController() {
        StagesManager.putController(KEY, this);
    }

    @FXML
    VBox panel;
    @FXML
    Button but_Exit;
    @FXML
    Button but_Setting;
    @FXML
    Button but_SendMessage;

    public void Show(Scene scene, double x, double y) {
        Initialize(scene);
        //set location
        stage.setX(x);
        stage.setY(y);
        stage.show();
    }

    public void Initialize(Scene scene) {
        stage = new Stage();
        //set CSS
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        this.scene = scene;
        super.Initialize();
        // set size
        panel.setPrefWidth(scene.getWidth());
        panel.setPrefHeight(scene.getHeight());
        scene.setFill(null);
        StagesManager.putStage(KEY, stage);
        // set controllers' events
        but_Exit.setOnMouseClicked(mouseEvent -> {
            StagesManager.dispose(KEY);
            Main.SendMessage("Exit!");
        });
        but_Setting.setOnMouseClicked(event -> {
            Scene setting = null;
            try {
                setting = new Scene(new FXMLLoader(Main.class.getResource("Setting.fxml")).load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ((SettingController) StagesManager.getController(SettingController.KEY)).Show(setting);
        });
        but_SendMessage.setOnMouseClicked(event -> {
            Scene sendMessage = null;
            try {
                sendMessage = new Scene(new FXMLLoader(Main.class.getResource("SendMessageBox.fxml")).load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ((SendMessageBoxController) StagesManager.getController(SendMessageBoxController.KEY)).Show(sendMessage);
        });
    }
}
