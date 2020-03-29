package lesson_2;

public class Main {
    public static void main(String[] args) {

        String[][] myArray1 = {{"1", "2"}, {"3", "4"}};
        String[][] myArray2 = {{"1", "2", "3", "4"}, {"1", "2", "a", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};
        String[][] myCorrectArray = {{"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}, {"1", "2", "3", "4"}};

        String[][][] arrays = {myArray1, myArray2, myCorrectArray};

        for (String[][] array : arrays) {
            try {
                System.out.println("Результат: " + fourByFourStringArraySummator(array));
            } catch (MyArraySizeException | MyArrayDataException e) {
                System.out.println(e.getMessage());
            }
        }


    }

    public static int fourByFourStringArraySummator(String[][] array) {
        if (array.length != 4 || array[0].length != 4) throw new MyArraySizeException();

        int sum = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }

        return sum;
    }
}
