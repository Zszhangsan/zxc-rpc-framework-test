package zxc.rpc.zxcrpcremote.remotingtest.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zxc.rpc.zxcrpcremote.remotingtest.dto.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HelloClient {
    private static Logger logger = LoggerFactory.getLogger(HelloClient.class);

    private Object send(Message message, String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream.writeObject(message);
            Message o = (Message) inputStream.readObject();
            return o;
        } catch (Exception e) {
            logger.error("发生异常：", e);
        }
        return null;
    }
//    public void static main () {}
    public static void main (String[] args) {
        HelloClient helloClient = new HelloClient();
        Message hello = (Message) helloClient.send(new Message("客户端hello"), "127.0.0.1", 6666);
        System.out.println("服务端响应的信息：" + hello.getContent());
    }
}
