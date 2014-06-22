import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PricingRulesTest {
    private PricingRules pricingRules;
    public TestUtils testUtils;

    @Before
    public void setUp() throws Exception {
        testUtils = new TestUtils();
        RuleColumn ruleColumn1 = testUtils.createRuleColumn("location", "xi'an");
        RuleColumn ruleColumn2 = testUtils.createRuleColumn("job", "engineer");
        RuleColumn ruleColumn3 = testUtils.createRuleColumn("location", "chengdu");
        RuleColumn ruleColumn4 = testUtils.createRuleColumn("job", "finance");
        PricingRuleRow pricingRule1 = testUtils.createPricingRow(testUtils.createPriceMap("price", 500.00),ruleColumn1,ruleColumn2);
        PricingRuleRow pricingRule2 = testUtils.createPricingRow(testUtils.createPriceMap("price", 200.00),ruleColumn3,ruleColumn4);
        pricingRules = testUtils.createPricingRules(pricingRule1,pricingRule2);
    }

    @Test
    public void shouldReturn1WhenExactMatch() throws Exception {
        List<String> keywords = Arrays.asList("xi'an", "engineer");
        int match = pricingRules.match(keywords);

        assertThat(match,is(1));
    }

    @Test
    public void shouldReturnMinus1WhenFuzzyMatch() throws Exception {
        List<String> keywords = Arrays.asList("xi'an", "finance");
        int match = pricingRules.match(keywords);

        assertThat(match,is(-1));
    }

    @Test
    public void shouldReturn0WhenNotMatchAtAll() throws Exception {
        List<String> keywords = Arrays.asList("gulin", "teacher");
        int match = pricingRules.match(keywords);

        assertThat(match, is(0));
    }

}
