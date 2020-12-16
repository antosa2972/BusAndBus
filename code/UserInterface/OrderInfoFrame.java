package UserInterface;

import Clients.Client;
import Clients.ClientsService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public class OrderInfoFrame extends JFrame {
    private final JPanel jPanel = new JPanel();
    private final ArrayList<JButton> jButtonArrayList= new ArrayList<JButton>();
    private final ArrayList<JLabel> jLabelArrayList = new ArrayList<JLabel>();
    private String username;
    private Client client= new Client();
    private int ind=1;
    public OrderInfoFrame(String winName, final Client client){
        super(winName);
        try {
            this.setIconImage(ImageIO.read(new File("E://tritpo//BusAndBus/src/main/java/UserInterface/img1.png")));
        }catch (IOException e){

        }
        this.client=client;
        this.setVisible(true);
        this.setLayout(null);
        //this.username=winName;
        this.setSize(500,200);
        this.setLocation(400,500);
        JButton leaveFeedBack = new JButton("Оставить отзыв");
        this.add(leaveFeedBack);
        leaveFeedBack.setBounds(10,130,200,20);
        JScrollPane jScrollPane = new JScrollPane(jPanel);
        this.add(jScrollPane);
        jScrollPane.setBounds(10,10,465,120);
        jScrollPane.createVerticalScrollBar();
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        leaveFeedBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame reviewsFrame= FramesService.createFrame(FramesService.FramesType.REVIEWS,client);
            }
        });
    }
    public void setJLabels(ArrayList<String> orders){
        jPanel.setLayout(new GridLayout(orders.size(),2));
        int j=10;
        int i=0;
        for(String x:orders){
            jLabelArrayList.add(new JLabel(x));
            jPanel.add(jLabelArrayList.get(i));
            jButtonArrayList.add(new JButton("Отменить"));
            jPanel.add(jButtonArrayList.get(i));
            j+=30;
            i++;
        }

    }
    public void setListeners(){
        for(final JButton but:jButtonArrayList) {
            but.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ClientsService.cancelOrder(ind);
                    jPanel.remove(but);
                    jPanel.remove(jLabelArrayList.get(jButtonArrayList.indexOf(but)));
                    repaint();
                }
            });
            ind++;
        }
    }

}
