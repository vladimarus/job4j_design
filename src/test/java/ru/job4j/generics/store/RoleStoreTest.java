package ru.job4j.generics.store;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleIsGuest() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Guest"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Guest"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Guest"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleNameIsSystem() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "System"));
        store.add(new Role("1", "Guest"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("System"));
    }

    @Test
    public void whenReplaceThenRoleNameIsSystem() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Guest"));
        store.replace("1", new Role("1", "System"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("System"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.replace("10", new Role("10", "User"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Admin"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleNameIsDefault() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Default"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Default"));
    }
}
