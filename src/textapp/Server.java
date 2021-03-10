/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textapp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.ServerSocket;
import java.net.*;

/**
 *
 * @author samarth
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
public class Server extends JFrame implements ActionListener{
    
    JPanel panel1;
    JTextField textfield1;
    JButton button1;
    static JTextArea textarea1;
    
    static ServerSocket server1;
    static Socket socket1;
    static DataInputStream datain;
    static DataOutputStream dataout;
    
    Server() {
        
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(new Color(212, 69, 0));
        panel1.setBounds(0,0,350,60);
        add(panel1);
        
        /* Profile Picture */
        ImageIcon image1 = new ImageIcon(ClassLoader.getSystemResource("textapp/icons/profile2.jpg"));
        Image image2 = image1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon image3 = new ImageIcon(image2);
        JLabel label1 = new JLabel(image3);
        label1.setBounds(10, 10, 40, 40);
        panel1.add(label1);
        
        /* Exit Icon */
        ImageIcon image4 = new ImageIcon(ClassLoader.getSystemResource("textapp/icons/exit.png"));
        Image image5 = image4.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon image6 = new ImageIcon(image5);
        JLabel label3 = new JLabel(image6);
        label3.setForeground(Color.WHITE);
        label3.setBounds(310, 15, 30, 30);
        panel1.add(label3);
        
        /* Close Button */
        
        label3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });
        /* Name */
        JLabel label2 = new JLabel("Samarth Shah");
        label2.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        label2.setForeground(Color.WHITE);
        label2.setBounds(55, 13, 200, 35);
        panel1.add(label2);
        
        /* Text Field */
        textfield1 = new JTextField();
        textfield1.setBounds(0,511,269,40);
        textfield1.setFont(new Font("SAN_SERIF",Font.PLAIN, 14));
        add(textfield1);
        
        /* Button */
        button1 = new JButton("Send");
        button1.setBounds(268, 509, 81, 40);
        button1.setBackground(new Color(212, 69, 0));
        button1.setForeground(Color.WHITE);
        button1.setFont(new Font("SAN_SERIF",Font.PLAIN, 14));
        button1.addActionListener(this);
        add(button1);
        
        /* Text Area */
        textarea1 = new JTextArea();
        textarea1.setBounds(1, 60, 347, 447);
        textarea1.setFont(new Font("SAN_SERIF",Font.PLAIN, 16));
        textarea1.setEditable(false);
        textarea1.setLineWrap(true);
        textarea1.setWrapStyleWord(true);
        /*textarea1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);*/
        add(textarea1);
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(350, 550);
        setUndecorated(true);
        setVisible(true);
        setLocation(600, 300);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String input = textfield1.getText();
            textarea1.setText(textarea1.getText() + " \n\t\t " + input);
            dataout.writeUTF(input);
            textfield1.setText("");
        } catch(Exception excep) {}
    }
 
    
    public static void main(String[] args) {
        new Server().setVisible(true);
        
        
        try {
            server1 = new ServerSocket(6501);
            socket1 = server1.accept();
            datain = new DataInputStream(socket1.getInputStream());
            dataout = new DataOutputStream(socket1.getOutputStream());
            
            String msginput = "";
        
            msginput = datain.readUTF();
            textarea1.setText(textarea1.getText() + " \n " + msginput);
            
            server1.close();
            socket1.close();
            
            
        } catch (Exception excep) {
            
        }
    }
}
