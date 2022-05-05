package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс реализует итератор для двухмерного массива.
 * Возвращает элементы 2-мерной структуры, как из плоского массива.
 */
public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        boolean res = false;
        while (!res && row < data.length) {
            if (column < data[row].length) {
                res = true;
            } else {
                column = 0;
                row++;
            }
        }
        return res;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}
