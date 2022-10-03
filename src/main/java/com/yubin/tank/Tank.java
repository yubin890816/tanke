package com.yubin.tank;

import java.awt.*;

public class Tank {
    // 坦克移动的速度
    public static final int SPEED = 5;

    private int x, y;

    private Dir dir;

    // 控制坦克静止和移动
    private boolean moving = false;

    private TankFrame tf;

    public Tank(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        // 获取画笔老的颜色
        Color color = g.getColor();
        // 设置坦克的颜色
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 50, 50);
        // 还原画笔的颜色
        g.setColor(color);
        //x += 10;
        //y += 10;
        move();
    }

    private void move() {
        if (!moving) return;
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

    public void fire() {
        tf.bullet = new Bullet(x, y, dir);
    }
}
