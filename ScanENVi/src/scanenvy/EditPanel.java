/**
 * Main panel
 * @author Thomas Edwards <010197938, edwards.itj@gmail.com>
 */
package scanenvy;

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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import res.CustomFonts;


public class EditPanel extends JPanel{
    private Product product;
    private JPanel inputArea;
    private JPanel imagePane;
    private Logo logo = new Logo(this);
    private BufferedImage full_logo;
    private BufferedImage upcImage;
    private CustomFonts fonts = new CustomFonts(CustomFonts.CUSTOM_ONLY);
    private String UPC;
    private static int count = 0;
    private static String type = "";
    private static JLabel typeLabel = new JLabel(type);
    private static JLabel upcLabel;
    private Input input;
    //private Timer timer = new Timer(1000, time());
    
    public EditPanel(Input input, Product product){
        this.input = input;
        this.product = product;
        this.UPC = product.getUpc();
        BoxLayout layout = new BoxLayout(this, Y_AXIS);
        setLayout(layout);
        
        add(createVerticalGlue());

        inputArea = inputPanel();
        
        add(logo, LINE_START);
        add(inputArea, LINE_START);     
    }
    public void setUPC(String UPC){
        this.UPC = UPC;
    }
    
    private JPanel inputPanel(){
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        
        panel.setBackground(WHITE);
        //gbc.anchor = GridBagConstraints.NORTH;
        //gbc.weighty = 1;
        gbc.ipady =0;
        gbc.ipady = 0;
        gbc.fill = gbc.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        
        JTextField upcText = new JTextField(product.getUpc());
        upcText.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                upcText.setText("");
            }
        });
        upcText.setEditable(true);
        upcText.setFont(fonts.getCustomFont(CustomFonts.ROBOTO, 16));
        panel.add(upcText, gbc);
        
        gbc.weighty = 0;
        gbc.fill = gbc.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        JTextField item = new JTextField(product.getName());
        item.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                item.setText("");
            }
        });
        item.setEditable(true);
        item.setPreferredSize( new Dimension( 200, 24 ) );
        item.setFont(fonts.getCustomFont(CustomFonts.ROBOTO, 16));
        panel.add(item, gbc);
        
        gbc.weighty = 0;
        gbc.fill = gbc.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        JTextField manuf = new JTextField(product.getManufact());
        manuf.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                manuf.setText("");
            }
        });
        manuf.setEditable(true);
        manuf.setFont(fonts.getCustomFont(CustomFonts.ROBOTO, 16));
        panel.add(manuf, gbc);
        
        gbc.weighty = 0;
        gbc.fill = gbc.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        String[] recycleCodes = { "Plastics", "Metal", 
            "Cardboard/Paper", "Glass", "Batteries/Bulbs", "Electronics", 
            "Not Recyclable", "Electronics", "Not Recyclable" };
        JComboBox recycleBox = new JComboBox(recycleCodes);
        recycleBox.setSelectedIndex(product.getRType());
        recycleBox.setEditable(false);
        recycleBox.setFont(fonts.getCustomFont(CustomFonts.ROBOTO, 16));
        panel.add(recycleBox, gbc);
        
        UPC = item.getText();

        gbc.weighty = 1.0;
        gbc.ipady = 0;
        gbc.fill = HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        JButton button = new JButton();
        
        button.setIcon(new ImageIcon(
                fonts.getTextFonted(fonts.getCustomFont(CustomFonts.ROBOTO, 16), "Help", Color.BLACK)));
        button.addActionListener(help());
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true); 
       // button.setBorder(new BorderFactory.createEmptyBorder(15, 8, 10, 8));
        panel.add(button, gbc);
        gbc.weighty = 1.0;
        gbc.ipady = 0;
        gbc.fill = HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        JButton okButton = new JButton();
        
        okButton.setIcon(new ImageIcon(
                fonts.getTextFonted(fonts.getCustomFont(CustomFonts.ROBOTO, 16), "Done", Color.BLACK)));
        okButton.addActionListener(done());
        okButton.setBackground(Color.WHITE);
        okButton.setFocusPainted(false);
        okButton.setContentAreaFilled(true); 
       // button.setBorder(new BorderFactory.createEmptyBorder(15, 8, 10, 8));
        panel.add(okButton, gbc);
        
        
        //panel.setPreferredSize(new Dimension(200, 50));
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
                    done();
                }
                getType(text);
                typeLabel.setText(type);            
            }
        };
        return ka;
    }
    private DocumentListener upcListen(){
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
    private MouseListener clearText(){
        MouseListener ml = new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                //e.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
               
            }
        };
        return ml;
    }
    private ActionListener help(){
        
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
   
            }
        };
        return al;
    }
    
    private ActionListener done(){
              
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.switchViews(Input.INFO);
            }
        };
        return al;
    }
    
    private void addCount(){
        count++;
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
        if (str.length() == 6){
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
