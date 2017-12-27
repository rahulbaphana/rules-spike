package com.tw.easy.rules.fact;

import com.tw.domain.Country;
import org.jeasy.rules.mvel.MVELRule;

public class Gift {
    private final Country country;
    private final int minAmount;
    private final int maxAmount;
    private final String action;

    public Gift(Country country, int minAmount, int maxAmount, String action) {
        this.country = country;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.action = action;
    }

    public boolean isValid(int amount) {
        return amount <= maxAmount;
    }

    public MVELRule amountRule() {
        return new MVELRule()
                .name("Gift rule for "+country.toString())
                .description("if amount is within minimum "+ minAmount + " and maximum "+ maxAmount +", then auto approved")
                .when("giftRule.isValidGift(amountToGift)")
                .then("System.out.println(\""+action+"\");");
    }
}
