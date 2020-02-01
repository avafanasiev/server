package afanasievald.interfaces;

import afanasievald.dataSets.SessionDataSet;
import afanasievald.dataSets.UsersDataSet;
import afanasievald.dbExecutor.DBException;

public interface DBService {
  public UsersDataSet getUserByLogin(String login)  throws DBException;

  public long addUser(String login, String password) throws DBException;

  public SessionDataSet getSessionBySessionId(String sessionId) throws DBException;

  public Long addSession(String sessionId, UsersDataSet usersDataSet) throws DBException;

  public void deleteSession(String sessionId) throws DBException;
}
