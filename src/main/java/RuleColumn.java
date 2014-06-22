import java.util.Map;

public class RuleColumn {
    private Map<String, String> rule;

    public RuleColumn(Map<String, String> rule) {
        this.rule = rule;
    }

    public Map<String, String> getRule() {
        return rule;
    }
}
