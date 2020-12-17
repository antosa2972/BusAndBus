package Drivers;

import DataBaseWork.DB_Orders_Work;

public class DriversService {
    public static int findRoute(String route){
        if(route.isEmpty())
            return 0;
        DB_Orders_Work.establishConnection();
        DB_Orders_Work.findRoute(route);
        DB_Orders_Work.closeConnection();
        return 1;
    }
    public static void beginRoute(String route){
        DB_Orders_Work.establishConnection();
        DB_Orders_Work.deleteUsers(route);
        DB_Orders_Work.closeConnection();
    }
}
