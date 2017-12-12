package com.tora;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) throws IOException {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        try(ServerSocket listener = new ServerSocket(9090)){
            while (true) {
                Socket socket = listener.accept();
//                String adr = socket.getInetAddress() + "";
//                System.out.println("Connected to: " + adr);
//                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
//                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//
//                String message = "";
//                do {
//                    message = dataInputStream.readUTF();
//                    if (message.equals("exit_me"))
//                        break;
//                    System.out.println(new Date().toString() + " Message from client: " + message);
//                } while (true);
//
//                System.out.println("Disconected from " + adr + "\n\n");

                executorService.submit(new ServerRunable(socket));

//                socket.close();
            }
        }
    }
}

