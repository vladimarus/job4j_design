package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс реализует шаблон Итератор.
 * Итератор извлекает только четные элементы массива.
 * Операция remove не поддерживается
 */
public class EvenNumbersIterator implements Iterator<Integer> {

    private final int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    /**
     * @return возвращает true, если в массиве есть четные перед указателем, иначе возвращает false.
     */
    @Override
    public boolean hasNext() {
        boolean res = false;
        if (index < data.length) {
            if (data[index] % 2 == 0) {
                res = true;
            } else {
                index++;
                res = hasNext();
            }
        }
        return res;
    }

    /**
     * @return извлекает только четные элементы итерируемого массива,
     *         при отсутствии четных элементов выкидывает NoSuchElementException
     */
    @Override
    public Integer next() {
        if (hasNext()) {
            return data[index++];
        } else {
            throw new NoSuchElementException();
        }
    }
}
