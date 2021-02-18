package it.bit.gestionalecorsi.entities;

import it.bit.gestionalecorsi.entities.Address;
import it.bit.gestionalecorsi.entities.Skill;

import java.time.LocalDate;
import java.util.List;

public abstract class Person {

    private long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private LocalDate birthDate;
    private String birthPlace;
    private String fiscalCode;
    private Address address;
    private String email;
    private String phone;

    private List<Skill> skills;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Person(){}

    public Person(String firstName,String lastName,Gender gender){

        this.firstName = firstName;
        this.lastName = lastName;
        this.gender =gender;

    }

    public Gender getGender(){
        return gender;
    }
}
