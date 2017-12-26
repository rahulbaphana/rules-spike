package com.tw.easy.rules;

import com.tw.domain.Country;
import com.tw.easy.rules.fact.Gift;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRuleFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

public class TestRule {
    public static void main(String[] args) throws FileNotFoundException {
        String giftRuleYmlFile = new TestRule().getRuleFromFile();
        Rule giftMVELRule = MVELRuleFactory.createRuleFrom(new File(giftRuleYmlFile));
        Facts giftFacts = new Facts();
        giftFacts.put("giftRule", new Gift(Country.USA, 0, 100, "Not Approved!"));
        giftFacts.put("amountToGift", 190);

        RulesEngine defaultRulesEngine = new DefaultRulesEngine();
        Map<Rule, Boolean> checkResult = defaultRulesEngine.check(new Rules(giftMVELRule), giftFacts);

        for (Rule rule : checkResult.keySet()) {
            System.out.println("Rule : "+rule.getName() + " | state : "+checkResult.get(rule));
        }
    }

    private String getRuleFromFile() {
        return getClass().getClassLoader().getResource("gift-rule.yml").getFile();
    }
}
