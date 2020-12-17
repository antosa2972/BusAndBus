package UserInterface;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import Clients.ClientsService;
import Controller.Controller;

public class RegistrationFrame extends JFrame {
    private final JTextField loginField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JPasswordField passwordField2 = new JPasswordField();
    private final JLabel status = new JLabel();
    public RegistrationFrame(String winName) {
        super(winName);
        Controller.setImage(this);
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setSize(400, 170);
        this.setLocation(550, 350);
        JLabel login = new JLabel("Логин");
        this.add(login);
        JButton register = new JButton("Регистрация");
        register.setForeground(Color.WHITE);
        register.setBackground(Color.BLUE);
        login.setBounds(10, 10, 50, 20);
        this.add(loginField);
        loginField.setBounds(165, 12, 200, 20);
        JLabel password = new JLabel("Пароль");
        this.add(password);
        password.setBounds(10, 40, 50, 20);
        this.add(passwordField);
        passwordField.setBounds(165, 42, 200, 20);
        this.add(passwordField2);
        passwordField2.setBounds(165, 72, 200, 20);
        JLabel password2 = new JLabel("Повторите пароль");
        this.add(password2);
        password2.setBounds(10, 72, 200, 20);
        this.add(register);
        register.setBounds(10, 100, 120, 20);
        this.add(status);
        status.setBounds(132,100,300,20);
        register.setFocusPainted(false);
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
                if(passwordField.getText().length()>=15){
                    e.consume();
                }
            }
        });
        passwordField2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(passwordField2.getText().length()>=15){
                    e.consume();
                }
            }
        });
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (ClientsService.register(loginField.getText(),passwordField.getText(),passwordField2.getText())){
                    case 0:
                        JOptionPane.showMessageDialog(RegistrationFrame.this,"Вы успешно зарегистрировались","Успешная регистрация",JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        return;
                    case 1:
                        status.setText("Вы ввели пустое поле");
                        break;
                    case 2:
                        status.setText("Пароли не совпадают");
                        break;
                    case 3:
                        status.setText("Такой пользователь уже существует");
                        break;
                }
            }
        });
    }
}
