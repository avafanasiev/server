package afanasievald.mBean;

import afanasievald.interfaces.AccountService;

public class AccountServerController implements AccountServerControllerMBean {
  private final AccountService accountService;
  public AccountServerController(AccountService accountService){
    this.accountService = accountService;
  }

  @Override //не везде у меня стоит эта аннотация, если я переопределею метод интерфейс
  public int getUsersCount(){
    return accountService.getUsersCount();
  }

  @Override
  public int getUsersLimit(){
    return accountService.getUsersLimit();
  }

  @Override
  public void setUsersLimit(int usersLimit){
    accountService.setUserLimits(usersLimit);
  }
}
