package zxc.rpc.zxcrpcremote.registry.zk;

import zxc.rpc.zxcrpcremote.registry.ServiceDiscovery;

import java.net.InetSocketAddress;

/**
 * @author zxc
 * @date 2024年05月09日 21:41:00
 */
public class ZkServiceDiscovery implements ServiceDiscovery {
    @Override
    public InetSocketAddress lookupService(String rpcServiceName) {
        return null;
    }
}
