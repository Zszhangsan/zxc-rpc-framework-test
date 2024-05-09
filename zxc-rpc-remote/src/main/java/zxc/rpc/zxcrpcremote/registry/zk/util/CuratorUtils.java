package zxc.rpc.zxcrpcremote.registry.zk.util;

import ch.qos.logback.classic.spi.EventArgUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Curator(zookeeper client) utils
 *
 * @author zxc
 * @date 2024年05月09日 21:42:00
 */
@Slf4j
public class CuratorUtils {

    private static final String ZK_ADDRESS = "127.0.0.1:2181";
    private static final int BASE_SLEEP_TIME = 5000;
    private static final int MAX_RETRIES = 3;

    private static final Set<String> REGISTERED_PATH_SET = ConcurrentHashMap.newKeySet();

    private static CuratorFramework zkClient;


    public CuratorUtils() {
    }

    public static CuratorFramework getZkClient() {
        if (zkClient != null && CuratorFrameworkState.STARTED == zkClient.getState()) {
            return zkClient;
        }
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(BASE_SLEEP_TIME, MAX_RETRIES);
        zkClient = CuratorFrameworkFactory.builder()
                .connectString(ZK_ADDRESS)
                .retryPolicy(retryPolicy)
                .build();
        zkClient.start();
        try {
            if (zkClient.blockUntilConnected(30, TimeUnit.SECONDS)) {
                throw new RuntimeException("Timeout occurred while initializing CuratorFramework");
            }
        } catch (InterruptedException e) {
            log.error("Interrupted while initializing CuratorFramework", e);
        }
        return zkClient;
    }

    /**
     * 创建持久化的节点
     * @param path  路径，eg: /my-rpc/github.HelloService/127.0.0.1:9999
     */
    public static void createPersistent(String path) {
        zkClient = getZkClient();
        try {
            if (REGISTERED_PATH_SET.contains(path) || getZkClient().checkExists().forPath(path) != null) {
                log.info("path {} already exists", path);
            } else {
                zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path);
                log.info("path {} created", path);
            }
            REGISTERED_PATH_SET.add(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
