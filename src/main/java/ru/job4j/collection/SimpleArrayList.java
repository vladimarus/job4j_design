package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            extendContainer();
        }
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = validateIndex(index);
        container[index] = newValue;
        modCount++;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T extracted = validateIndex(index);
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        size--;
        container[size] = null;
        modCount++;
        return extracted;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<>() {
            final int expectedModCount = modCount;
            int itdex = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return itdex < size;
            }

            @Override
            public T next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[itdex++];
            }
        };
    }

    private void extendContainer() {
        container = Arrays.copyOf(container, container.length * 2);
    }

    private T validateIndex(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }
}
