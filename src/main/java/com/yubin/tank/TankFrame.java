package com.yubin.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    // 写到这里, 如果我要和别人一起玩的话, 并且增加敌人的坦克, 那怎么处理呢? x1、y1; x2、y2; ...
    // 这样这个类该有多么的庞大, 其实细细思考下, x、y、dir 其实都是坦克的一个属性, 那么我们
    // 定义一个Tank 的类是不是好很多
    //int x = 200, y = 200;
    //Dir dir = Dir.DOWN;
    // 坦克移动的速度
    //private static final int SPEED = 10;

    static final int GAME_WIDTH = 800;

    static final int GAME_HEIGHT = 600;

    Tank myTank = new Tank(200, 200, Dir.DOWN, this);

    Bullet bullet = new Bullet(300, 300, Dir.DOWN);

    public TankFrame() throws HeadlessException {
        // 设置窗口大小
        setSize(GAME_WIDTH, GAME_HEIGHT);
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
        myTank.paint(g);
        bullet.paint(g);
        // 这个方法其实放在Tank类里面要更加的合适(坦克自己更加的了解他自己在什么位置)
        /**
         * 在窗口 200(x轴) 200(y轴)的位置画一个宽50 高50 的矩形
         * 这里将矩形位置的 x、y的变量设置为变量
         */
        /*g.fillRect(x, y, 50, 50);
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
        }*/
    }

    /**
     * 采用双缓存解决画面闪烁问题
     */
    Image offScreenImage = null;

    // repaint -> update -> paint (截获update)
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        // 获取内存中的画笔
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        // 将内存中的画面一次性画到屏幕上
        g.drawImage(offScreenImage, 0, 0, null);
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
                case KeyEvent.VK_SPACE: // 抬起 空格键 打出一颗子弹
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bU && !bR && !bD) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);
            }
        }
    }
}
