package UserInterface;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ShowReviewsFrame extends JFrame {
    private ArrayList<JTextArea> jj = new ArrayList<>();
    private JPanel jPanel = new JPanel();
    public ShowReviewsFrame(String winName){
        super(winName);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        JScrollPane jScrollPane = new JScrollPane(jPanel);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(jScrollPane);
        jScrollPane.setBounds(0,0,600,340);
        try {
            this.setIconImage(ImageIO.read(new File("E://tritpo//BusAndBus/src/main/java/UserInterface/img1.png")));
        }catch (IOException e){

        }
        this.setSize(600,340);
        this.setLocation(500,300);
    }
    public void showReviews(ArrayList<String> reviews){
        jPanel.setLayout(new GridLayout(reviews.size(),1));

        for(int i=0;i<reviews.size();i++){
            jj.add(new JTextArea(reviews.get(i)));
            jPanel.add(jj.get(i));
            jj.get(i).setLineWrap(true);
            jj.get(i).setWrapStyleWord(true);
            jj.get(i).setEditable(false);
        }
    }
}
