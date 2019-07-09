package com.houde.statck_queue;

/**
 * 栈
 * Created by houde
 * 2019-07-09 22:36
 */
public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
