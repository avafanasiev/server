package servlets;

import interfaces.AccountService;
import dbExecutor.DBException;
import dataSets.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//При получении POST запроса на signup сервлет SignUpServlet должн запомнить логин и пароль в AccountService.
   //     После этого польователь с таким логином считается зарегистрированным.
public class SignUpServlet extends HttpServlet {
  private final AccountService accountService;
  private static final Logger logger = LogManager.getLogger(SignUpServlet.class.getName());

  public SignUpServlet(AccountService accountService) {
    this.accountService = accountService;
  }

  public void doPost(HttpServletRequest request,
                     HttpServletResponse response) throws ServletException, IOException {
    try {
      logger.info("SignUpServlet doPost");
      String login = request.getParameter("login");
      String password = request.getParameter("password");

      if (login == null || password == null) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return;
      }

      //Может быть не стоит возвращать id? Если ошибка. вылетит исключение, иначе ok
      Long id = accountService.addNewUser(login, password);

      UsersDataSet usersDataSet = accountService.getUserByLogin(login);
      if (usersDataSet != null) {
        accountService.addSession(request.getSession().getId(), usersDataSet);
      }
      SessionDataSet sessionDataSet = accountService.getSessionBySessionId(request.getSession().getId());
      accountService.deleteSession(request.getSession().getId());

      response.setContentType("text/html;charset=utf-8");
      response.setStatus(HttpServletResponse.SC_OK);
    } catch (DBException e) {
      logger.error(e.getMessage());
      throw new ServletException(e);
    }
  }
}
