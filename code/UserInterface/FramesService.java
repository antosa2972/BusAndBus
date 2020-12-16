package UserInterface;

import Clients.Client;

import javax.swing.*;

public class FramesService {
    public static JFrame createFrame(FramesType type, Client client){
        switch (type){
            case MAIN:
                return createMainFrame(client);
            case REVIEWS:
                return createReviewsFrame(client);
            case ORDERINFO:
                return createOrderInfoFrame(client);
            case CLIENTSEARCH:
                return createOrderInfoFrame(client);
            case REGISTRATION:
                return createRegiistrationFrame();
            case AUTHORIZATION:
                return createAuthorizationFrame();
            case SHOWREVIEWS:
                return createShowReviews();
            default:
                throw new IllegalArgumentException("Wrong frame"+type);
        }
    }
    public enum FramesType{
        AUTHORIZATION,
        ORDERINFO,
        REGISTRATION,
        REVIEWS,
        MAIN,
        CLIENTSEARCH,
        SHOWREVIEWS
    }
    private static JFrame createAuthorizationFrame(){ return new AuthorizationFrame("Вход"); }
    private static JFrame createRegiistrationFrame(){
        return new RegistrationFrame("Регистрация");
    }
    private static JFrame createOrderInfoFrame(Client client){
        return new OrderInfoFrame("Информация о заказах",client);
    }
    private static JFrame createReviewsFrame(Client client){
        return new ReviewsFrame("Информация о заказах",client);
    }
    private static JFrame createMainFrame(Client client){
        return new MainFrame("Bus&Bus",client);
    }
    private static JFrame createShowReviews(){
        return new ShowReviewsFrame("Отзывы");
    }
}
