package com.boxy.conversormonedas.principal;

import com.boxy.conversormonedas.models.ResponseAPI;
import com.boxy.conversormonedas.services.APIClient;
import com.boxy.conversormonedas.services.JsonParser;
import com.boxy.conversormonedas.services.ValidatingInputUser;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Scanner;

public class Principal {
    private final Dotenv dotenv = Dotenv.configure().load();
    private final String apiKey = dotenv.get("API_KEY");
    private final JsonParser parser = new JsonParser();
    private final ValidatingInputUser validatingInputUser = new ValidatingInputUser();

    public void execute() {
        final int menuExitNumber = 7;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            showMenu();
            int choice = validatingInputUser.getValidMenuChoice(scanner, menuExitNumber);
            if (choice == menuExitNumber) {
                break;
            }

            double amount = validatingInputUser.getValidAmount(scanner);

            String url = constructURL(choice, amount);
            String responseData = APIClient.fetchDataFromAPI(url);
            if (responseData != null) {
                ResponseAPI dataConverted = parser.parseJson(responseData, ResponseAPI.class);
                if (dataConverted != null) {
                    System.out.println("Resultado: " + dataConverted.conversion_result());
                } else {
                    System.out.println("Error al parsear la respuesta del API.");
                }
            } else {
                System.out.println("Error al obtener datos del API.");
            }
        }
        scanner.close();
    }

    private void showMenu() {
        String menu = """
                ************************************************
                Seleccione una opción:
                1) Dolar -> Peso Argentino
                2) Peso Argentino -> Dolar
                3) Dolar -> Real Brasileño
                4) Real Brasileño -> Dolar
                5) Dolar -> Peso Colombiano
                6) Peso Colombiano -> Dolar
                7) Salir
                ************************************************
                """;
        System.out.println(menu);
    }

    private String constructURL(int choice, double amount) {
        String baseCode = "USD";
        String targetCode;
        switch (choice) {
            case 1: targetCode = "ARS"; break;
            case 2: baseCode = "ARS"; targetCode = "USD"; break;
            case 3: targetCode = "BRL"; break;
            case 4: baseCode = "BRL"; targetCode = "USD"; break;
            case 5: targetCode = "COP"; break;
            case 6: baseCode = "COP"; targetCode = "USD"; break;
            default: return null;
        }
        return "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + baseCode + "/" + targetCode + "/" + amount;
    }
}
