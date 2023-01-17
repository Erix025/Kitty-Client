package com.example.kittyfx.Models;

import com.example.kittyfx.ReadThread;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private Socket socket;
    public DataInputStream in;
    public DataOutputStream out;
    private ReadThread readThread;
    private ExecutorService tasksThreadPool;
    private User loggedUser;

    public Client(String address, int port) {
        try {
            //connect
            socket = new Socket(address, port);
            //get Stream
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            //set thread
            readThread = new ReadThread(this);
            readThread.start();
            tasksThreadPool = Executors.newFixedThreadPool(5);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void putTask(Runnable task) {
        tasksThreadPool.submit(task);
    }
}
