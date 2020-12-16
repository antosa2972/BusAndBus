package Controller;
import UserInterface.FramesService;

import javax.swing.*;

public class Controller {
    public static void control(){
        JFrame authorizationFrame=FramesService.createFrame(FramesService.FramesType.AUTHORIZATION,null);
    }
}
