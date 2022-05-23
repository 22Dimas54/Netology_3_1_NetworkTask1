package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 7777;
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            out.println("Hi");
            System.out.println("Client:Hi");
            while (true) {
                String resp = in.readLine();
                System.out.println(resp);
                if (resp.contains("Write your name")) {
                    out.println("Netology");
                    System.out.println("Client:Netology");
                } else if (resp.contains("Are you child")) {
                    out.println("yes");
                    System.out.println("Client:yes");
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
