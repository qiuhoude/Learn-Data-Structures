package com.houde.algoview.common;

import java.awt.*;

/**
 * 绘制控制基类,mvc中的c
 * Created by houde
 * 2020-04-21 12:00
 */
public abstract class BaseAlgoVisualizer<T> {
    public final static int DELAY_DEFAULT = 40;

    protected T data;                    // 数据
    protected BaseAlgoFrame<T> frame;    // 视图
    protected int canvasWidth;
    protected int canvasHeight;

    public BaseAlgoVisualizer(String title, int canvasWidth, int canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        // 初始化数据
        data = initData();

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = createFrame(title, canvasWidth, canvasHeight);
            addListener(frame);
            new Thread(() -> run()).start();
        });
    }


    protected BaseAlgoFrame createFrame(String title, int canvasWidth, int canvasHeight) {
        return new BaseAlgoFrame<T>(title, canvasWidth, canvasHeight) {
            @Override
            protected void drawContent(AlgoCanvas canvas, Graphics2D g2d, T data) {
                draw(g2d, data);
            }
        };
    }

    protected abstract void draw(Graphics2D g2d, T data);


    protected abstract T initData();

    /**
     * 暂停
     */
    protected void pause() {
        pause(DELAY_DEFAULT);
    }

    protected void pause(int delay) {
        CanvasUtils.pause(delay);
    }

    protected void repaintByData(T data, int delay) {
        frame.render(data);
        pause(delay);
    }

    protected void repaintByData(T data) {
        frame.render(data);
        pause();
    }

    /**
     * 动画逻辑
     */
    protected abstract void run();


    /**
     * 添加监听事件
     *
     * @param frame
     */
    protected void addListener(BaseAlgoFrame<T> frame) {

    }

}
