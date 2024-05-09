package zxc.rpc.zxcrpcremote.remoting.transport.socket;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import zxc.rpc.zxcrpcremote.remoting.dto.RpcRequest;
import zxc.rpc.zxcrpcremote.remoting.transport.RpcRequestTransport;

/**
 * 基于Socket传输RpcRequest
 *
 * @author zxc
 * @date 2024年05月08日 21:47:00
 */
@AllArgsConstructor
@Slf4j
public class SocketRpcClient implements RpcRequestTransport {
    @Override
    public Object sendRpcRequest(RpcRequest rpcRequest) {
        return null;
    }
}
