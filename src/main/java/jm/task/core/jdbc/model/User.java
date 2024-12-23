package jm.task.core.jdbc.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "lastName", nullable = false)
    private String lastName;


    @Column(name = "age", nullable = false)
    private Byte age;

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name=" + name
                + ", lastName=" + lastName + ", age="
                + age + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null | o.getClass() != getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(user.getName(), this.getName())
                && Objects.equals(user.getLastName(), this.getLastName())
                && Objects.equals(user.getAge(), this.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, age);
    }
}
