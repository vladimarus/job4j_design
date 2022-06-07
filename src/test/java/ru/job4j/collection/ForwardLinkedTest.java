package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ForwardLinkedTest {

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        assertThat(linked.deleteFirst(), is(1));
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }

    @Test
    public void addFirstWhenEmpty() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.addFirst(8);
        Iterator<Integer> it = linked.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(8));
        assertFalse(it.hasNext());
    }

    @Test
    public void addFirstWhenNotEmpty() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(7);
        linked.addFirst(8);
        Iterator<Integer> it = linked.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(8));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(7));
    }
}
