package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        System.out.println("Server started");
        int port = 7777;
        String name = "";
        try (ServerSocket serverSocket = new ServerSocket(port);) {
            // порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
                    System.out.println("New connection accepted");
                    while (true) {
                        String answer = in.readLine();
                        if (name.equals("")) {
                            if (answer.contains("Hi")) {
                                out.println("Server:Write your name");
                            } else {
                                name = answer;
                            }
                        }
                        if (!name.equals("")) {
                            if (answer.contains("yes")) {
                                out.println(String.format("Server:Welcome to the kids area, %s! Let's play!", name));
                                name = "";
                                break;
                            } else if (answer.contains("no")) {
                                out.println(String.format("Server:Welcome to the adult zone, %s! Have a good rest, or a good working day!", name));
                                name = "";
                                break;
                            } else {
                                out.println(String.format("Server:Hi %s, your port is %d! Are you child? (yes/no)", name, clientSocket.getPort()));
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
