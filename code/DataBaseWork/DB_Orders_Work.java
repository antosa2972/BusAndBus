package DataBaseWork;
import java.sql.*;
import java.util.ArrayList;
public class DB_Orders_Work {
    private static Connection connection;
    public DB_Orders_Work(){
        try{
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void establishConnection() {
        try {
            String URL="jdbc:mysql://localhost:3306/orders?useSSL=false&serverTimezone=UTC";
            String login="antosa2972",
                    password="55Ssteam29";
            connection = DriverManager.getConnection(URL, login, password);
            if(!connection.isClosed()){
                return;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void closeConnection(){
        try {
            if(!connection.isClosed())
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static int checkFreeSeats(int uniqueNum,int seats){
        String sql="select num_of_seats from orders.order_list where num_of_route='"+uniqueNum+"'";
        int orderedSeats=0;
        try {
            Statement statement =connection.createStatement();
            ResultSet resultSet =statement.executeQuery(sql);
            while (resultSet.next()){
                orderedSeats+=resultSet.getInt(1);
            }
            int MAX_PASSENGERS_NUM = 20;
            if(seats>(MAX_PASSENGERS_NUM -orderedSeats)){
                return MAX_PASSENGERS_NUM -orderedSeats;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 3;
    }
    public static void writeToOrderBase(int uniqueNum,String login,int numOfSeats,String info,int price,String date){
        String sql="insert into orders.order_list(num_of_route, name_of_client, num_of_seats,info,price,date) values (?,?,?,?,?,?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,uniqueNum);
            preparedStatement.setString(2,login);
            preparedStatement.setInt(3,numOfSeats);
            preparedStatement.setString(4,info);
            preparedStatement.setInt(5,price);
            preparedStatement.setString(6,date);
            preparedStatement.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static ArrayList<String> getRoutesByUsername(String userName){
        DB_Orders_Work.establishConnection();
        ArrayList<String> ord = new ArrayList<String>();
        String sql="select info,date,price from orders.order_list where name_of_client='"+userName+"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet= statement.executeQuery(sql);
            while(resultSet.next()){
                ord.add((resultSet.getString(1))+(" ")+(resultSet.getString(2))
                        +("       ")+(resultSet.getInt(3))+(" BYR")+("\n"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DB_Orders_Work.closeConnection();
        return ord;
    }
    public static void deleteOrderFromDB(String userName, int num){
        int whatNumToDelete=0;
        String sql= "select orders.order_list.num_of_route" +
                " from orders.order_list where order_list.name_of_client='"+userName+"'";
        int i=1;
        while(i<num) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                whatNumToDelete=resultSet.getInt(1);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            i++;
        }
        if(whatNumToDelete!=0) {
            String sql2 = "delete from orders.order_list where order_list.name_of_client='" + userName + "'"
                    + "and order_list.num_of_route='" + whatNumToDelete + "'";
            try {
                Statement statement = connection.createStatement();
                int result = statement.executeUpdate(sql2);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
