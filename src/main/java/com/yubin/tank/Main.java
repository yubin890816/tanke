package com.yubin.tank;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // 创建窗口对象
        TankFrame f = new TankFrame();
        while (true) {
            Thread.sleep(50);
            f.repaint();
        }
    }
}
