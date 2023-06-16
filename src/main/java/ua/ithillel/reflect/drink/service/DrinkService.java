package ua.ithillel.reflect.drink.service;

import ua.ithillel.reflect.drink.exception.DrinkClientException;
import ua.ithillel.reflect.drink.model.Drink;

public interface DrinkService {
    Drink getDrinkByName(String name) throws DrinkClientException;
}
