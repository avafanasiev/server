package dataSets;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sessions")
public class SessionDataSet implements Serializable { // Serializable Important to Hibernate!

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "sessionId", unique = true, updatable = false)
  private String sessionId;

  @ManyToOne ()
  @JoinColumn (name="userId")
  private UsersDataSet usersDataSet;


  //Important to Hibernate!
  @SuppressWarnings("UnusedDeclaration")
  public SessionDataSet() {
  }

  public SessionDataSet(String sessionId, UsersDataSet usersDataSet) {
    this.sessionId = sessionId;
    this.usersDataSet = usersDataSet;
  }

}