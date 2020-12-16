package DataBaseWork;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public  class DB_Registration_Work {
    private static Connection connection;
    public DB_Registration_Work(){
        try{
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void establishConnection() {
        try {
            String URL="jdbc:mysql://localhost:3306/workbase?useSSL=false&serverTimezone=UTC";
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
    public static void writeToBaseUsers(String login, String password){
        String insert="INSERT INTO workbase.user(username, password) VALUES(?,?)";
        try {
            PreparedStatement preparedStatement;
            preparedStatement=connection.prepareStatement(insert);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,password);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static List<String> getLogins(){
        List<String> listOfLogins= new ArrayList<String>();
        String get="SELECT username from workbase.user";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet= statement.executeQuery(get);
            while(resultSet.next()){
                listOfLogins.add(resultSet.getString(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listOfLogins;
    }
    public static boolean checkLogPas(String login,String password){
        String get="SELECT username,password from workbase.user";
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(get);
            while(resultSet.next()){
                if(login.equals(resultSet.getString(1))&&password.equals(resultSet.getString(2)))
                    return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
