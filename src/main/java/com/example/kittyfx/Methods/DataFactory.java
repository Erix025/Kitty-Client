package com.example.kittyfx.Methods;

import com.example.kittyfx.Datas.Data;
import com.example.kittyfx.Datas.LoginReturnData;
import com.example.kittyfx.Datas.Message;
import com.example.kittyfx.Datas.RegisterReturnData;
import com.example.kittyfx.Main;

public class DataFactory {
    public static void ReceiveData(Data source) {
        switch (source.getHead()) {
            case "Message":
                Main.client.putTask(new ReceiveMessage(new Message(source)));
                break;
            case "LoginReturnData":
                Main.client.putTask(new AnalysisLoginReturnData(new LoginReturnData(source)));
                break;
            case "RegisterReturnData":
                Main.client.putTask(new AnalysisRegisterReturnData(new RegisterReturnData(source)));
                break;
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
        //TODO receive Message
    }
}

class AnalysisLoginReturnData implements Runnable {
    LoginReturnData data;

    AnalysisLoginReturnData(LoginReturnData data) {
        this.data = data;
    }

    @Override
    public void run() {
        if (data.isLoginValid()) {
            Main.SendMessage("登陆成功");
        } else {
            Main.SendMessage("登录失败\n" + data.getInformation());
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
        if (data.isRegisterValid()) {
            Main.SendMessage("注册成功，请登录");
        } else {
            Main.SendMessage("注册失败\n" + data.getInformation());
        }
    }
}