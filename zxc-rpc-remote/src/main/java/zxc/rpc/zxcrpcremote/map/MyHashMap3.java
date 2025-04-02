package zxc.rpc.zxcrpcremote.map;

import java.util.ArrayList;
import java.util.List;

/**
 * 拉链法解决hash冲突
 * 需要自动扩容
 * 1. 什么时候扩容？size小于table长度的3/4
 * 2. 扩容多少？ 每次扩容两倍
 * 3. 怎么扩容？将老的table中节点数据移动新的table中 头插法
 *
 * @param <K>
 * @param <V>
 */
public class MyHashMap3<K, V> {

    private Node<K, V>[] table = new Node[10];

    private int size = 0;

    public V put(K key, V value) {
        int keyIndex = indexOf(key);
        Node<K, V> head = table[keyIndex];
        if (head == null) {
            table[keyIndex] = new Node<>(key, value);
            size++;
            resizeIfNecessary();
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
                size++;
                resizeIfNecessary();
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
        while (head != null) {
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
            size--;
            return head.value;
        }

        // 循环链表删除
        // 每次两个两个删除
        Node<K, V> pre = head;
        Node<K, V> current = head.next;
        while (current != null) {
            if (current.key.equals(key)) {
                pre.next = current.next;
                size--;
                return current.value;
            }
            pre = head.next;
            current = current.next;
        }
        return null;
    }

    /**
     * 1. 什么时候可以扩容 size > table.length * 0.75
     * 2. 扩容多大：*2
     * 3. 如何将老的链表转移到新的链表中，头插法
     *  头插法会导致在多线程的 情况下会出现死循环
     *  node.next = node，就会导致死循环
     *  jdk8之后头插法变成了尾插法
     *  尾插法
     */
    public void resizeIfNecessary() {
        if (this.size < this.table.length * 0.75) {
            return;
        }
        Node<K, V>[] newTable = new Node[this.table.length * 2];
        // 头插法
        for (Node<K, V> head : table) {
            if (head == null) {
                continue;
            }
            Node<K, V> current = head;
            while (current != null) {
                int newIndex = current.key.hashCode() % newTable.length;
                if (newTable[newIndex] == null) {
                    newTable[newIndex] = current;
                    Node<K, V> next = current.next;
                    current.next = null;
                    current = next;
                } else {
                    Node<K, V> next = current.next;
                    current.next = newTable[newIndex];
                    newTable[newIndex] = current;
                    current = next;
                }
            }
        }
        this.table = newTable;
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
