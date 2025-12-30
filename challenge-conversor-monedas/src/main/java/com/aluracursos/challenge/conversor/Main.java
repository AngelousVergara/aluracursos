package com.aluracursos.challenge.conversor;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExchangeRateApiClient apiClient = new ExchangeRateApiClient();
        CurrencyConverter converter = new CurrencyConverter();
        int option = 0;

        System.out.println("***************************************************");
        System.out.println("¡Bienvenido al Conversor de Monedas!");
        System.out.println("IMPORTANTE: Para usar este programa, necesitas una API Key de ExchangeRate-API.");
        System.out.println("Ve a https://www.exchangerate-api.com/ y regístrate para obtener una clave gratuita.");
        System.out.println("Luego, abre el archivo 'ExchangeRateApiClient.java' y reemplaza 'YOUR_API_KEY' con tu clave.");
        System.out.println("***************************************************\n");


        while (option != 7) {
            System.out.println("Seleccione una opción de conversión:");
            System.out.println("1. Dólar (USD) a Peso Argentino (ARS)");
            System.out.println("2. Peso Argentino (ARS) a Dólar (USD)");
            System.out.println("3. Dólar (USD) a Real Brasileño (BRL)");
            System.out.println("4. Real Brasileño (BRL) a Dólar (USD)");
            System.out.println("5. Dólar (USD) a Peso Colombiano (COP)");
            System.out.println("6. Peso Colombiano (COP) a Dólar (USD)");
            System.out.println("7. Salir");
            System.out.print("Opción: ");

            try {
                option = Integer.parseInt(scanner.nextLine());

                if (option >= 1 && option <= 6) {
                    System.out.print("Ingrese la cantidad a convertir: ");
                    double amount = Double.parseDouble(scanner.nextLine());

                    String fromCurrency = "";
                    String toCurrency = "";

                    switch (option) {
                        case 1: fromCurrency = "USD"; toCurrency = "ARS"; break;
                        case 2: fromCurrency = "ARS"; toCurrency = "USD"; break;
                        case 3: fromCurrency = "USD"; toCurrency = "BRL"; break;
                        case 4: fromCurrency = "BRL"; toCurrency = "USD"; break;
                        case 5: fromCurrency = "USD"; toCurrency = "COP"; break;
                        case 6: fromCurrency = "COP"; toCurrency = "USD"; break;
                    }

                    try {
                        ExchangeRateResponse rates = apiClient.getRates(fromCurrency);
                        double convertedAmount = converter.convert(amount, fromCurrency, toCurrency, rates);

                        System.out.println("---------------------------------------------------");
                        System.out.printf("%.2f %s equivale a %.2f %s%n", amount, fromCurrency, convertedAmount, toCurrency);
                        System.out.println("---------------------------------------------------\n");

                    } catch (IOException | InterruptedException e) {
                        System.err.println("Error al obtener las tasas de cambio: " + e.getMessage());
                        System.err.println("Verifica tu clave de API y tu conexión a internet.");
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error en la conversión: " + e.getMessage());
                    }
                } else if (option != 7) {
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }
        }

        System.out.println("Gracias por usar el Conversor de Monedas. ¡Hasta luego!");
        scanner.close();
    }
}