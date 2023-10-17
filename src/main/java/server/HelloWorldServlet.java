package server;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;
import backend.Customer;

public class HelloWorldServlet extends HttpServlet {
    private String mymsg;
    public void init() throws ServletException {
       mymsg = "Http Servlet Demo";
    }
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>" + mymsg + "</h1>");
        out.println("<p>" + "Ahoy ahoy!" + "</p>");
    }
}
