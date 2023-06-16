package ua.ithillel.reflect.drink.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import ua.ithillel.reflect.anno.Cacheable;
import ua.ithillel.reflect.drink.exception.DrinkClientException;
import ua.ithillel.reflect.drink.model.response.CocktailResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CocktailDbClient implements DrinkClient {
    private static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1";
    private final HttpClient httpClient;

    private final ObjectMapper mapper;

    public CocktailDbClient(HttpClient httpClient, ObjectMapper mapper) {
        this.httpClient = httpClient;
        this.mapper = mapper;
    }

    @Override
    @Cacheable
    public CocktailResponse getByName(String name) throws DrinkClientException {
        try {
            String encodedName = name.replace(" ", "%20");
            URI uri = new URI(BASE_URL + "/search.php?s=" + encodedName);

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(uri)
                    .build();

            HttpResponse<String> response 
                    = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            

            if (response.statusCode() == 200) {
                String body = response.body();
                return mapper.readValue(body, CocktailResponse.class);
            }

            throw new DrinkClientException(String.format("Cannot find requested drink '%s':", name));

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
