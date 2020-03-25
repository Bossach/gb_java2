package lesson_1;

public class Human implements Runner, Jumper {

    private static String DEFAULT_NAME = "Иван";
    private static double DEFAULT_RUN_LIMIT = 150.0;
    private static double DEFAULT_JUMP_LIMIT = 1.0;

    private String name;
    private final double runLimit;
    private final double jumpLimit;

    Human(String name, double runLimit, double jumpLimit) {
        this.name = name;
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
    }

    Human(String name, double runLimit) {
        this(name, runLimit, DEFAULT_JUMP_LIMIT);
    }

    Human(String name) {
        this(name, DEFAULT_RUN_LIMIT);
    }

    Human() {
        this(DEFAULT_NAME);
    }

    public boolean run(RunBarrier runBarrier) {
        double distance = runBarrier.getDistance();
        if (runLimit >= distance) {
            System.out.println(this.toString() + " успешно пробежал дистанцию в " + distance + " метров.");
            return true;
        } else {
            System.out.println(this.toString() + " не смог пробежать дистанцию в " + distance + " метров.");
            return false;
        }
    }

    public boolean jump(JumpBarrier jumpBarrier) {
        double height = jumpBarrier.getHeight();
        if (jumpLimit >= height) {
            System.out.println(this.toString() + " перепрыгнул препятствие в " + height + " метров.");
            return true;
        } else {
            System.out.println(this.toString() + " не смог перепрыгнуть препятствие в " + height + "метров.");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Человек " + name;
    }

}
