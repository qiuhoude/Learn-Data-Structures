package com.houde.algoview._03_probability_simulations;

import com.houde.algoview.common.CanvasUtils;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by houde
 * 2020-04-21 13:35
 */
public class MoreMoneyExperimentVisualizer extends MoneyExperimentVisualizer {

    public MoreMoneyExperimentVisualizer(int canvasWidth, int canvasHeight) {
        super(canvasWidth, canvasHeight);
    }

    @Override
    protected void draw(Graphics2D g2d, int[] money) {

        int w = canvasWidth / money.length; // 计算每个小块的宽度
        for (int i = 0; i < money.length; i++) {
            int startX = i * w + 1;
            if (money[i] >= 0) {
                int startY = canvasHeight / 2 - money[i];
                CanvasUtils.setColor(g2d, CanvasUtils.Blue);
                CanvasUtils.fillRectangle(g2d,
                        startX, startY, w - 1, money[i]);
                CanvasUtils.drawText(g2d, String.valueOf(money[i]), startX + (w / 2), startY - 5, 8.5f);
            } else {
                int startY = canvasHeight / 2;
                CanvasUtils.setColor(g2d, CanvasUtils.Red);
                CanvasUtils.fillRectangle(g2d,
                        startX, startY, w - 1, Math.abs(money[i]));
                CanvasUtils.drawText(g2d, String.valueOf(-money[i]), startX + (w / 2), startY - money[i] + 5, 8.5f);
            }
        }
    }

    @Override
    protected void run() {
        while (true) {
            repaintByData(data);

            for (int k = 0; k < 50; k++) {
                for (int i = 0; i < data.length; i++) {
                    int j = (int) (Math.random() * data.length);
                    data[i] -= 1;
                    data[j] += 1;
                }
            }
            Arrays.sort(data);
        }
    }

    public static void main(String[] args) {
        new MoreMoneyExperimentVisualizer(1000, 800);
    }
}
