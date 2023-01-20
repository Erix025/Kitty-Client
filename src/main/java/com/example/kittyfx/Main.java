package com.example.kittyfx;

import com.example.kittyfx.Controllers.MainController;
import com.example.kittyfx.Controllers.MessageBoxController;
import com.example.kittyfx.Models.Client;
import com.example.kittyfx.manager.StagesManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Client client;

    public void start(Stage stage) throws IOException {
        //加载Main
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        //设置主窗口
        Scene scene_Main = new Scene(fxmlLoader.load());
        ((MainController) StagesManager.getController("Main")).Show(scene_Main);
        try {
            client = new Client("111.231.69.245", 8808);
        } catch (IOException e) {
            Main.SendMessage("警告", "无法连接到服务器", new Runnable() {
                @Override
                public void run() {
                    System.exit(0);
                }
            });
        }
    }

    public static void SendMessage(String content) {
        Scene scene = null;
        try {
            scene = new Scene(new FXMLLoader(Main.class.getResource("MessageBox.fxml")).load(), 200, 150);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MessageBoxController controller = (MessageBoxController) StagesManager.getController(MessageBoxController.KEY + MessageBoxController.count);
        controller.Show(content, scene);
    }

    public static void SendMessage(String head, String content) {
        Scene scene = null;
        try {
            scene = new Scene(new FXMLLoader(Main.class.getResource("MessageBox.fxml")).load(), 200, 150);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MessageBoxController controller = (MessageBoxController) StagesManager.getController(MessageBoxController.KEY + MessageBoxController.count);
        controller.Show(head, content, scene);
    }

    public static void SendMessage(String head, String content, Runnable task) {
        Scene scene = null;
        try {
            scene = new Scene(new FXMLLoader(Main.class.getResource("MessageBox.fxml")).load(), 200, 150);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        MessageBoxController controller = (MessageBoxController) StagesManager.getController(MessageBoxController.KEY + MessageBoxController.count);
        controller.Show(head, content, scene, task);
    }

    public static void main(String[] args) {
        launch();
    }


}