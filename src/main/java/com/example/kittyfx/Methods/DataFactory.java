package com.example.kittyfx.Methods;

import com.example.kittyfx.Controllers.SendMessageBoxController;
import com.example.kittyfx.Controllers.SettingController;
import com.example.kittyfx.Datas.*;
import com.example.kittyfx.Main;
import com.example.kittyfx.Models.User;
import com.example.kittyfx.manager.StagesManager;
import javafx.application.Platform;

public class DataFactory {
    public static void ReceiveData(Data source) {
        switch (source.getHead()) {
            case "Message":
                Main.client.putTask(new ReceiveMessage(new Message(source)));
                break;
            case "MessageReturn":
                Main.client.putTask(new AnalysisMessageReturn(new MessageReturn(source)));
                break;
            case "LoginReturnData":
                Main.client.putTask(new AnalysisLoginReturnData(new LoginReturnData(source)));
                break;
            case "RegisterReturnData":
                Main.client.putTask(new AnalysisRegisterReturnData(new RegisterReturnData(source)));
                break;
            case "LogoutReturnData":
                Main.client.putTask(new AnalysisLogoutReturnData(new LogoutReturnData(source)));
        }
    }
}

class ReceiveMessage implements Runnable {
    Message message;

    ReceiveMessage(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        String head = "来自" + message.getSendUserID() + "的消息";
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Main.SendMessage(head, message.getContent());
            }
        });
    }
}

class AnalysisMessageReturn implements Runnable {
    MessageReturn data;

    AnalysisMessageReturn(MessageReturn data) {
        this.data = data;
    }

    @Override
    public void run() {
        var SendMessageWindow = (SendMessageBoxController) StagesManager.getController(SendMessageBoxController.KEY);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (data.isMessageValid()) {
                    SendMessageWindow.txt_message.setText("");
                }
                Main.SendMessage(data.getInformation());
            }
        });

    }
}

class AnalysisLoginReturnData implements Runnable {
    LoginReturnData data;

    AnalysisLoginReturnData(LoginReturnData data) {
        this.data = data;
    }

    @Override
    public void run() {
        var SettingWindow = (SettingController) StagesManager.getController(SettingController.KEY);
        if (data.isLoginValid()) {
            var user = new User(SettingWindow.txt_username.getText());
            Main.client.setLoggedUser(user);
            //set UI
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    SettingWindow.txt_username.setText("");
                    SettingWindow.txt_password.setText("");
                    SettingWindow.ap_login.setVisible(false);
                    SettingWindow.but_logout.setVisible(true);
                    SettingWindow.lab_userInfo.setText(user.getID() + " 您已登录");
                    Main.SendMessage("登陆成功");
                }
            });

        } else {
            //set UI
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    SettingWindow.txt_password.setText("");
                    Main.SendMessage("登录失败\n" + data.getInformation());
                }
            });

        }
    }
}

class AnalysisRegisterReturnData implements Runnable {
    RegisterReturnData data;

    AnalysisRegisterReturnData(RegisterReturnData data) {
        this.data = data;
    }

    @Override
    public void run() {
        var SettingWindow = (SettingController) StagesManager.getController(SettingController.KEY);
        if (data.isRegisterValid()) {
            //set UI
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    SettingWindow.txt_password.setText("");
                    Main.SendMessage("注册成功，请登录以继续");
                }
            });

        } else {
            //set UI
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    SettingWindow.txt_username.setText("");
                    SettingWindow.txt_password.setText("");
                    Main.SendMessage("注册失败\n" + data.getInformation());
                }
            });

        }
    }
}

class AnalysisLogoutReturnData implements Runnable {
    LogoutReturnData data;

    AnalysisLogoutReturnData(LogoutReturnData data) {
        this.data = data;
    }

    @Override
    public void run() {
        var SettingWindow = (SettingController) StagesManager.getController(SettingController.KEY);
        if (data.isLogoutValid()) {
            //logout user
            Main.client.logout();
            Platform.runLater(() -> {
                Main.SendMessage("注销成功");
                SettingWindow.lab_userInfo.setText("未登录");
                SettingWindow.but_logout.setVisible(false);
                SettingWindow.ap_login.setVisible(true);
                SettingWindow.txt_username.setText("");
                SettingWindow.txt_password.setText("");
            });
        } else {
            Main.SendMessage(data.getInformation());
        }
    }
}