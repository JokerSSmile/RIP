import Models.Car;

import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="Car Controller",urlPatterns={"/car"})
public class CarController extends HttpServlet {

    private ArrayList<Car> cars;

    public CarController()
    {
        cars = new ArrayList<>();
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response )
            throws ServletException, IOException
    {
        request.setAttribute("cars", cars);
        getServletConfig().getServletContext().getRequestDispatcher("/cars.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response )
            throws ServletException, IOException
    {
        Car car = new Car();
        car.Model = request.getParameter("model" );
        car.SubModel = request.getParameter("sub-model" );
        car.Year = Integer.parseInt( request.getParameter("year" ) );
        cars.add( car );

        response.sendRedirect("/index.jsp" );
    }
}
