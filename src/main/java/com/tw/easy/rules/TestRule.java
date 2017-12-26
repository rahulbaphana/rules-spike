package com.tw.easy.rules;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RuleBuilder;

import java.util.Map;

public class TestRule {
    public static void main(String[] args) {
        Rule giftAmountRule = new RuleBuilder()
                .description("Gift Amount Rule")
                .name("Country-Amount-Rule")
                .when(facts -> facts.<Integer>get("amountToGift") <= 100)
                .then(facts -> System.out.println("You may gift!!"))
                .build();

        Facts giftFacts = new Facts();
        giftFacts.put("amountToGift", 90);

        RulesEngine defaultRulesEngine = new DefaultRulesEngine();
        Map<Rule, Boolean> checkResult = defaultRulesEngine.check(new Rules(giftAmountRule), giftFacts);
        defaultRulesEngine.fire(new Rules(giftAmountRule), giftFacts);

        for (Rule rule : checkResult.keySet()) {
            System.out.println("Rule : "+rule.getName() + " | state : "+checkResult.get(rule));
        }
    }
}
