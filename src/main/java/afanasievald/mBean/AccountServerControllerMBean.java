package afanasievald.mBean;

public interface AccountServerControllerMBean {
  int getUsersCount();

  int getUsersLimit();

  void setUsersLimit(int usersLimit);
}
