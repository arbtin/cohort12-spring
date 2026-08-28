package mil.army.moda.springexample.person;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Role() {}
    public Role(String name) { this.name = name; }
    public String getName() { return name; }
}
