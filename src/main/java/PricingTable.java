import java.util.Arrays;

public class PricingTable {
    private PricingRules pricingRules;

    public PricingTable(PricingRules pricingRules) {

        this.pricingRules = pricingRules;
    }

    public double getPrice(String... keywords) {
        int match = pricingRules.match(Arrays.asList(keywords));
//        if(match == 1) return
        return 0;
    }
}
