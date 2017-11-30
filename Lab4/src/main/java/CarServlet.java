import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import template.engine.TemplateHelper;

@WebServlet(name = "Car Controller",urlPatterns = {"/"})
public class CarServlet extends HttpServlet {

    private CarController carController;

    public CarServlet()
    {
        carController = new CarController();
    }

    @Override
    public final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.print(TemplateHelper.getIndexPage(carController.getCars()));
        out.flush();
    }

    @Override
    public final void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        carController.addCar(request);

        PrintWriter out = response.getWriter();
        out.print(TemplateHelper.getIndexPage(carController.getCars()));
        out.flush();
    }
}
