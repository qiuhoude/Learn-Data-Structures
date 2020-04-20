package com.houde.algoview.preparegui;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by houde
 * 2020-04-20 10:59
 */
public abstract class CanvasUtils {

    /**
     * 画空心圆
     *
     * @param g
     * @param x
     * @param y
     * @param r
     */
    public static void strokeCircle(Graphics2D g, int x, int y, int r) {

        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g.draw(circle);
    }

    /**
     * 实心圆
     *
     * @param g
     * @param x
     * @param y
     * @param r
     */
    public static void fillCircle(Graphics2D g, int x, int y, int r) {

        Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        g.fill(circle);
    }

    /**
     * 设置颜色
     *
     * @param g
     * @param color
     */
    public static void setColor(Graphics2D g, Color color) {
        g.setColor(color);
    }

    /**
     * 设置画笔宽度
     *
     * @param g
     * @param w
     */
    public static void setStrokeWidth(Graphics2D g, int w) {
        int strokeWidth = w;
        g.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    /**
     * 暂停
     *
     * @param t
     */
    public static void pause(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            System.out.println("Error sleeping");
        }
    }
}
