package com.aluracursos.challenge.conversor;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateApiClient {

    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/";
    // IMPORTANTE: Reemplaza "YOUR_API_KEY" con tu propia clave de API de ExchangeRate-API
    private static final String API_KEY = "YOUR_API_KEY";

    public ExchangeRateResponse getRates(String baseCurrency) throws IOException, InterruptedException {
        String urlStr = API_BASE_URL + API_KEY + "/latest/" + baseCurrency;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlStr))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Error en la respuesta de la API: " + response.statusCode());
        }

        Gson gson = new Gson();
        return gson.fromJson(response.body(), ExchangeRateResponse.class);
    }
}
