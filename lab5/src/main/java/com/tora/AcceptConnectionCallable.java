package com.tora;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class AcceptConnectionCallable implements Runnable {
    private Socket socket;
    private Map<String, Socket> personeToSocket;
    private Map<String, Receiver> personeToReceiver;
    private Publisher publisher;
    private Sender sender;
    private ExecutorService executorService;

    public AcceptConnectionCallable(Socket socket, Map<String, Socket> personeToSocket, Map<String, Receiver> personeToReceiver, Publisher publisher, ExecutorService executorService, Sender sender) {
        this.socket = socket;
        this.personeToSocket = personeToSocket;
        this.personeToReceiver = personeToReceiver;
        this.publisher = publisher;
        this.sender = sender;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        String name = "";
        try {
            String message = (new DataInputStream(socket.getInputStream())).readUTF();
            name = message.toLowerCase().substring(7);
            System.out.println(name + " connected to me");
            if (message.matches("!hello [a-z]+")){
                if (name.length() > 0) {
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataOutputStream.writeUTF("!ack");
                    publisher.addMessageToPersone(name, "!hello " + name, true);
                    personeToSocket.put(name, socket);
                    Receiver receiver = new Receiver(name, personeToSocket, personeToReceiver, publisher, sender);
                    executorService.submit(() -> receiver.startReceive());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
