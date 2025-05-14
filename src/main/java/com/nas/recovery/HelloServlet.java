package com.nas.recovery;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("text/html");
    resp.getWriter().write("<h1>Hello, Jakarta EE!</h1>");
  }
}
