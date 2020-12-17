package Controller;
import UserInterface.FramesService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Controller {
    public static void control(){
        JFrame authorizationFrame=FramesService.createFrame(FramesService.FramesType.AUTHORIZATION,null);
    }
    public static void setImage(JFrame frame){
        try {
            frame.setIconImage(ImageIO.read(new File("E://tritpo//BusAndBus/src/main/java/UserInterface/img1.png")));
        }catch (IOException e){

        }
    }
}
