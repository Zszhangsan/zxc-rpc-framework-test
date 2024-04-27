package zxc.rpc.learning.netty;

import zxc.rpc.learning.netty.dto.RpcRequest;
import zxc.rpc.learning.netty.dto.RpcResponse;
import zxc.rpc.learning.netty.echo.client.NettyClient;
import zxc.rpc.learning.netty.echo.server.NettyServer;

public class TestNetty {
    public static void main(String[] args) {
        Thread server = new Thread(() -> {
            new NettyServer(8889).run();
        });

        Thread client = new Thread(() -> {
            RpcRequest rpcRequest = RpcRequest.builder().interfaceName("interfaceName").methodName("hello").build();
            NettyClient nettyClient = new NettyClient("127.0.0.1", 8889);
            for (int i = 0; i < 3; i++) {
                nettyClient.sendMessage(rpcRequest);
            }
            RpcResponse rpcResponse = nettyClient.sendMessage(rpcRequest);
            System.out.println(rpcResponse);
        });
        server.start();
        client.start();
    }
}
