package com.tora;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class Sender {
    private String me;
    private Publisher publisher;
    private Map<String, Socket> personeToSocket;
    private DataOutputStream dataOutputStream;
    private Map<String, IPAddress> dnsTable;
    private Map<String, Receiver> personeToReceiver;
    private ExecutorService executorService;

    public Sender(String me, Publisher publisher, Map<String, Socket> personeToSocket, Map<String, IPAddress> dnsTable, Map<String, Receiver> personeToReceiver, ExecutorService executorService) {
        this.publisher = publisher;
        this.personeToSocket = personeToSocket;
        this.dnsTable = dnsTable;
        this.me = me;
        this.personeToReceiver = personeToReceiver;
        this.executorService = executorService;
    }

    public void send(String persone, String message){
        if (message.matches("!hello [a-zA-Z]+")){
            persone = message.substring(7);
            if (personeToSocket.containsKey(persone))
                publisher.resetToPersone(persone);
            else
                initiateConnection(persone);
        } else {
            if (persone.equals("")){
                publisher.showError("First start a conversation!");
                return;
            }
            try {
                dataOutputStream = new DataOutputStream(personeToSocket.get(persone).getOutputStream());
                dataOutputStream.writeUTF(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initiateConnection(String person){
        //connect to the persone
        if (!dnsTable.containsKey(person)){
            publisher.showError(person + " is not in dns");
            return;
        }
        IPAddress ip = dnsTable.get(person);
        try {
            Socket socket = new Socket(ip.getHost(), ip.getPort());

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF("!hello " + me);
            publisher.addMessageToPersone(person, "!hello " + person, false);

            String message = (new DataInputStream(socket.getInputStream())).readUTF();
            if (message.equalsIgnoreCase("!ack")) {
                personeToSocket.put(person, socket);
                Receiver receiver =  new Receiver(person, personeToSocket, personeToReceiver, publisher, this);
                personeToReceiver.put(person, receiver);
                executorService.submit(() -> receiver.startReceive());
                publisher.resetToPersone(person);
            }
        } catch (IOException e) {
            System.out.println(person + " is not online");
        }
    }
}
