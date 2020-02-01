package afanasievald.services;

import afanasievald.dbExecutor.DBException;
import afanasievald.dataSets.*;
import afanasievald.interfaces.AccountService;
import afanasievald.interfaces.DBService;

public class AccountServiceImpl implements AccountService {
    private final DBService dbService;
    private int usersCount;
    private int usersLimit;

    public AccountServiceImpl(int usersLimit) {
        dbService = new DBServiceImpl();
        this.usersCount = 0;
        this.usersLimit = usersLimit;

    }

    public void removeUser(){
      usersCount --;
    }

    public int getUsersCount(){
      return usersCount;
    }

    public int getUsersLimit(){
      return usersLimit;
    }

    public void setUserLimits(int usersLimit){
      this.usersLimit = usersLimit;
    }

    public Long addNewUser(String login, String password) throws DBException {
      usersCount ++;
        long userId = dbService.addUser(login, password);
        return userId;
    }

    public UsersDataSet getUserByLogin(String login) throws DBException{
        UsersDataSet usersDataSet = dbService.getUserByLogin(login);
        return usersDataSet;
    }

    public SessionDataSet getSessionBySessionId(String sessionId) throws DBException {
        return dbService.getSessionBySessionId(sessionId);
    }

    public void addSession(String sessionId, UsersDataSet usersDataSet) throws DBException {
        dbService.addSession(sessionId, usersDataSet);
    }

    public void deleteSession(String sessionId) throws DBException{
        dbService.deleteSession(sessionId);
    }
}
