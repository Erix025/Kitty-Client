package com.example.kittyfx.controllers;

import com.alibaba.fastjson2.JSON;
import com.example.kittyfx.Main;
import com.example.kittyfx.datas.LoginData;
import com.example.kittyfx.datas.LogoutData;
import com.example.kittyfx.datas.RegisterData;
import com.example.kittyfx.manager.StagesManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
    public AnchorPane ap_loginInfo;
    @FXML
    public AnchorPane ap_login;
    @FXML
    public TextField txt_username;
    @FXML
    public TextField txt_password;
    @FXML
    public Label lab_userInfo;
    @FXML
    public Button but_login;
    @FXML
    public Button but_register;
    @FXML
    public Button but_logout;

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
        //initialize UI
        if (Main.client.isLogged()) {
            ap_login.setVisible(false);
            lab_userInfo.setText(Main.client.getLoggedUser().ID() + " 您已登录");
            but_logout.setVisible(true);
        }
        //put stage into manager
        StagesManager.putStage(KEY, stage);
        //set stage no border
        stage.initStyle(StageStyle.UNDECORATED);
        but_exit.setOnMouseClicked(event -> StagesManager.dispose(KEY));
        but_logout.setOnMouseClicked(event -> {
            Main.client.putTask(new SendLogoutMessage(Main.client.getLoggedUser().ID()));
        });
        but_login.setOnMouseClicked(event -> {
            if (txt_username.getText().isBlank() || txt_password.getText().isBlank()) {
                Main.SendMessage("账号或密码不能为空");
            } else {
                Main.client.putTask(new SendLoginMessage(txt_username.getText(), txt_password.getText()));
            }
        });
        but_register.setOnMouseClicked(event -> {
            if (txt_username.getText().isBlank() || txt_password.getText().isBlank()) {
                Main.SendMessage("账号或密码不能为空");
            } else {
                Main.client.putTask(new SendRegisterMessage(txt_username.getText(), txt_password.getText()));
            }
        });
    }
}

class SendRegisterMessage implements Runnable {
    private final String username;
    private final String password;

    SendRegisterMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        var client = Main.client;
        RegisterData data = new RegisterData(username, password, client.CLIENT_TYPE);
        client.putData(JSON.toJSONString(data.getJson()));
    }
}

class SendLoginMessage implements Runnable {
    private final String username;
    private final String password;

    SendLoginMessage(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        var client = Main.client;
        LoginData data = new LoginData(username, password, client.CLIENT_TYPE);
        client.putData(JSON.toJSONString(data.getJson()));
    }
}

class SendLogoutMessage implements Runnable {
    private final String username;

    SendLogoutMessage(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        var client = Main.client;
        LogoutData data = new LogoutData(username, client.CLIENT_TYPE);
        client.putData(JSON.toJSONString(data.getJson()));
    }
}