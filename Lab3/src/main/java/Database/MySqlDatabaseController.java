package Database;

import Models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySqlDatabaseController {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "cars";

    private static final String SCHEMA = "cars";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection dbConnection;

    public MySqlDatabaseController() throws Exception {
        Properties properties = new Properties();
        properties.put("user", USERNAME);
        properties.put("password", PASSWORD);
        properties.put("schema", SCHEMA);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            DriverManager.registerDriver( new com.mysql.jdbc.Driver () );
            dbConnection = DriverManager.getConnection(DATABASE_URL + DB_NAME, properties );
        } catch (Exception e) {
            Logger.getGlobal().log( Level.ALL, "Can not connect to database: " + e.getMessage() );
        }
    }

    public void AddCar(Car car) {
        try (PreparedStatement stmt = dbConnection.
                prepareStatement("INSERT INTO Cars (Model, SubModel, Price, Year) VALUES (?, ?, ?, ?)")) {

            stmt.setString( 1, car.Model );
            stmt.setString( 2, car.SubModel );
            stmt.setInt( 3, car.Price );
            stmt.setDate( 4, new java.sql.Date(car.Year.getTime()) );
            stmt.addBatch();
            stmt.executeBatch();
        } catch(Exception e) {
            Logger.getGlobal().log( Level.ALL, "Can not add car to database: " + e.getMessage() );
        }
    }

    public ArrayList<Car> GetCarsList() throws SQLException {
        try (PreparedStatement pst = dbConnection.prepareStatement("SELECT * FROM Cars")) {
            try (ResultSet rs = pst.executeQuery()){
                ArrayList<Car> cars = new ArrayList<>();

                while(rs.next()) {
                    Car car = new Car();
                    car.Model = rs.getString( "Model" );
                    car.SubModel = rs.getString( "SubModel" );
                    car.Price = rs.getInt( "Price" );
                    car.Year = rs.getDate( "Year" );

                    cars.add( car );
                }

                return cars;
            }
        }
    }
}
