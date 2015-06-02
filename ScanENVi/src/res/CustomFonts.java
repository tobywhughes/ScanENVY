package res;

/**
 * Custom Fonts
 * @author Thomas Edwards <010197938, edwards.itj@gmail.com>
 */

import java.awt.Color;
import static java.awt.Font.PLAIN;
import static java.awt.Font.TRUETYPE_FONT;
import static java.awt.Font.createFont;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import static java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Float.parseFloat;
import static java.lang.System.out;


public class CustomFonts{
    private java.awt.Font font;
    private java.awt.Font[] fonts = null;
    public final static int ROBOTO = 0;
    public final static int AVENIR = 1;
    public final static int HELVETICA = 2;
    public final static int CUSTOM_ONLY = 0;
    public final static int ADD_SYSTEM = 1;
    
    public CustomFonts(int load){
        //If you would rather have this handle the memory
        if (load == ADD_SYSTEM){
            loadSystemFonts();
        }
    }
    /**
     * Load System Fonts into memory
     */
    private void loadSystemFonts(){
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fonts = graphicsEnvironment.getAllFonts();
    }
    /**
     * Get System fonts
     * @return Font[] (System Fonts)
     */
    public java.awt.Font[] getSystemFonts() {
        if (fonts == null){
            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            fonts = graphicsEnvironment.getAllFonts();
        }
        return fonts;
        
    }
    /**
     * How to call
     * BufferedImage [image] = 
     * [CustomFonts].getFontImage([CustomFonts].getCustomFont(CustomFonts.ROBOTO, 20), Color.BLACK), 0,0, this);
     * 
     * @param font
     * @param color
     * @return 
     */
    public BufferedImage getFontImage(java.awt.Font font, Color color){
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setFont(font);
        FontMetrics fontMetrics = g2d.getFontMetrics();
        image = new BufferedImage(fontMetrics.stringWidth(font.getFontName()), 
                fontMetrics.getHeight(), BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        fontMetrics = g2d.getFontMetrics();
        g2d.setColor(color);
        g2d.drawString(font.getFontName(), 0, fontMetrics.getAscent());
        return image;
    }
    public BufferedImage getTextFonted(java.awt.Font font, String str, Color color){
        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setFont(font);
        FontMetrics fontMetrics = g2d.getFontMetrics();
        image = new BufferedImage(fontMetrics.stringWidth(str), 
                fontMetrics.getHeight(), BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        fontMetrics = g2d.getFontMetrics();
        g2d.setColor(color);
        g2d.drawString(str, 0, fontMetrics.getAscent());
        return image;
    }
    /**
     * Returns font based on selection
     * ttf's (font files) must be in the src\\util\\res folder
     * @param type (ROBOTO, AVENIR, HELVETICA)
     * @param inSize (Font size)
     * @return Font
     */
    public java.awt.Font getCustomFont(int type, int inSize) {
        String sizeStr = inSize + "f";
        float size = parseFloat(sizeStr);
        try {
            GraphicsEnvironment ge = getLocalGraphicsEnvironment();
            if (type == ROBOTO){
                this.font = createFont(TRUETYPE_FONT, new File("src\\res\\font\\Roboto.ttf")).deriveFont(size);
                ge.registerFont(createFont(TRUETYPE_FONT, new File("src\\res\\font\\Roboto.ttf")));
            }
            else if (type == AVENIR){
                font = createFont(TRUETYPE_FONT, new File("src\\res\\font\\New Cicle Fina.ttf")).deriveFont(size);
                ge.registerFont(createFont(TRUETYPE_FONT, new File("src\\res\\font\\New Cicle Fina.ttf")));
            }
            else if (type == HELVETICA){
                font = createFont(TRUETYPE_FONT, new File("src\\res\\font\\HelveticaNeue.ttf")).deriveFont(size);
                ge.registerFont(createFont(TRUETYPE_FONT, new File("src\\res\\font\\HelveticaNeue.ttf")));
            }
            else{
                throw new IOException();
            }
            return this.font;
        } catch (FontFormatException e1) {
            out.println("FontFormatException");
            this.font = new java.awt.Font("Calibri (Body)", PLAIN, (int)size);
            return font;
        } catch (IOException e1) {
            out.println("IOException Font");
            font = new java.awt.Font("Calibri (Body)", PLAIN, (int)size);
            return font;
        }
    }
}
