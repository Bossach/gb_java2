package lesson_2;

public class MyArrayDataException extends NumberFormatException {

    public MyArrayDataException() {
        super("Неверные данные");
    }

    public MyArrayDataException(int i, int j) {
        super("Неверные данные в ячейке [" + i + "][" + j + "]");
    }
}
