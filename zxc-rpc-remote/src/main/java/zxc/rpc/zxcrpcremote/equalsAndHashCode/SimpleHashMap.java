package zxc.rpc.zxcrpcremote.equalsAndHashCode;

import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 * 简单hashmap，使用hash方法（散列函数）
 * * 散列的价值在于速度：散列使得查询方法得以快速进行。由于瓶颈位于键的查询速度，因此解决方案之一
 * 就是保持键的排序状态
 *
 * @param <K>
 * @param <V>
 */
public class SimpleHashMap<K, V> extends AbstractMap<K, V> {
    // 选择一个素数，以实现均匀分布
    static final int SIZE = 997;
    // list 数组
    LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        MapEntry<K, V> pair = new MapEntry<>(key, value);
        boolean found = false;
        ListIterator<MapEntry<K, V>> it = bucket.listIterator();
        while (it.hasNext()) {
            MapEntry<K, V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair); // 新的替换旧的
                found = true;
                break;
            }
        }
        if (!found) {
            buckets[index].add(pair);
        }
        return oldValue;
    }

    @Override
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        for (MapEntry<K, V> pair : bucket) {
            if (pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }

        return null;
    }



    @Override
    public @NotNull Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (LinkedList<MapEntry<K, V>> bucket : buckets) {
            if (bucket == null) {
                continue;
            }
            for (MapEntry<K, V> pair : bucket) {
                set.add(pair);
            }
        }
        return set;
    }
}
