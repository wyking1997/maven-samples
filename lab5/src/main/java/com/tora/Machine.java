package com.tora;

import com.tora.utils.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

public class Machine {

//    private Map<String,Socket> personToSocket;
//    private Map<String,Receiver> personToReceiver;
//    private Map<String, IPAddress> dnsTable;
//    private ExecutorService executorService;
//    private Publisher publisher;
//    private Receiver receiver;
//    private Sender sender;
//    private String me;

    private StateObject state;

    public Machine(String me) {
        createStateObject(me);
        state.getExecutorService().submit(() -> startServerSocket(me));
        startClient();
    }
    
    private void createStateObject(String me){
        StateObject state = new StateObject();
        
        Map<String, IPAddress> dns = new HashMap<>();
        dns.put("maia", new IPAddress("localhost",9090));
        dns.put("sorin", new IPAddress("localhost",9091));
        dns.put("alex", new IPAddress("localhost",9092));
        dns.put("claudiu", new IPAddress("localhost",9093));
        dns.put("raul", new IPAddress("localhost",9094));
        dns.put("ana", new IPAddress("localhost",9095));

        state.setMe(me);
        state.setDnsTable(dns);
        state.setExecutorService(Executors.newFixedThreadPool(16));
        state.setPersonToReceiver(new ConcurrentHashMap<>());
        state.setPersonToSocket(new ConcurrentHashMap<>());
        state.setPublisher(new Publisher(me));
        state.setSender(new Sender(state));

        this.state = state;
    }

    private void startServerSocket(String me) {
        try(ServerSocket serverSocket = new ServerSocket(state.getDnsTable().get(me).getPort())) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                state.getExecutorService().submit(new AcceptConnectionCallable(clientSocket, state));
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
            this.state.getSender().send(this.state.getMe(), message);
        }
    }
}

