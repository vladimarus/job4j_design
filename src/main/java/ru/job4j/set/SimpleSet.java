package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

/**
 * Реализация интерфейса {@link Set} - коллекции на основе {@link SimpleArrayList}.
 *
 * @param <T>
 */
public class SimpleSet<T> implements Set<T> {

    private final SimpleArrayList<T> set = new SimpleArrayList<>(16);

    @Override
    public boolean add(T value) {
        boolean res = false;
        if (!this.contains(value)) {
            set.add(value);
            res = true;
        }
        return res;
    }

    @Override
    public boolean contains(T value) {
        boolean res = false;
        for (T t : set) {
            if (Objects.equals(t, value)) {
                res = true;
                break;
            }
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}

