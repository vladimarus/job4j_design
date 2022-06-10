package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((double) count / capacity >= LOAD_FACTOR) {
            expand();
        }

        boolean res = get(key) == null;
        if (res) {
            int idx = indexFor(this.hash(Objects.hashCode(key)));
            table[idx] = new MapEntry<>(key, value);
            modCount++;
            count++;
        }
        return res;
    }

    /**
     *
     * @param hashCode хэш-код
     * @return хэш ключа
     */
    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode * hashCode;
    }

    /**
     * Реализация вычисления индекса методом серединных квадратов.
     *
     * @param hash хэш ключа
     * @return индекс соответствующей записи в хэш-таблице
     */
    private int indexFor(int hash) {
        int res;
        if (hash == 0) {
            res = 0;
        } else {
            int hashLen = 0;
            int h = hash;
            while (h > 0) {
                hashLen++;
                h = h >>> 1;
            }
            int capLen = 0;
            int cap = capacity;
            while (cap > 0) {
                capLen++;
                cap = cap >>> 1;
            }
            hashLen = (hashLen - capLen) / 2;
            res = (h >>> hashLen) & (capacity - 1);
        }
        return res;
    }

    private void expand() {
        int oldCap = capacity;
        capacity *= 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        count = 0;

        for (int index = 0; index < oldCap; index++) {
            MapEntry<K, V> entry = oldTable[index];
            put(entry.key, entry.value);
        }
        modCount++;
    }

    @Override
    public V get(K key) {
        int idx = indexFor(this.hash(Objects.hashCode(key)));
        return table[idx] == null ? null : table[idx].value;
    }

    @Override
    public boolean remove(K key) {
        boolean res = get(key) != null;
        if (res) {
            int idx = indexFor(this.hash(Objects.hashCode(key)));
            table[idx] = null;
            modCount++;
            count--;
        }
        return res;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            final int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (table[index] == null || index < capacity - 1) {
                    index++;
                }
                return table[index] == null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}
