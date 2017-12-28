package com.domain;

import com.domain.country.rule.DrivingAgeLimit;
import com.domain.country.rule.GiftingMoneyLimit;

public enum Country implements DrivingAgeLimit, GiftingMoneyLimit {
    USA("USA", 18, 100);

    private String name;
    private int legalDrivingAge;
    private int legalGiftAmount;

    Country(String name, int legalDrivingAge, int legalGiftAmount) {
        this.name = name;
        this.legalDrivingAge = legalDrivingAge;
        this.legalGiftAmount = legalGiftAmount;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean isValidDrivingAge(int age) {
        return age >= legalDrivingAge;
    }

    @Override
    public boolean isWithingGifting(int amount) {
        return amount <= legalGiftAmount;
    }
}
