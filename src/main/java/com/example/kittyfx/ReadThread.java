package com.example.kittyfx;

import com.alibaba.fastjson2.JSONException;
import com.example.kittyfx.Datas.Data;
import com.example.kittyfx.Methods.DataFactory;
import com.example.kittyfx.Models.Client;

import java.io.IOException;

public class ReadThread extends Thread {
    private Thread thread;
    private Client client;
    private final static String threadName = "KittyClientReadThread";

    public ReadThread(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        super.run();
        while (!isInterrupted()) {
            try {
                String source = client.in.readLine();
                //test
                System.out.println(source);
                DataFactory.ReceiveData(new Data(source));
            } catch (IOException e) {
                break;//When the socket is closed
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public synchronized void start() {
        super.start();
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }
}
