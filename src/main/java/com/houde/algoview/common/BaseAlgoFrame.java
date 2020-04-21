package com.houde.algoview.common;

import javax.swing.*;
import java.awt.*;

/**
 * 绘制基类,mvc中的v , 泛型T表示m
 * Created by houde
 * 2020-04-21 11:49
 */
public abstract class BaseAlgoFrame<T> extends JFrame {

    protected int canvasWidth;
    protected int canvasHeight;
    protected AlgoCanvas canvas;
    protected T data; // 数据

    BaseAlgoFrame(String title, int canvasWidth, int canvasHeight) {

        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        canvas = new AlgoCanvas();
        setContentPane(canvas);
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setVisible(true);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }


    public void render(T data) {
        this.data = data;
        repaint();
    }

    /**
     * 绘制内容
     *
     * @param canvas
     * @param g2d
     * @param data
     */
    protected abstract void drawContent(AlgoCanvas canvas, Graphics2D g2d, T data);


    public class AlgoCanvas extends JPanel {

        public AlgoCanvas() {
            // 双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 具体绘制
            drawContent(canvas, g2d, data);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }
    }

}
