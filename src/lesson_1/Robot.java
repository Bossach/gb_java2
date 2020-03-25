package lesson_1;

public class Robot implements Runner, Jumper {

    private static String DEFAULT_MODEL = "R2D2";
    private static double DEFAULT_RUN_LIMIT = 35.0;

    private String model;
    private final double runLimit;

    Robot(String model, double runLimit) {
        this.model = model;
        this.runLimit = runLimit;
    }

    Robot(String model) {
        this(model, DEFAULT_RUN_LIMIT);
    }

    Robot() {
        this(DEFAULT_MODEL);
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
        System.out.println(this.toString() + " не может прыгать.");
        return false;
    }

    @Override
    public String toString() {
        return "Робот " + model;
    }

}
