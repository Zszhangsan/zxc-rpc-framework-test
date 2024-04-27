package zxc.rpc.learning.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HelloClient {
    public static final Logger LOGGER = LoggerFactory.getLogger(HelloClient.class);

    public Object send(Message message, String host, int port) {
        LOGGER.info("send message: {}", message);
        try (Socket socket = new Socket(host, port)) {
            OutputStream outputStream = socket.getOutputStream();
            new ObjectOutputStream(outputStream).writeObject(message);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (Exception e) {
            LOGGER.error("send message error", e);
        }

        return null;
    }

    public static void main(String[] args) {
        HelloClient helloClient = new HelloClient();
        Message hello = (Message) helloClient.send(new Message("hello"), "127.0.0.1", 6666);
        System.out.println(hello.getContent());
    }
}
