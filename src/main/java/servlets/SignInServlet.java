package servlets;

import interfaces.AccountService;
import dbExecutor.DBException;
import dataSets.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*При получении POST запроса на signin, после регистрации, SignInServlet проверяет,
        логин/пароль пользователя. Если пользователь уже зарегистрирован, север отвечает

        Status code (200)
        и текст страницы:
        Authorized: login

        если нет:
        Status code (401)
        текст страницы:
        Unauthorized*/

public class SignInServlet extends HttpServlet {
  private final AccountService accountService;
  private Logger logger;

  public SignInServlet(AccountService accountService) {
    this.accountService = accountService;
    logger = LogService.getLogger(SignInServlet.class.getName());
  }

  public void doPost(HttpServletRequest request,
                     HttpServletResponse response) throws ServletException, IOException {
    try {
      logger.info("SignInServlet doPost");
      String login = request.getParameter("login");
      String password = request.getParameter("password");

      if (login == null || password == null) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return;
      }

      UsersDataSet profile = accountService.getUserByLogin(login);
      // Тут мне не нравится, что я возвращаю UsersDataSet - внутренний класс для работы с дата сет.
      // В данном конкретном примере можно в принципе легко переписать? а в общем случае нужно какую-то прослойку делать?
      if (profile == null || !profile.getPassword().equals(password)) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return;
      }

      response.setContentType("text/html;charset=utf-8");
      response.getWriter().println(String.format("Authorized: %s", login));
      response.setStatus(HttpServletResponse.SC_OK);
    }
    catch (DBException e) {
      logger.error(e.getMessage());
      throw new ServletException(e);
    }
  }
}
