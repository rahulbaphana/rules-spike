import com.tw.domain.Country;
import com.tw.easy.rules.fact.Gift;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class CountryGiftRulesTest {

    @Test
    public void should_approve_for_USA_when_amount_is_less_than_100_dollars() {
        Gift giftRuleforUSA = new Gift(Country.USA, 90 , "Approved!");
        Rule giftMVELRule = giftRuleforUSA.amountRule();
        Facts giftFacts = new Facts();
        giftFacts.put("giftRule", giftRuleforUSA);

        RulesEngine defaultRulesEngine = new DefaultRulesEngine();
        Map<Rule, Boolean> checkResult = defaultRulesEngine.check(new Rules(giftMVELRule), giftFacts);

        Assert.assertTrue(checkResult.get(giftMVELRule));
    }

    @Test
    public void should_unapprove_for_USA_when_amount_is_greater_than_100_dollars() {
        Gift giftRuleforUSA = new Gift(Country.USA, 190, "Not Approved!");
        Rule giftMVELRule = giftRuleforUSA.amountRule();
        Facts giftFacts = new Facts();
        giftFacts.put("giftRule", giftRuleforUSA);

        RulesEngine defaultRulesEngine = new DefaultRulesEngine();
        Map<Rule, Boolean> checkResult = defaultRulesEngine.check(new Rules(giftMVELRule), giftFacts);

        Assert.assertFalse(checkResult.get(giftMVELRule));
    }
}
