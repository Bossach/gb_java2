package lesson_3.PhoneBook;


import java.util.*;

public class PhoneBook {

    private Map<String, Set<String>> book;

    public PhoneBook() {
        book = new HashMap<>();
    }


    public void add(String surname, String phoneNumber) {

        Set<String> numberSet = new HashSet<>();
        numberSet.add(phoneNumber);
        if (book.containsKey(surname)) {
            numberSet.addAll(book.get(surname));
        }

        book.put(surname, numberSet);
    }

    public Collection<String> get(String surname) {
        return book.containsKey(surname) ? book.get(surname) : new HashSet<>();
    }
}
