package Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private static int portNumber = 4444;
    private static Socket socket = null;
    private static String userName;

    public static void main(String[] args) {
        System.out.println("Please enter user name: ");
        Scanner scanner = new Scanner(System.in);
        userName = scanner.nextLine();
        scanner.close();

        try {
            socket = new Socket("localhost", portNumber);
            Thread.sleep(1000);
            Thread server = new Thread(new ServerThread(socket, userName));
            server.start();
        } catch (IOException e) {
            System.err.println("Connection error");
            System.exit(1);
        } catch (InterruptedException e) {
            System.err.println("Connection error");
            System.exit(1);
        }
    }
}
