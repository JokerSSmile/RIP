import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="Main Controller",urlPatterns={"/index"})
public class MainController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response )
            throws ServletException, IOException
    {
        request.getRequestDispatcher("/index.jsp").forward( request, response );
    }
}