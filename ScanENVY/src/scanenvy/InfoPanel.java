/**
 * Main panel
 * @author Thomas Edwards <edwards.itj@gmail.com>
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
import scanenvy.Shane.Run;
import scanenvy.Toby.Database;

public class InfoPanel extends JPanel{
    private Database data = new Database(); //Changed by Kir
    private Run run;
    private Product product;
    private JPanel inputArea;
    private Logo logo = new Logo(this);
    private CustomFonts fonts = new CustomFonts(CustomFonts.CUSTOM_ONLY);
    private String UPC;
    private static int count = 0;
    private static String type = "";
    private static JLabel typeLabel = new JLabel(type);
    private Input input;
    private JTextField upcText;
    
    public InfoPanel(Input input, Product product){
        this.input = input;
        this.product = product;
        BoxLayout layout = new BoxLayout(this, Y_AXIS);
        setLayout(layout);

        add(createVerticalGlue());
        
        inputArea = inputPanel();
        
        add(logo, LINE_START);
        add(inputArea, LINE_START);     
    }
    public void setUPC(String UPC){
        this.UPC = UPC;
        upcText.setText(UPC);
        inputArea.repaint();
    }
    public Product  getProduct(){
        return product;
    }
    private JPanel inputPanel(){
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        
        panel.setBackground(WHITE);
        gbc.ipady =0;
        gbc.ipady = 0;
        gbc.fill = gbc.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        
        upcText = new JTextField(product.getUpc());
        upcText.setEditable(false);
        upcText.setFont(fonts.getCustomFont(CustomFonts.ROBOTO, 16));
        panel.add(upcText, gbc);
        
        gbc.weighty = 0;
        gbc.fill = gbc.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        JTextField item = new JTextField(product.getName());
        item.setEditable(false);
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
        manuf.setEditable(false);
        manuf.setFont(fonts.getCustomFont(CustomFonts.ROBOTO, 16));
        panel.add(manuf, gbc);
        
        gbc.weighty = 0;
        gbc.fill = gbc.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        JTextField recycleInfo = new JTextField("Recyclable");
        recycleInfo.setEditable(false);
        recycleInfo.setFont(fonts.getCustomFont(CustomFonts.ROBOTO, 16));
        panel.add(recycleInfo, gbc);
        UPC = product.getUpc();
        
        //Changed by Kir
        int recycle = product.getRType();
        switch(recycle)
        {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                recycleInfo.setText("Plasitc");
                break;
            case 8:
                recycleInfo.setText("Metal");
                break;
            case 9:
               recycleInfo.setText("Cardboard/Paper");
                break;
            case 10:
                recycleInfo.setText("Glass");
                break;
            case 11:
                recycleInfo.setText("Batteries/Bulbs");
                break;
            case 12:
                recycleInfo.setText("Electronics");
                break;
            default:
                recycleInfo.setText("Not Recyclable");
                break;
        }
        gbc.weighty = 1.0;
        gbc.ipady = 0;
        gbc.fill = HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        JButton button = new JButton();
        
        button.setIcon(new ImageIcon(
                fonts.getTextFonted(fonts.getCustomFont(CustomFonts.ROBOTO, 16), "Edit", Color.BLACK)));
        button.addActionListener(edit());
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setContentAreaFilled(true); 
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
        panel.add(okButton, gbc);
        return panel;
    }
   
    private ActionListener edit(){    
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.switchViews(Input.EDIT);
                
            }
        };
        return al;
    }
    
    private ActionListener done(){
              
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.switchViews(Input.HOME);
            }
        };
        return al;
    }
    
    private ActionListener time(){
        ActionListener al = (ActionEvent e) -> {
            repaint();
        };
        return al;
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(input.getWidth(), input.getHeight());
    }
}
