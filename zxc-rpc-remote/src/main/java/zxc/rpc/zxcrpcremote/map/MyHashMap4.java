package zxc.rpc.zxcrpcremote.map;

import java.util.ArrayList;
import java.util.List;

/**
 * 初始化，修改下标获取方法
 * 再二进制运算符中 1 & 1 = 1，其他的情况下 都是0
 * 也就是说在第一位的所有数字都是1，那么在进行 & 运算的时候都只能获取到比当前数字小的结果
 * 当15 31 63  分别是00001111， 000000011111， 0000111111，都是2的幂次方-1得到，
 * 那么在&运算时，比上任何 一个数字的结果都是会小于 15 31 63
 * @param <K>
 * @param <V>
 */
public class MyHashMap4<K, V> {


    // 此处的初始化长度位2的幂次方
    private Node<K, V>[] table = new Node[16];

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
                int newIndex = current.key.hashCode() & (newTable.length -1);
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
        System.out.println("扩容了，扩容到了" + this.table.length );
    }

    public int size() {
        return size;
    }

    private int indexOf(Object key) {
        return key.hashCode() & (this.table.length - 1);
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
