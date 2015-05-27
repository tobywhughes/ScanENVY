/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanenvy;

import static java.awt.Color.WHITE;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import javax.imageio.ImageIO;
import static javax.imageio.ImageIO.read;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Thomas Edwards <010197938, edwards.itj@gmail.com>
 */
public class Logo extends JPanel implements ActionListener{
    private Timer timer = new Timer(1000, this);
    private BufferedImage full_logo;
    private JPanel panel;
    private BufferedImage upcImage;
    private static JLabel upcLabel = new JLabel();
    
    public Logo(JPanel panel){
        this.panel = panel;
        setBackground(WHITE);
        getLogo();
        
        upcLabel.setSize(new Dimension(100, 100));
        upcLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(upcLabel);
    }
    private void getLogo(){
        ClassLoader classloader = currentThread().getContextClassLoader();
        try {
            full_logo = read(classloader.getResourceAsStream("res/img/low_main_logo.png"));                  
        } catch (IOException e) {
            out.println("logo not loaded");
        }
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        AffineTransform at = new AffineTransform();
        //at.scale(1.5, 1.5);
        at.translate(getWidth()/2 - full_logo.getWidth()/2, getHeight()/2 - full_logo.getHeight());
        g2d.drawImage(full_logo, at, null);
        
        //repaint();
    }
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(panel.getWidth(), 200);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
