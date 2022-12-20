package it.step.Model;

import java.sql.Date;
import java.time.LocalDate;

public class Person {
    private Integer id;
    private String name;
    private String surname;
    private Gender gender;
    private LocalDate birthdate;

    public Person(Integer id, String name, String surname, Gender gender, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }
}
