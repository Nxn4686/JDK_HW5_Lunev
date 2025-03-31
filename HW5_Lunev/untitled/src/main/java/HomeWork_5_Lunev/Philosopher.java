package HomeWork_5_Lunev;

import java.util.concurrent.locks.Lock;

class Philosopher extends Thread {
    private final int id;
    private final Lock leftFork;
    private final Lock rightFork;
    private int mealsEaten = 0;

    public Philosopher(int id, Lock leftFork, Lock rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (mealsEaten < 3) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Философ " + id + " закончил есть.");
    }

    private void think() throws InterruptedException {
        System.out.println("Философ " + id + " думает.");
        Thread.sleep((int)(Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        System.out.println("Философ " + id + " хочет поесть.");

        leftFork.lock();
        System.out.println("Философ " + id + " взял левую вилку.");

        rightFork.lock();
        System.out.println("Философ " + id + " взял правую вилку.");

        System.out.println("Философ " + id + " ест.");
        Thread.sleep((int)(Math.random() * 1000));

        leftFork.unlock();
        System.out.println("Философ " + id + " отпустил левую вилку.");
        rightFork.unlock();
        System.out.println("Философ " + id + " отпустил правую вилку.");

        mealsEaten++;
        System.out.println("Философ " + id + " поел " + mealsEaten + " раз(а).");
    }
}
