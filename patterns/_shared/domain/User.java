package _shared.domain;

import lombok.Data;
import lombok.Setter;

@Data
public class User {
    private Long id;
    private String name;
    private String login;

    public User(String login, String name) {
        this.login = login;
        this.name = name;
    }

    public User() {}
}
