package main;

import interfaces.AccountService;
import mBean.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.AccountServiceImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 *         https://stepik.org/lesson/12405/step/15?unit=2835
 */
public class Main {
  private static final Logger logger = LogManager.getLogger(Main.class.getName());

  public static void main(String[] args) throws Exception {
      AccountService accountService = new AccountServiceImpl(10);

        AccountServerControllerMBean serverStatistics = new AccountServerController(accountService);
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("Admin:type=AccountServerController.usersLimit");
        mbs.registerMBean(serverStatistics, name);

        accountService.addNewUser("admin","admin");
        accountService.addNewUser("test", "test");

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        //https://stepik.org/lesson/12405/step/15?unit=2835
        logger.info("singup servlet");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");
        logger.info("singin servlet");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");

        //https://stepik.org/lesson/12512/step/15?unit=2971
        logger.info("admin servlet");
        context.addServlet(new ServletHolder(new AdminServlet(accountService)), "/admin");

        //https://stepik.org/lesson/12403/step/11?unit=2833
        logger.info("chat servlet");
        context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");

        Server server = new Server(8080);

        server.setHandler(context);

        server.start();
        logger.info("Server started");
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }
}
