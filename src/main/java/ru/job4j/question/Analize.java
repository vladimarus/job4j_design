package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info rsl = new Info(0, 0, 0);
        Map<Integer, String> userMap = new HashMap<>();

        for (User user : previous) {
            userMap.put(user.getId(), user.getName());
        }
        for (User user : current) {
            String putRes = userMap.put(user.getId(), user.getName());
            if (putRes == null) {
                rsl.setAdded(rsl.getAdded() + 1);
            } else if (!putRes.equals(user.getName())) {
                rsl.setChanged(rsl.getChanged() + 1);
            }
        }
        rsl.setDeleted(previous.size() - current.size() + rsl.getAdded());
        return rsl;
    }

}
