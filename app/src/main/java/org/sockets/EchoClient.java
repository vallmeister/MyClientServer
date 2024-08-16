package org.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(final String ip, final int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(final String msg) throws IOException {
        out.println(msg);
        final String resp = in.readLine();
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    public static void main(String[] args) throws IOException {
        final GreetClient client = new GreetClient();
        client.startConnection("127.0.0.1", 8888);
        for (int i = 0; i < 10; i++) {
            final String response = client.sendMessage(String.format("hello server %d", i));
            System.out.println(response);
        }
        final String response = client.sendMessage(".");
        System.out.println(response);
    }
}
