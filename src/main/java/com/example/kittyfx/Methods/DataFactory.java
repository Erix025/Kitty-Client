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
            case "LoginReturnData":
                Main.client.putTask(new AnalysisLoginReturnData(new LoginReturnData(source)));
            case "RegisterReturnData":
                Main.client.putTask(new AnalysisRegisterReturnData(new RegisterReturnData(source)));
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
        //TODO Analysis LoginReturnData
    }
}

class AnalysisRegisterReturnData implements Runnable {
    RegisterReturnData data;

    AnalysisRegisterReturnData(RegisterReturnData data) {
        this.data = data;
    }

    @Override
    public void run() {
        //TODO Analysis RegisterReturnData
    }
}