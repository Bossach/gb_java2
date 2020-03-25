package lesson_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static double MAX_DISTANCE = 300;
    private static double MAX_HEIGHT = 2;

    public static void main(String[] args) {

        List<Object> participants = new ArrayList<>();
        List<Object> barriers = new ArrayList<>();

        participants.add(new Human("Игорь", 250, 1.5));
        participants.add(new Cat("Пушок", 150, 2));
        participants.add(new Robot("C-3PO", 25));

        //:)
        /*
        participants.add(new JumpBarrier(2));
        barriers.add("Открытый люк");
        */

        genBarriers(barriers, 5);
        printBarriers(barriers);


        for (Object participant : participants) {
            System.out.println("Участник " + participant + " начинает:");
            int totalBarriers = barriers.size();
            int barrierCounter = 0;
            for (Object barrier : barriers) {
                if (barrier instanceof RunBarrier) {
                    if (!(participant instanceof Runner)) {
                        System.out.println("Участник " + participant +
                                " не знает что такое бегать, снимаем с дистанции.");
                        break;
                    }
                    if (((Runner) participant).run((RunBarrier) barrier)) {
                        barrierCounter++;
                    } else break;
                } else if (barrier instanceof JumpBarrier) {
                    if (!(participant instanceof Jumper)) {
                        System.out.println("Участник " + participant +
                                " не знает что такое прыгать, снимаем с дистанции.");
                        break;
                    }
                    if (((Jumper) participant).jump((JumpBarrier) barrier)) {
                        barrierCounter++;
                    } else break;
                } else {
                    System.out.println("Неизвестное препятствие " + barrier + ", пропускаем.");
                    totalBarriers--;
                }
            }
            System.out.println("Участник " + participant + " закончил, пройдено " +
                    barrierCounter + " из " + totalBarriers + " препятствий.");
            System.out.println("---------------------");
        }
    }

    private static void genBarriers(List<Object> barriers, int count) {
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            if (rand.nextBoolean()) {
                barriers.add(new RunBarrier(Math.floor(rand.nextDouble() * MAX_DISTANCE)));
            } else {
                barriers.add(new JumpBarrier(Math.floor(rand.nextDouble() * MAX_HEIGHT * 10) / 10));
            }
        }
    }

    public static void printBarriers(List<Object> barriers) {
        System.out.println("---Полоса препятствий:---");
        for (Object barrier : barriers) {
            System.out.println(barrier);
        }
        System.out.println("-------------------------");
    }

}
