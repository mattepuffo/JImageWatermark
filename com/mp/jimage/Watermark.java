package com.mp.jimage;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Watermark {

    private File initDir;
    private File finalDir;

    public Watermark(File dir) {
        initDir = dir;
        finalDir = new File(dir + "/WATERMARKED/");
        if (!finalDir.exists()) {
            finalDir.mkdir();
        }
    }

    public void init() {
        if (initDir.isDirectory()) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png");
            File[] files = initDir.listFiles((File file) -> filter.accept(file));
            for (File input : files) {
                if (input.isFile()) {
                    String nome = input.getName();
                    if (nome.indexOf(".") > 0) {
                        nome = nome.substring(0, nome.lastIndexOf("."));
                    }
                    addWatermark(nome, input);
                }
            }
        }
    }

    private void addWatermark(String text, File input) {
        try {
            BufferedImage bi = ImageIO.read(input);
            Graphics2D g2d = (Graphics2D) bi.getGraphics();
//            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
//            g2d.setComposite(ac);
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 100));
            FontMetrics fontMetrics = g2d.getFontMetrics();
            Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);
            int centerX = (bi.getWidth() - (int) rect.getWidth()) / 2;
            int centerY = bi.getHeight() / 2;
//            g2d.drawString(text, centerX, centerY);
            g2d.drawString(text, centerX, 90);
            File output = new File(finalDir + "/" + input.getName());
            ImageIO.write(bi, "jpg", output);
            g2d.dispose();
            System.out.println("Watermark aggiunto all'immagine: " + input.getAbsolutePath());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
