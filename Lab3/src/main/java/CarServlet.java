import Models.Car;
import TemplateEngine.TemplateHelper;

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
public class CarServlet extends HttpServlet {

    private CarController carController;

    public CarServlet()
    {
        carController = new CarController();
    }

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        out.print( TemplateHelper.GetIndexPage( carController.GetCars() ) );
        out.flush();
    }

    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        carController.AddCar( request );

        PrintWriter out = response.getWriter();
        out.print( TemplateHelper.GetIndexPage( carController.GetCars() ) );
        out.flush();
    }
}
