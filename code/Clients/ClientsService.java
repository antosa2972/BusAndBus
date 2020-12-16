package Clients;
import DataBaseWork.DB_Orders_Work;
import DataBaseWork.DB_Registration_Work;
import DataBaseWork.DB_Reviews_Work;

import java.util.List;
public class ClientsService {
    private static String[] routes={"Минск-Гродно","Минск-Могилёв","Минск-Молодечно","Минск-Щучин",
            "Минск-Белыничи","Минск-Логойск"};
    private static int[] prices={10,11,12,13,14,15};
    private static Client client = new Client();
    public static int logIn(String log,String pas){
        if(log.equals("")||pas.equals("")){
            return 1;
        }
        DB_Registration_Work.establishConnection();
        if(DB_Registration_Work.checkLogPas(log, pas)){
            client.setUsername(log);
        }else return 2;
        DB_Registration_Work.closeConnection();
        return 0;
    }
    public static int register(String log,String pas,String confPas){
        String login,password;
        if(log.equals("")||pas.equals(""))
            return 1;
        if(!(pas.equals(confPas)))
            return 2;
        boolean check=true;
        DB_Registration_Work.establishConnection();
        List<String> userNames= DB_Registration_Work.getLogins();
        for (String tmp:userNames) {
            if(tmp.equals(log)){
                return 3;
            }
        }
        DB_Registration_Work.writeToBaseUsers(log, pas);
        DB_Registration_Work.closeConnection();
        return 0;
    }
    public static int makeOrder(String from,String whereToGo,String date,int numOfPassengers,String log){
        if(from.isEmpty()||whereToGo.isEmpty()){
            return 2;
        }
        String routeTemp= from+"-"+whereToGo;
        boolean check=false;
        int price_ind=0;
        for (String route : routes) {
            price_ind++;
            if (routeTemp.equals(route)) {
                check = true;
                break;
            }
        }
        if(!check) return 3;
        int uniqNumber=0;
        for(int i=0;i<routeTemp.length();i++){
            uniqNumber+=routeTemp.charAt(i);
        }
        for(int i=0;i<date.length();i++){
            uniqNumber+=date.charAt(i);
        }
        DB_Orders_Work.establishConnection();
        if(DB_Orders_Work.checkFreeSeats(uniqNumber,numOfPassengers)==3)
            DB_Orders_Work.writeToOrderBase(uniqNumber,log,numOfPassengers,routeTemp,prices[price_ind-1],date);
        else return DB_Orders_Work.checkFreeSeats(uniqNumber,numOfPassengers);
        DB_Orders_Work.closeConnection();
        return 1;
    }
    public static int cancelOrder(int ind){
        if(client.getUsername().isEmpty())
            return 1;
        DB_Orders_Work.establishConnection();
        DB_Orders_Work.deleteOrderFromDB(client.getUsername(), ind);
        DB_Orders_Work.closeConnection();
        return 0;
    }
    public static void leaveReview(String review){
        DB_Reviews_Work.establishConnection();
        DB_Reviews_Work.writeToReviewBase(client.getUsername(),review);
        DB_Reviews_Work.closeConnection();
    }
}
