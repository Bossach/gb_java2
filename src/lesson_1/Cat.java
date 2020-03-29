package lesson_1;

public class Cat implements Runner, Jumper, Participant {

    private static String DEFAULT_NAME = "Барсик";
    private static double DEFAULT_RUN_LIMIT = 75.0;
    private static double DEFAULT_JUMP_LIMIT = 1.7;

    private String name;
    private final double runLimit;
    private final double jumpLimit;

    public Cat(String name, double runLimit, double jumpLimit) {
        this.name = name;
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
    }

    public Cat(String name, double runLimit) {
        this(name, runLimit, DEFAULT_JUMP_LIMIT);
    }

    public Cat(String name) {
        this(name, DEFAULT_RUN_LIMIT);
    }

    public Cat() {
        this(DEFAULT_NAME);
    }

    public boolean passBarrier(Barrier barrier) {
        if (barrier.canBarrier(this)) {
            return  barrier.passBarrier(this);
        } else {
            System.out.println("Участник " + this +
                    " не умеет " + barrier.getActionVerb() + ".");
            return false;
        }
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
        return "Кот " + name;
    }

}
