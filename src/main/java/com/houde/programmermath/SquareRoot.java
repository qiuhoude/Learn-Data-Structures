package com.houde.programmermath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 不用math函数实现求平方根的方式
 *
 * @author qiukun
 * @create 2019-02-01 11:50
 */
public class SquareRoot {
    private static final Logger logger = LoggerFactory.getLogger(SquareRoot.class);

    /**
     * 获取某个数的平方根 , 使用二分查找的方式找到这个数
     *
     * @param n              求大于1的平方根
     * @param deltaThreshold 精度值 0.001表示精确到小数点
     * @param maxTry
     * @return
     */
    private static double getSquareRoot(int n, double deltaThreshold, int maxTry) {
        if (n < 1) {
            return -1.0;
        }
        double min = 1.0, max = (double) n;
        for (int i = 0; i < maxTry; i++) {
            double middle = min + (max - min) / 2; //原来是 (max + min)/2 避免溢出
            double square = middle * middle;
            double delta = Math.abs((square / n) - 1);
            if (delta <= deltaThreshold) {
                return middle;
            } else {
                if (square > n) {
                    max = middle;
                } else {
                    min = middle;
                }
            }
        }
        return -2.0;
    }

    public static void main(String[] args) {
        int n = 2;
        double res = getSquareRoot(n, 0.000001, 100);
        logger.debug("{} 【自己的函数】的平方根是  {}", n, res);
        logger.debug("{} 【jdk math】的平方根是  {}", n, Math.sqrt(n));
    }
}
