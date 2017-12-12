package com.tora;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerRunable implements Runnable{

    Socket socket;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    InetAddress address;

    public ServerRunable(Socket socket) {
        this.socket = socket;
        address = socket.getInetAddress();
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Connected to: " + address);
        String message = "";
        do {
            try {
                message = dataInputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (message.equals("exit_me"))
                break;
            System.out.println(new Date().toString() + " Message from client: " + message);
        } while (true);

        System.out.println("Disconected from " + address + "\n\n");
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
