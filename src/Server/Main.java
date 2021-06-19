package Server;

import Chat.Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static int portNumber = 4444;
    private static ServerSocket serverSocket = null;
    protected static ArrayList<ClientThread> clients;

    public static void main(String[] args) {
        Chat chat = new Chat();

        try {
            serverSocket = new ServerSocket(portNumber);
            acceptClients();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Port doesn't work: " + portNumber);
            System.exit(1);
        }
    }

    public static void acceptClients() {
        clients = new ArrayList<>();
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                ClientThread client = new ClientThread(socket);
                System.out.println("Client connected");
                Thread thread = new Thread(client);
                thread.start();
                clients.add(client);
            } catch (IOException e) {
                System.err.println("Accept failed");
                System.exit(1);
            }
        }
    }
}
