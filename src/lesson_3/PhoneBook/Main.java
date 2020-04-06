package lesson_3.PhoneBook;

public class Main {
    public static void main(String[] args) {

        PhoneBook book = new PhoneBook();

        book.add("Иванов", "25044");
        book.add("Иванов", "13314");
        book.add("Иванов", "95382");
        book.add("Петров", "22232");
        book.add("Петров", "65674");
        book.add("Лещенко", "88383");
        book.add("Агутин", "86378");
        book.add("Моисеев", "12332");


        System.out.println("Иванов: " + book.get("Иванов"));
        System.out.println("Агутин: " + book.get("Агутин"));
        System.out.println("Медведев: " + book.get("Медведев"));

    }
}
