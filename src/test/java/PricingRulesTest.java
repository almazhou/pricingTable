import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PricingRulesTest {

    private RuleColumn ruleColumn1;
    private RuleColumn ruleColumn2;
    private PricingRuleRow pricingRule1;
    private PricingRules pricingRules;

    @Before
    public void setUp() throws Exception {
        createPricingRule("location", "xi'an", "job", "engineer");
    }

    private void createPricingRule(String columnName1, String keyword1, String columnName2, String keyword2) {
        ruleColumn1 = createRule(columnName1, keyword1);
        ruleColumn2 = createRule(columnName2, keyword2);
        pricingRule1 = createPricingRule(ruleColumn1, ruleColumn2);
        pricingRules = new PricingRules(Arrays.asList(pricingRule1));
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
        List<String> keywords = Arrays.asList("chengdu", "finance");
        int match = pricingRules.match(keywords);

        assertThat(match, is(0));
    }

    private PricingRuleRow createPricingRule(RuleColumn rule1, RuleColumn rule2) {
        List<RuleColumn> ruleColumns = Arrays.asList(rule1, rule2);
        Map<String, Double> priceMap = new HashMap<String, Double>();
        priceMap.put("price",500.00);
        PricingRuleRow pricingRuleRow = new PricingRuleRow(ruleColumns,priceMap);
        return pricingRuleRow;
    }

    private RuleColumn createRule(String itemName, String keyword) {
        Map<String, String> rule = new HashMap<String, String>();
        rule.put(itemName, keyword);
        return new RuleColumn(rule);
    }
}
