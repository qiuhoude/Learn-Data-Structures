package com.houde.algoview._03_probability_simulations;

import com.houde.algoview.common.BaseAlgoVisualizer;
import com.houde.algoview.common.CanvasUtils;

import java.awt.*;
import java.util.Arrays;

/**
 * 分钱问题 ,每人初始有100元,每轮每个人必须随机给另外一个人1元,n轮过后还每个人的钱数
 * <p>
 * Created by houde
 * 2020-04-21 12:50
 */
public class MoneyExperimentVisualizer extends BaseAlgoVisualizer<int[]> {


    public MoneyExperimentVisualizer(int canvasWidth, int canvasHeight) {
        super("Money Problem", canvasWidth, canvasHeight);
    }


    @Override
    protected int[] initData() {
        int[] money = new int[100];
        for (int i = 0; i < money.length; i++) {
            money[i] = 100;
        }
        return money;
    }

    @Override
    protected void draw(Graphics2D g2d, int[] money) {
        CanvasUtils.setColor(g2d, CanvasUtils.Blue);
        int w = canvasWidth / money.length; // 计算每个小块的宽度
        for (int i = 0; i < money.length; i++) {
            int startX = i * w + 1;
            int startY = canvasHeight - money[i];
            CanvasUtils.fillRectangle(g2d,
                    startX, startY, w - 1, money[i]);
            CanvasUtils.drawText(g2d, money[i] + "", startX + (w / 2), startY - 5, 9.4f);
        }
    }

    @Override
    protected void run() {
        while (true) {
            repaintByData(data);

            for (int i = 0; i < data.length; i++) {
                if (data[i] > 0) {
                    int j = (int) (Math.random() * data.length);
                    data[i] -= 1;
                    data[j] += 1;
                }
            }
            Arrays.sort(data);
        }
    }

    public static void main(String[] args) {
        int sceneWidth = 2000;
        int sceneHeight = 800;
        new MoneyExperimentVisualizer(sceneWidth, sceneHeight);
    }

}
