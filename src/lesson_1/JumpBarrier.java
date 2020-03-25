package lesson_1;

public class JumpBarrier {
    private static double DEFAULT_HEIGHT = 1.0;

    private double height;

    JumpBarrier(double height) {
        this.height = height;
    }

    JumpBarrier() {
        this(DEFAULT_HEIGHT);
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "барьер высотой " + getHeight() + "м";
    }
}
