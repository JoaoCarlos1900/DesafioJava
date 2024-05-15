package util;

import Model.ExchangeRates;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/9496fc3f6d063898cee00cd7/latest/USD";

    public static String getExchangeRatesJson() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();

        if (statusCode == 200) {
            return response.body();
        } else {
            throw new IOException("Falha na requisição HTTP: " + statusCode);
        }
    }

    public static void main(String[] args) {
        try {
            String responseBody = getExchangeRatesJson();
            System.out.println("Resposta da API:");
            System.out.println(responseBody);

            Gson gson = new Gson();
            ExchangeRates exchangeRates = gson.fromJson(responseBody, ExchangeRates.class);
            System.out.println("Taxas de câmbio: " + exchangeRates);
        } catch (IOException e) {
            System.err.println("Erro de E/S ao fazer solicitação HTTP: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Manter a flag de interrupção
            System.err.println("A solicitação HTTP foi interrompida: " + e.getMessage());
        }
    }
}
