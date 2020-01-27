package dao;

import dataSets.*;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class SessionsDAO {

  public SessionsDAO() {
  }

  public SessionDataSet getSessionBySessionId(Session session, String sessionId) throws HibernateException {
    Criteria criteria = session.createCriteria(SessionDataSet.class);
    return (SessionDataSet) criteria.add(Restrictions.eq("sessionId", sessionId)).uniqueResult();
  }

  public long addSession(Session session, String sessionId, UsersDataSet usersDataSet) throws HibernateException {
    return (Long) session.save(new SessionDataSet(sessionId, usersDataSet));
  }

  public void deleteSession(Session session, String sessionId) {
    SessionDataSet sessionDataSet = getSessionBySessionId(session, sessionId);
    //Delete the object
    if (sessionDataSet!= null) session.delete(sessionDataSet);
  }
}
