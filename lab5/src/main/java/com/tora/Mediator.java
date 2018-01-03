package com.tora;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mediator {

    private Map<String,Socket> personToSocket;
    private Map<String,Receiver> personToReceiver;
    private Map<String, IPAddress> dnsTable;
    private ExecutorService executorService;
    private Publisher publisher;
    private Receiver receiver;
    private Sender sender;
    private String me;

    public Mediator(String me) {
        personToSocket = new ConcurrentHashMap<>();
        personToReceiver = new ConcurrentHashMap<>();
        dnsTable = new HashMap<>();
        executorService = Executors.newFixedThreadPool(16);
        this.me = me;
        publisher = new Publisher(me);

        sender = new Sender(me, publisher, personToSocket, dnsTable,personToReceiver, executorService);

        dnsTable.put("maia", new IPAddress("localhost",9090));
        dnsTable.put("sorin", new IPAddress("localhost",9091));
        dnsTable.put("alex", new IPAddress("localhost",9092));
        dnsTable.put("claudiu", new IPAddress("localhost",9093));
        dnsTable.put("raul", new IPAddress("localhost",9094));
        dnsTable.put("ana", new IPAddress("localhost",9095));

        executorService.submit(() -> startServerSocket(me));
        startClient();
    }

    private void startServerSocket(String me) {
        try(ServerSocket serverSocket = new ServerSocket(dnsTable.get(me).getPort())) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                executorService.submit(new AcceptConnectionCallable(clientSocket, personToSocket, personToReceiver, publisher, executorService, sender));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startClient() {
        Scanner scanner = new Scanner(System.in);
        String message;

        while (true){
            message = scanner.nextLine();
            sender.send(publisher.getCurrentPersone(), message);
        }
    }
}

