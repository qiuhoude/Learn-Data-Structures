package com.houde.algoview._03_probability_simulations;

import com.houde.algoview.common.BaseAlgoVisualizer;
import com.houde.algoview.common.CanvasUtils;
import com.houde.algoview.preparegui.AlgoVisualizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.List;

/**
 * 模特卡洛方式获取PI值
 * 圆 = PI * R^2
 * 方 = 2R * 2R = 4R^2
 * PI = 4 * 圆 / 方
 * Created by houde
 * 2020-04-21 14:45
 */
public class MonteCarloGetPIVisualizer extends BaseAlgoVisualizer<MonteCarloData> {
    private static final Logger logger = LoggerFactory.getLogger(AlgoVisualizer.class);

    public MonteCarloGetPIVisualizer(String title, int canvasWidth, int canvasHeight) {
        super(title, canvasWidth, canvasHeight);
        if (canvasWidth != canvasHeight)
            throw new IllegalArgumentException("This demo must be run in a square window!");

    }

    @Override
    protected void draw(Graphics2D g2d, MonteCarloData data) {
        MonteCarloData.Circle circle = data.getCircle();
        List<Point> points = data.getPoints();

        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            if (circle.containPoint(p)) {
                CanvasUtils.setColor(g2d, CanvasUtils.Red);
            } else {
                CanvasUtils.setColor(g2d, CanvasUtils.Green);
            }
            CanvasUtils.fillCircle(g2d, p.x, p.y, 3);
        }

        CanvasUtils.setColor(g2d, CanvasUtils.Blue);
        CanvasUtils.setStrokeWidth(g2d, 3);
        CanvasUtils.strokeCircle(g2d, circle.getX(), circle.getY(), circle.getR());
    }

    @Override
    protected MonteCarloData initData() {
        int w = canvasWidth / 2;
        int N = 100_000;
        return new MonteCarloData(new MonteCarloData.Circle(w, w, w), N);
    }

    @Override
    protected void run() {
        int f = data.getN() / 100;
        for (int i = 0; i < data.getN(); i++) {
            int x = (int) (Math.random() * frame.getCanvasWidth());
            int y = (int) (Math.random() * frame.getCanvasHeight());
            Point point = new Point(x, y);
            if (data.getCircle().containPoint(point)) {
                data.incrInCircleCnt();
            }
            data.getPoints().add(point);
            if (i % f == 0) {
                repaintByData(data);
            }
//            logger.debug("PI ≈ {}", data.getPI());
        }
        repaintByData(data);
        logger.debug("PI ≈ {}", data.getPI());

    }

    public static void main(String[] args) {
        new MonteCarloGetPIVisualizer("Get Pi with Monte Carlo", 800, 800);
    }
}
