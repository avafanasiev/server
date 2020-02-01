package afanasievald.servlets;

import afanasievald.mBean.ResourceServerControllerMBean;
import afanasievald.sax.ReadXMLFileSAX;
import org.apache.logging.log4j.Logger;
import afanasievald.resources.TestResource;
import afanasievald.services.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResourcesServlet extends HttpServlet {
  private ResourceServerControllerMBean resourceServerController;
  private Logger logger;

  public ResourcesServlet(ResourceServerControllerMBean resourceServerController){
    logger = LogService.getLogger(SignInServlet.class.getName());
    this.resourceServerController = resourceServerController;
  }

//  Написать сервлет, который будет обрабатывать запросы на /.resources
//  При получении POST запроса с параметром path=path_to_resource
//  прочитает ресурс TestResource из указанного в параметре файла и сохранит ссылку в ResourceService

  public void doPost(HttpServletRequest request,
                     HttpServletResponse response) throws ServletException, IOException {
    try {
      logger.info("ResourcesServlet doPost");
      String path = request.getParameter("path");

      if (path == null) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return;
      }

      TestResource testResource = (TestResource) ReadXMLFileSAX.readXML(path);
      resourceServerController.setTestResource(testResource);

      response.setContentType("text/html;charset=utf-8");
      response.setStatus(HttpServletResponse.SC_OK);
    }
    catch (Exception e) {
      logger.error(e.getMessage());
      throw new ServletException(e);
    }
  }
}
