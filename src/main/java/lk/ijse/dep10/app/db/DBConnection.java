package lk.ijse.dep10.app.db;

import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static DBConnection dbConnection;
    private final Connection connection;
    private DBConnection(){
        try {
            File file = new File("application.properties");
            Properties properties = new Properties();
            FileReader fr = new FileReader(file);
            properties.load(fr);
            fr.close();

            String host = properties.getProperty("mysql.host", "localhost");
            String port = properties.getProperty("mysql.port", "3306");
            String database = properties.getProperty("mysql.database", "dep10_student_attendance");
            String username = properties.getProperty("mysql.username", "root");
            String password = properties.getProperty("mysql.password", "1234");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?createDatabaseIfNotExist=true&allowMultiQueries=true";
            connection = DriverManager.getConnection(url, username, password);
            //connection= DriverManager.getConnection(url,"root","1234");
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Configuration file doesn't exist.Try again").showAndWait();
            //System.exit(1);
            throw new RuntimeException();
        }catch (IOException e){
            //e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to read configurations.Try again").showAndWait();
            //System.exit(1);
            throw new RuntimeException();
        }catch (SQLException e){
            //e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to obtain database connection.Try again").showAndWait();
            //System.exit(1);
            throw new RuntimeException();
        }

    }
    public static DBConnection getInstance(){
        return dbConnection==null?dbConnection=new DBConnection():dbConnection;
    }
    public Connection getConnection(){

        return connection;
    }
}
