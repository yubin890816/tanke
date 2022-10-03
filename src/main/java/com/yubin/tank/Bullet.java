package com.yubin.tank;

import java.awt.*;

/**
 * 子弹实体类
 */
public class Bullet {

    // 子弹移动的速度
    public static final int SPEED = 10;

    private int x, y;

    private Dir dir;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        // 设置子弹的颜色
        g.setColor(Color.RED);
        g.fillOval(x, y, 10, 10);
        g.setColor(color);
        //x += 10;
        //y += 10;
        move();
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
    }
}
