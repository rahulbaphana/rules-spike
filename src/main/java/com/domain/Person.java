package com.domain;

public class Person {
    Country country;
    int age;

    public Person(Country country,int age) {
        this.country = country;
        this.age = age;
    }

    public boolean isDrivingAge() {
        return country.isValidDrivingAge(age);
    }
}
