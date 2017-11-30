import Database.MySqlDatabaseController;
import Models.Car;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

class CarController {

    private MySqlDatabaseController mySqlDatabase;

    CarController() {
        InitDatabase();
    }

    private void InitDatabase() {
        try {
            mySqlDatabase = new MySqlDatabaseController();
        } catch (Exception e) {
            Logger.getGlobal().log( Level.ALL, "Can not init database: " + e.getMessage() );
        }
    }

    void AddCar( HttpServletRequest request ) {
        try {
            DateFormat format = new SimpleDateFormat( "yyyy-MM-dd", Locale.ENGLISH );

            Car car = new Car();
            car.Model = request.getParameter( "model" );
            car.SubModel = request.getParameter( "subModel" );
            car.Price = Integer.parseInt( request.getParameter( "price" ) );
            car.Year = format.parse(request.getParameter("date"));

            mySqlDatabase.AddCar( car );
        } catch ( ParseException e ) {
            Logger.getGlobal().log( Level.ALL, "Invalid data: " + e.getMessage() );
        }
    }

    ArrayList<Car> GetCars() {
        try {
            return mySqlDatabase.GetCarsList();
        } catch (SQLException e) {
            Logger.getGlobal().log( Level.ALL, "Can not get car list from database: " + e.getMessage() );
            return new ArrayList<>();
        }
    }
}
