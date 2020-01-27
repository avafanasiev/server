package servlets;

import interfaces.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends HttpServlet {
  private final AccountService accountService;
  private static final Logger logger = LogManager.getLogger(AdminServlet.class.getName());

  public AdminServlet(AccountService accountService) {
    this.accountService = accountService;
  }

  public void doGet(HttpServletRequest request,
                     HttpServletResponse response) throws IOException {
      logger.info("AdminServlet doGet");
      response.setContentType("text/html;charset=utf-8");
      response.getWriter().println(accountService.getUsersLimit());
      response.setStatus(HttpServletResponse.SC_OK);
  }
}
