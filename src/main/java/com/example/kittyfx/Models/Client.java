package com.example.kittyfx.Models;

import com.example.kittyfx.ReadThread;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private Socket socket;
    public BufferedReader in;
    public BufferedWriter out;
    private ReadThread readThread;
    private ExecutorService tasksThreadPool;
    private User loggedUser;
    public final String CLIENT_TYPE = "Desktop";

    public Client(String address, int port) throws IOException {
        //connect
        socket = new Socket(address, port);
        //get Stream
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        //set thread
        readThread = new ReadThread(this);
        readThread.start();
        tasksThreadPool = Executors.newFixedThreadPool(5);
    }

    public Socket getSocket() {
        return socket;
    }

    public void putData(String string) {
        try {
            out.write(string + "\n");
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void putTask(Runnable task) {
        tasksThreadPool.submit(task);
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public boolean isLogged() {
        return loggedUser != null;
    }

    public void logout() {
        loggedUser = null;
    }
}
