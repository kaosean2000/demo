package com.example.demo.controller;

import com.sun.xml.messaging.saaj.util.ByteOutputStream;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GetImageController {
    @GetMapping(value = "/image", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImage() throws IOException {
        BufferedImage bImg = new BufferedImage(800,800,BufferedImage.TYPE_3BYTE_BGR);
        var g = bImg.createGraphics();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHints(rh);
        g.setColor(Color.ORANGE);
        g.fillOval(0,0,300,300);
        g.setColor(Color.black);
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 2.4F);
        g.setFont(newFont);
        g.drawString("ES306",100,100);

        ByteOutputStream s = new ByteOutputStream();
        ImageIO.write(bImg, "png", s);
        s.flush();
        return s.getBytes();
    }
}
