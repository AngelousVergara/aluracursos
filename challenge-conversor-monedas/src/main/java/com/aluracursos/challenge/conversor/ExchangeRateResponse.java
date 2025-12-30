package com.aluracursos.challenge.conversor;

import java.util.Map;

public record ExchangeRateResponse(String base_code, Map<String, Double> conversion_rates) {
}
