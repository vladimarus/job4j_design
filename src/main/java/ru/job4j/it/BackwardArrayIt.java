package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс реализует шаблон Итератор с помощью интерфейса Iterator.
 * Итератор возвращает элементы в обратном порядке.
 */
public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point;

    public BackwardArrayIt(int[] data) {
        this.data = data;
        point = data.length - 1;
    }

    @Override
    public boolean hasNext() {
        return point >= 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point--];
    }
}