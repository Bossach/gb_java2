package lesson_2;

public class MyArraySizeException extends RuntimeException {

    public MyArraySizeException() {
        super("Неверный размер массива");
    }
}
