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

        boolean res = false;
        int idx = indexFor(this.hash(Objects.hashCode(key)));
        if (table[idx] == null) {
            table[idx] = new MapEntry<>(key, value);
            count++;
            modCount++;
            res = true;
        }
        return res;
    }

    /**
     * @param hashCode хэш-код
     * @return хэш ключа
     */
    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode * hashCode * 31;
    }

    /**
     * Реализация вычисления индекса методом серединных квадратов.
     *
     * @param hash хэш ключа
     * @return индекс соответствующей записи в хэш-таблице
     */
    private int indexFor(int hash) {
        if (hash != 0) {
            int h = hash;
            hash = 0;
            while (h != 0) {
                hash += h % 10;
                h /= 10;
            }
            hash = hash % capacity;
        }
        return hash;
    }

    private void expand() {
        count = 0;
        int oldCapacity = capacity;
        capacity *= 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];

        for (int index = 0; index < oldCapacity; index++) {
            MapEntry<K, V> entry = oldTable[index];
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
        modCount++;
    }

    @Override
    public V get(K key) {
        V res = null;
        int idx = indexFor(this.hash(Objects.hashCode(key)));
        if (table[idx] != null && table[idx].key.equals(key)) {
            res = table[idx].value;
        }
        return res;
    }

    @Override
    public boolean remove(K key) {
        boolean res = false;
        int idx = indexFor(this.hash(Objects.hashCode(key)));
        if (table[idx] != null && table[idx].key.equals(key)) {
            table[idx] = null;
            modCount++;
            count--;
            res = true;
        }
        return res;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {

            final int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (table[index] == null && index < capacity - 1) {
                    index++;
                }
                return table[index] != null;
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
