package lesson_1;

public class JumpBarrier implements Barrier {
    private static double DEFAULT_HEIGHT = 1.0;

    private double height;

    public JumpBarrier(double height) {
        this.height = height;
    }

    public JumpBarrier() {
        this(DEFAULT_HEIGHT);
    }

    public double getHeight() {
        return height;
    }

    public boolean canBarrier(Object participant) {
        return participant instanceof Jumper;
    }

    public boolean passBarrier(Object participant) {
            return canBarrier(participant) && ((Jumper) participant).jump(this);
    }

    public String getActionVerb() {
        return "прыгать";
    }

    @Override
    public String toString() {
        return "барьер высотой " + getHeight() + "м";
    }
}
