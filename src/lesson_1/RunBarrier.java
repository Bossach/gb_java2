package lesson_1;

public class RunBarrier implements Barrier {
    private static double DEFAULT_DISTANCE = 50.0;

    private Double distance;

    public RunBarrier(double distance) {
        this.distance = distance;
    }

    public RunBarrier() {
        this(DEFAULT_DISTANCE);
    }

    public double getDistance() {
        return distance;
    }

    public boolean canBarrier(Object participant) {
        return participant instanceof Runner;
    }

    public boolean passBarrier(Object participant) {
            return canBarrier(participant) && ((Runner) participant).run(this);
    }

    public String getActionVerb() {
        return "бегать";
    }

    @Override
    public String toString() {
        return "дистанция в " + getDistance() + "м";
    }
}
