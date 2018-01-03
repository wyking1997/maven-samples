package com.tora;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

public class Receiver {
    private String persone;
    private DataInputStream dataInputStream;
    // name, msg
    private Publisher publisher;
    private Sender sender;
    private Map<String, Socket> personeToSocket;
    private Map<String, Receiver> personeToReceiver;

    public Receiver(String persone, Map<String, Socket> personeToSocket, Map<String, Receiver> personeToReceiver, Publisher publisher, Sender sender) {
        this.persone = persone;
        this.publisher = publisher;
        this.sender = sender;
        this.personeToReceiver = personeToReceiver;
        this.personeToSocket = personeToSocket;
        try {
            this.dataInputStream = new DataInputStream((personeToSocket.get(persone)).getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startReceive(){
        try {
            while (true){
                String msg = dataInputStream.readUTF();
                publisher.addMessageToPersone(persone, msg, true);
                if (msg.equalsIgnoreCase("!bye") || msg.equalsIgnoreCase("!byee")){
                    // delete connection
                    if (msg.equalsIgnoreCase("!bye"))
                        sender.send(persone, "!byee");
                    Socket socket = personeToSocket.get(persone);
                    socket.close();
                    personeToSocket.remove(persone);
                    personeToReceiver.remove(persone);

                    publisher.resetWindow();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(persone + " has gone offline!");
        }
    }
}
