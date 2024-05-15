import Model.ExchangeRates;
import util.ApiClient;
import util.JsonUtils;
import util.UserInterface;

import java.io.IOException;
import java.util.Map;

public class CurrencyConverter {

    private static Map<String, Double> exchangeRates;

    public static void main(String[] args) {
        try {
            // Obter o JSON das taxas de câmbio da API e parsear para ExchangeRates
            String exchangeRatesJson = ApiClient.getExchangeRatesJson();
            ExchangeRates rates = JsonUtils.parseExchangeRates(exchangeRatesJson);

            // Preencher o mapa de taxas de câmbio
            exchangeRates = rates.getConversionRates();

            // Exibir o menu e interagir com o usuário
            UserInterface.displayMenu();

            boolean continueProgram = true;

            while (continueProgram) {
                String choice = UserInterface.getUserInput("Escolha uma opção: ");

                switch (choice) {
                    case "1":
                        performCurrencyConversion();
                        break;
                    case "2":
                        continueProgram = false;
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            }

            UserInterface.closeScanner();
            System.out.println("Programa encerrado.");
        } catch (IOException e) {
            System.out.println("Erro ao acessar a API de taxas de câmbio: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Manter a flag de interrupção
            System.out.println("A solicitação HTTP foi interrompida: " + e.getMessage());
        }
    }

    private static void performCurrencyConversion() {
        if (exchangeRates == null) {
            System.out.println("As taxas de câmbio não foram carregadas corretamente. Tente novamente mais tarde.");
            return;
        }

        String fromCurrency = UserInterface.getUserInput("Digite a moeda de origem (ex: USD): ").toUpperCase();
        String toCurrency = UserInterface.getUserInput("Digite a moeda de destino (ex: EUR): ").toUpperCase();
        double amount = UserInterface.getDoubleInput("Digite o valor a ser convertido: ");

        double convertedAmount = convertCurrency(fromCurrency, toCurrency, amount);
        UserInterface.displayResult(String.format("%.2f %s é equivalente a %.2f %s", amount, fromCurrency, convertedAmount, toCurrency));
    }

    private static double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        if (exchangeRates == null) {
            return 0.0;
        }

        double fromRate = exchangeRates.getOrDefault(fromCurrency, 0.0);
        double toRate = exchangeRates.getOrDefault(toCurrency, 0.0);

        if (fromRate == 0.0 || toRate == 0.0) {
            System.out.println("Taxa de câmbio não encontrada para uma das moedas.");
            return 0.0;
        }

        return (amount / fromRate) * toRate;
    }
}
