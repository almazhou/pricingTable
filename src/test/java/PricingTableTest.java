import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class PricingTableTest {

    private TestUtils testUtils;
    private PricingTable pricingTable;

    @Before
    public void setUp() throws Exception {
        testUtils = new TestUtils();
        pricingTable = new PricingTable(getPricingRules());
    }

    @Test
    public void shouldReturnMoneyOfExactMatch() throws Exception {
        pricingTable.getPrice("chengdu","engineer");

    }

    private PricingRules getPricingRules() {
        RuleColumn rule1 = testUtils.createRuleColumn("city", "chengdu");
        RuleColumn rule2 = testUtils.createRuleColumn("job", "engineer");
        Map<String, Double> price1 = testUtils.createPriceMap("price", 300.00);
        PricingRuleRow pricingRow = testUtils.createPricingRow(price1, rule1, rule2);

        RuleColumn rule3 = testUtils.createRuleColumn("city", "xi'an");
        RuleColumn rule4 = testUtils.createRuleColumn("job", "finance");
        Map<String, Double> price2 = testUtils.createPriceMap("price", 200.00);
        PricingRuleRow pricingRow1 = testUtils.createPricingRow(price2, rule3, rule4);

        return testUtils.createPricingRules(pricingRow, pricingRow1);
    }
}
