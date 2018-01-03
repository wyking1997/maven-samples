package com.tora;

import java.util.HashMap;
import java.util.Map;

public class Publisher {
    private final String me;
    // persone, msg
    private Map<String, String> messages;
    private String currentPersone;

    public Publisher(String me){
        this.messages = new HashMap<>();
        this.currentPersone = "";
        this.me = me;

        this.resetWindow();
    }

    public void resetWindow(){
        resetConversation();
        this.currentPersone = "";
        System.out.println("I am " + me);
        System.out.println("Waiting for cmd");
    }

    // received = true if the message was received from smb or
    //          = false if it was sent to smb
    public void addMessageToPersone(String persone, String message, Boolean received){
        String toAdd = "";
        if (received == true)
            toAdd = "\t\t\t\t\t";

        if (messages.containsKey(persone))
            messages.put(persone, messages.get(persone) + toAdd + message + "\n");
        else
            messages.put(persone, toAdd + message + "\n");
        if (persone.equalsIgnoreCase(currentPersone))
            System.out.println(toAdd + message);

    }

    public void resetToPersone(String personeName){
        if (currentPersone.equalsIgnoreCase(personeName))
            return;
        currentPersone = personeName;
        resetConversation();
        if (!messages.containsKey(personeName)){
            messages.put(personeName, "");
        } else
            System.out.print(messages.get(currentPersone));
    }

    public void showError(String errorMsg){
        System.out.println("\n" + errorMsg.toUpperCase());
    }

    public String getCurrentPersone() {
        return currentPersone;
    }

    private void resetConversation(){
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public String getMe() {
        return me;
    }
}
