package DataBaseWork;

import java.sql.*;
import java.util.ArrayList;

public class DB_Reviews_Work {
    private static Connection connection;

    public DB_Reviews_Work() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void establishConnection() {
        try {
            String URL = "jdbc:mysql://localhost:3306/reviews?useSSL=false&serverTimezone=UTC";
            String login = "antosa2972",
                    password = "55Ssteam29";
            connection = DriverManager.getConnection(URL, login, password);
            if (!connection.isClosed()) {
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            if (!connection.isClosed())
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void writeToReviewBase(String username, String review) {
        String insert="INSERT INTO reviews.review(text,username) VALUES(?,?)";
        try {
            PreparedStatement preparedStatement;
            preparedStatement=connection.prepareStatement(insert);
            preparedStatement.setString(1,review);
            preparedStatement.setString(2,username);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static ArrayList<String> getAllReviews(){
        DB_Reviews_Work.establishConnection();
        ArrayList<String> temp = new ArrayList<>();
        String sql="select text,username from reviews.review";
        try {
            Statement statement =connection.createStatement();
            ResultSet resultSet =statement.executeQuery(sql);
            while (resultSet.next()){
                temp.add(resultSet.getString(1)+"\nОт:"+resultSet.getString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        DB_Reviews_Work.closeConnection();
        return temp;
    }
}
