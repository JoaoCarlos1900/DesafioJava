package Model;

import java.util.Map;

public class ExchangeRates {
    private Map<String, Double> conversionRates;

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }
}
