package com.tw.domain;

public enum Country {
    USA("USA");

    private String name;
    Country(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
