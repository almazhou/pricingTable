import java.util.ArrayList;
import java.util.List;

public class PricingRules {


    private List<PricingRuleRow> rules;

    public PricingRules(List<PricingRuleRow> rules) {
        this.rules = rules;
    }

    public int match(List<String> keywords) {
        List<Integer> findingResult = new ArrayList<Integer>();
        for(String keyword:keywords){
            for (PricingRuleRow item : rules) {
                compareEachRow(findingResult, keyword, item);
            }
        }
        if(noMatch(findingResult)) return 0;
        if(exactMatch(keywords, findingResult)) return 1;
        return -1;
    }

    private void compareEachRow(List<Integer> findingResult, String keyword, PricingRuleRow item) {
        List<RuleColumn> ruleColumns = item.getRuleColumns();
        for(RuleColumn column: ruleColumns){
            if(column.getRule().containsValue(keyword)) findingResult.add(1);
        }
    }

    private boolean exactMatch(List<String> keywords, List<Integer> findingResult) {
        return findingResult.size() == keywords.size();
    }

    private boolean noMatch(List<Integer> findingResult) {
        return findingResult.size()== 0;
    }
}
