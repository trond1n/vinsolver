package com.dominik.klientbackend.controller;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import data.CarData;

import java.util.*;

@RestController
@RequestMapping("/carCheck")
    //@CrossOrigin(origins = {"http://localhost:80","http://jellyfin5.cfd:80", "http://chemckmotive.com:80","http://localhost:3000"})
public class CarCheckController {

    // DTO wejściowy
    public static class CarRequest {
        public String brand;
        public String model;
        public int year;
        public int mileage;
        public String engine;
        public String transmission;
    }

    // DTO wyjściowy
    public static class CarResponse {
        public Map<String, Map<String, List<String>>> categories;

        public CarResponse(Map<String, Map<String, List<String>>> categories) {
            this.categories = categories;
        }
    }


    @PostMapping
    public CarResponse checkCar(@RequestBody CarRequest req) {
        if (!CarData.isValid(req.brand, req.model, req.engine)) {
            Map<String, Map<String, List<String>>> errorMap = new HashMap<>();
            Map<String, List<String>> errorContent = new HashMap<>();
            errorContent.put("zalety", Collections.emptyList());
            errorContent.put("wady", Arrays.asList("❌ Niepoprawne dane: marka/model/silnik nie istnieje w bazie."));
            errorMap.put("Błąd walidacji", errorContent);
            return new CarResponse(errorMap);
        }
        String prompt = String.format(

                "Działaj jako profesjonalny mechanik samochodowy i ekspert w dziedzinie oceny pojazdów. \n" +
                        "INFORMACJE O SAMOCHODZIE: Marka: %s\n" +
                        "Model: %s\n" +
                        "Rok Produkcji: %d\n" +
                        "Przebieg: %dkm\n" +
                        "Silnik: %skm\n" +
                        "Skrzynia biegów: %skm\n" +
                        "\n" +
                        "Twoim zadaniem jest dostarczenie kompleksowej oceny stanu pojazdu w formacie JSON, który można bezpośrednio sparsować naszym backendem. \n" +
                        "\n" +
                        "Wymagania:\n" +
                        "1. Odpowiedź musi być **jednym obiektem JSON**, bez dodatkowych tagów.\n" +
                        "2. Każda kluczowa kategoria (np. \"Ocena Ogólna\", \"Silnik\", \"Skrzynia biegów\", \"Zawieszenie\", \"Układ Kierowniczy\", \"Układ Hamulcowy\", \"Nadwozie i Podwozie\", \"Wnętrze i Wyposażenie\", \"Elektronika\") powinna być kluczem w głównym obiekcie.\n" +
                        "3. Każda kategoria musi mieć dokładnie dwa pola:\n" +
                        "   - \"zalety\" – lista stringów opisujących mocne strony\n" +
                        "   - \"wady\" – lista stringów opisujących słabe strony\n" +
                        "4. **Nie dodawaj żadnych dodatkowych pól** ani komentarzy, tylko \"zalety\" i \"wady\".\n" +
                        "5. Treść powinna być wnikliwa, dokładna, oparta na wiedzy motoryzacyjnej oraz typowych problemach danego modelu i rocznika.\n" +
                        "6. Ton odpowiedzi obiektywny i rzeczowy.\n" +
                        "\n" +
                        "Przykładowa struktura JSON, jakiej oczekuję:\n" +
                        "\n" +
                        "{\n" +
                        "  \"Ocena Ogólna\": {\n" +
                        "    \"zalety\": [\"Przykładowa zaleta 1\", \"Przykładowa zaleta 2\"],\n" +
                        "    \"wady\": [\"Przykładowa wada 1\", \"Przykładowa wada 2\"]\n" +
                        "  },\n" +
                        "  \"Silnik\": {\n" +
                        "    \"zalety\": [\"Zaleta silnika 1\", \"Zaleta silnika 2\"],\n" +
                        "    \"wady\": [\"Wada silnika 1\", \"Wada silnika 2\"]\n" +
                        "  },\n" +
                        "  ...\n" +
                        "}", req.brand, req.model, req.year, req.mileage, req.engine, req.transmission
        );

        System.out.println("Start1 generowania odpowiedzi");


        try {
            System.out.println("Start2 generowania odpowiedzi");
            // inicjalizacja klienta Gemini (SDK automatycznie pobiera klucz z GOOGLE_API_KEY)
            Client client = Client.builder().apiKey("AIzaSyDsQs1Ca2qYGgTWjLWYvvFc89Yf3GFNPrs").build();


            GenerateContentResponse response = client.models.generateContent(
                    "gemini-2.5-flash",
                    prompt,
                    null
            );

            String replyText = response.text();
            replyText = replyText.replaceAll("(?s)^```.*?\\n", "").replaceAll("```$", "");
            // parsowanie JSON z Gemini
            System.out.println(replyText);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Map<String, List<String>>> parsed = mapper.readValue(
                    replyText,
                    new TypeReference<Map<String, Map<String, List<String>>>>(){}
            );

            return new CarResponse(parsed);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Map<String, List<String>>> errorMap = new HashMap<>();
            Map<String, List<String>> errorContent = new HashMap<>();
            errorContent.put("Pros", new ArrayList<String>());
            errorContent.put("Cons", Arrays.asList("❌ Error parsing Gemini response: " + e.getMessage()));
            errorMap.put("Error", errorContent);
            return new CarResponse(errorMap);
        }
    }

    // testowy GET endpoint
    @GetMapping("/test")
    public CarResponse test() {
        Map<String, Map<String, List<String>>> testMap = new HashMap<>();
        Map<String, List<String>> sampleCategory = new HashMap<>();
        sampleCategory.put("Pros", Arrays.asList("Dobry komfort", "Niska cena"));
        sampleCategory.put("Cons", Arrays.asList("Drogi serwis"));
        testMap.put("Overall", sampleCategory);

        return new CarResponse(testMap);
    }
}
