package com.easy.rules.fact;

import com.domain.Country;
import org.jeasy.rules.mvel.MVELRule;

public class Gift {
    private final Country country;
    private final int amount;

    public Gift(Country country, int amount) {
        this.country = country;
        this.amount = amount;
    }

    public boolean isValid() {
        return country.isWithingGifting(this.amount);
    }

    public MVELRule amountRule() {
        return new MVELRule()
                .name("Gift rule for "+country.toString())
                .description("Amount within country bribery limit, then approve!")
                .when("giftRule.isValid()")
                .then("System.out.println(\"Approved!\");");
    }
}
