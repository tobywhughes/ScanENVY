/**
 * Main panel
 * @author Thomas Edwards <010197938, edwards.itj@gmail.com>
 */
package scanenvy;

import scanenvy.Shane.Run;
import static java.awt.BorderLayout.LINE_START;
import java.awt.Color;
import static java.awt.Color.WHITE;
import java.awt.Dimension;

import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.HORIZONTAL;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Character.isDigit;
import static java.lang.Thread.currentThread;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import static javax.swing.Box.createVerticalGlue;
import javax.swing.BoxLayout;
import static javax.swing.BoxLayout.Y_AXIS;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import res.CustomFonts;


public class HomePanel extends JPanel{
    private Run run = new Run();//Changed by Kir
    private JPanel inputArea;
    private JPanel imagePane;
    private Logo logo = new Logo(this);
    private BufferedImage upcImage;
    private CustomFonts fonts = new CustomFonts(CustomFonts.CUSTOM_ONLY);
    private String UPC;
    private int count = 0;
    private static String type = "";
    private static JLabel typeLabel = new JLabel(type);
    private static JLabel upcLabel;
    private Input input;
    //private Timer timer = new Timer(1000, time());
    
    public HomePanel(Input input){
        run.loadData();
        this.input = input;
        BoxLayout layout = new BoxLayout(this, Y_AXIS);
        setLayout(layout);
        
        add(createVerticalGlue());

        inputArea = inputPanel();
        
        imagePane = imagePanel();
        add(logo, LINE_START);
        add(imagePane, LINE_START);
        add(inputArea, LINE_START);     
    }
    public String getUPC(){
        return UPC;
    }
    public Run getRun(){
        return run;
    }
    private JPanel inputPanel(){
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        
        panel.setBackground(WHITE);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1.0;
        gbc.ipady =0;
        gbc.ipady = 0;
        gbc.fill = HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(
                fonts.getTextFonted(fonts.getCustomFont(CustomFonts.ROBOTO, 16), "Enter UPC: ", Color.BLACK)));
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(label, gbc);
        
        gbc.weighty = 1.0;
        gbc.fill = HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        JTextField textBox = new JTextField();
        textBox.addKeyListener(keyListener(textBox.getText()));
        textBox.getDocument().addDocumentListener(docListen());
        textBox.setPreferredSize( new Dimension( 200, 24 ) );
        //textBox.setFont(fonts.getCustomFont(CustomFonts.ROBOTO, 16));
        panel.add(textBox, gbc);
        
        UPC = textBox.getText();

        gbc.weighty = 1.0;
        gbc.ipady = 0;
        gbc.fill = HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        JButton button = new JButton();
        
        button.setIcon(new ImageIcon(
                fonts.getTextFonted(fonts.getCustomFont(CustomFonts.ROBOTO, 16), "Open Barcode", Color.BLACK)));
        button.addActionListener(open());
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true); 
       // button.setBorder(new BorderFactory.createEmptyBorder(15, 8, 10, 8));
        panel.add(button, gbc);
        gbc.weighty = 1.0;
        gbc.ipady = 0;
        gbc.fill = HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        JButton okButton = new JButton();
        
        okButton.setIcon(new ImageIcon(
                fonts.getTextFonted(fonts.getCustomFont(CustomFonts.ROBOTO, 16), "Ok", Color.BLACK)));
        okButton.addActionListener(enter(textBox));
        okButton.setBackground(Color.WHITE);
        okButton.setFocusPainted(false);
        okButton.setContentAreaFilled(true); 
       // button.setBorder(new BorderFactory.createEmptyBorder(15, 8, 10, 8));
        panel.add(okButton, gbc);
        
        
        //panel.setPreferredSize(new Dimension(200, 50));
        return panel;
    }
    private JPanel imagePanel(){
        JPanel panel = new JPanel();
        upcLabel = new JLabel();
        upcLabel.setSize(new Dimension(100, 100));
        upcLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        panel.add(upcLabel);
        panel.setPreferredSize(new Dimension(100,200));
        panel.setBackground(Color.WHITE);
        return panel;
        
    }
    
    private KeyAdapter keyListener(final String text){
        KeyAdapter ka = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                int k = e.getKeyChar();
                if (!isDigit(c) || count > 13) {             
                    e.consume();
                }    
                else if (e.getKeyCode() == KeyEvent.VK_ENTER ){
                    enter(null);
                }
                getType(text);
                typeLabel.setText(type);            
            }
        };
        return ka;
    }
    private DocumentListener docListen(){
        DocumentListener dl = new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                
                repaint();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                addCount();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                subCount();
                System.out.println(e);
            }
        };
        return dl;
    }
    private ActionListener open(){
        final JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png"));
        chooser.setAcceptAllFileFilterUsed(false);
        
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String name = chooser.getSelectedFile().getAbsolutePath();
                    ClassLoader classloader = currentThread().getContextClassLoader();
                    try {
                        upcImage = ImageIO.read(new File(name));
                        upcLabel.setIcon(new ImageIcon(upcImage));
                    } catch (IOException ex) {
                        Logger.getLogger(HomePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    inputArea.repaint();
                }
            }
        };
        return al;
    }
    
    private ActionListener enter(final JTextField textBox){             
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UPC = textBox.getText();
                run.run(UPC);//Changed by Kir
                input.switchViews(Input.INFO);
                upcImage = null;
                upcLabel.setIcon(null);
                repaint();
                logo.repaint();
            }
        };
        return al;
    }
    
    private void addCount(){
        ++count;
    }
    private void subCount(){
        --count;
    }
    private ActionListener time(){
        ActionListener al = (ActionEvent e) -> {
            repaint();
        };
        return al;
    }
    private void getType(String str){
        if (str.length() == 8){
            type = "UPC-E";          
        }
        else if(str.length() == 12) {
            type = "UPC-A";
        }
        else if(str.length() == 13) {
            type = "EAN/UCC-13";
        }
        else{
            System.out.println("UPC not valid");
        }
        typeLabel.setText(type);
    }
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(input.getWidth(), input.getHeight());
    }
}
