package com.aluracursos.challenge.conversor;

import java.util.Map;

public class CurrencyConverter {

    public double convert(double amount, String fromCurrency, String toCurrency, ExchangeRateResponse rates) {
        Map<String, Double> conversionRates = rates.conversion_rates();

        if (!conversionRates.containsKey(fromCurrency)) {
            throw new IllegalArgumentException("Moneda de origen no válida: " + fromCurrency);
        }
        if (!conversionRates.containsKey(toCurrency)) {
            throw new IllegalArgumentException("Moneda de destino no válida: " + toCurrency);
        }

        // La API nos da las tasas con respecto a la moneda base.
        // Si la moneda base es 'fromCurrency', la conversión es directa.
        // Si no, necesitamos hacer una conversión intermedia (ej. de XXX a USD y luego de USD a YYY)
        // Pero la API que usamos nos permite pedir la moneda base que queramos,
                // así que el 'fromCurrency' será nuestra moneda base.
        double rate = conversionRates.get(toCurrency);
        return amount * rate;
    }
}
