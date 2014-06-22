import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PricingRules {


    private List<PricingRuleRow> rules;

    public PricingRules(List<PricingRuleRow> rules) {
        this.rules = rules;
    }

    public int match(List<String> keywords) {
        List<List<Map<String, Double>>> matchResult = matches(keywords);
        int sum = 0;
        for (List<Map<String, Double>> list : matchResult) {
            sum += list.size();
            if (list.size() == keywords.size()) return 1;
        }
        if (sum != 0) return -1;
        return 0;
    }

    public List<List<Map<String, Double>>> matches(List<String> keywords) {
        List<List<Map<String, Double>>> findingResult = new ArrayList<List<Map<String, Double>>>();
        for (PricingRuleRow item : rules) {
            List<Map<String, Double>> resultMap = compareEachRow(item, keywords);
            findingResult.add(resultMap);
        }
        return findingResult;
    }

    private List<Map<String, Double>> compareEachRow(PricingRuleRow item, List<String> keywords) {
        List<RuleColumn> ruleColumns = item.getRuleColumns();
        List<Map<String, Double>> priceMaps = new ArrayList<Map<String, Double>>();
        for (String keyword : keywords) {
            for (RuleColumn column : ruleColumns) {
                if (column.getRule().containsValue(keyword)) {
                    priceMaps.add(item.getPrice());
                }
            }
        }
        return priceMaps;
    }

}
