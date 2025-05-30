package zxc.rpc.zxcrpcremote.equalsAndHashCode;

import java.util.Map;
import java.util.Objects;

public class MapEntry<K, V> implements Map.Entry<K,V> {
    private K key;
    private V value;
    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V v) {
        V result = v;
        value = v;
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object rval) {
        return rval instanceof MapEntry
                && Objects.equals(this.key, ((MapEntry<K, V>) rval).getKey())
                && Objects.equals(this.value, ((MapEntry<K, V>) rval).getValue());
    }

    @Override
    public String toString() {
        return this.key + "=" + this.value;
    }
}