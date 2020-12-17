package UserInterface;
import Controller.Controller;
import DataBaseWork.DB_Orders_Work;
import Drivers.DriversService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DriverFrame extends JFrame {
    JButton enter= new JButton("ОК");
    JLabel jLabel = new JLabel("Введите свой маршрут:");
    JTextField jTextField = new JTextField();
    public DriverFrame(String winName){
        super(winName);
        this.setVisible(true);
        this.setResizable(false);
        Controller.setImage(this);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setSize(270,120);
        this.setLocation(500,400);
        this.add(jLabel);
        jLabel.setBounds(20,10,200,20);
        this.add(jTextField);
        jTextField.setBounds(20,35,200,20);
        this.add(enter);
        enter.setBounds(20,60,100,20);
        jTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(jTextField.getText().length()>=20){
                    e.consume();
                }
            }
        });
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(DriversService.findRoute(jTextField.getText())==0){
                    JOptionPane.showMessageDialog(DriverFrame.this,"Вы ввели пустое поле",
                            "Ошибка",JOptionPane.ERROR_MESSAGE);
                }else
                    dispose();
                    //ClientSearchFrame clientSearchFrame= (ClientSearchFrame) FramesService.createFrame(FramesService.FramesType.CLIENTSEARCH,null);
                    ClientSearchFrame clientSearchFrame=new ClientSearchFrame("Пассажиры",jTextField.getText());
                    DB_Orders_Work.establishConnection();
                    clientSearchFrame.showClients(DB_Orders_Work.findRoute(jTextField.getText()));
                    DB_Orders_Work.closeConnection();
            }
        });
    }
}
