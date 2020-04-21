package com.houde.algoview.preparegui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 动画控制器Controller
 * Created by houde
 * 2020-04-20 11:35
 */
public class AlgoVisualizer {
    private static final Logger logger = LoggerFactory.getLogger(AlgoVisualizer.class);

    private final Circle[] circles;
    private final int sceneWidth;
    private final int sceneHeight;
    private boolean isAnimated = true;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;

        int R = 50;
        circles = new Circle[N];
        int safeCnt = 1000;
        for (int i = 0; i < N; i++) {

            int x = (int) (Math.random() * (sceneWidth - 2 * R)) + R;
            int y = (int) (Math.random() * (sceneHeight - 2 * R)) + R;
            // 避免初始化有重叠

          check:  for (; ; ) {
                safeCnt--;
                if (safeCnt == 0) {
                    break;
                }
                for (int j = 0; j < i; j++) {
                    if (circles[j].colliding(x, y, R)) { // 检查出重叠 进行重新随机
                        x = (int) (Math.random() * (sceneWidth - 2 * R)) + R;
                        y = (int) (Math.random() * (sceneHeight - 2 * R)) + R;
                        continue check;
                    }
                }
                break;
            }
            int vx = (int) (Math.random() * 11) - 5;
            int vy = (int) (Math.random() * 11) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
//            frame.render(circles);
            // 添加事件
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                while (true) {
                    // 绘制数据
                    frame.render(circles);

                    CanvasUtils.pause(20); //暂停

                    // 更新数据
                    if (!isAnimated) {
                        for (Circle circle : circles) {
                            circle.move(0, 0, sceneWidth, sceneHeight);
                            for (Circle other : circles) {
                                if (other != circle && circle.colliding(other)) {
                                    circle.resolveCollision(other);
                                }
                            }
                        }
                    }

                }
            }).start();
        });
    }

    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyChar() == ' ') {
                isAnimated = !isAnimated;
            }
        }
    }

    private class AlgoMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
//            super.mousePressed(e);
//            logger.debug("point={}", event.getPoint());
//            logger.debug("frame.getBounds().height={}", frame.getBounds().height);
//            logger.debug("frame.getCanvasHeight()={}", frame.getCanvasHeight());
            event.translatePoint(0, -(frame.getBounds().height - frame.getCanvasHeight()));
            for (Circle circle : circles) {
                if (circle.containPoint(event.getPoint())) {
                    circle.isFilled = !circle.isFilled;
                }
            }
        }
    }

    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            AlgoFrame frame = new AlgoFrame("Welcome", 500, 500);
//        });
        AlgoVisualizer circleAnimation = new AlgoVisualizer(
                500, 500, 5);
    }
}
