package scanenvy;

import scanenvy.HomePanel;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import java.awt.Dimension;
import java.awt.Image;
import static java.awt.Toolkit.getDefaultToolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static javax.imageio.ImageIO.read;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static javax.swing.SwingUtilities.invokeLater;
import scanenvy.Shane.Run;

/**
 *
 * @author Thomas Edwards <edwards.itj@gmail.com>
 */
public class Input extends JFrame{
    private static JPanel mainPanel = new JPanel();
    
    private HomePanel hp;
    private InfoPanel dip;
    private EditPanel ep;
    private Product product;
    private Run run;
    public static int HOME = 0;
    public static int INFO = 1;
    public static int EDIT = 2;
    
    public Input(){
        setTitle("ScanENVY");
        icon();
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        mainPanel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));

        hp = new HomePanel(this);
        
        mainPanel.add(hp);
        add(mainPanel, CENTER);
        
        
    }
    /**
     * HOME, INFO...
     * @param selection 
     */
    public void switchViews(int selection){
        mainPanel.removeAll();
        switch(selection){
            case 0:{
                hp = new HomePanel(this);
                mainPanel.add(hp);
                break;
            }
            case 1:{
                run = hp.getRun();
                System.out.println(hp.getUPC());
                product = run.lookUp(hp.getUPC());
                
                dip = new InfoPanel(this, product);//hp.getUPC() change to product
                dip.setUPC(hp.getUPC());
                mainPanel.add(dip);
                break;
            }
            case 2:{
                ep = new EditPanel(this, dip.getProduct());
                ep.setUPC(hp.getUPC());
                mainPanel.add(ep);
                break;
            }
        }
        //add(mainPanel, CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    private void icon(){
        ImageIcon image = null;
        ClassLoader classloader = currentThread().getContextClassLoader();
        image = new ImageIcon(classloader.getResource("res/img/iconSize.png"));                  
        setIconImage(image.getImage());
    }
    public static void main(String[] args) {
        invokeLater(() -> {
            JFrame frame = new Input();
            Dimension screenSize = getDefaultToolkit().getScreenSize();
            frame.setBounds(0,40,screenSize.width, screenSize.height -40);
            frame.setPreferredSize(new Dimension(screenSize.width, screenSize.height -40));
            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
    
}
