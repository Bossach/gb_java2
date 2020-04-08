package lesson_4;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {


        User me = new User("coolNickname");

        Set<User> usersSet = new HashSet<>();

        usersSet.add(me);
        usersSet.add(new User("ivan"));
        usersSet.add(new User("oleg"));
        usersSet.add(new User("kirill"));



        Chat chat = new Chat(me, usersSet);
    }
}
