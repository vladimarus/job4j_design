package ru.job4j.map;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void putWhenAbsentThenSuccess() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put(1, "apple"));
        assertThat(simpleMap.get(1), is("apple"));
    }

    @Test
    public void putWhenExistThenNotChange() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "apple");
        assertFalse(simpleMap.put(1, "orange"));
        assertThat(simpleMap.get(1), is("apple"));
    }

    @Test
    public void getWhenNotExistThenNull() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "apple");
        assertNull(simpleMap.get(2));
    }

    @Test
    public void removeWhenNotExistThenFalse() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "apple");
        assertFalse(simpleMap.remove(2));
    }

    @Test
    public void removeWhenExistThenTrue() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "apple");
        assertTrue(simpleMap.remove(1));
        Iterator<Integer> it = simpleMap.iterator();
        assertFalse(it.hasNext());
    }

    @Test
    public void hasNextWhenEmptyThenFalse() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        Iterator<Integer> iterator = simpleMap.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void hasNextWhenFilledThenTrue() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(543, "Car");
        Iterator<Integer> iterator = simpleMap.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void nextWhenFilledThenSuccess() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        List<Integer> numbers = new ArrayList<>(List.of(135, 247, 358));
        assertTrue(simpleMap.put(numbers.get(0), "Car"));
        assertTrue(simpleMap.put(numbers.get(1), "Car"));
        assertTrue(simpleMap.put(numbers.get(2), "Car"));
        Iterator<Integer> iterator = simpleMap.iterator();
        assertThat(iterator.next(), isIn(numbers));
        assertThat(iterator.next(), isIn(numbers));
        assertThat(iterator.next(), isIn(numbers));
    }

    @Test(expected = NoSuchElementException.class)
    public void nextWhenEmptyThenException() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        Iterator<Integer> iterator = simpleMap.iterator();
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void nextWhenModifiedThenException() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        Iterator<Integer> iterator = simpleMap.iterator();
        simpleMap.put(123, "Truck");
        iterator.next();
    }

}
