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
        Node<K, V> item = table[keyIndex];
        if (item == null) {
            table[keyIndex] = new Node<>(key, value);
            size ++;
            return null;
        }
        // 循环链表
        while (true) {
//            同一个key，替换掉旧值
            if (item.key.equals(key)) {
                V oldValue = item.value;
                item.value = value;
                return oldValue;
            }
            // 链表尾 此时出现了hash冲突，所以会向后继续放入值，而不是命中的目标key放入值。
            if (item.next == null) {
                item.next = new Node<>(key, value);
                size ++;
                return null;
            }
            // 此处的kvNod为了下次循环使用
            item = item.next;
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

        // 循环链表删除，由于会有hash冲突的场景出现，
        // 所以需要循环链表，通过equals方法查找到符合条件的值，并删除
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
