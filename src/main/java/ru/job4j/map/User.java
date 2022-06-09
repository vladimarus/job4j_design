package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {

    private final String name;
    private final int children;
    private final Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday.get(Calendar.YEAR)
                + '}';
    }

    public static void main(String[] args) {
        Calendar sameDate = Calendar.getInstance();
        User person0 = new User("Ivan", 0, sameDate);
        User person1 = new User("Ivan", 0, sameDate);

        Map<User, Object> map = new HashMap<>(Map.of(
                person0, new Object(),
                person1, new Object()));

        System.out.println("\n" + map);

        System.out.println("person0 hashCode: " + person0.hashCode());
        System.out.println("person1 hashCode: " + person1.hashCode());
    }
}
