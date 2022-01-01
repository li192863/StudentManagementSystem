package com.lee.util;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateVerificationCodeImageTest {
    @Test
    public void test() throws IOException {
        BufferedImage bufferedImage = CreateVerificationCodeImage.getVerificationCodeImage();
        System.out.println(CreateVerificationCodeImage.getVerificationCode());
        FileOutputStream fos = new FileOutputStream("img.jpg");
        ImageIO.write(bufferedImage, "jpg", fos);
        fos.close();
    }
}
