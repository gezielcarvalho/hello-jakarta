package info.gezielcarvalho;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class SubmitServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String name = request.getParameter("name");
    String agree = request.getParameter("agree");

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    out.println("<html><body>");
    out.println("<h2>Submission Result</h2>");
    if (name != null && !name.trim().isEmpty() && agree != null) {
      out.println("<p>Thank you, " + name + "! Your submission was successful.</p>");
    } else {
      out.println("<p>Submission failed. Please make sure all fields are filled correctly.</p>");
    }
    out.println("<a href=\"index.jsp\">Back</a>");
    out.println("</body></html>");
  }
}
