package com.boxy.conversormonedas.models;

public record ResponseAPI(
        String base_code,
        String target_code,
        double conversion_rate,
        double conversion_result
) {
}
