package ua.ithillel.reflect.drink.client;

import ua.ithillel.reflect.anno.Cacheable;
import ua.ithillel.reflect.drink.exception.DrinkClientException;
import ua.ithillel.reflect.drink.model.response.CocktailResponse;

public interface DrinkClient {
    @Cacheable
    CocktailResponse getByName(String name) throws DrinkClientException;
}
