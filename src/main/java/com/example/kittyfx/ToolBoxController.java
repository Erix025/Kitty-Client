package com.example.kittyfx;

import com.example.kittyfx.manager.StagesManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ToolBoxController extends MovableController {
    public static final String KEY = "ToolBox";

    public ToolBoxController() {
        StagesManager.putController(KEY, this);
    }

    @FXML
    GridPane grid_main;
    @FXML
    Button but_Exit;

    public void Show(Scene scene, double x, double y) {
        stage = new Stage();
        //set CSS
        scene.getStylesheets().add(getClass().getResource("ToolBox.css").toExternalForm());
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setX(x);
        stage.setY(y);
        this.scene = scene;
        // set size
        grid_main.setPrefWidth(scene.getWidth());
        grid_main.setPrefHeight(scene.getHeight());
        scene.setFill(null);
        StagesManager.putStage(KEY, stage);
        Initialize();
        stage.show();
    }

    private void Initialize() {
        MoveInitialize();
        but_Exit.setOnMouseClicked(mouseEvent -> {
            StagesManager.dispose(KEY);
            try {
                Main.SendMessage("Exit!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
