import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PricingRuleRow {
    private List<RuleColumn> ruleColumns;
    private Map<String,Double> price;
    public PricingRuleRow(Map<String, Double> price, RuleColumn... ruleColumns) {
        this.ruleColumns = Arrays.asList(ruleColumns);
        this.price = price;
    }

    public List<RuleColumn> getRuleColumns() {
        return ruleColumns;
    }

    public Map<String, Double> getPrice() {
        return price;
    }
}
