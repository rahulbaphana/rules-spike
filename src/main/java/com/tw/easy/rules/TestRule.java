package com.tw.easy.rules;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;

import java.util.Map;

public class TestRule {
    public static void main(String[] args) {
        //Create rule
        MVELRule drinkingRuleInIndia = new MVELRule()
                .name("drinking rule in India")
                .description("If 25 years of age, you can drink")
                .when("age >= 25")
                .then("System.out.println(\"You may drink since you are above the drinking age!\");");

        //Add facts
        Facts ageFacts = new Facts();
        ageFacts.put("age", 25);

        //Check with the rules engine
        RulesEngine defaultRulesEngine = new DefaultRulesEngine();
        Map<Rule, Boolean> checkResult = defaultRulesEngine.check(new Rules(drinkingRuleInIndia), ageFacts);
        for (Rule rule : checkResult.keySet()) {
            System.out.println("Rule :: " + rule.getName() + " | result ::"+checkResult.get(rule));
        }
    }
}
