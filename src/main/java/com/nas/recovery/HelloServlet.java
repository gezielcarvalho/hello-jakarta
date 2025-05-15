package com.nas.recovery;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static final String NAME = "HelloServlet";
  private static final String VERSION = "1.0.0";
  private static final String DESCRIPTION = "A simple servlet that responds with a greeting.";
  private static final String AUTHOR = "Geziel Carvalho";
  private static final String LICENSE = "Apache License 2.0";
  private static final String LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0";
  private static final String COPYRIGHT = "Copyright (c) 2025 Geziel Carvalho";
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("text/html");
    resp.getWriter().write("<h1>Hello, Jakarta EE! v8</h1>");
  }
}
