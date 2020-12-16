package UserInterface;
import Clients.Client;
import Clients.ClientsService;
import DataBaseWork.DB_Orders_Work;
import DataBaseWork.DB_Reviews_Work;
import TimeWork.TimeWork;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainFrame extends JFrame{
    String [] dates;
    private JPanel panelMain;
    private JLabel header;
    private JTextField textField1;
    private JTextField textField2;
    private JButton findButton;
    private JPanel secondPanel;
    private JSpinner spinner1;
    private JComboBox comboBox1;
    private JButton MinskGrodno;
    private JLabel from;
    private JLabel where;
    private JLabel date;
    private JLabel numOfPass;
    private JButton MinskMolodechno;
    private JButton MinskMogilev;
    private JButton MinskShuchin;
    private JButton MinskLogoysk;
    private JButton MinskBelynichi;
    private JButton showMyOrders;
    private JLabel imegeLabel;
    private JButton reviewsBtn;
    private Client client=new Client();
    public MainFrame(String name, final Client client) {
        super(name);
        dates= TimeWork.getDates();
        for (String s : dates) {
            comboBox1.addItem(s);
        }
        this.client=client;
        try {
            this.setIconImage(ImageIO.read(new File("E://tritpo//BusAndBus/src/main/java/UserInterface/img1.png")));
        }catch (IOException e){

        }
        spinner1.setModel(new SpinnerNumberModel(1,1,4,1));
        this.setContentPane(this.getPanelMain());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocation(300,200);
        this.setSize(1030,550);
        findButton.setFocusPainted(false);
        this.getRootPane().setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        findButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectOrder(from.getText(),where.getText());
            }
        });
                MinskGrodno.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        selectOrder("Минск","Гродно");
                    }
                });
                MinskMolodechno.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        selectOrder("Минск","Молодечно");
                    }
                });
                MinskMogilev.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        selectOrder("Минск","Могилёв");
                    }
                });
                MinskShuchin.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        selectOrder("Минск","Щучин");
                    }
                });
                MinskLogoysk.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        selectOrder("Минск","Логойск");
                    }
                });
                MinskBelynichi.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        selectOrder("Минск","Белыничи");
                    }
                });
                showMyOrders.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        OrderInfoFrame orderInfoFrame= (OrderInfoFrame) FramesService.createFrame(FramesService.FramesType.ORDERINFO,client);
                        ArrayList<String> orders = DB_Orders_Work.getRoutesByUsername(client.getUsername());
                        orderInfoFrame.setJLabels(orders);
                        orderInfoFrame.setListeners();
                    }
                });
                reviewsBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ShowReviewsFrame reviewsFrame= (ShowReviewsFrame) FramesService.createFrame(FramesService.FramesType.SHOWREVIEWS,null);
                        reviewsFrame.showReviews(DB_Reviews_Work.getAllReviews());
                    }
                });
            }
            private void selectOrder(String from,String where){
                int flg= ClientsService.makeOrder(from,where,
                        (String)comboBox1.getSelectedItem(),(Integer)spinner1.getValue(),client.getUsername());
                switch (flg){
                    case 1:
                        JOptionPane.showMessageDialog(MainFrame.this,"Заказ: "+from+"-"+
                                        where+" Дата: "+ (String)comboBox1.getSelectedItem()+ ". успешно создан!",
                                "Заказ",JOptionPane.INFORMATION_MESSAGE);
                        return;
                    case 2:
                        JOptionPane.showMessageDialog(MainFrame.this,"Вы ввели пустое поле",
                                "Ошибка",JOptionPane.ERROR_MESSAGE);
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(MainFrame.this,"Заданного маршрута не существует",
                                "Ошибка",JOptionPane.ERROR_MESSAGE);
                        break;
                    default:
                        JOptionPane.showMessageDialog(MainFrame.this,"Свободно "+flg+" мест",
                                "Ошибка",JOptionPane.ERROR_MESSAGE);
                }
            }
            public JPanel getPanelMain() {
                return panelMain;
            }
        }
