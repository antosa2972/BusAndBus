package UserInterface;
import Controller.Controller;
import DataBaseWork.DB_Orders_Work;
import Drivers.DriversService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientSearchFrame extends JFrame {
    private JPanel jPanel = new JPanel();
    private ArrayList<JTextField> arrayList = new ArrayList<>();
    private ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
    private JButton beginRide= new JButton("Начать поездку");
    String route;
    public ClientSearchFrame(String winName,String route){
        super(winName);
        this.route=route;
        this.setLayout(null);
        this.setVisible(true);
        this.setResizable(true);
        Controller.setImage(this);
        this.setSize(220,300);
        this.setLocation(500,300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JScrollPane jScrollPane = new JScrollPane(jPanel);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setBounds(0,0,300,200);
        this.add(jScrollPane);
        this.add(beginRide);
        beginRide.setBounds(10,220,180,30);
        beginRide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DriversService.beginRoute(route);
            }
        });

    }
    public void showClients(ArrayList<String> clients){
        jPanel.setLayout(new GridLayout(clients.size(),2));
        for(int i=0;i<clients.size();i++){
            arrayList.add(new JTextField(clients.get(i)));
            checkBoxes.add(new JCheckBox());
            arrayList.get(i).setBorder(null);
            jPanel.add(arrayList.get(i));
            jPanel.add(checkBoxes.get(i));
            arrayList.get(i).setEditable(false);
        }
    }
}
