package com.boxy.conversormonedas.services;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonParser {
    private final Gson gson;

    public JsonParser() {
        this.gson = new Gson();
    }

    public <T> T parseJson(String json, Class<T> targetType) {
        try {
            return gson.fromJson(json, targetType);
        } catch (JsonSyntaxException e) {
            System.out.println("Error: Failed to parse JSON: " + e.getMessage());
            return null;
        }
    }

}
