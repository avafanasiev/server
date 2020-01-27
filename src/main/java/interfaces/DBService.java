package interfaces;

import dataSets.SessionDataSet;
import dataSets.UsersDataSet;
import dbExecutor.DBException;

public interface DBService {
  public UsersDataSet getUserByLogin(String login)  throws DBException;

  public long addUser(String login, String password) throws DBException;

  public SessionDataSet getSessionBySessionId(String sessionId) throws DBException;

  public Long addSession(String sessionId, UsersDataSet usersDataSet) throws DBException;

  public void deleteSession(String sessionId) throws DBException;
}
