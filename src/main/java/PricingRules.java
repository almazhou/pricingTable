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
        if (findMatchRow(keywords, matchResult) != null) return 1;
        if (isFuzzMatch(matchResult)) return -1;
        return 0;
    }

    private boolean isFuzzMatch(List<List<Map<String, Double>>> matchResult) {
        return matchResult.size() != 0;
    }

    public List<Map<String, Double>> findMatchRow(List<String> keywords, List<List<Map<String, Double>>> matchResult) {
        for (List<Map<String, Double>> list : matchResult) {
            if (list.size() == keywords.size()) return list;
        }
        return null;
    }

    public List<List<Map<String, Double>>> matches(List<String> keywords) {
        List<List<Map<String, Double>>> findingResult = new ArrayList<List<Map<String, Double>>>();
        for (PricingRuleRow item : rules) {
            List<Map<String, Double>> resultMap = compareEachRow(item, keywords);
            if (resultMap.size() != 0) {
                findingResult.add(resultMap);
            }
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
