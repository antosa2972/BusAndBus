package UserInterface;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Clients.Client;
import Controller.Authorization;
import Controller.Controller;

public class AuthorizationFrame extends JFrame {
    private final JTextField loginField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JLabel error = new JLabel();
    private final JCheckBox checkBox = new JCheckBox("Водитель");
    private Client client = new Client();
   public AuthorizationFrame(String winName){
       super(winName);
       Controller.setImage(this);
       this.setLayout(null);
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
       this.setResizable(false);
       this.setVisible(true);
       this.add(checkBox);
       this.setSize(300,180);
       this.setLocation(550,350);
       JButton enter = new JButton("Войти");
       enter.setBackground(Color.blue);
       JButton register = new JButton("Регистрация");
       register.setBackground(new Color(255));
       enter.setForeground(Color.white);
       register.setForeground(Color.white);
       JLabel login = new JLabel("Логин");
       this.add(login);
       login.setBounds(10,10,50,20);
       this.add(loginField);
       loginField.setBounds(65,12,200,20);
       JLabel password = new JLabel("Пароль");
       this.add(password);
       password.setBounds(10,40,50,20);
       this.add(passwordField);
       passwordField.setBounds(65,42,200,20);
       this.add(enter);
       enter.setBounds(10,80,120,20);
       this.add(register);
       this.add(error);
       loginField.addKeyListener(new KeyAdapter() {
           @Override
           public void keyTyped(KeyEvent e) {
               if(loginField.getText().length()>=15){
                   e.consume();
               }
           }
       });
       passwordField.addKeyListener(new KeyAdapter() {
           @Override
           public void keyTyped(KeyEvent e) {
               if(passwordField.getText().length()>=20){
                   e.consume();
               }
           }
       });
       error.setBounds(10,110,300,20);
       checkBox.setBounds(190,110,300,20);
       error.setText("Добро пожаловать в Bus&Bus!");
       register.setBounds(150,80,120,20);
       enter.setFocusPainted(false);
       register.setFocusPainted(false);
       enter.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               int flg=1;
               if(checkBox.isSelected()) {
                   flg=0;
               }
               switch (Authorization.authenticate(loginField.getText(),passwordField.getText(),flg)){
                   case 0:
                       dispose();
                       client.setUsername(loginField.getText());
                       JFrame mainFrame= FramesService.createFrame(FramesService.FramesType.MAIN,client);
                       return;
                   case 1:
                       error.setText("Пустое поле");
                       break;
                   case 2: //case 0xFFFFFFFF:
                       error.setText("Неправильный логин или пароль");
                       break;
                   case 4:
                       dispose();
                       JFrame driverFrame = FramesService.createFrame(FramesService.FramesType.DRIVERFRAME,null);
               }
           }
       });
       register.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               JFrame registrationWindow = FramesService.createFrame(FramesService.FramesType.REGISTRATION,client);
           }
       });
   }
}
