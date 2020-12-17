package UserInterface;

import Clients.Client;
import Clients.ClientsService;
import Controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ReviewsFrame extends JFrame {
    private JTextArea writeHere= new JTextArea();
    private JButton send = new JButton("ОТПРАВИТЬ");
    private Client client = new Client();
    public ReviewsFrame(String name,Client client){
        super(name);
        this.client=client;
        this.setLayout(null);
        this.setVisible(true);
        this.setResizable(false);
        Controller.setImage(this);
        this.setSize(480,240);
        this.setLocation(500,300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.add(writeHere);
        writeHere.setBounds(10,10,440,150);
        writeHere.setBackground(Color.LIGHT_GRAY);
        this.add(send);
        send.setBounds(10,170,130,20);
        send.setBackground(new Color(255));
        send.setForeground(Color.white);
        writeHere.setLineWrap(true);
        writeHere.setWrapStyleWord(true);
        writeHere.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char input=e.getKeyChar();
                if(input>'0'&&input<'9'||writeHere.getText().length()>=300){
                    e.consume();
                }
            }
        });
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientsService.leaveReview(writeHere.getText());
            }
        });
    }
}
