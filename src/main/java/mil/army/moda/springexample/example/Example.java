package mil.army.moda.springexample.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Example {

    @Id
    private Long id;

    private String name;

    private char size;

    private int direction;

    private double precision;

    private boolean isAvailable;

    public Long getId() {
        return id;
    }

    public Example setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Example setName(String name) {
        this.name = name;
        return this;
    }

    public char getSize() {
        return size;
    }

    public Example setSize(char size) {
        this.size = size;
        return this;
    }

    public int getDirection() {
        return direction;
    }

    public Example setDirection(int direction) {
        this.direction = direction;
        return this;
    }

    public double getPrecision() {
        return precision;
    }

    public Example setPrecision(double precision) {
        this.precision = precision;
        return this;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public Example setIsAvailable(boolean isAvailable) {
        isAvailable = isAvailable;
        return this;
    }
}
