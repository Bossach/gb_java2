package lesson_3;

import java.util.HashMap;
import java.util.Map;

public class StringArrayPrinter {

    public static void main(String[] args) {
        String[] array = {"кружка", "ложка", "вилка", "ложка",
                "тумба", "ножка", "принтер", "крошка",
                "принтер", "свитер", "стартер", "люстра",
                "грамофон", "комбайн", "ложка", "сарафан"};

        printMap(fillMapFromArray(array));

    }

    public static Map<String, Integer> fillMapFromArray(String[] array) {
        Map<String, Integer> map = new HashMap<>();

        for (String str : array) {
            int value = 1;
            if (map.containsKey(str)) value += map.get(str);
            map.put(str, value);
        }

        return map;
    }

    public static void printMap(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " (" + entry.getValue() + ")");
        }
    }
}
