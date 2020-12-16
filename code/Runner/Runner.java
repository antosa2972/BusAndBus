package Runner;
import Controller.Controller;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.text.ParseException;

public class Runner {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, ParseException {
        FlatLightLaf.install();
        UIManager.setLookAndFeel(new FlatIntelliJLaf());
        Controller.control();
    }
}
