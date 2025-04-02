package zxc.rpc.zxcrpcremote.remotingtest.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zxc.rpc.zxcrpcremote.remotingtest.dto.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HelloServer {
    private static final Logger logger = LoggerFactory.getLogger(HelloServer.class);

    private void start(int port) {
//        1. 创建一个SocketServer对象
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket;
//          2. 通过accept()方法监听客户端请求
            while ((socket = serverSocket.accept()) != null) {
                try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                     ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {
//                  3. 通过输入流读取客户端发送的请求信息
                    Message o = (Message) inputStream.readObject();
                    logger.info("客户端发送的信息：{}", o.getContent());
                    o.setContent("服务端hello");
                    // 4. 通过输出流响应客户端
                    outputStream.writeObject(o);
                    outputStream.flush();
                }
            }
        } catch (Exception e) {
            logger.error("产生异常：", e);
        }
    }

    public static void main(String[] args) {
        HelloServer server = new HelloServer();
        server.start(6666);
    }
}
