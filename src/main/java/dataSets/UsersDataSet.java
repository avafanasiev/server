package dataSets;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable { // Serializable Important to Hibernate!

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login ", unique = true, updatable = false)
    private String login;

    @Column(name = "password")
    private String password;

    @OneToMany (mappedBy="usersDataSet", fetch=FetchType.EAGER)
    private Collection<SessionDataSet> sessionDataSetCollection;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet() {
    }

    public UsersDataSet(String login, String password)
    {
        this.login = login;
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
}