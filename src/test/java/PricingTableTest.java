import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
        double price = pricingTable.getPrice("xi'an", "finance");

        assertThat(price,is(200.00));
    }

    @Test
    public void shouldReturnHighestMoneyWhenFuzzMatch() throws Exception {
        double price = pricingTable.getPrice("xi'an", "chengdu");

        assertThat(price,is(300.00));

    }

    @Test
    public void shouldReturnDefaultValueWhenNotMatch() throws Exception {
        double price = pricingTable.getPrice("zhouxuan", "test");

        assertThat(price,is(100.00));

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
