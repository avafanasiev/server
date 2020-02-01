package afanasievald.services;

import afanasievald.dbExecutor.DBException;
import afanasievald.dao.*;
import afanasievald.dataSets.*;
import afanasievald.dbExecutor.Executor;
import afanasievald.interfaces.DAOHandler;
import afanasievald.interfaces.DBService;
import org.hibernate.Session;

public class DBServiceImpl implements DBService {


    private Executor executor;
    public DBServiceImpl() {
      executor = new Executor();
    }

    public UsersDataSet getUserByLogin(String login)  throws DBException {
      UsersDataSet dataSet = executor.execQuery(new DAOHandler<UsersDataSet>() {
                           @Override
                           public UsersDataSet handle(Session session){
                             UsersDAO usersDAO = new UsersDAO();
                             return usersDAO.getUserByLogin(session, login);
                           }
                         });
      return dataSet;
    }

    public long addUser(String login, String password) throws DBException {
      Long id = executor.execQuery(new DAOHandler<Long>() {
        @Override
        public Long handle(Session session){
          UsersDAO usersDAO = new UsersDAO();
          return usersDAO.insertUser(session, login, password);
        }
      });
      return id;
    }

    public SessionDataSet getSessionBySessionId(String sessionId) throws DBException {
      SessionDataSet dataSet = executor.execQuery(new DAOHandler<SessionDataSet>() {
        @Override
        public SessionDataSet handle(Session session){
          SessionsDAO sessionsDAO = new SessionsDAO();
          return sessionsDAO.getSessionBySessionId(session, sessionId);
        }
      });
      return dataSet;
    }

    public Long addSession(String sessionId, UsersDataSet usersDataSet) throws DBException {
      Long id = executor.execQuery(new DAOHandler<Long>() {
        @Override
        public Long handle(Session session){
          SessionsDAO sessionsDAO = new SessionsDAO();
          return sessionsDAO.addSession(session, sessionId, usersDataSet);
        }
      });
      return id;
    }

    public void deleteSession(String sessionId) throws DBException {
      executor.execQuery(new DAOHandler<Long>() {
        @Override
        public Long handle(Session session){
          SessionsDAO sessionsDAO = new SessionsDAO();
          sessionsDAO.deleteSession(session, sessionId);
          return 1L;
        }
      });
    }
}
