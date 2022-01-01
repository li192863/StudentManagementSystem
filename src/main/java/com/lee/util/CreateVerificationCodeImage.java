package com.lee.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 生成验证码以及验证码图片
 */
public class CreateVerificationCodeImage {

    private static int WIDTH = 90;
    private static int HEIGHT = 35;
    private static int FONT_SIZE = 20; // 字符大小
    private static char[] verificationCode; // 验证码
    private static BufferedImage verificationCodeImage; // 验证码图片

    /**
     * 获取验证码图片
     * @return
     */
    public static BufferedImage getVerificationCodeImage() {
        verificationCodeImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
        Graphics graphics = verificationCodeImage.getGraphics();

        verificationCode = generateCheckedCode();
        drawBackground(graphics);
        drawRands(graphics, verificationCode);

        graphics.dispose();

        return verificationCodeImage;
    }

    /**
     * 获取验证码
     * @return
     */
    public static char[] getVerificationCode() {
        return verificationCode;
    }

    /**
     * 随机生成验证码
     * @return
     */
    private static char[] generateCheckedCode() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        char[] rands = new char[4];
        for (int i = 0; i < 4; i++) {
            int rand = (int)  (Math.random() * (10 + 26 * 2));
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }

    /**
     * 绘制验证码
     * @param g
     * @param rands
     */
    private static void drawRands(Graphics g, char[] rands) {
        g.setFont(new Font("Console", Font.BOLD, FONT_SIZE));

        for (int i = 0; i < rands.length; i++) {
            g.setColor(getRandomColor());
            g.drawString("" + rands[i], i * FONT_SIZE + 10, 25);
        }
    }

    /**
     * 绘制验证码图片背景
     * @param g
     */
    private static void drawBackground(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 绘制验证码干扰点
        for (int i = 0; i < 200; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            g.setColor(getRandomColor());
            g.drawOval(x, y, 1, 1);
        }
    }

    /**
     * 获取随机颜色
     * @return
     */
    private static Color getRandomColor() {
        Random ran = new Random();
        return new Color(ran.nextInt(220), ran.nextInt(220), ran.nextInt(220));
    }

}
