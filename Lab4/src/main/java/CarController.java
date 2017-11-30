import database.MySqlDatabaseController;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import models.Car;

class CarController {

    private MySqlDatabaseController mySqlDatabase;

    CarController() {
        initDatabase();
    }

    private void initDatabase() {
        try {
            mySqlDatabase = new MySqlDatabaseController();
        } catch (Exception e) {
            Logger.getGlobal().log(Level.ALL, "Can not init database: " + e.getMessage());
        }
    }

    void addCar(HttpServletRequest request) {
        try {
            final DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            Car car = new Car();
            car.setModel(request.getParameter("model"));
            car.setSubModel(request.getParameter("subModel"));
            car.setPrice(Integer.parseInt(request.getParameter("price")));
            car.setYear(format.parse(request.getParameter("date")));

            mySqlDatabase.addCar(car);
        } catch (ParseException e) {
            Logger.getGlobal().log(Level.ALL, "Invalid data: " + e.getMessage());
        }
    }

    List<Car> getCars() {
        try {
            return mySqlDatabase.getCarsList();
        } catch (SQLException e) {
            Logger.getGlobal()
                    .log(Level.ALL, "Can not get car list from database: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
