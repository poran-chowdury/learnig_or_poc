package com.towfiq.thread;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 12/2/21 12:02 AM
 */


class ClientThread extends Thread {
    Socket clientSocket;
    Scanner reader;
    PrintWriter writer;

    public ClientThread(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.reader = new Scanner(clientSocket.getInputStream());
        this.writer = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (clientSocket.isConnected()) {
                System.out.println("Client: " + reader.nextLine());
            }
        } catch (Exception e) {
            System.out.println("Client Left");
        }
        Server.clientList.remove(this);
    }
}
