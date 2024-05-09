package zxc.rpc.zxcrpcremote.registry;

import java.net.InetSocketAddress;

/**
 * 服务注册
 *
 * @author zxc
 * @date 2024年05月08日 22:34:00
 */
public interface ServiceRegistry {

    /**
     * 注册服务到注册中心
     *
     * @param serviceName       完整的服务名称（class  name + group + version）
     * @param inetSocketAddress 远程服务地址
     */
    void registerService(String serviceName, InetSocketAddress inetSocketAddress);
}
