package lesson_5;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int size = 10;//000000;
        float[] globArr = new float[size];

        Arrays.fill(globArr, 1);

        System.out.println("\tMain aray from main() :" + Arrays.toString(globArr));

        singleThreadProcess(globArr);

        System.out.println("\tMain aray from main() :" + Arrays.toString(globArr));

        Arrays.fill(globArr, 1);

        multiThreadProcess(globArr, 2);

        System.out.println("\tMain aray from main() :" + Arrays.toString(globArr));

        Arrays.fill(globArr, 1);

        multiThreadProcess(globArr, 64);    //лучший результат на моём компьютере (из степеней двойки)
    }

    private static void process(float[] arr, int bias) {
        for (int i = 0; i < arr.length; i++) {
            int realI = i + bias;   //вычисляем позицию элемента в изначальном массиве для коректного рассчёта
            arr[i] = (float) (arr[i] * Math.sin(0.2f + realI / 5) * Math.cos(0.2f + realI / 5) * Math.cos(0.4f + realI / 2));
        }
    }

    private static void process(float[] arr) {
        process(arr, 0);
    }

    private static void singleThreadProcess(float[] arr) {
        System.out.print("Single thread computing: ");

        long startTime = System.currentTimeMillis();

        process(arr);

        long endTime = System.currentTimeMillis();

        System.out.printf("%dms%n", endTime - startTime);

        //Debug
        System.out.println("\tMain array print from singleThreadProcess() :" + Arrays.toString(arr));
    }

    private static void multiThreadProcess(float[] arr, int threadsCount) {

        if (threadsCount < 1) threadsCount = 1;

        System.out.print("Multihreads computing: ");

        long startTime = System.currentTimeMillis();

        float[][] splittedArrays = splitFloatArray(arr, threadsCount);

        threadsCount = splittedArrays.length; //на случай если передали количество потоков большее, чем элементов в массиве

        Thread[] threads = new Thread[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            float[] tempArray = splittedArrays[i];    //чтобы запизнуть в лямбду, i не хотела пропихиваться
            int bias = i * arr.length / threadsCount;   // Учитываем смещение для корректного рассчёта
            threads[i] = new Thread(() -> process(tempArray, bias));
            threads[i].start();
        }

        //Ждём все потоки
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
            }
        //

        arr = joinFloatArrays(splittedArrays);

        long endTime = System.currentTimeMillis();

        System.out.printf("%d threads - %dms%n", threadsCount, endTime - startTime);

        //Debug
        System.out.println("\tMain array print from multiThreadProcess() :" + Arrays.toString(arr));
    }

    private static float[][] splitFloatArray(float[] sourceArray, int partsCount) {
        if (partsCount < 1) partsCount = 1;
        if (partsCount > sourceArray.length) partsCount = sourceArray.length;


        float[][] partedArrays = new float[partsCount][];
        int standartStep = sourceArray.length / partsCount;
        int corrector = sourceArray.length % partsCount;

        for (int i = 0; i < partsCount; i++) {
            int currentStep = standartStep + (i == partsCount - 1 ? corrector : 0);
            partedArrays[i] = new float[currentStep];
            System.arraycopy(sourceArray, i * standartStep, partedArrays[i], 0, currentStep);
        }

        return partedArrays;
    }

    private static float[] joinFloatArrays(float[][] sourceArrays) {
        int elementsCount = 0;
        for (float[] array : sourceArrays) {
            elementsCount += array.length;
        }

        float[] resultArray = new float[elementsCount];

        int currentPosition = 0;

        for (float[] array : sourceArrays) {
            System.arraycopy(array, 0, resultArray, currentPosition, array.length);
            currentPosition += array.length;
        }


        return resultArray;
    }

}
