package zxc.rpc.learning.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

public class ZkClient {
    /**
     * 重试之间等待的初始时间
     */
    private static final int BASE_SLEEP_TIME = 1000;
    /**
     * 最大重试次数
     */
    private static final int MAX_RETRIES = 3;

    public static void main(String[] args) {
        init();
    }

    public static void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(BASE_SLEEP_TIME, MAX_RETRIES);
        CuratorFramework zkClient = CuratorFrameworkFactory.builder()
                .connectString("192.168.88.128") // 要连接的服务器列表
                .retryPolicy(retryPolicy) // 重试策略
                .build();
        zkClient.start();
        try {
            zkClient.create().forPath("/node1");
            zkClient.create().forPath("/node1/00001");
            zkClient.create().withMode(CreateMode.PERSISTENT).forPath("/node1/00002");
            // 当父节点（/node2）不存在的时候自动创建
            zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/node2/00001");
            zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/node2/00002");
            zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/node2/00003");
            // 获取节点的数据内容，获取到的时byte数组
            zkClient.getData().forPath("/node2/00003");
            // 检测节点是否创建成功
            zkClient.checkExists().forPath("/node2/00003");
            // 删除子节点
            zkClient.delete().forPath("/node2/00003");
            // 删除一个节点及其下面所有的子节点
            zkClient.delete().deletingChildrenIfNeeded().forPath("/node2");
            // 获取/更新节点数据内容
            Stat stat = zkClient.setData().forPath("/node2/00003", "c++".getBytes());
            byte[] bytes = zkClient.getData().forPath("/node2/00003");
            // 获取某个节点的所有子节点路径
            List<String> strings = zkClient.getChildren().forPath("/node1");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
