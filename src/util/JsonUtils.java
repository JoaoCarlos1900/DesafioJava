package util;

import com.google.gson.Gson;
import Model.ExchangeRates;
import java.util.Map;

public class JsonUtils {

    public static ExchangeRates parseExchangeRates(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ExchangeRates.class);
    }

    public static void main(String[] args) {
        String jsonResponse = "{\n" +
                "    \"result\": \"success\",\n" +
                "    \"documentation\": \"https://www.exchangerate-api.com/docs\",\n" +
                "    \"terms_of_use\": \"https://www.exchangerate-api.com/terms\",\n" +
                "    \"time_last_update_unix\": 1715731201,\n" +
                "    \"time_last_update_utc\": \"Wed, 15 May 2024 00:00:01 +0000\",\n" +
                "    \"time_next_update_unix\": 1715817601,\n" +
                "    \"time_next_update_utc\": \"Thu, 16 May 2024 00:00:01 +0000\",\n" +
                "    \"base_code\": \"USD\",\n" +
                "    \"conversion_rates\": {\n" +
                "        \"USD\": 1,\n" +
                "        \"AED\": 3.6725,\n" +
                "        \"AFN\": 72.2574,\n" +
                "        \"ALL\": 93.1142,\n" +
                "        \"AMD\": 387.7417,\n" +
                "        \"ANG\": 1.7900,\n" +
                "        \"AOA\": 845.0812,\n" +
                "        \"ARS\": 864.7500,\n" +
                "        \"AUD\": 1.5104,\n" +
                "        \"AWG\": 1.7900,\n" +
                "        \"AZN\": 1.7002,\n" +
                "        \"BAM\": 1.8082,\n" +
                "        \"BBD\": 2.0000,\n" +
                "        \"BDT\": 116.8173,\n" +
                "        \"BGN\": 1.8086,\n" +
                "        \"BHD\": 0.3760,\n" +
                "        ...\n" +
                "    }\n" +
                "}";


        ExchangeRates exchangeRates = parseExchangeRates(jsonResponse);

        if (exchangeRates != null && exchangeRates.getConversionRates() != null) {
            Map<String, Double> rates = exchangeRates.getConversionRates();
            
            String[] currencyCodes = {"ARS", "BOB", "BRL", "CLP", "COP", "USD"};

            System.out.println("Valores das moedas:");
            for (String currencyCode : currencyCodes) {
                if (rates.containsKey(currencyCode)) {
                    System.out.println(currencyCode + " - " + rates.get(currencyCode));
                } else {
                    System.out.println(currencyCode + " - Valor não encontrado");
                }
            }
        } else {
            System.out.println("Resposta JSON inválida ou vazia.");
        }
    }
}
