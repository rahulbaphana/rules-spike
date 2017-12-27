package com.tw.domain;

public enum Country {
    USA("USA", 18);

    private String name;
    private int legalDrivingAge;

    Country(String name, int legalDrivingAge) {
        this.name = name;
        this.legalDrivingAge = legalDrivingAge;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isValidDrivingAge(int age) {
        return age >= legalDrivingAge;
    }
}
