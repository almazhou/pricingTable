import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestUtils {
    public PricingRuleRow createPricingRow(Map<String, Double> priceMap, RuleColumn... ruleColumns) {
        return new PricingRuleRow(priceMap,ruleColumns);
    }

    public Map<String, Double> createPriceMap(String price, double amount) {
        Map<String, Double> priceMap = new HashMap<String, Double>();
        priceMap.put(price, amount);
        return priceMap;
    }

    public RuleColumn createRuleColumn(String itemName, String keyword) {
        Map<String, String> rule = new HashMap<String, String>();
        rule.put(itemName, keyword);
        return new RuleColumn(rule);
    }

    public PricingRules createPricingRules(PricingRuleRow... pricingRuleRow) {
        return new PricingRules(Arrays.asList(pricingRuleRow));
    }
}
