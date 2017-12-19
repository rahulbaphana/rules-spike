import com.tw.domain.Country;
import com.tw.easy.rules.Gift;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class TestRules {

    @Test
    public void should_approve_for_USA_when_amount_is_less_than_100_dollars() {
        Gift giftRuleforUSA = new Gift(Country.USA, 0, 100, "Approved!");
        MVELRule giftMVELRule = giftRuleforUSA.toEasyRule();
        Facts giftFacts = new Facts();
        giftFacts.put("giftRule", giftRuleforUSA);
        giftFacts.put("amountToGift", 90);

        RulesEngine defaultRulesEngine = new DefaultRulesEngine();
        Map<Rule, Boolean> checkResult = defaultRulesEngine.check(new Rules(giftMVELRule), giftFacts);
        defaultRulesEngine.fire(new Rules(giftMVELRule), giftFacts); // this is not mandatory!

        Assert.assertTrue(checkResult.get(giftMVELRule));
    }
}