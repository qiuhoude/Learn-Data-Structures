package com.houde.algoview.preparegui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by houde
 * 2020-04-20 00:13
 */
public class AlgoFrame extends JFrame {
    private int canvasWidth;
    private int canvasHeight;
    private JPanel canvas;
    private Circle[] circles;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight) {
        super(title);
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

//        setSize(canvasWidth, canvasHeight);
//        canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight)); // 设置面板的大小
        canvas = new AlgoCanvas();
        setContentPane(canvas); // 设置内容
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public AlgoFrame(String title) {

        this(title, 768, 768);
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }


    public void render(Circle[] circles) {
        this.circles = circles;
        repaint();  // 重新绘制
    }


    private class AlgoCanvas extends JPanel {


        public AlgoCanvas() {
            // 开启双缓存
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 画一个圆
            // g.drawOval(50, 50, 300, 300);
            Graphics2D g2d = (Graphics2D) g; //   使用Graphics2D 的API

            // 画笔的宽度
            /*int strokeWidth = 5;
            g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            g2d.setColor(Color.pink); // 绘制的颜色
            Ellipse2D ellipse = new Ellipse2D.Double(50, 50, 300, 300);
            g2d.draw(ellipse);

            g2d.setColor(Color.BLUE);
            Ellipse2D ellipse2 = new Ellipse2D.Double(60, 60, 280, 280);
            g2d.fill(ellipse2); // 填充*/

            // 开启抗锯齿
            RenderingHints hints = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.addRenderingHints(hints);

            // 绘制
            CanvasUtils.setStrokeWidth(g2d, 1);
            CanvasUtils.setColor(g2d, Color.BLUE);
            for (Circle circle : circles) {
                if (!circle.isFilled) {
                    CanvasUtils.strokeCircle(g2d, circle.x, circle.y, circle.getR());
                } else {
                    CanvasUtils.fillCircle(g2d, circle.x, circle.y, circle.getR());
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            // 大小
            return new Dimension(canvasWidth, canvasHeight);
        }
    }
}
