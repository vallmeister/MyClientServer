package org.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoMultiServer {
    private ServerSocket serverSocket;

    public void start(final int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true)
            new EchoClientHandler(serverSocket.accept()).start();
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    private static class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    if (".".equals(inputLine)) {
                        out.println("bye");
                        break;
                    } else {
                        out.println(inputLine);
                    }
                }
                out.println(inputLine);
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread serverThread = new Thread(() -> {
            final EchoMultiServer server = new EchoMultiServer();
            try {
                server.start(8888);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        serverThread.start();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread clientThread = new Thread(() -> {
                EchoClient echoClient = new EchoClient();
                try {
                    echoClient.startConnection("127.0.0.1", 8888);
                    String msg1 = echoClient.sendMessage(String.format("hello from %d", finalI));
                    System.out.println(msg1);
                    Thread.sleep(1000);
                    System.out.println(String.format("%s %d", echoClient.sendMessage("."), finalI));
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            clientThread.start();
        }
    }
}
