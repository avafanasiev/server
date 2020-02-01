package afanasievald.interfaces;

import org.hibernate.Session;

public interface DAOHandler<T> {
  T handle(Session session);
}
