package com.towfiq.thread;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 12/1/21 11:58 PM
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.Scanner;

public class Server {
    public static LinkedList<ClientThread> clientList = new LinkedList<>();

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(20);
            System.out.println("Server Started");
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    Scanner consoleInput = new Scanner(System.in);
                    while (true) {
                        String serverInput = consoleInput.nextLine();
                        System.out.println(serverInput);
                        for (int i = 0; i < clientList.size(); i++) {
                            System.out.println(serverInput);
                            clientList.get(i).writer.println(serverInput);
                        }
                    }
                }
            }.start();
            while (true) {
                clientList.add(new ClientThread(server.accept()));
                clientList.peekLast().start();
            }
        } catch (IOException e) {
            System.out.println("Sorry! Can not start the server");
        }
    }
}