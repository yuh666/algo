package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yuh
 * @date 2019-04-02 11:42
 **/
public class Main {


    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();


    static class Even implements Runnable {

        @Override
        public void run() {
            lock.lock();
            for (int i = 0; i < Integer.MAX_VALUE; i += 2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + i);
                condition.signalAll();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();
        }
    }

    static class Odd implements Runnable {

        @Override
        public void run() {
            lock.lock();
            for (int i = 1; i < Integer.MAX_VALUE; i += 2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + i);
                condition.signalAll();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Even(),"even-thread").start();
        Thread.sleep(1000);
        new Thread(new Odd(),"odd-thread").start();
    }
}
