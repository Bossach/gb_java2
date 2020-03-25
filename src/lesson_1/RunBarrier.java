package lesson_1;

public class RunBarrier {
    private static double DEFAULT_DISTANCE = 50.0;

    private Double distance;

    RunBarrier(double distance) {
        this.distance = distance;
    }

    RunBarrier() {
        this(DEFAULT_DISTANCE);
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "дистанция в " + getDistance() + "м";
    }
}
