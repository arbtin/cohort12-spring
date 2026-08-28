package mil.army.moda.springexample.person;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Assignment {
    @Id
    @GeneratedValue
    private Long id;
    private String type;

    public Assignment() {}
    public Assignment(String type) { this.type = type; }
    public String getType() { return type; }
}