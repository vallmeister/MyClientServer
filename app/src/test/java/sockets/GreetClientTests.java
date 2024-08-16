package sockets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sockets.GreetClient;
import org.sockets.GreetServer;

import java.io.IOException;

public class GreetClientTests {
    private final static int PORT = 1111;

    @BeforeEach
    void startSever() throws IOException {
        System.out.println("Starting server");
        final GreetServer server = new GreetServer();
        server.start(PORT);
    }

    @Test
    void testServerResponseToCorrectGreeting() throws IOException {
        System.out.println("Starting client");
        final GreetClient client = new GreetClient();
        client.startConnection("127.0.0.1", PORT);
        final String response = client.sendMessage("hello server");
        assert "hello client".equals(response);
    }
}
