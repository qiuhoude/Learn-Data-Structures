package com.houde.algoview._03_probability_simulations;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by houde
 * 2020-04-21 14:45
 */
public class MonteCarloData {

    private final Circle circle;
    private final int n; // 打点的个数
    private final List<Point> points;
    private int inCircleCnt = 0;

    public MonteCarloData(Circle cricle, int n) {
        this.circle = cricle;
        this.n = n;
        points = new ArrayList<>(n);
    }

    public Circle getCircle() {
        return circle;
    }

    public int getN() {
        return n;
    }

    public List<Point> getPoints() {
        return points;
    }

    public int getInCircleCnt() {
        return inCircleCnt;
    }

    public void incrInCircleCnt() {
        this.inCircleCnt++;
    }

    public double getPI() {
        int sqrt = getPoints().size();
        if (sqrt == 0) {
            return 0;
        }
        int circleCnt = getInCircleCnt();
        //  PI = 4 * 圆 / 方
        double PI = 4.0 * circleCnt / sqrt;
        return PI;
    }

    public static class Circle {
        private final int x, y, r;

        public Circle(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getR() {
            return r;
        }

        public boolean containPoint(Point p) {
            return Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2) <= r * r;
        }
    }


}
