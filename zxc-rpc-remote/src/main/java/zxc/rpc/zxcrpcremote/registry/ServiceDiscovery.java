package zxc.rpc.zxcrpcremote.registry;

import java.net.InetSocketAddress;

/**
 * 服务发现
 *
 * @author zxc
 * @date 2024年05月08日 22:37:00
 */
public interface ServiceDiscovery {

    /**
     * 根据rpcServiceName获取远程服务地址
     *
     * @param rpcServiceName 完整的服务地址（class name + group + version ）
     * @return 远程服务地址
     */
    InetSocketAddress lookupService(String rpcServiceName);
}
