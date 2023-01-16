package com.example.kittyfx;

import com.example.kittyfx.manager.StagesManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public void start(Stage stage) throws IOException {
        //加载Main
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        //设置主窗口
        Scene scene_Main = new Scene(fxmlLoader.load(), 320, 240);
        ((MainController) StagesManager.getController("Main")).Show(scene_Main);
        //新窗口
        scene_Main.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (StagesManager.IsStageShowed(MessageBoxController.KEY)) {
                    return;
                }
                if (mouseEvent.getClickCount() == 2) {
                    if (StagesManager.IsStageShowed("ToolBox")) {
                        //close previous stage
                        StagesManager.dispose("ToolBox");
                    }
                    Scene scene_toolbox = null;
                    try {
                        scene_toolbox = new Scene(new FXMLLoader(Main.class.getResource("ToolBox.fxml")).load(), 200, 200);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ((ToolBoxController) StagesManager.getController("ToolBox")).Show(scene_toolbox, mouseEvent.getScreenX(), mouseEvent.getScreenY());
                }
            }
        });
    }

    public static void SendMessage(String content) throws IOException {
        Scene scene = new Scene(new FXMLLoader(Main.class.getResource("MessageBox.fxml")).load(), 200, 150);
        MessageBoxController controller = (MessageBoxController) StagesManager.getController("MessageBox");
        controller.Initialize(scene);
        controller.Show(content);
    }

    public static void main(String[] args) {
        launch();
    }
}