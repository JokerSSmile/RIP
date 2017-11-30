package TemplateEngine;

import Models.Car;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TemplateHelper {

    public static String GetIndexPage(ArrayList<Car> cars) {
        return GetAddCarForm() + GetCarsListHtml( cars );
    }

    private static String GetAddCarForm() {
        return "<html>" +
            "<head>" +
            "<title>" +
            "Cars" +
            "</title>" +
            "</head>" +
            "<body>" +
            "<form action=\"CarServlet\" method=\"POST\">" +
            "Model: <input type=\"text\" name=\"model\">" +
            "<br />" +
            "SubModel: <input type=\"text\" name=\"subModel\" />" +
            "<br />" +
            "Price: <input type=\"number\" name = \"price\" required /> $" +
            "<br/>" +
            "Year: <input type=\"date\" name=\"date\" required />" +
            "<br />" +
            "<input id=\"submitButton\" type=\"submit\" value=\"Add car\" />" +
            "</form>";
    }

    private static String GetCarsListHtml( ArrayList<Car> cars ) {
        String carsListHtml =
            "<details>" +
            "<summary>Added cars</summary>" +
            "<table>" +
            "<tr>" +
            "<th>Model</th>" +
            "<th>SubModel</th>" +
            "<th>Price</th>" +
            "<th>Year</th>" +
            "</tr>";

        for ( Car car: cars )
        {
            carsListHtml += AddCarRow( car );
        }

        carsListHtml +=
            "</table>" +
            "</details>" +
            "</body>" +
            "</html>";

        return carsListHtml;
    }

    private static String AddCarRow( Car car ) {
        return "<tr>" +
            "<td>" + car.Model + "</td>" +
            "<td>" + car.SubModel + "</td>" +
            "<td>" + car.Price + "$</td>" +
            "<td>" + new SimpleDateFormat("MM-dd-yyyy").format(car.Year ) + "</td>" +
            "</tr>";
    }
}
