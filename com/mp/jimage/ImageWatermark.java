package com.mp.jimage;

import java.io.File;
import org.apache.commons.cli.ParseException;

public class ImageWatermark {

    public static void main(String[] args) {

//        String[] testArgs = {};
//        String[] testArgs = {"-p", "/home/matte/Desktop/TEST"};
        Cli cli = new Cli(args);
        try {
            cli.parseCli();
            File path = new File(cli.getPath());
            if (path.isDirectory()) {
                Watermark w = new Watermark(path);
                w.init();
            } else {
                System.out.println("Directory inesistente");
                System.exit(0);
            }
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
    }
}
