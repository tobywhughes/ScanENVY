/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanenvy;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.Reader;

/**
 *
 * @author Thomas Edwards <010197938, edwards.itj@gmail.com>
 */
public class ParseBarcode {
    private BufferedImage barcode;
    
    public ParseBarcode (BufferedImage image){
        barcode = image;
    }
    private void parse(){
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        FontMetrics fontMetrics = g2d.getFontMetrics();
        image = new BufferedImage(barcode.getWidth(), 
                barcode.getHeight(), BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    }
    private void read(){
        int w = barcode.getWidth();
        int h = barcode.getHeight(null);
        int[] rgbs = new int[w*h];
        barcode.getRGB(0, 0, w, h, rgbs, 0, w);

        for (int i = 0; i < barcode.getWidth(); i++){
            int rgb = barcode.getRGB(i, barcode.getHeight()/2);
            Color color = new Color(barcode.getRGB(i, barcode.getHeight()/2));
            int r = color.getRed();
            int g = color.getGreen();
            int b = color.getBlue();
            double y = 0.2126*r + 0.7152*g + 0.0722*b;
            //if (Color c = y < 128 ? barcode.setRGB(i, barcode.getHeight()/2, Color.BLACK.getRGB()) : 
              //      barcode.setRGB(i, barcode.getHeight()/2, Color.WHITE.getRGB())){
            
            //}
       // }
        }
    }
}
