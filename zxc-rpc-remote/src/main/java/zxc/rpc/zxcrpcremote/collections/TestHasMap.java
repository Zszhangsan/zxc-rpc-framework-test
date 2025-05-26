//package zxc.rpc.zxcrpcremote.collections;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Objects;
//
//public class TestHasMap <K,V> {
//    static class Node<K,V> implements Map.Entry<K,V> {
//        final int hash;
//        final K key;
//        V value;
//        TestHasMap.Node<K,V> next;
//
//        Node(int hash, K key, V value, TestHasMap.Node<K,V> next) {
//            this.hash = hash;
//            this.key = key;
//            this.value = value;
//            this.next = next;
//        }
//
//        public final K getKey()        { return key; }
//        public final V getValue()      { return value; }
//        public final String toString() { return key + "=" + value; }
//
//        public final int hashCode() {
//            return Objects.hashCode(key) ^ Objects.hashCode(value);
//        }
//
//        public final V setValue(V newValue) {
//            V oldValue = value;
//            value = newValue;
//            return oldValue;
//        }
//
//        public final boolean equals(Object o) {
//            if (o == this)
//                return true;
//
//            return o instanceof Map.Entry<?, ?> e
//                    && Objects.equals(key, e.getKey())
//                    && Objects.equals(value, e.getValue());
//        }
//    }
//    transient TestHasMap.Node<K,V>[] table;
//
//    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
//                   boolean evict) {
//        Node<K, V>[] tab;
//        Node<K, V> p;
//        int n, i;
//
//        if ((tab = table) == null || (n = tab.length) == 0) {
//            n = (tab = resize()).length;
//        }
//        if ((p = tab[i = (n-1) & hash]) == null) {
//            tab[i]
//        }
//    }
//
//    final TestHasMap.Node<K,V>[] resize() {
//        return new TestHasMap.Node[table.length * 2];
//    }
//}
