package com.houde.statck_queue;

import java.util.Random;

/**
 * Created by houde
 * 2019-07-09 22:48
 */
public interface Queue<E> {

    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();

}


class TestMain {
    public static void main(String[] args) {
//        int opCnt = 100000;
//        benchmarkTestQueue(new ArrayQueue<>(), opCnt);
//        benchmarkTestQueue(new LoopQueue<>(), opCnt);

//        normalTestQueue(new ArrayQueue<>());
        normalTestQueue(new LoopQueue<>());
        normalTestQueue(new LoopNoSizeQueue<>());
    }


    public static void normalTestQueue(Queue<Integer> queue) {
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

    public static void benchmarkTestQueue(Queue<Integer> q, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            q.dequeue();

        long endTime = System.nanoTime();

        double costTime = (endTime - startTime) / 1000000000.0;
        System.out.println(q.getClass().getSimpleName() + ", time: " + costTime + " s");

    }
}