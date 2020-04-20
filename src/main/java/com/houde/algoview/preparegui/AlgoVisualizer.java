package com.houde.algoview.preparegui;

import java.awt.*;

/**
 * 动画控制器Controller
 * Created by houde
 * 2020-04-20 11:35
 */
public class AlgoVisualizer {

    private final Circle[] circles;
    private final int sceneWidth;
    private final int sceneHeight;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int N) {
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;

        int R = 50;
        circles = new Circle[N];
        for (int i = 0; i < N; i++) {
            int x = (int) (Math.random() * (sceneWidth - 2 * R)) + R;
            int y = (int) (Math.random() * (sceneHeight - 2 * R)) + R;
            int vx = (int) (Math.random() * 11) - 5;
            int vy = (int) (Math.random() * 11) - 5;
            circles[i] = new Circle(x, y, R, vx, vy);
        }
        EventQueue.invokeLater(() -> {
            AlgoFrame frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
//            frame.render(circles);
            new Thread(() -> {
                while (true) {
                    // 绘制数据
                    frame.render(circles);

                    CanvasUtils.pause(20); //暂停

                    // 更新数据
                    for (Circle circle : circles) {
                        circle.move(0, 0, sceneWidth, sceneHeight);
                    }

                }
            }).start();
        });
    }
    
}
