package com.example.kittyfx.datas;

import com.alibaba.fastjson2.JSONObject;

public class RegisterReturnData {
    private final JSONObject json;
    private final boolean isRegisterValid;
    private final String information;
    public final static String Head = "RegisterReturnData";

    public RegisterReturnData(Data data) {
        json = data.getJson();
        isRegisterValid = json.getBoolean("Valid");
        information = json.getString("Information");
    }

    public RegisterReturnData(boolean isRegisterValid, String information) {
        this.isRegisterValid = isRegisterValid;
        this.information = information;
        json = new JSONObject();
        json.put("Head", Head);
        json.put("Valid", isRegisterValid);
        json.put("Information", information);
    }

    public JSONObject getJson() {
        return json;
    }

    public String getInformation() {
        return information;
    }

    public boolean isRegisterValid() {
        return isRegisterValid;
    }
}