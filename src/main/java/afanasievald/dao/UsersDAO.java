package afanasievald.dao;

import afanasievald.dataSets.*;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UsersDAO {

  public UsersDAO() {
  }

  public UsersDataSet get(Session session, long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public UsersDataSet getUserByLogin(Session session, String login) throws HibernateException {
        Criteria criteria = session.createCriteria(UsersDataSet.class);
        return (UsersDataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult();
    }

    public long insertUser(Session session, String login, String password) throws HibernateException {
        return (Long) session.save(new UsersDataSet(login, password));
    }
}
