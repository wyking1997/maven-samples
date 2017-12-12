package com.tora;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        String serverAddress = "localhost";
        Socket s = new Socket(serverAddress, 9090);

        OutputStream out = s.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(out);
        sendContinuosly(dataOutputStream);

        s.close();
    }

    private static void sendContinuosly(DataOutputStream dataOutputStream) {
//        dataOutputStream.writeUTF(readMessage());
        Scanner scanner = new Scanner(System.in);
        String message = "";
        while (true){
            System.out.print("Message for client: ");
            try {
                message = scanner.nextLine();
                dataOutputStream.writeUTF(message);

                if (message.equals("exit_me"))
                    return;
            } catch (IOException e) {}

        }
    }

}
