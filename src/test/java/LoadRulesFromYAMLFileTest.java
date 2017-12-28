import com.tw.domain.Country;
import com.tw.easy.rules.fact.Gift;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

public class LoadRulesFromYAMLFileTest {

    @Test
    public void should_load_rule_from_file() throws FileNotFoundException {
        String giftRuleYmlFile = getClass().getClassLoader().getResource("gift-rule.yml").getFile();
        Rule giftMVELRule = MVELRuleFactory.createRuleFrom(new File(giftRuleYmlFile));
        Facts giftFacts = new Facts();
        giftFacts.put("giftRule", new Gift(Country.USA, 190, "Not Approved!"));

        RulesEngine defaultRulesEngine = new DefaultRulesEngine();
        Map<Rule, Boolean> checkResult = defaultRulesEngine.check(new Rules(giftMVELRule), giftFacts);

        Assert.assertFalse(checkResult.get(giftMVELRule));
    }
}
