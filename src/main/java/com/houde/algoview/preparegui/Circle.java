package com.houde.algoview.preparegui;

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

    public boolean colliding(Circle circle) {
        double distance = Math.sqrt(Math.pow(this.x - circle.x, 2)
                + Math.pow(this.y - circle.y, 2));
        return distance < this.r + circle.r;
    }

    public void resolveCollision(Circle circle) {

    }

    // 边界检查
    private void checkCollision(int minx, int miny, int maxx, int maxy) {
        if (x - r < minx) {
            x = r;
            vx = -vx;
        }
        if (x + r >= maxx) {
            x = maxx - r;
            vx = -vx;
        }
        if (y - r < miny) {
            y = r;
            vy = -vy;
        }
        if (y + r >= maxy) {
            y = maxy - r;
            vy = -vy;
        }
    }
}
