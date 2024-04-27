package zxc.rpc.learning.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloServer.class);

    public void start(int port) {
        try (ServerSocket server = new ServerSocket(port)) {
            Socket socket;
            while ((socket = server.accept()) != null) {
                LOGGER.info("server connected");
                try (ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                     ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
                    Message o = (Message) inputStream.readObject();
                    LOGGER.info("server receive message: {}", o);
                    o.setContent("server response1111");
                    outputStream.writeObject(o);
                    outputStream.flush();
                } catch (ClassNotFoundException e) {
                    LOGGER.error("server read object error: ", e);
                }
            }

        } catch (IOException e) {
            LOGGER.error("server start error: ", e);
        }
    }

    public static void main(String[] args) {
        HelloServer helloServer = new HelloServer();
        helloServer.start(6666);
    }
}
