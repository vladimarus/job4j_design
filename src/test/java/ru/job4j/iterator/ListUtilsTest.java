package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIfIsMatch() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.removeIf(input, (v) -> v < 2);
        assertThat(input, is(List.of(2)));
    }

    @Test
    public void whenRemoveIfIsNotMatch() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.removeIf(input, (v) -> v > 2);
        assertThat(input, is(List.of(0, 1, 2)));
    }

    @Test
    public void whenReplaceIfIsMatch() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 1, 2));
        ListUtils.replaceIf(input, (v) -> v == 2, 0);
        assertThat(input, is(List.of(1, 0, 1, 0)));
    }

    @Test
    public void whenRemoveAllIsRemoves() {
        List<Integer> input = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> elem = new ArrayList<>(List.of(1, 6, 5, 0));
        ListUtils.removeAll(input, elem);
        assertThat(input, is(List.of(2, 3, 4)));
    }

    @Test
    public void whenRemoveAllIsRemovesAll() {
        List<Integer> input = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> elem = new ArrayList<>(List.of(1, 2, 3, 4));
        ListUtils.removeAll(input, elem);
        assertThat(input, is(List.of()));
    }
}