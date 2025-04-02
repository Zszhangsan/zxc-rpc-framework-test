package zxc.rpc.zxcrpcremote.map;

import java.util.ArrayList;
import java.util.List;

/**
 * 拉链法解决hash冲突
 *
 * @param <K>
 * @param <V>
 */
public class MyHashMap2<K, V> {

    private Node<K, V>[] table = new Node[10];
    private int size = 0;

    public V put(K key, V value) {
        int keyIndex = indexOf(key);
        Node<K, V> head = table[keyIndex];
        if (head == null) {
            table[keyIndex] = new Node<>(key, value);
            size ++;
            return null;
        }
        // 循环链表
        while (true) {
//            同一个key，替换掉旧值
            if (head.key.equals(key)) {
                V oldValue = head.value;
                head.value = value;
                return oldValue;
            }
            // 链表尾
            if (head.next == null) {
                head.next = new Node<>(key, value);
                size ++;
                return null;
            }
            // 此处的kvNod为了下次循环使用
            head = head.next;
        }
    }

    public V get(K key) {
        int keyIndex = indexOf(key);
        Node<K, V> head = table[keyIndex];
        // 循环链表
        while (head !=null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {
        int keyIndex = indexOf(key);
        Node<K, V> head = table[keyIndex];
        if (head == null) {
            return null;
        }
        // 头节点等于key
        if (head.key.equals(key)) {
            table[keyIndex] = head.next;
            size --;
            return head.value;
        }

        // 循环链表删除
        // 每次两个两个删除
        Node<K, V> pre = head;
        Node<K, V> current = head.next;
        while (current != null) {
            if (current.key.equals(key)) {
                pre.next = current.next;
                size --;
                return current.value;
            }
            pre = head.next;
            current = current.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    private int indexOf(Object key) {
        return key.hashCode() % this.table.length;
    }

    class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
