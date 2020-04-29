package lesson_6.Server;

public class LoadAnim extends Thread {

    @Override
    public void run() {
        try {
            while (true) {
                System.out.print("*  ");
                Thread.sleep(500);
                System.out.print("\b\b* ");
                Thread.sleep(500);
                System.out.print("\b*");
                Thread.sleep(500);
                System.out.print("\b\b\b   ");
                Thread.sleep(500);
                System.out.print("\b\b\b");
            }
        } catch (InterruptedException ignored) {
        }
    }
}
