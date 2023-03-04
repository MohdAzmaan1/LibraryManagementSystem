package com.example.Library.Management.System.DTO;

public class AuthorEntryDTO {

    //This is just an object that will be used to take request from postman
    //It will contain the parameter that we want to send from postman

    private String name;
    private int age;
    private String country;
    private double rating;

    //ID is not here because we dnt want to send it from postman


    public AuthorEntryDTO() {
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
