package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerThread implements Runnable {
    private Socket socket;
    private String userName;
    private boolean firsTime = true;

    private PrintWriter out;
    private BufferedReader serverIn;
    private BufferedReader userIn;

    public ServerThread(Socket socket, String userName) {
        this.socket = socket;
        this.userName = userName;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream());
            serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            userIn = new BufferedReader(new InputStreamReader(System.in));
            // TODO: 19/06/2021 message from chat

            while (!socket.isClosed()) {
                if (serverIn.ready()) {
                    String input = serverIn.readLine();
                    if (input != null) {
                        System.out.println(input);
                    }
                }
                if (userIn.ready()) {
                    out.println(userName + " > " + userIn.readLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
