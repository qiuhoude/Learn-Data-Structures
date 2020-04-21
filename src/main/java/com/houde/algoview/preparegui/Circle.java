package com.houde.algoview.preparegui;

import java.awt.*;

/**
 * 圆的数据
 * Created by houde
 * 2020-04-20 11:28
 */
public class Circle {
    public int x; //圆心x
    public int y;
    private int r;

    public int vx; // x轴方向的速度
    public int vy;// y轴方向的速度
    public boolean isFilled = false; // 是否填充

    public Circle(int x, int y, int r, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
    }

    public int getR() {
        return r;
    }

    public void move(int minx, int miny, int maxx, int maxy) {
        x += vx;
        y += vy;

        checkCollision(minx, miny, maxx, maxy);
    }

    public boolean containPoint(Point p) {
        return (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y) <= r * r; // 去掉开根号减少计算
    }

    public boolean colliding(Circle circle) {
        return colliding(circle.x, circle.y, circle.r);
    }

    public boolean colliding(int ox, int oy, int or) {
        double distance = Math.sqrt(Math.pow(this.x - ox, 2)
                + Math.pow(this.y - oy, 2));
        return distance  < this.r + or ;
    }

    public void resolveCollision(Circle circle) {
        int tmpx = circle.vx;
        int tmpy = circle.vy;
        circle.vx = vx;
        circle.vy = vy;
        vx = tmpx;
        vy = tmpy;
    }

    // 边界检查
    private void checkCollision(int minx, int miny, int maxx, int maxy) {
        if (x - r < minx) { // 碰到了 y 轴起点
            x = r;
            vx = -vx;
        }
        if (x + r >= maxx) {
            x = maxx - r;
            vx = -vx;
        }
        if (y - r < miny) {// 碰到了 x 轴起点
            y = r;
            vy = -vy;
        }
        if (y + r >= maxy) {
            y = maxy - r;
            vy = -vy;
        }
    }
}
