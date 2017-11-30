package template.engine;

import java.text.SimpleDateFormat;
import java.util.List;

import models.Car;

public final class TemplateHelper {

    private TemplateHelper() {};

    public static String getIndexPage(List<Car> cars) {
        return getAddCarForm() + getCarsListHtml(cars);
    }

    private static String getAddCarForm() {
        return "<html>"
                + "<head>"
                + "<title>"
                + "Cars"
                + "</title>"
                + "</head>"
                + "<body>"
                + "<form action=\"CarServlet\" method=\"POST\">"
                + "Model: <input type=\"text\" name=\"model\">"
                + "<br />"
                + "SubModel: <input type=\"text\" name=\"subModel\" />"
                + "<br />"
                + "Price: <input type=\"number\" name = \"price\" required /> $"
                + "<br/>"
                + "Year: <input type=\"date\" name=\"date\" required />"
                + "<br />"
                + "<input id=\"submitButton\" type=\"submit\" value=\"Add car\" />"
                + "</form>";
    }

    private static String getCarsListHtml(List<Car> cars) {
        String carsListHtml =
            "<details>"
            + "<summary>Added cars</summary>"
            + "<table>"
            + "<tr>"
            + "<th>Model</th>"
            + "<th>SubModel</th>"
            + "<th>Price</th>"
            + "<th>Year</th>"
            + "</tr>";

        for (Car car: cars) {
            carsListHtml = carsListHtml.concat(addCarRow(car));
        }

        carsListHtml +=
            "</table>"
            + "</details>"
            + "</body>"
            + "</html>";

        return carsListHtml;
    }

    private static String addCarRow(Car car) {
        return
            "<tr>"
            + "<td>"
            + car.getModel()
            + "</td>"
            + "<td>"
            + car.getSubModel()
            + "</td>"
            + "<td>"
            + car.getYear()
            + "</td>"
            + "<td>"
            + new SimpleDateFormat("MM-dd-yyyy").format(car.getYear())
            + "</td>"
            + "</tr>";
    }
}
