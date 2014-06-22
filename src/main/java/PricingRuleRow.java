import java.util.List;
import java.util.Map;

public class PricingRuleRow {
    private List<RuleColumn> ruleColumns;
    private Map<String,Double> price;
    public PricingRuleRow(List<RuleColumn> ruleColumns, Map<String, Double> price) {
        this.ruleColumns = ruleColumns;
        this.price = price;
    }

    public List<RuleColumn> getRuleColumns() {
        return ruleColumns;
    }

    public Map<String, Double> getPrice() {
        return price;
    }
}
