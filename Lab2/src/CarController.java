import Models.Car;

import java.util.Date;
import java.util.Locale;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( name="Car Controller",urlPatterns={"/"} )
public class CarController extends HttpServlet {

    private ArrayList<Car> cars;

    public CarController()
    {
        cars = new ArrayList<>();
    }

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        out.print( getIndexHtml() );
        out.flush();
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        String dateStr = request.getParameter( "date" );
        DateFormat format = new SimpleDateFormat( "yyyy-MM-dd", Locale.ENGLISH );

        Car car = new Car();
        car.Model = request.getParameter( "model" );
        car.SubModel = request.getParameter( "subModel" );
        car.Price = Integer.parseInt( request.getParameter( "price" ) );
        try {
            car.Year = format.parse( dateStr );
        } catch ( ParseException ignored ) {
            car.Year = new Date( 2017, 10, 13);
        }

        cars.add( car );

        PrintWriter out = response.getWriter();
        out.print( getIndexHtml() );
        out.flush();
    }

    private String getIndexHtml()
    {
        String addCarHtml = "<html>" +
                "<head>" +
                "<title>" +
                "Cars" +
                "</title>" +
                "</head>" +
                "<body>" +
                "<form action=\"CarController\" method=\"POST\">" +
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
            carsListHtml +=
                    "<tr>" +
                    "<td>" + car.Model + "</td>" +
                    "<td>" + car.SubModel + "</td>" +
                    "<td>" + car.Price + "$</td>" +
                    "<td>" + new SimpleDateFormat("MM-dd-yyyy").format(car.Year ) + "</td>" +
                    "</tr>";
        }

        carsListHtml +=
                "</table>" +
                "</details>" +
                "</body>" +
                "</html>";

        return addCarHtml + carsListHtml;
    }
}
