import com.tw.domain.Country;
import com.tw.easy.rules.fact.Gift;
import com.tw.reader.JsonRulesReader;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class JsonRulesReaderTest {
    @Test
    public void should_load_rules_from_json() throws IOException {
        JsonRulesReader jsonRulesReader = loadRulesReader();

        List<Rule> rules = jsonRulesReader.allRules();

        assertEquals(2, rules.size());
    }

    @Test
    public void should_get_rule_by_name() throws IOException {
        JsonRulesReader jsonRulesReader = loadRulesReader();

        Optional<Rule> countryGiftRule = jsonRulesReader.getRuleBy("country-gift-rule");

        assertEquals(expectedCountryGiftRule(), countryGiftRule.get());
    }

    @Test
    public void should_execute_gift_rule_with_failure() throws IOException {
        JsonRulesReader jsonRulesReader = loadRulesReader();
        Gift giftAmountInUSA = legalGiftAmountInUSA();
        Rule countryGiftRule = jsonRulesReader.getRuleBy("country-gift-rule").get();

        RulesEngine defaultRulesEngine = new DefaultRulesEngine();
        Map<Rule, Boolean> checkResult = defaultRulesEngine.check(new Rules(countryGiftRule), factsFor(giftAmountInUSA, 190));

        assertFalse(checkResult.get(countryGiftRule));
    }

    @Test
    public void should_execute_gift_rule_with_approval() throws IOException {
        JsonRulesReader jsonRulesReader = loadRulesReader();
        Gift giftAmountInUSA = legalGiftAmountInUSA();
        Rule countryGiftRule = jsonRulesReader.getRuleBy("country-gift-rule").get();

        RulesEngine defaultRulesEngine = new DefaultRulesEngine();
        Map<Rule, Boolean> checkResult = defaultRulesEngine.check(new Rules(countryGiftRule), factsFor(giftAmountInUSA, 90));

        assertTrue(checkResult.get(countryGiftRule));
    }

    private Facts factsFor(Gift giftAmountInUSA, int amountToGift) {
        Facts giftFacts = new Facts();
        giftFacts.put("gift", giftAmountInUSA);
        giftFacts.put("amountToGift", amountToGift);
        return giftFacts;
    }

    private Gift legalGiftAmountInUSA() {
        return new Gift(Country.USA, 0, 100, "Not Approved!");
    }

    private MVELRule expectedCountryGiftRule() {
        return new MVELRule().name("country-gift-rule")
                                    .description("Country specific rule for gifting money")
                                    .priority(1)
                                    .when("giftRule.isValidGift(amountToGift)")
                                    .then("System.out.println(\"Its pre approved!\");");
    }


    private JsonRulesReader loadRulesReader() throws IOException {
        return new JsonRulesReader("[{\n" +
                "   \"name\": \"country-gift-rule\",\n" +
                "   \"description\": \"Country specific rule for gifting money\",\n" +
                "   \"condition\": \"gift.isValid(amountToGift)\",\n" +
                "   \"priority\": 1,\n" +
                "   \"action\": \"System.out.println(\\\"Its pre approved!\\\");\"\n" +
                " }, {\n" +
                "  \"name\": \"country-age-rule\",\n" +
                "  \"description\": \"Country specific rule for driving\",\n" +
                "  \"condition\": \"person.isDrivingAge()\",\n" +
                "  \"priority\": 1,\n" +
                "  \"action\": \"System.out.println(\\\"You may drive!\\\");\"\n" +
                "}]");
    }
}
