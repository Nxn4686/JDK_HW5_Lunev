package HomeWork_5_Lunev;

/**
 * 1. Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
 * 2. Вилки лежат на столе между каждой парой ближайших философов.
 * 3. Каждый философ может либо есть, либо размышлять.
 * 4. Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
 * 5. Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
 * 6. Можно брать только две вилки одновременно.
 * Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        int numPhilosophers = 5;

        // Создаю вилки
        Lock[] forks = new Lock[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new ReentrantLock();
        }

        // Создаю философов
        Philosopher[] philosophers = new Philosopher[numPhilosophers];

        // Логика взятия вилок
        for (int i = 0; i < numPhilosophers; i++) {
            Lock leftFork = forks[i];
            Lock rightFork = forks[(i + 1) % numPhilosophers];
            philosophers[i] = new Philosopher(i, leftFork, rightFork);
            philosophers[i].start();
        }
    }
}

