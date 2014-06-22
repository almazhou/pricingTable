import java.util.*;

public class PricingTable {
    public static final double DEFAULT_PRICE = 100.00;
    private PricingRules pricingRules;

    public PricingTable(PricingRules pricingRules) {

        this.pricingRules = pricingRules;
    }

    public double getPrice(String... keywords) {
        List<String> keywordList = Arrays.asList(keywords);
        int match = pricingRules.match(keywordList);
        if (match == 1) {
            return getPriceForExactMatch(keywordList);
        }
        if (match == -1) {
            return getPriceForFuzzMatch(keywordList);
        }
        return DEFAULT_PRICE;
    }

    private double getPriceForFuzzMatch(List<String> keywordList) {
        List<List<Map<String, Double>>> matchResult = pricingRules.matches(keywordList);
        List<Double> prices = new ArrayList<Double>();
        for(List<Map<String, Double>> list:matchResult){
            Double price = list.get(0).get("price");
            prices.add(price);
        }
        Double max = Collections.max(prices);

        return max;
    }

    private double getPriceForExactMatch(List<String> keywordList) {
        List<List<Map<String, Double>>> matchResult = pricingRules.matches(keywordList);
        List<Map<String, Double>> matchRow = pricingRules.findMatchRow(keywordList, matchResult);
        return matchRow.get(0).get("price");
    }
}
