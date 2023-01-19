package com.example.kittyfx.Controllers;

import com.alibaba.fastjson2.JSON;
import com.example.kittyfx.Datas.Message;
import com.example.kittyfx.Main;
import com.example.kittyfx.manager.StagesManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Date;

public class SendMessageBoxController extends MovableController {
    @FXML
    public Label lab_loginInfo;
    @FXML
    public TextField txt_objectUser;
    @FXML
    public TextArea txt_message;
    @FXML
    public VBox panel;
    @FXML
    public Button but_exit;
    @FXML
    public Button but_send;
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
        //initialize UI
        if (Main.client.isLogged()) {
            lab_loginInfo.setText(Main.client.getLoggedUser().getID() + " 您已登录");
            but_send.setDisable(false);
        } else {
            lab_loginInfo.setText("未登录");
            but_send.setDisable(true);
        }
        //put stage into manager
        StagesManager.putStage(KEY, stage);
        //set stage no border
        stage.initStyle(StageStyle.UNDECORATED);
        but_send.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (txt_message.getText().isBlank()) {
                    Main.SendMessage("发送消息不能为空");
                    return;
                }
                if (txt_objectUser.getText().isBlank()) {
                    Main.SendMessage("发送对象不能为空");
                    return;
                }
                Main.client.putTask(new SendMessage(txt_message.getText(), txt_objectUser.getText()));
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

class SendMessage implements Runnable {
    private String content;
    private String receiverID;

    public SendMessage(String content, String receiverID) {
        this.content = content;
        this.receiverID = receiverID;
    }

    @Override
    public void run() {
        var data = new Message(content, new Date(), receiverID, Main.client.getLoggedUser().getID());
        Main.client.putData(JSON.toJSONString(data.getJson()));
    }
}