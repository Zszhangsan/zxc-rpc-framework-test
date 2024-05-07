package zxc.rpc.zxcrpcremote.remoting.transport;

import zxc.rpc.zxcrpcremote.remoting.dto.RpcRequest;

/**
 * send RpcRequest.
 *
 * @author zxc
 * @date 2024年05月07日 23:02
 */
public interface RpcRequestTransport {
    Object sendRpcRequest(RpcRequest rpcRequest);
}
