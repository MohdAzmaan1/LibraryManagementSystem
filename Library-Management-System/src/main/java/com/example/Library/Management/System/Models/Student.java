package com.example.Library.Management.System.Models;

import javax.persistence.*;

@Entity
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String email;

    private String name;
    private int age;
    private String country;
    private String mobileNumber;

    //Name of variable of the Parent Entity that you have written in child class foreign key attribute
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL) // Bidirectional Mapping
    private Card card;
    /*
        Steps to find that variable
        1. Go the child class (In this case)
        2. Out of all the attributes select the foreign key attribute that is helping you connect
        with parent class
        (Ref :  @OneToOne
                @JoinColumn
                private Student studentVariableName;
        )
        3. Choose the variable name of the parent Entity (reference : student)
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
