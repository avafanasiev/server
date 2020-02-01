package services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class LogService {
  private static Map<String, Logger> loggers;
  private LogService(){
    loggers = new HashMap<>();
  };

  public static Logger getLogger(String className) {
    if (loggers == null){
      loggers = new HashMap<>();
    }

    if (loggers.containsKey(className)){
      return loggers.get(className);
    } else{
      Logger logger = LogManager.getLogger(className);
      loggers.put(className, logger);
      return logger;
    }
  }
}
