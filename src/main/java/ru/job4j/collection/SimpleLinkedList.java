package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    Node<E> head;
    Node<E> last;

    int size;
    int modCount;

    @Override
    public void add(E value) {
        Node<E> node = new Node<>(value);

        if (size == 0) {
            head = node;
        } else {
            last.next = node;
        }
        last = node;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node = head;
        for (int count = 0; count < index; count++) {
            node = node.next;
        }
        return node.element;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            Node<E> itNode = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return itNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = itNode.element;
                itNode = itNode.next;
                return result;
            }
        };
    }
}
