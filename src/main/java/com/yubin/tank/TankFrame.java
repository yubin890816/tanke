package com.yubin.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    int x = 200, y = 200;

    Dir dir = Dir.DOWN;

    // 坦克移动的速度
    private static final int SPEED = 10;

    public TankFrame() throws HeadlessException {
        // 设置窗口大小
        setSize(800, 600);
        // 不能改变窗口大小
        setResizable(false);
        // 设置窗口标题栏的文字
        setTitle("tank war");
        // 设为可见
        setVisible(true);

        addKeyListener(new MyKeyListener());
        // 添加windows监听器
        addWindowListener(new WindowAdapter() {
            // 添加窗口关闭的监听器(点击窗口的红叉,关闭窗口)
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // 窗口需要重新绘制的时候, 自动执行此方法(如:窗口刚创建的时候、窗口被盖住之后重新显示出来的时候...)
    // Graphics: 相当于画笔
    @Override
    public void paint(Graphics g) {
        /**
         * 在窗口 200(x轴) 200(y轴)的位置画一个宽50 高50 的矩形
         * 这里将矩形位置的 x、y的变量设置为变量
         */
        g.fillRect(x, y, 50, 50);
        //x += 10;
        //y += 10;
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

    class MyKeyListener extends KeyAdapter {

        boolean bL = false, bU = false, bR = false, bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    //x -= 10;
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    //y -= 10;
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    //x += 10;
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    //y += 10;
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (bL) dir = Dir.LEFT;
            if (bU) dir = Dir.UP;
            if (bR) dir = Dir.RIGHT;
            if (bD) dir = Dir.DOWN;
        }
    }

}
