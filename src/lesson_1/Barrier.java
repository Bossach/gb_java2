package lesson_1;

public interface Barrier {
    boolean passBarrier(Object participant);

    boolean canBarrier(Object participant);

    String getActionVerb();
}
